package chap01;

import java.util.Scanner;

public class Digits {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		int no;
		
		System.out.println("2자리의 정수를 입력하세요.");
		
		do {
			System.out.println("입력");
			no = stdIn.nextInt();
			if ( no <= 99 && no >= 10) 
				break;
				System.out.println("두자리의 정수를 입력할때까지 반복됩니다.");
		}while (no < 10 || no > 99);
		
		System.out.println("변수 no의 값은 " + no + "가 되었습니다.");
		
	}
	
}
