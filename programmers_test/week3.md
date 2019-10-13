## 프로그래머스 문제풀이 3

* [문자열을 정수로 바꾸기](https://programmers.co.kr/learn/courses/30/lessons/12925)

### younggeun0

```javascript
function solution(s) {
    var answer = 0;
    
    answer = parseInt(s);
    
    return answer;
}
```

### minj0i

```JAVA
class Solution {
  public int solution(String s) {
      int answer = 0;
      
      answer = Integer.parseInt(s);
      
      return answer;
  }
}
```

### sgmsgood

```java
class Solution {
  public int solution(String s) {
      int answer = 0;
      answer = Integer.parseInt(s);
      return answer;
  }
}
```

---

* [핸드폰 번호 가리기](https://programmers.co.kr/learn/courses/30/lessons/12948)

### younggeun0 

```javascript
function solution(phone_number) {
    var answer = '';
    
    var maskLength = phone_number.length-4;
    
    for(var i=0; i<maskLength; i++){
        answer += "*";
    }
    
    answer += phone_number.substring(maskLength);
    
    return answer;
}
```

### minj0i

```JAVA

```

### sgmsgood

```JAVA

```


---

* [모의고사](https://programmers.co.kr/learn/courses/30/lessons/42840?language=javascript)


### younggeun0 

```javascript

```

### minj0i

```JAVA

```

### sgmsgood

```JAVA

```


---
