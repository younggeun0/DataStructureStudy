package chap02;

import java.util.Arrays;
import java.util.Random;

public class ReverseArray {

	static void swap(int[] a, int idx, int idx2) {
		int tmp = a[idx];
		a[idx] = a[idx2];
		a[idx2] = tmp;
	}
	
	static void reverse(int[] a) {
		for(int i=0; i<a.length/2; i++) {
			swap(a, i, a.length-i-1);
		}
	}
	
	public static void main(String[] args) {
		Random r = new Random();
		int num = r.nextInt(10);
		System.out.println("num = "+num);
		
		int[] x = new int[num];
		
		for(int i=0; i<num; i++) {
			x[i] = r.nextInt(5);
		}
		System.out.println("origin Array : "+Arrays.toString(x));
		
		reverse(x);
		System.out.println("reversed Array : "+Arrays.toString(x));
	}
}
