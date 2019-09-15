package chap06;

import java.util.Scanner;

public class ShellSort { // shellsort 복습..

	static void shellSort(int[] arr, int n) {
		for(int h=n/2; h>0; h/=2) {
			for(int i=h; i<n; i++) {
				int j;
				int tmp = arr[i];
				for(j=i-h; j>=0 && arr[j]>tmp; j-= h) {
					arr[j+h] = arr[j];
				}
				arr[j+h] = tmp;
			}
		}
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
		
		shellSort(arr, n);
		
		System.out.println("오름차순으로 정렬하였습니다.");
		for(int i=0; i<n; i++) {
			System.out.println("arr["+i+"] = "+arr[i]);
		}
				
	}
}
