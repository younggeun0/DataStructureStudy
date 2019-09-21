package chap01;

import java.util.Scanner;

public class Max3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("�꽭 �젙�닔�쓽 理쒕뙎媛믪쓣 援ы빀�땲�떎.");
		System.out.print("a�쓽 媛�: "); int a = sc.nextInt();
		System.out.print("b�쓽 媛�: "); int b = sc.nextInt();
		System.out.print("c�쓽 媛�: "); int c = sc.nextInt();
		
		int max = a; 
		if(b > max) {
			max = b;
		}
		if(c > max) {
			max = c;
		}
		
		System.out.println("理쒕뙎媛믪� " + max + "�엯�땲�떎.");
		sc.close();
	}
}
