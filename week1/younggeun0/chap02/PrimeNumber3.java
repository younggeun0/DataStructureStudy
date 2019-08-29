package chap02;

public class PrimeNumber3 {

	public static void main(String[] args) {
		int counter = 0; // °ö¼À°ú ³ª´°¼À È½¼ö
		int ptr = 0; // Ã£Àº ¼Ò¼öÀÇ °³¼ö
		int[] prime = new int[500];
		
		prime[ptr++] = 2;
		prime[ptr++] = 3; // 2¿Í 3Àº ¼Ò¼ö
		
		for (int n=5; n<=1000; n += 2) { // ´ë»óÀº È¦¼ö¸¸
			boolean flag = false;
			for(int i=1; prime[i]*prime[i] <= n; i++) { // ¼Ò¼öÀÇÁ¦°ö º¸´Ù ÀÛÀ»¶§ ¹Ýº¹
				counter += 2; // °öÇÏ°í ³ª´©´Ï±î 2
				if (n % prime[i] == 0) { // ³ª´² ¶³¾îÁö¸é ¼Ò¼ö°¡ ¾Æ´Ô
					flag = true;
					break;
				}
			}
			if (!flag) { // ¸¶Áö¸·±îÁö ³ª´²¶³¾îÁöÁö ¾ÊÀ½
				prime[ptr++] = n; // ¼Ò¼ö´Â ¹è¿­¿¡ ÀúÀå
				counter++;
			}
		}
		
		for(int i=0; i<ptr; i++) {
			System.out.println(prime[i]);
		}
		
		System.out.println("°ö¼À°ú ³ª´°¼À È½¼ö : "+counter); // 3774
	}
}
