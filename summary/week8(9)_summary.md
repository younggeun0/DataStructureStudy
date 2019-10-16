# Week 8
## 병합 정렬
병합 정렬은 배열을 앞부분과 뒷부분으로 나누어 각각 정렬한 다음 병합하는 작업을 반복하여 정렬을 수행하는 알고리즘

### **정렬을 마친 배열의 병합**
- 시간 복잡도는 n
```JAVA
class MergeArray {
	//정렬을 마친 배열 a,b를 병합하여 배열 c에 저장합니다.
	static void merge(int[] a, int na, int[] b, int nb, int[] c) {
		int pa = 0;
		int pb = 0;
		int pc = 0;
		
		//작은 값을 저장
		while (pa < na && pb < nb)
			c[pc++] = (a[pa] <= b[pb]) ? a[pa++] : b[pb++];
		
		//a에 남아 있는 요소를 복사
		while (pa < na)
			c[pc++] = a[pa++];
		
		//b에 남아 있는 요소를 복사
		while (pb < nb)
			c[pc++] = b[pb++];
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		int[] a = {2,4,6,8,1,13};
		int[] b = {1,2,3,4,9,16,21};
		int[] c = new int[13];
		
		System.out.println("두 배열의 병합");
		
		merge(a, a.length, b, b.length, c);
		
		System.out.println("배열 a와 b를 병합하여 배열 c에 저장했습니다.");
		System.out.println("배열 a : ");
		for (int i=0; i<a.length; i++) {
			System.out.println("a[" + i + "] = " + a[i]);
		}
		
		System.out.println("배열 b : ");
		for (int i=0; i<b.length; i++) {
			System.out.println("b[" + i + "] = " + b[i]);
		}
		
		System.out.println("배열 c : ");
		for (int i=0; i<c.length; i++) {
			System.out.println("c[" + i + "] = " + c[i]);
		}
	}
}
```

### **병합 정렬(merge sort)** //합병 정렬이라고도 함
**병합 정렬 알고리즘**
- <u>배열의 요소 개수가 2개 이상인 경우</u>
1. 배열의 앞부분을 병합 정렬로 정렬
2. 배열의 뒷부분을 병합 정렬로 정렬
3. 배열의 앞부분과 뒷부분을 병합

시간 복잡도는 n log n
```JAVA
class MergeSort {
	static int[] buff; //작업용 배열
	
	//a[left] ~ a[right]를 재귀적으로 병합 정렬
	static void __mergeSort(int[] a, int left, int right) {
		if (left < right) {
			int i;
			int center = (left + right) / 2;
			int p = 0;
			int j = 0;
			int k = left;
			
			__mergeSort(a, left, center); //베열의 앞부분을 병합 정렬
			__mergeSort(a, center+1, right); //배열의 뒷부분을 병합 정렬
			
			//병합을 수행하는 코드
			for(i = left; i <= center; i++) 
				buff[p++] = a[i];
			
			while(i<=right && j < p)
				a[k++] = (buff[j] <= a[i]) ? buff[j++] : a[i++] ;
			
			while(j<p)
				a[k++] = buff[j++];
		}
	}
	
	//병합 정렬
	static void mergeSort(int[] a, int n) {
		//작업용 배열을 생성
		buff = new int[n];
		
		//배열 전체를 병합 정렬
		__mergeSort(a, 0, n-1);
		
		//작업용 배열을 해체
		buff=null;
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("병합 정렬");
		System.out.println("요솟수 : ");
		int nx = stdIn.nextInt();
		int[] x = new int[nx];
		
		for(int i=0; i<nx; i++) {
			System.out.println("x["+i+"] : ");
			x[i] = stdIn.nextInt();
		}
		
		mergeSort(x, nx);
		
		System.out.println("오름차순으로 정렬했습니다.");
		for(int i=0; i<nx; i++)
			System.out.println("x["+i+"]="+x[i]);
	}
}
```

### **Arrays.sort로 퀵 정렬과 병합 정렬하기**