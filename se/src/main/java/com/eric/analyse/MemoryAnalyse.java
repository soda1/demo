package com.eric.analyse;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author eric
 * @date 5/20/2024
 */
public class MemoryAnalyse {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> largeList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            largeList.add(i);
        }
        MemoryPoolMXBean edenSpace = null;
        MemoryPoolMXBean survivorSpace = null;
        MemoryPoolMXBean oldGenPool = null;

        for (MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
            String name = pool.getName();
            if (name.contains("Eden") ) {
                 edenSpace = pool;
            } else if (name.contains("Old")) {
                oldGenPool = pool;
            } else if (name.contains("Survivor")) {
                survivorSpace = pool;
            }
        }
        while (true) {
            if (edenSpace != null) {
                System.out.println("Eden Space: " + edenSpace.getUsage().getCommitted() / (1024 * 1024) + " MB");
            }
            if (survivorSpace != null) {
                System.out.println("Survivor Space: " + survivorSpace.getUsage().getCommitted() / (1024 * 1024) + " MB");
            }
            if (edenSpace != null && survivorSpace != null) {
                System.out.println("Young Generation: " + (survivorSpace.getUsage().getCommitted() * 2 +
                        edenSpace.getUsage().getCommitted()) / (1024 * 1024) + " MB");
            }
            if (oldGenPool != null) {
                System.out.println("Old Generation: " + oldGenPool.getUsage().getCommitted() / (1024 * 1024) + " MB");
            }

            TimeUnit.SECONDS.sleep(30);
        }
    }
}
