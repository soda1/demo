package com.eric.netty.savefile.command;

/**
 * @author eric
 * @date 5/26/2024
 */
public interface Command {

    /**
     * i don't know pass args in execute method is a good idea for command pattern
     * @param args
     */
    void execute(Object[] args);
}
