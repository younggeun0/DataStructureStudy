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

```

### sgmsgood

```java

```

