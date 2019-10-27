## 프로그래머스 문제풀이 4

---

* [2016년](https://programmers.co.kr/learn/courses/30/lessons/12901)

### younggeun0

```java
import java.util.*;

class Solution {
  public String solution(int a, int b) {
      String answer = "";
      
      
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.YEAR, 2016); cal.set(Calendar.MONTH, a-1);
      cal.set(Calendar.DAY_OF_MONTH, b);
      
      answer = "SUN,MON,TUE,WED,THU,FRI,SAT".split(",")[cal.get(Calendar.DAY_OF_WEEK)-1];
      
      return answer;
  }
}
```

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
import java.util.Calendar;

class Solution {
  public String solution(int a, int b) {
      String answer = "";
      String[] answers = {"FRI","SAT", "SUN","MON","TUE","WED","THU"};
      int day = 0;
      
      if ( a <= 12 && a >= 1) {
          switch(a) {
              case 1: {
                  if (b > 31) {
                      break;
                  } else {
                      day = b;
                  }
                  break;
              }
              case 2: {
                if ( b > 29) {
                      break;
                  } else {
                      day = b + 31;
                  }
                  break;
              }
              case 3: {
                  if ( b > 31) {
                      break;
                  } else {
                      day = b + 31  + 29;
                  }
                  break;
              }
              case 4: {
                  if ( b > 30) {
                      break;
                  } else {
                      day = b + 31  + 29 + 31;
                  }
                  break;
              }
              case 5:{
                  if ( b > 31) {
                      break;
                  } else {
                      day = b + 31 + 29 + 31 + 30;
                  }
                  break;
              }
              case 6: {
                  if ( b > 30) {
                      break;
                  } else {
                      day = b + 31 + 29 + 31 + 30 + 31;
                  }
                  break;
              }
              case 7: {
                  if ( b > 31) {
                      break;
                  } else {
                      day = b + 31 + 29 + 31 + 30 + 31 + 30 ; 
                  }
                  break;
              } 
              case 8: {
                  if ( b > 31) {
                      break;
                  } else {
                      day = b + 31 + 29 + 31 + 30 + 31 + 30 + 31 ;
                  }
                  break;
              }
              case 9:{
                  if ( b > 30) {
                      break;
                  } else {
                      day = b + 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31;
                  }
                  break;
              } 
              case 10: {
                  if ( b > 31) {
                      break;
                  } else {
                      day = b + 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30;
                  }
                  break;
              }
              case 11: {
                  if ( b > 30) {
                      break;
                  } else {
                      day = b + 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31;
                  }
                  break;
              }
              case 12: {
                  if ( b > 31) {
                      break;
                  } else {
                      day = b + 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30;
                  }
                  break;
              }
              default : break;
          }
        }
      
      int days = day%7;
      answer = answers[days - 1];

      return answer;
  }
}
```

### sgmsgood

```java
import java.util.Calendar;

class Solution {
  public String solution(int a, int b) {
      String answer = "";
      Calendar cal = Calendar.getInstance();
      cal.set(2016,a-1, b);
      answer = cal.getTime().toString().substring(0,3).toUpperCase();
      return answer;
  }
}
```

---

* [완주하지 못한 선수](https://programmers.co.kr/learn/courses/30/lessons/42576)

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



