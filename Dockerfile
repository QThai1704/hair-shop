FROM openjdk:17

# Định nghĩa biến môi trường
ARG FILE_JAR=target/*.jar

# Thêm vào Docker và đặt tên mới
ADD ${FILE_JAR} api-service-hairshop.jar

# Chạy lệnh
ENTRYPOINT [ "java", "-jar", "api-service-hairshop.jar" ]

# Expose port
EXPOSE 8080