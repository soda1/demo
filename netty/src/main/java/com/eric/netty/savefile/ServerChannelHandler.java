package com.eric.netty.savefile;

import io.netty.channel.ChannelPipeline;
import org.apache.logging.log4j.LogManager;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;

/**
 * @author eric
 * @date 5/5/2024
 */
public class ServerChannelHandler extends ChannelInboundHandlerAdapter {

    Path tmpPath = Files.createTempFile("temp", ".txt");
    OutputStream out = new FileOutputStream(tmpPath.toFile());
    private static final Logger logger = LogManager.getLogger(ServerChannelHandler.class);

    public ServerChannelHandler() throws IOException {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        logger.info(MessageFormat.format("read msg:{0}", in.toString(StandardCharsets.UTF_8)));
        while (in.isReadable()) {
            out.write(in.readByte());
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("connect active");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        out.flush();
        out.close();
        logger.info("finish");
    }
}
