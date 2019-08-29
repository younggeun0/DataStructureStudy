package chap01;

import java.util.Scanner;

public class SomeForPos {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n;
		
		System.out.println("1~n sum");
		
		do {
			System.out.print("n : ");
			n = sc.nextInt();
			if (n < 0) {
				System.out.println("enter pros");
			}
		} while(n <= 0);
		
		int sum = 0;
		
		for(int i=1; i<n; i++) {
			sum += i;
		}
		
		System.out.println("1~n sum : "+sum);
		
	}

}
