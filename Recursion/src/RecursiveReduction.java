import java.util.Stack;

public class RecursiveReduction {
    public static int fractorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * fractorial(n - 1);
        }
    }

    public static int fibo1(int n) {
        if (n < 3) return 1;
        return fibo1(n - 2) + fibo1(n - 1);
    }

    public static void reverseInteger1(int n) {
        if (n < 0) return;
        System.out.print(n % 10);
        n = n / 10;
        if (n > 0) reverseInteger1(n);
    }

    //Khử đệ quy: Dùng stack để lưu trữ dữ liệu còn tính toán
    public static void reverseInteger2(int n) {
        if (n < 0) return;
        Stack<Integer> stk = new Stack<Integer>();
        stk.push(n); //cất n vào stack
        while (!stk.empty()) {
            int x; // dữ liệu xử lý hiện hành
            //cất dữ liệu vào stack
            x = stk.pop();
            System.out.print(x % 10);
            x /= 10;
            if (x > 0) stk.push(x);
        }
    }

    public static void main(String[] args) {
        int n = 132098;
        reverseInteger1(n);
        System.out.println();
        reverseInteger2(n);
        System.out.println();
        System.out.println(fractorial(5));
        System.out.println(fibo1(40));
    }
}
