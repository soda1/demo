package com.eric.netty.savefile.command;

/**
 * @author eric
 * @date 5/26/2024
 */
public class PrevFileCommand implements Command {

    private FileSystemReceiver fileSystemReceiver;

    public PrevFileCommand(FileSystemReceiver fileSystemReceiver) {
        this.fileSystemReceiver = fileSystemReceiver;
    }

    @Override
    public void execute(Object[] args) {
        fileSystemReceiver.back();
    }
}
