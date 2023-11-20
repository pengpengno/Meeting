package com.github.peng.connect.handler.server;

import cn.hutool.core.util.StrUtil;
import com.github.peng.connect.connection.server.ServerToolkit;
import com.github.peng.connect.connection.server.context.ConnectionGroupRoom;
import com.github.peng.connect.utils.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.rtsp.RtspHeaderNames;
import io.netty.handler.codec.rtsp.RtspResponseStatuses;
import io.netty.handler.codec.rtsp.RtspVersions;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@ChannelHandler.Sharable
@Slf4j
public class RtspServerHandler extends SimpleChannelInboundHandler<DefaultHttpRequest> {


        @Override
        protected void channelRead0(ChannelHandlerContext ctx, DefaultHttpRequest o) {

            QueryStringDecoder uri = new QueryStringDecoder(o.uri());
            String path = uri.path();
            System.out.println(path);
            if (path.contains("/desktop"))
            {

                log.info("active desktop ");
                List<String> split = StrUtil.split(path, "/");

                String group = split.getLast();

                ConnectionGroupRoom connectionGroupRoom = ServerToolkit.contextAction()
                        .applyConnectionGroup(group);
//                connectionGroupRoom.getByteFlux().

                ByteBuf byteBuf1 = connectionGroupRoom.pollByteBuf();

                byte[] bytes = ByteBufUtils.readByteBuf2Array(byteBuf1);

                ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);

                FullHttpResponse response = new DefaultFullHttpResponse(RtspVersions.RTSP_1_0,
                        RtspResponseStatuses.OK , byteBuf );
                log.info("receive  rtsp request ");
                final String cseq = o.headers().get(RtspHeaderNames.CSEQ);
                response.headers().add(RtspHeaderNames.CSEQ, cseq);
//                ctx.writeAndFlush(connectionGroupRoom.offerFrameData());
                ctx.writeAndFlush(response);


            }else {
//                super.channelRead(ctx, o);
            }
        }

}
