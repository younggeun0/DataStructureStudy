package chap03;

import java.util.Scanner;

public class SeqSearchSen { // 배열 선형탐색 보초법
	// 일반 선형탐색에선 종료조건이 끝의 인덱스와 같은지(1), 키값이 존재하는지(2) 두가지를 체크했음  
	// 반복하면 두번의 계산 비용이 발생
	// 보초법은 맨 마지막에 찾는 값을 넣어 찾는 값이 마지막인덱스면 종료시켜 종료조건을 하나로 줄이는 방법
	
	static int seqSearchSen(int[] a, int n, int key) {
		int i = 0;
		a[n] = key;
		
		while(true) {
			if (a[i] == key)
				break;
			i++;
		}
		
		return i == n ? -1 : i; // 마지막에 넣은 값과 같으면 끝(없음)
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("요소 수 : ");
		int num = sc.nextInt();
		
		int[] x = new int[num+1]; // 보초를 넣기때문에 +1
		
		for(int i=0; i<num; i++) {
			System.out.print("x["+i+"] : ");
			x[i] = sc.nextInt();
		}
		
		System.out.print("검색할 값 : ");
		int key = sc.nextInt();
		
		int idx = seqSearchSen(x, num, key);
		
		if (idx == -1) {
			System.out.println("없음");
		} else {
			System.out.println(key+"는 x["+idx+"]에 있음");
		}
	}
}
