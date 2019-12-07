# 해시법
### 해시법 </br>
</t>: 검색과 더불어 데이터의 추가와 삭제도 효율적으로 수행할 수 있는 방법</br>
</t>: 데이터를 저장할 위치(인덱스)를 간단한 연산으로 구하는 것.</br>
</t> (연산 =  배열의 키 값 % 배열의 사이즈)</br>
</t>: 검색뿐만 아니라 추가, 삭제도 효율적으로 수행할 수 있음.</br>
</t>: 해시 테이블의 각 요소를 버킷(bucket) 이라고 부름.</br>

### 충돌
</t>: 위와 같이 나머지의 값을 인덱스에 넣는 방식의 경우 이미 값이 채워져 있는 곳에 대응되는 값이 들어올 수 있음.</br>
</t>: 이런 경우, 아래의 두 가지 방법으로 대처할 수 있음.</br>
</t> 1) 체인법: 같은 해시 값을 갖는 요소를 연결 리스트로 관리.</br>
</t> 2) 오픈 주소법: 빈 버킷을 찾을 때까지 해시를 반복함.</br>

## 체인법
: 같은 해시 값을 갖는 데이터를 쇠사슬 모양으로 연결 리스트에서 연결하는 방법으로 오픈 해시법(open hashing)이라고 함.</br>
![chainHashing](http://lcm.csa.iisc.ernet.in/dsa/img53.gif)

* 버킷용 테스트 Node<K, V></br>
</t>: 필드 next에 대입되는 값은 체인데 있는 다음 노드에 대한 참조.</br>
</t>(다음 노드가 없을 경우: null)
```java
    class Node<K,V> {
		private K key;					// 키 값
		private V data;					// 데이터
		private Node<K,V> next;			// 다음 노드에 대한 참조

		// 생성자
		Node(K key, V data, Node<K,V> next) {
			this.key  = key;
			this.data = data;
			this.next = next;
		}

		// 키 값을 반환합니다.
		K getKey() {
			return key;
		}

		// 데이터를 반환합니다.
		V getValue() {
			return data;
		}

		// 키의 해시값을 반환합니다.
		public int hashCode() {
			return key.hashCode();
		}
	}
```
* 생성자 ChainHash</br>
</t>: 비어있는 해시 테이블을 생성하며, 매개변수 capacity에 전달받는 것은 해시 테이블의 용량.</br>
</t>: 매개변수 capacity에 전달받는 것은 해시 테이블의 용량</br>
</t>: 요솟수가 ㅊ멤챠쇼인 배열 table의 본체를 생성하고 capacity 값을 필드 size에 복사</br>
</t>: 생성자가 호출된 직후 배열 table의 모든 요소는 null을 참조하며, 모든 버킷이 비어있는 상태가 됨.</br>
</t>: 또한, 메모리 확보에 실패하면 필드 'Size = 0' </br>

* hashValue 메서드 </br>
</t>: 해시 값을 구하는 메서드</br>
</t>: key의 해시 값을 해시 테이블의 크기 size로 나눈 나머지를 반환함</br>

```java
    private int	size;						// 해시 테이블의 크기
	private Node<K,V>[] table;				// 해시 테이블

    // 생성자
	public ChainHash(int capacity) {
		try {
			table = new Node[capacity];
			this.size = capacity;
		} catch (OutOfMemoryError e) {		// 테이블을 생성할 수 없음
			this.size = 0;
		}
	}
```
* 키 값으로 요소를 검색하는 search 메서드</br>
</t>1) 해시 함수가 키 값을 해시 값으로 변환</br>
</t>2) 해시 값을 인덱스로 하는 버킷을 선택</br>
</t>3) 선택한 버킷의 연결 리스트를 처음부터 순서대로 검색</br>
</t>(키 값과 같은 값을 찾으면 검색 성공, 찾지 못하면 검색 실패)</br>

* 요소를 추가하는 Add 메서드</br>
</t>: 키 값이 key이고 데이터가 data인 요소를 삽입하는 메서드</br>
</t>1) 해시 함수가 키 값을 해시 값으로 변환합니다.</br>
</t>2) 해시 값을 인덱스로 하는 버킷을 선택합니다.</br>
</t>3) 버킷이 가리키는 연결 리스트를 처음부터 순서대로 검색합니다. </br> 
</t> 키 값과 같은 값을 찾으면 키 값이 이미 등록된 상태이므로 추가에 실패합니다.</br>
</t> 끝까지 스캔하여 찾지 못하면 리스트의 맨 앞 위치에 노드를 삽입합니다.</br>

