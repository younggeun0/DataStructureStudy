package class3_jisu;

import java.util.Scanner;

public class SeqSearch {
	static int seqSearch(int[] a, int n, int key) {
		int i= 0;
		while(true) {
			if(i == n) {
				return -1;
			}
			if(a[i] == key) {
				return i;
			}
			i++;
		}
		
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("�슂�넖�닔: ");
		int num = sc.nextInt();
		int[] x = new int[num];
		
		for(int i = 0; i < num; i++) {
			System.out.println("x[" + i + "]: ");
			x[i] = sc.nextInt();
		}
		
		System.out.print("寃��깋�븷 媛�: ");
		int ky = sc.nextInt();
		
		int idx = seqSearch(x, num, ky);
		
		if(idx == 1) {
			System.out.println("洹� 媛믪쓽 �슂�냼媛� �뾾�뒿�땲�떎.");
		}else{
			System.out.println(ky+"��(�뒗) x["+idx+"]�뿉 �엳�뒿�땲�떎.");
		}
		
		sc.close();
	}
}
