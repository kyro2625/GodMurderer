package List1;

public class SLLNode<T> {
    T info;
    SLLNode<T> next;

    public SLLNode() {
        next = null;
    }

    // Tao 1 phan tu cuoi
    public SLLNode(T e1) {
        info = e1;
        next = null;
    }

    // Tao phan tu dung truoc afterEle
    public SLLNode(T e1, SLLNode<T> afterEle) {
        info =  e1;
        next = afterEle;
    }
}