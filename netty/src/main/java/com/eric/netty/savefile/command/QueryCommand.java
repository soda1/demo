package com.eric.netty.savefile.command;

/**
 * @author eric
 * @date 5/26/2024
 */
public class QueryCommand implements Command {

    private FileSystemReceiver fileSystemReceiver;

    public QueryCommand(FileSystemReceiver fileSystemReceiver) {
        this.fileSystemReceiver = fileSystemReceiver;
    }

    @Override
    public void execute(Object[] args) {
        fileSystemReceiver.query();
    }
}
