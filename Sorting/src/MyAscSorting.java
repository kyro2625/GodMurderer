public class MyAscSorting {
    //Check whether an array is ascending or not
    public static boolean isAscending(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) if (a[i].compareTo(a[i + 1]) > 0) return false;
        return true;
    }

    //Swap element at position i with element at position k
    private static void swap(Comparable[] a, int i, int k) {
        Comparable x = a[i];
        a[i] = a[k];
        a[k] = x;
    }

    //Selection sort
    public static void selectionSort(Comparable[] a, int first, int last) {
        int i, j, minIndex;
        Comparable minVal;
        int n = a.length;
        for (i = first; i < last - 1; i++) {
            minVal = a[i];
            minIndex = i;
            for (j = i + 1; j < last; j++)
                if (a[j].compareTo(minVal) < 0) {
                    minIndex = j;
                    minVal = a[j];
                }
            if (minIndex != i) swap(a, i, minIndex);
        }
    }

    public static void selectionSort(Comparable[] a) {
        selectionSort(a, 0, a.length);
    }

    //Bubble sort
    public static void bubbleSort(Comparable[] a, int first, int last) {
        int i;
        boolean mustSwap;
        do {
            mustSwap = false;
            for (i = first; i < last - 1; i++)
                if (a[i].compareTo(a[i + 1]) > 0) {
                    swap(a, i, i + 1);
                    mustSwap = true;
                }
        } while (mustSwap);
    }

    public static void bubbleSort(Comparable[] a) {
        bubbleSort(a, 0, a.length);
    }

    //Bubble sort - ann alternative
    public static void bubbleSort2(Comparable[] a, int first, int last) {
        int i, k;
        boolean mustSwap;
        k = 1;
        do {
            mustSwap = false;
            for (i = first; i < last - k; i++)
                if (a[i].compareTo(a[i + 1]) > 0) {
                    swap(a, i, i + 1);
                    mustSwap = true;
                }
            i++;
        } while (mustSwap);
    }

    public static void bubbleSort2(Comparable[] a) {
        bubbleSort2(a, 0, a.length);
    }

    public static void insertSort(Comparable[] a, int first, int last) {
        int i, j, n = a.length;
        Comparable x;
        for (i = first + 1; i < last; i++) {
            //Ban đầu dãy chỉ có 1 phần tử a[0]
            x = a[i]; //chèn phần tử a[i] vào dãy đã sắp xếp a[0], a[1], a[2], ... , a[i-1]
            j = i;
            while (j > 0 && x.compareTo(a[j - 1]) < 0) {
                a[j] = a[j - 1];  //Kéo nút lớn hơn x lên h vị trí
                j--;
            }
            //Chèn x vào vị trí hợp lệ, j là vị trí đầu tiên mà x>=a[j-1]
            a[j] = x;
        }
    }

    public static void insertSort(Comparable[] a) {
        insertSort(a, 0, a.length);
    }

    public static void quickSort(Comparable[] a, int first, int last) {
        int lower = first + 1, upper = last;
        swap(a, first, (first + last) / 2);
        //trị đầu tiên là trị giữa ban đầu --> Chọn làm trị đứng ở trục
        Comparable bound = a[first];
        //Hoán vị trị nhỏ về đầu, trị lớn về cuối
        //partion mảng thành 2 phần, trị nhỏ ở trước, lớn ở sau
        while (lower <= upper) {
            while (bound.compareTo(a[lower]) > 0) lower++;
            while (bound.compareTo(a[upper]) < 0) upper--;
            if (lower < upper) swap(a, lower++, upper--);
            else lower++;
        }
        swap(a, upper, first);
        if (first < upper - 1) quickSort(a, first, upper - 1);
        if (upper + 1 < last) quickSort(a, upper + 1, last);
    }

    public static void quickSort(Comparable[] a) {
        if (a.length < 2) return;
        int max = 0;
        //find the largest element and put it at the end of data
        for (int i = 1; i < a.length; i++)
            if (a[max].compareTo(a[i]) < 0) max = i;
        swap(a, a.length - 1, max);     //largest e1 is now in its final position
        quickSort(a, 0, a.length - 2);
    }

    //Trộn 2 mảng đã có thứ tự tăng
    static Comparable[] temp;   //Được dùng bởi merge sort

    //Nhóm trị từ vị trí first..mid..last hình thành 2 mảng con tăng dần
    //Trộn chúng thành một nhóm tăng dần
    private static void merge(Comparable[] a, int first, int last) {
        int mid = (first + last) / 2;
        int i1 = 0, i2 = first, i3 = mid + 1;
        //chép 2 nhóm con vào mảng tạm theo thứ tự tăng dần
        while (i2 <= mid && i3 <= last)
            if (a[i2].compareTo(a[i3]) < 0) temp[i1++] = a[i2++];
            else temp[i1++] = a[i3++];
        while (i2 <= mid) temp[i1++] = a[i2++];
        while (i3 <= last) temp[i1++] = a[i3++];
        //copy nhóm trị trong temp vào lại mảng a
        for (i1 = 0, i2 = first; i2 <= last; a[i2++] = temp[i1++]) ;
    }

    //merge sort từ vị trí first cho tới last của mảng
    private static void mergeSort(Comparable[] a, int first, int last) {
        int mid = (first + last) / 2;
        if (first < mid) mergeSort(a, first, mid); //merge sort 2 nửa mảng
        if (mid + 1 < last) mergeSort(a, mid + 1, last);
        merge(a, first, last);  //trộn chúng lại
    }
    public static void mergeSort(Comparable[] a){
        if(a.length<2) return;
        temp = new Comparable[a.length];
        mergeSort(a,0,a.length-1);
    }
}
