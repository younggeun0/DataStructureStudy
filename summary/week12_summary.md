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
		Node<E> ptr = head; // 현재 스캔중인  노드

		while (ptr != null) {
			if (c.compare(obj, ptr.data) == 0) { // 검색 성공
				crnt = ptr;
				return ptr.data;
			}
			ptr = ptr.next; // 다음 노드를 선택
		}
		return null; // 검색 실패
	}

	// 머리에 노드 삽입
	public void addFirst(E obj) {
		Node<E> ptr = head; // 삽입 전의 머리 노드
		head = crnt = new Node<E>(obj, ptr);
	}

	// 꼬리에 노드 삽입
	public void addLast(E obj) {
		if (head == null)	// 리스트가 비어 있으면 
			addFirst(obj); // 머리에 삽입
		else {
			Node<E> ptr = head;
			while (ptr.next != null)
				ptr = ptr.next;
			ptr.next = crnt = new Node<E>(obj, null);
		}
	}

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