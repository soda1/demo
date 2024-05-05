package com.soda.leetcode;

import java.util.HashMap;

/**
 * @author soda
 * @date 2022/5/25
 */
public class Trie {
    private Trie left;
    private Trie right;
    public Trie() {
    }
    public void insert(int num) {
        Trie curr = this;
        for(int i = 30; i >= 0; i--){
            int bit = (num >> i) & 1;
            if(bit == 0) {
                if(curr.left == null) {
                    curr.left = new Trie();
                }
                curr = curr.left;
            }else{
                if(curr.right == null) {
                    curr.right = new Trie();
                }
                curr = curr.right;
            }
        }
    }
    public int findMaximumXOR(int num){
        // 贪心查找
        int x = 0;
        Trie curr = this;
        for(int i = 30; i >= 0; i--){
            int bit = (num >> i) & 1;
            if(bit == 0) {
                if(curr.right != null) {
                    curr = curr.right;
                    x += 1 << i;
                }else {
                    curr = curr.left;
                }
            }else {
                if(curr.left != null) {
                    curr = curr.left;
                    x += 1 << i;
                }else {
                    curr = curr.right;
                }
            }
        }
        return x;
    }
}
