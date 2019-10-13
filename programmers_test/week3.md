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
class Solution {
  public String solution(String phone_number) {
      String answer = "";
      int num = phone_number.length();
      
      for(int i = 0; i < num-4; i++){
          answer= answer + "*";
      }
      
      answer = answer + phone_number.substring(phone_number.length()-4, phone_number.length());
      return answer;
  }
}
```

### sgmsgood

```JAVA
class Solution {
  public String solution(String phone_number) {
      String answer = "";
      String temp = "";
      
      int wordLength = phone_number.length();
      
            for(int j = 0; j < phone_number.length()-4; j++){
                temp += "*";
                
            }
      answer = phone_number.replace(phone_number.substring(0, wordLength-4),temp);
      
      
      return answer;
  }
}

```


---

* [모의고사](https://programmers.co.kr/learn/courses/30/lessons/42840?language=javascript)


### younggeun0 

```javascript

```

### minj0i

```JAVA
class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        int[] answerA = {1, 2, 3, 4, 5};
        int[] answerB = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] answerC = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int cntA = 0, cntB = 0, cntC = 0;
        
        for (int i=0; i<answers.length; i++){
            cntA = cntA + (answers[i] == answerA[i%5] ? 1 : 0);
            cntB = cntB + (answers[i] == answerB[i%8] ? 1 : 0);
            cntC = cntC + (answers[i] == answerC[i%10] ? 1: 0);
        }
        
        if (cntA > cntB ){
            if (cntA > cntC) {
                answer = new int[1];
                answer[0] = 1;
            }else if (cntA < cntC) {
                answer = new int[1];
                answer[0] = 3;
            }else {
                answer = new int[2];
                answer[0] = 1;
                answer[1] = 3;
            }
        }else if (cntB > cntA){
            if (cntB > cntC) {
                answer = new int[1];
                answer[0] = 2;
            }else if (cntB < cntC) {
                answer = new int[1];
                answer[0] = 3;
            }else {
                answer = new int[2];
                answer[0] = 2;
                answer[1] = 3;
            }
        }else if (cntB == cntA) {
            if (cntB > cntC) {
                answer = new int[2];
                answer[0] = 1;
                answer[1] = 2;
            }else if (cntB < cntC){
                answer = new int[1];
                answer[0] = 3;
            }else {
                answer = new int[3];
                answer[0] = 1;
                answer[1] = 2;
                answer[2] = 3;
            }
        }
        return answer;
    }
}
```

### sgmsgood

```JAVA

```


---
