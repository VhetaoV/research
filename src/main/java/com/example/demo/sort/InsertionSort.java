package com.example.demo.sort;

/**
 * @description: 插入排序
 * @author: chenliang
 * @create: 2022-10-12 09:16
 **/
public class InsertionSort {

    public static void main(String[] args) {
        int[] array = {14, 5, 44, 8, 21, 50};
        insertSort(array);
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+"\t");
        }
    }

    public static void insertSort(int[] arr){
        for(int i=0;i<arr.length;i++){
            //寻找元素arr[i]的插入位置
            for(int j=i;j>0;j--){
               if(arr[j] < arr[j-1]){
                  int tmp = arr[j];
                  arr[j] = arr[j-1];
                  arr[j-1] = tmp;
               }
            }
        }
    }
}