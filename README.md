# EMOTION-DIARY SPRING BOOT SERVER

---

> 도메인 주소 : https://standard-chan.com


## JWT 인증 필요

signup, login request는 예외처리.

다른 request는 모두 token이 필요하므로,
signup을 통해서 token을 얻은 이후 다른 request를 사용할 것.

---

## 1. User Authentication API

### **POST** `/api/user/signup`
- 회원가입 처리

#### **Request Body**
```json
{
    "email": "321@naver.com",
    "password": "112233"
}
```

#### **Response**
- 성공 시 HTTP 상태 코드 `201 Created`

---

### **POST** `/api/user/login`
- 사용자 로그인 처리

#### **Request Body**
```json
{
    "email": "321@naver.com",
    "password": "112233"
}
```

#### **Response**
- 성공 시 : ok. 및 **JWT token** 반환


---

## 2. Emotion Conversation API
대화 저장 및 불러오기

### **POST** `/api/emotion/conversation`
- 사용자의 대화 내용 저장

#### **Request Body**
```json
{
    "userEmail" : "123@naver.com",
    "date" : "20240304",
    "userMessage" : "사용자의 말",
    "assistantMessage" : "GPT response"
}
```

#### **Response**
- 성공 시 HTTP 상태 코드 `201 CREATE`

---

### **GET** `/api/emotion/conversation`
- 특정 사용자의 대화 목록을 조회합니다.

#### **Query Parameters**
| Parameter  | Type   | Description |
|------------|--------|-------------|
| `userEmail` | String | 사용자 이메일 |
| `date` | String | 조회할 날짜 (YYYYMMDD) |

#### **Response Body**
```json
{
    "userEmail" : "123@naver.com",
    "date" : "20240304",
    "userMessage" : ["사용자의 말", "..."],
    "assistantMessage" : ["...", "GPT response"]
}
```

#### **Response Codes**
- `200 OK` : 성공 및 대화 내용 JSON 반환

---

## 3. Emotion Result API

### **POST** `/api/emotion/result`
- 감정 분석 결과 저장

#### **Request Body**
```json
{
    "userEmail" : "123@naver.com",
    "date" : "20240305",
    "advice" : "때로는 휴식을 취할 필요가 있습니다.",
    "emotions" : ["설렘", "아픔"],
    "reasons" : ["내일도 살아갈 수 있다는 사실에 설렙니다.", "공부를 오래해서 허리가 아픕니다."],
    "scores" : [6, 5]
}
```

#### **Response**
- 성공 시 HTTP 상태 코드 `201 CREATAED`
- 실패 시 HTTP 상태 코드 `400 Bad Request`

---

### **GET** `/api/emotion/result/day`
- 특정 날짜에 대한 감정 분석 결과 조회.

#### **Query Parameters**
| Parameter  | Type   | Description |
|------------|--------|-------------|
| `email` | String | 사용자 이메일 |
| `date` | String | 조회할 날짜 (YYYYMMDD) |

#### **Response Body**
```json
{
    "userEmail" : "123@naver.com",
    "date" : "20240305",
    "advice" : "휴식이 필요해보여요",
    "emotions" : ["설렘", "아픔"],
    "reasons" : ["내일도 살아갈 수 있다는 사실에 설렙니다.", "공부를 오래해서 허리가 아픕니다."],
    "scores" : [6, 5]
}
```

#### **Response Codes**
- `200 OK` : 성공. 해당 날짜의 감정 기록 결과

### **GET** `/api/emotion/result/month`
- 한달동안의 감정 분석 결과 조회.

#### **Query Parameters**
| Parameter  | Type   | Description |
|------------|--------|-------------|
| `email` | String | 사용자 이메일 |
| `date` | String | 조회할 날짜 (YYYYMMDD) |

#### **Response Body**
```json
[
  {
    "userEmail" : "123@naver.com",
    "date" : "20240305",
    "advice" : "휴식이 필요해보여요",
    "emotions" : ["설렘", "아픔"],
    "reasons" : ["내일도 살아갈 수 있다는 사실에 설렙니다.", "공부를 오래해서 허리가 아픕니다."],
    "scores" : [6, 5]
  }, 
  {
    ...
  }, ...]
```

#### **Response Codes**
- `200 OK` : 한달동안의 감정 결과 데이터 셋

---

