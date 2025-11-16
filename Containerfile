# SPDX-FileCopyrightText: 2025 Digg - Agency for Digital Government
# SPDX-License-Identifier: CC0-1.0

FROM maven:3.9-eclipse-temurin-21@sha256:ca90b9ac00e416b1f0904bd7e68db6e37ff0d80842f65d71a77e3097659e853c AS builder

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline -B

COPY src ./src
COPY development ./development
RUN mvn clean package -DskipTests -Dcheckstyle.skip=true -B

FROM gcr.io/distroless/java21-debian12@sha256:b41ca849c90e111ed5a6d2431b474225535f266ac1b3902cd508718f160cea60

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]