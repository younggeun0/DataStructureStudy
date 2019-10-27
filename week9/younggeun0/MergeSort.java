package week9;

import java.util.Scanner;

class MergeSort {
    static int[] buff;

    static void __mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int i;
            int center = (left+right) / 2;

            int p = 0;
            int j = 0;
            int k = left;

            __mergeSort(a, left, center); // 배열 앞부분을 병합정렬
            __mergeSort(a, center+1, right); // 배열 뒷부분을 병합정렬

            // buff랑 병합하기 전 a랑 두 배열을 이용해서 a를 새로 정렬
            for (i = left; i<= center; i++)
                buff[p++] = a[i]; // buff에 a의 앞부분을 담고

            // for문 끝나면 p 값은 center-left+1이 됨

            // 배열의 뒷 부분(a[center+1] ~ a[right])과 buff로 복사한 배열의 앞부분 
            // p개를 병합한 결과를 배열 a에 저장
            while (i <= right && j < p) {
                a[k++] = (buff[j] <= a[i]) ? buff[j++] : a[i++];
            }

            // 배열 buff에 남아 있는 요소를 배열 a에 복사
            while (j < p) {
                a[k++] = buff[j++];
            }
        }
    }

    static void mergeSort(int[] a, int n) {
        buff = new int[n];

        __mergeSort(a, 0, n-1);

        buff = null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("merge sort");
        System.out.print("n : ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            System.out.print("arr["+i+"] : ");
            arr[i] = sc.nextInt();
        }

        mergeSort(arr, n);

        System.out.println("sorted by ascending order");
        for(int i=0; i<n; i++) {
            System.out.println("arr["+i+"]="+arr[i]);
        }
    }
}