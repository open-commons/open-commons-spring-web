[2023/05/18]
- Release: 0.6.0-SNAPSHOT
- Tag: 0.5.0

[2023/05/18]
- Release: 0.5.0

[2023/05/12]
- Update
  + open.commons.spring.web.rest.RestUtils2
    + exchange(Supplier&lt;ResponseEntity&lt;RES&gt;&gt;, HttpMethod, URI, int, HttpEntity&lt;REQ&gt;, Object, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RET&gt;&gt;, Function&lt;Exception, Result&lt;RET&gt;&gt;): 에러 로그 추가


[2023/03/06]
- Add
  + open.commons.spring.web.rest.RestUtils2
    + exchange(RestTemplate, HttpMethod, String, String, int, String, int, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RET&gt;&gt;, Function&lt;Exception, Result&lt;RET&gt;&gt;)
    + exchange(RestTemplate, HttpMethod, String, String, int, String, int, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RET&gt;&gt;, Function&lt;Exception, Result&lt;RET&gt;&gt;)
    + exchange(RestTemplate, HttpMethod, String, String, int, String, String, int, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RET&gt;&gt;, Function&lt;Exception, Result&lt;RET&gt;&gt;)
    + exchange(RestTemplate, HttpMethod, String, String, int, String, String, int, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RET&gt;&gt;, Function&lt;Exception, Result&lt;RET&gt;&gt;)
    + exchange(RestTemplate, HttpMethod, URI, int, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RET&gt;&gt;, Function&lt;Exception, Result&lt;RET&gt;&gt;)
    + exchange(RestTemplate, HttpMethod, URI, int, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RET&gt;&gt;, Function&lt;Exception, Result&lt;RET&gt;&gt;)
    + exchange(Supplier&lt;ResponseEntity&lt;RES&gt;&gt;, HttpMethod, URI, int, HttpEntity&lt;REQ&gt;, Object, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RET&gt;&gt;, Function&lt;Exception, Result&lt;RET&gt;&gt;)

[2022/12/01]
- Add
  + open.commons.spring.web.servlet.NotFoundException

[2022/11/29]
- Add
  + open.commons.spring.web.mvc.service.IConvertingService.transformAll(S, Class&lt;T&gt;): 
- Delete
  + open.commons.spring.web.mvc.service.IConvertingService.transferAll(S, Class&lt;T&gt;):  

[2022/11/25]
- Add
  + open.commons.spring.web.mvc.service.IConvertingService
    + convertMultiResult(Result&lt;List&lt;S&gt;&gt;, Class&lt;T&gt;)
    + convertSingleResult(Result&lt;S&gt;, Class&lt;T&gt;)
    + transferAll(S, Class&lt;T&gt;)

[2022/11/17]
- Dependencies
	+ spring-core.version: 5.3.23 고정
	
[2022/05/04]
- New
  + open.commons.spring.web.utils.ArgumentsUtils: Application Argument에 대한 지원 기능을 제공.

[2022/05/04]
- Add
  + open.commons.spring.web.mvc.service.AbstractComponent
    + getMultiValuesArgument(ApplicationArguments, String, Class&lt;T&gt;)
    + getMultiValuesArguments(ApplicationArguments, Map&lt;String, Class&lt;?&gt;&gt;)
    + getSingleValueArgument(ApplicationArguments, String, Class&lt;T&gt;)
    + getSingleValueArguments(ApplicationArguments, Map&lt;String, Class&lt;?&gt;&gt;)

[2022/04/07]
- Release: 0.5.0-SNAPSHOT
- Tag: 0.4.0
- Dependencies:
  + open.commons.core: 2.0.0-SNAPSHOT
  
[2022/04/07]
- Release: 0.4.0

