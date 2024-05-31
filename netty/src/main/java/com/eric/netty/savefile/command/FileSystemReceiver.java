package com.eric.netty.savefile.command;

import com.eric.netty.savefile.bo.Directory;
import com.eric.netty.savefile.bo.Response;
import com.eric.utils.LoggerUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.Data;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author eric
 * @date 5/26/2024
 */
@Data
public class FileSystemReceiver {

    private static Logger logger = LoggerUtils.getLogger(FileSystemReceiver.class);

    private List<String> snapshot;

    private String directoryPath;

    private Channel channel;

    public FileSystemReceiver(String directoryPath, Channel channel) {
        this.directoryPath = directoryPath;
        this.channel = channel;
    }

    public void query() {
        File directory = new File(directoryPath);
        List<Directory> resultList = new ArrayList<>();
        for (File file : directory.listFiles()) {
            resultList.add(new Directory(file.getName(), file.isDirectory()));
        }
        ChannelFuture write = channel.writeAndFlush(new Response(resultList));
        write.addListener(future -> {
            logger.debug("write query status:{}", future.get());
        });
    }

    public void back() {
        directoryPath = directoryPath.substring(0, directoryPath.lastIndexOf(File.separator));
        logger.debug("directory path:{}", directoryPath);
        channel.writeAndFlush(new Response(directoryPath));
    }

    public void next(String nextPath) {
        directoryPath = directoryPath + File.separator + nextPath;
        channel.writeAndFlush(new Response(directoryPath));
    }

    public void help() {
        String s = "" +
                "query: query the file";
        channel.write(s);
    }
}
