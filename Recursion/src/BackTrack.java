/*  Có n biến có thể mang các trị phân biệt từ 0 đến n-1
 *   Hãy tìm ra các giải pháp gán trị cho các biến sao cho 2 trị
 *   kề nhau phải có trị chênh lệch từ d1 đến d2, d1<d2 */

public class BackTrack {
    int n; //số biến, miền trị 0 .. n-1
    int a[]; //tập biến
    int d1, d2; //hai điều kiện của lời giải
    boolean success = false; //biến mô tả kết quả
    int count = 0; //số lời giải

    public BackTrack(int n, int d1, int d2) {
        this.n = n;
        a = new int[n]; //khởi động tập biến
        if (d1 > d2) {
            int t = d1;
            d1 = d2;
            d2 = t;
        }
        this.d1 = d1;
        this.d2 = d2;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int[] getA() {
        return a;
    }

    public void setA(int[] a) {
        this.a = a;
    }

    public int getD1() {
        return d1;
    }

    public void setD1(int d1) {
        this.d1 = d1;
    }

    public int getD2() {
        return d2;
    }

    public void setD2(int d2) {
        this.d2 = d2;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //Lấy lời giải
    public int[] getSolution() {
        return success == true ? a : null;
    }

    //kiểm tra tính hợp lệ cho đến biến thứ i khi gán trị val cho biến này
    //ĐIỀU KIỆN CHUNG CỦA BÀI TOÁN
    boolean valid(int i, int val) {
        if (i == 0) return true;
        a[i] = val;
        int d = Math.abs(a[i] - a[i - 1]);
        boolean proceed = d >= d1 && d <= d2; // điều kiện chênh lệch
        int j = i - 1;
        while (proceed && j >= 0) {
            proceed = (a[i] != a[j--]);
        }
        return proceed;
    }

    //Xuất - xử lý lời giải (1 cấu hình)
    public void processSolution() {
        //code cho việc xử lý 1 cấu hình
        for (int i = 0; i < n - 1; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println(a[n - 1]);
    }

    //Giải thuật backtrack cho bài toán
    private void backtrack(int i) {
        if (i >= n) return;
        for (int val = 0; val < n; val++) //duyệt tập biến
            if (valid(i, val)) {
                a[i] = val;
                if (i == n - 1) {
                    success = true;
                    count++;    //tăng số lời giải
                    processSolution();
                } else backtrack(i + 1);
            }
    }
    public void solve() {
        success = false;
        backtrack(0);
        if (success==false) System.out.println("No Solution!");
    }

    public static void main(String[] args) {
        //10 biến, chênh nhau trị từ 3 -> 5
        BackTrack problem = new BackTrack(10,3,5);
        problem.solve();
        System.out.println("Solution count: " + problem.getCount());
    }
    
}
