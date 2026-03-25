# SPDX-FileCopyrightText: 2025 Digg - Agency for Digital Government
# SPDX-License-Identifier: CC0-1.0

FROM maven:3.9-eclipse-temurin-21@sha256:fe6cfb95c7ec52702f41b3071cae4cf69b8b8cc224104cfe0cad65b36e9107a5 AS builder

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline -B

COPY src ./src
COPY development ./development
RUN mvn clean package -DskipTests -Dcheckstyle.skip=true -B

FROM gcr.io/distroless/java21-debian12@sha256:f34fd3e4e2d7a246d764d0614f5e6ffb3a735930723fac4cfc25a72798950262

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]