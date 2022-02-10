package com.example.demo.tree;

import java.util.*;

/**
 * @description:
 * @author: chenliang
 * @create: 2022-02-10 16:16
 **/
public class TraverseTree {

    public static void main(String[] args) {
        TreeNode root = BuildTree.buildTree();

        List<TreeNode> bfsResult = test2(root);
        System.out.println(bfsResult);
    }


    /**
     * 深度优先遍历（递归方式） --- 树（Tree）
     */
    public void recurTree(TreeNode root) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        path.add(root.getValue());
        findPath(result, root, path);
        System.out.println(result);
    }

    private void findPath(List<List<String>> result, TreeNode node, List<String> path) {
        if (node.getChildren() == null || node.getChildren().size() <= 0) {
            result.add(path);
            return;
        }

        for (int i = 0; i < node.getChildren().size(); i++) {
            TreeNode child = node.getChildren().get(i);
            List<String> cPath = new ArrayList<>();
            cPath.addAll(path);
            cPath.add(child.getValue());
            findPath(result, child, cPath);
        }
    }


    /**
     * 深度优先遍历（非递归方式） ----- 查找树的全部叶子路径
     *
     * @param root
     *            根节点
     * @return 叶子路径的集合
     */
    public List<List<TreeNode>> dfsTree(TreeNode root) {
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<List<TreeNode>> pathStack = new Stack<>();
        List<List<TreeNode>> result = new ArrayList<>();
        nodeStack.push(root);
        ArrayList<TreeNode> arrayList = new ArrayList<>();
        arrayList.add(root);
        pathStack.push(arrayList);

        while (!nodeStack.isEmpty()) {
            TreeNode curNode = nodeStack.pop();
            List<TreeNode> curPath = pathStack.pop();

            if (curNode.getChildren() == null || curNode.getChildren() .size() <= 0) {
                result.add(curPath);
            } else {
                int childSize = curNode.getChildren() .size();
                for (int i = childSize - 1; i >= 0; i--) {
                    TreeNode node = curNode.getChildren() .get(i);
                    nodeStack.push(node);
                    List<TreeNode> list = new ArrayList<>(curPath);
                    list.add(node);
                    pathStack.push(list);
                }
            }
        }
        return result;
    }


    /**
     * 广度优先遍历 ---- 查找树的全部叶子路径
     *
     * @param root
     *            根节点
     * @return 叶子路径的集合
     */
    public static List<List<TreeNode>> bfsTree(TreeNode root) {
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        Queue<List<TreeNode>> qstr = new LinkedList<>();
        ArrayList<TreeNode> arrayList = new ArrayList<>();
        qstr.add(arrayList);

        List<List<TreeNode>> result = new ArrayList<>();

        while (!nodeQueue.isEmpty()) {
            TreeNode curNode = nodeQueue.remove();
            List<TreeNode> curList = qstr.remove();

            if (curNode.getChildren() == null || curNode.getChildren().size() <= 0) {
                curList.add(curNode);
                result.add(curList);
            } else {
                for (int i = 0; i < curNode.getChildren().size(); i++) {
                    TreeNode treeNode = curNode.getChildren().get(i);
                    nodeQueue.add(treeNode);
                    List<TreeNode> list = new ArrayList<>(curList);
                    list.add(curNode);
                    qstr.add(list);
                }
            }
        }
        return result;
    }


    public static List<List<TreeNode>> test(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<List<TreeNode>> result = new ArrayList<>();

        List<TreeNode> curList = new ArrayList<>();
        Queue<List<TreeNode>> queue2 = new LinkedList<List<TreeNode>>();
        queue2.add(curList);

        while (!queue.isEmpty()){
            TreeNode treeNode = queue.remove();
            List<TreeNode> curList2 = queue2.remove();
            if(treeNode.getChildren() == null || treeNode.getChildren().size() == 0){
                curList2.add(treeNode);
                result.add(curList2);
            }else{
                for(TreeNode childTreeNode : treeNode.getChildren()){
                    queue.add(childTreeNode);
                    List<TreeNode> list = new ArrayList<>();
                    list.addAll(curList2);
                    list.add(treeNode);
                    queue2.add(list);
                }
            }
        }
       return result;
    }


    public static List<TreeNode> test2(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<TreeNode> result = new ArrayList<>();
        result.add(root);
        List<TreeNode> curList = new ArrayList<>();
        Queue<List<TreeNode>> queue2 = new LinkedList<List<TreeNode>>();
        queue2.add(curList);

        while (!queue.isEmpty()){
            TreeNode treeNode = queue.remove();
            if(treeNode.getChildren() == null || treeNode.getChildren().size() == 0){
                result.add(treeNode);
            }else{
                for(TreeNode childTreeNode : treeNode.getChildren()){
                    queue.add(childTreeNode);
                    result.add(childTreeNode);
                }
            }
        }
        return result;
    }

}