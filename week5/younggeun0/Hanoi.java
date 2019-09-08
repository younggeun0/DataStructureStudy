package chap05;

import java.util.Scanner;

public class Hanoi {
	// n개의 원반을 x번 기둥에서 y번 기둥으로 옮김
	static void move(int n, int x, int y) {
		if (n > 1) {
			move(n-1, x, 6-x-y); 
		}
		
		System.out.println("원반["+n+"]을 "+x+"기둥에서 "+y+"기둥으로 옮김");
		
		if (n > 1) {
			move(n-1, 6-x-y, y); // 중간 기둥의 번호는 6-x-y로 구할 수 있음
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("하노이의 탑");
		System.out.print("원반 개수 : ");
		
		int n = sc.nextInt();
		
		move(n, 1, 3); // 1번 기둥의 n개의 원반을 3번 기둥으로 옮김
	}
}
