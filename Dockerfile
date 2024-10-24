# Sử dụng hình ảnh cơ bản của OpenJDK
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc
WORKDIR /app

# Copy file JAR từ build của bạn vào container
COPY crossplatform/target/*.jar /app/crossplatform.jar

# Khai báo cổng mà ứng dụng sẽ chạy
EXPOSE 8080

# Khởi động ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "/app/crossplatform.jar"]
