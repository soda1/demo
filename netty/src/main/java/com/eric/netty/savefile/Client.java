package com.eric.netty.savefile;

import con.eric.utils.LoggerUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

/**
 * @author eric
 * @date 5/5/2024
 */
public class Client {

    private static Logger logger = LoggerUtils.getLogger(Client.class);

    private EventLoopGroup group = new NioEventLoopGroup();

    private Channel channel;

    private ChannelFuture init(int port) {
        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture future = bootstrap.channel(NioSocketChannel.class)
                .group(group)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast("handler", new ClientChannelHandler());
                                // outbound order must higher than inbound
                                // .addLast("outHandler", new ClientChannelOutboundHandler())
                                // .addLast("handler1", new ClientChannelHandler())
                                // .addLast("handler", new ClientChannelHandle2());

                    }
                })
                .connect(new InetSocketAddress(port));
        channel = future.channel();
        return future;
    }

    public void destroy() {
        if (channel != null) {
            channel.close();
        }
        group.shutdownGracefully();
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Client client = new Client();
        client.init(10034);
        ChannelFuture writeFuture = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Channel channel = client.channel;
        for (; ; ) {
            String line = in.readLine();
            writeFuture = channel.writeAndFlush(Unpooled.copiedBuffer((line + "\r\n").getBytes()));

            writeFuture.addListener(it -> {
              logger.info("send msg:{}", it.isSuccess());
            });
            if ("quit".equals(line.toLowerCase())) {
                // client.channel.closeFuture().awaitUninterruptibly();
                break;
            }
        }
        client.destroy();
    }

}
