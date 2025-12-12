# SPDX-FileCopyrightText: 2025 Digg - Agency for Digital Government
# SPDX-License-Identifier: CC0-1.0

FROM maven:3.9-eclipse-temurin-21@sha256:6fdc855a6ed81d288ca7ca37ac6ff5e9308b612485c0801d70b25a858c83d237 AS builder

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline -B

COPY src ./src
COPY development ./development
RUN mvn clean package -DskipTests -Dcheckstyle.skip=true -B

FROM gcr.io/distroless/java21-debian12@sha256:ed87b011df38601c55503cb24a0d136fed216aeb3bcd57925719488d93d236f4

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]