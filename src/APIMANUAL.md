# API manual

---
### request (JWT)
다음 헤더를 추가
```
 headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        }
```


### api/emotions
```
{
    "userEmail" : "123@naver.com",
    "date" : "20240305",
    "emotions" : ["설렘", "아픔"],
    "reasons" : ["내일도 살아갈 수 있다는 사실에 설렙니다.", "공부를 오래해서 허리가 아픕니다."],
    "scores" : [6, 5]
}
```

### 