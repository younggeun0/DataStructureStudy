# Week 11

## 1. Brute Force 

* **문자열 검색(String Searching)은 어떤 문자열(text) 안에 다른 문자열(pattern)이 들어 있는지 조사하고 들어 있다면 그 위치를 찾아내는 것**
* **Brute Force법은 선형 검색을 확장한 알고리즘**
  * **단순법, 소박법**이라고도 함
  * 검사를 진행한 위치를 기억하지 못해 효율이 좋지 않음

```java
public class BFmatch {
	static int bfMatch(String txt, String pattern) {
		int pText = 0;    // 원문 커서
		int pPattern = 0; // 패턴 커서
		
		while(pText != txt.length() && pPattern != pattern.length()) {
			if (txt.charAt(pText) == pattern.charAt(pPattern)) {
				pText++;
				pPattern++;
			} else {
				pText = pText - pPattern + 1; // 비교했던 패턴만큼 롤백 후 오른쪽 이동
				pPattern = 0;
			}
		}
		
		// while문에 끝났을 때 패턴 커서가 가리키는 위치+1(탐색이 끝나 ++된 후)과 패턴의 길이가 동일하다면
		// 동일한 문자열을 찾았다는 의미
		if (pPattern == pattern.length()) // 검색 성공
			// 탐색이 끝난 후 pText에서 pPattern일 빼면 패턴이 시작되는 첫 번째 인덱스가 
			return pText - pPattern;
			// 텍스트에 패턴이 여러 개 있는 경우 가장 앞쪽에 위치한 텍스트의 인덱스를 반환
		return -1; // 검색 실패
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("String : ");
		String str = sc.next();     // 원문 문자열
		
		System.out.print("Pattern : ");
		String pattern = sc.next(); // 패턴용 문자열
		
		sc.close();
		int idx = bfMatch(str, pattern); // brute-force
		
		if (idx == -1) 
			System.out.println("입력한 패턴이 없음");
		else {
			// 일치하는 문자 바로 앞까지의 길이를 구함(패턴 앞에 공백 주려고) - 생략가능한 
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
  * java.lang패키지 String클래스에서 제공하는 오버로딩된 여러 **indexOf 메서드**를 사용하면 쉽게 문자열 검색 결과를 얻을 수 있음

## 2. KMP(Knuth-Morris-Pratt, 크누스 모리스 프랫)

* Brute Force와는 다르게 검사했던 위치 결과를 버리지 않고 이를 효율적으로 사용하는 알고리즘
  * 'apple' 패턴을 찾을 때 'app'까지 맞고 안맞다면 다음 'app'이 존재하는 곳으로 이동 후 'l'('app' 이후)부터 비교하는 방법
  * 하지만 몇 번째 문자부터 다시 검색을 시작할지 패턴을 이동시킬때마다 계산하므로 효율은 높지 않음
* **텍스트와 패턴이 겹치는 부분을 찾아내 검사를 다시 시작할 위치를 표로 구함**
  * **패턴 안에서 중복되는 문자의 나열을 먼저 찾아 건너뛰는 표를 만들어 사용**
    * 예) text : abcabd, pattern : abd일 때 skip[abcabd.length()] = { -, 0, 0, 0, 1, 2 }
  * **건너 뛰기 표에 값은 패턴이 비교를 시작하는 인덱스**
* KMP법에서 텍스트를 스캔하는 커서 pText는 다시 뒤로 돌아오지 않음
  * 하지만 Brute Force보다 복잡하고 Boyer-Moore보다 성능이 같거나 좋지 않아 실제로 많이 안쓰임

```java
static int kmpMatch(String txt, String pattern) {
	int pText = 1;    // 원문 커서
	int pPattern = 0; // 패턴 커서
	int[] skip = new int[pattern.length() + 1]; // 건너뛰기 표
	
	// 패턴을 이용하여 건너뛰기 표 만들기 //////////
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
	
	if (pPattern == pattern.length()) // pText - pPattern을 반환(검색되면 0)
		return pText - pPattern;
	return -1; // 검색 실패
}
```


## 3. Boyer-Moore

* Brute-Force를 개선한 KMP보다 효율이 더 우수하여 실제 문자열 검색에 널리 사용하는 알고리즘
* **R.S Boyer와 J.S.Moore가 만들어서 Boyer-Moore법이라 불림**
  * 현재 대부분 워드프로세스 "검색" 기능에서 사용되고 있음
  * Boyer-Moore 알고리즘도 각각의 문자를 만났을 때 패턴을 옮길 크기를 저장할 표(건너뛰기 표)를 미리 만듦
* **패턴의 마지막 문자부터 앞쪽으로 검사를 진행하면서 일치하지 않는 문자가 있으면 미리 준비한 표에 따라 패턴을 옮길 크기를 정함**
1. **패턴에 들어 있지 않은 문자를 만난 경우, 패턴을 옮길 크기는 n**
2. **패턴에 들어 있는 문자를 만난 경우, 마지막에 나오는 위치의 인덱스가 k이면 패턴을 옮길 크기는 n-k-1**
* 아래는 하나의 배열만 사용해서 검사하는 간단하게 구현된 Boyer-Moore 알고리즘(원래 두개의 배열로 문자열을 검사)
  * * Boyer-Moore법을 사용할 때 패턴에 존재할 수 있는 모든 문자열의 옮길 크기를 계싼하고 저장해야 하기 떄문에 건너뛰기 표의 요소 개수는 Character.MAX_Value+1

```java
static int bmMatch(String txt, String pattern) {
	int pText; 		// txt 커서
	int pPattern;	        // pattern 커서
	int txtLength = txt.length();
	int patternLength = pattern.length();
	
	int[] skip = new int[Character.MAX_VALUE+1]; // 건너뛰기 표
	
	// 건너뛰기 표 생성(일단 패턴의 길이만큼 건너뛰기 표 값을 초기화)
	for(pText = 0; pText <= Character.MAX_VALUE; pText++) {
		skip[pText] = patternLength; 
	}
	
	for(pText = 0; pText < patternLength-1; pText++) {
		// 패턴이 들어있는 문자를 만난 경우 
		// 마지막에 나오는 위치의 인덱스가 pText이면 패턴을 옮길 크기는 패턴의 길이 - pText - 1
		skip[pattern.charAt(pText)] = patternLength - pText - 1; // pText == patternLength - 1
	}
	
	// 검색
	while(pText < txtLength) {
		pPattern = patternLength - 1; // pattern의 끝 문자에 주목
		
		while (txt.charAt(pText) == pattern.charAt(pPattern)) { // 패턴 끝문자가 같은지 확인
			if (pPattern == 0) {
				return pText; // 검색 성공
			}
			
			pPattern--;
			pText--;
		}
		// 패턴 끝비교가 다를 경우 건너뛰기 표를 사용해서 비교할 텍스트 커서 이동
		pText += (skip[txt.charAt(pText)] > patternLength - pPattern) ?
				skip[txt.charAt(pText)] : patternLength - pPattern;
	}
	return -1;
}
```
