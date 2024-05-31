package com.eric.netty.savefile.command;

import com.eric.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;

/**
 * @author eric
 * @date 5/26/2024
 */
public class Invoker {

    private static final Logger logger = LoggerUtils.getLogger(Invoker.class);

    private Command query;

    private Command help;

    private Command back;

    private Command next;

    private Object[]  args;

    public Invoker(Command query, Command help, Command back, Command next) {
        this.query = query;
        this.help = help;
        this.back = back;
        this.next = next;
    }

    public void invoke(String type) {
        logger.debug("type:{}", type);
        switch (type) {
            case "query":
                query.execute(args);
                break;
            case "back":
                back.execute(args);
                break;
            case "next":
                next.execute(args);
                break;
            default:
                help.execute(args);
        }
        this.args = null;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
