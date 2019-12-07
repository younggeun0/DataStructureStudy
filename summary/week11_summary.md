# 커서로 연결리스트 만들기
 * 노드를 배열 안의 요소에 저장하고 그 요소를 이용해 연결 리스트를 구현하는 방법 
    * 앞에 연결 리스트의 장점</br>
 </t>: 노드의 삽입, 삭제를 데이터 이동 없이 수행한다.
    * 앞에 연결 리스트의 단점</br>
 </t>: 삽입, 삭제를 수행할 때마다 노드용 객체를 위한 메모리 영역을 만들고 해제하는 과정이 필요함.</br>
 </t>(메모리 영역을 만들고 해제하는 데 필요한 비용은 무시할 수 없음.)

* 이때 프로그램 실행 중에 데이터 수가 크게 바뀌지 않고 데이터 수의 최댓값을 미리 알 수 있다는 가정 하에 '커서'를 사용하여 연결 리스트를 만듦.</br>
</br>
</t>- 커서에 해당하는 값은 다음 노드에 대한 포인터가 아니라 다음 노드가 들어 있는 요소의 인덱스에 대한 값. </br>
</t>(즉, 커서는 포인터 역할을 하는 인덱스를 뜻 함.)</br>
</br>
</t>- 커서를 사용하는 연결 리스트는 데이터 수의 최댓값을 미리 계산하여 모든 노드를 저장하기에 충분한 크기의 배열을 만들어야 함.</br>
    * 문제점</br>
    </t>: 삭제를 여러 번 반복하면 배열 안은 빈 레코드 투성이가 됨.</br>
    </t>: 삭제한 레코드가 하나라면 그 인덱스를 어떤 변수에 넣어두고 관리하는 것으로 레코드를 쉽게 재사용 가능.</br>
    </t>(but, 실제로는 여러 레코드를 삭제하므로 단순한 문제가 아님.)

# 원형 이중 연결 리스트
* 연결 리스트의 꼬리 노드가 머리 노드를 가리키는 리스트</br>
</t>: 연결 리스트의 가장 큰 단점 -> 다음 노드는 찾기 쉽지만 앞쪽의 노드는 찾을 수 없음.</br> 
</t>: 원형 리스트와 연결 리스트의 차이점은 꼬리 노드의 다음 노드를 갈키는 포인터가 null이 아니라 머리 노드의 포인터 값임.</br>
</t>: 원형 리스트는 연결 리스트에서 사용했던 것과 같은 자료형을 사용할 수 있음.</br>

* 노드 클래스 Node< E >
```kotlin
class Node<E> {
		private E data;			// 데이터
		private Node<E> prev;	// 앞쪽 포인터 (앞쪽 노드에 대한 참조)
		private Node<E> next;	// 뒤쪽 포인터 (다음 노드에 대한 참조)

		/**
        * 데이터 data가 null이고 앞쪽 포인터와 뒤쪽 포인터가 모두 this 노드를 생성함.
        * 자기 자신의 노드가 앞쪽 노드이면서 동시에 다음 노드가 됨.
        */
		Node() {
			data = null;
			prev = next = this;
		}
        
        /**
        * 데이터 data가 obj이고 앞쪽 포인터가 prev, 뒤쪽 포인터가 next인 노드를 생성함.
        */
		Node(E obj, Node<E> prev, Node<E> next) {
			data = obj;
			this.prev = prev;
			this.next = next;
		}
	}
```

* 원형 이중 연결 리스트 클래스 DblLinkedList< E >
```kotlin
    private Node<E> head;					// 머리 노드 (더미 노드)
    private Node<E> crnt;					// 선택 노드
```

* 생성자 DblLinkedList</br>
</t>: 비어 있는 원형 이중 연결 릿트를 생성함.</br>
</t>: 이 노드는 노드의 삽입과 삭제 처리를 원활하게 하도록 리스트의 머리에 계속 존재하는 더미 노드임.</br>
</t>: 노드를 생성할 때 new Node< E >()에 생성자를 호출하므로 더미 노드의 prev와 next는 자기 자신의 노드를 가리키도록 초기화 됨.

