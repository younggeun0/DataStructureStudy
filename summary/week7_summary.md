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
* 세 가지 단순 정렬(버블, 선택, 삽입)의 시간 복잡도는 모두 O(n<sup>2</sup>), 효율이 좋지 않음


## 2. 버블 정렬(Bubble Sort)

* **이웃한 두 요소의 대소 관계를 비교하여 교환을 반복하는 알고리즘**
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
		///////////////////////// 패스
		for(int j=n-1; j>k; j--) {
			if(a[j-1] > a[j]) {
				swap(a, j-1, j);
				last = j; 
			}
		}
		////////////////////////////
		k = last; // swap이 되면  last값이 n-1이 아니게 됨
	}
}
```

## 3. 단순 선택 정렬(Straight Selection Sort)

* **가장 작은 요소부터 선택하여 알맞은 위치로 옮겨서 순서대로 정렬하는 알고리즘**
  * 아직 정렬하지 않은 부분에서 가장 작은 키의 값(a[min])을 선택
  * a[min]과 아직 정렬하지 않은 부분의 첫 번째 요소를 교환
* 요솟값을 비교하는 횟수는 (n<sup>2</sup>-n)/2
* 이 정렬 알고리즘은 서로 떨어져 있는 요소를 교환하기 때문에 안정적이지 않음
  * **중복된 요소 값의 순서가 뒤바뀜**

```java
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
```

## 4. 단순 삽입 정렬(Straight Insertion Sort)

* **선택한 요소를 그보다 더 앞쪽의 알맞은 위치에 '삽입'하는 작업을 반복하여 정렬하는 알고리즘**
  * 트럼프 카드를 한 줄로 늘어놓을 때 사용하는 방법과 비슷
  * 단순 선택 정렬과 비슷하게 보이지만 단순 선택 정렬은 가장 작은 요소를 선택해 알맞은 위치로 옮긴다는 점이 다름
* **아직 정렬되지 않은 부분의 첫 번째 요소를 정렬된 부분의 알맞은 위치에 삽입**
  * 알맞은 위치에 삽입이란 왼쪽에 이웃한 요소가 선택한 요소보다 크면 그 값을 대입하고 이동하면서 이 작업을 반복, 그러다가 선택한 값 이하의 요소를 만나서 그보다 앞쪽은 검사할 필요가 없으므로 해당 위치에 삽입할 값을 대입
  * tmp에 a[i]를 대입하고 반복 제어용 변수 j에 i-1을 대입한 다음 아래 두 조건 중 하나가 만족할 때까지 j를 1씩 감소하면서 대입하는 작업을 반복

```bash
1.정렬된 열의 왼쪽 끝에 도달 
2.tmp보다 작거나 같은 key를 갖는 항목 a[j]를 발견

# 드모르간 법칙을 적용하면 위 내용은 아래 두 조건이 성립할 때까지 반복한다와 동일

1. j가 0보다 큼
2. a[j-1]값이 tmp보다 큼
```

```java
static void insertionSort(int[] arr, int n) {
	for(int i=1; i<n; i++) {
		int j;
		int tmp = arr[i]; // 삽입할 선택한 요소
		for(j=i; j>0 && arr[j-1] > tmp; j--) {
			arr[j] = arr[j-1]; // 왼쪽 값을 오른쪽으로 옮김
		}
		// for문이 끝나는 조건(왼쪽 끝 또는 왼쪽 요소값이 선택한 요소값보다 큰 경우)
		arr[j] = tmp;
	}
}
```

* 단순 삽입 정렬은 **셔틀 정렬(Shuttle Sort)**라고도 함
* 단순 삽입 정렬 알고리즘은 떨어져 있는 요소들이 서로 뒤바뀌지 않아 안정적임
* 요소의 비교 횟수와 교환 횟수는 n<sup>2</sup>/2회
