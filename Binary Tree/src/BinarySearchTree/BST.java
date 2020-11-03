package BinarySearchTree;

import InorderBinaryTree.MyQueue;

import java.util.Stack;

//Cây BST tỏng quát
//Hầu hết các hành vi cần có trên cây BST đã được hiện thực trong lớp này
public class BST<T extends Comparable<? super T>> {
    protected BSTNode<T> root = null;

    public BST() {
    }

    public void clear() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /*Hàm chèn phần tử mới KHÔNG DÙNG KỸ THUẬT DỆ QUY. Phần tử mới é sẽ được chèn
     * thành một nút lá mới trong cây và là con của một nút là previous ĐÃ CÓ
     * ==> cần biết nút previous để cập nhật tham chiếu đến nút con mới này */
    public void insert(T e1) {
        if (root == null) {
            //Nếu cây trống thì e1 sẽ được đưa vào nút gốc
            root = new BSTNode<T>(e1);
            return;
        }
        //Nếu cây có rồi, tìm nút cha để chèn nút con
        BSTNode<T> p = root, prev = null; //p : biến tạm để chạy trước
        while (p != null) {
            //tìm vị trí chèn nút mới
            prev = p;
            if (p.e1.compareTo(e1) < 0) p = p.right;
            else p = p.left;
        }
        //Nếu nút cha có data <data của e1 ==> chèn vào bên phải
        if (prev.e1.compareTo(e1) < 0) prev.right = new BSTNode<T>(e1);
            //Ngược lại, chèn bên trái, chấp nhận nút trùng lặp
            //Nếu không muốn trị trùng lặp thì thêm if(prev.e1.compareTo(e1) >0 )
        else prev.left = new BSTNode<T>(e1);
    }

    /*  Chèn phần tử DÙNG KĨ THUẬT DỆ QUY - Recursion. Nếu CÂY CON p trống thì
     *   cấp phát mới và trả về nút mới này. Nếu p không trống thì đệ quy tác vụ
     *   xuống nút con để chèn vào nút lá */
    protected BSTNode<T> recInsert(BSTNode<T> p, T e1) {
        if (p == null) p = new BSTNode<T>(e1);
        else if (p.e1.compareTo(e1) < 0) p.right = recInsert(p.right, e1);
            //Nếu không muốn trị trùng lặp thì thêm if(prev.e1.compareTo(e1) >0)
        else p.left = recInsert(p.left, e1);
        return p;
    }

    //Chèn phần tử e1 vào cây
    public void recInsert(T e1) {
        root = recInsert(root, e1);
    }

    //Vì data có thự tự nên việc tìm kiếm được hiện thực dễ dàng nhờ vòng lặp
    //O(logn) cho cây cân bằng, O(n) cho cây suy thoái về 1 hướng, n: số nút
    protected T search(T e1) {
        BSTNode<T> p = root;
        while (p != null)
            if (e1.equals(p.e1)) return p.e1;
            else if (e1.compareTo(p.e1) < 0) p = p.left;
            else p = p.right;
        return null;
    }

    //kiềm tra e1 có trong cây không
    public boolean isInTree(T e1) {
        return search(e1) != null;
    }

    //Viếng nút p
    protected void visit(BSTNode<T> p) {
        //code phù hợp với việc xủ lý bài toán
        System.out.print(p.e1 + " ");
    }

    //các phép duyệt nút cơ bản
    protected void inorder(BSTNode<T> p) {
        if (p != null) {
            inorder(p.left);    //LEFT
            visit(p);           //NODE
            inorder(p.right);   //RIGHT
        }
    }

    protected void preorder(BSTNode<T> p) {
        if (p != null) {
            visit(p);       //NODE
            preorder(p.left);   //LEFT
            preorder(p.right);  //RIGHT
        }
    }

    protected void postorder(BSTNode<T> p) {
        if (p != null) {
            postorder(p.left);  //LEFT
            postorder(p.right); //RIGHT
            visit(p);           //NODE
        }
    }

    //Các tác vụ duyệt toàn bộ cây
    public void preorder() {
        preorder(root);
    }

    public void inorder() {
        inorder(root);
    }

    public void postorder() {
        postorder(root);
    }

