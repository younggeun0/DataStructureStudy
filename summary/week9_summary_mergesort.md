# Week 9
## 6-7 병합 정렬 (merge sort)
* 배열을 앞부분과 뒷부분으로 나누어 각각 정렬한 다음 병합하는 작업을 반복하여 정렬을 수행하는 알고리즘
<br/> (문제를 작은 2개의 문제로 분리하고, 각각을 해결한 다음 결과를 모아서 원래의 문제를 해결하는 전략)

* 과정 설명 
    1. 리스트의 길이가 0 또는 1이면 이미 정렬된 것으로 판단
    2. 1번이 아닌 경우, 정렬되지 않은 리스트를 절반으로 잘라 비슷한 크기의 두 부분 리스트로 나눈다. 
    3. 각 부분 리스트를 재귀적으로 합병 정렬을 이용해 정렬한다.
    4. 두 부분 리스트를 다시 하나의 정렬된 리스트로 합병한다.

* 전체 시간 복잡도 O(n log n)

### 병합 정렬의 장단점
* 단점: 임시 배열이 필요함 (= 제자리 정렬[in-place sorting이 아님)
* 장점
    * 안정적인 정렬 방법
<br/>: 서로 떨어져 있는 요소를 교환하는 것이 아니므로, 데이터의 분포에 영향을 덜 받는다.
<br/>: 레코드를 연결리스트로 구성하면, 링크 인덱스만 변경되므로 데이터 이동은 무시할 수 있을 정도로 작아짐 (제자리 정렬로 구현할 수 있다.)
<br/>: 크기가 큰 레코드를 정렬할 경우에 연결 리스틀 사용한다면, 합병 정렬은 퀵을 포함한 다른 어떤 정렬 방법보다 효율적이다.

![merge array1](https://gmlwjd9405.github.io/images/algorithm-merge-sort/merge-sort-concepts.png)
-------------

![merge sort](https://gmlwjd9405.github.io/images/algorithm-merge-sort/merge-sort.png)

```java
public class MergeArray {
	// 정렬을 마친 배열 a, b를 병합하여 배열 c에 저장합니다.
	static void merge(int[] a, int na, int[] b, int nb, int[] c) {
		int pa = 0;
		int pb = 0;
		int pc = 0;
		
		while(pa < na && pb < nb) { //작은 값을 저장합니다.
			c[pc++] = (a[pa] <= b[pb]) ? a[pa++] : b[pb++];
		}
		
		while(pa < na) { //a에 남아있는 요소를 복사합니다.
			c[pc++] = a[pa++];
		}
		
		while(pb < nb) { //b에 남아있는 요소를 복사합니다.
			c[pc++] = b[pb++];
		}
	}
}
```

## 6-8 힙 정렬
* 선택 정렬을 응용한 알고리즘
* heap의 특성(부모의 값이 자식의 값보다 항상 크다)을 사용하여 정렬하는 알고리즘
* 부모와 자식 관계는 일정하지만, 형제 사이의 대소관계는 일정하지 않음
* 즉, '가장 큰 값이 루트(트리의 맨 윗 부분)에 위치'하는 특징을 이용하는 정렬 알고리즘
* 힙에서 가장 큰 값인 루트를 꺼내고, 남은 요소에서 다시 가장 큰 값을 구함
<br/> ->  힙으로 구성된 10개의 요소에서 가장 큰 값을 어애면 나머지 9개의 요소 중에서 가장 큰 값을 루트로 정해야 함. 
<br/> -> 따라서 나머지 9개 요소로 만든 트리도 힙의 형태를 유지할 수 있도록 재구성해야 함

* 힙 정렬의 시간 복잡도:
<br/> 힙 정렬에서 다시 힙으로 만드는 작업의 시간 복잡도는 O(n log n)임.

![heap](https://t1.daumcdn.net/cfile/tistory/242099415900C7CC34)

```java
public class HeapSort {
	//배열 요소 a[idx1]과 a[idx2]의 값을 바꿉니다.
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	//a[left] ~ a[right]를 힙으로 만듭니다.
	static void downHeap(int[] a, int left, int right) {
		int temp = a[left]; //루트
		int child; //큰 값을 가진 노드
		int parent; //부모
		
		for(parent = left; parent < (right+1)/ 2; parent = child) {
			int cl = parent * 2 + 1; //왼쪽 자식
			int cr = cl + 1; 		// 오른쪽 자식
			child = (cr < right && a[cr] > a[cl])? cr : cl; // 큰 값을 가진 노드를 자식에 대입
			if(temp >= a[child]) {
				break;
			}
			a[parent] = a[child];
		}
		a[parent] = temp;
	}
	
	//힙 정렬
	static void heapSort(int[] a, int n) {
		for(int i = (n - 1)/ 2; i >= 0; i--) { //a[i] ~ a[n-1]를 힙으로 만들기
			downHeap(a, i, n - 1);
		}
		
		for(int i = n - 1; i > 0; i--) {
			swap(a, 0, i); //가장 큰 요소와 아직 정렬되지 ㅇ낳은 부분의 마지막 요소를 교환
			downHeap(a, 0, i - 1); //a[0] ~ a[r-1]을 힙으로 만듭니다.
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("힙 정렬");
		System.out.println("요솟수: ");
		int nx = sc.nextInt();
		int[] x = new int[nx];
		
		for(int i = 0; i < nx; i++) {
			System.out.print("x[" + i + "]:");
			x[i] = sc.nextInt();
		}
		
		heapSort(x, nx);
		
		System.out.println("오름차순으로 정렬했습니다.");
		for(int i = 0; i < nx; i++) {
			System.out.println("x[" + i + "]= " + x[i]);
		}
		
		sc.close();
	}
}
```




## 6-9 도수정렬
* 요소의 대소 관계를 판단하지 않고 빠르게 정렬할 수 있는 알고리즘
* 단일 for 문 만을 사용하며, 재귀 호출, 이중 for문이 없어서 아주 효율적인 알고리즘임.
* 단, 도수분포표가 필요하므로 데이터의 '최솟값'과 '최댓값'을 미리 알고 있는 경우에만 사용 가능
* 각 단계에서 배열 요소를 건너뛰지 않고 순서대로 스캔하기 때문에 같은 값에 대해서 순서가 바뀌는 일이 없어서 안정적임 
    * 단,  배열 a를 스캔할 때 마지막 위치부터가 아닌 처음부터 스캔하면 안정적이지 않음!
* 4단계로 구성되어 있음
    * 도수분포표 작성
    * 누적도수분포표 작성
    * 목적 배열 만들기
    * 배열 복사

#### 1. 도수 분포표 만들기
* 각 항의 값에 맞게 데이터를 정렬
```java
for(int i = 0; i < n; i++){
    f[a[i]]++;
}
```
![first](https://t1.daumcdn.net/cfile/tistory/99DA59495C80DDC334)

#### 2. 누적도수분포표 만들기
* 총 몇 개의 데이터가 있는지 계산
```java
for(int i = 1; i <= max; i++){
    f[i] += f[i - 1]
}
```
![second](https://t1.daumcdn.net/cfile/tistory/99130F495C80DDC332)

#### 3. 목적배열 만들기
* 배열의 각 요솟값과 누적도수분포표 f를 대조하여 정렬을 마친 배열을 만드는 작업
```java
for(int i =n -1 ; i >= 0; i--){
    b[--f[a[i]]] = a[i];
}
```
![third](https://t1.daumcdn.net/cfile/tistory/99F74B4A5C80EC650C)

#### 4. 배열 복사하기
* 배열 b에 정렬되어 있는 값을 배열 a로 복사하는 과정
* '0 < 모든 요솟값 < max' 전제로 배열 a에 대해서 도수 정렬을 수행
* 아래의  FSort메서드는 정렬을 위한 작업용 배열 f와 b를 만듦
    * f = 도수분포와 누적도수를 넣는 배열
    * b = 정렬한 배열을 임시로 저장하는 배열

```java
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
}
```
