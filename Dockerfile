FROM openjdk:11
ARG JAR_FILE_PATH=build/libs/enjoytrip-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE_PATH} enjoytrip.jar
ENTRYPOINT ["java","-jar","enjoytrip.jar"]