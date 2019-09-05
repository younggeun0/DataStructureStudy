package chap03;
//자연스러운 정렬을 하려면 다음과 같은 방법으로 클래스를 정의합니다.

class A implements Comparable<A> {

	public int compareTo(A c) {
		//this가 c보다 크면 양의 값 반환
		//this가 c보다 작으면 음의 값 반환
		//this가 c와 같으면 0 반환
	}
	
	public boolean equals(Object c) {
		//this와 c가 같으면  true 반환
		//this와 c가 같지 않으면 false 반환
	}
}
