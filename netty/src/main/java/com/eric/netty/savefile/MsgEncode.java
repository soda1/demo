package com.eric.netty.savefile;

import com.eric.utils.LoggerUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author eric
 * @date 5/24/2024
 */
public class MsgEncode extends MessageToMessageEncoder<String> {

    private static Logger logger = LoggerUtils.getLogger(MsgEncode.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        logger.debug("msg be encode:{}", msg);
        out.add("FFF" + msg);
    }
}
