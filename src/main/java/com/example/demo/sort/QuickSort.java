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


    /*
     * 快速排序
     *
     * 参数说明：
     *     a -- 待排序的数组
     *     l -- 数组的左边界(例如，从起始位置开始排序，则l=0)
     *     r -- 数组的右边界(例如，排序截至到数组末尾，则r=a.length-1)
     */
    public static void quickSort2(int[] a, int l, int r) {

        if (l < r) {
            int i, j, x;

            i = l;
            j = r;
            x = a[i];
            while (i < j) {
                while (i < j && a[j] > x)
                    j--; // 从右向左找第一个小于x的数
                if (i < j)
                    a[i++] = a[j];
                while (i < j && a[i] < x)
                    i++; // 从左向右找第一个大于x的数
                if (i < j)
                    a[j--] = a[i];
            }
            a[i] = x;
            quickSort2(a, l, i - 1); /* 递归调用 */
            quickSort2(a, i + 1, r); /* 递归调用 */
        }
    }
}
