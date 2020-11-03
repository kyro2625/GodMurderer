package BinarySearchTree;

import java.lang.Comparable;

/*Lớp cho 1 nút tổng quát trong cây BST
* Vì cây có thứ tự nên dữ liệu trên cây phải có khả năng so sánh
* => nút thực tế phải hiện thực interface: java.lang.Comparable<T extends Comparable>
 và có lớp chặn dưới là lớp T này <T extends Comparable<?super T>>*/
public class BSTNode<T extends Comparable<? super T>> {
    protected T e1;
    protected BSTNode<T> left, right;

    public BSTNode() {
        left = right = null;
    }

    public BSTNode(T e1) {
        this(e1, null, null);
    }

    public BSTNode(T e1, BSTNode<T> lt, BSTNode<T> rt) {
        this.e1 = e1;
        left = lt;
        right = rt;
    }
}
