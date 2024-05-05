package com.soda.leetcode.region;

import java.net.Inet4Address;
import java.util.*;

/**
 * @author soda
 * @date 2022/6/26
 */
public class CountRangeSum {

    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] preSums = new long[nums.length + 1];
        long sum = 0;
        int i = 0;
        for (int num : nums) {
            sum += num;
            preSums[i + 1] = sum;
            i++;
        }
        // 离散化
        Set<Long> set = new TreeSet<>();
        for (long preSum : preSums) {
            set.add(preSum);
            set.add(preSum - upper);
            set.add(preSum - lower);
        }
        int idx = 0;
        Map<Long, Integer> indMap = new HashMap<>();
        for (Long integer : set) {
            indMap.put(integer, idx);
            idx ++;
        }
        SegNodeCount root = build(0, set.size() - 1);

        int ans = 0;
        for (long preSum : preSums) {
            int l = indMap.get(preSum - upper), r = indMap.get(preSum - lower);
            int x = indMap.get(preSum);
            ans += count(root, l, r);
            update(root, x, x, 1);
        }
        return ans;
    }

    private int count(SegNodeCount node, long lc, long rc) {
        int s = node.s, e = node.e;
        if (lc <= s && e <= rc){
            return node.val;
        }
        int ans = 0;
        int mid = s + e >> 1;
        if (lc <= mid){
            ans += count(node.l, lc ,rc);
        }
        if (rc > mid) {
            ans += count(node.r, lc, rc);
        }
        return ans;
    }

    SegNodeCount build(int s, int e){
        SegNodeCount node = new SegNodeCount(s, e);
        if (s == e){
            return  node;
        }
        int mid = s + e >> 1;
        node.l = build(s, mid);
        node.r = build(mid + 1, e);
        return node;
    }
    void update(SegNodeCount node, int lc, int rc,  int v){
        int s = node.s, e = node.e;
        if (lc <= s && e <= rc){
            node.val += v;
            return;
        }
        int mid = (s + e) >> 1;
        if (lc <= mid) {
            update(node.l, lc, rc,v);
        }
        if (rc > mid) {
            update(node.r, lc, rc, v);
        }
        node.val = node.l.val + node.r.val;
    }
    public static void main(String[] args) {
        int upper = 2, lower = -2;
        int[] nums = new int[] {-2,5,-1, 0};
        CountRangeSum countRangeSum = new CountRangeSum();
        int i = countRangeSum.countRangeSum(nums, lower, upper);
        System.out.println(i);
    }
}

class SegNodeCount{
    SegNodeCount l, r;
    int s, e;
    int add, val;
    public SegNodeCount( int s, int e){
        this.s = s;
        this.e = e;
        this.val = 0;
        this.l  = null;
        this.r = null;
    }

}
