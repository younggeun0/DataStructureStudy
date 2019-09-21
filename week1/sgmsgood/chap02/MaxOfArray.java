package chap02;

import java.util.Scanner;

public class MaxOfArray {
	static int maxOf(int[] a) {
		int max = a[0];
		for(int i = 1; i < a.length; i++) {
			if(a[i] > max) {
				max = a[i];
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("�궎�쓽 理쒕뙎媛믪쓣 援ы빀�땲�떎.");
		System.out.print("�궗�엺 �닔: ");
		int num = sc.nextInt();
		
		int[] height = new int[num];
		
		for(int i = 0; i < num; i++) {
			System.out.print("height[" + i + "]: ");
			height[i] = sc.nextInt();
		}
		
		System.out.println("理쒕뙎媛믪� " + maxOf(height) + "�엯�땲�떎.");
		
		sc.close();
	}
}
