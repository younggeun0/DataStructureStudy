package chap03;

import java.util.Scanner;

public class BinSearch {

	static int binSearch(int[] arr, int n, int key) {
		int left = 0;
		int right = n -1;
		
		do {
			int center = (left+right) / 2;
			if (arr[center] == key) {
				return center;
			} else if (key > arr[center]) {
				left = center + 1;
			} else {
				right = center - 1;
			} 
		} while (left <= right);
		
		return -1;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("요솟수 : ");
		int num = sc.nextInt();
		int[] arr = new int[num];
		
		System.out.println("오름차순으로 입력!");
		
		System.out.print("arr[0] : ");
		arr[0] = sc.nextInt();
		
		for(int i=1; i<num; i++) {
			do {
				System.out.print("arr["+i+"] : ");
				arr[i] = sc.nextInt();
				
			} while(arr[i] < arr[i-1]);
		}
		
		System.out.print("검색할 값 : ");
		int key = sc.nextInt();
		
		int idx = binSearch(arr, num, key);
		
		if (idx == -1) {
			System.out.println("없음");
		} else {
			System.out.println(key+"는 arr["+idx+"]에 있음");
		}
		
	}
	
}
