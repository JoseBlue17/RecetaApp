# Usa una imagen oficial de OpenJDK como base
FROM openjdk:21-jdk-slim

# Copia el archivo JAR al contenedor
COPY target/demo1-0.0.1-SNAPSHOT.jar /app/demo1.jar

# Establece el directorio de trabajo
WORKDIR /app

# Comando para ejecutar el JAR
CMD ["java", "-jar", "demo1.jar"]
