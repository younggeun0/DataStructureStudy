package chap05;

import java.util.Scanner;

public class EuclidGCD { // GCD(Greatest Common Divisor)
	// 유클리드 호제법으로 최대공약수 구하기
	
	static int gcd(int x, int y) {
		//정수 x,y의 최대공약수를 구하면 반환
		if (y == 0)
			return x;
		else
			return gcd(y, x%y);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("두 정수의 최대공약수를 구함");
		
		System.out.print("정수를 입력하세요 : "); int x = sc.nextInt();
		System.out.print("정수를 입력하세요 : "); int y = sc.nextInt();

		sc.close();
		
		System.out.println("최대 공약수는 "+gcd(x,y)+"입니다.");
	}
}
