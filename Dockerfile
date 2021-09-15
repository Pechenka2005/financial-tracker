FROM openjdk:11-jdk-oracle

ENV DB_HOST ${DB_HOST}
ENV DB_NAME ${DB_NAME}
ENV DB_USERNAME ${DB_USERNAME}
ENV DB_PASSWORD ${DB_PASSWORD}

ARG JAR_FILE=build/libs/financialTracker-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","-XX:+UseSerialGC","-Xss512k","-XX:MaxRAM=256m","/app.jar"]
