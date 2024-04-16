

function handleOffer(offer) {
    peerConnection.setRemoteDescription(new RTCSessionDescription(offer));

    // create and send an answer to an offer
    peerConnection.createAnswer(function(answer) {
        peerConnection.setLocalDescription(answer);
        send({
            event : "answer",
            data : answer
        });
    }, function(error) {
        alert("Error creating an answer");
    });

};

function handleCandidate(candidate) {
    peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
};

function handleAnswer(answer) {
    peerConnection.setRemoteDescription(new RTCSessionDescription(answer));
    console.log("connection established successfully!!");
};

function sendMessage() {
    dataChannel.send(input.value);
    input.value = "";
}
///***
// * 本地预览
// * @param meetingId
// */
//async function startLocalPreview(ws) {
//    try {
//        const constraints = {
//            video: true,
//            audio: true // 不需要音频
//        };
//        const stream = await navigator.mediaDevices.getUserMedia(constraints);
//        const videoElement = document.getElementById('localVideo');
//        videoElement.srcObject = stream;
//
//     const peerConnection = new RTCPeerConnection({
//            iceServers: [
//                { url: 'stun:stun.l.google.com:19302' },
//                { url: 'stun:stun1.l.google.com:19302' }
//            ]
//        });
//          // 创建并发送offer
//        peerConnection.createOffer()
//            .then(offer => {
//                // 设置本地描述
//                peerConnection.setLocalDescription(offer);
//                // 发送offer到远程
//                ws.send(JSON.stringify(offer));
//            })
//            .catch(error => {
//                console.error('创建offer失败:', error);
//            });
//
//        ws.onopen = function(event) {
//            console.log('Connected to WebSocket server');
//            if (localStream) {
//                const mediaRecorder = new MediaRecorder(videoElement);
//                mediaRecorder.ondataavailable = function(event) {
//                    if (event.data && event.data.size > 0) {
//                        ws.send(event.data); // 将视频流数据发送到服务器
//                    }
//                };
//                mediaRecorder.start(1000); // 每秒发送一次数据
//            }
//        };
//
//
////        ws.onerror = function(event) {
////            console.error('WebSocket error:', event);
////        };
////
////        ws.onclose = function(event) {
////            console.log('WebSocket connection closed');
////        };
//
//    } catch (error) {
//        console.error('Error accessing local camera:', error);
//    }
//}


function createConnection() {
  dataChannelSend.placeholder = '';
  var servers = null;
  pcConstraint = null;
  dataConstraint = null;
  trace('Using SCTP based data channels');
  // For SCTP, reliable and ordered delivery is true by default.
  // Add localConnection to global scope to make it visible
  // from the browser console.
  window.localConnection = localConnection =
      new RTCPeerConnection(servers, pcConstraint);
  trace('Created local peer connection object localConnection');

  sendChannel = localConnection.createDataChannel('sendDataChannel',
      dataConstraint);
  trace('Created send data channel');

  localConnection.onicecandidate = iceCallback1;
  sendChannel.onopen = onSendChannelStateChange;
  sendChannel.onclose = onSendChannelStateChange;

  // Add remoteConnection to global scope to make it visible
  // from the browser console.
  window.remoteConnection = remoteConnection =
      new RTCPeerConnection(servers, pcConstraint);
  trace('Created remote peer connection object remoteConnection');

  remoteConnection.onicecandidate = iceCallback2;
  remoteConnection.ondatachannel = receiveChannelCallback;

  localConnection.createOffer().then(
    gotDescription1,
    onCreateSessionDescriptionError
  );
  startButton.disabled = true;
  closeButton.disabled = false;
}


function start (ws) {

// 处理WebSocket连接打开事件
        ws.onopen = () => {
            // 获取本地视频流
            navigator.mediaDevices.getUserMedia({ video: true, audio: true })
                .then(stream => {
                    localVideo.srcObject = stream;
                    localStream = stream;

                    // 创建RTCPeerConnection对象
                    peerConnection = new RTCPeerConnection();

                    // 添加本地视频流到RTCPeerConnection
                    localStream.getTracks().forEach(track => peerConnection.addTrack(track, localStream));

                    // 监听远程流
                    peerConnection.ontrack = event => {
                        remoteVideo.srcObject = event.streams[0];
                    };

                    // 发送offer
                    peerConnection.createOffer()
                        .then(offer => {
                            peerConnection.setLocalDescription(offer);
                            // 发送offer到远程
                            ws.send(JSON.stringify(offer));
                        })
                        .catch(error => {
                            console.error('创建offer失败:', error);
                        });
                })
                .catch(error => {
                    console.error('获取本地视频流失败:', error);
                });
        };

        // 处理WebSocket消息
        ws.onmessage = event => {
            const message = JSON.parse(event.data);
            if (message.type === 'answer') {
                // 处理远程的answer
                peerConnection.setRemoteDescription(new RTCSessionDescription(message));
            } else if (message.type === 'candidate') {
                // 添加远程的ICE候选者
                peerConnection.addIceCandidate(new RTCIceCandidate(message.candidate));
            }
        };
}