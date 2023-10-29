FROM eclipse-temurin:17

LABEL maintainer ="badjatiya.animesh@gmail.com"

WORKDIR /app

COPY target/jarfileName.jar /app/jarfileName.jar

ENTRYPOINT ["java", "-jar", "jarfileName.jar"]