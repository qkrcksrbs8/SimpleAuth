# JWT 로그인, AOP를 이용한 로그관리
JWT로 로그인 후 유저 조회하는 프로젝트입니다. <br>
모든 API는 REST API를 참조하여 만들었습니다. <br>
REST API의 대표적인 특징 6개 중 필요한 내용을 적용했습니다.. <br>
그래서 REST API가 아닌 REST API를 참조하여 만든 API입니다.
* client-server: 서버와 클라이언트를 분리했습니다. SimpleAuth 프로젝트는 서버 프로젝트입니다.
* stateless: 서버에서 클라이언트의 행위나 상태를 저장하지 않습니다.
* cache: 중복 응답을 캐싱하여 전달합니다.
* layered system: 클라이언트에서는 이 서버가 EndPoint인지 알 수 없습니다. 그리고 필요 시 게이트웨이를 하나 두면 더 안전해집니다. 
* code-on-demand(optional): 자바스크립트를 응답해주면 클라이언트에서 실행 가능합니다.
* uniform interface: HATEOAS를 제외한 나머지를 적용했습니다.

###
JWT API는 발급과 디코딩이 있습니다. <br>
어노테이션을 이용하여 인증이 필요한 API에 JWT 인증을 구현했습니다. <br>
유저 조회 기능은 JWT 인증이 되어야 사용 가능합니다. <br>

AOP를 이용하여 유저 조회 시 요청, 응답 로그를 남기고 있습니다. <br>
로그에 UUID를 남겨 사용자를 식별하고 있습니다. <br>


### Annotation ex.1
* @ValidJwt 어노테이션을 커스텀으로 만들어서 JWT 유효성을 체크합니다. <br>
* Controller 메서드 위에 붙여서 사용 가능합니다. @GetMapping, @RequestBody 처럼 상단에 붙여주면 됩니다.

### API ex.1 <br>
URI: /auth/token <br>
METHOD: GET <br>
API 기능: 토큰을 디코딩하여 응답해줍니다.

### API ex.2 <br>
URI: /auth/token <br>
METHOD: POST <br>
API 기능: body의 유저 정보 기준으로 인증되면 토큰을 발급해줍니다.

### API ex.3
URI: /users <br>
METHOD: GET <br>
API 기능: 모든 회원 정보를 가져옵니다. 모든 회원 정보는 잘 바뀌지 않기 때문에 캐싱합니다.

### API ex.4
URI: /users/{userid} <br>
METHOD: GET <br>
API 기능: 쿼리 스트링이 아닌 URI 값으로 회원 정보를 조회합니다.

