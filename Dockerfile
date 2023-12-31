FROM eclipse-temurin:17

LABEL maintainer ="badjatiya.animesh@gmail.com"

WORKDIR /app

COPY target/teamviewer-ecommerce-0.0.1-SNAPSHOT.jar /app/teamviewer-ecommerce.jar

ENTRYPOINT ["java", "-jar", "teamviewer-ecommerce.jar"]