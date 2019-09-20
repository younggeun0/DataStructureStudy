package class1;

import java.util.Scanner;

public class SumFor {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1부터 n까지의 합을 구합니다.");
		System.out.print("n의 값: ");
		int n = sc.nextInt();
		
		int sum = 0;
		for(int i = 1; i <= n; i++) {
			sum += 1;
		}
		System.out.println("1부터 " + n + "까지의 합은 "+ sum + "입니다.");
		
		sc.close();
	}
}
