package chap03;

import java.util.Scanner;

public class SeqSearch {

	static int seqSearch(int[] a, int n, int key) {
		/*int i =0; // 배열 선형탐색 while사용했을 경우
		
		while(true) {
			if (i == n) {
				return -1;
			}
			if (a[i] == key) {
				return i;
			}
			i++;
		}*/
		for(int i=0; i<n; i++) { // for문을 이용한 배열 선형탐색
			if (a[i] == key) 
				return i;
		}
		return -1;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("요솟 수 : ");
		int num = sc.nextInt();
		int[] x = new int[num];
		
		for(int i=0; i<num; i++) {
			System.out.print("x["+i+"] : ");
			x[i] = sc.nextInt();
		}
		
		System.out.print("검색할 값 : ");
		int key = sc.nextInt();
		int idx = seqSearch(x, num, key);
		
		if(idx == -1) {
			System.out.println("검색값이 없음");
		} else {
			System.out.println(key+"는 x["+idx+"]에 있음");
		}
		
		
		
	}
}
