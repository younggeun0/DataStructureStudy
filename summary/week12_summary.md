# Week 12 리스트
## 선형 리스트(linear list)

### 선형 리스트란?
리스트 : 데이터를 순서대로 나열해 놓은 자료구조 (스택과 큐도 리스트 구조) <br/>
선형리스트(linear list) 또는 연결 리스트(linked list)는 가장 단순한 구조 <br/>

- 선형 리스트의 특징
    - 하나를 건너띄어 전달할 수 없음
    - 리스트의 데이터는 노드(node) 또는 요소(element)라고 함
    - 각각의 노드는 데이터와 다음 노드를 가리키는 포인터를 가짐
    - 처음 노드: head node, 끝 노드: tail node
    - 바로 앞 노드: predeccessor node, 바로 뒤 노드: successor node

![linkedList](https://steemitimages.com/DQmQRFECQN5yQNGRmwh2yEBHUGiHgVPNKmr8kbJRCYTT5jY/single-list.png)

### 배열로 선형 리스트 만들기
- 간단한 배열로 구현 가능
    - 다음 노드 꺼내기 : 1만큼 큰 인덱스에 접근
    - 노드의 삽입과 삭제 : 삽입하거나 삭제하는 경우 요소 다음의 모든 요소를 하나씩 뒤로 밀거나 앞으로 당겨야 함

- 선형리스트의 문제점
    1. 쌓이는 데이터의 크기를 미리 알아야 함
    2. 데이터의 삽입, 삭제 시 데이터를 모두 옮겨야 하므로 효율이 좋지 않음

## 포인터로 연결 리스트 만들기
![PointerLinkedList](https://www.tutorialride.com/images/data-structures/linked-list-ex.jpeg)

```JAVA
class Node<E> {
    E data; //데이터
    Node<E> next; //다음 노드를 가리키는 포인터
}
```

- 자기 참조형(self-referential)
    - 데이터용 필드인 data,
    - 자기 자신과 같은 클래스형의 인스턴스를 참조하기 위한 참조용 필드 next 로 이루어짐

* Node<E>는 제네릭으로 구현: 데이터 형 E는 임의의 클래스 형이 허용됨 = 사용하는 쪽에서 형을 자유롭게 지정 가능
* 필드 data형인 E는 참조형
* 클래스형 변수인 data는 데이터 그 자체가 아니라 데이터에 대한 **참조**임
* 다음 노드가 없는 '꼬리 노드'의 뒤쪽 포인터 값은 널(NULL)을 참조함

```JAVA
import java.util.Comparator;
// 연결 리스트 클래스

public class LinkedList<E> {
	// 노드
	class Node<E> {
		private E data;	// 데이터
		private Node<E> next; // 뒤쪽 포인터(다음 노드 참조)

		// 생성자
		Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node<E> head; // 머리 노드
	private Node<E> crnt; // 선택 노드
```
- 노드 클래스 Node<E>
    - data: 데이터를 가리킴
    - next: 다음 노드르 가리키는 포인터
    - 생성자: Node<E>의 생성자는 인수로 전달받은 data, next를 해당 필드에 설정

- 연결 리스트 클래스 LinkedList<E>
    - head: 머리 노드
    - crnt: 현재 선택한 노드를 가리킴. "검색" 노드 선택, "삭제" 등의 용도

```JAVA
	// 생성자
	public LinkedList() {
		head = crnt = null;
	}
```

- 생성자 LinkedList
    - 변수 head에 null을 대입 => 노드가 하나도 없는 비어 있는 연결 리스트가 생성됨
    - 변수 head가 머리 노드에 대한 참조일 뿐 머리 노드 그 자체는 아님!

- 연결 리스트가 비어 있는지 판단하는 방법
    - head == null

- 노드가 1개인 연결 리스트를 판단하는 방법
    - 노드가 1개 = head의 뒤쪽 포인터의 값이 null
    - head.next == null
    - 1개짜리 노드의 데이터: head.data

- 노드가 2개인 연결 리스트를 판단하는 방법
    - head.next.next == null
    - 2개짜리 노드의 2번째 노드의 데이터: head.next.data

- 꼬리 노드인지 판단하는 방법
    - p.next == null

```JAVA
	// 노드 검색
	public E search(E obj, Comparator<? super E> c) {
		Node<E> ptr = head; // 현재 스캔중인  노드, head로 초기화

		while (ptr != null) { //조건 1 판단
			if (c.compare(obj, ptr.data) == 0) { // 조건 2 판단, 검색 성공
				crnt = ptr;
				return ptr.data;
			}
			ptr = ptr.next; // 다음 노드를 선택
		}
		return null; // 검색 실패 시 null 반환
	}
```

- 검색을 수행하는 search 메서드
	- 종료 조건 1: 검색 조건을 만족하는 노드를 찾지 못하고 꼬리 노드를 지나가기 직전인 경우
	- 종료 조건 2: 검색 조건을 만족하는 노드를 찾은 경우
	- 이 메소드가 전달받는 매개 변수는
		- 첫 번째 매개변수 obj: 검색할 때 key가 되는 데이터를 넣어둔 object
		- 두 번째 매개변수 c: 첫 번째 매개변수와 연결 리스트의 개별 노드 안에 있는 데이터를 비교하기 위한 comparator, comparator c에 의해 obj와 선택한 노드의 데이터를 비교하여 그 결과가 0이면 검색 조건이 성립하는 것으로 봄

- 머리에 노드를 삽입하는 addFirst 메서드
	- 노드의 데이터는 obj가 되고, 뒤쪽 포인터가 가리키는 곳은 ptr(삽입 전의 머리 노드)
	- 생성한 노드를 참조하도록 head를 업데이트

- 꼬리에 노드를 삽입하는 addLast 메서드
	- 먼저 리스트가 비어있는지(head==null) 먼저 확인 하고 다음 작업 실행
	1. 리스트가 비어있는 경우: 머리에 노드 삽입
	2. 리스트가 비어있지 않은 경우: 리스트 꼬리에 마지막 노드 삽입

```JAVA
	// 머리에 노드 삽입
	public void addFirst(E obj) {
		Node<E> ptr = head; // 삽입 전의 머리 노드
		head = crnt = new Node<E>(obj, ptr); //생성 노드 참조하도록 업데이트
	}

	// 꼬리에 노드 삽입
	public void addLast(E obj) {
		if (head == null)	// 리스트가 비어 있으면 
			addFirst(obj); // 머리에 삽입
		else {
			Node<E> ptr = head;
			while (ptr.next != null) //while문 종료시, ptr은 꼬리 노드를 가리킴
				ptr = ptr.next;
			ptr.next = crnt = new Node<E>(obj, null);
		}
	}
```

- 머리 노드를 삭제하는 removeFirst 메서드
	- 리스트가 비어있지 않은 경우 (head!=null)에서만 삭제 실행

- 꼬리 노드를 삭제하는 removeLast 메서드
	1. 리스트에 노드가 1개만 있는 경우: 머리 노드를 삭제하는 작업. removeFirst메서드로 처리
	2. 2개이상 있는 경우: while문, 스캔 중인 노드의 앞쪽 노드를 참조하는 변수 pre가 추가된 것을 활용


```JAVA
	// 머리 노드 삭제
	public void removeFirst() {
		if (head != null) // 리스트가 비어 있지 않으면
			head = crnt = head.next;
	}

	// 꼬리 노드  삭제
	public void removeLast() {
		if (head != null) {
			if (head.next == null) // 노드가 하나만 있으면
				removeFirst(); // 머리 노드를 삭제
			else {
				Node<E> ptr = head;	// 스캔 중인  노드
				Node<E> pre = head;	// 스캔 중인  노드의 앞쪽 노드

				while (ptr.next != null) {
					pre = ptr;
					ptr = ptr.next;
				}
				pre.next = null; // pre는 삭제 후의 꼬리 노드
				crnt = pre;
			}
		}
	}
```

- 선택한 노드를 삭제하는 remove 메서드
	1. p가 머리 노드인 경우 : 머리노드를 삭제, removeFirst 메서드로 처리
	2. p가 머리 노드가 아닌 경우: ptr.next가 p와 같을때까지 반복, null을 만나면 p가 참조하는 노드가 없다는 것. 삭제 처리를 하지 않고 반환에 의해 메서드 실행을 마침. while문 종료된 후 ptr이 참조하는 곳은 삭제할 노드 D의 앞쪽 노드.<br/>
	삭제 대상 노드의 뒤쪽 포인터 p.next를 삭제 대상 노드의 앞 노드 뒤쪽 포인터에 대입하여 뒤 노드로 이어줌.<br/>
	참조하지 않는 노드의 메모리는 자동으로 해제 

```JAVA
	// 노드 p를 삭제
	public void remove(Node p) {
		if (head != null) {
			if (p == head) // p가 머리 노드면
				removeFirst(); // 머리 노드를 삭제
			else {
				Node<E> ptr = head;

				while (ptr.next != p) {
					ptr = ptr.next;
					if (ptr == null) return; // p가 리스트에 없습니다.  
				}
				ptr.next = p.next;
				crnt = ptr;
			}
		}
	}

	// 선택 노드를 삭제
	public void removeCurrentNode() {
		remove(crnt);
	}

	// 모든 노드를 삭제
	public void clear() {
		while (head != null) // 노드에 아무것도 없을 때까지
			removeFirst(); // 머리 노드를 삭제
		crnt = null;
	}

	// 선택 노드를 하나 뒤쪽으로 이동
	public boolean next() {
		if (crnt == null || crnt.next == null)
			return false; // 이동할 수 없음
		crnt = crnt.next;
		return true;
	}
```

- 선택 노드를 삭제하는 removeCurrentNode 메서드
	- remove 메서드에 crnt를 건네주고 처리를 맡김

- 모든 노드를 삭제하는 clear 메서드
	- 연결 리스트가 비어 있는 상태까지 머리요소의 삭제를 반복하여 모든 노드 삭제

- 선택 노드를 하나 뒤쪽으로 이동하는 next 메서드
	- 선택 노드를 하나 뒤쪽으로 이동하는 메서드. 선택노드가 이동하면 true 반환

```JAVA
	// 선택 노드를 출력
	public void printCurrentNode() {
		if (crnt == null)
			System.out.println("선택한 노드가 없습니다.");
		else
			System.out.println(crnt.data);
	}

	// 모든 노드를 출력
	public void dump() {
		Node<E> ptr = head;

		while (ptr != null) {
			System.out.println(ptr.data);
			ptr = ptr.next;
		}
	}
}
```

- 선택 노드를 표시하는 printCurrentNode 메서드
	- 선택 노드를 표시. crnt가 참조하는 노드의 데이터를 crnt.data를 표시

- 모든 노드를 표시하는 dump
	- 머리 노드부터 꼬리 노드까지 스캔하면서 ptr.data 표시
