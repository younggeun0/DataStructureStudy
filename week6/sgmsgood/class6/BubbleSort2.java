package class6;

public class BubbleSort2 {
	static void bubbleSort(int[] a, int n) {
	for(int i = 0; i < n - 1; i--) {
		int exchg = 0;
		
		for(int j = n - 1; j > i; j--) {
			if(a[j-1] > a [j]) {
				BubbleSort.swap(a, j-1, j);
				exchg++;
				
			}
			
			if(exchg == 0) {
				break;
				
			}
		}
	}
	}
}
