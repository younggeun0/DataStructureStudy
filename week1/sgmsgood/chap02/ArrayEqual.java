package chap02;

import java.util.Scanner;

public class ArrayEqual {
	static boolean equals(int[] a, int[] b) {
		if (a.length != b.length) {
			return false;
		}

		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("諛곗뿴 a�쓽 �슂�넖�닔: ");
		int na = sc.nextInt();
		
		int[] a = new int[na];
		
		for(int i = 0; i < na; i++) {
			System.out.println("a[" + i + "]: ");
			a[i] = sc.nextInt();
		}
		
		System.out.print("諛곗뿴 b�쓽 �슂�넖�닔: ");
		int nb = sc.nextInt();
		
		int[] b = new int[nb];
		
		for(int i = 0; i < nb; i++) {
			System.out.println("b[" + i + "]: ");
			b[i] = sc.nextInt();
		}
		System.out.println("諛곗뿴 a�� b�뒗 " + (equals(a,b) ? "媛숈뒿�땲�떎." : "媛숈� �븡�뒿�땲�떎."));
		
		sc.close();
	}
}

