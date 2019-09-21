# Week 4
## 1. 스택 (Stack)
*  **스택**
    * 데이터를 일시적으로 저장하기 위해 사용하는 자료구조
    * 후입선출(LIFO, Last In First Out) 방식
    * push(데이터를 넣는 작업)
    * pop (데이터를 꺼내는 작업)
    * top (스택의 가장 윗 부분 = 가장 나중에 들어간 데이터)
    * botton (스택의 가장 아랫부분 = 가장 먼저 들어간 데이터)

* **메서드의 호출과 실행과정**
``` java
void x(){/*...*/}

void y(){/*...*/}

void z(){/*...*/}

int main(){
    z();
}
```
    * main -> z -> x 의 순서대로 메서드 호출
    * x 메서드의 실행이 종료되면 x 메서드만 팝을 함.

* **스택 만들기**

* **생성자**
    * 생성자는 스택 본체용 배열을 생성하는 등 준비 작업 수행.
    * 매개변수 capacity로 전달받은 값을 스택 용량 나타내는 max에 복사
``` java
public class IntStack{
    private int max;
    private int ptr;
    private int[] stk;

    public class EmptyIntStackException extneds RuntimeException{
        public EmptyIntStackException(){}
    }

    public class OverflowIntStackException extends RuntimeException{
        public OverflowIntStackException(){}
    }

    //생성자
    public IntStack(int capacity){
        ptr = 0;
        max = capacity;
        try{
            stk = new int[max];
        }catch(OutOfMemoryError e){
            max = 0;
        }
    }
}
```

* **푸시 메서드 (push)**
    * 스택에 데이터를 푸시하는 메서드

```java
public int push(int x) throws OverflowIntStackException{
    if(ptr >= max) {
    	throw new OverflowIntStackException();
    }
    return stk[ptr++] = x;
}
```

* **팝 메서드 (pop)**
    * 스택의 꼭대기에서 데이터를 팝(제거)하고 그 값을 반환하는 메서드
```java
public int pop() throws EmptyIntStackException{
    if(ptr <= 0) {
    	throw new EmptyIntStackException();
    }
	return stk[--ptr];
}
```

* **피크 메서드 (peek)**
    * 스택의 꼭대기에 있는 데이터를 "몰래 엿보는" 메서드
```java
public int peek() throws EmptyIntStackException{
    if(ptr <= 0) {
		throw new EmptyIntStackException();
    }
    return stk[ptr - 1];
}
```

* **검색 메서드 (indexOf)**
    * 스택 본체의 배열 stk에 x와 같은 값의 데이터가 포함되어 있는지, 포함되어 있다면 몇 번째 배열인지 조사하는 메서드
    * 검색은 꼭대기 쪽에서 바닥 쪽으로 선형 검색 수행함. (= 배열 인덱스가 큰 쪽  -> 작은 쪽)
    * 실패하면 -1 반환
```java
public int indexOf(int x) {
    for(int i = ptr - 1; i >= 0; i--) {
   	if(stk[i] == x) {
    		return i;
    	}
    }
    return -1;
}
```
* **스택의 모든 요소 삭제 메서드 (clear)**
    * 스택에 쌓여있는 모든 데이터를 삭제하는 메서드
```java
public void clear() {
   ptr = 0;
}
```

* **용량을 확인하는 (capacity)**
    * 스택의 용량을 반환하는 메서드 (max값 그대로 반환)
```java
public int capacity() {
    return max;
}
```

* **데이터 수를 확인하는 메서드 (size)**
    * 현재 스택에 쌓여있는 데이터 수를 반환하는 메서드
```java
public int size() {
    return ptr;
}
```
* **스택이 비어 있는지 검사하는 메서드 (IsEmpty)**
    * 스택이 비어있으면 false, 가득 찼으면 true 반환
```java
public boolean isEmpty() {
    return ptr <= max;
}
```
* **스택이 가득 찼는지 검사하는 메서드 (IsFull)**
    * 스택이 비어있으면 false, 가득 찼으면 true 반환
```java
public boolean isFull() {
    return ptr >= max;
}
```    
* **스택 안에 있는 모든 데이터를 표시하는 메서드 (dump)**
    * 쌓여있는 모든 데이터를 바닥에서 꼭대기 순으로 표시하는 메서드
```java
public void dump() {
    if(ptr <= 0) {
    	System.out.println("스택이 비어 있습니다.");
    }else {
    	for(int i = 0; i < ptr; i++) {
    		System.out.print(stk[i] + " ");
    	}
    	System.out.println();
    }
}
``` 
****
## 2. 큐 (Queue)
    * 데이터를 일시적으로 쌓아 놓은 자료구조 (스택과 비슷)
    * 차이점: 선입선출(FIFO) 방식 cf) 스택 = 선입후출 (LIFO)
    * enqueue (큐에 데이터를 넣는 작업)
    * dequeue (큐에서 데이터를 꺼내는 작업)
    * front (데이터를 꺼내는 쪽)
    * rear (데이터를 넣는 쪽)

* **링 버퍼로 큐 만들기**
    * 배열의 처음과 끝이 연결되었다고 보는 자료 구조
```java  
public class IntQueue {
	private int max;
	private int front;
	private int rear;
	private int num;
	private int[] que;
	
	public class EmptyIntQueueException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public EmptyIntQueueException() {}
	}
 	
	public class OverflowIntQueueException extends RuntimeException{
		private static final long serialVersionUID = 1L;

		public OverflowIntQueueException() {}
	}
	
	public IntQueue(int capacity) {
		num = front = rear = 0;
		max = capacity;
		try {
			que = new int[max];
		}catch(OutOfMemoryError e) {
			max = 0;
		}
    }
```
