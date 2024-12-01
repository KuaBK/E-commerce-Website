# Stage 1: Build ứng dụng Spring Boot
FROM maven:3.9.5-eclipse-temurin-21 AS builder

# Set thư mục làm việc trong container
WORKDIR /app

# Copy toàn bộ mã nguồn vào container
COPY . .

# Build ứng dụng, tạo file JAR
RUN mvn clean package -DskipTests

# Stage 2: Runtime để chạy ứng dụng
FROM eclipse-temurin:21-jre

# Set thư mục làm việc trong container
WORKDIR /app

# Copy file JAR từ stage build
COPY --from=builder /app/target/*.jar app.jar

# Expose cổng 8080 để Spring Boot lắng nghe
EXPOSE 8080

# Lệnh để khởi động ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
