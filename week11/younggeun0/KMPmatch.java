package chap08;

import java.util.Scanner;

public class KMPmatch {
	static int kmpMatch(String txt, String pattern) {
		int pText = 1;    // 원문 커서
		int pPattern = 0; // 패턴 커서
		int[] skip = new int[pattern.length() + 1]; // 건너뛰기 표
		
		// 건너뛰기 표 만들기
		skip[pText] = 0;
		while(pText != pattern.length()) {
			if (pattern.charAt(pText) == pattern.charAt(pPattern))
				skip[++pText] = ++pPattern;
			else if (pPattern == 0) 
				skip[++pText] = pPattern;
			else
				pPattern = skip[pPattern];
		} 
		
		// 검색
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
		
		if (pPattern == pattern.length()) // pText - pPattern을 반환
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
		int idx = kmpMatch(str, pattern); // KMP
		
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
