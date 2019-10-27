# Week 9

## 1. 집합(Set)

* **집합**이란 명확한 조건을 만족하는 자료의 모임
  * 집합(set)이란 객관적으로 범위를 규정한 '어떤 것'의 모임
  * 그 집합 안에서 각각의 '어떤 것'을 요소(element)라고 부름  
* 집합에 포함되는 요소는 서로 달라야 함
  * **요소가 중복적으로 저장되지 않음**
* 수학적 표기법은 생략
  * 요소의 포함 여부, 집합 비교 등 
* **부분집합과 진부분집합**
  * 집합 A의 모든 요소가 집합 B의 요소이면 A는 B의 **부분집합**이고 A는 B에 포함된다고 함
    * A⊂B 또는 B⊃A
  * 집합 A의 모든 요소가 집합 B의 요소이면서 집합 A와 집합 B가 같지 않을 때 A는 B의 **진부분집합**이라고 함
    * A⊂B이고 A≠B

```bash
A = { 1, 3 }
B = { 1, 3, 5 } 
# A는 B의 부분집합이면서 진부분집합
```

* **집합의 연산**
  * **합집합**
    * 집합 A와 집합 B 가운데 적어도 한쪽에 속하는 요소의 집합을 A와 B의 합집합이라고 함 (A∪B)
  * **교집합**
    * 집합 A,B 양쪽 모두에 속하는 요소의 집합을 A와 B의 교집합이라고 함 (A∩B)
  * **차집합**
    * 집합 A의 요소 가운데 집합 B의 요소를 제외한 요소의 집합을 차집합이라고 함 (A-B)

## 2. 배열로 집합 만들기

* 같은 자료형이 모인 집합은 배열로 표현가능
  * 배열을 사용하여 집합을 표현하려면 집합의 요소 개수와 배열의 요소 개수는 항상 같아야 함
  * 집합 상태를 표현할 데이터가 필요
    * 집합의 최대 크기를 나타내는 max, 집합의 현재 요솟수를 나타내는 num

```java
public class IntSet {
    int max;    // 집합의 최대 크기
    int num;    // 집합의 요소 개수
    int[] set;  // 집합 본체

    // 생성자
    public IntSet(int capacity) {
        num = 0;
        max = capacity;
        try {
            set = new int[max];
        } catch (OutOfMemoryError e) {
            max = 0;
        }
    }
    
    // 집합의 최대 개수
    public int capacity() {
        return max;
    }

    // 집합의 요소 개수
    public int size() {
        return num;
    }

    //  집합에서 n을 검색(index 반환)
    public int indexOf(int n) {
        for(int i=0; i<num; i++) {
            if (set[i] == n) {
                return i; // 검색 성공
            }
        }
        return -1; // 검색 실패
    }

    // 집합에 n이 있는지 없는지 확인
    public boolean contains(int n) {
        return (indexOf(n) != -1) ? true : false;
    }

    // 집합에 n을 추가
    public boolean add(int n) {
        if (num >= max || contains(n) == true) { // 가득 차거나 이미 요소 n존재
            return false;
        } else {
            set[num++] = n; // 가장 마지막 자리에 추가
            return true;
        }
    }

    // 집합에서 n 삭제
    public boolean remove(int n) {
        int idx; // n이 저장된 인덱스

        if (num <= 0 || (idx = indexOf(n)) == -1) { // 비어있거나 n이 존재하지 않음
            return false;
        } else {
            set[idx] = set[--num];  // 마지막 요소를 삭제한 곳으로 옮김
            return true;
        }
    }

    // 집합 s에 복사(현재 Set을 s에 복사)
    public void copyTo(IntSet s) {
        int n = (s.max < num) ? s.max : num; // 복사할 요솟수
        for (int i=0; i<n; i++) {
            s.set[i] = set[i];
        }
        s.num = n;
    }

    // 집합 s를 복사(s를 현재 Set에 복사)
    public void copyFrom(IntSet s) {
        int n = (max < s.num) ? max : s.num; // 복사할 요솟수
        for (int i=0; i<n; i++) {
            set[i] = s.set[i];
        }
        num = n;
    }

    // 집합 s와 같은지 확인
    public boolean equalTo(IntSet s) {
        if (num != s.num) {     // 요솟수가 다르면 다른 집합
            return false;
        }

        for (int i=0; i<num; i++) {
            int j = 0;
            for(; j<s.num; j++) {
                if (set[i] == s.set[j]) {
                    break;
                }
            }

            if (j == s.num) { 
                // 현재 집합의 요소가 s에 포함되지 않았을 경우
                // j는 for문을 모두 돌기 때문에 s.num과 같아진다
                return false;
            }
        }
        return true;
    }

    // 집합 s1과 s2의 합집합을 복사
    public void unionOf(IntSet s1, IntSet s2) {
        copyFrom(s1);   // 집합 s1을 복사
        for (int i=0; i<s2.num; i++) { // 집합 s2의 요소를 추가
            add(s2.set[i]);
        }
    }

    // "{ a b c }"형식의 문자열로 반환
    public String toString() {
        StringBuffer tmp = new StringBuffer("{ ");
        for (int i=0; i<num; i++) {
            tmp.append(set[i] + " ");
        }
        tmp.append(" }");
        return tmp.toString();
    }
}
```

* **toString 메서드**
  * java.lang 패키지 Object 클래스에서 정의된 메서드
    * 자바의 모든 클래스는 Object 클래스를 상속, toString메서드를 재정의하면(오버라이드) 다른 형태로 문자열을 찍을 수 있다.
    * 원래는 클래스이름@해시값 형태로 문자열을 반환

```java
public class Object {
    ...
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
    ...
}
```
