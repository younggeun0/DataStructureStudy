package minj0i;
// int형 집합

public class IntSet {
	private int max;
	private int num;
	private int[] set;

	// 생성자
	public IntSet(int capacity) {
		num = 0;
		max = capacity;
		try {
			set = new int[max];
		}
		catch (OutOfMemoryError e) {
			max = 0;
		}
	}

	// 집합의 최대 개수
	public int capacity() {
		return max;
	}

	// 집합의 요소 개수
	public int size() {
		return num;
	}

	// 집합에서 n을 검색합니다(index 반환).
	public int indexOf(int n) {
		for (int i = 0; i < num; i++)
			if (set[i] == n)
				return i;
		return -1;
	}

	// 집합에 n이 있는지 없는지 확인합니다. 
	public boolean contains(int n) {
		return (indexOf(n) != -1) ? true : false;
	}

	// 집합에 n을 추가합니다.
	public boolean add(int n) {
		if (num >= max || contains(n) == true)
			return false;
		else {
			set[num++] = n;
			return true;
		}
	}

	// 집합에서 n을 삭제합니다.
	public boolean remove(int n) {
		int idx;

		if (num <= 0 || (idx = indexOf(n)) == -1)
			return false;
		else {
			set[idx] = set[--num];
			return true;
		}
	}

	// 집합 s에 복사합니다. 
	public void copyTo(IntSet s)	{
		int n = (s.max < num) ? s.max : num;
		for (int i = 0; i < n; i++)
			s.set[i] = set[i];
		s.num = n;
	}

	// 집합 s를 복사합니다.
	public void copyFrom(IntSet s)	{
		int n = (max < s.num) ? max : s.num;
		for (int i = 0; i < n; i++)
			set[i] = s.set[i];
		num = n;
	}

	// 집합 s와 같은지 확인합니다. 
	public boolean equalTo(IntSet s)	{
		if (num != s.num)
			return false;

		for (int i = 0; i < num; i++) {
			int j = 0;
			for ( ; j < s.num; j++)
				if (set[i] == s.set[j])
					break;
			if (j == s.num)
				return false;
		}
		return true;
	}

	// 집합 s1과 s2의 합집합을 복사합니다.
	public void unionOf(IntSet s1, IntSet s2) {
		copyFrom(s1);
		for (int i = 0; i < s2.num; i++)
			add(s2.set[i]);
	}

	// "{ a b c }"형식의 문자열로 표현을 바꿉니다. 
	public String toString() {
		StringBuffer temp = new StringBuffer("{ ");
		for (int i = 0; i < num; i++)
			temp.append(set[i] + " ");
		temp.append("}");
		return temp.toString();
	}
}