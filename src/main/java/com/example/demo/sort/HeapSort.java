package com.example.demo.sort;

/**
 * @description: https://www.cnblogs.com/chengxiao/p/6129630.html
 * 它的最坏，最好，平均时间复杂度均为O(nlogn)
 * @author: chenliang
 * @create: 2022-10-25 08:34
 **/
public class HeapSort {

    public static void main(String[] args) {
        int[] array = {14, 5, 44, 8, 21, 50};
        sort(array);
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+"\t");
        }
    }

    public static void sort(int[] arr){
        //1、构造大顶堆
        for(int i=arr.length/2 - 1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }
        //2、交换堆顶与末尾元素，继续调整堆结构
        for(int i=arr.length-1;i>=0;i--){
            //交换
            swap(arr,0,i);
            //调整堆结构
            adjustHeap(arr,0,i);
        }
    }


    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[i] < arr[k]) {
                arr[i] = arr[k];
                i = k;
                arr[i] = temp;
            }
        }
    }



    public static void swap(int[] arr,int a,int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}