import java.util.Random;
import java.util.Scanner;

public class TestSort {
    final static Scanner sc = new Scanner(System.in);
    //Tạo mảng ngẫu nhiên các số int đi từ int tới max
    public static Integer[] generateArray(int n, int min, int max) {
        if (max < min) {
            int t = min;
            min = max;
            max = t;
        }
        Integer a[] = new Integer[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) a[i] = min + r.nextInt(max - min);
        return a;
    }
    //Chép mảng đã có thành mảng khác
    public static Integer[] copy(Integer[] a) {
        Integer[] b = new Integer[a.length];
        for (int i = 0; i < a.length; i++) b[i] = a[i];
        return b;
    }
    //Xuất mảng
    public static void display(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
            if (i > -0 && i % 20 == 0) System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] a = null; //Mảng ban đầu
        Integer[] b = null; //Mảng sao chép từ a
        int n = 0, min = 0, max = 0;
        long t1 = 0, t2 = 0;
        Menu menu = new Menu();
        menu.add("Create an array of randomly integers");
        menu.add("Display initail array");
        menu.add("Bubble sort 1");
        menu.add("Bubble sort 2");
        menu.add("Insertion sort");
        menu.add("Selection sort");
        menu.add("Merge sort");
        menu.add("Quick sort");
        menu.add("Exit");
        int choice;
        do {
            choice = menu.getUserChoice();
            switch (choice) {
                case 1:
                    System.out.print("Number of randomly integers:");
                    n = Integer.parseInt(sc.nextLine());
                    System.out.print("Minimum value:");
                    min = Integer.parseInt(sc.nextLine());
                    System.out.print("Maximum:");
                    max = Integer.parseInt(sc.nextLine());
                    a = generateArray(n, min, max);
                    break;
                case 2:
                    System.out.println("Initial array:");
                    display(a);
                    break;
                case 3:
                    b = copy(a);
                    t1 = System.currentTimeMillis();
                    MyAscSorting.bubbleSort(b);
                    t2 = System.currentTimeMillis();
                    display(b);
                    System.out.println("Bubble sort 1:" + (t2 - t1) + "millisecs");
                    break;
                case 4:
                    b = copy(a);
                    t1 = System.currentTimeMillis();
                    MyAscSorting.bubbleSort2(b);
                    t2 = System.currentTimeMillis();
                    display(b);
                    System.out.println("Bubble sort 2:" + (t2 - t1) + "millisecs");
                    break;
                case 5:
                    b = copy(a);
                    t1 = System.currentTimeMillis();
                    MyAscSorting.insertSort(b);
                    t2 = System.currentTimeMillis();
                    display(b);
                    System.out.println("Insertion sort:" + (t2 - t1) + "millisecs");
                    break;
                case 6:
                    b = copy(a);
                    t1 = System.currentTimeMillis();
                    MyAscSorting.selectionSort(b);
                    t2 = System.currentTimeMillis();
                    display(b);
                    System.out.println("Selection sort:" + (t2 - t1) + "millisecs");
                    break;
                case 7:
                    b = copy(a);
                    t1 = System.currentTimeMillis();
                    MyAscSorting.mergeSort(b);
                    t2 = System.currentTimeMillis();
                    display(b);
                    System.out.println("Merge sort:" + (t2 - t1) + "millisecs");
                    break;
                case 8:
                    b = copy(a);
                    t1 = System.currentTimeMillis();
                    MyAscSorting.quickSort(b);
                    t2 = System.currentTimeMillis();
                    display(b);
                    System.out.println("Quick sort:" + (t2 - t1) + "millisecs");
                    break;
            }
        }
        while (choice > 0 && choice < 9);
    }
}

