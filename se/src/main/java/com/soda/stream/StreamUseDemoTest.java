package com.soda.stream;

import org.junit.Test;

import java.util.*;

/**
 * @author soda
 * @date 2021/7/6
 */
public class StreamUseDemoTest {

    @Test
    public void test() {
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("index", i);
            map.put("num", i);
            map.put("prop", "num");
            mapList.add(map);
        }
        Map<String, Object> map = mapList.stream().findFirst().get();
        System.out.println(map.toString());
        System.out.println(mapList.stream().distinct().count());
        
    }


    @Test
    public void collectorTest() {
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("index", i);
            map.put("num", i);
            mapList.add(map);
        }
        List<Map<String, Object>> orgList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("index", 40);
        orgList.add(map);

        //ArrayList::addAll 这个感觉没啥用,没有添加其他容器的实例数据,BiConsumer需要两个参数,使用实例方法orgList.addAll会报错
        ArrayList<Map<String, Object>> collect = mapList.stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        //System.out.println(list.toString());
    }

    @Test
    public void statisticsTest() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            list.add(random.nextInt(3000));
        }
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        max.ifPresent(System.out::println);
        Optional<Integer> min = list.stream().min(Integer::compareTo);
        min.ifPresent(System.out::println);
        System.out.println(list.stream().mapToInt(Integer::intValue).sum());
        System.out.println(list.stream().mapToInt(Integer::intValue).count());
    }

    /**
     * 统计不重复的字母数
     */
    @Test
    public void flatMapTest() {
        List<String> list = Arrays.asList("hello", "world", "and", "you");
        long count = list.stream().map(e -> e.split("")).flatMap(Arrays::stream).distinct().count();
        System.out.println(count);
    }

    @Test
    public void reduceTest() {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            list.add(random.nextInt(3000));
        }
        //最小值
        Optional<Integer> reduce = list.stream().reduce((a, b) -> {
            if (a > b) {
                return b;
            } else {
                return a;
            }
        });
        reduce.ifPresent(System.out::println);
    }



}
