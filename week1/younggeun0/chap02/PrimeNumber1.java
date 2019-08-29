package chap02;

public class PrimeNumber1 {
	public static void main(String[] args) {
		int counter = 0;
		
		for(int n=2; n<=1000; n++) {
			int i;
			for (i=2; i<n; i++) {
				counter++;
				if (n%i == 0) // 나누어 떨어지면 소수가 아님
					break;
			}
			if (n==i) // i가 n-1까지 나누어 떨어지지 않으면 n값이 됨(마지막까지 나누어떨어지지 않음)
				System.out.println(n); // n은 소수
		}
		System.out.println("나눗셈을 수행한 횟수 : "+counter);
	}
}
