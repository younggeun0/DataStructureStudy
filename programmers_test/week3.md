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

* 아래 JS풀이인 경우 효율이 좋지 못하여 40/100점 나온듯

```javascript
function solution(answers) {
    var answer = [];
    
    // 학생들 패턴
    var student1 = [1,2,3,4,5];
    var student2 = [2,1,2,3,2,4,2,5];
    var student3 = [3,3,1,1,2,2,4,4,5,5];
    
    var studentIdx = [0, 0, 0]; // 탐색용 인덱스
    var studentCnt = [0, 0, 0]; // 학생별 맞춘 문제 수
    
    var tmpAnswer; // 비교할 문제답
    for(var i=0; i<answers.length; i++){
        tmpAnswer = answers[i];
        if (student1[studentIdx[0]++] == tmpAnswer) {
            studentCnt[0]++;
            studentIdx[0] = studentIdx[0] % student1.length;
        }
        
        if (student2[studentIdx[1]++] == tmpAnswer) {
            studentCnt[1]++;
            studentIdx[1] = studentIdx[1] % student2.length;
        }
        
        if (student3[studentIdx[2]++] == tmpAnswer) {
            studentCnt[2]++;
            studentIdx[2] = studentIdx[2] % student3.length;
        }
    }
    
    // 가장 많이 맞춘 학생구하기
    // 1. 최고 정답 수 구하기
    var maxCnt = 0;
    var maxCntIdx = 0;
    for (var i=0; i<studentCnt.length; i++){    
        if (studentCnt[i] > maxCnt) {
            maxCnt = studentCnt[i];
            maxCntIdx = i;
        }
    }
    
    // 2.동일한 수를 맞춘 학생이 있는지 탐색
    var sameScoreCnt = 0;
    var sameScoreIdx = [];
    for (var i=0; i<studentCnt.length; i++){
        if (maxCnt == studentCnt[i]) {
            sameScoreCnt++;
            sameScoreIdx.push(i);
        }
    }
    
    // 3. 동일한 사람이 있으면 반복, 추가
    if (sameScoreCnt > 1) {
        for (var i=0; i<sameScoreIdx.length; i++){
            answer.push(sameScoreIdx[i]+1);
        }
    } else { // 없으면 최고점자만 추가
        answer.push(maxCntIdx+1);
    }
    
    return answer;
}
```

* 온라인 풀이 참조하여 통과한 버전
  * 비교연산 줄이고, for-in문 사용으로 좀 더 효율이 좋아 처리시간이 단축된게 통과된 요인인듯

```javascript
function solution(answers) {
    var answer = [];
    
    // 학생들 패턴
    var student = [
        [1,2,3,4,5],
        [2,1,2,3,2,4,2,5],
        [3,3,1,1,2,2,4,4,5,5]
    ];
    
    var studentCnt = [0, 0, 0]; // 학생별 맞춘 문제 수
    
    for (var i in answers) { // for-in서 i는 인덱스
        if (student[0][i%5] == answers[i]) { // 따로 idx변수를 두지않고 i를 나눈 나머지를 사용
            studentCnt[0]++;
        }
        if (student[1][i%8] == answers[i]) {
            studentCnt[1]++;
        }
        if (student[2][i%10] == answers[i]) {
            studentCnt[2]++;
        }
    }
    
    // Math.max()사용, Spread Syntax로 배열값 넣어 최대값 구함
    var max = Math.max(...studentCnt);
    
    for (var i in studentCnt) { // for-in에서 최고 정답수를 가질 때 정답에 추가
        if (studentCnt[i] == max) {
            answer.push(Number(i)+1);
        }
    }
    
    return answer;
}
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
