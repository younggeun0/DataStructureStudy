package chap06;

import java.util.Scanner;

public class MergeSort {
	static int[] buff; //�۾��� �迭
	
	//a[left] ~ a[right]�� ��������� ���� ����
	static void __mergeSort(int[] a, int left, int right) {
		if(left < right) {
			int i;
			int center = (left + right) / 2;
			int p = 0;
			int j = 0; 
			int k = left;
			
			__mergeSort(a, left, center); //�迭�� �� �κ��� ���� �����մϴ�.
			__mergeSort(a, center+1, right); //�迭�� �޺κ��� ���� �����մϴ�.
			
			for(i = left; i <= center; i++) {
				buff[p++] = a[i];
			}
			
			while(i <= right && j < p) {
				a[k++] = (buff[j] <= a[i]) ? buff[j++] : a[i++];
			}
			
			while(j < p) {
				a[k++] = buff[j++];
			}
		}
		
	}
	static void mergeSort(int[] a, int n) {
		buff = new int[n]; //�۾��� �迭�� �����մϴ�.
		__mergeSort(a, 0, n-1); //�迭 ��ü�� ���� �����մϴ�.
		buff = null; // �۾��� �迭�� �����մϴ�.
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("���� ����");
		System.out.println("��ڼ�: ");
		int nx = sc.nextInt();
		int[] x = new int[nx];
		
		for(int i = 0; i < nx; i++) {
			System.out.print("x[" + i + "]:");
			x[i] = sc.nextInt();
		}
		
		mergeSort(x, nx);
		
		sc.close();
	}
}