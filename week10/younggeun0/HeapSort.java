package week10;

import java.util.Scanner;

class HeapSort {

    // 힙의 요소를 배열에 저장하면 다음과 같은 관계가 성립
    // 부모는 a[(i-1) / 2]
    // 왼쪽 자식은 a[i*2+1]
    // 오른쪽 자식은 a[i*2+2] 


    // a[left] ~ a[right]를 힙으로 만듦
    static void downHeap(int[] arr, int left, int right) {
        int temp = arr[left];    // 루트
        int child;  // 큰 값을 가진 노드
        int parent; // 부모

        for (parent = left; parent < (right+1) / 2; parent = child) {
            int cl = parent * 2 + 1;        // 왼쪽 자식
            int cr = cl + 1;                // 오른쪽 자식
            child = (cr <= right && arr[cr] > arr[cl]) ? cr : cl; // 큰 값을 가진 노드를 자식에 대입
            if (temp >= arr[child])
                break;
            arr[parent] = arr[child];
        }
        arr[parent] = temp;
    }

    // 힙 정렬
    static void heapSort(int[] arr, int n) {
        for (int i=(n-1)/2; i>=0; i--) {  // a[i] ~ a[n-1]를 힙으로 만들기
            downHeap(arr, i, n-1);
        }

        for (int i=n-1; i>0; i--) {
            swap(arr, 0, i); // 가장 큰 요소와 아직 정렬되지 않은 부분의 마지막 요소를 교환
            downHeap(arr, 0, i-1); // a[0]~a[i-1]를 힙으로 만들기
        }
    }

    static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1]; 
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("heap sort");
        System.out.print("n : ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            System.out.print("arr["+i+"] : ");
            arr[i] = sc.nextInt();
        }

        heapSort(arr, n);

        System.out.println("sorted by ascending order");
        for(int i=0; i<n; i++) {
            System.out.println("arr["+i+"]="+arr[i]);
        }
    }
}