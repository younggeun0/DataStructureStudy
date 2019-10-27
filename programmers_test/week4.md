## 프로그래머스 문제풀이 4

* [2016년](https://programmers.co.kr/learn/courses/30/lessons/12901)

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

