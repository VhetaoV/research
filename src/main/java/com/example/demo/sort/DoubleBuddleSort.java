package com.example.demo.sort;

/**
 * @description: 双向冒泡排序  较大气泡从左到右移动，较小气泡从右到左移动”来实现双向冒泡排序的效果
 * 时间复杂度 O(n^2)
 * @author: chenliang
 * @create: 2022-10-15 22:47
 **/
public class DoubleBuddleSort {

    public static void main(String[] args) {
        int[] array = {14,5,44,8,21,50};
        doubleBuddleSort(array);
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+"\t");
        }
    }

    public static void doubleBuddleSort(int[] arr){
        int left = 0;
        int right = arr.length - 1;

        while (left < right){
            for(int i=left+1;i<=right;i++){
               if(arr[left] > arr[i]){
                   int tmp = arr[left];
                   arr[left] = arr[i];
                   arr[i] = tmp;
               }
            }
            left++;

            for(int i=right-1;i>=left;i--){
               if(arr[i] > arr[right]){
                   int tmp = arr[i];
                   arr[right] = arr[i];
                   arr[i] = tmp;
               }
            }
            right--;
        }
    }

}