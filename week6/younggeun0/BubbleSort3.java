package chap06;

import java.util.Scanner;

public class BubbleSort3 {
// 버블 정렬(버전 3)
	
	// a[idx1]과 a[idx2]의 값을 바꿈
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	// 버블 정렬
	static void bubbleSort(int[] a, int n) {
		int k = 0; // a[k]보다 앞쪽은 정렬된 상태, k는 정렬된 가장큰 수를 가리키는 인덱스
		
		while(k < n-1) {
			int last = n-1;
			for(int j=n-1; j>k; j--) {
				if(a[j-1] > a[j]) {
					swap(a, j-1, j);
					last = j; 
				}
			}
			k = last; // swap이 되면  last값이 n-1이 아니게 됨
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("버블 정렬(버전1)");
		System.out.print("요솟수 : ");
		int n = sc.nextInt();
		int[] x = new int[n];
		
		for(int i=0; i<n; i++) {
			System.out.print("x["+i+"] = ");
			x[i] = sc.nextInt();
		}
		
		bubbleSort(x, n);
		
		System.out.println("오름차순으로 정렬됨");
		for(int i=0; i<n; i++) {
			System.out.println("x["+i+"] = "+x[i]);
		}
		
	}
	

}