[2022/02/11]
- Modify
  + open.commons.spring.web.mvc.service.AbstractMvcService: SearchResultType에 따라서 Pageable 값 자동 조정
    + selectMulti(SearchResultType, Function&lt;String[], Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, Pageable)
    + selectMulti(SearchResultType, Function&lt;String[], Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, Pageable, Function&lt;E, D&gt;)
    + selectMulti(SearchResultType, P, BiFunction&lt;P, String[], Result&lt;List&lt;E&gt;&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, Pageable)
    + selectMulti(SearchResultType, P, BiFunction&lt;P, String[], Result&lt;List&lt;E&gt;&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, Pageable, Function&lt;E, D&gt;)
    + selectMultiPagination(SearchResultType, P, Function&lt;P, Result&lt;Integer&gt;&gt;, BiFunction&lt;P, String[], Result&lt;List&lt;E&gt;&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, Pageable, Function&lt;E, D&gt;)
    + selectMultiPagination(SearchResultType, Supplier&lt;Result&lt;Integer&gt;&gt;, Function&lt;String[], Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, Pageable, Function&lt;E, D&gt;)

[2022/02/10]
- Add
  + open.commons.spring.web.utils.PaginationUtils
  
[2022/01/26]
- Add
  + open.commons.spring.web.mvn.service.AbstractMvcService
    + transform(S, boolean, T, boolean)
    + transform(S, T)

[2022/01/10]
- Add
  + open.commons.spring.web.mvc.service.AbstractMvcService
    + selectMulti(SearchResultType, Function&lt;String[], Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, Pageable)
    + selectMulti(SearchResultType, Function&lt;String[], Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, Pageable, Function&lt;E, D&gt;)
    + selectMulti(SearchResultType, P, BiFunction&lt;P, String[], Result&lt;List&lt;E&gt;&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, Pageable)
    + selectMulti(SearchResultType, P, BiFunction&lt;P, String[], Result&lt;List&lt;E&gt;&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, Pageable, Function&lt;E, D&gt;)
    + selectMultiPagination(P, Function&lt;P, Result&lt;Integer&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, Pageable)
    + selectMultiPagination(P, Function&lt;P, Result&lt;Integer&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, Pageable, Function&lt;E, D&gt;)
    + selectMultiPagination(P, Function&lt;P, Result&lt;Integer&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String...)
    + selectMultiPagination(P, Function&lt;P, Result&lt;Integer&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String[], Function&lt;E, D&gt;)
    + selectMultiPagination(P, Supplier&lt;Result&lt;Integer&gt;&gt;, TripleFunction&lt;P, Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int)
    + selectMultiPagination(P, Supplier&lt;Result&lt;Integer&gt;&gt;, TripleFunction&lt;P, Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int, Function&lt;E, D&gt;)
    + selectMultiPagination(SearchResultType, P, Function&lt;P, Result&lt;Integer&gt;&gt;, BiFunction&lt;P, String[], Result&lt;List&lt;E&gt;&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, Pageable, Function&lt;E, D&gt;)
    + selectMultiPagination(SearchResultType, Supplier&lt;Result&lt;Integer&gt;&gt;, Function&lt;String[], Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, Pageable, Function&lt;E, D&gt;)
    + selectMultiPagination(Supplier&lt;Result&lt;Integer&gt;&gt;, BiFunction&lt;Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int)
    + selectMultiPagination(Supplier&lt;Result&lt;Integer&gt;&gt;, BiFunction&lt;Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int, Function&lt;E, D&gt;)
    + selectMultiPagination(Supplier&lt;Result&lt;Integer&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, Pageable, Function&lt;E, D&gt;)
    + selectMultiPagination(Supplier&lt;Result&lt;Integer&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String...)
    + selectMultiPagination(Supplier&lt;Result&lt;Integer&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String[], Function&lt;E, D&gt;)

