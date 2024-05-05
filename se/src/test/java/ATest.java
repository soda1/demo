import org.junit.Test;

import java.util.*;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ATest {


    @Test
    public void test01() {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Math.round(-11.5));

    }

    @Test
    public void test02() {
        //基本类型缓存
        Integer i = -123;
        Integer i1 = -123;
        Integer i5 = new Integer(-123);
        Integer i2 = 330;
        Integer i4 = 330;
        System.out.println(i == i1);
        System.out.println(i2 == i4);
        System.out.println(i == i5);
    }

    @Test
    public void outOfTime() throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                throw new RuntimeException();
            }
        }, 1);
        TimeUnit.SECONDS.sleep(1);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                throw new RuntimeException();
            }
        }, 1);
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    public void dp() {
        int[] G = new int[4];
        G[0] = 1;
        G[1] = 1;
        for(int i = 2; i <= 3; i++){
            for(int j = 1; j <= i; j ++){
                G[i] += G[j - 1] * G[i - j];
            }
        }
        System.out.println("fafa");
    }

    @Test
    public void remove() {

        System.out.println("leetcode".compareTo("coding"));

    }
}
