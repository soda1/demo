package com.eric.netty.savefile.command;

/**
 * @author eric
 * @date 5/26/2024
 */
public class NextFileCommand implements Command {

    private FileSystemReceiver fileSystemReceiver;


    public NextFileCommand(FileSystemReceiver fileSystemReceiver) {
        this.fileSystemReceiver = fileSystemReceiver;
    }

    @Override
    public void execute(Object[] args) {
        if (args == null || args.length == 0) {
            return;
        }
        String path = (String) args[0];
        fileSystemReceiver.next(path);
    }
}
