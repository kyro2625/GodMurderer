package List1;

public class SLL<T> {
    protected SLLNode<T> head, tail;

    //Initialize an empty list
    public SLL() {
        head = tail = null;
    }
    //Getters, setters

    public SLLNode<T> getHead() {
        return head;
    }

    public void setHead(SLLNode<T> head) {
        this.head = head;
    }

    public SLLNode<T> getTail() {
        return tail;
    }

    public void setTail(SLLNode<T> tail) {
        this.tail = tail;
    }

    //Check whether the list is empty
    public boolean isEmpty() {
        return head == null;
    }

    // Add an element to the head of the list
    public void addToHead(T e1) {
        head = new SLLNode<T>(e1, head);
        if (tail == null) tail = head;
    }

    //Add an element to the end of the list
    public void addToTail(T e1) {
        if (!isEmpty()) {
            tail.next = new SLLNode<T>(e1);
            tail = tail.next;
        } else head = tail = new SLLNode<T>(e1);
    }

    //Delete the head and return the deleted info
    public T deleteFromHead() {
        if (isEmpty()) return null;
        T e1 = head.info;
        if (head == tail) head = tail = null; //if only one node on the list
        else head = head.next;
        return e1;
    }

    //delete the tail and return the deleted info
    public T deleteFromTail() {
        if (isEmpty()) return null;
        T e1 = tail.info;
        if (head == tail) head = tail = null; //if only one node in the list
        else {                              //if more than one node in the list
            SLLNode<T> tmp;                 // find the predecessor of tail
            for (tmp = head; tmp.next != tail; tmp = tmp.next) ;
            tail = tmp;
            tail.next = null;
        }
        return e1;
    }

    //delete the node containing info = e1
    public void delete(T e1) {
        if (!isEmpty())
            if (head == tail && e1.equals((head.info))) //if only one
                head = tail = null;               //node on the list
            else if (e1.equals(head.info))              //if more than one node on the list
                head = head.next;                //and e1 is in a non-head node;
            else {                                      //if more than one non-head node
                SLLNode<T> pred, tmp;            //and e1 is in a non-head node
                for (pred = head, tmp = head.next; tmp != null && !(tmp.info.equals(e1)); pred = pred.next, tmp = tmp.next)
                    ;
                if (tmp != null) {        //if e1 was found
                    pred.next = tmp.next;
                    if (tmp == tail)   //if e1 is in the last node
                        tail = pred;
                }
            }
    }

    //Print the list
    public void printAll() {
        for (SLLNode<T> tmp = head; tmp != null; tmp = tmp.next)
            System.out.println(tmp.info);
    }

    //Checking whether the e1 element is in the list
    public boolean isInList(T e1) {
        SLLNode<T> tmp;
        for (tmp = head; tmp != null && !tmp.info.equals(e1); tmp = tmp.next) ;
        return tmp != null;
    }
}
