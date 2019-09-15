package chap06;

import java.util.Scanner;

public class QuickSort {
	
	//배열 요소 arr[i]와 arr[j]의 값을 바꿈
	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	static void quickSort(int[] arr, int left, int right) {
		int pl = left;
		int pr = right;
		int pivot = arr[(pl+pr)/2];
		
		do {
			while(arr[pl] < pivot) pl++;
			while(arr[pr] > pivot) pr--;
			if(pl <= pr) {
				swap(arr, pl++, pr--);
			}
		} while(pl <= pr);
		
		if (left < pr) quickSort(arr, left, pr);
		if (pl < right) quickSort(arr, pl, right);
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("셸 정렬(버전1)");
		System.out.print("요솟 수 : ");
		int n = sc.nextInt();
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			System.out.print("arr["+i+"] : ");
			arr[i] = sc.nextInt();
		}
		
		quickSort(arr, 0, n-1);
		
		System.out.println("오름차순으로 정렬하였습니다.");
		for(int i=0; i<n; i++) {
			System.out.println("arr["+i+"] = "+arr[i]);
		}
	}
}
