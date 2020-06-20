package com.example.demo.string;
//https://blog.csdn.net/qq_39843374/article/details/81583690
public class BuildOrRun {

    public static void main(String[] args){

        test7();
    }

    /** * 编译期无法确定 */
    public static void test7(){
        String s0 = "ab";
        String s1 = "b";
        String s2 = "a" + s1;
        System.out.println("===========test7============");
        System.out.println((s0 == s2)); //result = false
    }

    /** * 比较字符串常量的“+”和字符串引用的“+”的区别 */
    public static void test8(){
        String test="javalanguagespecification";
        String str="java";
        String str1="language";
        String str2="specification";
        System.out.println("===========test8============");
        System.out.println(test == "java" + "language" + "specification");//true
        System.out.println(test == str + str1 + str2);//false
    }

}
