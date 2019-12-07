# Week 10 

## 1. Brute Force 

* **문자열 검색(String Searching)은 어떤 문자열(text) 안에 다른 문자열(pattern)이 들어 있는지 조사하고 들어 있다면 그 위치를 찾아내는 것**
* **Brute Force법은 선형 검색을 확장한 알고리즘**
  * **단순법, 소박법**이라고도 함
  * 검사를 진행한 위치를 기억하지 못해 효율이 좋지 않음
    * 3단어의 패턴을 검사한다면 1단어 일치 확인, 1\~2단어 일치 확인, 1\~3단어 일치 확인을 수행(비효율적)

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
			return pText - pPattern; // 텍스트에 패턴이 여러 개 있는 경우 가장 앞쪽에 위치한 텍스트의 인덱스를 반환
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
  * java.lang패키지 String클래스에서 제공하는 오버로딩된 여러 indexOf를 사용하면 쉽게 문자열 검색 결과를 얻을 수 있음

## 2. KMP(Knuth-Morris-Pratt, 크누스 모리스 프랫)

* Brute Force와는 다르게 검사했던 위치 결과를 버리지 않고 이를 효율적으로 사용하는 알고리즘
  * 'apple' 패턴을 찾을 때 'app'까지 맞고 안맞다면 다음 'app'이 존재하는 곳으로 이동 후 'l'('app' 이후)부터 비교하는 방법
  * 하지만 몇 번째 문자부터 다시 검색을 시작할지 패턴을 이동시킬때마다 계산하므로 효율은 높지 않음
* **텍스트와 패턴이 겹치는 부분을 찾아내 검사를 다시 시작할 위치를 표로 구함**
  * 표는 접두사가 일치하는 최대 길이를 저장한 것, 검색 중 일치하지 않으면 이 표를 이용하여 점프
* KMP법에서 텍스트를 스캔하는 커서 pText는 다시 뒤로 돌아오지 않음
  * 하지만 Brute Force보다 복잡하고 Boyer-Moore보다 성능이 같거나 좋지 않아 실제로 많이 안쓰임

```java
// * text : abcabd, pattern : abd일 때 skip[abcabd.length()] = { -, 0, 0, 0, 1, 2 }

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
	
	if (pPattern == pattern.length()) // pText - pPattern을 반환(검색되면 0)
		return pText - pPattern;
	return -1; // 검색 실패
}
```


## 3. Boyer-Moore

* Brute-Force를 개선한 KMP보다 효율이 더 우수하여 실제 문자열 검색에 널리 사용하는 알고리즘
* R.S Boyer와 J.S.Moore가 만들어서 Boyer-Moore법이라 불림
  * 현재 대부분 워드프로세스 "검색" 기능에서 사용되고 있음
* 문자열 패턴과 문자열을 비교 시 문자열의 가장 뒷부분 위치를 비교, 다르면 일정 길이만큼 이동하여 비교를 계속하는 기법
  * **Good Suffix Method(착한 접미사 이동)와 Bad Character Method(나쁜문자 이동) 활용**
    * **Bad Character Shift 방식**
      * 본문의 문자열과 패턴이 불일치하도록 만드는 문자(나쁜문자) 발견, 패턴의 나쁜문자와 본문 문자열의 나쁜 문자 위치를 일치하도록 패턴 이동
      * 문자열에서 찾은 나쁜문자 위치보다 패턴에 존재하는 동일 문자위치가 뒤 쪽인 경우 활용 불가 => 착한 접미사 이동 사용
    * **Good Suffix Shift 방식**
      * 오른쪽에서 왼쪽으로 패턴과 문자열이 일치하는 것을 불일치 할때까지 찾음, 패턴에서 착한 접미사와 일치하는 문장을 이동
      * 착한접미사와 일치하는 문자열이 있으면 패턴에서 착한접미사와 일치하는 부분을 착한접미사에 일치하게 이동
      * 착한접미사와 일치하는 문자열이 없으면 착한 접미사의 문자열을 왼쪽부터 하나씩 줄여나가면서 반복 조사
        * 완전 불일치의 경우, 패턴의 길이만큼 이동한 후 비교

![bad-character](https://github.com/younggeun0/DataStructureStudy/blob/master/week12/younggeun0/img/bad-character.jpg?raw=true)

![good-suffix](https://github.com/younggeun0/DataStructureStudy/blob/master/week12/younggeun0/img/good-suffix.jpg?raw=true)

* **패턴의 마지막 문자부터 앞쪽으로 검사를 진행하면서 일치하지 않는 문자가 있으면 미리 준비한 표에 따라 패턴을 옮길 크기를 정함**
  * **텍스트안에서 패턴이 들어 있지 않으면 패턴의 길이만큼 건너 뜀**
* Boyer-Moore 알고리즘도 각각의 문자를 만났을 때 패턴을 옮길 크기를 저장할 표(건너뛰기 표)를 미리 만듦
* 패턴에 들어 있지 않은 문자를 만난 경우, 패턴을 옮길 크기는 n
* 패턴에 들어 있는 문자를 만난 경우, 마지막에 나오는 위치의 인덱스가 k이면 패턴을 옮길 크기는 n-k-1
  * 같은 문자가 패턴 안에서 중복해서 들어 있지 않다면 패턴을 옮길 크기는 n
* 아래는 하나의 배열만 사용해서 검사하는 Boyer-Moore 알고리즘(원래 두개의 배열로 문자열을 검사)

```java
static int bmMatch(String txt, String pattern) {
	int pText; 		// txt 커서
	int pPattern;	        // pattern 커서
	int txtLength = txt.length();
	int patternLength = pattern.length();
	
	int[] skip = new int[Character.MAX_VALUE+1]; // 건너뛰기 표
	
	// 건너뛰기 표 생성
	for(pText = 0; pText <= Character.MAX_VALUE; pText++) {
		skip[pText] = patternLength;
	}
	
	for(pText = 0; pText < patternLength-1; pText++) {
		skip[pattern.charAt(pText)] = patternLength - pText - 1; // pText == patternLength - 1
	}
	
	// 검색
	while(pText < txtLength) {
		pPattern = patternLength - 1; // pattern의 끝 문자에 주목
		
		while (txt.charAt(pText) == pattern.charAt(pPattern)) {
			if (pPattern == 0) {
				return pText; // 검색 성공
			}
			
			pPattern--;
			pText--;
		}
		pText += (skip[txt.charAt(pText)] > patternLength - pPattern) ?
				skip[txt.charAt(pText)] : patternLength - pPattern;
	}
	return -1;
}
```
