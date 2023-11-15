package com.github.peng.connect.handler.server;

import cn.hutool.core.util.StrUtil;
import com.github.peng.connect.connection.server.ServerToolkit;
import com.github.peng.connect.connection.server.context.ConnectionGroupRoom;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.rtsp.RtspResponseStatuses;
import io.netty.handler.codec.rtsp.RtspVersions;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@ChannelHandler.Sharable
@Slf4j
public class RtspServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {






        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest o) throws Exception
        {


            FullHttpResponse response = new DefaultFullHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.OK);
            log.info("receive  rtsp request ");

            QueryStringDecoder uri = new QueryStringDecoder(o.uri());
            String path = uri.path();
            System.out.println(path);
            if (path.contains("/desktop"))
            {

                log.info("active desktop ");
                List<String> split = StrUtil.split(path, "/");

                String group = split.getLast();

                ConnectionGroupRoom connectionGroupRoom = ServerToolkit.contextAction().applyConnectionGroup(group);

                ctx.writeAndFlush(connectionGroupRoom.offerFrameData());

            }
            super.channelRead(ctx, o);
        }

}
