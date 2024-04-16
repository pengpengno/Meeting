async function startLocalPreview() {
    try {
        const constraints = {
            video: true,
            audio: true // 不需要音频
        };
        const stream = await navigator.mediaDevices.getUserMedia(constraints);
        const videoElement = document.getElementById('localVideo');
        videoElement.srcObject = stream;
    } catch (error) {
        console.error('Error accessing local camera:', error);
    }
}


async function startLocalPreview(video = true , audio = true) {
    try {
        const constraints = {
            video: video,
            audio: audio // 不需要音频
        };
        const stream = await navigator.mediaDevices.getUserMedia(constraints);
        const videoElement = document.getElementById('localVideo');
        videoElement.srcObject = stream;
    } catch (error) {
        console.error('Error accessing local camera:', error);
    }
}


// 发送视频流数据到服务器
function sendVideoStream(localStream) {
    const ws = new WebSocket('ws://localhost:8080/path');

    ws.onopen = function(event) {
        console.log('Connected to WebSocket server');
        if (localStream) {
            const mediaRecorder = new MediaRecorder(localStream);
            mediaRecorder.ondataavailable = function(event) {
                if (event.data && event.data.size > 0) {
                    ws.send(event.data); // 将视频流数据发送到服务器
                }
            };
            mediaRecorder.start(1000); // 每秒发送一次数据
        }
    };

    ws.onerror = function(event) {
        console.error('WebSocket error:', event);
    };

    ws.onclose = function(event) {
        console.log('WebSocket connection closed');
    };
}