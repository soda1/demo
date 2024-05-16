package com.eric.netty.savefile;

import con.eric.utils.LoggerUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import jdk.nashorn.internal.runtime.options.LoggingOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.EntryMessage;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

/**
 * @author eric
 * @date 5/6/2024
 */
public class ClientChannelHandler extends ChannelOutboundHandlerAdapter {

    private static final Logger logger = LoggerUtils.getLogger(ClientChannelHandler.class);


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ctx.writeAndFlush(msg);
        // super.write(ctx, msg, promise);
    }
}
