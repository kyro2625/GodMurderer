package Stack_Queue;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

//Tính trị biểu thức số nguyên dạng hậu tố có toán tử + - * /
public class PostFixExpression {
    static double evaluatePostFixExp(String exp) {
        double result = 0;
        // Cắt biểu thức thành các chuỗi con
        StringTokenizer stk = new StringTokenizer(exp, "() ");
        Stack<Double> stack = new Stack<Double>();
        while (stk.hasMoreTokens()) {
            String S = stk.nextToken();
            if (!S.equals("+") && !S.equals("-") && !S.equals("*") && !S.equals("/"))
                stack.push(Double.parseDouble(S)); //cất toán hạng vào stack
            else {
                //lấy toán hạng trong stack ra để thực hiện phép toán
                double n1 = stack.pop();
                double n2 = stack.pop();
                if (S.equals("+")) result = n1 + n2;
                else if (S.equals("-")) result = n1 - n2;
                else if (S.equals("*")) result = n1 * n2;
                else if (S.equals("/")) {
                    if (n2 == 0.0) throw new RuntimeException("Divided by 0!");
                    else result = n1 / n2;
                } else throw new RuntimeException("This operator is not supported!");
                stack.push(result); //cất ket71 quả trung gian vào stack
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S;
        System.out.print("Enter an string of post fix expression: ");
        S = sc.nextLine();
        System.out.println("Result= " + evaluatePostFixExp(S));
    }
}
