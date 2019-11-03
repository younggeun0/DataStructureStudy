package list_study;
import java.util.Comparator;

public class LinkedList<E> {

    class Node<E> {
        private E data;
        private Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> current;

    public LinkedList() {
        head = current = null;
    }

    public E search(E obj, Comparator<? super E> c) {
        Node<E> p = head;

        while (p != null) {
            if (c.compare(obj, p.data) == 0) { // 검색 성공
                current = p;
                return p.data;
            }
            p = p.next; // 다음 노드를 선택
        }

        return null; // 검색 실패
    }

    public void addFirst(E obj) {
        Node<E> p = head;
        head = current = new Node<E>(obj, p); // 기존 헤드를 next로 넣어서 새 노드를 추가
    }

    public void addLast(E obj) {
        if (head == null) {
            addFirst(obj);
        } else {
            Node<E> p = head;
            while(p.next != null) {
                p = p.next;
            } // 마지막 노드를 찾음
            p.next = current = new Node<E>(obj, null);
        }
    }

    public void removeFirst() {
        if (head != null) { // 리스트가 비어 있지 않으면
            head = current = head.next;
        }
    }

    public void removeLast() {
        if (head != null) {
            if (head.next == null) { // 노드가 하나면
                removeFirst(); // 머리 노드 삭제
            } else {
                Node<E> p = head; // 스캔중인 노드
                Node<E> pre = head; // 스캔 중인 노드의 앞쪽 노드

                while (p.next != null) {
                    pre = p;
                    p = p.next;
                } // while 끝나면 pre는 마지막 노드 앞 노드를 가리킴, p는 마지막 노드를 가리킴

                pre.next = null; // p룰 삭제
                current = pre; 
            }
        }
    }

    public void remove(Node<E> n) {
        if (head != null) {
            if (n == head) {
                removeFirst();
            } else {
                Node<E> p = head;

                while (p.next != n) {
                    p = p.next;
                    if (p == null) return; // n이 리스트에 없음
                }

                p.next = n.next; // n 다음 노드를 n 이전노드인 p가 가리키게 만들어서 삭제처리
                current = p;
            }
        }
    }
    
    public void removeCurrentNode() {
    	remove(current);
    }

    public void clear() {
        while (head != null) { // 노드에 아무것도 없을 때까지
            removeFirst(); // 머리노드 삭제
        }
        current = null;
    }

    public boolean next() {
        if (current == null || current.next == null) {
            return false;
        }
        current = current.next;
        return true;
    }

    public void printCurrentNode() {
        if (current == null) {
            System.out.println("no selected node");
        } else {
            System.out.println(current.data);
        }
    }

    public void dump() {
        Node<E> p = head;

        while (p != null) {
            System.out.println(p.data);
            p = p.next;
        }
    }
}