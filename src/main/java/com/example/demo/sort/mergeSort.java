package com.example.demo.sort;

/**
 * @description: 归并排序--双路归并  O(nlogn)
 * @author: hetao
 * @create: 2019-06-22 19:24
 **/
public class mergeSort {

    public static  void main(String[] args){
        int[] data = new int[]{ 2, 4, 7, 5, 8, 1, 3, 6 };
        System.out.print("排序前：\t");
        print(data);
        System.out.println();
        mergeSort(data,0,data.length-1);
        System.out.println("排序后：\t");
        print(data);
    }

    /**
     * @Description:
     * @Param:
     * @param data
     * @param left
     * @param right
     * @Return: void
     * @date: 2019/6/22
     */
    public static void mergeSort(int[] data,int left, int right){
          if(left >= right){
              return;
          }
          //两路归并，找出中间索引
          int middle = (left + right)>>1;
          //先对左边数组进行递归排序
          mergeSort(data,left,middle);
          //对右边数组进行递归排序
          mergeSort(data,middle+1,right);
          //合并排序后的数组
          merge(data,left,middle,middle+1,right);
          System.out.print("排序中:\t");
          print(data);
    }

  
   /**
    * @Description:  将两个数组进行归并，归并前面2个数组已有序，归并后依然有序
    * @Param:
    * @param data
    * @param leftStart
    * @param leftEnd
    * @param rightStart
    * @param rightEnd
    * @Return: void
    * @date: 2019/6/22
    */
    public static void merge(int[] data, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        int i = leftStart;
        int j = rightStart;
        int k = 0;
        //创建一个临时数组，用来存放临时排序的数组
        int[] temp = new int[rightEnd - leftStart + 1];

        while (i <= leftEnd && j <= rightEnd) {
            //从两个数组中取出较小的数据放入到临时数组
            if (data[i] > data[j]) {
                temp[k++] = data[j++];
            } else {
                temp[k++] = data[i++];
            }
        }
        //将剩余部分放入到临时数组中
        while (i <= leftEnd) {
            temp[k++] = data[i++];
        }
        while (j <= rightEnd) {
            temp[k++] = data[j++];
        }
        //将临时数组中的内容拷贝回原来的数组中，其实位置为leftStart
        //即left-right范围内的数据
        k = leftStart;
        for (int element : temp) {
            data[k++] = element;
        }
    }

    
    public  static void print(int[] data){
        for(int i=0;i<data.length;i++){
            System.out.print(data[i]+"\t");
        }
        System.out.println();
    }























}
