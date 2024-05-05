package com.soda.leetcode.region;

class MajorityChecker {
    int[] arr;
    SegNodeM root;
    public MajorityChecker(int[] arr) {
        root = build(0, arr.length - 1, arr);
        this.arr = arr;
    }

    public int query(int left, int right, int threshold) {
        SegNodeM nodeM = queryHelper(root, left, right);
        if (nodeM.l == null && nodeM.r == null) return  -1;
        int candidate = nodeM.candidate;
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (arr[i] == candidate) {
                count ++;
            }
        }

        return count >= threshold ? candidate : -1;
    }
    SegNodeM build(int s, int e, int[] arr){

        SegNodeM node = new SegNodeM(s, e);
        if (s == e){
            node.set(arr[s], 1);
            return node;
        }
        int mid = s + e >> 1;
        node.l = build(s, mid, arr);
        node. r = build(mid + 1, e, arr);
        merge(node);
        return node;
    }

    private void merge(SegNodeM node) {
        SegNodeM l = node.l;
        SegNodeM r = node.r;
        if (l == null && r == null) {
            return;
        }
        if (l != null) {
            node.candidate = l.candidate;
            node.count = l.count;
        }
        if (r != null){
            if (node.candidate == r.candidate) {
                node.count += r.count;
            } else if (node.count >= r.count) {
                node.count -= r.count;
            } else {
                node.candidate = r.candidate;
                node.count = r.count - node.count;
            }
        }
    }

    SegNodeM queryHelper(SegNodeM root, int left, int right){

        if (left <= root.s && root.e <= right) {
            return root;
        }
        int mid = root.getMid();
        SegNodeM node = new SegNodeM(0, 0);
        if (left <= mid) {
            node.l= queryHelper(root.l, left, right);
        }
        if (right > mid) {
            node.r = queryHelper(root.r, left, right);
        }
        merge(node);
        return node;
    }

    public static void main(String[] args) {
        MajorityChecker majorityChecker = new MajorityChecker(new int[] {1, 1, 2, 2, 1, 1});
        int query = majorityChecker.query(2, 3, 2);
        System.out.println(query);
    }
}

class SegNodeM {
    SegNodeM l, r;
    int s, e;
    int candidate,count;
    public SegNodeM(int s, int e){
        this.s = s;
        this.e = e;
        this.candidate = -1;
        this.l = null;
        this.r = null;
        this.count = 0;
    }
    public void set(int candidate, int count){
        this.candidate = candidate;
        this.count = count;
    }

    public int getMid() {
        return s + e >> 1;
    }
}
