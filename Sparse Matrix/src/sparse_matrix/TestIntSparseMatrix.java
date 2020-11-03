package sparse_matrix;

public class TestIntSparseMatrix {
    //Print out a matrix
    public static void print(IntSparseMatrix m) {
        if (m == null) {
            System.out.println("Empty matrix!");
            return;
        }
        for (int i = 0; i<m.getMaxRow(); i++) {
            for (int j = 0; j < m.getMaxCol(); j++) {
                Integer val = m.get(i, j);
                System.out.print((val == null ? 0 : val) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int i,j,k;
        // m1                           // m2
        // 0 1 2 3 4 5 6 7 8 9          // 0 1 2 3 4 5 6 7 8 9
        // 0 0 0 0 0 0 1 0 0 2          // 1 0 0 0 0 0 0 0 0 0
        // 3 0 0 0 4 0 0 0 5 0          // 0 1 0 0 0 0 0 0 0 0
        // 6 0 0 7 0 0 8 0 0 0          // 0 0 1 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 9 8 7 0          // 0 0 0 1 0 0 0 0 0 0
        // 0 6 0 0 5 4 0 0 0 0          // 0 0 0 0 1 0 0 0 0 0
        // 0 0 3 2 0 0 0 0 0 0          // 0 0 0 0 0 1 0 0 0 0
        // other values are 0           // other values are 0
        IntSparseMatrix m1 = new IntSparseMatrix(10,10);
        IntSparseMatrix m2 = new IntSparseMatrix(10,10);
        //Setup 2 matrix
        m1.add(0,6,1);
        m1.add(0,9,2);
        m1.add(1,0,3);
        m1.add(1,4,4);
        m1.add(1,8,5);
        m1.add(2,0,6);
        m1.add(2,3,7);
        m1.add(2,6,8);
        m1.add(3,6,9);
        m1.add(3,7,8);
        m1.add(3,8,7);
        m1.add(4,1,6);
        m1.add(4,4,5);
        m1.add(4,5,4);
        m1.add(5,2,3);
        m1.add(5,3,2);
        m2.add(0,0,1);
        m2.add(1,1,1);
        m2.add(2,2,1);
        m2.add(3,3,1);
        m2.add(4,4,1);
        m2.add(5,5,1);

        //Print the matrix
        System.out.println("Matrix 1: ");
        print(m1);
        System.out.println("Matrix 2: ");
        print(m2);
        System.out.println("Multiplication of matrices: ");
        IntSparseMatrix m4 = m1.multiply(m2);
        print(m4);
        //Lỗi
        System.out.println("Sum of matrices: ");
        IntSparseMatrix m3 = m1.add(m2);
        print(m3);

    }
}
