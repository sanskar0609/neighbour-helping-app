

FROM maven:3.8.4-openjdk-17
WORKDIR /app

COPY . /app

RUN mvn clean install  # Build the project

CMD ["java", "-jar", "target/MyUnityHubTest-1.0-SNAPSHOT.jar"]
