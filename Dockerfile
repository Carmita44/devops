FROM amazoncorretto:17
COPY ./target/semApp-jar-with-dependencies.jar /app/semApp.jar
WORKDIR /app
CMD ["java", "-jar", "semApp.jar"]