[2021/12/29]
- Modify
  + open.commons.spring.web.mvc.service.AbstractMvcService: DTO Class&lt;?&gt; 파라미터 제거
    + selectMulti(SearchResultType, Function&lt;String[], Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String[], Function&lt;E, D&gt;)
    + selectMulti(SearchResultType, P, BiFunction&lt;P, String[], Result&lt;List&lt;E&gt;&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String[], Function&lt;E, D&gt;)
    + selectMulti(SearchResultType, P, Function&lt;P, Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;P, Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int, Function&lt;E, D&gt;)
    + selectMulti(SearchResultType, Supplier&lt;Result&lt;List&lt;E&gt;&gt;&gt;, BiFunction&lt;Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int, Function&lt;E, D&gt;)
    + selectMultiPagination(SearchResultType, P, Function&lt;P, Result&lt;Integer&gt;&gt;, BiFunction&lt;P, String[], Result&lt;List&lt;E&gt;&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String[], Function&lt;E, D&gt;)
    + selectMultiPagination(SearchResultType, P, Supplier&lt;Result&lt;Integer&gt;&gt;, Function&lt;P, Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;P, Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int, Function&lt;E, D&gt;)
    + selectMultiPagination(SearchResultType, Supplier&lt;Result&lt;Integer&gt;&gt;, Function&lt;String[], Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String[], Function&lt;E, D&gt;)
    + selectMultiPagination(SearchResultType, Supplier&lt;Result&lt;Integer&gt;&gt;, Supplier&lt;Result&lt;List&lt;E&gt;&gt;&gt;, BiFunction&lt;Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int, Function&lt;E, D&gt;)
  + open.commons.spring.web.mvc.service.IConvertingService: 변환 이후 Class&lt;?&gt; 파라미터 제거
    + convertMultiPaginationResult(Result&lt;Page&lt;S&gt;&gt;, Function&lt;S, T&gt;)
    + convertMultiResult(List&lt;S&gt;, Function&lt;S, T&gt;)
    + convertMultiResult(Result&lt;List&lt;S&gt;&gt;, Function&lt;S, T&gt;)
    + convertMultiResultAsStream(List&lt;S&gt;, Function&lt;S, T&gt;)
    + convertSingleResult(Result&lt;S&gt;, Function&lt;S, T&gt;)

[2021/12/29]
- Add
  + open.commons.spring.web.mvc.service.AbstractMvcService
    + executePagination(Supplier&lt;Result&lt;List&lt;E&gt;&gt;&gt;, Supplier&lt;Result&lt;Integer&gt;&gt;, int, int, String[])
    + orderBy(String...)
    + selectMultiPagination(SearchResultType, P, Function&lt;P, Result&lt;Integer&gt;&gt;, BiFunction&lt;P, String[], Result&lt;List&lt;E&gt;&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String...)
    + selectMultiPagination(SearchResultType, P, Function&lt;P, Result&lt;Integer&gt;&gt;, BiFunction&lt;P, String[], Result&lt;List&lt;E&gt;&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String[], Class&lt;D&gt;, Function&lt;E, D&gt;)
    + selectMultiPagination(SearchResultType, P, Supplier&lt;Result&lt;Integer&gt;&gt;, Function&lt;P, Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;P, Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int)
    + selectMultiPagination(SearchResultType, P, Supplier&lt;Result&lt;Integer&gt;&gt;, Function&lt;P, Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;P, Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int, Class&lt;D&gt;, Function&lt;E, D&gt;)
    + selectMultiPagination(SearchResultType, Supplier&lt;Result&lt;Integer&gt;&gt;, Function&lt;String[], Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String...)
    + selectMultiPagination(SearchResultType, Supplier&lt;Result&lt;Integer&gt;&gt;, Function&lt;String[], Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String[], Class&lt;D&gt;, Function&lt;E, D&gt;)
    + selectMultiPagination(SearchResultType, Supplier&lt;Result&lt;Integer&gt;&gt;, Supplier&lt;Result&lt;List&lt;E&gt;&gt;&gt;, BiFunction&lt;Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int)
    + selectMultiPagination(SearchResultType, Supplier&lt;Result&lt;Integer&gt;&gt;, Supplier&lt;Result&lt;List&lt;E&gt;&gt;&gt;, BiFunction&lt;Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int, Class&lt;D&gt;, Function&lt;E, D&gt;)
  + open.commons.spring.web.mvc.service.IConvertingService
    + convertMultiPaginationResult(Result&lt;Page&lt;S&gt;&gt;, Class&lt;T&gt;, Function&lt;S, T&gt;)
  
