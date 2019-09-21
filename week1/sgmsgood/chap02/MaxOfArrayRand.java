package chap02;

import java.util.Random;
import java.util.Scanner;

public class MaxOfArrayRand {
	static int maxOf(int[] a) {
		int max = a[0];
		for(int i = 1; i < a.length; i++) {
			if(a[i] < max) {
				max = a[i];
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		Random rand = new Random();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("�궎�쓽 理쒕뙎媛믪쓣 援ы빀�땲�떎.");
		System.out.print("�궗�엺 �닔: ");
		int num = sc.nextInt();
		
		int[] height = new int[num];
		
		System.out.println("�궎 媛믪� �븘�옒�� 媛숈뒿�땲�떎.");
		for(int i = 0; i < num ; i++) {
			height[i] = 100 + rand.nextInt(90);
			System.out.println("height[" + i + "]");
		}
		
		System.out.println("理쒕뙎媛믪� " + maxOf(height) + "�엯�땲�떎.");
		sc.close();
	}
}
