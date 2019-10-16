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
