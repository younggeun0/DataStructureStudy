package week13;
import java.util.Comparator;

public class DblLinkedList<E> {

    class Node<E> {
        private E data;         // 데이터
        private Node<E> prev;   // 앞쪽 포인터(앞쪽 노드에 대한 참조)
        private Node<E> next;   // 뒤쪽 포인터(다음 노드에 대한 참조)

        // 생성자
        Node() {
            data = null;
            prev = next = this;
        }

        // 생성자
        Node(E obj, Node<E> prev, Node<E> next) {
            data = obj;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> head; // 머리 노드(더미 노드)
    private Node<E> current; // 선택 노드

    // 생성자
    public DblLinkedList() {
        head = current = new Node<E>(); // 더미 노드를 생성
    }

    // 리스트가 비었는가?
    public boolean isEmpty() {
        return head.next == head;
    }

    // 노드를 검색
    public E search(E obj, Comparator<? super E> c) {
        Node<E> p = head.next;

        while (p != head) { // 더미 노드와 같을 떄까지 반복 p.next == head
            if (c.compare(obj, p.data) == 0) { // 검색 성공
                current = p;
                return p.data;
            }
            p = p.next; // 다음 노드를 선택
        }

        return null; // 검색 실패
    }

    // 선택 노드의 바로 뒤에 노드를 삽입
    public void add(E obj) {
        Node<E> node = new Node<E>(obj, current, current.next);
        current.next = current.next.prev = node;
        current = node;
    }

    // 머리에 노드를 삽입
    public void addFirst(E obj) {
        current = head; // 더미 노드 head의 바로 뒤에 삽입
        add(obj);
    }

    // 꼬리에 노드를 삽입
    public void addLast(E obj) {
        current = head.prev; // 꼬리 노드 head.prev의 바로 뒤에 삽입
        add(obj);
    }

    // 머리 노드를 삭제
    public void removeFirst() {
        current = head.next;    // 머리 head.next를 삭제
        removeCurrentNode();
    }

    // 꼬리 노드를 삭제
    public void removeLast() {
        current = head.prev;    // 꼬리 노드 head.prev를 삭제
        removeCurrentNode();
    }

    // 노드 p를 삭제
    public void remove(Node<E> n) {
        Node<E> p = head.next;
        
        while (p != head) {
            if (p == n) { // n을 찾음
                current = n;
                removeCurrentNode();
                break;
            }
            p = p.next;
        }
    }

    // 선택 노드를 삭제
    public void removeCurrentNode() {
        if (!isEmpty()) {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            current = current.prev;
            if (current == head) current = head.next;
        }
    }
    
    public void clear() {
        while (!isEmpty()) { //텅 빌 때까지
            removeFirst(); // 머리노드 삭제
        }
    }

    // 선택 노드를 하나 뒤쪽으로 이동
    public boolean next() {
        if (isEmpty() || current.next == head) {
            return false; // 이동할 수 없음
        }
        current = current.next;
        return true;
    }

    // 선택 노드를 하나 앞쪽으로 이동
    public boolean prev() {
        if (isEmpty() || current.prev == head) {
            return false; // 이동할 수 없음
        }
        current = current.prev;
        return true;
    }

    // 선택 노드를 출력
    public void printCurrentNode() {
        if (isEmpty()) {
            System.out.println("no selected node");
        } else {
            System.out.println(current.data);
        }
    }

    // 모든 노드를 출력
    public void dump() {
        Node<E> p = head.next; // 더미 노드의 다음 노드

        while (p != head) {
            System.out.println(p.data);
            p = p.next;
        }
    }

    // 모든 노드를 거꾸로 출력
    public void dumpReverse() {
        Node<E> p = head.prev; // 더미 노드의 다음 노드

        while (p != head) {
            System.out.println(p.data);
            p = p.prev;
        }
    }
}