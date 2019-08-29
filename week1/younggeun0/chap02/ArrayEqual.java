package chap02;

import java.util.Random;
import java.util.Scanner;

public class ArrayEqual {

	static boolean equals(int[] a, int[] b) {
		if (a.length != b.length)
			return false;
		
		for (int i=0; i<a.length; i++) 
			if(a[i] != b[i])
				return false;
		
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("put size of array a : ");
		int num = sc.nextInt();
		System.out.println(num);
		
		int[] a = new int[num];
		
		for(int i=0; i<num; i++) {
			System.out.print("a["+i+"] : ");
			a[i] = sc.nextInt();
		}
		
		System.out.print("put size of array b : ");
		int num2 = sc.nextInt();
		System.out.println(num2);
		
		int[] b = new int[num2];
		
		for(int i=0; i<num2; i++) {
			System.out.print("b["+i+"] : ");
			b[i] = sc.nextInt();
		}
		
		System.out.println("a array and b array "+
				(equals(a,b) ? "are the same." : "aren't the same"));
	}
}
