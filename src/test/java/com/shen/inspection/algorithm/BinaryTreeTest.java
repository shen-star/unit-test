package com.shen.inspection.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.function.Consumer;

public class BinaryTreeTest {

    @Data
    @AllArgsConstructor
    public static class TreeNode {
        /**
         * 节点值
         */
        private final int value;
        /**
         * 左子结点
         */
        private final TreeNode left;
        /**
         * 右子节点
         */
        private final TreeNode right;

        //重写toString方法，直接返回节点值
        public String toString() {
            return String.valueOf(value);
        }
    }

    public enum Type {
        PRE, IN, POST
    }

    /**
     * 生成一个二叉树，通过高阶函数实现对二叉树进行前中后序遍历
     * 1
     * /  \
     * 2    3
     * /    / \
     * 4    5   6
     * <p>
     * 前序遍历 124356
     * 中序变量 421536
     * 后序遍历 425631
     */

    @Test
    void traversalTest() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4, null, null),
                        null),
                new TreeNode(3,
                        new TreeNode(5, null, null),
                        new TreeNode(6, null, null)));

        //递归二叉树遍历
        System.out.println("============递归遍历=============");
        System.out.println("===============前序==============");
        traversalOne(root, Type.PRE, System.out::println);
        System.out.println("===============中序==============");
        traversalOne(root, Type.IN, System.out::println);
        System.out.println("===============后序==============");
        traversalOne(root, Type.POST, System.out::println);
        //非递归二叉树遍历
        System.out.println("============非递归遍历===========");
        System.out.println("===============前序==============");
        traversalTwo(root, Type.PRE, System.out::println);
        System.out.println("===============中序==============");
        traversalTwo(root, Type.IN, System.out::println);
        System.out.println("===============后序==============");
        traversalTwo(root, Type.POST, System.out::println);
        System.out.println("=================================");

    }

    /**
     * 递归遍历
     *
     * @param root     根节点
     * @param type     遍历类型(先序、中序、后序)
     * @param consumer 函数对象
     */
    public static void traversalOne(TreeNode root, Type type, Consumer<TreeNode> consumer) {
        if (root == null) {
            return;
        }
        //前序处理
        if (type == Type.PRE) {
            consumer.accept(root);
        }
        traversalOne(root.left, type, consumer);
        //中序处理
        if (type == Type.IN) {
            consumer.accept(root);
        }
        traversalOne(root.right, type, consumer);
        //后序处理
        if (type == Type.POST) {
            consumer.accept(root);
        }
    }

    /**
     * 非递归遍历
     *
     * @param root     根节点
     * @param type     遍历类型(先序、中序、后序)
     * @param consumer 函数对象
     */
    public static void traversalTwo(TreeNode root, Type type, Consumer<TreeNode> consumer) {
        //记录整个遍历过程
        LinkedList<TreeNode> stack = new LinkedList<>();
        //当前节点
        TreeNode curr = root;
        //最近一次遍历完的节点
        TreeNode last = null;
        while (curr != null || !stack.isEmpty()) {
            //左子树未遍历完
            if (curr != null) {
                //添加遍历路线
                stack.push(curr);
                //前序处理
                if (type == Type.PRE) {
                    consumer.accept(curr);
                }
                //向左子树移动
                curr = curr.left;
            }
            //左子树遍历完
            else {
                //刚才遍历过的节点
                TreeNode peek = stack.peek();
                //没有右子节点
                if (peek.right == null) {
                    if (type == Type.IN || type == Type.POST) {
                        consumer.accept(peek);
                    }
                    last = stack.pop();
                }
                //存在右子节点，已遍历
                else if (peek.right == last) {
                    if (type == Type.POST) {
                        consumer.accept(peek);
                    }
                    last = stack.pop();
                }
                //存在右子节点，未遍历
                else {
                    if (type == Type.IN) {
                        consumer.accept(peek);
                    }
                    curr = peek.right;
                }
            }

        }
    }
}