[2025/08/20]
- Bugfix
  + open.commons.spring.web.beans.rest.IdBasedRestApiDecl.getHeaders()
    : header 정보 Map을 직접 전달함으로써 호출이 반복되는 경우 헤더가 중복적용되여 Header Max Length 오류 발생 
  + open.commons.spring.web.handler.InterceptorIgnoreUrlProperties: 'unmodifiable set' 으로 제공하여 데이터 처리시 오류 발생
    + getExcludePathPatterns()
    + getIncludePathPatterns()
- New
  + open.commons.spring.web.servlet.filter: OncePerRequestFilter 추상 클래스 추가
    + AbstractOncePerRequestFilter.java
    + RequestHeaderFilter.java
  + open.commons.spring.web.servlet.filter.header: 헤더 정보 공유 지원
    + DefaultSharedHeader.java
    + SharedHeader.java
    + SharedHeadersBuiltinProvider.java
- Modify
  + open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration
    + Header 정보 공유 기능
      + beanDefaultRequestHeaderFilter()
      + beanPrimarySharedHeaders(Map&lt;String, SharedHeader&gt;, Map&lt;String, List&lt;SharedHeader&gt;&gt;)
      + configBuiltinSharedHeaders()
  + open.commons.spring.web.aspect.LogFeatureAspect.handleExternalRequest(ProceedingJoinPoint): SharedHeader 공유 적용.

[2025/08/18]
- Bugfix
  + open.commons.spring.web.aspect.MethodExecutionElapsedTimeAspect: 동일한 유형 server -> server -> server 와 같은 호출구조인 경우 'context key'가 중복사용되는 오류 수정
    + afterController(String, Log, ProceedingJoinPoint)
    + afterRepository(String, Log, ProceedingJoinPoint)
    + afterService(String, Log, ProceedingJoinPoint)
    + beforeController(String, Log, ProceedingJoinPoint)
    + beforeRepository(String, Log, ProceedingJoinPoint)
    + beforeService(String, Log, ProceedingJoinPoint)
    
- Modify
  + open.commons.spring.web.aspect.AbstractMethodCallChainLogAspect: 아래 메소드에 "AOP가 적용되어 Aspect에 기반하여 메소드 호출을 잡은 위치의 식별정보" 를 추가 (첫번째 파라미터)
    + afterController(String, Log, ProceedingJoinPoint)
    + afterRepository(String, Log, ProceedingJoinPoint)
    + afterService(String, Log, ProceedingJoinPoint)
    + beforeController(String, Log, ProceedingJoinPoint)
    + beforeRepository(String, Log, ProceedingJoinPoint)
    + beforeService(String, Log, ProceedingJoinPoint)
  + open.commons.spring.web.servlet.filter.RequestThreadNameFilter.doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain): 요청 URL 기반으로 Thread 이름을 결정할 때 'context-path' 정보 제거

[2025/08/13]
- New
  + open.commons.spring.web.resources.ThreadPoolExecutorConfig
- Add
  + open.commons.spring.web.config.ResourceConfiguration
    + BEAN_QUALIFIER_DEFAULT_THREAD_POOL_EXECUTOR
    + CONFIGURATION_DEFAULT_THREAD_POOL_EXECUTOR_CONFIG
    + PROPERTIES_DEFAULT_THREAD_POOL_EXECUTOR_CONFIG
    + CONFIGURATION_THREAD_POOL_EXECUTOR_CONFIG_ON_MDC
    + beanThreadPoolExecutor(ThreadPoolExecutorConfig)
    + configThreadPoolExecutorConfig()
    + configThreadPoolExecutorConfigOnMDC(ThreadPoolExecutorConfig)

[2025/08/06]
- New
  + open.commons.spring.web.concurrent.DelegatingTaskExecutor<S extends AsyncListenableTaskExecutor>: ThreadPoolTaskExecutor 기반
- Modify
  + open.commons.spring.web.mdc.MdcWrappedJob&lt;V&gt;: 동일 Executor에서 다중 작업이 실행될 때 TaskNumber 동적 적용.
