package com.eric.netty.savefile.server;

import com.eric.netty.savefile.command.FileSystemReceiver;
import com.eric.netty.savefile.command.HelpCommand;
import com.eric.netty.savefile.command.Invoker;
import com.eric.netty.savefile.command.NextFileCommand;
import com.eric.netty.savefile.command.PrevFileCommand;
import com.eric.netty.savefile.command.QueryCommand;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @author eric
 * @date 5/5/2024
 */
public class ServerReadMsgHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LogManager.getLogger(ServerReadMsgHandler.class);

    private Invoker invoker;

    public ServerReadMsgHandler() throws IOException {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String command = (String) msg;
        // split string by empty space
        String[] commands = command.split(" ");
        logger.debug("read msg:{}", msg);
        //remove commands first element
        String[] newArray2 = new String[commands.length - 1];
        System.arraycopy(commands, 1, newArray2, 0, newArray2.length);
        invoker.setArgs(newArray2);
        invoker.invoke(commands[0]);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String basePath = "C:\\Users\\Eric Li\\Desktop";
        FileSystemReceiver fileSystemReceiver = new FileSystemReceiver(basePath, ctx.channel());
        QueryCommand queryCommand = new QueryCommand(fileSystemReceiver);
        HelpCommand helpCommand = new HelpCommand(fileSystemReceiver);
        NextFileCommand nextFileCommand = new NextFileCommand(fileSystemReceiver);
        PrevFileCommand prevFileCommand = new PrevFileCommand(fileSystemReceiver);
        invoker = new Invoker(queryCommand, helpCommand, prevFileCommand, nextFileCommand);
        logger.debug("connect active");
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
