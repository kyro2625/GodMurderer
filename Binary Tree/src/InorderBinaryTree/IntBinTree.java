package InorderBinaryTree;

import java.util.Scanner;

//Lớp mô tả cây nhị phân số nguyên
public class IntBinTree {
    final int LEFT = 0;
    final int RIGHT = 1;
    IntBinTreeNode root;
    public static Scanner sc = new Scanner(System.in);

    public IntBinTree() {
        root = null;
    }

    //Viếng 1 nút p trên cây
    protected void visit(IntBinTreeNode p) {
        System.out.print(p.key + " ");
    }

    //CHÚ Ý: Dùng kỹ thuật đệ quy thường phải viết 2 methods
    //Duyệt inorder nút p - Left Node Right
    public void inorder(IntBinTreeNode p) {
        if (p != null) {
            inorder(p.left); //Duyệt nút con trái - LEFT
            visit(p);
            inorder(p.right); //Duyệt nút con phải - RIGHT
        }
    }

    //Duyệt cây LNR
    public void inorder() {
        inorder(root);
    }

    //Duyệt tìm 1 key trên cây con gốc p inorder nút p - NLR
    public IntBinTreeNode search_inorder(int key, IntBinTreeNode p) {
        IntBinTreeNode result = p;
        if (p == null) return null;
        else if (p.key == key) return p;
        else {
            result = search_inorder(key, p.left);
            if (result == null) result = search_inorder(key, p.right);
        }
        return result;
    }

    public IntBinTreeNode search_inorder(int key) {
        return search_inorder(key, root);
    }

    //Nhập cây số nguyên từ bàn phím
    protected IntBinTreeNode input(IntBinTreeNode p) {
        int x; // trị nhập
        System.out.print("Enter an integer for a node, 0 for quit: ");
        x = Integer.parseInt(sc.nextLine());
        if (x != 0 && p == null) {
            //chỉ đưa vvào trị khác 0
            p = new IntBinTreeNode(x);                  //NODE
            System.out.print("Left of " + x + ": ");    //Nhập cây con trái
            p.left = input(p.left);                     //LEFT
            System.out.print("Right of " + x + ": ");   //Nhập cây con phải
            p.right = input(p.right);                   //RIGHT
        }
        return p;
    }

    public void input() {
        root = null;    //hủy cây cũ nếu có để nhập cây mới
        root = input(root);
    }

    //BREADTH-FIRST TRAVERSAL - Duyệt theo hàng ngang
    public void breadthFirst() {
        if (root == null) {
            System.out.println("Empty tree!");
            return;
        }
        IntBinTreeNode p = root;
        MyQueue queue = new MyQueue();
        queue.enqueue(p);       //Khởi tạo queue là nút gốc để bắt đầu duyệt
        while (!queue.isEmpty()) {
            //khi chưa duyệt xong
            p = (IntBinTreeNode) queue.dequeue(p.left);
            visit(p);
            //cất 2 nút con vào queue
            if (p.left != null) queue.enqueue(p.left);
            if (p.right != null) queue.enqueue(p.right);
        }
    }

    //Duyệt nút p ở mức L với mức biết trước cần duyệt level
    protected void inorder_level(IntBinTreeNode p, int L, int level) {
        if (p != null) {
            if (L == level) visit(p);
            else if (L < level) {
                inorder_level(p.left, L + 1, level);
                inorder_level(p.right, L + 1, level);
            }
        }
    }

    public void inorder_level(int level) {
        inorder_level(root, 0, level); //nút gốc có mức 0
    }

    //Duyệt tìm chiều cao của cây
    protected int height(IntBinTreeNode p) {
        if ((p == null) || (p.left == null && p.right == null)) return 0;
        int hL = 1 + height(p.left);
        int hR = 1 + height(p.right);
        return hL > hR ? hL : hR;
    }

    public int height() {
        return height(root);
    }

    public static void main(String[] args) {
        IntBinTree tree = new IntBinTree();
        System.out.println("Input a tree: ");
        tree.input();
        System.out.println("Inorder - LNR - Recursive Traversal: ");
        tree.inorder();
        System.out.println();
        System.out.println("Bread-first Traversal: ");
        tree.breadthFirst();
        System.out.println();
        int h = tree.height();
        System.out.println("Height: " + h);
        int x; //trị cần tìm
        System.out.print("Input a searched value: ");
        x = Integer.parseInt(sc.nextLine());
        IntBinTreeNode p = tree.search_inorder(x);
        if (p == null) System.out.println("The value " + x + " does not exist!");
        else System.out.println("The value " + x + " exists.");
        int level;
        System.out.print("Input a level: ");
        level = Integer.parseInt(sc.nextLine());
        if (level > h) System.out.println("This level does not exist!");
        else tree.inorder_level(level);
    }
}
