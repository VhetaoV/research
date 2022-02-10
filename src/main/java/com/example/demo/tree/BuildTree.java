package com.example.demo.tree;

/**
 * @description:
 * @author: chenliang
 * @create: 2022-02-10 16:11
 **/
public class BuildTree {

    public static TreeNode buildTree(){
        // 建立一棵树
        TreeNode root = new TreeNode("A");
        // 第二层
        root.getChildren().add(new TreeNode("B"));
        root.getChildren().add(new TreeNode("C"));
        // 第三层
        root.getChildren().get(0).getChildren().add(new TreeNode("D"));
        root.getChildren().get(0).getChildren().add(new TreeNode("E"));
        root.getChildren().get(1).getChildren().add(new TreeNode("F"));
        root.getChildren().get(1).getChildren().add(new TreeNode("H"));
        root.getChildren().get(1).getChildren().add(new TreeNode("G"));
        // 第四层
        root.getChildren().get(0).getChildren().get(1).getChildren().add(new TreeNode("I"));
        return root;
    }
}