import java.io.PrintStream;

//Bài toán sắp đặt n quân hậu trên bàn cờ nxn
public class Queens {
    int n;      //số ô trên hàng/cột = số quân hậu
    final boolean available = true;
    int[] rows = null;   //mảng các vị trí sẽ đặt rows[i]=a-->(i,a)
    boolean[] columns = null;    //mảng đánh dấu cột nào còn được phép đặt
    boolean[] leftDiagonals = null;     //mảng đánh dấu đường chéo trái nào còn được phép đặt
    boolean[] rightDiagonals = null;    //mảng đánh dấu đường chéo phải nào còn được phép đặt
    int count = 0; //số cách giải

    public Queens(int n) {
        this.n = n;
        //Khởi tạo các mảng
        int i;
        rows = new int[n];
        columns = new boolean[n];
        for (i = 0; i < n; i++) {
            rows[i] = -1;
            columns[i] = available;
        }
        leftDiagonals = new boolean[2 * n - 1];
        rightDiagonals = new boolean[2 * n - 1];
        for (i = 0; i < 2 * n - 1; i++) {
            leftDiagonals[i] = rightDiagonals[i] = available;
        }
        count = 0;
    }

    //kiểm tra vị trí (row, col) có thể đặt 1 quân cờ không
    private boolean validPosition(int row, int col) {
        return (columns[col] == available) && (leftDiagonals[row + col] == available) && (rightDiagonals[row - col + n - 1] == available);
    }

    //In bàn cờ
    public void printBoard(PrintStream stream) {
        //Dùng Q: mô tả quân hậu,, dùng - mô tả ô trống
        if (count <= 0) return;
        stream.println("Solution " + count + ": ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) stream.print((rows[i] == j) ? "Q " : "- ");
            stream.println();
        }
    }

    //Backtrack đẻ đặt quân cờ ở dòng r
    public void putQueen(int row) {
        for (int col = 0; col < n; col++) {
            if (validPosition(row, col)) {
                rows[row] = col;
                columns[col] = !available;
                leftDiagonals[row + col] = !available;
                rightDiagonals[row - col + n - 1] = !available;
                if (row < n - 1) putQueen(row + 1); //chưa xếp xong
                else {
                    //xong 1 lời giải
                    count++;
                    printBoard(System.out);
                }// in bàn cờ
                columns[col] = available;
                leftDiagonals[row + col] = available;
                rightDiagonals[row - col + n - 1] = available;
            }
        }
    }
    //Giải bài toán n queens
    public void solve() {
        count = 0;
        putQueen(0);
    }

    public static void main(String[] args) {
        Queens problem = new Queens(8);
        problem.solve();
        System.out.println("Number od solutions: " + problem.count);
    }
}
