package review;

class QueenB {
    // 각 열에 1개의 퀸을 배치하는 조합을 재귀적으로 나열
    static int[] pos = new int[8]; // 각 열의 퀸의 위치

    static void print() {//각 열의 퀸의 위치를 출력
        for(int i=0; i<8; i++) {
            System.out.printf("%2d", pos[i]);
        }
        System.out.println();
    }

    // i열에 퀸을 놓음
    static void set(int i) {
        for (int j=0; j<8; j++) { 
            pos[i] = j; // 퀸을 j행에 배치
            if (i == 7) // 모든 열에 배치
                print();
            else  
                set(i+1); // 다음 열에 퀸을 배치
        }
    }

    public static void main(String[] args) {
        set(0); // 0열에 퀸을 배치
    }
}