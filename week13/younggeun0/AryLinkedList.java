package week13;

import java.util.Comparator;

public class AryLinkedList<E> { // 배열 커서 버전
	
	 class Node<E> {
        private E data; // 데이터
        private int next; // 리스트의 뒤쪽 포인터
        private int dnext; // free 리스트의 뒤쪽 포인터

        void set(E data, int next) {
            this.data = data;
            this.dnext = next;
        }
	 }
	 
	 private Node<E>[] n;	// 리스트 본체
	 private int size;	// 리스트 용량
	 private int max;	// 사용중인 꼬리 record
	 private int head;	// 머리 노드
	 private int current;	// 선택 노드
	 private int deleted;	// free 리스트의 머리 노드
	 private static final int NULL = -1;	// 다음 노드 없음/ 리스트가 가득 참
	 
	 public AryLinkedList(int capacity) {
		 head = current = max = deleted = NULL;
		 
		 try {
			 n = new Node[capacity];
			 for (int i=0; i<capacity; i++) {
				 n[i] = new Node<E>();
			 }
			 size = capacity;
		 } catch (OutOfMemoryError e) { // 배열 생성 실패
			 size = 0;
		 }
	 }
	 
	 // 삭제한 레코드를 프리 리스트에 등록하는 메서드
	 private int getInsertIndex() { // 다음에 삽입하는 record의 인덱스를 구함
		 if (deleted == NULL) { // 삭제할 record가 없음
			 if (max < size) {
				 return ++max; // 새 record를 사용
			 } else {
				 return NULL; // 용량 over(넘침)
			 }
		 } else {
			 int rec = deleted; // free 리스트에서
			 deleted = n[rec].dnext; // 머리 rec를 꺼냄
			 return rec;
		 }
	 }
	 
	 private void deleteIndex(int idx) { // record idx를 free 리스트에 등록
		 if (deleted == NULL) { // 삭제할 record 가 없음 
			 deleted = idx; // idx를 free 리스트의 
			 n[idx].dnext = NULL; // 머리에 등록
		 } else {
	 		 int rec= deleted; // idx를 free 리스트의
			 deleted = idx; // 머리에 삽입
			 n[rec].dnext = rec;
		 }
	 }
	 
	 public E search(E obj, Comparator<? super E> c) { // 노드를 검색
		 int ptr = head; //현재 스캔 중인 노드
		 
		 while (ptr != NULL) {
			 if (c.compare(obj, n[ptr].data) == 0) {
				 current = ptr;
				 return n[ptr].data; // 검색 성공
			 }
			 ptr = n[ptr].next; // 다음 노드에 주목
		 }
		 return null; //검색 실패
	 }
	 
	 public void addFirst(E obj) { // 머리 노드를 삽입
		 int ptr = head; // 삽입 전의 머리 노드
		 int rec = getInsertIndex();
		 if (rec != NULL) {
			 head = current = rec; // 인덱스 rec인 record에 삽입
			 n[head].set(obj, ptr);
		 }
	 }
	 
	 public void addLast(E obj) { // 꼬리에 노드를 삽입
		 if (head == NULL) { // 리스트가 비었으면
			 addFirst(obj); // 머리에 삽입
		 } else {
			 int ptr = head;
			 while(n[ptr].next != NULL) {
				 ptr = n[ptr].next;
			 }
			 int rec = getInsertIndex();
			 if (rec != NULL) { // 인덱스 rec인 record에 삽입
				 n[ptr].next = current = rec;
				 n[rec].set(obj, NULL);
			 }
		 }
	 }
	 
	 public void removeFirst() { // 머리 노드를 삭제
		 if (head != NULL) { // 리스트가 비어 있지 않으면
			 int ptr = n[head].next;
			 deleteIndex(head);
			 head = current = ptr;
		 }
	 }
	 
	 public void removeLast() { // 꼬리 노드를 삭제
		 if (head != NULL) {
			 if (n[head].next == NULL) { // 노드가 하나만 있으면
				 removeFirst(); // 머리 노드를 삭제
			 } else {
				 int ptr = head; // 스캔 중인 노드
				 int pre = head; // 스캔 중인 노드의 앞쪽 노드
			 
				 while (n[ptr].next != NULL) {
					 pre = ptr;
					 ptr = n[ptr].next;
				 }
				 n[pre].next = NULL; // pre는 삭제 후의 꼬리 노드
				 deleteIndex(pre);
				 current = pre;
			 }
		 }
	 }
	 
	 public void remove(int p) { // record p를 삭제
		 if (head != NULL) {
			 if (p == head) { // p가 머리 노드면
				 removeFirst();
			 } else {
				 int ptr = head;
				 
				 while (n[ptr].next != p) {
					 ptr = n[ptr].next;
					 if (ptr == NULL) return; // p가 리스트에 없을 때
				 } 
				 
				 n[ptr].next = NULL;
				 deleteIndex(ptr);
				 n[ptr].next = n[p].next;
				 current = ptr;
			 }
		 }
	 }
	 
	 public void removeCurrentNode() { // 선택 노드를 삭제
		 remove(current);
	 }
	 
	 public void clear() { // 모든 노드를 삭제
		 while (head != NULL) { // 텅 빌 때까지
			 removeFirst(); // 머리 노드를 삭제
		 }
		 current = NULL;
	 }
	 
	 public boolean next() { // 선택 노드를 하나 뒤쪽으로 이동
		 if (current == NULL || n[current].next == NULL) {
			 return false; // 이동할 수 없음
		 } 
		 current = n[current].next;
		 return true;
	 }
	 
	 public void printCurrentNode() { // 선택 노드를 출력
		 if (current == NULL) {
			 System.out.println("선택 노드가 없습니다.");
		 } else {
			System.out.println(n[current].data); 
		 }
	 }
	 
	 public void dump() { // 모든 노드를 출력
		 int ptr = head;
		 
		 while (ptr != NULL) {
			 System.out.println(n[ptr].data);
			 ptr = n[ptr].dnext;
		 }
	 }
}