    //Xóa phần tử e1 bằng PHƯƠNG PHÁP TRỘN
    //Phương pháp này sẽ cập nhật các tham chiếu rồi xóa phần tử được chọn
    //Sao cho thứ tự dự liệu phải được bảo tồn
    //TÌNH HUỐNG NÚT BỊ XÓA CÓ 2 CÂY CON, TRÔN 2 CÂY CON NÀY THÀNH 1
    public void deleteByMerging(T e1) {
        //Đi tìm nút bị xóa p (ứng với e1) và nút cha previous của nó
        BSTNode<T> p = root, prev = null;
        while (p != null && !p.e1.equals(e1)) {
            prev = p;
            if (p.e1.compareTo(e1) < 0) p = p.right;
            else p = p.left;
        }
        //Khởi tạo nút cần bảo tồn, node, là nút bị xóa p
        //sau đó cập nhật tùy tình huống
        BSTNode<T> node = p;
        BSTNode<T> tmp;     // Biến tạm, nút bên phải của cây con trái
        if (p != null && p.e1.equals(e1)) {
            //Có phần tử cần phải xóa
            //Nếu node không có con phải => gắn con trái của nó vào nút cha
            //=> chưyển node về node.left để gắn vào previous sau này
            if (node.right == null) node = node.left;
                //và ngược lại
            else if (node.left == null) node = node.right;
            else {
                //node có cả 2 coon, trộn các cây con
                //tìm nút bên phải của cây con trái
                tmp = node.left;
                while (tmp.right != null) tmp = tmp.right;
                //Gắn bên phải của node vào bên phải của tmp vì chúng có trị lớn hơn
                tmp.right = node.right;
                //Giú node.left để hiệu chỉnh vào previous
                node = node.left;
            }
            //Trường hợp nút bị xóa là nút gốc
            if (p == root) root = node; //node là gốc của cây kết quả
                // nếu xóa nút con trái của nút ch
            else if (prev.left == p) prev.left = node;
                //nếu xóa nút con phải của nút cha
            else prev.right = node;
        } else if (root != null) System.out.println("Element " + e1 + " is not in the tree");
        else System.out.println("The tree is empty!");
    }

    //Xóa phàn tử e1 dụa trên phép sao chép
    //Copy dữ liệu của nút con Bên Phải của cây con trái vào nút bị xóa rồi hủy nút bên phải này
    public void deleteByCopying(T e1) {
        //Đi tìm nút bị xóa p(ứng với e1) và nút cha previous của nó
        BSTNode<T> p = root, prev = null;
        while (p != null && !p.e1.equals(e1)) {
            //tìm nút p với phần tử e1
            prev = p;
            if (p.e1.compareTo(e1) < 0) p = p.right;
            else p = p.left;
        }
        //Khởi tạo nút cần bảo tồn là nút bị xóa sau đó cập nhật tùy tình huống
        BSTNode<T> node = p;
        if (p != null && p.e1.equals(e1)) {
            //có nút bị xóa
            //node này không có con phải, bảo tồn con trái
            if (node.right == null) node = node.right;
                //node này không có con trái, bảo tồn con phải
            else if (node.left == null) node = node.right;
            else {
                //node cần phải xóa có cả 2 con
                //Tìm tmp là nút bên phải của cây con trái của node
                BSTNode<T> previous = node;
                BSTNode<T> tmp = node.left;
                while (tmp.right != null) {
                    previous = tmp;
                    tmp = tmp.right;
                }
                //chép data của nút biên phải vào nút cần xóa node
                node.e1 = tmp.e1;
                //Nút biên trái không có cây con phải -> cập nhật previous.left
                if (previous == node) previous.left = tmp.left;
                    //Nút biên phải có cây con phải, previous nằm dưới node
                    //Móc temp.left ( có trị lớn hơn ) và bên phải của previous (trị nhỏ hơn)
                else previous.right = tmp.left;
            }
            //Nếu nút bị xóa là nút gốc, thay gốc bằng node
            if (p == root) root = node;
                //Nếu nút bị xóa là con trái của nút cha, thay con trái của previous
            else if (prev.left == p) prev.left = node;
                //nếu nút bị xóa là con phải của nút cha, thay con phải của previous
            else prev.right = node;
        } else if (root != null) // không có nút bị xóa
            System.out.println("Element " + e1 + " is not in the tree!");
        else System.out.println("The tree is empty");
    }

    //Duyệt preorder bằng phép lặp dùng Stack
    public void interactivePreorder() {
        BSTNode<T> p = root;
        Stack<BSTNode<T>> stack = new Stack<BSTNode<T>>();
        if (p != null) {
            stack.push(p);
            while (!stack.isEmpty()) {
                p = stack.pop();
                visit(p);           //NODE
                //Cất right vào trước dể lấy ra sau
                if (p.right != null) stack.push(p.right);
                //Cất left vào sau để lấy ra trước
                if (p.left != null) stack.push(p.left);
            }
        }
    }

    //Duyệt inorder bằng phép lặp dùng Stack
    public void interactiveInorder() {
        BSTNode<T> p = root;
        Stack<BSTNode<T>> stack = new Stack<BSTNode<T>>();
        while (p != null) {
            while (p != null) {
                //Cất right vào stack trước để lấy ra sau
                if (p.right != null) stack.push(p.right);
                stack.push(p); //Cất nút hiện hành sau để lấy ra trước right
                p = p.left;     //Chuyển sang trái để duyệt left trước
            }
            p = stack.pop();    //Lấy ra 1 nút trong stack
            //Khi còn nút phải xét và nút này không có con phải
            while (!stack.isEmpty() && p.right == null) {
                visit(p);
                p = stack.pop();
            }
            visit(p); //viếng nút có con phải
            if (!stack.isEmpty()) p = stack.pop();
            else p = null;
        }
    }

