package com.example.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: chenliang
 * @create: 2022-02-15 15:08
 **/
public class Question2 {

    public static void main(String[] args) {
        String str = "pabcdewwkew";
        System.out.println(lengthOfLongestSubstring(str));
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

}