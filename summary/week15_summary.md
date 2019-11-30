# Week 15 트리(2/2)
## 이진트리와 이진검색트리

### **이진 트리(binary tree)**
- 노드가 왼쪽 자식과 오른쪽 자식을 갖는 트리
- 각 노드의 자식은 2명 이하만 유지
- 왼쪽 : left subtree, 오른쪽: right subtree

![binary tree](https://cdn.programiz.com/sites/tutorial2program/files/sub-tree-concept.jpg)

### **완전 이진 트리(complete binary tree)**
- 루트부터 노드가 채워져 있고
- 같은 레벨에서는 왼쪽에서 오른쪽으로 노드가 채워져 있는 이진 트리
- 마지막 레벨은 왼쪽부터 오른쪽 방향으로 노드를 채우되 끝까지 채울 필요는 없음
- 힙 정렬에서 사용함
- 높이가 k인 완전이진트리가 가질 수 있는 노드의 최댓값은 2^(k+1) - 1 개

![type of binaryTree](https://i.ytimg.com/vi/oNJfm5Gnb_I/maxresdefault.jpg)
- 정이진트리(Full Binary tree) : 트리의 모든 노드가 0개 혹은 2개의 자식을 가지는 경우 = 자식을 하나만 가진 노드가 없는 경우
- 포화이진트리(Perfect Binary tree): 모든 레벨에서 노드가 꽉 차 있는 트리 

### **이진 검색 트리(biary search tree)**
이진 트리가 다음 조건을 만족하면 이진검색트리
1. 어떤 노드 N을 기준으로 왼쪽 서브 트리 노드의 모든 키 값은 노드 N의 키 값보다 작아야 한다.
2. 오른쪽 서브 트리 노드의 키 값은 노드 N의 키 값보다 커야 한다
3. 같은 키 값을 갖는 노드는 없다.

![example of binary search tree](https://media.geeksforgeeks.org/wp-content/uploads/Untitled-Diagram-2-7.png)

이진 검색 트리의 장점
- 중위 순회를 하면 키 값의 오름차순으로 노드를 얻을 수 있음
- 구조가 단순
- 이진검색과 비슷한 방식으로 검색이 가능
- 노드의 삽입이 쉽다

### **이진검색트리 만들기**

**노드 클래스 Node<K, V>**
<table style="border: 1px solid">
  <tr><td>key</td><td>키 값</td></tr>
  <tr><td>data</td><td>데이터</td></tr> 
  <tr><td>left</td><td>왼쪽 자식 노드에 대한 참조(왼쪽 포인터)</td></tr> 
  <tr><td>right</td><Td>오른쪽 자식 노드에 대한 참조(오른쪽 포인터)</td></tr> 
</table>

**생성자**
- 각 필드에 넣어둘 4개의 값을 전달받아 그대로 설정

**키 값을 반환하는 getKey 메서드**
- 키 값key를 그대로 반환

**데이터를 반환하는 getValue 메서드**
- 데이터 data를 그대로 반환

**데이터를 출력하는 print 메서드**
- 출력하는 것은 data

```JAVA
// 생성자
	Node(K key, V data, Node<K, V> left, Node<K, V> right) {
		this.key = key;
		this.data = data;
		this.left = left;
		this.right = right;
	}

// 키 값을 반환
	K getKey() {
		return key;
	}

// 데이터를 반환
	V getValue() {
		return data;
	}

// 데이터를 출력
	void print() {
		System.out.println(data);
	}
```

**이진검색트리 클래스 BinTree< K, V >**
- root : 루트에 대한 참조를 보존, 유지하는 필드
- comparator : 키 값의 대소 관계를 비교하는 비교자
	- 트리가 비어 있는 경우에는 루트도 없으므로 루트 값은 null

**생성자**
- 2개의 생성자가 있음
1. BinTree() : root를 null로 하여 노드가 하나도 없는(비어 있는) 이진검색트리 생성
	- 키를 나타내는 K의 형(type)이 Comparable 인터페이스를 구현하고 있는 Integer 클래스나 String 클래스에 알맞음
2. BinTree(Comparator<? super K> c) : 인수로 비교자를 전달받는 생성자
```JAVA
	private Node<K, V> root;// 루트
	private Comparator<? super K> comparator = null;// 비교자

// 생성자
	public BinTree() {
		root = null;
	}

// 생성자
	public BinTree(Comparator<? super K> c) {
		this();
		comparator = c;
	}
```

**두 키 값을 비교하는 comp 메서드**
- 2개의 키 값을 비교하는 메서드
	- key1 > key2면 양수
	- key1 < key2면 음수
	- key1 == key2면 0
```JAVA
// 두 키 값을 비교
	private int comp(K key1, K key2) {
		return (comparator == null) ? ((Comparable<K>) key1).compareTo(key2) : comparator.compare(key1, key2);
	}
```

**키 값으로 검색하는 search 메서드**
1. 루트부터 선택하여 검색을 진행. 선택하는 노드를 p
2. p가 널이면 검색 실패
3. 검색하는 key와 선택한 노드 p값 비교
	- 값이 같으면 검색 성공(종료)
	- key가 작으면 왼쪽 자식 노드 대입, 크면 오른쪽 자식 노드 대입
4. 2번 과정으로 돌아감
```JAVA
// 키에 의한 검색
	public V search(K key) {
		Node<K, V> p = root;// 루트에 주목

		while (true) {
			if (p == null)// 더 이상 진행하지 않으면
				return null;// 검색 실패
			int cond = comp(key, p.getKey());// key와 노드 p의 키를 비교
			if (cond == 0)// 같으면
				return p.getValue();// 검색 성공
			else if (cond < 0)// key 쪽이 작으면
				p = p.left;// 왼쪽 서브 트리에서 검색
			else// key 쪽이 크면
				p = p.right;// 오른쪽 서브 트리에서 검색
		}
	}
```

**노드를 삽입하는 add 메서드**
<br> 노드가 null이 아닌 경우 <br>
1. 루트를 선택, 선택하는 노드를 node
2. 삽입할 키 key와 선택 노드 node의 키 값을 비교. 값이 같으면 실패(종료)
	- key 값이 삽입할 값보다 작으면
		- 왼쪽 자식 노드가 없는 경우 노드 삽입(종료)
		- 왼쪽 자식 노드 있는 경우 선택 노드를 왼쪽으로 옮김
	- key값이 삽입할 값보다 크면
		- 오른쪽 자식 노드가 없는 경우 노드 삽입(종료)
		- 오른쪽 자식 노드가 있는 경우 선택 노드를 오른쪽으로 옮김
3. 2로 되돌아감

```JAVA
// node를 루트로 하는 서브 트리에 노드<K,V>를 삽입
	private void addNode(Node<K, V> node, K key, V data) {
		int cond = comp(key, node.getKey());
		if (cond == 0)
			return;// key가 이진검색트리에 이미 있음
		else if (cond < 0) {
			if (node.left == null)
				node.left = new Node<K, V>(key, data, null, null);
			else
				addNode(node.left, key, data);// 왼쪽 서브 트리에 주목
		} else {
			if (node.right == null)
				node.right = new Node<K, V>(key, data, null, null);
			else
				addNode(node.right, key, data);// 오른쪽 서브 트리에 주목
		}
	}

// 노드를 삽입
	public void add(K key, V data) {
		if (root == null)
			root = new Node<K, V>(key, data, null, null);
		else
			addNode(root, key, data);
	}
```

**노드를 삭제하는 remove 메서드**
1. 자식 노드가 없는 노드를 삭제하는 경우
	- 삭제할 노드가 부모 노드 왼쪽/오른쪽 이면 부모의 왼쪽/오른쪽 포인터를 null
2. 자식 노드가 1개인 노드를 삭제하는 경우
	- 삭제 대상 노드가 부모 노드 왼쪽/오른쪽 자식이면 왼쪽/오른쪽 포인터가 삭제 대상 노드의 자식을 가리키도록 함
3. 자식 노드가 2개인 노드를 삭제하는 경우
	- 삭제할 노드의 왼쪽 서브 트리에서 키 값이 가장 큰 노드 검색
	- 검색한 노드를 삭제 위치로 옮김(검색 노드의 데이터를 삭제 대상 노드 위치로 복사)
	- 옮긴 노드를 삭제
```JAVA
// 키 값이 key인 노드를 삭제
	public boolean remove(K key) {
		Node<K, V> p = root;// 스캔 중인 노드
		Node<K, V> parent = null;// 스캔 중인 노드의 부모 노드
		boolean isLeftChild = true;// p는 parent의 왼쪽 자식 노드인가?

		while (true) {
			if (p == null)// 더 이상 진행하지 않으면
				return false;// 그 키 값은 없습니다.
			int cond = comp(key, p.getKey());// key와 노드 p의 키 값을 비교
			if (cond == 0)// 같으면
				break;// 검색 성공
			else {
				parent = p;// 가지로 내려가기 전에 부모를 설정
				if (cond < 0) {// key 쪽이 작으면
					isLeftChild = true;// 왼쪽 자식으로 내려감
					p = p.left;// 왼쪽 서브트리에서 검색
				} else {// key 쪽이 크면
					isLeftChild = false;// 오른쪽 자식으로 내려감
					p = p.right;// 오른쪽 서브 트리에서 검색
				}
			}
		}

		if (p.left == null) {// p에는 왼쪽 자식이 없음
			if (p == root)
				root = p.right;
			else if (isLeftChild)
				parent.left = p.right;// 부모의 왼쪽 포인터가 오른쪽 자식을 가리킴
			else
				parent.right = p.right;// 부모의 오른쪽 포인터가 오른쪽 자식을 가리킴
		} else if (p.right == null) {// p에는오른쪽 자식이 없음
			if (p == root)
				root = p.left;
			else if (isLeftChild)
				parent.left = p.left;// 부모의 왼쪽 포인터가 왼쪽 자식을 가리킴
			else
				parent.right = p.left;// 부모의 오른쪽 포인터가 왼쪽 자식을 가리킴
		} else {
			parent = p;
			Node<K, V> left = p.left;// 서브 트리 가운데 가장 큰 노드
			isLeftChild = true;
			while (left.right != null) {// 가장 큰 노드 left를 검색
				parent = left;
				left = left.right;
				isLeftChild = false;
			}
			p.key = left.key;// left의 키 값을 p로 옮김
			p.data = left.data;// left의 데이터를 p로 옮김
			if (isLeftChild)
				parent.left = left.left;// left를 삭제
			else
				parent.right = left.left;// left를 삭제
		}
		return true;
	}
```

**모든 노드를 출력하는 print 메서드**
중위 순회(inorder)방법으로 트리 검색
```JAVA
// node를 루트로 하는 서브 트리의 노드를 키 값의 오름차순으로 출력
	private void printSubTree(Node node) {
		if (node != null) {
			printSubTree(node.left);// 왼쪽 서브 트리를 키 값의 오름차순으로 출력
			System.out.println(node.key + " " + node.data);// node를 출력
			printSubTree(node.right);// 오른쪽 서브 트리를 키 값의 오름차순으로 출력
		}
	}

// 모든 노드를 키 값의 오름차순으로 출력
	public void print() {
		printSubTree(root);
	}
```