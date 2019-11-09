package week16;

public class OpenHash<K, V> {

    // 버킷 상태
    enum Status { OCCUPIED, EMPTY, DELETED }; // {데이터 저장, 비어 있음, 삭제 마침}

    // 버킷
    class Bucket<K, V> {
        private K key;
        private V data;
        private Status stat; //상태

        // 생성자
        Bucket() {
            stat = Status.EMPTY; // 버킷은 비어있음
        }

        // 모든 필드에 값을 설정
        void set(K key, V data, Status stat) {
            this.key = key;
            this.data = data;
            this.stat = stat;
        }

        // 상태를 설정
        void setStat(Status stat) {
            this.stat = stat;
        }
    
        K getKey() {
            return key;
        }
    
        V getValue() {
            return data;
        }
    
        public int hashCode() {
            return key.hashCode();
        }
    }

    private int size; // 해시 테이블의 크기
    private Bucket<K, V>[] table; // 해시 테이블

    public OpenHash(int size) {
        try {
            table = new Bucket[size];
            for (int i=0; i<size; i++) {
                table[i] = new Bucket<K, V>();
            }
            this.size = size;
        } catch(OutOfMemoryError error) {
            this.size = 0;
        }
    }

    // 해시 값을 구함
    public int hashValue(Object key) {
        return key.hashCode() % size;
    }

    // 충돌 시 재해시 값을 구함
    public int rehashValue(int hash) {
        return (hash+1) % size;
    }

    // 키 값 key를 갖는 버킷 검색
    public Bucket<K, V> searchNode(K key) {
        int hash = hashValue(key);    // 검색할 데이터의 해시 값
        Bucket<K, V> p = table[hash]; // 선택 노드

        for (int i=0; p.stat != Status.EMPTY && i < size; i++) {
            if (p.stat == Status.OCCUPIED && p.getKey().equals(key)) { 
                return p; // 검색 성공
            }
            hash = rehashValue(hash); // 해시값이 같으면 재해시
            p = table[hash];
        }
        return null;
    }

    // 키 값 key를 갖는 요소의 검색(데이터를 반환)
    public V search(K key) {
        Bucket<K,V> p = searchNode(key); // 버킷 찾고

        if (p != null) {
            return p.getValue(); // 검색성공 시 값 반환
        } else {
            return null;         //검색 실패
        }
    }

    // 키 값 key, 데이터 data를 갖는 요소의 추가
    public int add(K key, V data) {
        if (search(key) != null) {
            return 1; // 이미 등록된 키 값
        }

        int hash = hashValue(key);    // 추가할 데이터의 해시 값
        Bucket<K, V> p = table[hash]; // 선택 버킷

        for (int i=0; i<size; i++) {
            // 비어있거나 삭제된 공간에만 추가
            if (p.stat == Status.EMPTY || p.stat == Status.DELETED) {
                p.set(key, data, Status.OCCUPIED);
                return 0; // 추가 성공
            }
            hash = rehashValue(hash); // 재해시
            p = table[hash];
        }
        return 2; // 해시테이블이 가득 참
    }

    // 키 값 key를 갖는 요소의 삭제
    public int remove(K key) {
        Bucket<K, V> p = searchNode(key); // 선택 버킷

        if (p == null) {
            return 1; // 키 값 없음, 삭제 실패
        }

        p.setStat(Status.DELETED); // 삭제 상태 처리
        return 0; // 삭제 성공
    }

    // 해시 테이블을 덤프
    public void dump() {
        for(int i=0; i<size; i++) {
            System.out.printf("%02d ",i);
            switch (table[i].stat) {
                case OCCUPIED:
                    System.out.printf("%s (%s)\n",
                        table[i].getKey(), table[i].getValue());
                    break;
                case EMPTY:
                    System.out.println("--미등록--");
                    break;
                case DELETED:
                    System.out.println("--삭제 마침--");
                    break;
            }
        }
    }
}