# Week 5

## 1. 재귀의 기본

* **재귀란?**
  * 어떤 사건이 자기 자신을 포함하고 다시 자기 자신을 사용하여 정의될 때 **재귀적(recursive)**이라고 함
* **재귀적 정의(recursive definition)**에 의해 무한으로 존재하는 자연수를 두 문장으로 정의할 수 있음

> 1. 1은 자연수
> 2. 자연수 n의 바로 다음 수도 자연수

* 재귀를 효과적으로 사용하면 프로그램을 간결하게 할 수 있음
  * 병합 정렬, 퀵 정렬, 이진검색트리 등에 사용됨

* **팩토리얼(factorial) 구하기**
  * 간단한 재귀 사용 예
  * 음이 아닌 정수 n의 팩토리얼(n!)은 아래처럼 재귀적으로 정의가능

> 1. 0! = 1
> 2. n>0 이면 n!=n*(n-1)!

```java
public class Factorial { // 팩토리얼을 재귀적으로 구현
	static int factorial(int n) { // 양의 정수 n의 팩토리얼을 반환
		if (n > 0) {
			return n*factorial(n-1); // 재귀적 호출(recursive call)
		} else {
			return 1;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수를 입력하세요 : ");
		int n = sc.nextInt();
		
		System.out.println(n+"의 팩토리얼은 "+factorial(n)+"입니다.");
		
	}
}
```

* **직접 재귀와 간접 재귀**
  * **직접(direct) 재귀**
    * 자기 자신과 같은 메서드를 호출
    * factorial 메서드는 그 내부에서 factorial 메서드를 호출하는 것
  * **간접(indirect) 재귀**
    * 다른 메서드를 통해 자기 자신과 같은 메서드를 호출
    * 메서드 a가 메서드 b를 호출하고, 다시 메서드 b가 메서드 a를 호출하는 구조
* **유클리드 호제법(Euclidean method of mutual division)**
  * 두 정수의 **최대 공약수(Greatest Common Divisor)**를 재귀적으로 구하는 방법
  * 두 정수가 주어질 경우 큰 값을 작은 값으로 나누었을 때 나누어 떨어지는 가장 작은 값이 **최대 공약수**
    * 나누어지지 않으면 작은 값(얻은 나머지)에 대해 나누어 떨어질때까지 같은 과정을 재귀적으로 반복

```java
public class EuclidGCD { // GCD(Greatest Common Divisor)
	// 유클리드 호제법으로 최대공약수 구하기
	static int gcd(int x, int y) {
		//정수 x,y의 최대공약수를 구하면 반환
		if (y == 0) // 나누어졌을 때 가장 작은 값이 GCD
			return x;
		else
			return gcd(y, x%y); // 나누어 떨어지지 않으면 재귀적으로 반복
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("두 정수의 최대공약수를 구함");
		System.out.print("정수를 입력하세요 : "); int x = sc.nextInt();
		System.out.print("정수를 입력하세요 : "); int y = sc.nextInt();
		sc.close();
		System.out.println("최대 공약수는 "+gcd(x,y)+"입니다.");
	}
}
```

## 2. 재귀 알고리즘 분석

* 재귀 알고리즘 분석법은 **하향식(top down) 분석과 상향식(bottom up) 분석**이 존재

```java
public class Recur {
	static void recur(int n) { 
		// 재귀 호출을 여러 회 실행하는 메서드는 순수하게(genuinely) 재귀적이라 함
		if (n > 0) {
			recur(n-1);
			System.out.println(n);
			recur(n-2);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int n = sc.nextInt(); // 4가 입력되면
		sc.close();
		recur(n); // 1 2 3 1 4 1 2가 출력
	}
}
```

* **하향식 분석(top-down analysis)**
  * **가장 위쪽에 위치한 상자의 메서드 호출부터 시작해 계단식으로 자세히 조사하는 분석기법**
  * 매개변수 n으로 4를 전달하면 recur 메서드는 아래 과정으로 실행
  * 같은 호출이 여러번 존재하면 (recur(1), recur(2)같은 경우) 하향식 분석이 효율적이지 않을 수 있음

```java
// recur(4)의 호출 순서
recur(3)
-recur(2)
--recur(1)
---recur(0)
---1출력
---recur(-1)
--2출력
--recur(0)
-3출력
-recur(1)
--recur(0)
--1출력
--recur(-1)
4출력
recur(2)
-recur(1)
--recur(0)
--1출력
--recur(-1)
-2출력
-recur(0)
```

* **상향식 분석(bottom-up analysis)**
  * **하향식 분석과 대조적으로 아래쪽부터 쌓아 올리며 분석하는 방법**
  * recur메서드는 n이 양수일 때만 실행, recur(1)을 우선 분석
	* recur(0) - 1출력 - recur(-1)을 호출
	  * recur(0)과 recur(-1)은 출력 안함
  * recur(2)를 분석
    * recur(1) - 2출력 - recur(0) 실행
	  * recur(1)은 1을 출력
	  * 때문에 recur(2)를 호출하면 1과 2가 출력됨
  * 이와 같은 방식으로 recur(4)까지 분석

```java
recur(1) // recur(0); 1출력; recur(-1) => 1출력
recur(2) // recur(1); 2출력; recur(0) => 1,2출력
recur(3) // recur(2); 3출력; recur(1) => 1,2,3,1 출력
recur(4) // recur(3); 4출력; recur(2) => 1,2,3,1,4,1,2 출력
```

* **재귀 알고리즘의 비재귀적 표현**
  * 위 recur 메서드를 재귀호출 없이 구현하는 방법
  * ==> **꼬래 재귀 제거 + 재귀의 제거**
* **꼬리 재귀(tail recursion)의 제거**
  * recur(n-2)라는 말은 '인자로 n-2를 전달하여 recur메서드를 호출'과 동일한 의미
   
```java
static void recur(int n) { 
	// 꼬리 재귀를 제거한 모습
	while (n > 0) {
		recur(n-1);
		System.out.println(n);
		n = n-2;
	}
}
```

* **재귀의 제거**
  * 꼬리 재귀