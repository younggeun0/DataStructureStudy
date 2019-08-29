package chap01;

public class Max3m {

	static int max3(int a, int b, int c) {
		int max = a;
		
		if (b > max)
			max = b;
		if (c > max)
			max = c;
		
		return max;
	}
	
	public static void main(String[] args) {
		System.out.println("max3(3,2,1) = "+max3(3,2,1));
		System.out.println("max3(3,5,4) = "+max3(3,5,4));
		System.out.println("max3(3,21,22) = "+max3(3,21,22));
		System.out.println("max3(123,21,9) = "+max3(123,21,9));
	}
}
