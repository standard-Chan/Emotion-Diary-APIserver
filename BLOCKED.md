
## 25.03.03

### Security 무한 순환 문제
UserDetailsService을 잘못 import해서 발생



### Security JWT Secret Key 문제

로그인 이후 token 요청시 secret key 를 사용하는 부분에서 다음 문제 발생.
> javax.xml.bind.DataTypeConverter

JDK 과거버전에만 있는 거라 의존성에 아래 추가.
implementation 'javax.xml.bind:jaxb-api:2.3.1'