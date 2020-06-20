package com.example.demo.file;

import java.io.File;

public class GetFiles {

    public static void main(String[] args){

        File file = new File("D:\\Program File");
        getFiles(file);
    }

    public static void getFiles(File file){
        if(file!=null){
            if(file.isDirectory()){
                for(File file2 : file.listFiles()){
                    getFiles(file2);
                }
            }else{
                System.out.println(file.getAbsolutePath());
            }
        }
    }
}
