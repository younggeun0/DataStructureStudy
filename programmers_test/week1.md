## 프로그래머스 문제풀이 1

* [가운데 글자 가져오기](https://programmers.co.kr/learn/courses/30/lessons/12903)

### younggeun0

```javascript
function solution(s) {
    var answer = '';
    var length = s.length;
    
    if (length % 2 == 0) { // 짝수면 두글자
        // "abcdef" => cd
        // length = 6, 23번 인덱스, length/2 => 3
        //  012345
        answer = s.substring(length/2-1, length/2+1);
        
    } else { // 짝수 아니면 한글자
        // "abcde" => c
        // length = 5, 2번 인덱스, length/2 => 2
        //  01234
        answer = s.substring(length/2, length/2+1);
    }
    
    return answer;
}
```

### minj0i

```JAVA
class Solution {
  public String solution(String s) {
      String answer = "";
      int solutionlength = s.length();
      int middle = (solutionlength/2);
      
      if(solutionlength >= 1 && solutionlength < 101) {
          if(solutionlength % 2 == 1){
             answer =  s.substring(middle ,middle+1);    
          } else {
              answer = s.substring(middle-1, middle+1);
          }
          return answer;
        }
      return "제한사항 에러";
  }//solution
}//class
```

### rlawjddbs

```JAVA
class Solution {
    public String solution(String s) {
        String result ="1~100 사이의 숫자 입력";
        
        if(s.length() > 0 && s.length() < 101) {
            int num = s.length() / 2;
            if(s.length() % 2 == 0) {
                result = s.substring(num-1, num+1);
            } else {
                result = s.substring(num, num+1);
            } // end else		
        } // end if
        return result;
    }
}
```

### sgmsgood

```java
class Solution {
  public String solution(String s) {
      String answer = "";
      int letterPosition = 0;
      char a;
      
      letterPosition = (s.length() / 2);
      
      if(s.length() %2 == 1){
          a = s.charAt(letterPosition);
          answer = String.valueOf(a);
      }else{
          answer = s.substring(letterPosition-1, letterPosition+1);
      }
      
      return answer;
  }
}
```

---

* [서울에서 김서방 찾기](https://programmers.co.kr/learn/courses/30/lessons/12919)

### younggeun0 

```java
class Solution { // Java풀이
  public String solution(String[] seoul) {
      String answer = "";
      
      // 풀이1
      int idx = 0;
      for(String name : seoul) {
          if ("Kim".equals(name)) {
              break;
          } else {
              idx++;
          }
      }
      
      // 풀이2, 프로그래머스에서 동작은 안함
      // int idx = java.util.Arrays.asList(seoul).indexOf("Kim");
      // int idx = java.util.Arrays.binarySearch(seoul, "Kim");
      
      answer = "김서방은 "+idx+"에 있다";
      
      return answer;
  }
}
```

```javascript
function solution(seoul) { // JavaScript 풀이
    var answer = '';
    
    var idx = seoul.indexOf('Kim');
    
    answer = "김서방은 "+idx+"에 있다";
    
    return answer;
}
```

### minj0i

### rlawjddbs

### sgmsgood

