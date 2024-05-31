package com.eric.netty.savefile.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.net.InetSocketAddress;

/**
 * @author eric
 * @date 5/5/2024
 */
public class Server {

    private Channel channel;
    private EventLoopGroup group = new NioEventLoopGroup();

    public ChannelFuture init(int port) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                // serialized po to byteBuf
                                .addLast("objEncode", new ObjectEncoder())
                                .addLast("objDecode", new ObjectDecoder(ClassResolvers
                                        .cacheDisabled(this.getClass().getClassLoader())))
                                // business logic
                                .addLast("read", new ServerReadMsgHandler());
                    }
                });
        ChannelFuture future = serverBootstrap.bind().syncUninterruptibly();
        channel = future.channel();
        return future;
    }

    public void destroy() {
        if (channel != null) {
            channel.close();
        }
        group.shutdownGracefully();
    }

    public static void main(String[] args) {
        Server server = new Server();
        ChannelFuture future = server.init(10034);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> server.destroy()));

        future.channel().closeFuture().syncUninterruptibly();
    }
}
