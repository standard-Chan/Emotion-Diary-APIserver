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