[2021/12/24]
- Add
  + open.commons.spring.web.mvc.service.AbstractMvcService
    + save(List&lt;D&gt;, Class&lt;E&gt;, Function&lt;D, E&gt;, Function&lt;List&lt;E&gt;, Result&lt;Integer&gt;&gt;): DTO -> Entity로 저장하는 함수.

[2021/12/22]
- Add
  + open.commons.spring.web.mvc.service.AbstractMvcService
    + transform(S, boolean, Class&lt;T&gt;, boolean)
    + transform(S, Class&lt;T&gt;)

[2021/12/20]
- Bugfix
  + open.commons.spring.web.rest.RestApiDecl
    + getBody()
    + getHeaders()

[2021/12/16]
- Add
  + open.commons.spring.web.validation.EnumConstraintValidator&lt;C extends Annotation, T extends Enum&lt;T&gt;&gt;: Enum&lt;T&gt; 데이터를 검증하는 상위 클래스 정의

[2021/12/15]
- Add
  + open.commons.spring.web.mvc.service.AbstractComponent
    + streamOf(boolean, T...)
    + streamOf(String, String, T...)
- <strike>Add
  + open.commons.spring.web.mvc.service.AbstractMvcService
    + streamOf(boolean, T...)
    + streamOf(String, String, T...)</strike>

[2021/12/10]
- Add
  + open.commons.spring.web.mvc.service.AbstractMvcService
- Delete
  + open.commons.spring.web.mvc.service.AbstractGenericService: 아래 메소드를 'open.commons.spring.web.mvc.service.AbstractMvcService'로 이관. 
    + selectMulti(SearchResultType, Function&lt;String[], Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String...)
    + selectMulti(SearchResultType, Function&lt;String[], Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String[], Class&lt;D&gt;, Function&lt;E, D&gt;)
    + selectMulti(SearchResultType, P, BiFunction&lt;P, String[], Result&lt;List&lt;E&gt;&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String...)
    + selectMulti(SearchResultType, P, BiFunction&lt;P, String[], Result&lt;List&lt;E&gt;&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String[], Class&lt;D&gt;, Function&lt;E, D&gt;)
    + selectMulti(SearchResultType, P, Function&lt;P, Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;P, Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int)
    + selectMulti(SearchResultType, P, Function&lt;P, Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;P, Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int, Class&lt;D&gt;, Function&lt;E, D&gt;)
    + selectMulti(SearchResultType, Supplier&lt;Result&lt;List&lt;E&gt;&gt;&gt;, BiFunction&lt;Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int)
    + selectMulti(SearchResultType, Supplier&lt;Result&lt;List&lt;E&gt;&gt;&gt;, BiFunction&lt;Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int, Class&lt;D&gt;, Function&lt;E, D&gt;)

[2021/12/09]
- Modify
  + open.commons.spring.web.mvc.service.AbstractGenericService: 파라미터 순서 변경
    + selectMulti(SearchResultType, P, BiFunction&lt;P, String[], Result&lt;List&lt;E&gt;&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String...)
    + selectMulti(SearchResultType, P, BiFunction&lt;P, String[], Result&lt;List&lt;E&gt;&gt;&gt;, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String[], Class&lt;D&gt;, Function&lt;E, D&gt;)
    + selectMulti(SearchResultType, P, Function&lt;P, Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;P, Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int)
    + selectMulti(SearchResultType, P, Function&lt;P, Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;P, Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int, Class&lt;D&gt;, Function&lt;E, D&gt;)
