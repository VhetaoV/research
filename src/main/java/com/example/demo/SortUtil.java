package com.example.demo;

/**
 * @description: 面试用
 * @author: hetao
 * @create: 2020-04-22 14:07
 **/
public class SortUtil {

    /**
     * 升序排列
     * @param array
     * @return array
     */
    public static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args){
        int[] array = {14,5,44,8,21,50};
        System.out.print("排序前：");
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+"\t");
        }
        array = SortUtil.bubbleSort(array);
        System.out.print("\n排序后：");
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+"\t");
        }
    }


}
