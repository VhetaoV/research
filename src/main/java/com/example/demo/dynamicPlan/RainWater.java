package com.example.demo.dynamicPlan;

import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * @description: 动态规划-接雨水
 * @author: chenliang
 * @create: 2022-08-24 17:56
 **/
public class RainWater {

    public static void main(String[] args) {
        Integer[] arr = {0,3,2,1,2,5};
      int sum = space2Time(arr);
      System.out.println(sum);
    }

    /**
     * 栈
     * @param height
     * @return
     */
    public int pushStack(int[] height) {
        int n = height.length;
        int result = 0;
        if(n == 0 || n == 1) {
            return result;
        }
        int cur = 0;
        Stack<Integer> stack = new Stack<Integer>();
        while(cur < n) {
            while(!stack.isEmpty() && height[cur] > height[stack.peek()]){
                int top = stack.pop();
                if(stack.isEmpty()){
                    break;
                }
                //distance represents the width
                int distance = cur - stack.peek() - 1;
                //tempHeight represents the height
                int tempHeight = Math.min(height[cur], height[stack.peek()]) - height[top];
                result += tempHeight  * distance;
            }
            stack.push(cur);
            cur++;
        }
        return result;
    }

    /**
     * 空间换时间
     * @param height
     * @return
     */
    public static int space2Time(Integer[] height) {
        int n = height.length;
        int result = 0;
        if(n == 0 || n == 1) {
            return result;
        }
        Integer[] leftMax = new Integer[n];
        Integer[] rightMax = new Integer[n];
        leftMax[0] = height[0];
        rightMax[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
            rightMax[n - 1 - i] = Math.max(height[n - 1 - i], rightMax[n - i]);
        }
        Arrays.asList(height).forEach(e->{
            System.out.print(e);
        });
        System.out.println();
        Arrays.asList(leftMax).forEach(e->{
            System.out.print(e);
        });
        System.out.println();
        Arrays.asList(rightMax).forEach(e->{
            System.out.print(e);
        });
        for (int i = 1; i < n - 1; i++) {
            int min = Math.min(leftMax[i - 1], rightMax[i + 1]);
            if(min > height[i]) {
                result += min - height[i];
            }
        }
        return result;
    }
    /**
     * 简单直接暴力方式
     * @param arr
     * @return
     */
    public static int violence(int[] arr){
        int sum = 0;
        for(int i=0; i< arr.length - 1; i++){

            int leftMax = 0;

            for(int l=0; l<i; l++){
                leftMax = Math.max(arr[l],leftMax);
            }

            int rightMax = 0;
            for(int r=i+1; r<arr.length; r++){
                rightMax = Math.max(arr[r],rightMax);
            }

            int min = Math.min(leftMax,rightMax);
            if(min > arr[i]){
                sum += min - arr[i];
            }
        }
        return sum;
    }

    /**
     *  分层处理  从第一层开始 寻找左右两侧最高 如果满足条件 加 1
     *
     * @param arr
     * @return
     */
    public static int level(int[] arr){
        int sum = 0;
        int maxHeight = arr[0];

        for(int i=1; i< arr.length; i++){
            maxHeight = Math.max(maxHeight,arr[i]);
        }

        for(int j=1; j< maxHeight; j++){
            int left = arr[j-1];
            int curr = arr[j];
            int right = arr[j+1];

            if(left > curr && curr < right){

            }


            
        }
        return sum;
    }

}