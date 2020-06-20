package com.example.demo.sort;
/**
 * @description: 快速排序 快排的平均时间为：O(nlogn) 最坏 O(n^2)
 * @author: hetao
 * @create: 2019-06-22 19:24
 **/
public class QuickSort {


    /**
     * @Description:
     * @Param:
     * @param array
     * @param lo
     * @param hi
     * @Return: void
     * @date: 2019/6/22
     */
    public static void quickSort(int[] array,int lo,int hi){
      if(lo<hi){
          int base = array[lo];//基准点
          int i=lo,j=hi;
          while (i<=j){
              //从右边开始寻找小于基准点的数据
              while (array[j]>base&&j>lo){
                  j--;
              }
              //从左边开始寻找大于基准点的数据
              while (array[i]<base&&i<hi){
                  i++;
              }
              //交换数据
              if(i<=j){
                  int temp = array[i];
                  array[i] = array[j];
                  array[j] = temp;
                  i++;
                  j--;
              }
          }

          //如果当前光标的位置大于开始的节点，则说明左侧还没有遍历
          if(j>lo){
              quickSort(array,lo,j);
          }
          //如果当前光标的位置小于开始的节点，则说明右侧还没有遍历
          if(i<hi){
              quickSort(array,i,hi);
          }
      }


    }

    public  static  void main(String[] args) {
        int[] array = {14, 5, 44, 8, 21, 50};
        quickSort(array,0,5);
        for (int i=0;i<array.length;i++){
            System.out.print(array[i]+"\t");
        }
    }


}
