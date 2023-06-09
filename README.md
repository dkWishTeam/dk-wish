# DK테크인 MSA 기반 Full Stack 개발자 양성과정 - 미니 프로젝트①  Wi$h

<br>

## 📋 서비스 소개

> 당신의 소망을 이루는 첫 걸음, Wish와 함께하세요! 당신이 원하는 물건을 구매할 수 있게 도와주며 다른 이들의 위시 내역을 보며 자극을 받을 수 있습니다. Wish는 함께하는 여정에서 당신의 꿈을 현실로
> 만들어드립니다. 지금 Wish와 함께 꿈을 이루어나가보세요!🍀

- 사용자가 원하는 물건을 살때 목표금액을 모으는 과정을 기록하면서 동기부여를 받을 수 있게 도와주는 서비스

### 🙋‍♂️ 서비스 대상

- 큰 지출을 앞두고 있는 사람
  - ex) 여행, 지인 선물, 개인적인 물건 구매
- 계획한 일을 실천할때 기록하면서 동기를 얻는 사람

  <br>

### 2조 - 지니🧞‍

#### 팀원

<div align=center>

| <img src="https://user-images.githubusercontent.com/65614734/234463952-88a0316a-9a7f-46e5-9521-286f1a336fde.png" width=60%> | <img src="https://user-images.githubusercontent.com/65614734/234463967-8c061f78-a3c0-4820-b3fe-860a7619534e.png" width=60%> | <img src="https://user-images.githubusercontent.com/65614734/234463974-fb7a45f9-78a0-4527-b0dc-ecfa94ed2dfe.png" width=60%> | <img src="https://user-images.githubusercontent.com/65614734/234463976-a7153eab-5ba4-4a60-a829-432de60b490d.png" width=60%>|
|:-:|:-:|:-:|:-:|
|[최정우](https://github.com/cofreeman)|[김태현](https://github.com/ffolabear)|[신다인](https://github.com/FunnyDain)|[김주영](https://github.com/JYK75)|

</div>


<br>

### 🛠️ 사용 기술

#### 💻 Back-end

- Java 11
- Spring Boot(2.7.10)
- MyBatis

#### 🖥️ Front-end

- HTML5
- CSS3 (Tailwind CSS)
- ES6 JavaScript
- [Toastr](https://github.com/CodeSeven/toastr)
- AJAX
- Thymeleaf

#### 💾 DB

- MySQL (8.0.31)

<br>

### ✅ 코딩 컨벤션

- [구글 자바 코딩 스타일 가이드](https://google.github.io/styleguide/javaguide.html) 을 채택
   </div>

<br>

### ✅ 커밋 브랜치 규칙

- 기능별 브랜치 생성
- 커밋 메세지 양식

```text
//타입: 제목 
Feat: "추가 로그인 함수"
  
//본문
로그인 API 개발

//꼬리말 (생략가능)
Resolves: #123
Ref: #456
Related to: #48, #45
```

<br>
<hr>

### ⚙️ 서비스 기능

### 🧑‍💻 회원 관련 기능

- 회원가입 및 로그인 기능(사이트 자체 회원가입)
- 다른 사용자들이 등록한 챌린지(이하 위시) 열람 가능
  - 비회원은 상세보기는 불가능
- 로그인한 사용자에 한해서 위시 등록 가능
- 마이페이지 기능

### 🛠️ 관리자 기능

- 관리자는 악성 유저 및 운영 방침을 미준수한 위시를 블락처리할 수 있음
  - 적절한 조치를 취한 후 다시 보이게 할 수있음

### 🍀 위시 관련 기능

- 로그인한 사용자가 마이 위시 페이지에서 자신이 등록한 위시 확인 가능
- 로그인한 사용자가 새로운 위시 등록 가능
  - 사용자는 자신의 위시를 `공개` 또는 `비공개`로 설정 가능
  - 위시에는 `위시제목`, `위시내용`, `상품이름`, `목표금액`, `목표날짜` 정보를 입력할 수 있음
- 마이 위시에서 상세보기를 클릭한 `위시기록` 의 추가 및 수정과 위시 상세정보 수정 가능

### ⌛️ 추가 예정 기능

- 위시에 좋아요 기능정
- 다른 사람과 같이 위시 진행하기 기능

<br>
<br>