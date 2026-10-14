# Usar una imagen base con JDK 17 y Maven
FROM maven:3.9-eclipse-temurin-17 AS build

# Establecer un directorio de trabajo
WORKDIR /app

# Descargar las dependencias primero: esta capa queda en caché mientras no cambie el pom.xml
COPY pom.xml /app/
RUN mvn -B -q dependency:go-offline

# Copiar el código fuente y construir el proyecto
COPY src /app/src
RUN mvn -B -q package -DskipTests

# Crear una nueva imagen basada en OpenJDK 17 para ejecutar la aplicación
FROM eclipse-temurin:17-jre

# Exponer el puerto que utilizará la aplicación
EXPOSE 8080

# Copiar el archivo JAR construido desde la etapa anterior
COPY --from=build /app/target/libraryAPI-0.0.1-SNAPSHOT.jar /app/libraryAPI-0.0.1-SNAPSHOT.jar

# Limitar la memoria para el plan gratuito de Render (512 MB)
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=60 -XX:+UseSerialGC -Xss512k -XX:TieredStopAtLevel=1"

# Establecer el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/libraryAPI-0.0.1-SNAPSHOT.jar"]