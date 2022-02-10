package com.example.demo.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 树
 * @author: chenliang
 * @create: 2022-02-10 16:09
 **/
@Data
public class TreeNode {

    private String value;
    private List<TreeNode> children;

    public TreeNode() {
        children = new ArrayList<>();
    }

    public TreeNode(String value) {
        this.value = value;
        children = new ArrayList<>();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return value;
    }
}