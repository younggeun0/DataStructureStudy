package chap01;

import java.util.Scanner;

public class MaxValue {

	private int a, b, c;

	public void getMaxValue() {
		int[] values = { a, b, c };
		int max = 0;
		int min = 999999999;
		for (int i = 0; i < values.length; i++) {
			if (max < values[i]) {
				max = values[i];
			}
			
			if(min > values[i]) {
				min = values[i];
			}
		}
		System.out.println("max: "+ max);
		System.out.println("min: "+ min);
	}

	public static void main(String[] args) {
		MaxValue mv = new MaxValue();

		Scanner sc = new Scanner(System.in);
		System.out.println("3媛쒖쓽 �닽�옄瑜� �엯�젰�빐二쇱꽭�슂.");
		mv.a = sc.nextInt();
		mv.b = sc.nextInt();
		mv.c = sc.nextInt();

		mv.getMaxValue();
		
		sc.close();
	}
}
