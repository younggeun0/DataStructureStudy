## 프로그래머스 문제풀이 2

* [짝수와 홀수](https://programmers.co.kr/learn/courses/30/lessons/12937)

### younggeun0

```javascript
function solution(num) {
    var answer = '';
    
    if(num%2 == 0) {
        answer = 'Even';
    } else {
        answer = 'Odd';
    }
    
    return answer;
}
```

### minj0i

```JAVA

```

### sgmsgood

```java

```

---

* [수박수박수박수박수박수?](https://programmers.co.kr/learn/courses/30/lessons/12922)
  * 아래처럼 풀면 안돼요..

![watermelon_bad](https://github.com/younggeun0/DataStructureStudy/blob/master/programmers_test/img/watermelon_bad.png)

### younggeun0 

```javascript
function solution(n) {
    var answer = '';
    
    var arr = ['수','박'];
    var idx = 0;
    
    if (n > 0) {
        for(var i=0; i<n; i++) {
            answer += arr[idx++];
            if (idx > 1) {
                idx = 0;
            }
        }
    }
    
    return answer;
}
```

### minj0i

```JAVA
class Solution {
  public String solution(int n) {
    String answer = "";
      String watermelon2 = "수박";
      
      int num = n / 2;
      while(num == 1){
            answer = answer + watermelon2;
            num = num - 1;
      }
    if(n%2 == 0){
        answer = answer+watermelon2+watermelon2;   
    }else {
        answer=answer+"수";
    }
      return answer;
  }
}
```

### sgmsgood

```java

```

