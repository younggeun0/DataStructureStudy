package minj0i;

import java.util.Comparator;
// 연결 리스트 클래스(배열 커서 버전)

public class AryLinkedList<E> {

	class Node<E> {
		private E data;
		private int next;
		private int dnext;
		
		void set(E data, int next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node<E>[] n;
	private int size;
	private int max;
	private int head;
	private int crnt;
	private int deleted;
	private static final int NULL = -1;

	public AryLinkedList(int capacity) {
		head = crnt = max = deleted = NULL;
		try {
			n = new Node[capacity];
			for (int i = 0; i < capacity; i++)
				n[i] = new Node<E>();
			size = capacity;
		} catch (OutOfMemoryError e) {
			size = 0;
		}
	}

	private int getInsertIndex() {
		if (deleted == NULL) {
			if (max < size)
				return ++max;
			else
				return NULL;
		} else {
			int rec = deleted;
			deleted = n[rec].dnext;
			return rec;
		}
	}

	private void deleteIndex(int idx) {
		if (deleted == NULL) {
			deleted = idx;
			n[idx].dnext = NULL;
		} else {
			int rec = deleted;
			deleted = idx;
			n[rec].dnext = rec;
		}
	}

	public E search(E obj, Comparator<? super E> c) {
		int ptr = head;

		while (ptr != NULL) {
			if (c.compare(obj, n[ptr].data) == 0) {
				crnt = ptr;
				return n[ptr].data;
			}
			ptr = n[ptr].next;
		}
		return null;
	}

	public void addFirst(E obj) {
		int ptr = head;
		int rec = getInsertIndex();
		if (rec != NULL) {
			head = crnt = rec;
			n[head].set(obj, ptr);
		}
	}

	public void addLast(E obj) {
		if (head == NULL)
			addFirst(obj);
		else {
			int ptr = head;
			while (n[ptr].next != NULL)
				ptr = n[ptr].next;
			int rec = getInsertIndex();
			if (rec != NULL) {
				n[ptr].next = crnt = rec;
				n[rec].set(obj, NULL);
			}
		}
	}

	public void removeFirst() {
		if (head != NULL) {
			int ptr = n[head].next;
			deleteIndex(head);
			head = crnt = ptr;
		}
	}

	public void removeLast() {
		if (head != NULL) {
			if (n[head].next == NULL) 
				removeFirst();
			else {
				int ptr = head;
				int pre = head;

				while (n[ptr].next != NULL) {
					pre = ptr;
					ptr = n[ptr].next;
				}
				n[pre].next = NULL;
				deleteIndex(pre);
				crnt = pre;
			}
		}
	}

	public void remove(int p) {
		if (head != NULL) {
			if (p == head)
				removeFirst();
			else {
				int ptr = head;

				while (n[ptr].next != p) {
					ptr = n[ptr].next;
					if (ptr == NULL)
						return;
				}
				n[ptr].next = NULL;
				deleteIndex(ptr);
				n[ptr].next = n[p].next;
				crnt = ptr;
			}
		}
	}

	public void removeCurrentNode() {
		remove(crnt);
	}

	public void clear() {
		while (head != NULL)
			removeFirst();
		crnt = NULL;
	}

	public boolean next() {
		if (crnt == NULL || n[crnt].next == NULL)
			return false;
		crnt = n[crnt].next;
		return true;
	}

	public void printCurrentNode() {
		if (crnt == NULL)
			System.out.println("선택 노드가 없습니다.");
		else
			System.out.println(n[crnt].data);
	}

	public void dump() {
		int ptr = head;

		while (ptr != NULL) {
			System.out.println(n[ptr].data);
			ptr = n[ptr].next;
		}
	}
}