# Dockerfile
# build
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

#  run
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV JAVA_OPTS=""
EXPOSE 8080
# usa sh -c para permitir JAVA_OPTS
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Dserver.port=8080 -jar /app/app.jar"]
