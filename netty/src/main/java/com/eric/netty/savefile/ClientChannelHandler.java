package com.eric.netty.savefile;

import com.eric.utils.LoggerUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.apache.logging.log4j.Logger;

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
