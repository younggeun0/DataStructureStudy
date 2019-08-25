package chap01;

import java.util.Scanner;

public class JudgeSign {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("num : ");
		int n = sc.nextInt();
		
		if (n > 0) {
			System.out.println("양수");
		} else if (n < 0) {
			System.out.println("음수");
		}
	}
}