    //Duyệt postorder dùng stack tự quản lý
    //Cần 2 stack: stack để thao tác
    //Giải thuật này đòi hỏi bộ nhớ phải đủ chưa toàn bộ các nút của cây
    public void interactivePostorder2() {
        BSTNode<T> p = root;
        //stack giúp cất tạm các nút
        Stack<BSTNode<T>> stack = new Stack<BSTNode<T>>();
        //stack chứa thứ tự duyệt
        Stack<BSTNode<T>> output = new Stack<BSTNode<T>>();
        if (p != null) {
            //left -to- right postorder = right-to-left preorder;
            stack.push(p);
            while (!stack.isEmpty()) {
                p = stack.pop();    //Lấy ra NODE
                output.push(p);         //cất vào output
                if (p.left != null) stack.push(p.left);
                if (p.right != null) stack.push(p.right);
            }
            //duyệt từng nút theo tự tự đã có
            while (!output.isEmpty()) {
                p = output.pop();
                visit(p);
            }
        }
    }

    public void interactivePostorder() {
        BSTNode<T> p = root, q = root;
        Stack<BSTNode<T>> stack = new Stack<BSTNode<T>>();
        while (p != null) {
            //Cất các left vào stack
            for (; p.left != null; p = p.left) stack.push(p);
            while (p != null && (p.right == null || p.right == q)) {
                visit(p);
                q = p;
                if (stack.isEmpty()) return;
                p = stack.pop();
            }
            stack.push(p);
            p = p.right;
        }
    }

    public void breadthFirst() {
        BSTNode<T> p = root;
        MyQueue<BSTNode<T>> queue = new MyQueue<BSTNode<T>>();
        if (p != null) {
            queue.enqueue(p);
            while (!queue.isEmpty()) {
                p = queue.dequeue();
                visit(p);
                if (p.left != null) queue.enqueue(p.left);
                if (p.right != null) queue.enqueue(p.right);
            }
        }
    }

    public void MorrisInorder() {
        BSTNode<T> p = root, tmp;
        while (p != null)
            if (p.left == null) {
                visit(p);
                p = p.right;
            } else {
                tmp = p.left;
                while (tmp.right != null && tmp.right != p) tmp = tmp.right;
                if (tmp.right == null) {
                    tmp.right = p;
                    p = p.left;
                } else {
                    visit(p);
                    tmp.right = null;
                    p = p.right;
                }
            }
    }

    public void MorrisPreorder() {
        BSTNode<T> p = root, tmp;
        while (p != null)
            if (p.left == null) {
                visit(p);
                p = p.right;
            } else {
                tmp = p.left;
                while (tmp.right != null && tmp.right != p) tmp = tmp.right;
                if (tmp.right == null) {
                    visit(p);
                    tmp.right = p;
                    p = p.left;
                } else {
                    tmp.right = null;
                    p = p.right;
                }
            }
    }

    public void MorrisPostorder() {
        BSTNode<T> p = new BSTNode<T>(), tmp, q, r, s;
        p.left = root;
        while (p != null)
            if (p.left == null) p = p.right;
            else {
                tmp = p.left;
                while (tmp.right != null && tmp.right != p) tmp = tmp.right;
                if (tmp.right == null) {
                    tmp.right = p;
                    p = p.left;
                } else {
                    for (q = p.left, r = q.right, s = r.right; r != p; q = r, r = s, s = s.right) r.right = q;
                    for (s = q.right; q != p.left; q.right = r, r = q, q = s, s = s.right) visit(q);
                    visit(p.left);
                    tmp.right = null;
                    p = p.right;
                }
            }
    }

    //Duyệt inorder dạng gióng hàng
    private void visit_align(BSTNode p, int level) {
        if (p==null) return;
        if (level>0) {
            for (int i=0; i<level-1; i++) System.out.print("   ");
            System.out.print("  |__");
        }
        System.out.println(p.e1);   //NODE
        visit_align(p.left, level+1);   //LEFT
        visit_align(p.right, level+1);  //RIGHT
    }

    //Xuất cây dạng gióng hàng
    public void print_align() {
        visit_align(root,0);
    }
    //chèn 1 nhóm trị ĐÃ CÓ THỰ TỰ TĂNG từ vị trí first đến vị trí last
    //vào cây để tạo cây cân bằng
    public void insertBalance(T data[], int first, int last) {
        if(first <= last) {
            int middle = (first+last)/2;
            insert(data[middle]);
            insertBalance(data, first,middle-1);
            insertBalance(data,middle+1, last);
        }
    }
    public void insertBalance(T data[]) {
        insertBalance(data,0, data.length-1);
    }
}
