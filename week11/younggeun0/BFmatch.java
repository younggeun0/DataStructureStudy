package chap08;

import java.util.Scanner;

public class BFmatch {
	
	static int bfMatch(String str, String pattern) {
		int pString = 0;  // 원문 커서
		int pPattern = 0; // 패턴 커서
		
		while(pString != str.length() && pPattern != pattern.length()) {
			if (str.charAt(pString) == pattern.charAt(pPattern)) {
				pString++;
				pPattern++;
			} else {
				pString = pString - pPattern + 1;
				pPattern = 0;
			}
		}
		
		if (pPattern == pattern.length()) // 검색 성공
			return pString - pPattern;
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