- Add
  + open.commons.spring.web.concurrent.DelegatingExecutorSupportor: TaskExecutor 제공 메소드 추가
    + taskExecutor(AsyncListenableTaskExecutor)
    + taskExecutor(AsyncListenableTaskExecutor, String)

[2025/08/04]
- HttpServletRequest 기반 Thread 이름 설정 기능 변경에 따른 클래스 변경
  + New
    + open.commons.spring.web.servlet.filter.AntPathRequest: Request 패턴 
    + open.commons.spring.web.servlet.filter.RequestThreadNameFilter: 기능 이관 클래스
    + open.commons.spring.web.utils.PathUtils: open.commons.spring.web.handler.InterceptorIgnoreValidator에서 공통 기능 이관해 옴.
  + Add
    + open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration.defaultRequestThreadNameFilter()
    + open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration.oncePerRequestShouldNotFilters(Map&lt;String, AntPathRequest&gt;, Map&lt;String, List&lt;AntPathRequest&gt;&gt;)
    + open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration.staticOncePerRequestShouldNotFilters()
    + open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration.swaggerOncePerRequestShouldNotFilters()
    + open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration.threadNamingFilter(RequestThreadNameFilter)
  + Modify
    + open.commons.spring.web.handler.InterceptorIgnoreValidator: Path 관련 기능은 open.commons.spring.web.utils.PathUtils 으로 이관함.
    + open.commons.spring.web.aspect.LogFeatureAspect: Thread 이름 관련된 기능을 open.commons.spring.web.servlet.filter.RequestThreadNameFilter와 공유함.

[2025/08/04]
- Add
  + open.commons.spring.web.aspect.LogFeatureAspect
    + handleBeforeHandlerInterceptor(ProceedingJoinPoint): HandlerInterceptor 이전에 실행되는 메소드에 대한 처리
  + open.commons.spring.web.config.ResourceConfiguration: java.util.Executor 구현체에 대한 '기본제공'과 '내부사용' 설정을 분리.
- Modify
  + 복제 메소드 추가
    + open.commons.spring.web.resources.ScheduledThreadPoolExecutorConfig
    + open.commons.spring.web.resources.ThreadPoolTaskExecutorConfig
    + open.commons.spring.web.resources.ThreadPoolTaskSchedulerConfig

[2025/08/03]
- @Scheduled 어노테이션 설정 메소드 및 수동 Executor/ExecutorService 지원
  + New
    + open.commons.spring.web.concurrent 패키지
    + open.commons.spring.web.resources
     + open.commons.spring.web.resources.ScheduledThreadPoolExecutorConfig
     + open.commons.spring.web.resources.ThreadPoolTaskSchedulerConfig

[2025/07/31]
- 비동기 상황에서의 MDC(Mapped Diagnostic Context) 전파
  + New
    + open.commons.spring.web.async.AsyncConfig
    + open.commons.spring.web.async.MdcTaskDecorator
    + open.commons.spring.web.concurrent.DelegatingExecutorService
    + open.commons.spring.web.concurrent.MdcWrappedJob&lt;V&gt;
  + Modify
    + open.commons.spring.web.aspect.LogFeatureAspect
      + handleExternalRequest(ProceedingJoinPoint): @Controller, @RestController 지원
      + handleInternalTrigger(ProceedingJoinPoint): @Scheduled 어노테이선 지원


[2025/07/30]
- New
  + open.commons.spring.web.handler: HandlerInterceptor에서 처리할 URL 패턴 기능 추가
    + InterceptorIgnoreUrlProperties.java
    + InterceptorIgnoreValidator.java
    + InvalidIgnoreUrlPatternException.java
