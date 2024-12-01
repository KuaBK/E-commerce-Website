# Base image chứa Maven 3.9.9 và JDK 21
FROM maven:3.9.9-eclipse-temurin-21

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Copy toàn bộ mã nguồn vào container
COPY . .

# Build project bằng Maven
RUN mvn clean package -DskipTests

# Image cho runtime (không cần Maven)
FROM eclipse-temurin:21-jre

# Copy file JAR từ giai đoạn build
COPY --from=0 /app/target/*.jar app.jar

# Lệnh chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
