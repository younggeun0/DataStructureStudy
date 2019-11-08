package week15;

import java.util.Comparator;

public class BinTree<K, V> {

    static class Node<K, V> { // 노드
        private K key;  // 키 값
        private V data; // 데이터
        private Node<K, V> left; // 왼쪽 자식 노드
        private Node<K, V> right; // 오른쪽 자식 노드

        // 생성자
        Node(K key, V data, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.data = data;
            this.left = left;
            this.right = right;
        }

        K getKey() { // 키 값을 반환
            return key;
        }

        V getValue() {// 데이터를 반환
            return data;
        }

        void print() {// 데이터 출력
            System.out.println(data);
        }
    }

    private Node<K, V> root; // 루트
    private Comparator<? super K> comparator = null; // 비교자

    public BinTree() { // 이진트리 생성자, 자연 순서에 따라 값을 비교
        root = null;
    }

    public BinTree(Comparator<? super K> c) { // 비교자로 키 값을 비교
        this();
        comparator = c;
    }

    // 두 키 값을 비교
    private int comp(K key1, K key2) { // 두 값을 비교
        return (comparator == null) ? ((Comparable<K>)key1).compareTo(key2)
            : comparator.compare(key1, key2);
    }

    // 이진트리 키에 의한 검색
    public V search(K key) {
        Node<K, V> p = root; // 루트에 주목

        while(true) {
            if (p == null ) // 더 이상 진행하지 않으면
                return null;

            int cond = comp(key, p.getKey()); // key와 노드 p 키를 비교
            if (cond == 0) {
                return p.getValue();    // 검색 성공
            } else if (cond < 0) {      // key쪽이 작으면
                p = p.left;             // 왼쪽 서브 트리에서 검색
            } else {                    // key쪽이 크면
                p = p.right;            // 오른쪽 서브 트리에서 검색
            }
        }
    }

    // 이진트리 삽입, node를 루트로 하는 서브 트리에 노드<K, V>를 삽입
    private void addNode(Node<K, V> node, K key, V data) {
        int cond = comp(key, node.getKey()); // 비교 후 삽입 위치를 결정
        if (cond == 0) {
            return;             // key가 이진검색트리에 이미 있음
        } else if (cond < 0){   // 왼쪽 서브트리에 삽입
            if (node.left == null) {
                node.left = new Node<K,V>(key, data, null, null);
            } else {
                addNode(node.left, key, data); // 왼쪽 노드가 이미 있으면 왼쪽노드의 왼쪽에 새로 추가
            }
        } else {                // 오른쪽 서브트리에 삽입
            if (node.right == null) {
                node.right = new Node<K,V>(key, data, null, null);
            } else {
                addNode(node.right, key, data); // 오른쪽 노드가 이미 있으면 오른쪽 노드의 오른쪽에 새로 추가
            }
        }
    }

    // 노드를 삽입
    public void add(K key, V data) {
        if (root == null) {     // 트리가 비어있는 상태
            root = new Node<K, V>(key, data, null, null);
        } else {
            addNode(root, key, data);
        }
    }

    // 노드를 삭제
    public boolean remove(K key) {
        Node<K, V> p = root;            // 스캔 중인 노드
        Node<K, V> parent = null;       // 스캔 중인 노드의 부모노드
        boolean isLeftChild = true;     // p는 부모 노드의 왼쪽자식인지 판단하는 플래그

        // 노드를 삭제할 노드를 탐색
        while(true) {
            if (p == null) {    // 더 이상 진행하지 않으면
                return false;   // 그 키 값은 없음
            }
            int cond = comp(key, p.getKey()); // key와 노드 p의 키 값 비교
            if (cond == 0) {
                break;
            } else {
                parent = p;              // 가지로 내려가기 전 부모를 설정
                if (cond < 0) {          // key 가 작으면 왼쪽 서브트리에서 검색
                    isLeftChild = true;
                    p = p.left;
                } else {
                    isLeftChild = false;
                    p = p.right;
                }
            }
        }

        if (p.left == null) { // p에는 왼쪽 자식이 없음
            if (p == root) { 
                root = p.right;
            } else if (isLeftChild) { // 부모의 왼쪽 포인터가 오른쪽 자식을 가리켜 삭제
                parent.left = p.right;
            } else { // 부모의 오른쪽 포인터가 오른쪽 자식을 가리켜 삭제
                parent.right = p.right; 
            }
        } else if (p.right == null) { // p에는 오른쪽 자식이 없음
            if (p == root) {
                root = p.left;
            } else if (isLeftChild) {
                parent.left = p.left; // 부모 왼쪽 포인터가 왼쪽 자식을 가리켜 삭제
            } else {
                parent.right = p.left; // 부모의 오른쪽 포인터가 왼쪽 자식을 가리켜 삭제
            }
        } else { // 양쪽 서브트리가 존재할 때
            parent = p;
            Node<K, V> left = p.left; // 서브 트리가운데 가장 큰 노드
            isLeftChild = true;
            while(left.right != null) { // 왼쪽 서브트리에서 가장 큰 노드 left를 검색
                parent = left;
                left = left.right;
                isLeftChild = false;
            }

            // 왼쪽 서브트리에서 가장 오른쪽에 있는 노드로 삭제할 노드에 값을 복사, 덮어씀
            p.key = left.key;   
            p.data = left.data;

            // 복사한 가장 오른쪽에 있는 노드를 삭제(부모가 null을 가리키게 만듦)
            if (isLeftChild) {
                parent.left = left.left; // left를 삭제
            } else {
                parent.right = left.right; // right를 삭제
            }
        }

        return true;
    }

    // 오름차순으로 출력하기 위해 중위순회(Inorder) - 재귀적 처리(Recursive)
    // node를 루트로 하는 서브 트리의 노드를 키 값의 오름차순으로 출력
    private void printSubTree(Node node) {
        if (node != null) {
            printSubTree(node.left);        // 왼쪽 서브 트리를 키 값의 오름차순으로 출력
            System.out.println(node.key + " " + node.data); // node를 출력
            printSubTree(node.right);       // 오른쪽 서브 트리를 키 값의 오름차순으로 출력
        }
    }    

    // 모든 노드를 키 값의 오름차순으로 출력
    public void print() {
        printSubTree(root);
    }
}