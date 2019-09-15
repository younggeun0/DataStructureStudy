package chap06;

import java.util.Scanner;

public class Partition {
	
	//배열 요소 arr[i]와 arr[j]의 값을 바꿈
	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	static void partition(int[] arr, int n) {
		int left = 0; // 왼쪽 커서
		int right = n-1; //오른쪽 커서
		int pivot = arr[n/2]; // 피벗(가운데 위치)
		
		do {
			while(arr[left] < pivot) left++;
			while(arr[right] > pivot) right--;
			
			if (left <= right) {
				swap(arr, left++, right--);
			}
		} while(left <= right);
		
		System.out.println("피벗의 값은 "+pivot+"입니다.");
		System.out.println("피벗 이하의 그룹");
		for(int i=0; i<=left; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		if (left > right+1) {
			System.out.println("피벗과 일치하는 그룹");
			for(int i=right+1; i<=left-1; i++) {
				System.out.print(arr[i]+" ");
			}
			System.out.println();
		}
		
		System.out.println("피벗 이상의 그룹");
		for(int i=right+1; i<n; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
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
		
		partition(arr, n);
	}
}
