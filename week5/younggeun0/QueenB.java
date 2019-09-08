package chap05;

public class QueenB {
// 각 열에 1개의 퀸을 배치하는 조합을 재귀적으로 나열
	
	static int[] pos = new int[8]; // 각 열의 퀸 위치
	
	// 각 열의 퀸의 위치를 출력
	static void print() {
		for(int i=0; i<8; i++) {
			System.out.printf("%2d",pos[i]);
		}
		System.out.println();
	}
	
	static void set(int i) { // i열에 퀸을 놓음
		for(int j=0; j<8; j++) {
			pos[i] = j;	// 퀸을 j행에 배치
			if (i == 7) {// 모든 열에 배치되면
				print(); // 출력
			} else {
				set(i+1); // 다음 열에 퀸 배치
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		set(0); // 0 열에 퀸을 배치
	}

}