- Modify
  + 기능별 로그 분리 관련 클래스 이름 변경
    + open.commons.spring.web.log: 사용자 정의 어노테이션을 통합 기능별 로그파일 분리 지원.
      + IMdcPropertyLogDecorationConsolidator -> ILogFeatureDecorationConsolidator
      + IMdcPropertyLogDecorator -> ILogFeatureDecorator
      + InvalidServiceFeatureException -> InvalidLogFeatureException
      + MdcPropertyLogBuilder -> LogFeatureBuilder
      + MdcPropertyLogDecorationConsolidator -> LogFeatureDecorationConsolidator
      + ServiceMetadata -> LogFeature
    + open.commons.spring.web.aspect.FeatureBasedLoggingAspect -> LogFeatureAspect
    + open.commons.spring.web.config.MdcPropertyLogDecorationConfiguration -> LogFeatureDecorationConfiguration
  + HTTP 요청 Proxy 헤더 클래스 변경
    + open.commons.spring.web.handler.HttpRequestProxyHeader: Map&lt;String, String&gt;를 상속받아 확장가능하게 개선됨.
        + 추가되는 헤더이름은 상수화 시키는 작업을 계속적으로 추가.
- Add
  + open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration.interceptorIgnoreUrlPatterns(Map&lt;String, InterceptorIgnoreUrlProperties&gt;, Map&lt;String, List&lt;InterceptorIgnoreUrlProperties&gt;&gt;)
  + open.commons.spring.web.config.CustomWebMvcConfigurer
    + addExcludePatternsToInterceptor(InterceptorRegistration, List&lt;String&lt;)
    + addIncludePatternsToInterceptor(InterceptorRegistration, List&lt;String&lt;)
    + addIncludePatternsToInterceptor(InterceptorRegistration, String...)
    + setEnumPkgs(EnumPackages)
    + setInterceptorIgnoreUrlConfigurations(Set&lt;InterceptorIgnoreUrlProperties&lt;)
  + open.commons.spring.web.config.ResourceConfiguration.interceptorIgnoreUrlPatterns()


[2025/07/29]
- New
  + open.commons.spring.web.log: 사용자 정의 어노테이션을 통합 기능별 로그파일 분리 지원.
    + IMdcPropertyLogDecorationConsolidator.java
    + IMdcPropertyLogDecorator.java
    + InvalidServiceFeatureException.java
    + MdcPropertyLogBuilder.java
    + MdcPropertyLogDecorationConsolidator.java
    + ServiceMetadata.java
  + open.commons.spring.web.aspect.FeatureBasedLoggingAspect
  + open.commons.spring.web.config.MdcPropertyLogDecorationConfiguration
  
- Modify
  + nginx와 같은 Proxy 서버 전달 헤더 오류 수정
    + open.commons.spring.web.handler.HttpRequestProxyHeader
    + open.commons.spring.web.handler.ProxyHeaderUtil

[2025/07/24]
- Deprecated
  + open.commons.spring.web.jacksons.decoration: {@link Jackson2ObjectMapperBuilder}를 {@link Bean}으로 제공받아 {@link ObjectMapper}를 생성하는 방식으로 변경됨에 따라 필요성이 없어짐.
    + open.commons.spring.web.jacksons.decoration.IObjectMapperDecorationConsolidator
    + open.commons.spring.web.jacksons.decoration.IObjectMapperDecorator
    + open.commons.spring.web.jacksons.decoration.ObjectMapperDecorationConsolidator
    + open.commons.spring.web.jacksons.decoration.ObjectMapperDecorator

[2025/07/24]
- Dependencies
  + Add
    + jakarta.validation:jakarata.validation-api:${managed-version}
    + com.google.code.findbugs:jsr305:${managed-version}

[20225/07/23]
- Add
  + open.commons.spring.web.mvc.service.AbstractSshService: Input/Output Stream 형태의 데이터 전송 추가
    + download(String, int, String, String, String, OutputStream, boolean)
    + upload(String, int, String, String, InputStream, String, boolean)
    + list(String, int, String, String, String): 디렉토리 내 목록 조회 기능

[20225/07/18]
- New
  + nginx와 같은 proxy 서비스를 통한 http request의 실제 클라이언트 연결 정보 제공
    + open.commons.spring.web.handler.HttpRequestProxyHeader
    + open.commons.spring.web.handler.ProxyHeaderUtil
- Add
  + nginx와 같은 proxy 서비스를 통한 http request의 실제 클라이언트 연결 정보 제공
    + open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration.getProxyHeader(): 추가
    + open.commons.spring.web.handler.DefaultGlobalInterceptor.setProxyHeader(HttpRequestProxyHeader)
