package com.soda.leetcode.region;

import java.util.*;

/**
 * @author soda
 * @date 2022/6/23
 */
public class RangArea {
    int OPEN = 1, CLOSE = -1;
    public int rectangleArea(int[][] rectangles) {

        Set<Integer> yVal = new HashSet<>();
        int[][] line = new int[rectangles.length * 2][];
        int t = 0;
        for (int[] rect : rectangles) {
            line[t++] = new int[]{rect[0], OPEN, rect[1], rect[3]};
            line[t++] = new int[]{rect[2], CLOSE, rect[1], rect[3]};
            yVal.add(rect[1]);
            yVal.add(rect[3]);
        }
        Arrays.sort(line, Comparator.comparingInt(a -> a[0]));
        Integer[] Y = yVal.toArray(new Integer[0]);
        Arrays.sort(Y, Comparator.comparingInt(a->a));
        //
        return 0;
    }

    public static void main(String[] args) {
        RangArea rangArea = new RangArea();
        int i = rangArea.rectangleArea(new int[][]{{0, 0, 2, 2}, {1, 0, 2, 3}, {1, 0, 3, 1}});
        System.out.println(i);

    }

}
class SegTree {

}
class SegNode {
    int start, end; // 起始点
    SegNode lc, rc; // 左右子树
    int count; // 被查找寻区间覆盖次数
    long len; // 区间长度
    Integer[] X; // X实际坐标

    public SegNode(int start, int end, Integer[] X) {
        this.start = start;
        this.end = end;
        this.X = X;
        lc = null;
        rc = null;
        count = 0;
        len = 0;
    }

    public int getMid() {
        return (start + end) >> 1;
    }
}