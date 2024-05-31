package com.eric.netty.savefile.command;

/**
 * @author eric
 * @date 5/27/2024
 */
public class HelpCommand implements Command{

    private FileSystemReceiver receiver;

    public HelpCommand(FileSystemReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(Object[] args) {
        receiver.help();
    }
}
