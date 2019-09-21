package chap01;

import java.util.Scanner;

public class JudgeSign {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�젙�닔瑜� �엯�젰�븯�꽭�슂: ");
		int n = sc.nextInt();
		
		if(n > 0) {
			System.out.println("�씠 �닔�뒗 �뼇�닔�엯�땲�떎.");
		}else if(n < 0) {
			System.out.println("�씠 �닔�뒗 �쓬�닔�엯�땲�떎.");
		}
		
		sc.close();
	}
}
