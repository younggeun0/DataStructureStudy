package chap07;

public class IntSet { // int형 집합
	private int max; // 집합의 최대 개수
	private int num; //집합의 요소 개수
	private int[] set; // 집합 본체
	
	public IntSet(int capacity) {
		num = 0;
		max = capacity;
		
		try {
			set = new int[max]; // 집합 배열 생성
		} catch (OutOfMemoryError e) { // 배열 생성 실패
			max = 0;
		}
	}
	
	public int capacity() { // 집합의 최대 개수
		return max;
	}
	
	public int size() { // 집합의 요소 개수
		return num;
	}
	
	public int indexOf(int n) { // 집합에서 n을 검색(idx반환)
		for(int i=0; i<num; i++) {
			if (set[i] == n) {
				return i; // 검색 성공
			}
		}
		return -1; // 검색 실패
	}
	
	public boolean contains(int n) {
		return (indexOf(n) != -1) ? true : false;
	}
	
	public boolean add(int n) {
		if (num >= max || contains(n) == true) {
			// 가득 찼거나 이미 n이 존재하면
			return false;
		} else {
			// 가장 마지막 자리에 추가
			set[num++] = n;
			return true;
		}
	}
	
	public boolean remove(int n) { // 집합에서 n을 삭제
		int idx; // n이 저장된 요소의 인덱스
		
		if (num <= 0 || (idx = indexOf(n)) == -1) {
			// 비어있거나 n이 존재하지 않으면
			return false;
		} else {
			// 마지막 요소를 삭제한 곳으로 옮김
			set[idx] = set[--num];
			return true;
		}
	}
	
	public void copyTo(IntSet s) { // 집합 s에 복사
		int n = (s.max < num) ? s.max : num; // 복사할 요소 개수
		for (int i=0; i<n; i++) {
			s.set[i] = set[i];
		}
		s.num = n;
	}
	
	public void copyFrom(IntSet s) { // 집합 s를 복사
		int n = (max < s.num)? max : s.num; // 복사할 요소 개수
		for (int i=0; i<n; i++) {
			set[i] = s.set[i];
		}
		num = n;
	}
	
	public boolean equalTo(IntSet s) { // 집합 s와 같은지 확인
		if (num != s.num) { // 요소 개수가 같지 않으면
			return false; // 집합도 같지 않음
		}
		
		for (int i=0; i<num; i++) {
			int j = 0;
			for(; j<s.num; j++) {
				if (set[i] == s.set[j])
					break;
			}
			if (j == s.num) // set[i]는 s에 포함되지 않음
				return false;
		}
		
		return true;
	}
	
	public void unionOf(IntSet s1, IntSet s2) { // 집합 s1과 s2의 합집합을 복사
		copyFrom(s1); // 집합 s1을 복사
		for (int i=0; i<s2.num; i++) { // 집합 s2의 요소를 추가
			add(s2.set[i]);
		}
	}
	
	// "{a b c}"형식의 문자열 표현으로 바꿈
	public String toString() {
		StringBuffer temp = new StringBuffer("{ ");
		for(int i=0; i<num; i++) {
			temp.append(set[i] + " ");
		}
		temp.append(" } ");
		return temp.toString();
	}
	
}
