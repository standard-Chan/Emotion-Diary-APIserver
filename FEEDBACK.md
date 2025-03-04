
## 25.03.03

### Security 무한 순환 문제
UserDetailsService을 잘못 import해서 발생



### Security JWT Secret Key 문제

로그인 이후 token 요청시 secret key 를 사용하는 부분에서 다음 문제 발생.
> javax.xml.bind.DataTypeConverter

JDK 과거버전에만 있는 거라 의존성에 아래 추가.
implementation 'javax.xml.bind:jaxb-api:2.3.1'

### request body 예시를 적어놓지 않았음
각각 api의 request body를 적어놓지 않아서 일일이 DTO를 확인해야하는 번거로움이 발생.
앞으로는 md 파일이나 코드에 별도로 예시를 적어놓는 습관을 가지자.