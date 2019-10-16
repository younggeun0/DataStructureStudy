package week8;

import java.util.Scanner;

class ShellSort {
    static void shellSort(int[] a, int n) {
        for (int h=n/2; h > 0; h/=2) {
            for (int i=h; i<n; i++) {
                int j;
                int tmp = a[i];
                for(j = i-h; j>=0 && a[j] > tmp; j-= h) {
                    a[j+h] = a[j];
                }
                a[j+h] = tmp;
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("shell sort");
        System.out.print("element num : ");
        int n = sc.nextInt();
        int[] a = new int[n];

        for(int i=0; i<n; i++) {
            System.out.print("a["+i+"] : ");
            a[i] = sc.nextInt();
        }

        shellSort(a, n);

        System.out.println("ascending sort");
        for(int i=0; i<n; i++) {
            System.out.println("a["+i+"] = "+a[i]);
        }
    }
}