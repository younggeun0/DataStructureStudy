# Week 1

## 1. 기본 알고리즘

* 기본적인 문법, 분기, 반복, 순서도 내용은 생략
* **알고리즘**
  * **문제를 해결하기 위한 것으로, 명확하게 정의되고 순서가 있는 유한 개의 규칙으로 이루어진 집합**
* **구조적 프로그래밍**
  * 하나의 입구와 하나의 출구를 가진 구성 요소만을 계층적으로 배치하여 프로그램을 구성하는 방법
* **드모르간의 법칙**
  * '각 조건을 부정하고 논리곱을 논리합으로, 논리합을 논리곱으로 바꾸고 다시 전체를 부정하면 원래의 조건과 같다'라는 법칙

```java
x && y == !(!x || !y)
x || y == !(!x && !y)
```

## 2. 기본 자료구조

* **자료구조(Data Structure)**
  * **데이터 단위와 데이터 자체 사이의 물리적 또는 논리적인 관계**
    * 데이터 단위는 데이터를 구성하는 한 덩어리, 간단히 자료구조는 자료를 효율적으로 이용할 수 있도록 컴퓨터에 저장하는 방법
* **배열(Array)**
  * 가장 기본적이고 간단한 자료구조
  * 같은 자료형의 변수로 이루어진 구성 요소(Component)가 모인 것

```java
int[] a;        // 선언
a = new int[5]; // 참조

// 요솟값 초기화 배열 선언
int[] b = {1, 2, 3, 4, 5}; 
int[] c = new int[] {6, 7, 8, 9, 10};

// 배열 복제
int[] d = c.clone(); // {6, 7, 8, 9, 10}
```

* **최대값 구하기**
  * Random클래스 사용법은 생략

```java
public class MaxOfArrayRand {
	public static int maxOf(int[] a) {
		int max = a[0];
		for(int i=1; i<a.length; i++) {
			if(a[i]>max) {
                max = a[i];
			}
		}
		return max;
	}
	public static void main(String[] args) {
		Random r = new Random();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("get max height");
		System.out.print("people : ");
		int num = sc.nextInt();
		
		int[] height = new int[num];
		
		for(int i=0; i<num; i++) {
			height[i] = 100 + r.nextInt(90);
			System.out.println("height["+i+"] : "+height[i]);
		}
        System.out.println("max is "+maxOf(height));
    }
}
```

* 컴퓨터에서 생성하는 난수는 진짜 난수가 아님
  * 환경이 같고 seed값이 같다면 동일한 값이 나올 수 있음 == **의사난수**
  * 때문에 **현재 시간**을 seed로 사용하는게 일반적
* **배열 요수값 교환**
  * 교환 횟수는 '요소 개수/2'
  * 교환을 위해선 임시변수에 값을 보관하여 교환

```java
tmp = a[i];
a[i] = a[i+1];
a[i+1] = tmp;
```

* **배열 역순 정렬**

```java
public class ReverseArray {
	static void swap(int[] a, int idx, int idx2) {
		int tmp = a[idx];
		a[idx] = a[idx2];
		a[idx2] = tmp;
	}
	static void reverse(int[] a) {
		for(int i=0; i<a.length/2; i++) {
			swap(a, i, a.length-i-1);
		}
	}
	public static void main(String[] args) {
		Random r = new Random();
		int num = r.nextInt(10);
		System.out.println("num = "+num);
		
		int[] x = new int[num];
		
		for(int i=0; i<num; i++) {
			x[i] = r.nextInt(5);
		}
		System.out.println("origin Array : "+Arrays.toString(x));
		
		reverse(x);
		System.out.println("reversed Array : "+Arrays.toString(x));
	}
}
```

* **기수**
  * 기수는 수를 나타내는 데 기초가 되는 수
    * 10진법에서는 0에서 9까지의 정수를 말함(0~9)
	* 8진수(0~7)
	* 16진수(0~9 A~F)
* **기수변환**이란 정숫값을 임의의 기수로 변환하는 것
  * 10진수 정수를 r으로 나눈 나머지를 구하는 동시에 그 몫에 대해 나눗셈을 반복
    * 이 과정을 몫이 0이 될 때까지 반복, 구한 나머지를 거꾸로 늘어 놓은 숫자가 기수 변환된 값

```java
public class CardConvRev {
	static int cardConvR(int x, int r, char[] d) {
		int digits = 0;
		String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		do {
			d[digits++] = dchar.charAt(x % r);
			x /= r;
		} while(x != 0);
		
		return digits;
	}
	
	public static void main(String[] args) {
		
		char[] d = new char[6];
		int x = 59;
		int r = 2;
		
		cardConvR(x, r, d);
		
		System.out.println(Arrays.toString(d)); // 110111
	}
}
```

* **소수 구하기**
  * 소수는 자신과 1 이외의 정수로 나누어 떨어지지 않는 정수
    * 즉, 2부터 n-1까지의 어떤 정수로도 나누어 떨어지지 않음

