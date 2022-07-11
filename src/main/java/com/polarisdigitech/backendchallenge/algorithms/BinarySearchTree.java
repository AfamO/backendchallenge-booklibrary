package com.polarisdigitech.backendchallenge.algorithms;

import com.polarisdigitech.backendchallenge.exceptions.CustomException;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> {
    private TreeNode<E> root;

    public BinarySearchTree(TreeNode<E> root){
        this.root = root;
    }

    private void visitTree(TreeNode<E> treeNode){
        System.out.println(treeNode.getValue()+" ");
    }



    public boolean binarySearch(String value, TreeNode<String> root){
        if (root == null)
            return  false;
        else
            if (root.getValue() == null)
                throw new NullPointerException("The value parameter in the tree node can't be null.");
            if (root.getValue().compareTo(value) == 0)
                return true;
            else if (root.getValue().compareToIgnoreCase(value) < 0)
                return binarySearch(value,root.getRight());
            else
                return binarySearch(value, root.getLeft());
    }


    public boolean insertIntoBinarySearchTree(TreeNode<String> root, String value){
        if (root == null)
            throw new IllegalArgumentException("The root node can't be null");
        int compareTo = root.getValue().compareTo(value);
        if (root.getLeft() == null || root.getRight() == null){
            TreeNode<String> nodeToAdd = new TreeNode<>(value,root);
            if (compareTo == 0)
                return false;
                //throw new IllegalArgumentException("This node "+value+" already exists");
            else if (compareTo < 0)
            {
                root.setRight(nodeToAdd);
                return true;
            }
            else if (compareTo > 0)
            {
                root.setLeft(nodeToAdd);
                return true;
            }
        }
        else
        {
            if (compareTo == 0)
                return false;
                //throw new IllegalArgumentException("This node already existss");
            else if (compareTo < 0)
                this.insertIntoBinarySearchTree(root.getRight(),value);
            else
                this.insertIntoBinarySearchTree(root.getLeft(),value);

        }
        return false;
    }

    public void levelOrderTraversal(){
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode<E> currentTree = queue.remove();
            if (currentTree!=null){
                this.visitTree(currentTree);
                queue.add(currentTree.getLeft());
                queue.add(currentTree.getRight());
            }
        }
    }
}
