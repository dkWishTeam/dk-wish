## 커밋 컨벤션
- [AngularJS commit conventions](https://gist.github.com/stephenparish/9941e89d80e2bc58a153) 을 참고

<br>

### 커밋 메세지 
```text
[헤더]
        //공백
[본문]
        //공백
[푸터]
```

### 헤더 
```text
[type]: subject

ex)
feat(WishController): wish 작성 컨트롤러로 이동 하는 메서드 추가 
docs(README): 화면 레이아웃 프로토타입 추가 
chore(WishWrite): 공백 제거 및 변수명 수정
```

#### Type
- `Feat` : 새로운 기능을 추가할 경우
- `Fix` : 버그를 고친 경우
- `Design` : CSS 등 사용자 UI 디자인 변경
- `!BREAKING CHANGE` : 커다란 API 변경의 경우
- `!HOTFIX` : 급하게 치명적인 버그를 고쳐야하는 경우
- `Style` : 코드 포맷 변경, 세미 콜론 누락, 코드 수정이 없는 경우
- `Refactor` : 프로덕션 코드 리팩토링
- `Comment` : 필요한 주석 추가 및 변경
- `Docs` : 문서를 수정한 경우
- `Test` : 테스트 추가, 테스트 리팩토링(프로덕션 코드 변경 X)
- `Chore` : 빌드 태스트 업데이트, 패키지 매니저를 설정하는 경우(프로덕션 코드 변경 X)
- `Rename` : 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우
- `Remove` : 파일을 삭제하는 작업만 수행한 경우

<br>

#### Subject
- 변경한 내용에 대한 제목을 한글로 작성
- 명령문으로 작성하며 현재형으로 작성
- 메세지의 끝에 마침표 `.`를 붙이지 않음
- 올바른 방법 🟢 : `WishController에 새로운 메서드 추가`, `빌드 파일 수정`
- 잘못된 방법 ⛔️ : `WiShController에 필드 추가 했음`, `빌드 파일 수정.`, `Add new method to WishController`

<br>

### 본문
```text
ex)
- WishController 에서 반복되는 불필요한 객체 생성 부분 삭제
- WishCreate 에서 wishMoney 에 타입에 맞지 않는 입력값 입력시 예외처리 부분 추가
```

- 변경 내용의 상세를 한글로 작성
- 명령문으로 작성하며 현재형으로 작성
- 변경에 대한 짧은 설명을 작성
- 변경에 대한 동기와 변경 이전과의 비교를 추가로 작성 가능


<br>

### 푸터
```
해결: #5
참고: #3
```
- 해결한 이슈 커밋의 ID, 해결하기 위해 참고한 커밋의 ID 등을 작성
- 필수 사항은 아니며 생략 가능

<hr>

### PR 컨벤션
```text
## 💡 Motivation

## 🔑 Key Changes

## 🗒️ Todo

```

### Motivation
- 커밋을 하게된 이유                                        

### Key Changes
- 수정의 주요 변경 사항

### Todo
- 커밋 이후 추가로 필요한 작업이 있다면 작성
- 작성내용이 없다면 생략 가능


<br>