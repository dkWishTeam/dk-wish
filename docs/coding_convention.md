
## 코딩 컨벤션
- [구글 코딩 컨벤션](https://google.github.io/styleguide/javaguide.html#s4-formatting)을 따름
- 이 문서에서는 다른 부분 및 주요 부분만 명시

### Formatting
- 블럭 들여쓰기: +4 스페이스
    - 1`tab` = 4`space`
    - 새 블록 또는 블록과 유사한 구조가 열릴 때마다 들여쓰기가 네 칸씩 증가
    - 블록이 끝나면 들여쓰기는 이전 들여쓰기 단계로 돌아감
    - 들여쓰기 단계는 블록 전체의 코드와 주석 모두에 적용
    - 가독성을 향상시키기 위해서 빈 줄을 추가할 수 있음
  ```java
  class Wish{
      public wish(){
      }
  
      public static void main(String[] args){
          int result = 0;
          for (int i=0; i < 10; i++){
              result += i;
          }
  
      }
  }    
  ```

- 열 제한: 120


<br>

### Naming

### 패키지 네이밍
- 소문자 or 숫자로 명명
- 카멜케이스 사용 ❌
- 올바른 방법 🟢 : `com.example.userwish`
- 잘못된 방법 ⛔️ : `com.example.userWish`, `com.example.UserWish`, `com.example.user_wish`


### 클래스 네이밍
- `CamelCase` 사용
- 클래스의 기능을 유추할 수 있는 **명사**를 사용
- 올바른 방법 🟢 : `UserController`,
- 잘못된 방법 ⛔️ : `ControlUser`, `WriteWish`, `userTest`


### 인터페이스 네이밍
- `CamelCase` 사용
- `Interface` 단어는 붙이지 않아도 됨
- 인터페이스의 기능을 유추할 수 있는 **명사 또는 형용사**를 사용
- 올바른 방법 🟢 : `Validation`, `Runnable`
- 잘못된 방법 ⛔️ : `userController`, `usercontroller`, `ValidateInterface`


### 메소드 네이밍
- `camelCase` 사용
- 동사 혹은 동사구를 사용
- 올바른 방법 🟢 : `sendMessage`, `stop`
- 잘못된 방법 ⛔️ : `message`, `userWish`

### 기타 네이밍

#### 상수
- `UPPER_SNAKE_CASE` 사용
- 올바른 방법 🟢 : `MAXIMUM_USER_WISH`, `USER_DEFAULT_ID`, `static final Map<String, Integer> AGES = ...`
- 잘못된 방법 ⛔️ : `userId`, `userWish`

#### 상수가 아닌 필드, 지역 변수,
- `lowerCamelCase` 사용
- 반복문 안의 변수는 `i`, `j`, `k`, `l`, `m`, `n`... 순으로 사용
- 명사 혹은 명사구 사용
- 올바른 방법 🟢 : `myWishList`
- 잘못된 방법 ⛔️ : `generateWish`, `GenerateWish`,


<br>
<br>