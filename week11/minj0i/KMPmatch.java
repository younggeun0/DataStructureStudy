package minj0i;
import java.util.Scanner;
// KMP법에 의한 문자열 검색 - 브루트-포스법보다는 복잡하고, Boyer-Moore법과는 성능이 같거나 좋지 않아 실제 프로그램에서는 거의 사용하지 않음

class KMPmatch {
	// KMP법에 의한 문자열 검색
	static int kmpMatch(String txt, String pat) {
		int pt = 1;	 // txt 커서
		int pp = 0; // pat 커서
		int[] skip = new int[pat.length() + 1]; // 건너뛰기 표

		// 건너뛰기 표를 만듭니다.
		skip[pt] = 0;
		while (pt != pat.length()) {
			if (pat.charAt(pt) == pat.charAt(pp))
				skip[++pt] = ++pp;
			else if (pp == 0)
				skip[++pt] = pp;
			else
				pp = skip[pp];
		}

		// 검색
		pt = pp = 0;
		while (pt != txt.length() && pp != pat.length()) {
			if (txt.charAt(pt) == pat.charAt(pp)) {
				pt++;
				pp++;
			} else if (pp == 0)
				pt++;
			else
				pp = skip[pp];
		}

		if (pp == pat.length()) // pt - pp를 반환합니다.
			return pt - pp;
		return -1; // 검색 실패
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("텍스트：");
		String s1 = stdIn.next();  // 텍스트용 문자열 

		System.out.print("패턴：");
		String s2 = stdIn.next(); // 패턴용 문자열 

		int idx = kmpMatch(s1, s2);	// 문자열 s1에서 문자열 s2를 브루트-포스법으로 검색

		if (idx == -1)
			System.out.println("텍스트에 패턴이 없습니다.");
		else {
			int len = 0;
			for (int i = 0; i < idx; i++)
				len += s1.substring(i, i + 1).getBytes().length;
			len += s2.length();

			System.out.println((idx + 1) + "번째 문자와 일치합니다.");
			System.out.println("텍스트：" + s1);
			System.out.printf(String.format("패턴：%%%ds\n", len), s2);
		}
	}
}
