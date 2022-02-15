package com.example.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: https://leetcode-cn.com/problems/two-sum/
 * @author: chenliang
 * @create: 2022-02-15 14:11
 **/
public class Question1 {

    public static void main(String[] args) {
        int[] array = {11,2,34,9,7};

        System.out.println(twoSum(array,9)[0]+"   "+twoSum(array,9)[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                res[1] = i;
                res[0] = map.get(temp);
            }
            map.put(nums[i], i);
        }
        return res;
    }
}