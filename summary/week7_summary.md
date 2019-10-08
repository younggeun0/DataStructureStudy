# Week 7

## 1. 정렬(Sorting)

* **정렬이란 핵심 항목(key)의 대소 관계에 따라 데이터 집합을 일정한 순서로 줄지어 늘어서도록 바꾸는 작업**
* 데이터를 정렬하면 검색을 더 쉽게 할 수 있음
* 키 값에 따라 **오름차순(Ascending Order), 내림차순(Descending Order)** 정렬
* **안정된(stable) 정렬 알고리즘**은 같은 값인 요소의 순서가 정렬 전후에도 유지됨
  * 안정되지 않은 알고리즘은 같은 값인 경우 요소의 순서가 정렬 후 보장되지 않음
* **내부 정렬(Internal Sorting** - 정렬할 모든 데이터를 하나의 배열에 저장할 수 있는 경우 사용하는 알고리즘
  * 이 교재에서 다루는 모든 알고리즘들은 내부 정렬
* **외부 정렬(External Sorting)** - 정렬할 데이터가 너무 많아서 하나의 배열에 저장할 수 없는 경우 사용하는 알고리즘
* 정렬 알고리즘의 핵심 요소는 **교환, 선택, 삽입**

## 2. 버블 정렬

* **이웃한 두 요소의 대소 관계를 비교하여 교환을 반복**
* 오름차순 정렬 시 왼쪽 요소 값이 오른쪽 요소 값보다 작아야함
  * 마지막 두 요소부터 비교, 작으면 교환하는 작업을 첫 요소까지 반복함(보글보글)
    * 이런 **일련의 과정(비교, 교환 작업)을 패스(Pass)**라고 함
  * 패스가 1회 수행될 때마다 정렬 요소가 줄어들기 때문에 비교연산은 1번씩 줄어든다
    * (n-1)+(n-2)+... +1 = n(n-1)/2

```java
public class BubbleSort {
// 버블 정렬(버전 1)
	
	// a[idx1]과 a[idx2]의 값을 바꿈
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	// 버블 정렬
	static void bubbleSort(int[] a, int n) {
		for (int i=0; i<n-1; i++) {
			//////////////////////// 패스
			for(int j=n-1; j>i; j--) {
				if(a[j-1] > a[j])
					swap(a, j-1, j);
			}
			/////////////////////////////
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("버블 정렬(버전1)");
		System.out.print("요솟수 : ");
		int n = sc.nextInt();
		int[] x = new int[n];
		
		for(int i=0; i<n; i++) {
			System.out.print("x["+i+"] = ");
			x[i] = sc.nextInt();
		}
		
		bubbleSort(x, n);
		
		System.out.println("오름차순으로 정렬됨");
		for(int i=0; i<n; i++) {
			System.out.println("x["+i+"] = "+x[i]);
		}
	}
}
```

* **알고리즘 개선(1)**
  * 패스에서 요소의 교환이 한 번도 이루어지지 않으면 정렬이 끝난 것

```java
// 버블 정렬
static void bubbleSort(int[] a, int n) {
	int exchgCnt = 0;
	for (int i=0; i<n-1; i++) {
		////////////////////////////// 패스
		for(int j=n-1; j>i; j--) {
			if(a[j-1] > a[j]) {
				swap(a, j-1, j);
				exchgCnt++;
			}
		}
		///////////////////////////////////
		if (exchgCnt == 0) {
			System.out.println("교환없음");
			break;
		}
	}
}
```

* **알고리즘 개선(2)**
  * 각각 패스에서 비교, 교환을 하다가 어떤 시점 이후에 교환이 수행되지 않는다면 그보다 앞쪽 요소는 이미 정렬을 마친 상태

```java
// 버블 정렬
static void bubbleSort(int[] a, int n) {
	int k = 0; // a[k]보다 앞쪽은 정렬된 상태, k는 정렬된 가장큰 수를 가리키는 인덱스
	
	while(k < n-1) {
		int last = n-1;
		for(int j=n-1; j>k; j--) {
			if(a[j-1] > a[j]) {
				swap(a, j-1, j);
				last = j; 
			}
		}
		k = last; // swap이 되면  last값이 n-1이 아니게 됨
	}
}
```

## 3. 단순 선택 정렬

## 4. 단순 삽입 정렬