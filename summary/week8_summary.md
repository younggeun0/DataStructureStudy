# Week 8
## 셸 정렬
셸 정렬은 단순 삽입 정렬의 장점은 살리고 단점은 보완하여 좀 더 빠르게 정렬하는 알고리즘

### 단순 삽입 정렬의 특징
- 정렬을 마쳤거나 정렬을 마친 상태에 가까우면 정렬 속도가 매우 빨라짐(장점)
- 삽입할 위치가 멀리 떨어져 있으면 이동(대입)해야 하는 횟수가 많아짐(단점)

### 셸 정렬(shell sort))
먼저 정렬할 배열의 요소를 그룹으로 나눠 각 그룹 별로 단순 삽입 정렬을 수행하고,<br/> 그 그룹을 합치면서 정렬을 반복하여 요소의 이동 횟수를 줄이는 방법
<br/>
<br/>
여러개의 그룹으로 나누어 정렬하는 이유 : 단순 삽입 정렬의 장점은 살리고 단점은 보완하기 위해.<br/>
=> 정렬해야 하는 횟수는 늘지만 전체적으로 요소 이동의 횟수가 줄어들어 효율적인 정렬이 가능해짐
<br/>
<br/>
![shell sort](https://image2.slideserve.com/4117683/slide19-l.jpg)
<br/>
```JAVA
package chap06;
import java.util.Scanner;
//셸 정렬(버전1)
//시간 복잡도 n^2
class ShellSort {
	
	static void shellSort(int[] a, int n) {
		for(int h = n/2; h>0; h/=2) {
			for(int i=h; i<n; i++) {
				int j;
				int tmp = a[i];
				for (j=i-h; j>=0 && a[j]>tmp; j-=h)
					a[j+h] = a[j];
				a[j+h]=tmp;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("셸 정렬(버전 1)");
		System.out.println("요솟수: ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];
		
		for (int i=0; i<nx; i++) {
			System.out.println("x["+i+"]:");
			x[i] = stdIn.nextInt();
		}
		//배열 x를 쉘 정렬
		shellSort(x, nx);
		
		System.out.println("오름 차순으로 정렬했습니다.");
		for (int i=0; i<nx; i++) {
			System.out.println("x["+i+"]="+x[i]);
		}
	}

}
```

**증분값(h값)의 선택**<br/>
- h 값이 서로 배수가 되지 않도록 하면 효율을 높일 수 있음
- 1부터 시작하여 3배한 값에 1을 더하는 수열
- h의 초기값은 너무 크면 효과가 없기 때문에 배열의 요솟수 n은 9를 넘지 않도록 해야 함
```JAVA
package chap06;
import java.util.Scanner;
//셸 정렬 버전2
//시간 복잡도 n^(1.25)
//그러나 멀리 떨어져있는 요소를 교환해야 하므로 안정적이지는 않음

class ShellSort2 {
	// 셸정렬
	static void shellSort(int[] a, int n) {
		int h;
		for (h = 1; h < n / 9; h = h * 3 + 1);

		for ( ; h > 0; h /= 3)
			for (int i = h; i < n; i++) {
				int j;
				int tmp = a[i];
				for (j = i - h; j >= 0 && a[j] > tmp; j -= h)
					a[j + h] = a[j];
				a[j + h] = tmp;
			}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("셸 정렬(버전 2)");
		System.out.print("요솟수：");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; i++) {
			System.out.print("x[" + i + "]：");
			x[i] = stdIn.nextInt();
		}

		// 배열 x를 셸 정렬
		shellSort(x, nx);
		
		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++)
			System.out.println("x[" + i + "]＝" + x[i]);
	}
}
```
## 퀵 정렬
피벗(pivot, 그룹을 나누는 기준)으로 빠르게 정렬하는 알고리즘

### 배열을 두 그룹으로 나누기
왼쪽 끝 요소의 인덱스를 pl, 오른쪽 끝 요소의 인덱스를 pr로 하여 검색<br/>
pl과 pr의 교차점이 피벗과 일치하는 지점<br/>
일치 지점에서 동일한 요소를 교환하지만 1회<br/>
```JAVA
package chap06;

import java.util.Scanner;

class Partition {
	//배열 요소 a[idx1]과 a[idx2]의 값을 바꿉니다.
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}
	
	//배열을 나눕니다.
	static void partition(int[] a, int n) {
		int pl = 0; //왼쪽 커서
		int pr = n-1; //오른쪽 커서
		int x = a[n/2]; //피벗
		do {
			while (a[pl] < x) pl++;
			while (a[pr] > x) pr--;
			if (pl <= pr)
				swap(a, pl++, pr--);
		}while (pl <= pr);
		
		System.out.println("피벗의 값은 "+ x + "입니다.");
		
		System.out.println("피벗 이하의 그룹");
		for (int i=0; i<=pl-1; i++)
			System.out.print(a[i]+ " ");
		System.out.println();
		
		if (pl > pr + 1) {
			System.out.println("피벗과 일치하는 그룹");
			for (int i = pr + 1; i <= pl - 1; i++)
				System.out.print(a[i] + " ");
			System.out.println();
		}

		System.out.println("피벗 이상의 그룹");
		for (int i = pr + 1; i < n; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("배열을  나눕니다.");
		System.out.print("요솟수：");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; i++) {
			System.out.print("x[" + i + "]：");
			x[i] = stdIn.nextInt();
		}
		partition(x, nx);
	}
}
```
### 퀵 정렬
피벗을 기준으로 나눈 것을 또 분할하여 정렬하는 것으로 재귀 호출을 사용하여 구현
```JAVA
package chap06;
import java.util.Scanner;
// 퀵 정렬

class QuickSort1 {
	// 배열 요소 a[idx1]과 a[idx2]의 값을 바꿉니다.
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];  a[idx1] = a[idx2];  a[idx2] = t;
	}

	// 퀵 정렬
	static void quickSort(int[] a, int left, int right) {
		int pl = left;
		int pr = right;
		int x = a[(pl + pr) / 2];
		
		//진행 과정을 출력하는 부분
		System.out.printf("a[%d]~a[%d]：{", left, right);
		for (int i = left; i < right; i++)
			System.out.printf("%d , ", a[i]);
		System.out.printf("%d}\n", a[right]);
		
		do {
			while (a[pl] < x) pl++;
			while (a[pr] > x) pr--;
			if (pl <= pr)
				swap(a, pl++, pr--);
		} while (pl <= pr);

		//재귀 호출 사용 부분
		if (left < pr)  quickSort(a, left, pr);
		if (pl < right) quickSort(a, pl, right);
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("퀵 정렬");
		System.out.print("요솟수：");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; i++) {
			System.out.print("x[" + i + "]：");
			x[i] = stdIn.nextInt();
		}

		//배열 x를 퀵 정렬
		quickSort(x, 0, nx - 1);

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++)
			System.out.println("x[" + i + "]＝" + x[i]);
	}
}
```

### 비재귀적인 퀵 정렬
quickSort 메서드를 비재귀적으로 구현하는 방법. '스택'을 사용
```JAVA
package chap06;
import java.util.Scanner;
//퀵 정렬 (비재귀 버전)

class QuickSort2 {
	// 배열 요소 a[idx1]과 a[idx2]의 값을 바꿉니다.
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];  a[idx1] = a[idx2];  a[idx2] = t;
	}

	// 퀵정렬
	static void quickSort(int[] a, int left, int right) {
		IntStack lstack = new IntStack(right - left + 1);
		IntStack rstack = new IntStack(right - left + 1);

		lstack.push(left);
		rstack.push(right);

		while (lstack.isEmpty() != true) {
			int pl = left  = lstack.pop();
			int pr = right = rstack.pop();
			int x = a[(left + right) / 2];

			do {
				while (a[pl] < x) pl++;
				while (a[pr] > x) pr--;
				if (pl <= pr)
					swap(a, pl++, pr--);
			} while (pl <= pr);

			//왼쪽 그룹 범위 인덱스 푸쉬
			if (left < pr) {
				lstack.push(left);
				rstack.push(pr);
			}
			//오른쪽 그룹 범위 인덱스 푸쉬
			if (pl < right) {
				lstack.push(pl);
				rstack.push(right);
			}
		}
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.println("퀵 정렬(비재귀 버전)");
		System.out.print("요솟수：");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];

		for (int i = 0; i < nx; i++) {
			System.out.print("x[" + i + "]：");
			x[i] = stdIn.nextInt();
		}

		quickSort(x, 0, nx - 1);			// 배열 x를 퀵 정렬합니다.

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++)
			System.out.println("x[" + i + "]＝" + x[i]);
	}
}
```
- 요소의 개수가 많은 그룹 vs 적은 그룹 먼저 푸시하는 경우<br/>
일반적으로 요소의 개수가 적은 배열일수록 적은 횟수로 분할을 종료할 수가 있음<br>
**따라서** 요소의 개수가 *많은 그룹*을 <u>먼저 push</u>하여 *적은 그룹*을 <u>먼저 pop</u>하고 분할 하는 것이 데이터가 적게 쌓임

- **효율적인 피벗 선택 방법**
1. 나눌 배열의 요소 개수가 3개 이상이면 임의로 요소 3을 선택하고 그중에서 중앙값인 요소를 피벗으로 선택한다.
2. 나눌 배열의 처음, 가운데, 끝 요소를 정렬한 다음 가운데 요소와 끝에서 두 번째 요소를 교환한다.<br/>피벗으로 끝에서 두 번째 요소의 값 a[right-1]을 선택하여 나눌 대상의 범위를 a[left+1]~a[right-2]로 좁힘
<br/>
=> 크기가 한 쪽으로 치우치는 것을 피하면서도 나눌 때 스캔할 요소를 3개씩 줄일 수 있다는 장점

<p><img src="https://mblogthumb-phinf.pstatic.net/MjAxNjExMjVfMTM0/MDAxNDgwMDM1Njc3MDc3.-BzXnvTma6VWXgVrdKk9drbNVyeAjYpzM8LWREz2eTgg.m89qmJhFXGrEN991hlVuSdpaPbTauFlyF-boPDafarUg.JPEG.occidere/image_6832344861480032556796.jpg?type=w800" alt="피벗선택하기2" style="width:500px"></p>

- **시간 복잡도**
퀵 정렬의 시간 복잡도는 n log n <br/>
매번 단 하나의 요소와 나머지 요소로 나누게 되면 n번 분할해야하므로 최악의 시간 복잡도는 n^2<br/>