- Add
  + open.commons.spring.web.mvc.service.AbstractGenericService
    + selectMulti(SearchResultType, BiFunction&lt;P, String[], Result&lt;List&lt;E&gt;&gt;&gt;, P, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String...)
    + selectMulti(SearchResultType, BiFunction&lt;P, String[], Result&lt;List&lt;E&gt;&gt;&gt;, P, QuadFunction&lt;P, Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String[], Class&lt;D&gt;, Function&lt;E, D&gt;)
    + selectMulti(SearchResultType, Function&lt;String[], Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String...)
    + selectMulti(SearchResultType, Function&lt;String[], Result&lt;List&lt;E&gt;&gt;&gt;, TripleFunction&lt;Integer, Integer, String[], Result&lt;List&lt;E&gt;&gt;&gt;, int, int, String[], Class&lt;D&gt;, Function&lt;E, D&gt;)
    
[2021/12/08]
- Add
  + open.commons.spring.web.mvc.service.AbstractGenericService
    + selectMulti(SearchResultType, Function&lt;P, Result&lt;List&lt;E&gt;&gt;&gt;, P, TripleFunction&lt;P, Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int)
    + selectMulti(SearchResultType, Supplier&lt;Result&lt;List&lt;E&gt;&gt;&gt;, BiFunction&lt;Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int)
- Update
  + open.commons.spring.web.config.CustomWebMvcConfigurer
    + addFormatters(FormatterRegistry): Enum 검색 패키지 확장.
      - 'open.commons' 기본값으로 설정.

[2021/12/06]
- Update
  + open.commons.spring.web.mv.service.AbstractGenericService
    + implements open.commons.spring.web.mv.service.IConvertingService
    + selectMulti(SearchResultType, Function&lt;P, Result&lt;List&lt;E&gt;&gt;&gt;, P, TripleFunction&lt;P, Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int, Class&lt;D&gt;, Function&lt;E, D&gt;)
    + selectMulti(SearchResultType, Function&lt;Result&lt;List&lt;E&gt;&gt;&gt;, P, BiFunction&lt;Integer, Integer, Result&lt;List&lt;E&gt;&gt;&gt;, int, int, Class&lt;D&gt;, Function&lt;E, D&gt;)
  
  
- New
  + open.commons.spring.web.mv.service.IConvertingService
    + convertMultiResultAsStream(List&lt;S&gt;, Class&lt;T&gt;, Function&lt;S, T&gt;)
  
[2021/12/03]
- New
  + open.commons.spring.web.mv.service.IConvertingService

[2021/11/16]
- Add
  + open.commons.spring.web.mv.service.CliExecutionComponent

[2021/11/09]
- Add
  + open.commons.spring.web.mvn.service.AbstractComponent
    + execute(Consumer&lt;T&gt;, T, String)
    + execute(Function&lt;T, R&gt;, T, String)
    + execute(Runner, String)

[2021/10/04]
- Updated
  + open.common.spring.web.rest.RestApiDecl: 설정 데이터 변경을 막기 위한 조치.
    + getHeaders()
    + getMethod()
    + setBody(MultiValueMap&lt;String, Object&gt;)
    + setHeaders(MultiValueMap&lt;String, String&gt;)
  
[2021/10/04]
- Add
  + open.commonad.spring.web.mvc.service.AbstractComponent
    - execute(Supplier&lt;T&gt;, String)

[2021/09/16]
- Bugfix
  + open.commons.spring.web.event.AbstractEventDrivenMonitor.UnsubscriedParametersClosure  
    + contains(String, Object): 포함 여부 변수의 혼용사용에 따른 버그 수정

[2021/09/09]
- Add
  + open.common.spring.web.event
    + AbstractEventDrivenMonitor
    + IEventDrivenService

[2021/09/09]
- Modify
  + open.commons.spring.web.config.ResourceConfiguration.createThreadPoolTaskExecutor(ThreadPoolTaskExecutorConfig, String)
    - 내부 구현 변경.
- Changed
  + open.commons.spring.web.event.IEventStatus <- open.commons.spring.web.event.IEventType
    + getStatus() <- getType()
