# Sử dụng image có JDK và Maven sẵn
FROM maven:3.8.6-eclipse-temurin-17 AS builder

# Cài đặt thư mục làm việc
WORKDIR /app

# Sao chép toàn bộ mã nguồn vào container
COPY . .

# Build ứng dụng
RUN mvn clean package -DskipTests

# Sử dụng image nhẹ hơn cho runtime
FROM eclipse-temurin:17-jre AS runtime

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
