package chap03;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearchTester {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("요소 수 :");
		int num = sc.nextInt();
		int[] arr = new int[num];
		
		System.out.println("오름차순으로 입력하세요..");
		
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
		
		int idx = Arrays.binarySearch(arr, key); // 검색 실패시 -1 또는 -(삽입 포인트)-1 값을 반환, 없으면 걍 음수를 반환
		
		if (idx  < 0) { 
			System.out.println("그 값의 요소가 없습니다.");
		} else {
			System.out.println(key+"는 arr["+idx+"]에 있음");
		}
		
	}

}
