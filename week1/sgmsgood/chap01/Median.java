package chap01;

import java.util.Scanner;

public class Median {
	
	public int med3(int a, int b, int c) {
		if(a >= b) {
			if(b >= c) {
				return b;
			}else if(a <= c) {
				return a;
			}else {
				return c;
			}
		}else if(a > c){
			return a;
		}else if(b > c){
			return c;
		}else {
			return b;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("a�쓽 媛�: ");
		int a = sc.nextInt();
		System.out.print("b�쓽 媛�: ");
		int b = sc.nextInt();
		System.out.print("c�쓽 媛�: ");
		int c = sc.nextInt();

		Median m = new Median();
		System.out.println("以묒븰媛믪� " + m.med3(a,b,c) + "�엯�땲�떎.");
		
		sc.close();
	}
}
