package exm.algo.tree;


import sun.reflect.generics.tree.Tree;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树遍历
 */
public class BinaryTreeTraversing {

    public static void main(String[] args) {
        TreeNode test = new TreeNode("A");
        TreeNode testl = new TreeNode("B");
        TreeNode testr = new TreeNode("C");
        TreeNode testll = new TreeNode("D");
        TreeNode testrr = new TreeNode("E");
        TreeNode testlr = new TreeNode("F");
        TreeNode testrl = new TreeNode("G");
        test.lChild = testl;
        testl.lChild = testll;
        testl.rChild = testlr;
        test.rChild = testr;
        testr.rChild = testrr;
        testr.lChild = testrl;
        preorderTraversing1(test);
        System.out.println();
        preorderTraversing2(test);
        System.out.println();
        inorderTraversing1(test);
        System.out.println();
        inorderTraversing2(test);
        System.out.println();
        postorderTraversing1(test);
        System.out.println();
        postorderTraversing2(test);
        System.out.println();
        layerTraversing(test);
    }

    /**
     * 递归前序遍历
     * @param root 根节点
     */
    public static void preorderTraversing1 (TreeNode root) {
        if(root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preorderTraversing1(root.lChild);
        preorderTraversing1(root.rChild);
    }

    /**
     * 非递归前序遍历
     * 借助栈来实现 根 -> 左子节点 -> 右子节点 的遍历
     * @param root 根节点
     */
    public static void preorderTraversing2 (TreeNode root) {
        if(root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode spec = null;
        while (!stack.isEmpty()) {
            spec = stack.pop();
            System.out.print(spec.val + " ");
            if(spec.rChild != null) {
                stack.push(spec.rChild);
            }
            if(spec.lChild != null) {
                stack.push(spec.lChild);
            }
        }
    }

    /**
     * 递归中序遍历
     * @param root 根节点
     */
    public static void inorderTraversing1 (TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversing1(root.lChild);
        System.out.print(root.val + " ");
        inorderTraversing1(root.rChild);
    }

    /**
     * 非递归中序遍历
     * 借助一个栈实现
     * 1.循环压入左子树，直到左子树为空
     * 2.pop当前节点并打印，再以同样的模式遍历右子树
     * 3.当栈与当前节点都为空时，遍历完成
     * @param root
     */
    public static void inorderTraversing2 (TreeNode root) {
        if(root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode spec = root;
        while (!stack.isEmpty() || spec != null) {
            while (spec != null) {
                stack.push(spec);
                spec = spec.lChild;
            }
            spec = stack.pop();
            System.out.print(spec.val + " ");
            spec = spec.rChild;
        }
    }

    /**
     * 递归后序遍历
     * @param root
     */
    public static void postorderTraversing1 (TreeNode root) {
        if (root == null) {
            return;
        }
        postorderTraversing1(root.lChild);
        postorderTraversing1(root.rChild);
        System.out.print(root.val + " ");
    }

    /**
     * 非递归后序遍历
     * 借助两个栈实现，首先将当前节点放入栈一，pop后将左右子节点继续放入栈一，并将当前节点放如栈二
     * 循环以上过程直到栈一为空，此时栈二节点的出栈顺序即为树的后序遍历
     * （当前节点的左右子树入栈一时，是左节点先入，以保证在入栈二时是后入）
     * @param root
     */
    public static void postorderTraversing2 (TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode spec= root;
        stack1.push(spec);
        while (!stack1.isEmpty()) {
            spec = stack1.pop();
            if (spec.lChild != null) {
                stack1.push(spec.lChild);
            }
            if (spec.rChild != null) {
                stack1.push(spec.rChild);
            }
            stack2.push(spec);
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + " ");
        }
    }

    /**
     * 按层遍历二叉树
     * 借助一个队列，不断将每层节点放入队列，放入下一层节点时打印当前层
     * LinkedList 实现了 Queue<E> 接口
     * @param root
     */
    public static void layerTraversing(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode cur = null;
        queue.offer(root);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur.lChild != null) {
                queue.offer(cur.lChild);
            }
            if (cur.rChild != null) {
                queue.offer(cur.rChild);
            }
            System.out.print(cur.val + " ");
        }
    }
}

class TreeNode {
    TreeNode lChild;
    TreeNode rChild;
    String val;
    TreeNode(String val) {
        this.val = val;
    }
}