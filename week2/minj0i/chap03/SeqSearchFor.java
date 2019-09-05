package chap03;

import java.util.Scanner;

public class SeqSearchFor {

	static int seqSearch(int[] a, int n, int key) {
		for (int i = 0; i < n; i++)
			if (a[i]==key)
				return i;
			return -1;
	}//seqSearch
	
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
				
		System.out.print("요솟수 : ");
		int num = stdIn.nextInt();
		int[] x = new int[num];
		
		for (int i=0; i < num; i++) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}//end for
		
		System.out.print("검색 할 값 : ");
		int ky = stdIn.nextInt();
		int idx = seqSearch(x, num, ky);
		
		if(idx == -1)
			System.out.println("그 값의 요소가 없습니다.");
		else
			System.out.println(ky + "은(는) x["+ idx + "]에 있습니다.");
	}//main
}//class

