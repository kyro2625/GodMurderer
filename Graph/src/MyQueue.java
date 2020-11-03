import java.util.LinkedList;

//Lớp này được dùng trong phép duyệt cây Breadth-first
public class MyQueue<E> extends LinkedList<E> {
    public MyQueue() {
        super();
    }

    //Thêm vào cuối hàng đợi
    public void enqueue(E x) {
        this.addLast(x);
    }

    public E dequeue(E x) {
        return this.poll();
    }
    public E dequeue() {
        return this.poll();
    }
}
