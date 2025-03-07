# Design
## Entity

---
### User

---
### emotion_talk  
User와 GPT의 대화기록
> #### 구조
> - user
> - date
> - user_conversation (ArrayList)
> - assistant_conversation (ArrayList)
- ID : user 당 1일 1회 => id는 user와 date
- 메세지를 대상으로 검색하지 않으므로 message 배열을 JSON으로 바로 저장
- 대화는 항상 한번에 불러오므로 배열로 저장하는게 효율적이라고 판단.
- user의 질문과 assistant(GPT)응답 발화자 별로 별도로 저장. 


---
### emotion
User의 일일 감정상태
> #### 구조
> - user
> - date
> - emotions (ArrayList)
> - reasons	scores (ArrayList)

user당 1일 -> 2개의 emotion



----

## API 설계

- [x] 대화 내용 저장하기
- [x] 대화 내용 가져오기 (특정 날짜만 가져오기)

- [x] 감정 추측 결과 저장하기
- [x] 감정 추측 결과 가져오기 (month 모두 가져오기)
- [x] 감정 추측 결과 가져오기 (특정 날짜만 가져오기)


---
## 로그인 인증 구현
  - [x] user entity
  - [ ] spring boot security
    - [ ] AuthenticationManager 구현
    - [ ] JWT 토큰 생성/검증
      - key provider signWith 부분 문제가 발생함.

---

- [ ] 빌드 이후 리액트 js 구현
  - js axios 수정
