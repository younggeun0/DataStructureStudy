package chap02;

import java.util.Scanner;

public class DayOfYear {
	static int[][] mdays = { { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
			{ 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };

	static int isLeap(int year) {
		return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? 1 : 0;
	}

	static int dayOfYear(int y, int m, int d) {
		int days = d;

		for (int i = 1; i < m; i++) {
			days += mdays[isLeap(y)][i - 1];
		}
		
		return days;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int retry;

		System.out.println("洹� �빐 寃쎄낵 �씪�닔瑜� 援ы빀�땲�떎.");

		do {
			System.out.println("�뀈: ");
			int year = sc.nextInt();
			System.out.println("�썡: ");
			int month = sc.nextInt();
			System.out.println("�씪: ");
			int day = sc.nextInt();

			System.out.printf("洹� �빐 %d�씪吏몄엯�땲�떎.\n", dayOfYear(year, month, day));

			System.out.print("�븳 踰� �뜑 �븷源뚯슂? (1. �삁 / 2. �븘�땲�삤):");
			retry = sc.nextInt();
		} while (retry == 1);
		
		sc.close();
	}
}
