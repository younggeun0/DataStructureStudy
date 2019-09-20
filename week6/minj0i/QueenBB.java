package chap05;
//각 행, 열에 1개의 퀸을 배치하는 조합을 재귀적으로 나열합니다.
//8룩(Look)
class QueenBB {
	static boolean[] flag = new boolean[8];
	static int[] pos = new int[8];
	
	static void print() {
		for(int i=0; i<8; i++)
			System.out.printf("%2d", pos[i]);
		System.out.println();
	}
	
	static void set(int i) {
		for(int j=0; j<8; j++) {
			if(flag[j]==false) {
				pos[i] = j;
				if(i==7)
					print();
				else {
					flag[j] = true;
					set(i+1);
					flag[j] = false;
				}//end else
			}//end if
		}//end for
	}//end set
	
	public static void main(String[] args) {
		set(0);
	}//main

}//class
