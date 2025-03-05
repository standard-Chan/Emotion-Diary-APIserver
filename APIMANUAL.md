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
##  api/emotion/conversation
### POST

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

### Get

> String userEmail;
> String date;
> String userMessage;
> String assistantMessage;

```
{
    "userEmail" : "123@naver.com",
    "date" : "20240304",
    "userMessage" :  ["사용자의 말", "..."]
    "assistantMessage" :  ["...", "GPT response"]
}
```


## /api/emotion/result

### POST
> String userEmail;
> String date;
> String advice
> ArrayList<String> emotions;
> ArrayList<String> reasons;
> ArrayList<Integer> scores;
```
response body
{
    "userEmail" : "123@naver.com",
    "date" : "20240305",
    "advice" : "휴식이 필요해보여요",
    "emotions" : ["설렘", "아픔"],
    "reasons" : ["내일도 살아갈 수 있다는 사실에 설렙니다.", "공부를 오래해서 허리가 아픕니다."],
    "scores" : [6, 5]
}
```

---

### GET
> key : email = ""
> key : date = ""

> String userEmail;
> String date;
> String advice;
> ArrayList<String> emotions;
> ArrayList<String> reasons;
> ArrayList<Integer> scores;
```
response body
{
    "userEmail" : "123@naver.com",
    "date" : "20240305",
    "advice" : "휴식이 필요해보여요",
    "emotions" : ["설렘", "아픔"],
    "reasons" : ["내일도 살아갈 수 있다는 사실에 설렙니다.", "공부를 오래해서 허리가 아픕니다."],
    "scores" : [6, 5]
}
```

---

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