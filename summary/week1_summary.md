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
