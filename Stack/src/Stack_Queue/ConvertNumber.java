package Stack_Queue;

import java.util.Scanner;
import java.util.Stack;

//Nhập số nguyên dương, xuất biểu diễn hệ 16,10,8,2

public class ConvertNumber {
    public static String convert(int n, int base) {
        String S = "";
        Stack<Integer> stk = new Stack<Integer>();
        //Cất phần dư của phép chia vào stack
        do {
            stk.push(n % base);
            n /= base;
        } while (n > 0);
        //Lấy kết quả ra khỏi stack nối vào chuỗi S
        int x;
        while (!stk.empty()) {
            x = stk.pop();
            S += Character.forDigit(x, base);
        }
        return S;
    }

    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        n = Integer.parseInt(sc.nextLine());
        System.out.println("Base 16: " + convert(n, 16));
        System.out.println("Base 10: " + convert(n, 10));
        System.out.println("Base 8: " + convert(n, 8));
        System.out.println("Base 2: " + convert(n, 2));
    }
}
