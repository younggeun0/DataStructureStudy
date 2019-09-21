package chap01;

import java.util.Scanner;

public class SumWhile {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1遺��꽣 n源뚯��쓽 �빀�쓣 援ы빀�땲�떎.");
		System.out.println("n�쓽 媛�: ");
		int n = sc.nextInt();
		
		int sum = 0;
		int i = 1;
		
		while(i < n) {
			sum += i;
			i++;
		}
		System.out.println("1遺��꽣 " + n + "源뚯��쓽 �빀�� " + sum + "�엯�땲�떎.");
		
		sc.close();
	}
}