* 요소를 삭제하는 remove 메서드</br>
</t>1) 해시 함수가 키 값을 해시 값으로 변환</br>
</t>2) 해시 값을 인덱스로 하는 버킷을 선택</br>
</t>3) 버킷이 가리키는 연결 리스트를 처음부터 순서대로 검색.</br>
</t> 키 값과 같은 값을 찾으면 그 노드를 리스트에서 삭제 (그렇지 않으면 삭제 실패)</br>
```java
    // 키 값 key를 갖는 요소의 검색 (데이터를 반환)
	public V search(K key) {
		int hash = hashValue(key);			// 검색할 데이터의 해시값
		Node<K,V> p = table[hash];			// 선택 노드

		while (p != null) {
			if (p.getKey().equals(key))
				return p.getValue();		// 검색 성공
			p = p.next;						// 다음 노드에 주목
		}
		return null;						// 검색 실패
	}

	// 키 값 key, 데이터 data를 갖는 요소의  추가
	public int add(K key, V data) {
		int hash = hashValue(key);			// 추가할 데이터의 해시값
		Node<K,V> p = table[hash];			// 선택 노드

		while (p != null) {
			if (p.getKey().equals(key))		// 이 키 값은 이미 등록됨
				return 1;
			p = p.next;						// 다음 노드에 주목
		}
		Node<K,V> temp = new Node<K,V>(key, data, table[hash]);
		table[hash] = temp;					// 노드를 삽입
		return 0;
	}

	// 키 값 key를 갖는 요소의 삭제
	public int remove(K key) {
		int hash = hashValue(key);			// 삭제할 데이터의 해시 값
		Node<K,V> p = table[hash];			// 선택 노드
		Node<K,V> pp = null;				// 바로 앞의 선택 노드

		while (p != null) {
			if (p.getKey().equals(key)) {	//  찾으면
				if (pp == null)
					table[hash] = p.next;
				else
					pp.next = p.next;
				return 0;
			}
			pp = p;
			p = p.next;						// 다음 노드를 가리킴
		}
		return 1;							// 그 키 값은 없습니다. 
	}
```
* 해시 테이블의 모든 내용을 출력하는 dump 메서드</br>
</t>: 해시 테이블의 내용을 통째로 출력하는 메서드</br>
</t>: 해시 테이블의 모든 요소에 대하여 다음에 오는 노드를 끌어당기면서 각 노드의 키 값과 데이터를 출겨하는 작업을 반복</br>
```java
00
01 -> 14
02
03 -> 29
04 -> 69 -> 17
05 -> 5
06 -> 6
07 -> 46 -> 20 -> 33 
08
09
```

## 오픈 주소법
: 충돌이 발생했을 때 재해시(rehashing)를 수행하여 비어 있는 버킷을 찾아내는 방법으로 '닫힌 해시법'이라고도 함.

* 요소 삽입</br>
</t>: 재해시할 때 해시 메서드는 자유롭게 결정할 수 있음. </br>
</t>(예: 키 값에 1을 더한 값을 13으로 나누는 방법)</br>
</t>: 만일 재해시 한 인덱스에 버킷이 존재한다면, 빈 인덱스가 찾아질 때까지 반복수행</br>

* 요소 삭제</br>
</t>: 존재하는 버킷을 비워버리면, 같은 해시 값을 갖는 18을 검색할 때 '해시 값이 5인 데이터는 존재하지 않음'으로 여겨짐.</br>
</t>: 각 버킷에 대해 아래의 속성을 부여</br>
</t>1) 데이터 저장 속성값</br>
</t>2) 비어 있음 속성값(-)</br>
</t>3) 삭제 마침 속성값(*)</br>
</t>: 삭제를 마친 경우 속성값 * 을 입력</br>

* 요소 검색 </br>
</t>: 삭제가 된 부분에 * 가 있을 경우 재해시를 수행하여 해당 버킷을 검색.