- Modify
  + nginx와 같은 proxy 서비스를 통한 http request의 실제 클라이언트 연결 정보 제공
    + open.commons.spring.web.handler.DefaultGlobalInterceptor.preHandle(HttpServletRequest, HttpServletResponse, Object)

[20225/07/14]
- Add
  + open.commons.spring.web.rest.RestUtils2: Result&lt;R&gt;를 제거한 메소드 추가
    + exchangeAsRaw(RestTemplate, HttpMethod, String, String, int, String, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, RET&gt;)
    + exchangeAsRaw(RestTemplate, HttpMethod, String, String, int, String, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, RET&gt;, int)
    + exchangeAsRaw(RestTemplate, HttpMethod, String, String, int, String, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, RET&gt;)
    + exchangeAsRaw(RestTemplate, HttpMethod, String, String, int, String, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, RET&gt;, int)
    + exchangeAsRaw(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, RET&gt;)
    + exchangeAsRaw(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, RET&gt;, int)
    + exchangeAsRaw(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, RET&gt;)
    + exchangeAsRaw(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, RET&gt;, int)
    + exchangeAsRaw(RestTemplate, HttpMethod, URI, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, RET&gt;)
    + exchangeAsRaw(RestTemplate, HttpMethod, URI, HttpEntity&lt;REQ&gt;, Class&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, RET&gt;, int)
    + exchangeAsRaw(RestTemplate, HttpMethod, URI, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, RET&gt;)
    + exchangeAsRaw(RestTemplate, HttpMethod, URI, HttpEntity&lt;REQ&gt;, ParameterizedTypeReference&lt;RES&gt;, Function&lt;ResponseEntity&lt;RES&gt;, RET&gt;, int)
    + exchangeAsRaw(Supplier&lt;ResponseEntity&lt;RES&gt;&gt;, HttpMethod, URI, HttpEntity&lt;REQ&gt;, Object, Function&lt;ResponseEntity&lt;RES&gt;, RET&gt;, int)
  + open.commons.spring.web.rest.service.AbstractRestApiClient: open.commons.spring.web.rest.RestUtils2 메소드 확장에 따른 대응 메소드 추가
  + open.commons.spring.web.beans.rest.IIdBasedRestApiService: open.commons.spring.web.rest.RestUtils2 메소드 확장에 따른 대응 메소드 추가
  + open.commons.spring.web.beans.rest.AbstractIdBasedRestApiService: open.commons.spring.web.beans.rest.IIdBasedRestApiService 메소드 확장에 따른 대응 메소드 추가

[20225/07/10]
- New
  + open.commons.spring.web.beans.rest.QueryParam: 쿼리 파라미터 필수 여부 검증
- Modify
  + open.commons.spring.web.beans.rest.AbstractIdBasedRestApiService: 쿼리 파라미터 변경에 따른 수정.
  + open.commons.spring.web.beans.rest.IdBasedRestApiDecl: 쿼리 파라미터 변경에 따른 수정.

[20225/07/03]
- New
  + 'ID'기반 REST API 식별 기능 추가
    + open.commons.spring.web.beans.rest.AbstractIdBasedRestApiService
    + open.commons.spring.web.beans.rest.IdBasedRestApiDecl
    + open.commons.spring.web.beans.rest.IIdBasedRestApiService
- Modify
  + open.commons.spring.web.rest.service.AbstractRestApiClient
    : 기존 `open.commons.spring.web.rest.service.AbstractRestService` 클래스 기능 개선 및 이름 변경
  

[20225/07/02]
- New
  + open.commons.spring.web.rest.service.AbstractRestService: REST API 서비스 연동 지원

[20225/06/24]
- Notation
  + (req) Controller -> ... -> (res) Controller 과정의 메소드 호출 추적 
  + Authorized-Resources 권한인증 비활성화 기능 지원
- New
  + open.commons.spring.web.beans.authority.IAuthorizedResourceAuthenticationPause
  + open.commons.spring.web.servlet.filter.AuthorizedResourceFilter
  + open.commons.spring.web.thread.AuthorizedResourceContext
  + open.commons.spring.web.thread.MethodContextHandler
  + open.commons.spring.web.thread.MethodLogContext
