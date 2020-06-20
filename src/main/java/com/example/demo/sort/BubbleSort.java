package com.example.demo.sort;

/**
 * @description: 冒泡排序 O(n²) 
 * @author: hetao
 * @create: 2019-06-22 19:24
 **/
public class BubbleSort {

    public  static  void main(String[] args){
        int[] array = {14,5,44,8,21,50};
        System.out.print("排序前：");
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+"\t");
        }

        for (int i=0;i<array.length-1;i++){
            for(int j=0;j<array.length-i-1;j++){
                if(array[j]>array[j+1]){//升序排列
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
        System.out.print("\n排序后：");
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+"\t");
        }
    }




}
