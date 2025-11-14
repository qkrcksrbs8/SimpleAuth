# 1. JDK 17 기반 이미지 사용
FROM eclipse-temurin:17-jdk-alpine

# 2. 앱 디렉토리 생성
WORKDIR /app

# 3. 빌드된 jar 복사
COPY build/libs/SimpleAuth-0.0.1-SNAPSHOT.jar app.jar

# 4. 컨테이너 포트 설정 (Spring Boot 기본 8080)
EXPOSE 8080

# 5. 컨테이너 실행 시 명령
ENTRYPOINT ["java","-jar","app.jar"]