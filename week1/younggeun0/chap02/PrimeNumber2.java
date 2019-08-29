package chap02;

public class PrimeNumber2 {

	public static void main(String[] args) {
		int counter = 0; // 나눈 횟수
		int ptr = 0; // 찾은 소수의 값(+ prime 배열의 인덱스)
		int[] prime = new int[500]; // 소수를 저장하는 배열
		
		prime[ptr++] = 2; // 2는 소수
		
		for(int n=3; n<=1000; n+=2) { // 홀수만 대상
			int i;
			for(i =1; i<ptr; i++) {
				counter++;
				if(n%prime[i] == 0) { // 찾은 소수와 비교
					break;
				}
			}
			if (ptr == i) { // 마지막까지 나눠떨어지지 않으면 
				prime[ptr++] = n; // 소수이므로 배열에 저장, 반복
			}
		}
		
		for(int i=0; i<ptr; i++) {
			System.out.println(prime[i]); // 찾은 소수 출력
		}
		System.out.println("나눗셈을 수행한 수 : " + counter);
	}
}