```kotlin
    public DblLinkedList() {
	    head = crnt = new Node<E>();		// 더미 노드를 생성
    }
```
* 리스트가 비어 있는가를 조사하는 isEmpty 메서드</br>
</t>: 리스트가 비어있는가(더미 노드만 있는가)를 조사하는 메서드</br>
</t>: 더미 노드의 뒤쪽 포인터 head.next가 더미 노드은 head를 가리키면 리스트는 비어 있는 것</br>
</t>: 리스트가 비어 있으면 true를, 그렇지 않으면 false를 반환함</br>
```kotlin
    public boolean isEmpty() {
	    return head.next == head;
    }
```
* 노드를 검색하는 search메서드</br>
</t>: 노드를 연결 검색하는 메서드</br>
</t>: 머리노드부터 시작해서 뒤쪽 포인터를 차례로 찾아가며 스캔하는 과정은 연결 리스트 클래스와 같음.</br>
</t>(단, 실질적인 머리 노드가 더미 노드의 다음 노드이므로 거색을 시작하는 곳이 다름)</br>
```kotlin
	public E search(E obj, Comparator<? super E> c) {
		Node<E> ptr = head.next;			// 현재 스캔 중인  노드

		while (ptr != head) {
			if (c.compare(obj, ptr.data) == 0) {
				crnt = ptr;
				return ptr.data;			// 검색 성공
			}
			ptr = ptr.next;					// 다음 노드로 선택
		}
		return null;						// 검색 실패
	}
```

* 선택 노드를 출력하는 printCurrentNode 메서드</br>
</t>: 선택 노드의 데이터 crnt.data를 출력함.</br>
</t>: 리스트가 비어 있으면 '선택 노드는 없습니다.'라고 출력함.</br>
```kotlin
    public void printCurrentNode() {
		if (isEmpty())
			System.out.println("선택 노드가 없습니다.");
		else
			System.out.println(crnt.data);
	}
```
* 모든 노드를 출력하는 dump 메서드</br>
</t>: 리스트에 있는 모든 노드를 머리부터 꼬리까지 순서대로 출력하는 메서드</br>
</t>: head.next부터 스캔을 시작하여 뒤쪽 포인터를 찾아가면서 각 노드의 데이터를 출력합니다.</br>
```kotlin
	public void dump() {
		Node<E> ptr = head.next;			// 더미 노드의 다음 노드

		while (ptr != head) {
			System.out.println(ptr.data);
			ptr = ptr.next;
		}
	}
```

* 모든 노드를 거꾸로 출력하는 dumpReverse 메서드</br>
</t>: head.prev부터 스캔을 시작하여 앞쪽 포인터를 찾아가면서 각 노드의 데이터를 출력함.</br>
</t>: 한 바퀴 돌아 head로 돌아오면 스캔이 끝남.</br>
```kotlin
	public void dumpReverse() {
		Node<E> ptr = head.prev;			// 더미 노드의 앞쪽 노드

		while (ptr != head) {
			System.out.println(ptr.data);
			ptr = ptr.prev;
		}
	}
```
p.356
* 선택 노드를 뒤쪽으로 이동하는 next 메서드</br>
</t>: 리스트가 비어 있지 않고 선택 노드의 다음 노드가 있을 때만 선택 노드가 이동함.</br>
</t>: 선택 노드가 이동하면 true, 그렇지 않으면 false 반환.</br>


* 선택 노드를 앞쪽으로 이동하는 prev 메서드</br>
</t>: 리스트가 비어 있지 않고 선택 노드의 앞쪽 노드가 있을 때만 선택 노드가 이동</br>
</t>: 선택 노드가 이동하면 true, 그렇지 않으면 false를 반환함.</br>

* 노드를 삽입하면 add 메서드 </br>
</t>: 노드를 삽입한 곳은 crnt가 가리키는 노드와 crnt.next가 가리키는 노드 사이임.</br>

* 노드를 삽입하면 addFirst 메서드 </br>