```java
public class PrimeNumber1 {
	public static void main(String[] args) {
		int counter = 0;
		
		for(int n=2; n<=1000; n++) {
			int i;
			for (i=2; i<n; i++) {
				counter++;
				if (n%i == 0) // 나누어 떨어지면 소수가 아님
					break;
			}
			if (n==i) // i가 n-1까지 나누어 떨어지지 않으면 n값이 됨(마지막까지 나누어떨어지지 않음)
				System.out.println(n); // n은 소수
		}
		System.out.println("나눗셈을 수행한 횟수 : "+counter); // 78022
	}
}
```

* **소수 구하는 알고리즘 개선1**
  * 예를 들어 7이 소수인지는 7보다 작은 소수 2,3,5로 나눗셈을 하면 충분
  * 구한 소수를 prime 배열에 저장, 임의의 수 n이 소수인지 판단하기 위해 쌓아둔 소수로 나눗셈을 진행

```java
public class PrimeNumber2 {
	public static void main(String[] args) {
		int counter = 0; // 나눈 횟수
		int ptr = 0; // 찾은 소수의 개수
		int[] prime = new int[500]; // 소수를 저장하는 배열
		
		prime[ptr++] = 2; // 2는 소수
		
		for(int n=3; n<=1000; n+=2) { // 홀수만 대상
			int i;
			for(i =1; i<ptr; i++) {
				counter++;
				if(n%prime[i] == 0) { // 찾은 소수와 비교
					break;
				}
			}
			if (ptr == i) { // 마지막까지 나눠떨어지지 않으면 
				prime[ptr++] = n; // 소수이므로 배열에 저장, 반복
			}
		}
		
		for(int i=0; i<ptr; i++) {
			System.out.println(prime[i]); // 찾은 소수 출력
		}
		System.out.println("나눗셈을 수행한 수 : " + counter); // 14622
	}
}
```

* 소수 구하는 알고리즘의 개선으로 알 수 있는 점
  * **같은 답을 얻는 알고리즘은 하나가 아님**
  * **빠른 알고리즘은 메모리를 많이 요구함**
* **소수 구하는 알고리즘 개선2**
  * 어떤 정수 n은 n의 제곱근 이하의 어떤 소수로도 나누어떨어지지 않는다면 소수라는 점을 이용
  * 

```java
public class PrimeNumber3 {
	public static void main(String[] args) {
		int counter = 0; // 곱셈과 나눗셈 횟수
		int ptr = 0; // 찾은 소수의 개수
		int[] prime = new int[500];
		
		prime[ptr++] = 2;
		prime[ptr++] = 3; // 2와 3은 소수
		
		for (int n=5; n<=1000; n += 2) { // 대상은 홀수만
			boolean flag = false;
			for(int i=1; prime[i]*prime[i] <= n; i++) { // 소수의제곱 보다 작을 값만 확인
				counter += 2; // 곱하고 나누니까 2씩 더함
				if (n % prime[i] == 0) { // 나눠 떨어지면 소수가 아님
					flag = true;
					break;
				}
			}
			if (!flag) { // 마지막까지 나눠떨어지지 않음
				prime[ptr++] = n; // 소수는 배열에 저장
				counter++;
			}
		}
		
		for(int i=0; i<ptr; i++) {
			System.out.println(prime[i]);
		}
		
		System.out.println("곱셈과 나눗셈 횟수 : "+counter); // 3774
	}
}
```

* **다차원 배열(Multi-Dimensional Array)**
  * 배열을 구성요소로 하는 것이 2차원 배열, 2차원 배열을 구성 요소로 하는 것이 3차원 배열

```java
int[][] x = new int[2][4]; 
long[][][] y = new long[2][3][4];
```

* **한 해 경과 일 수 계산 프로그램**

```java
public class DayOfYear {

	static int[][] mdays = { // 각 달의 일수
			{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}, // 평년
			{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31} // 윤년
	};
	
	// 윤년 구하는 함수
	static int isLeap(int year) { // 윤년에 해당하는 조건들 참이면 윤년(1), 아니면 평년(0)
		return (year %4 == 0 && year % 100 != 0 || year % 400 == 0) ? 1: 0;
	}
	
	static int dayOfYear(int y, int m, int d) {
		int days = d; // 일 수
		
		for(int i=1; i<m; i++) { // 1월~(m-1)월의 일수를 더함 
			days += mdays[isLeap(y)][i-1]; // isLeap 윤년이면 1, 평년이면 0
		}
		return days;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int retry;
		
		do {
			System.out.print("년 : "); int year = sc.nextInt();
			System.out.print("월 : "); int month = sc.nextInt();
			System.out.print("일 : "); int day = sc.nextInt();
			
			System.out.printf("그 해 %d일째 \n", dayOfYear(year, month, day));
			
			System.out.print("다시 ? (1:예/0:아니오) : ");
			retry = sc.nextInt();
		} while(retry == 1);
	}
}
```

