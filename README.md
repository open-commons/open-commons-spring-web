# open-commons-spring-web
Open Commons for Spring Web on Spring Boot 

---
# News
[2025/06/09]
- 'OpenCommonsSpringWeb' autoconfigure 적용.
  - [자세히 보기](https://github.com/open-commons/open-commons-spring-web/wiki/about-EnableOpenCommonsSpringWeb)

[2025/04/17]
- Http Session 관련 보안 기능 제공
  - 주요 클래스: [SecurityUtils.java](https://github.com/open-commons/open-commons-spring-web/blob/main/src/main/java/open/commons/spring/web/utils/SecurityUtils.java)
  - 사용법: [바로가기](https://github.com/open-commons/open-commons-spring-web/wiki/How-to-SecurityUtils-class)

[2025/04/08]
- [GroupedOpenApi 외부설정](https://github.com/open-commons/open-commons-spring-web/wiki/외부설정파일을-이용하여-GroupedOpenApi-등록하기)<br>
 "Class org.springdoc.core.GroupedOpenApi" 등록 정보를 외부 설정파일(application.yml, application.properties)에 작성하여 자동으로 등록되는 기능을 지원합니다.  

---
# History
See [history.md](./history.md).

---
# HOWTO
See [wiki](https://github.com/open-commons/open-commons-spring-web/wiki)

---
# 'release' Repository
NOT YET (**[Go to LATEST](https://central.sonatype.com/artifact/io.github.open-commons/open-commons-spring-web)**)

# 'snapshot' Repository
**[Go to LATEST](https://nexus3.ymtech.co.kr/#browse/browse:maven-public:io%2Fgithub%2Fopen-commons%2Fopen-commons-spring-web)**

Add 'Repository'.

``` xml
<repositories>
  <repository>
    <id>ymtech.co.kr</id>
    <name>YMTECH Maven Repository</name>
    <url>https://nexus3.ymtech.co.kr/repository/maven-public/</url>
    <layout>default</layout>
  </repository>
</repositories>
```
