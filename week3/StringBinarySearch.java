package chap03;

import java.util.Arrays;
import java.util.Scanner;

public class StringBinarySearch {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String[] arr = {
			"abstract", "assert", "boolean", "break", "byte",
			"case", "catch", "char", "class", "const",
			"continue", "default", "do", "double", "else",
			"enum", "extends", "final", "finally", "float",
			"for", "goto", "if", "implements", "import",
			"instanceof", "int", "interface", "long", "native",
			"new", "package", "private", "protected", "public",
			"return", "short", "static", "strictfp", "super",
			"switch", "synchronized", "this", "throw", "throws",
			"transient", "try", "void", "volatile", "while"
		};
		
		System.out.print("원하는 키워드를 입력하세요 : ");
		String key = sc.next();
		
		int idx = Arrays.binarySearch(arr, key);
		
		if (idx < 0) {
			System.out.println("해당 키워드 없음");
		} else {
			System.out.println("해당 키워드는 arr["+idx+"]에 있음");
		}
	}
}
