package com.polarisdigitech.backendchallenge.algorithms;

import java.util.Arrays;

public class TreeNode<E> {
    private E value;
    private TreeNode<E> parent;
    private TreeNode<E> left;

    public TreeNode(E value, TreeNode<E> parent){
        this.value = value;
        this.parent = parent;
    }


    public  TreeNode<E> addLeftChild(E value) {
        this.left = new TreeNode<>(value, this);
        return this.left;
    }

    public TreeNode<E> addRightChild(E value) {
        this.right = new TreeNode<>(value,this);
        return this.right;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public TreeNode<E> getParent() {
        return parent;
    }

    public void setParent(TreeNode<E> parent) {
        this.parent = parent;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }

    private TreeNode<E> right;


}
