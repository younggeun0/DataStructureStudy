package chap06;

import java.util.Scanner;

public class ShellSort2 {

	static void shellSort(int[] arr, int n) {
		int h;
		for(h=1; h<n/9; h=h*3+1); // for문은 h의 초기값을 구함
		
		for(; h>0; h/=3) {
			for(int i=h; i<n; i++) {
				int j;
				int tmp = arr[i];
				for(j=i-h; j>=0 && arr[j]>tmp; j-=h) {
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
