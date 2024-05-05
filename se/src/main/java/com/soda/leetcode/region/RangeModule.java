package com.soda.leetcode.region;

/**
 * 动态开点线段树
 *
 * @author soda
 * @date 2022/6/20
 */
class RangeModule {
    /**
     * ls 和 rs 分别指向左右区间子节点（当采用「估点数组」方式时，记录的是左右区间子节点在线段树数组中的下标；在「动态指针」方式时，记录的是左右区间子节点对象）；
     * sum 为记录当前区间有多少个整数被追踪；
     * add 为懒标记，当 add = -1 代表 removeRange 懒标记，当 add = 1 则代表 addRange 懒标记。
     */
    class Node {
        int ls, rs, sum, add;
    }

    int N = (int) 1e9 + 10, M = 500010, cnt = 1;
    Node[] tr = new Node[M];

    /**
     * @param u  当前下标
     * @param lc 当前左区间值
     * @param rc 当前有区间值
     * @param l  更新左区间值
     * @param r  更新有区间值
     * @param v  更新值
     */
    void update(int u, int lc, int rc, int l, int r, int v) {
        int len = rc - lc + 1;
        // 当前节点的包含的区间在更新区间内直接更新sum值，并将懒惰标志置1；
        if (l <= lc && rc <= r) {
            tr[u].sum = v == 1 ? len : 0;
            tr[u].add = v;
            return;
        }
        pushdown(u, len);
        int mid = lc + rc >> 1;
        if (l <= mid) update(tr[u].ls, lc, mid, l, r, v);
        if (r > mid) update(tr[u].rs, mid + 1, rc, l, r, v);
        pushup(u);
    }

    int query(int u, int lc, int rc, int l, int r) {
        if (l <= lc && rc <= r) return tr[u].sum;
        pushdown(u, rc - lc + 1);
        int mid = lc + rc >> 1, ans = 0;
        if (l <= mid) ans = query(tr[u].ls, lc, mid, l, r);
        if (r > mid) ans += query(tr[u].rs, mid + 1, rc, l, r);
        return ans;
    }

    void pushdown(int u, int len) {
        // 如果当前节点为空则先创建
        if (tr[u] == null) tr[u] = new Node();
        if (tr[u].ls == 0) {
            tr[u].ls = ++cnt;
            tr[tr[u].ls] = new Node();
        }
        if (tr[u].rs == 0) {
            tr[u].rs = ++cnt;
            tr[tr[u].rs] = new Node();
        }
        if (tr[u].add == 0) return;

        //如果当前区间被remove，就置0；
        if (tr[u].add == -1) {
            tr[tr[u].ls].sum = tr[tr[u].rs].sum = 0;
        } else {
            // tr[u].add == 1，更新子节点的值
            tr[tr[u].ls].sum = len - len / 2;
            tr[tr[u].rs].sum = len / 2;
        }
        //将子节点的add更换为tr[u]的add，且将tr[u]懒惰标志位置为0；
        tr[tr[u].ls].add = tr[tr[u].rs].add = tr[u].add;
        tr[u].add = 0;
    }

    void pushup(int u) {
        tr[u].sum = tr[tr[u].ls].sum + tr[tr[u].rs].sum;
    }

    public void addRange(int left, int right) {
        update(1, 1, N - 1, left, right - 1, 1);
    }

    public boolean queryRange(int left, int right) {
        return query(1, 1, N - 1, left, right - 1) == right - left;
    }

    public void removeRange(int left, int right) {
        update(1, 1, N - 1, left, right - 1, -1);
    }

    public static void main(String[] args) {
        int left = 4, right = 90;
        RangeModule obj = new RangeModule();
        obj.addRange(left, right);
        boolean param_2 = obj.queryRange(left, right);
        obj.removeRange(left, right);
    }
}
