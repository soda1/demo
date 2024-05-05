package com.soda.stream.parallel;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author soda
 * @date 2021/7/24
 */
public class WordCounter {

    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            if (!lastSpace) {
                return new WordCounter(counter, true);
            }
            return this;
        } else {
            if (lastSpace) {
                return new WordCounter(counter + 1, false);
            } else {
                return this;
            }
        }
    }

    public WordCounter combine( WordCounter a2) {
        return new WordCounter(this.counter + a2.counter, false);
    }

    public static void main(String[] args) {
        // 创建字符流
        String testStr =" Nel mezzo del cammin di nostra vita " +
                "mi ritrovai in una selva oscura" +
                " ché la dritta via era smarritafaf dfafa fadf afa";
        // stream只有 int double long
        Stream<Character> stream = IntStream.range(0, testStr.length()).mapToObj(testStr::charAt);
        WordCounter reduce = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        System.out.println(reduce.counter);
        // 并行,字符会随意分割
        Stream<Character> parallelStream = IntStream.range(0, testStr.length()).mapToObj(testStr::charAt);
        WordCounter parallelReduce = parallelStream.parallel().reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        System.out.println(parallelReduce.counter);
        // 使用自定义splitter


    }
}
