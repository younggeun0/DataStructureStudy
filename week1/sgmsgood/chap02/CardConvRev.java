package chap02;

import java.util.Scanner;

public class CardConvRev {
	static int cardConvR(int x, int r, char[] d) {
		int digits = 0;
		String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		do {
			d[digits++] = dchar.charAt(x % r);
			x /= r;
		}while(x != 0);
		
		return digits;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int no; 
		int cd;
		int dno; 
		int retry;
		char[] cno = new char[32];
		
		System.out.println("10吏꾩닔瑜� 湲곗닔 蹂��솚�빀�땲�떎.");
		do{
			do {
				System.out.print("蹂��솚�븯�뒗 �쓬�씠 �븘�땶 �젙�닔: ");
				no = sc.nextInt();
			}while(no < 0);
			
			do {
				System.out.print("�뼱�뼡 吏꾩닔濡� 蹂��솚�븷源뚯슂? (2 ~ 36) : ");
				cd = sc.nextInt();
			}while(cd < 2 || cd > 36);
			
			dno = cardConvR(no, cd, cno);
			
			System.out.print(cd + "吏꾩닔濡쒕뒗 ");
			for(int i = dno -1 ; i >= 0; i--) {
				System.out.println(cno[i]);
			}
			System.out.println("�엯�땲�떎.");
			
			System.out.println("�븳 踰� �뜑 �븷源뚯슂? (1. �삁/ 2. �븘�땲�삤): ");
			retry = sc.nextInt();
		}while(retry == 1);
		
		sc.close();
	}
}
