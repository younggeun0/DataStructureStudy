package chap08;

import java.util.Scanner;

public class BMMatch {
	
	static int bmMatch(String txt, String pattern) {
		int pText; 		// txt 커서
		int pPattern;	// pattern 커서
		int txtLength = txt.length();
		int patternLength = pattern.length();
		
		int[] skip = new int[Character.MAX_VALUE+1]; // 건너뛰기 표
		
		// 건너뛰기 표 생성
		for(pText = 0; pText <= Character.MAX_VALUE; pText++) {
			skip[pText] = patternLength; // 일단 패턴의 길이만큼 건너뛰기 표 값을 초기화
		}
		for(pText = 0; pText < patternLength-1; pText++) {
			// 패턴이 들어있는 문자를 만난 경우 
			// 마지막에 나오는 위치의 인덱스가 pText이면 패턴을 옮길 크기는 패턴의 길이 - pText - 1
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
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("String : ");
		String str = sc.next(); // 원문 문자열
		
		System.out.print("Pattern : ");
		String pattern = sc.next(); // 패턴용 문자열
		
		sc.close();
		int idx = bmMatch(str, pattern); // brute-force
		
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