- Add
  + open.commons.spring.web.event
    + AbstractEventObject&lt;T, E extends IEventType&gt;
    + IEventObject&lt;T, E extends IEventType&gt;
    + IEventType
- Release: 0.4.0-SNAPSHOT
- Release: 0.3.0

[2021/08/24]
- Add
  + open.commons.spring.web.mvc.service.AbstractComponent
    + error(String)
    + error(String, Object...)
    + error(T, String)
    + error(T, String, Object...)
    + success(T, String)
    + success(T, String, Object...) 

[2021/08/20]
- bugfix
  + Bean Name 설정
    + open.commons.spring.web.config.getRestTemplateRequestFactoryResource()
    + open.commons.spring.web.config.getThreadPoolTaskExecutorConfig()
  + Qualifier 설정
    + open.commons.spring.web.config.RestTemplateRequestFactoryResource
    + open.commons.spring.web.config.ThreadPoolTaskExecutorConfig

[2021/08/19]
- Add
  + open.commons.spring.web.config.createThreadPoolTaskExecutor(ThreadPoolTaskExecutorConfig, String)

[2021/07/05]
- New
  + open.commons.spring.web.validation.CustomConstraintValidator&lt;A extends Annotation, T&gt;
- Modify
  + open.commons.spring.web.rest.RestUtils2
    - exchange(Supplier&lt;ResponseEntity&lt;RES&gt;&gt;, HttpMethod, URI, HttpEntity&lt;REQ&gt;, Object, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RET&gt;&gt;, Function&lt;Exception, Result&lt;RET&gt;&gt;)
- __CVE-2020-13956__ 
  Vulnerable versions: &lt; 4.5.13  
  Patched version: 4.5.13 
  Apache HttpClient versions prior to version 4.5.13 and 5.0.3 can misinterpret malformed authority component in request URIs passed to the library as java.net.URI object and pick the wrong target host for request execution. 
```
   	<dependency>
  		<groupId>org.apache.httpcomponents</groupId>
  		<artifactId>httpclient</artifactId>
  		<version>[4.5.13,)</version>
	</dependency>
```

[2021/06/11]
- New
  + open.commons.spring.web.validation.CustomConstraintValidator&lt;A extends Annotation, T&gt;

[2021/06/11]
- Add
  + open.commons.spring.web.rest.RestUtils2 추가
    - 기존 RestUtils의 메소드 정의 확장: REQ, RES -> REQ, RES, RET
      - REQ: Http Reqeust Entity T ype 
      - RES: Http Response Type
      - RET: REST API를 연동한 메소드에거 제공할 데이타 타입.
- deprecated
  + open.commons.spring.web.rest.RestUtils
    - exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
    - exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
    - exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
    - exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
    - exchange(RestTemplate, HttpMethod, URI, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
    - exchange(RestTemplate, HttpMethod, URI, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)

[2021/04/23]
- Add
  + open.commons.spring.web.mvn.IAsyncJobHandler
    - getAsyncManagerHolder()
    - register(K, Future&lt;?&gt;)
    - unregister(K) 

    
[2021/01/13]
- New
  + open.commons.spring.web.mvc.IAsyncJobHanlder
- Deprecated
  + open.commons.spring.web.mvc.service.IAsyncHandlerService


[2020/12/09]
- Update
  + open.commons.spring.web.config.ResourceConfiguration
    - getRequestFactory(HttpClient, RestTemplateRequestFactoryResource): access modifier 변경 (private -> public static)
    - getRestTemplate(): @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS) 적용
    - getRestTemplateAllowPrivateCA(): @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS) 적용
  + open.commons.spring.web.rest.RestUtils
    - createHttpsClient(boolean): HttpClientConnection을 Thread-Safe 하게 생성하기 위한  HttpClientConnectionManager 변경
      * BasicHttpClientConnectionManager -> PoolingHttpClientConnectionManager

