package chap01;

import java.util.Scanner;

public class SumForPos {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		
		System.out.println("1遺��꽣 n源뚯��쓽 �빀�쓣 援ы빀�땲�떎.");
		
		do{
			System.out.print("n�쓽 媛�: ");
			n = sc.nextInt();
		}while(n <= 0);
		
		int sum = 0;
		for(int i = 1; i <= n; i++) {
			sum += i;
		}
		System.out.println("1遺��꽣 " + n + "源뚯��쓽 �빀�� " + sum + "�엯�땲�떎.");
		sc.close();
	}
}
