# todo


### 🛠 프로젝트 구상 및 로컬 환경 초기화

- (1) 책에 나와있는 버전과 동일하게 하기 위해 로컬환경에서 npm, node 삭제하기
  <br/>
  <img width="286" alt="image" src="https://github.com/jongheonleee/todo/assets/87258372/633b5a77-036e-493b-8ca3-87294ece31d1">

- (2) todo 프로젝트 구성
    - 백엔드와 프론트엔드 부분으로 나누기
      <br/>
      <img width="584" alt="image" src="https://github.com/jongheonleee/todo/assets/87258372/890da172-72f7-414f-83f4-1d8d4b22f099">




## 2. 백엔드 개발 

### 2.1 백엔드 개발 환경 설정

#### (1). 스프링 프레임워크

스프링 프레임워크의 핵심 기능은 DI(의존성 주입), 그 과정에서 IoC(제어의 역전)이 사용됨

- 오브젝트가 서로 협력하여 애플리케이션을 실행시킴, 그 과정에서 한 오브젝트는 다른 오브젝트에 의존하게됨
- 직접적인 의존관계는 개발 환경을 악화시킴, 예를 들어 변경에 대해 효율적으로 대처할 수 없이 일일이 고쳐야함
- 즉, 앞으로 일어날 변경을 효율적으로 대처하여 좋은 개발 환경을 만들기 위해서 DI(의존성 주입) 기술을 사용해야함, 그 과정에서 IoC(제어의 역전)이 활용됨
- 작은 규모의 애플리케이션의 경우 DI를 직접 구현할 수 있지만, 큰 규모의 애플리케이션은 이를 전문적으로 관리해주는 기술이 필요함
- 그것이 바로 의존성 주입 컨테이너고 스프링 프레임워크는 그 중 하나임
- 어노테이션, XML, 자바로 오브젝트 사이의 의존관계를 명시할 수 있음

#### (2). 스프링 부트

스프링 부트를 통해 개발자가 최소한의 설정을 통해 애플리케이션을 개발하고 실행할 수 있음
나머지 부분은 스프링 부트가 자동으로 설정해줌

#### (3). 스프링 프레임워크와 디스패쳐 서블릿