[2020/11/26]
- New
  + open.commons.spring.web.mvc.service.IAsyncHandlerService
  + open.commons.spring.web.mvc.service.AbstractSshService
- Add
  + open.commons.spring.web.rest.RestUtils
    - exchange(Supplier&lt;ResponseEntity&lt;RES&gt;&gt;, HttpMethod, URI, HttpEntity&lt;REQ&gt;, Object, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
- Update
  + open.commons.spring.web.rest.RestUtils 
    - exchange(RestTemplate, HttpMethod, URI, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
    - exchange(RestTemplate, HttpMethod, URI, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
- Delete
  + open.commons.spring.web.rest.RestUtils
    - createArrayResponseType(Class&lt;T&gt;)
    - createResponseType(Class&lt;T&gt;)    
- Deprecated
  + open.commons.spring.web.mvc.service.AsyncHandlerService

[2020/11/23]
- Add
  + open.commons.spring.web.rest.RestUtils
    - createArrayResponseType(Class&lt;T&gt;)
    - createClient()
    - createHttpsClient(boolean)
    - createRegistryBuilder(boolean)
    - createResponseType(Class&lt;T&gt;)
- Update
  + open.commons.spring.web.config.ResourceConfiguration
    - getRestTemplateAllowPrivateCA() <- getRestTemplateIgnoreHostNameVerification(): 메소드 이름변경
    
[2020/11/21]
- Add
  + open.commons.spring.web.config.ResourceConfiguration
    - getRequestFactory(HttpClient): ClientHttpRequestFactory  제공함수 별도 분리
    - getRestTemplateIgnoreHostNameVerification(): 호스트명 확인 무시 RestTemplate 제공


[2020/11/19]
- Add
  + open.commons.spring.web.rest.RestUtils
    - exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;)
    - exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
    - exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;)
    - exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)
    - exchange(RestTemplate, HttpMethod, URI, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, Result&lt;RES&gt;&gt;, Function&lt;Exception, Result&lt;RES&gt;&gt;)

[2020/11/11]
- Add
  + open.commons.spring.web.mvc.service.AsyncHandlerService: 비동기(Future&lt;V&gt; 반환)로 수행하는 메소드를 제어하는 기능 제공
- Modify
  + open.commons.spring.web.handler.DefaultGlobalInterceptor: 상위 클래스 변경
    - HandlerInterceptorAdapter(Deprecated) -> AsyncHandlerInterceptor
- Update
  + open.commons.spring.web.swagger.SpringfoxSwaggerConfig:
    - getSwaggerApiInfo(): 직접 구현

[2020/11/10]
- Dependencies
  + open.commons.core: 1.8.0-SNAPSHOT


[2020/10/21]
- Add
  + open.commons.spring.web.swagger
    - SpringfoxSwaggerConfig: API 설정 클래스.
    - SwaggerApiInfo: API 정의 클래스.

[2020/10/21]
- Add
  + open.commons.spring.web.rest.RestUtils
    - queryParameters(MultiValueMap&lt;String, Object&gt;)
    - queryParameters(String...)    

[2020/09/05]
- Add
  + open.commons.spring.web.springfox.swagger.SpringfoxSwagger
    - springfox-swagger, springfox-swagger-ui 를 위한 설정
- Update
  + open.commons.spring.web.config.CustomWebMvcConfigurer
    - Spring Security 자동 설정 방지 추가: @SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

[2020/09/04]
- Add
  + open.commons.spring.web.config.SpringfoxSwaggerWebSecurityConfigurer 


[2020/09/03]
- Add
  + pom.xml: swagger2 적용
- Update
  + open.commons.spring.web.config
    - swagger2 적용  

[2020/08/27]
- Add
  + open.commons.spring.web.rest
    - RestApiDecl.java
    - RestApiServer.java
- Update
  + open.commons.spring.web.rest
    - RestUtils.java
        
