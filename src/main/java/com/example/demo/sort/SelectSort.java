package com.example.demo.sort;

/**
 * @description: 选择排序  首先在未排序的数列中找到最小(or最大)元素，
 * 然后将其存放到数列的起始位置；接着，再从剩余未排序的元素中继续寻找最小(or最大)元素，
 * 然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 * @author: chenliang
 * @create: 2022-10-11 08:49
 **/
public class SelectSort {

    public static void main(String[] args) {
        int[] array = {14, 5, 44, 8, 21, 50};
        selectSort(array,6);
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+"\t");
        }
    }


    public static void selectSort(int[] arr,int arrLength){
        int i; //有序区的末尾位置
        int j; //无序区的起始位置
        int min; //无序区中最小元素位置

        for(i=0;i<arrLength;i++){
            min = i;

            for(j=i+1;j<arrLength;j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }

            if(i != min){
                int tmp = arr[min];
                arr[min] = arr[i];
                arr[i] = tmp;
            }
        }
    }
}