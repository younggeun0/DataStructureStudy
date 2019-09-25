# Week 10

## 1. Brute Force

* **문자열 검색(String Searching)**은 어떤 문자열 안에 다른 문자열이 들어 있는지 조사하고 들어 있다면 그 위치를 찾아내는 것
* **Brute Force법은 선형 검색을 확장한 알고리즘**
  * 단순법, 소박법이라고도 함
  * 검사를 진행한 위치를 기억하지 못해 효율이 좋지 않음

```java
public class BFmatch {
	static int bfMatch(String txt, String pattern) {
		int pText = 0;  // 원문 커서
		int pPattern = 0; // 패턴 커서
		
		while(pText != txt.length() && pPattern != pattern.length()) {
			if (txt.charAt(pText) == pattern.charAt(pPattern)) {
				pText++;
				pPattern++;
			} else {
				pText = pText - pPattern + 1;
				pPattern = 0;
			}
		}
		
		if (pPattern == pattern.length()) // 검색 성공
			return pText - pPattern;
		return -1; // 검색 실패
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("String : ");
		String str = sc.next(); // 원문 문자열
		
		System.out.print("Pattern : ");
		String pattern = sc.next(); // 패턴용 문자열
		
		sc.close();
		int idx = bfMatch(str, pattern); // brute-force
		
		if (idx == -1) 
			System.out.println("입력한 패턴이 없음");
		else {
			int len = 0;
			for(int i=0; i<idx; i++) {
				len += str.substring(i, i+1).getBytes().length;
			}
			len += pattern.length();
			
			System.out.println((idx+1)+"번째 문자부터 일치");
			System.out.println("원문 : "+str);
			System.out.printf(String.format("패턴 : %%%ds\n", len), pattern);
		}
	}
}
```

* [String.indexOf](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html)
  * java.lang패키지 String클래스에서 제공하는 오버로딩된 여러 indexOf를 사용하면 쉽게 문자열 검색 결과를 얻을 수 있음

## 2. KMP

* Brute Force와는 다르게 검사했던 위치 결과를 버리지 않고 이를 효율적으로 사용하는 알고리즘
* **텍스트와 패턴이 겹치는 부분을 찾아내 검사를 다시 시작할 위치를 구함**
  * 하지만 몇 번째 문자부터 다시 검색을 시작할지 패턴을 이동시킬때마다 계산하므로 효율은 높지 않음
    * 그래서 **몇 번째 문자부터 다시 검색할지에 대한 값을 미리 표로 만들어서 해결**
    * 표를 작성할 때는 패턴 안에 중복되는 문자의 나열을 먼저 찾음, 이 과정에서 KMP법을 사용
* KMP법에서 텍스트를 스캔하는 커서 pText는 다시 뒤로 돌아오지 않음
  * 하지만 Brute Force보다 복잡하고 Boyer-Moore보다 성능이 같거나 좋지 않아 실제로 많이 안쓰임

```java
static int kmpMatch(String txt, String pattern) {
	int pText = 1;    // 원문 커서
	int pPattern = 0; // 패턴 커서
	int[] skip = new int[pattern.length() + 1]; // 건너뛰기 표
	
	// 건너뛰기 표 만들기 //////////
	skip[pText] = 0;
	while(pText != pattern.length()) {
		if (pattern.charAt(pText) == pattern.charAt(pPattern))
			skip[++pText] = ++pPattern;
		else if (pPattern == 0)
			skip[++pText] = pPattern;
		else
			pPattern = skip[pPattern];
	} 
    ////////////////////////////////
	
	// 검색 ////////////////////////
	pText = pPattern = 0;
	while (pText != txt.length() && pPattern != pattern.length()) {
		if (txt.charAt(pText) == pattern.charAt(pPattern)) {
			pText++;
			pPattern++;
		} else if (pPattern == 0) {
			pText++;
		} else {
			pPattern = skip[pPattern];
		}
	}
    ////////////////////////////////
	
	if (pPattern == pattern.length()) // pText - pPattern을 반환
		return pText - pPattern;
	return -1; // 검색 실패
}
```


## 3. Boyer-Moore