[2020/07/30]
- Tag: 0.3.0-SNAPSHOT
- Add
  + open.commons.spring.web.servlet.BadRequestException
  + open.commons.spring.web.servlet.InternalServerException
- Update
  + open.commons.spring.web.servlet.method.annotation.DefaultGlobalExceptionHandler
    + handle4xxException(Exception, WebRequest): 대상 추가
      + open.commons.spring.web.servlet.BadRequestException
    + handle5xxException(Exception, WebRequest): 대상 추가
      + open.commons.spring.web.servlet.InternalServerException


[2020/02/13]
- Tag: 0.2.4-SNAPSHOT

[2020/02/13]
- Release: 0.2.3
- Add
  + open.commons.spring.web.BasePackageMarker
  + open.commons.spring.web.handler.DefaultGlobalInterceptor
  + open.commons.spring.web.mvc.service.AbstractComponent
  + open.commons.spring.web.mvc.service.AbstractGenericService
  + open.commons.spring.web.servlet.method.annotation.DefaultGlobalExceptionHandler
- Update
  + open.commons.spring.web.OpenCommonsSpringWeb
  + open.commons.spring.web.annotation.CustomHttpMessageconverter
  + open.commons.spring.web.config.ResourceConfiguration
  + open.commons.spring.web.mvc.support.UrlInfo  
  + open.commons.spring.web.resources.ThreadPoolTaskExecutorConfig

[2019/10/23]
- Add
  + open.commons.spring.web.rest.RestUtils


[2019/10/15]
- Add
  + open.commons.spring.web.validation.ValidationTarget

[2019/10/10]
- Add
  + open.commons.spring.web.validation.Validational&lt;C extends List&lt;E&gt;, E extends Validational&lt;List&lt;E&gt;, E&gt;&gt;

[2019/10/08]
- Release: 0.2.2-RELEASE
- Tag: 0.2.3-SNAPSHOT
- Add
  + open.commons.spring.web.utils.ValidationUtils
 
[2019/9/20]
- Update
  + open.commons.spring.web.resources.ThreadPoolTaskExecutorConfig.maxPoolSize 기본값 변경
    - 30 -> Integer.MAX_VALUE

[2019/9/18]
- Release: 0.2.1
- Tag: 0.2.2-SNAPSHOT

[2019/9/9]
- Add
  + open.commons.spring.web.OpenCommonsSpringWeb 

[2019/9/8]
- Tag: 0.2.1-SNAPSHOT
- Add
  + open.commons.spring.web.config.CustomWebMvcConfigurer.extendMessageConverters(List&lt;HttpMessageConverter&lt;?&gt;&gt;)

[2019/8/7]
- Dependency
  + open.commons.core: 1.6.12

[2019/7/17]
- Release: 0.2.0
- Add
  + open.commons.spring.web.resources.ThreadPoolTaskExecutorConfig
- Dependency
  + open.commons.core: 1.6.11

[2019/7/4]
- Dependency
  + open.commons.core: 1.6.10


[2019/7/1]
- Bugfix
  + open.commons.spring.web.config.CustomWebMvcConfigurer.addFormatters(FormatterRegistry)

[2019/6/28]
- Release: 0.1.1
- Add
  + open.commons.spring.web.servlet.mvn.support
  + open.commons.spring.web.utils.WebUtils
  
- Dependency
  + javax.servlet.servlet-api
  + open.commons.core

[2019/6/27]
- Release: 0.1.0
- Add
  + open.commons.spring.web.config.ResourceConfiguration
  + open.commons.spring.web.resources.RestTempalteReqeust

[2019/06/11]
- Release: 0.0.3
  + 사용자 정의 HandlerInterceptor 자동 등록 추가

[2019/06/07]
- Release: 0.0.2
  + 다중 패키지 지원
  + application.yml (.properties or ...) 항목명 수정.
  + Bean 등록방법 추가

[2019/06/03]
- Release: 0.0.1
