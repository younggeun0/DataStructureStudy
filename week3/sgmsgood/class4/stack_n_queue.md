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

### 링 버퍼로 큐 만들기 : 
* 배열의 처음과 끝이 연결되었다고 보는 자료 구조

## **< 큐 클래스 IntQueue >**

#### 1. 큐로 사용할 배열 (que)
* 인큐하는 데이터를 저장하기 위한 큐 본체용 배열

#### 2. 큐의 최대 용량 (max)
* 큐의 최대 용량을 저장하는 필드로, 이 값은 배열 que에 저장할 수 있는 최대 요소의 개수와 같음

#### 3. 프런트 (front)
* 인큐하는 데이터 가운데 첫 번째 요소의 인덱스를 저장하는 필드

#### 4. 리어 (rear)
* 인큐한 데이터 가운데 맨 나중에 넣은 요소의 하나 뒤의 인덱스를 저장하는 필드

#### 5. 현재 데이터 수 (num)
* 큐에 쌓아 놓은 데이터의 수
    * front와 rear의 값이 같은 경우 큐가 비어있는지, 가득 찼는지 구별할 수 없는 상황을 피하기 위해서 이 변수가 필요함

#### 생성자 IntQueue
* 생성자는 큐 본체용 배열을 생성하는 등의 준비 작업을 수행함
    * 생성 시 큐는 비어있기 때문에 num, front, rear 값을 0으로 초기화
    * 매개변수 capacity로 전달받은 '큐의 용량'을 필드 max에 복사하고, 요솟수가 max인 배열 que의 본체생성
```java  
public class IntQueue {
	private int max; //큐의 용량
	private int front; //첫 번째 요소 커서
	private int rear; //마지막 요소 커서
	private int num; //현재 데이터 수
	private int[] que; //큐 본체
	
    //실행 시 예외: 큐가 비어 있음
	public class EmptyIntQueueException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public EmptyIntQueueException() {}
	}
 	
    //실행 시 예외: 큐가 가득 참
	public class OverflowIntQueueException extends RuntimeException{
		private static final long serialVersionUID = 1L;

		public OverflowIntQueueException() {}
	}
	
    //생성자
	public IntQueue(int capacity) {
		num = front = rear = 0;
		max = capacity;
		try {
			que = new int[max]; // 큐 본체용 배열을 생성
 		}catch(OutOfMemoryError e) { //생성할 수 없음
			max = 0;
		}
    }
```
----

* **인큐 메서드 (enque)**
    * 큐에 데이터를 인큐하는 메서드 
    * 인큐에 성공하면 인큐한 값을 그대로 반환
    * 큐가 가득 차서 인큐할 수 없으면 예외처리부분에서 처리 (OverflowIntQueueException)
        * ! rear 값이 max와 같아지면 실제 배열에는 없는 공간인 que[12]를 가리키게 됨 !
        * -> 큐의 최대 용량의 값이 max와 같아질 경우 rear를 배열의 처음인 0으로 변경해야 함
        
```java
public int enque(int x) throws OverflowIntQueueException{
	if(num >= max) {
		throw new OverflowIntQueueException(); //큐가 가득 참
	}
	que[rear++] = x;
	num++;
		
	if(rear == max) {
		rear = 0;
	}
	return x;
}
```
* **디큐 메서드 (deque)**
    * 큐에서 데이터를 빼고 그 값을 반환하는 메서드
    * 큐가 비어 있어서 디큐할 수 없으면 예외처리 (EmptyIntQueueException)
        * 디큐도 인덱스 초과 문제가 발생함
        * -> 디큐하기 전의 front 값이 배열의 끝 (11) 이라면 위의 과정을 거치고 난 후의 
        front 값은 max(12)가 되어 배열 마지막 요소의 인덱스를 초과함
        * 즉, front의 값이 큐의 용량인 max와 같아지면 front 값을 배열의 처음인 0으로 변경해야함

 ```java
public int deque() throws EmptyIntQueueException{
	if(num <= 0) {
		throw new EmptyIntQueueException(); //큐가 비어있음
	}
	int x = que[front++];
	num--;
		
	if(front == max) {
		front = 0;
	}
		
	return x;
}
``` 

``` java
//큐에서 데이터를 피크 (프런트 데이터를 들여다 봄)
public int peek() throws EmptyIntQueueException{
	if(num <= 0) {
		throw new EmptyIntQueueException();
	}
	return que[front];
}

//큐에서 x를 검색하여 인덱스(찾지 못하면 -1) 반환
public int indexOf(int x) {
	for(int i = 0; i < num; i++) {
		int idx = (i + front) % max;
		if(que[idx] == x) {
			return idx;
		}
	}
	return -1;
}

//큐를 비움	
public void clear() {
	num = front = rear = 0;
}

//큐의 용량을 반환	
public int capacity() {
	return max;
}

//큐에 쌓여 있는 데이터 수를 반환	
public int size() {
	return num;
}
	
// 큐가 비어있는지 확인
public boolean isEmpty() {
	return num <= 0;
}

// 큐가 가득 찼는지 확인
public boolean isFull() {
	return num >= max;
}

//큐 안의 모든 데이터를 출력 (프런트 -> 리어 순)	
public void dump() {
	if(num <= 0) {
		System.out.println("큐가 비어 있습니다.");
	}else{
		for(int i = 0; i < num; i++) {
			System.out.print(que[(i + front) % max] + " ");
		}
		System.out.println();
	}
}
```   

