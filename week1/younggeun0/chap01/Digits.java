package chap01;

import java.util.Scanner;

public class Digits {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int no;
		
		System.out.println("2자리 정수로 제한");
		
		do {
			System.out.print("enter num : ");
			no = sc.nextInt();
			if (no < 10 || no > 99) 
				System.out.println("2자리 숫자만");
//		} while (no < 10 || no > 99);
		} while (!(no >= 10 && no <= 99));
		
		System.out.println("no is "+no);
	}
}
