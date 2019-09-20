package chap05;
// int형 스택

public class IntStack {
	private int max;			// 스택의 용량
	private int ptr;			// 스택포인터
	private int[] stk;		// 스택의 본체

	//--- 실행시 예외：스택が空 ---//
	public class EmptyIntStackException extends RuntimeException {
		public EmptyIntStackException() { }
	}

	//--- 실행시 예외：스택が가득 참 ---//
	public class OverflowIntStackException extends RuntimeException {
		public OverflowIntStackException() { }
	}

	//--- 생성자 ---//
	public IntStack(int capacity) {
		ptr = 0;
		max = capacity;
		try {
			stk = new int[max];				// 스택본체용의 배열을 생성
		} catch (OutOfMemoryError e) {	// 생성할 수 없음
			max = 0;
		}
	}

	//--- 스택にx을푸시 ---//
	public int push(int x) throws OverflowIntStackException {
		if (ptr >= max)									// 스택은 가득 참
			throw new OverflowIntStackException();
		return stk[ptr++] = x;
	}

	//--- 스택에서 데이터를 팝 (頂上의 데이터를  꺼냄） ---//
	public int pop() throws EmptyIntStackException {
		if (ptr <= 0)										// 스택은 空
			throw new EmptyIntStackException();
		return stk[--ptr];
	}

	//--- 스택에서 데이터를 피크 (頂上의 데이터를 覗き見） ---//
	public int peek() throws EmptyIntStackException {
		if (ptr <= 0)										// 스택은 空
			throw new EmptyIntStackException();
		return stk[ptr - 1];
	}

	//--- 스택에서 x을探하여 인덱스 (찾지 못하면-1)을 return ---//
	public int indexOf(int x) {
		for (int i = ptr - 1; i >= 0; i--)		// 頂上쪽에서 선형 검색
			if (stk[i] == x)
				return i;		// 검색 성공
		return -1;				// 검색 실패
	}

	//--- 스택을空に하는  ---//
	public void clear() {
		ptr = 0;
	}

	//--- 스택의 용량을 return ---//
	public int capacity() {
		return max;
	}

	//--- 스택に積まれている데이터 수를 return ---//
	public int size() {
		return ptr;
	}

	//--- 스택은 空임.か ---//
	public boolean isEmpty() {
		return ptr <= 0;
	}

	//--- 스택은 가득 참임.か ---//
	public boolean isFull() {
		return ptr >= max;
	}

	//--- 스택내의 모든 데이터를 底→頂上의 차례로 나타냄 ---//
	public void dump() {
		if (ptr <= 0)
			System.out.println("스택은 空입니다.");
		else {
			for (int i = 0; i < ptr; i++)
				System.out.print(stk[i] + " ");
			System.out.println();
		}
	}
}
