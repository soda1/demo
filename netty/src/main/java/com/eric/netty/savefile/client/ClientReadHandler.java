package com.eric.netty.savefile.client;

import com.eric.netty.savefile.bo.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @author eric
 * @date 5/5/2024
 */
public class ClientReadHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LogManager.getLogger(ClientReadHandler.class);


    public ClientReadHandler() throws IOException {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Response response = (Response) msg;
        if (CollectionUtils.isNotEmpty(response.getDirectories())) {
            response.getDirectories().forEach(directory -> {
                //display directory properties in a line each property format is key:value and four spaces between each property
                StringBuilder formattedDirectory = new StringBuilder();
                formattedDirectory.append("Name: ").append(directory.getName()).append("    ");
                formattedDirectory.append("Is Directory: ").append(directory.isDirectory()).append("    ");
                // Add more properties if needed
                System.out.println(formattedDirectory);
            });
        }
        if (response.getCurrentDirectory() != null) {
            System.out.println("Current Directory: " + response.getCurrentDirectory());
        }
        logger.debug("read msg:{}", msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

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
        logger.debug("finish");
    }
}