- Modify
  + open.commons.spring.web.autoconfigure.configuration.AuthorizedResourcesConfiguration
  + open.commons.spring.web.autoconfigure.EnableOpenCommonsSpringWeb
  + open.commons.spring.web.aspect.AbstractMethodLogAspect
  + open.commons.spring.web.jackson.AuthorizedObjectJackson2HttpMessageConverter


[20225/06/23]
- Add
  + open.commons.spring.web.aspect.AbstractAspectPointcuts: Aspect Pointcut 을 지원하기 위한 상위 클래스 추출
- Modify
  + open.commons.spring.web.aspect.AbstractAuthorizedResourceAspect.AbstractAuthorizedResourceAspect(ApplicationContext, Class<T>): 상위 Aspect 기능 제거
- New
  + open.commons.spring.web.aspect.AbstractMethodLogAspect: 메소드 호출을 추적하기 위한 Aspect 추가

[20225/06/20]
- Add
  + open.commons.spring.web.authority.metadata 패키지 추가: AuthorizedObject, AuthorizedField 어노테이션 정보를 외부설정(예: yaml 파일), 프로그래밍 코드에서 설정할 수 있도록 지원.

[20225/06/18]
- Modify
  + open.commons.spring.web.beans.authority.forced.ForcedUnintelligibleJudge: 클래스명 오타 수정
- Add
  + open.commons.spring.web.authority.metadata.AuthorizedMetadataBuilder: 권한제어 메타데이터 빌더 추가

[20225/06/12]
- Modify
  + open.commons.spring.web.jackson.AuthorizedObjectJackson2HttpMessageConverter.writeInternal(Object, Type, HttpOutputMessage): serialize 도중 오류가 발생했을 때, 이전 데이터 제거 처리. (JacksonGenerator는 기본적으로 스트림으로 처리하기 때문에 내부 버퍼를 적용함.)
- Add
  + open.commons.spring.web.beans.authority.forced: 패키지 
  
  
[20225/06/11]
- Modify
  + open.commons.spring.web.autoconfigure.configuration.AuthorizedResourcesConfiguration: 권한제어대상 객체 메시지 변환기 분리
    + open.commons.spring.web.autoconfigure.configuration.AuthorizedObjectMessageConverterConfiguration
    + open.commons.spring.web.autoconfigure.configuration.AuthorizedObjectMessageConfigureConfiguration
- Add
  + META-INF/spring.factories
    + open.commons.spring.web.autoconfigure.configuration.AuthorizedObjectMessageConverterConfiguration
    + open.commons.spring.web.autoconfigure.configuration.AuthorizedObjectMessageConfigureConfiguration
  + open.commons.spring.web.OpenCommonsSpringWebMarker: 명확한 클래스명으로 변경.

[20225/06/05]
- Auto Configuration 적용
  + Add
    + src/main/resources/META-INF/spring.factories
    + open.commons.spring.web.autoconfigure.EnableOpenCommonsSpringWeb
    + open.commons.spring.web.autoconfigure.OpenCommonsSpringWebAutoConfigurationImportSelector
    + open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration
  + Modify
    + open.commons.spring.web.autoconfigure.configuration.AuthorizedResourcesConfiguration: package 변경
  + Delete
    + open.commons.spring.web.autoconfigure.AuthorizedResourcesImportSelector: OpenCommonsSpringWebAutoConfigurationImportSelector 으로 통합 처리
    + open.commons.spring.web.autoconfigure.EnableAuthorizedResorces: EnableOpenCommonsSpringWeb으로 통합 처리

[2025/05/28]
- Add
  + open.commons.spring.web.servlet.method.annotation.DefaultGlobalExceptionHandler
    + resolveAnnotatedResponseStatus(Exception, HttpStatus): ResponseStatus 를 통한 HttpStatus 설정 지원
    + handleAllException(Exception, WebRequest): 모든 예외 클래스(Throwable로 설정) 처리.
- Deprecated
  + open.commons.spring.web.servlet.method.annotation.DefaultGlobalExceptionHandler
    + handle4xxException(Exception, WebRequest): resolveAnnotatedResponseStatus 적용
    + handle5xxException(Exception, WebRequest): resolveAnnotatedResponseStatus 적용
    
