## 프로그래머스 문제풀이 week1

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