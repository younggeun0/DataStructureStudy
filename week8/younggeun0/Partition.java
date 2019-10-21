package week8;

import java.util.Scanner;

class Partition {

    static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    static void partition(int[] arr, int n) {
        int pLeft = 0;
        int pRight = n-1;
        int pivot = arr[n / 2];

        do {
            while(arr[pLeft] < pivot) pLeft++;
            while(arr[pRight] > pivot) pRight--;

            if (pLeft <= pRight) {
                swap(arr, pLeft++, pRight--);
            }
        } while(pLeft <= pRight);

        System.out.println("pivot value is "+pivot);
        System.out.println("pivot left group : ");

        for(int i=0; i<=pLeft-1; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        
        if (pLeft > pRight+1) {
            System.out.println("group including pivot : ");
            for(int i=pRight+1; i<pLeft-1; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
        
        System.out.println("pivot right group : ");

        for(int i=pRight+1; i<n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
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

        partition(arr, n);
    }
}