[2025/05/27]
- Add
  + open.commons.spring.web.jackson 패키지
  + open.commons.spring.web.beans.factory.
  + open.commons.spring.web.config.AuthorizedObjectMessageConfigure
- Modify
  + 패키지 구조 변화 
    + open.commons.spring.web.ac -> open.commons.spring.web.authority
    + open.commons.spring.web.ac.provider -> open.commons.spring.web.beans.ac
- Delete
  + open.commons.spring.web.authority.AuthorizedResponse
  + open.commons.spring.web.aspect.AuthorizedResponseAspect
    
[2025/05/20]
- Add
  + open.commons.spring.web.aspect.AuthorizedResponseAspect: 응답 권한 처리 
  + open.commons.spring.web.beans: 데이터 처리 기본 인터페이스 및 추상 클래스 추가.
    + AbstractResponseDataHandler.java
    + DefaultUnauthorizedFieldHandler.java
    + IUnauthorizedFieldHandler.java
  
[2025/05/19]
- New
  + open.commons.spring.web.ac: 자원 유형별 권한 정의 어노테이션
  + open.commons.spring.web.aspect: 자원 유형별 권한 중개
  + open.commons.spring.web.beans.factory: 자원 유형 어노테이션 설정 검증.
  + open.commons.spring.web.autoconfiguration: 자동설정 적용.

[2025/05/14]
- Patch
  + open.commons.spring.web.utils.SecurityUtils 
    + encryptBySessionUUID(String, String): 예외처리 세분화
    + decryptBySessionUUID(String, String): 예외처리 세분화

[2025/05/12]
- Patch
  + open.commons.spring.web.mvc.service.IConvertingService.convertSingleResult(Result&lt;S&gt;, Function&lt;S, T&gt;): 내부 데이터 검증 개선


[2025/05/09]
- Add
  + open.commons.spring.web.utils.SecurityUtils
    + registerDecryptionConverter(Class&lt;?&gt;, Class&lt;?&gt;, String)
    + registerEncryptionConverter(Class&lt;?&gt;, Class&lt;?&gt;, String)

[2025/04/29]
- Add
  + open.commons.spring.web.oas.OpenApiConfig.loadGroupedOpenApi(Map<String, GroupedOpenApiProperties>, String): API 그룹에서 해당하는 API 정보를 생성하는 함수 단일화.

[2025/04/28]
- tranfer
  + open.commons.spring.web.initialize.DatabaseInitResources: DB 관련 기능으로 open-commons-spring-jdbc로 이관함.
    ``` xml
    <dependency>
      <groupId>io.github.open-commons&lt;/groupId>
      <artifactId>open-commons-spring-jdbc&lt;/artifactId>
      <version>${0.5.0-SNAPSHOT or higher}&lt;/version>
    </dependency>
    ```
[2025/04/18]
- Add
  + open.commons.spring.web.utils.SecurityUtils
    + decryptBySessionUUID(String)
    + decryptBySessionUUID(String, String)
    + encryptBySessionUUID(String)
    + encryptBySessionUUID(String, String)
- Deprecated
  + open.commons.spring.web.utils.SecurityUtils
    + decryptBySessionId(String)
    + decryptBySessionId(String, String)
    + encryptBySessionId(String)
    + encryptBySessionId(String, String)

[2025/04/18]
- Update
  + open.commons.spring.web.utils.SecurityUtils
    + decryptBySessionId(String, String): Base64 Url Decoder 적용
    + encryptBySessionId(String, String): Based64 Url Encoder 적용
  + open.commons.spring.web.config.CustomWebMvcConfigurer: 어노테이션 변경
    + @Configuration, @EnableWebMvc, @SpringBootAppliication 제거: 구현 클래스에서 적용하도록 허용.
  
