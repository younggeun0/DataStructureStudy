package chap06;

import java.util.Scanner;

public class Fsort {
	static void fSort(int[] a, int n, int max) {
		int[] f = new int[max + 1];
		int[] b= new int[n];
		
		for(int i = 0 ; i < n; i++) {
			f[a[i]]++;
		}
		
		for(int i = 1; i <= max; i++) {
			f[i] += f[i - 1];
		}
		
		for(int i = n - 1; i >= 0; i--) {
			b[--f[a[i]]] = a[i];
		}
		
		for(int i = 0; i < n; i++) {
			a[i] = b[i];
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("도수 정렬");
		System.out.println("요솟수: " );
		int nx = sc.nextInt();
		int[] x = new int[nx];
		
		for(int i = 0; i < nx; i++) {
			do {
				System.out.println("x[" + i + "]:");
				x[i] = sc.nextInt();
			}while(x[i] < 0);
		}
		
		int max = x[0];
		for(int i = 1; i < nx; i++) {
			if (x[i] > max) {
				max = x[i];
			}
		}
		
		fSort(x, nx, max);
		
		System.out.println("오름차순으로 정렬했습니다.");
		for(int i = 0; i < nx; i++) {
			System.out.println("x[" + i + "]= " + x[i]);
		}
		
		sc.close();
	}
}
