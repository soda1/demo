package com.eric.netty.savefile;

import com.eric.utils.LoggerUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author eric
 * @date 5/24/2024
 */
public class MsgDecode extends MessageToMessageDecoder<String> {
    private static Logger logger = LoggerUtils.getLogger(MsgDecode.class);
    @Override
    protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        logger.debug("msg be decode:{}", msg);
        out.add(msg.substring(3));
    }
}
