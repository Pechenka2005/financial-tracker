FROM openjdk:11-jdk-oracle

ENV DB_HOST 34.88.25.70
ENV DB_NAME sirius8_db
ENV DB_USERNAME sirius8
ENV DB_PASSWORD Fvc2ds

ARG JAR_FILE=build/libs/financialTracker-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","-XX:+UseSerialGC","-Xss512k","-XX:MaxRAM=256m","/app.jar"]
