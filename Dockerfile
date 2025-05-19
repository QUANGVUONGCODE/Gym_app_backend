# Sử dụng image OpenJDK 17 (phù hợp với Spring Boot)
FROM openjdk:21-jdk-slim

# Tạo thư mục làm việc trong container
WORKDIR /app

# Sao chép file JAR từ thư mục gym/gym/target
COPY gym/gym/target/gym-0.0.1-SNAPSHOT.jar app.jar

# Cổng mặc định của Spring Boot
EXPOSE 8080

# Lệnh khởi động ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]