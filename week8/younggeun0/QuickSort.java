package week8;

import java.util.Scanner;

class QuickSort {

    static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1]; 
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    static void quickSort(int[] arr, int left, int right) {
        int pLeft = left;
        int pRight = right;
        int pivot = arr[(pLeft+pRight)/2];

        System.out.printf("arr[%d]~arr[%d] : {",left,right);
        for (int i=left; i<right; i++) {
            System.out.printf("%d, ",arr[i]);
        }
        System.out.printf("%d }\n", arr[right]);

        do {
            while(arr[pLeft] < pivot) pLeft++;
            while(arr[pRight] > pivot) pRight--;

            if (pLeft <= pRight)
                swap(arr, pLeft++, pRight--);

        } while(pLeft <= pRight);

        if (left < pRight) quickSort(arr, left, pRight);
        if (pLeft < right) quickSort(arr, pLeft, right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("n : ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            System.out.print("arr["+i+"] : " );
            arr[i] = sc.nextInt();
        }

        quickSort(arr, 0, n-1);

        System.out.println("sort by ascending order");
        for (int i=0; i<n; i++) {
            System.out.println("arr["+i+"] = "+arr[i]);
        }
    }
}