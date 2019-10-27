## 프로그래머스 문제풀이 4

* [2016년](https://programmers.co.kr/learn/courses/30/lessons/12901)

---

### younggeun0

```javascript
function solution(a, b) {
    var answer = '';
    
    var day = [ 'SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI' ,'SAT' ];
    
    var dateInstance = new Date("2016-"+a+"-"+b);
    answer = day[dateInstance.getDay()];
    
    
    return answer;
}
```

### minj0i

```JAVA

```

### sgmsgood

```java

}
```

* [완주하지 못한 선수](https://programmers.co.kr/learn/courses/30/lessons/42576)

---

### younggeun0

```javascript
function solution(participant, completion) {
    participant.sort();
    completion.sort();
    
    for(var i=0; i<participant.length; i++) {
        if (participant[i] != completion[i]) {
            return participant[i];
        }
    }
}
```

### minj0i

```JAVA

```

### sgmsgood

```java

}
```