[2025/04/17]
- Add
  + open.commons.spring.web.utils.SecurityUtils
    + clearAuthentication()
    + clearSession()
    + clearSession(HttpServletRequest)
    + decryptBySessionId(String)
    + decryptBySessionId(String, String)
    + encryptBySessionId(String)
    + encryptBySessionId(String, String)
    + getAuthentication()
    + getCurrentPrincipal()
    + getHttpSession()
    + getHttpSession(boolean)
    + getHttpSession(HttpServletRequest, boolean)
    + getRequest()
    + getSessionId()
    + getSessionId(boolean)
    + getSessionId(HttpServletRequest)
    + getSessionId(HttpServletRequest, boolean)
- Update
  + open.commons.spring.web.servlet.method.annotation.DefaultGlobalExceptionHandler: 응답 객체 생성 함수를 동적으로 설정하도록 변경
  
[2025/04/08]
- New
  + open.commons.spring.web.utils.SecurityUtils
- Add
  + open.commons.spring.web.utils.WebUtils
    + getParameters(String)


[2025/04/08]
- Add
  + open.commons.spring.web.oas.GroupedOpenApiProperties
  + open.commons.spring.web.oas.OpenApiConfig
    + loadGroupedOpenApiProperties()
    + transform(GroupedOpenApiProperties, String)

[2025/04/03]
- Add
  + open.commons.spring.web.initialize 추가

[2025/02/21]
Apply 'Maven Central Deployment'

- Update
  + <deploymentManagement>
    + Release: Maven Central (https://central.sonatype.com)
  + 'open.commons' dependencies 
    + groupId: io.github.open-commons
- Add
  + <build>
    + org.sonatype.central:central-publishing-maven-plugin
    + org.apache.maven.plugins:maven-gpg-plugin
    
[2025/02/17]
- Snapshot: 0.8.0-SNAPSHOT
- Dependencies (공통파일로 별도 관리 적용)
  + open-commons-spring-dependencies: 0.1.0-SNAPSHOT

[2025/02/17]
- Release: 0.7.0

[2024/10/31]
- ETC
  + Maven Repository 주소 변경 (http -> https)
  
[2023/11/02]
- Modify
  + open.commons.spring.web.mvc.service.AbstractSshService
    + connectTimeout @Value 기본값 설정: 100
    + download(String, int, String, String, String, String)
    + transfer(String, int, String, String, String, String, boolean)
    + upload(String, int, String, String, String, String)
  + open.commons.spring.web.mvc.IAsyncJobHandler
    + register(H, K, Future<?>): 반환 데이터 추가
    + register(K, Future<?>): 반환 데이터 추가
    

[2023/11/02]
- Add
  + open.commons.spring.web.mvc.IAsyncJobHandler
    + unregister(H, K): 반환 데이터 추가
    + unregister(K): 반환 데이터 추가
  + open.commons.spring.web.mvc.service.CliExecutionComponent
    + executeNoWait(String[], File, String)
    + executeNoWait(String[], String[], File, String)
    + executeNoWait(String[], String[], String)
    + executeWaitFor(String[], File, String)
    + executeWaitFor(String[], String)
    + executeWaitFor(String[], String[], File, String)
    + executeWaitFor(String[], String[], String)

[2023/09/07]
- Release: 0.7.0-SNAPSHOT
- Dependencies
  + spring-core:: 5.3.29
  + spring-boot: 2.7.15
  + sl4jf-api: 2.0.9

[2023/09/07]
- Release: 0.6.0
- Dependencies 
  + spring-core: 5.3.23 (rollback)
  + spring-boot: 2.5.3 (rollback)
  + sl4jf-api: 1.7.32 (rollback)
  
[2023/09/05]
- Dependencies
  + spring-core:: 5.3.29
  + spring-boot: 2.7.15
  + sl4jf-api: 2.0.9

[2023/07/21]
- Bugs Fix
  + open.commons.spring.web.config.CustomWebMvcConfigurer
    + 사용자 정의 Interceptor 등록 메소드 추가

[2023/07/19]
- Add
  + Sprinngdoc Open API 지원
    + dependency 추가
      + org.springdoc:springdoc-openapi-ui:1.7.0
    + open.commons.spring.web.oas.OpenApiConfig
    + application.yml에 Open API를 위한 속성 추가.
      + open-commons.springdocs.open-api.info
      + open-commons.springdocs.open-api.external-docs

[2023/05/19]
- Remove
  + Springfox Swagger2 제거

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
