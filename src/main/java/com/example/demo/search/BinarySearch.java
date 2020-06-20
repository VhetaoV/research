package com.example.demo.search;

public class BinarySearch {

    public  static void main(String[] args){
        int[] array = {1,2,3,4,5,6,7};
        int res = binarySearch(array,4);
        System.out.println(res);
    }

    public static int binarySearch(int[] array ,int k){
        int low=0;
        int high=array.length-1;
        while(low<high){
            int mid = (low+high)/2;
           if(k == array[mid]){
               return mid;
           }else if(k > array[mid]){
               low = low+1;
           }else if (k < array[mid]){
               high = high - 1;
           }
        }
        return -1;
    }

}
