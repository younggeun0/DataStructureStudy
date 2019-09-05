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
 
### **복잡도**
- 복잡도
1. 시간 복잡도(time complexity) : 실행에 필요한 시간을 평가한 것
2. 공간 복잡도(space complexity) : 기억 영역과 파일 공간이 얼마나 필요한가를 평가한 것

* **선형 검색의 시간 복잡도**

|실행 횟수|복잡도|
|---|---|
|1|O(1)|
|n/2|O(n)|

* 복잡도를 계산하는 방법
	* O(f(n)) + O(g(n)) = O(max(f(n)), g(n))

* **이진 검색의 시간 복잡도**

|실행 횟수|복잡도|
|---|---|
|1|O(1)|
|log n|O(log n)|

<------------복잡도와 증가율---------------><br/>
<--1, log n, n, n log n, n^2, n^3, ,,, 2^n--><br/>

### **Arrays.binarySearch에 의한 이진 검색**
- java.util.Arrays 클래스의 binarySearch 메서드
	- 장점1. 이진 검색 메서드를 직접 코딩할 필요가 없다.
	- 장점2. 모든 자료형 배열에서 검색할 수 있다.

- 검색에 성공한 경우
	- key와 일치하는 요소의 인덱스를 반환
	- 여러 개 있다면 무작위의 인덱스를 반환

- 검색에 실패한 경우
	- 반환값은 삽입 포인트를 x라고 할 때 -x-1을 반환
	- 삽입포인트: 검색하기 위해 지정한 key보다 큰 요소 중 첫 번째 요소의 인덱스
	- 만약 배열의 모든 요소가 key보다 작다면 배열의 길이를 삽입 포인트로 정함
	ex) key=4, 삽입포인트 : 3, 반환값 : -4

* **기본 자료형 배열에서 binarySearch메서드로 검색하기**
```JAVA
public class BinarySearchTester {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("요솟수 : ");
		int num = stdIn.nextInt();
		int[] x = new int[num];
		
		System.out.println("오름차순으로 입력하세요.");
		
		System.out.print("x[0] : ");
		x[0] = stdIn.nextInt();
		
		for (int i = 1; i < num; i++) {
			do {
				System.out.print("x[" + i + "]:");
				x[i] = stdIn.nextInt();
			}while (x[i] < x[i-1]);
		}
		
		System.out.print("검색할 값:");
		int ky = stdIn.nextInt();
		
		int idx = Arrays.binarySearch(x, ky);
		
		if (idx < 0)
			System.out.println("그 값의 요소가 없습니다.");
		else
			System.out.println(ky + "은(는) x[" + idx + "]에 있습니다.");
	}//main
}//class
```

- JAVA 메서드의 종류
	- 인스턴스 메서드(비정적 메서드): static을 붙이지 않고 선언한 메서드
	- 클래스 메서드(정적 메서드): static을 붙여 선언한 메서드
	- 인스턴스 메서드 호출 시 : 클래스형 변수 이름.메서드이름
	- 클래스 메서드 호출 시 : 클래스이름.메서드이름

* **객체의 배열에서 검색하기**
1. static int binarySearch(Object[] a, Object key) <br/>
: 자연 정렬이라는 방법으로 요소의 대소 관계를 판단. 정수배열, 문자열 배열에서 검색할 때 적당
2. static <T> int binarySearch(T[] a, T key, Comparator<? super T> c) <br/>
: 줄지어 있는 배열에서 검색하거나 "자연 순서"를 논리적으로 갖지 않는 클래스 배열에서 검색할 때 알맞음

*A. 자연 정렬로 정렬된 배열에서 검색하기*
```JAVA
class StringBinarySearch {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		String[] x = {
				"abstract",   "assert",       "boolean",   "break",      "byte",
				"case",       "catch",        "char",      "class",      "const",
				"continue",   "default",      "do",        "double",     "else",
				"enum",       "extends",      "final",     "finally",    "float",
				"for",        "goto",         "if",        "implements", "import",
				"instanceof", "int",          "interface", "long",       "native",
				"new",        "package",      "private",   "protected",  "public",
				"return",     "short",        "static",    "strictfp",   "super",
				"switch",     "synchronized", "this",      "throw",      "throws",
				"transient",  "try",          "void",      "volatile",   "while"
		};
		
		System.out.println("원하는 키워드를 입력하세요: ");
		String ky = stdIn.next();
		
		int idx = Arrays.binarySearch(x, ky);
		
		if (idx < 0)
			System.out.println("그 값의 요소가 없습니다.");
		else
			System.out.println(ky + "은(는) x[" + idx + "]에 있습니다.");
	}//main

}//class
```

- 자연 정렬(natural ordering) : 사람이 보기 자연스러운 방법으로 정렬 되는 것. binarySearch 메서드에 배열과 키 값을 전달하는 간단한 방법으로 검색할 수 있는 이유는 String 클래스가 Comparable<T> 인터페이스와 compareTo 메서드를 구현하고 있기 때문. 

*B. 자연 정렬로 정렬되지 않은 배열에서 검색하기*
- 제네릭 메서드로 하면 됨
- 단, 배열의 요소가 어떤 순서로 줄지어 있는지 binarySearch 메서드에 알려줘야 함(매개변수 c에 comprator를 전달)
- 1. java.util.Comparator 인터페이스
```JAVA
package java.util;

public interface Comparator <T> {
	int compare(T o1, T o2);
	boolean equals(Object obj);
}
```
- 2. Comparator 직접 구현
```JAVA
public int compare(T d1, T d2){
	if(d1 > d2) return 양수;
	if(d1 < d2) return 음수;
	if(d1 == d2) return 0;
}
```
***제네릭***
- class/interface 클래스이름/인터페이스이름 <파라미터> {/*...*/}
- class/interface 클래스이름/인터페이스이름 <파라미터1, 파라미터2, ... > {/*...*/}
- 파라미터의 이름을 작성하는 방법
> 1. 1개의 대문자를 사용(소문자는 가급적 사용하지 않음)
> 2. 컬렉션(collection)의 자료형은 element의 앞글자인 E를 사용
> 3. 맵(Map)의 키(key), 값(value)은 key와 vlaue의 앞글자인 K와 V를 사용
> 4. 일반적으로는 T를 사용
- 형 변수에 와일드 카드를 지정하는 것도 가능 ex) <? extneds T>
