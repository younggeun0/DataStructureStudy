package chap06;

public class SelectionSort {
	// a[idx1]과 a[idx2]의 값을 바꿈
	static void swap(int[] arr, int idx1, int idx2) {
		int t = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = t;
	}
	
	static void selectionSort(int[] arr, int n) {
		for(int i=0; i<n-1; i++) {
			int min = i;
			for(int j=i+1; j<n; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			swap(arr, i, min);
		}
	}
	
	public static void main(String[] args) {
		
	}
}
