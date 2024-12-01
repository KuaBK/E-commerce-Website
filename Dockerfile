FROM eclipse-temurin:21-jdk AS build

COPY . .
RUN mvn clean package -DskipTests


# Run stage

FROM eclipse-temurin:21-alpine
WORKDIR /app

COPY --from=build /target/DrComputer-0.0.1-SNAPSHOT.war drcomputer.war
EXPOSE 8080

ENTRYPOINT ["java","-jar","drcomputer.war"]
