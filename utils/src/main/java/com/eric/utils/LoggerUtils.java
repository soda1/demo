package com.eric.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author eric
 * @date 5/12/2024
 */
public class LoggerUtils {
    public static Logger getLogger(Class<?> clz) {
        return LogManager.getLogger(clz);
    }
}
