# Dockerfile simple para aplicación Spring Boot - Citas Médicas

# Imagen base con OpenJDK 17
FROM openjdk:17-jdk-slim

# Directorio de trabajo
WORKDIR /app

# Copiar archivos de Gradle
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradlew.bat .
COPY gradle gradle

# Copiar código fuente
COPY src src

# Dar permisos al gradlew
RUN chmod +x ./gradlew

# Compilar la aplicación
RUN ./gradlew clean build -x test

# Puerto que expone la aplicación
EXPOSE 8080

# Ejecutar la aplicación
CMD ["java", "-jar", "build/libs/citasmedicas-0.0.1-SNAPSHOT.jar"]