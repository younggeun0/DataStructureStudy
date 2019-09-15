package chap06;

import java.util.Scanner;

public class InsertionSort {

	static void insertionSort(int[] arr, int n) {
		for(int i=1; i<n; i++) {
			int j;
			int tmp = arr[i];
			for(j=i; j>0 && arr[j-1] > tmp; j--) {
				arr[j] = arr[j-1];
			}
			arr[j] = tmp;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("단순 삽입 정렬");
		System.out.print("요솟수 : ");
		int n = sc.nextInt();
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			System.out.print("arr["+i+"] : ");
			arr[i] = sc.nextInt();
		}
		
		insertionSort(arr, n);
		
		System.out.println("오름차순으로 정렬하였습니다.");
		for(int i=0; i<n; i++) {
			System.out.println("arr["+i+"] = "+arr[i]);
		}
	}
}
