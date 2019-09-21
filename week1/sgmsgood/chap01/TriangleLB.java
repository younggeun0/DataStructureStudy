package chap01;

import java.util.Scanner;

public class TriangleLB {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		
		System.out.println("�쇊履� �븘�옒媛� 吏곴컖�씤 �씠�벑蹂� �궪媛곹삎�쓣 異쒕젰�빀�땲�떎.");
		do {
			System.out.println("紐� �떒 �궪媛곹삎�엯�땲源�? ");
			n = sc.nextInt();
		}while(n <= 0);
		
		for(int i = 1; i <= n; i++) {
			
			for(int j = 1; j < i+1; j++) {
				System.out.print("*");
			}			
			System.out.println();
		}
	}
}
