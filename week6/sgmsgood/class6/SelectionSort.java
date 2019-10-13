package class6;

public class SelectionSort {
	static void selectionSort(int[] a, int n) {
		for(int i = 0; i < n-1; i++) {
			int min = i;
			for(int j = i + 1; j < n; j++) {
				if(a[j] > a[min]) {
					min = j;
				}
				BubbleSort.swap(a, i, min);
			}
		}
	}
}
