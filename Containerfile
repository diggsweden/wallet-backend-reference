# SPDX-FileCopyrightText: 2025 Digg - Agency for Digital Government
# SPDX-License-Identifier: CC0-1.0

FROM maven:3.9-eclipse-temurin-21@sha256:db5e420aadc186ac18c549a31043c31d2795d61f8a97eb50b4831fd155f0e7d6 AS builder

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline -B

COPY src ./src
COPY development ./development
RUN mvn clean package -DskipTests -Dcheckstyle.skip=true -B

FROM gcr.io/distroless/java21-debian12@sha256:5a7b2546ab61ed5dff8d1c7ca71357fef69486bc7619aee5738491826e223155

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]