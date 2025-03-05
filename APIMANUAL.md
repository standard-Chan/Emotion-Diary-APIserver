# API manual

---
### request (JWT)
인증을 위해 다음 헤더를 추가
```
예시 (json)
 headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        }
```
## 
### api/emotion/conversation

> String userEmail;
> String date;
> String userMessage;
> String assistantMessage;

```
{
    "userEmail" : "123@naver.com",
    "date" : "20240304",
    "userMessage" :  "사용자의 말",
    "assistantMessage" :  "GPT response"
}
```
### api/emotions

> String userEmail;
> String date;
> ArrayList<String> emotions;
> ArrayList<String> reasons;
> ArrayList<Integer> scores;
```
예시
{
    "userEmail" : "123@naver.com",
    "date" : "20240305",
    "emotions" : ["설렘", "아픔"],
    "reasons" : ["내일도 살아갈 수 있다는 사실에 설렙니다.", "공부를 오래해서 허리가 아픕니다."],
    "scores" : [6, 5]
}
```

### api/user/signup

```
{
    email: "321@naver.com",
    password: "112233"
}
```

### api/user/login

```
{
    email: "321@naver.com",
    password: "112233"
}
```