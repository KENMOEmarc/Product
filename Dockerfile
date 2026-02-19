FROM eclipse-temurin:17-jdk-alpine
LABEL authors="kenmoe"

WORKDIR /app

COPY target/product.jar ./product.jar

EXPOSE 8080

CMD ["java", "-jar", "product.jar"]