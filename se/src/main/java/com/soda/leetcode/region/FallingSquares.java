package com.soda.leetcode.region;

import java.util.ArrayList;
import java.util.List;

/**
 * @author soda
 * @date 2022/6/26
 */
public class FallingSquares {
    int range = (int) (1e8);
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> res = new ArrayList<>();
        SegNodeFall root = new SegNodeFall();
        for (int[] position : positions) {
            int x = position[0], w = position[1];
            int curr_h = query(root, x, x + w - 1, 1, range);
            update(root, x, x + w - 1, 1, range, curr_h + w);
            res.add(root.val);
        }
        return res;
    }

    int query(SegNodeFall node, int lc, int rc, int s, int e){
        if (lc <= s && e <= rc){
            return node.val;
        }
        pushDown(node);
        int ans = 0;
        int mid = s + e >> 1;
        if (lc <= mid){
            ans = query(node.l, lc ,rc , s , mid);
        }
        if (rc > mid) {
            ans = Math.max(ans, query(node.r, lc, rc, mid + 1, e));
        }
        return ans;

    }
    void update(SegNodeFall node, int lc, int rc, int s, int e, int v){

        if (lc <= s && e <= rc){
            node.val = v;
            node.add = v;
            return;
        }
        pushDown(node);
        int mid = (s + e) >> 1;
        if (lc <= mid) {
            update(node.l, lc, rc, s, mid, v);
        }
        if (rc > mid) {
            update(node.r, lc, rc, mid + 1, e, v);
        }
        pushUp(node);
    }

    void pushUp(SegNodeFall node){
        node.val = Math.max(node.l.val, node.r.val);
    }
    void pushDown(SegNodeFall node){
        if (node.l == null && node.r == null) {
            node.l = new SegNodeFall();
            node.r = new SegNodeFall();
        }
        if (node.add == 0) return;
        node.l.add = node.r.add = node.add;
        node.l.val = node.r.val = node.val;
        node.add = 0;
    }
    public static void main(String[] args) {
        FallingSquares fallingSquares = new FallingSquares();
        List<Integer> list = fallingSquares.fallingSquares(new int[][]{{6,1}, {9, 2}, {2, 4}});
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
class SegNodeFall{
    SegNodeFall l, r;
    int add, val;
}
