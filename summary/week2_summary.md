# Week 2
## 1. 검색 알고리즘

### **검색과 키**
- 특정 항목에 주목한다는 점은 '검색하기'의 공통점
- 그 주목하는 항목을 키 (key)라고 생각

### **배열에서 검색하기**
1. 선형 검색 : **무작위로** 늘어놓은 데이터 모임에서 검색을 수행합니다.
2. 이진 검색 : **일정한 규칙**으로 늘어놓은 데이터 모임에서 **아주 빠른 검색**을 수행합니다.
3. 해시법 : 추가, 삭제가 자주 일어나는 데이터 모임에서 아주 빠른 검색을 수행합니다.
- 체인법 : 같은 해시 값의 데이터를 **선형 리스트**로 연결하는 방법
- 오픈 주소법 : 데이터를 위한 해시 값이 충돌할 때 재해시하는 방법

*용도나 목적, 실행 속도, 자료구조 등을 고려하여 알고리즘을 선택!* 

## 2. 선형 검색
### **선형 검색**
- 원하는 키 값을 갖는 요소를 만날 때까지 맨 앞부터 순서대로 요소를 검색
- 선형 검색이 끝나는 조건 2가지
> 1. 검색할 값을 발견하지 못하고 배열의 끝을 지나간 경우
> 2. 검색할 값과 같은 요소를 발견한 경우
- 배열의 요솟수가 n개일 때 조건 1,2를 판단하는 횟수는 **평균 n/2회**
- 선형검색은 배열에서 순서대로 검색하는 유일한 방법

* 선형 검색을 구현한 프로그램
```JAVA
public class SeqSearch {
	static int seqSearch(int[] a, int n, int key) {
		int i = 0;
		
		while(true) {
			if(i == n)
				return -1;
			if(a[i] == key)
				return i;
			i++;
		}
	}//seqSearch
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
				
		System.out.print("요솟수 : ");
		int num = stdIn.nextInt();
		int[] x = new int[num];
		
		for (int i=0; i < num; i++) {
			System.out.print("x[" + i + "] : ");
			x[i] = stdIn.nextInt();
		}//end for
		
		System.out.print("검색 할 값 : ");
		int ky = stdIn.nextInt();
		int idx = seqSearch(x, num, ky);
		
		if(idx == -1) //값이 key인 요소가 존재하지 않으면 -1을 반환
			System.out.println("그 값의 요소가 없습니다.");
		else
			System.out.println(ky + "은(는) x["+ idx + "]에 있습니다.");
	}//main
}//class
```
* for문을 활용한 위 seqSearch method
```JAVA
static int seqSearch(int[] a, int n, int key) {
		for (int i = 0; i < n; i++)
			if (a[i]==key)
				return i;
			return -1;
}//seqSearch
```

### **보초법**
- 종료 조건을 검사하는 비용을 반(50%)으로 줄이는 방법 : 보초법
- 검색하고자 하는 키 값을 맨 끝 요소에 저장함으로써 종료조건을 하나로 줄임

* 보초법을 이용한 선형 검색
> if(i == n) 필요하지 않기 때문에 삭제<br/>
> if(a[i] == key) 만 남음
```JAVA
public class SeqSearchSen {
// 선형 검색 (보초법)
	static int seqSearchSen(int[] a, int n, int key) {
		int i = 0;
		
		a[n] = key;
		
		while(true) {
			if (a[i] == key)
				break;
			i++;
		}
		return i == n ? -1 : i;
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("요솟수 : ");
		int num = stdIn.nextInt();
		int[] x = new int[num + 1];
		
		for(int i = 0; i < num; i++) {
			System.out.print("x["+i+"] : ");
			x[i] = stdIn.nextInt();
		}
		
		System.out.print("검색할 값 : ");
		int ky = stdIn.nextInt();
		
		int idx = seqSearchSen(x, num, ky);
		
		if(idx == -1)
			System.out.println("그 값의 요소는 없습니다.");
		else
			System.out.println(ky+"은(는) + x["+idx+"]에 있습니다.");
	}//main

}//class
```

## 3. 이진 검색
전제 조건 : 데이터가 키 값으로 이미 정렬(sort)되어 있다는 것

### **이진 검색**
- 검색 범위의 맨 앞 인덱스를 pl
- 맨 끝 인덱스를 pr
- 중앙 인덱스를 pc 라고 지정

1. a[pc] < key 일 때
- 검색 범위를 a[pc+1]~a[pr]로 좁힘
- pl의 값을 pc+1로 업데이트
2. a[pc] > key 일 때
- 검색 범위를 a[pl]~a[pc-1]로 좁힘
- pr의 값을 pc-1로 업데이트

* **이진 검색 알고리즘의 종료 조건**
> 1. a[pc]와 key가 일치하는 경우
> 2. 검색 범위가 더 이상 없는 경우
* 이진 검색에 필요한 평균 값은 log n
* 검색에 실패할 경우는 log⌈(n+1)⌉회, 검색에 성공한 경우는 대략 log(n)-1회
* ⌈  ⌉ 천장 메서드(ceiling function)를 나타내는 기호로, x보다 크거나 같으면서 가장 작은 정수 ex)⌈3.5⌉ = 4
 
