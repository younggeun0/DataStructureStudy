package chap02;

import java.util.Arrays;

public class CardConvRev {

	static int cardConvR(int x, int r, char[] d) {
		int digits = 0;
		String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		do {
			d[digits++] = dchar.charAt(x % r);
			x /= r;
		} while(x != 0);
		
		return digits;
	}
	
	public static void main(String[] args) {
		
		char[] d = new char[6];
		int x = 59;
		int r = 2;
		
		cardConvR(x, r, d);
		
		System.out.println(Arrays.toString(d)); // 110111
	}
}
