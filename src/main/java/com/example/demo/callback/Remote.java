package com.example.demo.callback;

/**
 * @description:
 * @author: chenliang
 * @create: 2020-10-31 15:22
 **/
public class Remote {

    public void executeMessage(String msg,CallBack callBack){

        for(int i=0;i < 1000000000;i++){
            ;
        }
        System.out.println("excute msg:"+msg);
        msg = msg+"msg change...";
        callBack.execute(msg);
    }
}