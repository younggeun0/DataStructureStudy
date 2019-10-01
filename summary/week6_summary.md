# Week 6
## 하노이의 탑(Tower of Hanoi)
작은 원반이 위에, 큰 원반이 아래에 위치할 수 있도록 원반을 3개의 기둥 사이에서 옮기는 문제. <br/>
모든 원반의 크기는 다르고 처음에는 모든 원반이 첫 번째 기둥에 쌓여 있음 <br/>
원반은 1개씩만 옮길 수 있고 큰 원반을 작은 원반 위에 쌓을 수 없다

> **풀이** <br/>
> 제일 아래 원반을 제외한 나머지를 그룹이라고 생각하여 <br/>
> 1. 그룹을 시작 기둥에서 중간 기둥으로
> 2. 맨 아래 원반을 시작 기둥에서 목표 기둥으로
> 3. 그룹을 중간 기둥에서 목표 기둥으로
```JAVA
package chap05;
import java.util.Scanner;
//하노이의 탑
public class Hanoi {
    //no개의 원반을 x번 기둥에서 y번 기둥으로 옮김
	static void move(int no, int x, int y) {
		if(no > 1)
			move(no-1, x, 6-x-y);
		System.out.println("원반[" + no + "]을" + x + "기둥에서 " + y + "기둥으로 옮김");
		
		if(no > 1)
			move(no-1, 6-x-y, y);
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("하노이의 탑");
		System.out.println("원반 개수 : ");
		int n = stdIn.nextInt();
		
		move(n, 1, 3); //1번 기둥의 n개의 원반을 3번 기둥으로 옮김
	}//main
}//class
```
- move 메서드는 다음과 같은 과정을 거침
> 1. 바닥 원반을 제외한 그룹(원반[1]~원반[no-1])을 시작 기둥에서 중간 기둥으로 옮김
> 2. 바닥 원반 no을 시작 기둥에서 목표 기둥으로 옮겼음을 출력
> 3. 바닥 원반을 제외한 그룹(원반[1]~원반[no-1])을 중간 기둥에서 목표 기둥으로 옮김


## 8퀸 문제
### **8퀸 문제란?**
: *서로 공격하여 잡을 수 없도록 8개의 퀸을 8x8 체스판에 놓으세요.*

### **퀸 배치하기**
- 8개의 퀸을 배치하는 조합은
- **64P8**

> [규칙 1] 각 열에 퀸을 1개만 배치합니다. <br/>
> 규칙 1에 따라 <br/>
> **8^8**

### **가지 뻗기(branching)**
배열 pos : 퀸의 배치 <br/>
i열에 놓인 퀸의 위치가 j행일 경우 <br/>
pos[i]의 값이 j <br/>
set 메서드는 pos[i]에 0부터 7까지의 값을 순서대로 대입하여 'i열에 퀸을 1개만 배치하는 8가지 조합을 만드는 재귀 메서드'
> set(0);
- 가장 먼저 main메서드에서 호출된 set 메서드는 매개변수 i에 0을 전달받음
- 0열에 1개의 퀸을 배치하는 8가지 조합을 for문에 의해 나열
- j값을 0부터 7까지 1씩 증가하며 다음과 같이 재귀 호출
> set(i+1);
- 앞에서 했던 작업을 다음 열인 1열에서 수행
- i가 7이 되면 8개의 퀸이 모두 배치
- print 메서드를 호출하여 퀸이 배취된 위치를 출력

```JAVA
//각 열의 퀸의 위치를 출력
static void print() {
		for(int i=0; i<8; i++)
			System.out.printf("%2d", pos[i]);
		System.out.println();
	}

//i열에 퀸 놓기
static void set(int i) {
		for (int j=0; j<8; j++) {
			if(flag_a[j] == false &&
				flag_b[i+j] == false &&
				flag_c[i-j+7] == false) {
				pos[i] = j;
				if(i==7)
					print();
				else {
					flag_a[j] = flag_b[i+j] = flag_c[i-j+7] = true;
					set(i+1);
					flag_a[j] = flag_b[i+j] = flag_c[i-j+7] = false;
				}
			}
		}
	}
```
- 가지를 뻗으며 퀸을 배치하는 조합 모두 나열 : 가지뻗기 방법 (branching) <br/>
- 문제를 세분하고 세분된 작은 문제의 풀이를 결합해 전체 문제를 풀이하는 기법을 분할 정복법(divide and conquer) <br/>
- 문제를 세분할 때는 작은 문제의 풀이에서 원래 문제의 풀이가 쉽게 도출되도록 설계해야 함

### **분기 한정법(divide and conquer)**
> [규칙 2] 각 행에 퀸을 1개만 배치
```JAVA
package chap05;
//각 행, 열에 1개의 퀸을 배치하는 조합을 재귀적으로 나열합니다.

class QueenBB {
    //각 행에 퀸을 배치했는지 체크
	static boolean[] flag = new boolean[8];
    //각 열의 퀸의 위치
	static int[] pos = new int[8];
	
    //각 열의 퀸의 위치를 출력합니다.
	static void print() {
		for(int i=0; i<8; i++)
			System.out.printf("%2d", pos[i]);
		System.out.println();
	}
	
    //i열의 알맞은 위치에 퀸을 배치합니다.
	static void set(int i) {
		for(int j=0; j<8; j++) {
			if(flag[j]==false) { //j행에 아직 퀸을 배치하지 않았다면
				pos[i] = j; //퀸을 j행에 배치
				if(i==7) //모든 열에 배치한 경우
					print();
				else {
					flag[j] = true;
					set(i+1);
					flag[j] = false;
				}//end else
			}//end if
		}//end for
	}//end set
	
	public static void main(String[] args) {
		set(0);
	}//main

}//class
```
- 필요하지 않은 분기를 없애 불필요한 조합을 줄이는 방법을 한정(bounding)조작
- 가지 뻗기 + 한정 조작 = 분기 한정법(branching and bounding method)

### **최종 8퀸 문제 풀이**
```JAVA
package chap05;
//8퀸 문제 풀이

class EightQueen {
    //각 행에 퀸을 배치했는지 체크
	static boolean[] flag_a = new boolean[8];
    // / 대각선 방향으로 퀸을 배치했는지 체크
	static boolean[] flag_b = new boolean[15];
    // \ 대각선 방향으로 퀸을 배치했는지 체크
	static boolean[] flag_c = new boolean[15];
    // 각 열의 퀸의 위치
	static int[] pos = new int[8];
	
    //퀸의 위치를 출력
	static void print() {
		for(int i=0; i<8; i++)
			System.out.printf("%2d", pos[i]);
		System.out.println();
	}
	
    // i열의 알맞은 위치에 퀸을 배치
	static void set(int i) {
		for (int j=0; j<8; j++) {
			if(flag_a[j] == false && //가로(j행)에 아직 배치하지 않았습니다.
				flag_b[i+j] == false && //대각선 /에 아직 배치하지 않았습니다.
				flag_c[i-j+7] == false) { //대각선 \에 아직 배치하지 않았습니다.
				pos[i] = j; //퀸을 j행에 배치
				if(i==7) //모든 열에 배치했다면
					print();
				else {
					flag_a[j] = flag_b[i+j] = flag_c[i-j+7] = true;
					set(i+1);
					flag_a[j] = flag_b[i+j] = flag_c[i-j+7] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		set(0);
	}//main

}//class
```
