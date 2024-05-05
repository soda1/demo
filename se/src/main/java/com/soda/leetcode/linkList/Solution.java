package com.soda.leetcode.linkList;

import java.util.*;

class Solution {

    public boolean carPooling(int[][] trips, int capacity) {
        List<int[]> list = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] trip : trips) {
            list.add(new int[] { trip[1], trip[0]});
            list.add(new int[] { trip[2], -trip[0]});
        }
        Collections.sort(list, (a, b) -> {
            if (a[0] == b[0]){
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int sum = 0;
        for (int[] ints : list) {
            if (sum > capacity) {
                return false;
            }
            sum += ints[1];
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        // List<List<Integer>> skyline = solution.getSkyline(new int[][]{{0,2,3}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}});
    }


}