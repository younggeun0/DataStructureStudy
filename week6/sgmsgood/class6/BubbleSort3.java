package class6;

public class BubbleSort3 {
	static void bubbleSort(int[] a, int n) {
		int k = 0; 
		while(k < n-1) {
			int last = n - 1;
			for(int j = n - 1; j > k; j--) {
				if(a[j-1]> a[j]) {
					BubbleSort.swap(a, j - 1, j);
					last = j;
				}
				k = last;
			}
		}
	}
}