![KakaoTalk_Image_2023-09-12-19-41-13](https://github.com/jongheonleee/todo/assets/87258372/262983ea-eab3-4832-a872-7922262f223d)

</br>
스프링 프레임워크와 디스패쳐 서블릿을 통해 개발자가 비즈니스 개발에 집중할 수 있음
나머지 반복 작업은 디스패쳐 서블릿을 통해 처리함

- 요청에 따른 서버 작업 과정을 보면 "파라미터 해석 - 비즈니스 로직 - 응답 구축"
- 위 과정에서 파라미터 해석과 응답 구축은 반복적으로 처리해주어야함
- 반복작업은 디스패쳐 서블릿이 담당하고 개발자는 비즈니스 로직에 집중하고 해당 로직을 디스패쳐 서블릿이 이해할 수 있도록 내부 기능 구축해면됨

#### (4). 메인 메서드와 @SpringBootApplication

@SpringBootApplication : 해당 어노테이션이 달린 클래스의 패키지를 베이스 패키지로 간주함

- 스프링은 베이스 패키지 및 그 하위 패키지에서 자바 빈을 찾아 스프링의 의존성 주입 컨테이너인 ApplicationContext에 등록함
- 애플리케이션 실행 과정에서 어떤 오브젝트가 필요할 경우 의존하는 다른 오브젝트를 찾아 연결해줌
- @Autowired : 자동으로 다른 오브젝트를 찾아 연결해줌
- @Component : 해당 클래스가 자바 빈임을 나타냄
- @ComponentScan : 컴포넌트 스캔 범위를 나타내며 베이스 패키지와 그 하위 패키지가 탐색 범위임
- @Bean : 스프링에게 이 오브젝트를 어떻게 생성하고 매개변수를 어떻게 넘겨야 하는지 알려줌


#### (5). 빌드 자동화 툴 : Gradle과 라이브러리

애플리케이션을 개발할 때 반복 작업을 자동으로 처리해줌

- 반복 작업 -> 일련의 코드로 명시, Gradle이 자동으로 실행
- plugins : 그래들 확장 하는 부분, 프로젝트 내에서 사용하는 컴파일, 스프링, 디펜던시 매니저 등등 명시
- group/version/sourceCompatibility : 프로젝트 메타 데이터
- Lombok : 어노테이션에 상응하는 코드를 컴파일 시점에 자동으로 생성해주는 라이브러리, 해당 작업을 하기위해선 어노테이션 프로세서가 필요함
- Repository : 라이브러리를 다운로드 하는 곳
- Dependency : 프로젝트에서 사용할 라이브러리를 명시하는 곳, Gradle이 이를 보고 Reposity에서 라이브러리를 다운로드 받음


### 2.2 백엔드 서비스 아키텍처


#### (6). 레이어드 아키텍처 패턴과 Rest 아키텍처 패턴 그리고 Restful 서비스

레이어드 아키텍처 패턴 : 코드를 분리하고 관리하는 패턴, 이에 따라 애플리케이션의 구조를 구축함</br>
Rest 아키텍처 : 해당 애플리케이션을 이용하는 클라이언트가 어떤 형태의 요청과 응답을 보내고 받는지 설계</br>
Restful 서비스 : Rest 아키텍처에 따라 서비스가 구현된 형태
 
#### (7). 레이어드 아키텍처

애플리케이션의 구성 요소를 수평적으로 레이어드를 나눠서 구축하는 형태

- 레이어드 분리 : 클래스 단위로 분리
- 레이어드의 특징은 특정 레이어드가 그 하위 레이어드를 활용함
- 변경에 효율적으로 대처, 레이어드별로 분리했기 때문에 변경이 발생한 레이어드내에서 수정을 해주면됨


#### (8). 모델, 엔티티, DTO

해당 클래스는 데이터를 저장하는 클래스임, 물론 각각 특징이 있음

- 모델 : 비즈니스 데이터나 로직을 저장
- 엔티티 : DB의 테이블을 저장
- DTO : 데이터 저장

애플리케이션 내부에서 데이터를 전달할 때, 주로 DTO를 많이 사용함, 이유는 크게 2가지가 있음
첫 번째 보안성, 모델 그리고 엔티티의 경우 DB의 테이블 구조와 유사하기 때문에 해당 애플리케이션의 DB 구조가 노출될 수 있음
두 번째 클라이언트가 필요한 데이터 모두 포함, 모델과 엔티티의 경우 클라이언트가 필요한 모든 데이터를 포함하지 못함, 예를 들어 에러 메시지가 있음
따라서 위의 두 가지 이유 때문에 데이터 전달과정에서 주로 DTO를 사용함

- @Builder : Builder 패턴을 통해 오브젝트를 생성하라고 명시
- @NoArgsConstructor : 매개변수가 없는 생성자 정의
- @AllArgsConstrpucotr : 모든 멤버 변수를 매개변수로 받는 생성자 정의
- @Data : Getter/Setter 메서드 구현하라고 명시

#### (9). REST API

아키텍처 스타일로서 반복되는 아키텍처 디자인을 의미함 주로 HTTP Method와 URI를 어떤 형식의 요청과 응답을 만들지 결정하는 부분
<br/>
<img width="964" alt="image" src="https://github.com/jongheonleee/todo/assets/87258372/f4ea7d40-4be8-4180-af10-b0256a2c095d">

[https://www.youtube.com/watch?v=RP_f5dMoHFc](https://www.youtube.com/watch?v=RP_f5dMoHFc)
<br/>

- REST 제약 조건
  
  1. 클라이언트 - 서버 : 리소스, 네트워크, 클라이언트와 서버 관계
  2. 상태가 없는(stateless) : 이전 요청에 대한 정보를 기록x
  3. 캐시 가능한 데이터 : 서버에서 캐시 가능한지 여부를 알려줘야함
  4. 일관적인 인터페이스 : 요청과 응답의 형태가 일관적이어야함
  5. 레이어 시스템 : 여러개의 레이어로 구성된 서버
  6. 코드 온-디멘드(선택사항) : 쿨라가 서버로 코드를 요청, 서버는 이를 실행함
  

#### (10). 컨트롤러 레이어 : 스프링 REST API 컨트롤러

애플리케이션의 요청과 응답을 어떻게 처리할지 결정하는 컨트롤러 레이어
요청 정보를 통해 그에 대응하는 메서드를 호출함

- @RestController : REST API 를 처리하는 컨트롤러
- @GetMapping, @PostMapping, @PutMapping, @DeleteMapping : HTTP 메서드 매핑, ()안에 경로 지정
- @PathVariable : URI의 경로로 넘어오는 파라미터를 변수로 받음
- @RequestParam : URI에 ?di={id} 와 같은 형식으로 넘어오는 요청 매개변수를 변수로 받을 수 있음
- @RequestBody : 오브젝트처럼 복잡한 자료형을 받아옴, HTTP Body에 JSON을 자바 객체로 변환하여 받음
- @ResponseBody : 오브젝트처럼 복잡한 자료형을 리턴할 때 사용, 이때 @RestController = @Controller + @ResponseBody
- ResponseEntity : HTTP 응답 + 여러 부가 정보(status, header 조작 가능)

#### (11). 서비스 레이어 : 비즈니스 로직

컨트롤러와 퍼시스턴스 사이에서 비즈니스 로직을 수행하는 역할(순수한 자바 코드, 변경 영향 x)

- @Service : 스프링 컴포넌트며 기능적으로 비즈니스 로직을 수행함을 의미

#### (12). 퍼시스턴스 레이어 : 스프링 데이터 JPA

DB에 접근해서 실제 데이터에 접근하는 역할

- JDBC 드라이버 : 자바에서 DB에 연결할 수 있도록 도와주는 라이브러리
- JPA : 자바에서 DB에 접근, 저장, 관리에 필요한 인터페이스, 이를 구현한 것이 Hibernate
- 스프링 데이터 JPA : JPA를 더 사용하기 쉽도록 만든 프로젝트
- ORM : "DB 연결 - SQL 실행 - 결과 반환한 뒤 오브젝트로 변환", 위 작업은 반복작업으로 JPA를 통해 처리함


#### (13). TodoEntity.java

 ORM이 엔티티를 보고 어떤 테이블의 어떤 필드에 매핑해야 하는지 알 수 있어야함
 또 어떤 키가 기본 키인지 외래 키인지 구분할 수 있어야함
 따라서, 엔티티는 그 자체가 테이블을 정의해야함

- 어노테이션 조합 : @Builder, @NoArgsConstructor , @AllArgsConstructor, @Data, @Entity
- @Entity : 엔티티임을 명시
- @Table : 테이블 이름을 지정, 예를 들어 @Table(name = "Todo")
- @Id : 기본키가 될 필드에 지정
- @GeneratedValue : DB에 데이터 저장할 때마다 자동으로 id값 생성, generator로 어떻게 생성할 지 명시할 수 있음
- @GenericGenerator : 나만의 Generator를 사용하고 싶을 때 이용함 


#### (14). TodoRepository.java

기본적인 CRUD 개능은 JpaRepository<T, ID>에 정의되어 있음 따라서 이 인터페이스를 확장한 인터페이스로 정의해서 사용
구현체는 스프링 데이터 JPA가 실행 시점에 알아서 생성함


### 2.3 서비스 개발 및 실습

#### (1). 로그 어노테이션

디버깅을 위한 로그 설정, sout 형식으로 로그를 남기면 안됨

- @Slf4j : 로그 라이브러리 사용을 명시


#### (2). Create Todo 구현

- 퍼시스턴스 구현 : 이미 JpaRepository에 구현되어 있음(save())
- 서비스 구현 : create() 작성 -> 1. 검증 -> 2. save() -> 3. return findByUserId()
- 컨트롤러 구현 : createTodo() 작성 -> 1. dto -> entity -> 2. 엔티티 초기 설정 -> 3. create()호출 -> 4. entity -> dtos -> 5. ResponseDTO 생성 및 ResponseEntity 반환


#### (3). Retrieve Todo 구현(검색)

- 서비스 구현 : retrieve() 작성 -> 1. findByUserId() 호출
- 컨트롤러 구현 : retrieveTodoList() 작성 -> 1. 서비스의 retrieve() 호출 -> 2. entities -> dtos -> 3. ResponseDTO 생성 및 ResponseEntity 반환

#### (4). Update Todo 구현

- 퍼시스턴스 구현 : save(), findByUserId() 활용
- 서비스 구현 : update() 작성 -> 1. 검증 -> 2. 오리지날 데이터 가져옴 -> 3. 업데이트 및 저장 -> 4. retrieve() 호출
- 컨트롤러 구현 : updateTodo() 작성 -> 1. dto -> entity -> 2. entity 초기 설정 -> 3. update() 호출 -> 4. entities -> dtos -> 5. ResponseDTO 생성 및 ResponseEntity 반환


#### (5). Delete Todo 구현

- 퍼시스턴스 구현 : delete(), findByUserId() 활용
- 서비스 구현 : delete() 작성 -> 1. 검증 -> 2. delete() 호출 밀 예외처리 -> 3. 새 Todo list 리턴(retrieve() 활용)
- 컨트롤러 구현 : delete() 작성 -> 1. dto -> entity -> 2. entity 초기 설정 -> 3. delete() 호출 -> 4. entitis -> dtos -> 5. ResponseDTO 새성 및 ResponseEntity 반환


## 3. 프론트엔드 개발 

### 3.1 프론트엔드 개발 환경

#### (1). 브라우저의 작동 원리

HTML 파일을 트리 형태로 변환하여 브라우저에 각 노드를 그려줌으로써 시각화된 HTML 파일을 볼 수 있음

![KakaoTalk_Image_2023-09-16-11-47-47_001](https://github.com/jongheonleee/todo/assets/87258372/9f6a04a6-87b4-4ee6-b49a-29c0a9406621)
<br/>
![KakaoTalk_Image_2023-09-16-11-47-48_002](https://github.com/jongheonleee/todo/assets/87258372/9995a13f-6128-400a-a6cb-449c17e8e3d7)
<br/>


- 파싱 : 렌더링 진행 전, 전처리 과정. 여기서는 3가지 작업이 진행됨 "HTML -> DOM 트리 변환" -> "리소스 다운로드(CSS -> CSSOM 트리 변환)" -> "js 실행"
- 렌더링 : "렌더 트리 만듦(DOM 트리 + CSSOM 트리)" -> "레이아웃 정함(트리의 각 노드가 어디에 위치할지 등을 정함)" -> "브라우저 스크린에 렌더 트리의 각 노드를 그림"


#### (2). React.js

- SPA : Single Page Application, 새로고침을 하지 않는 이상 새로 로딩안함, 자세히 말하면 js가 동적으로 HTML을 재구성해 만드는 클라이언트 애플리케이션을 의미함(서버가 만드는 것이 아님)

![KakaoTalk_Image_2023-09-16-11-57-15_001](https://github.com/jongheonleee/todo/assets/87258372/6e6f7d3c-6c2f-49da-a12a-93fdeef0e33a)
<br/>

![KakaoTalk_Image_2023-09-16-11-57-15_002](https://github.com/jongheonleee/todo/assets/87258372/e8b8506d-e3f3-46f5-a357-7aed83c1597e)
<br/>
보라색 네모박스는 npm start를 실행했을 때 만들어짐, 그래서 ReactDom.render(HTML 엘리먼트)가 실행되어 html 파일을 시각화해줌 
<br/>

![KakaoTalk_Image_2023-09-16-12-08-18](https://github.com/jongheonleee/todo/assets/87258372/1dad8cfa-3eb2-4a0e-9f1d-8eeaaa41617f)
<br/>

- React 컴포넌트 : JSX = HTML(렌더링) + JS(로직), 다른 컴포넌트 사용 가능, ReactDOM.render( 컴포넌트, root) -> DOM 트리
![image](https://github.com/jongheonleee/todo/assets/87258372/685757d5-54a3-483a-9cc2-d832207d6c65)
<br/>

### 3.2 프론트엔드 서비스 개발

#### (1). Todo 리스트

- 컴포넌트를 통해 만드려고 하는 기능을 정의 -> App 컴포넌트에 전달 -> ReactDOM.render()에 전달
- 동적 처리 : useState()를 통해 사용자 상태 추적 -> 변경 -> 재랜더링
- material-ui를 통해 디자인, 이미 잘 디자인된 컴포넌트

 
   
