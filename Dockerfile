FROM openjdk:11-jdk-slim

COPY target/ex22_SpringBootstrap-0.0.1-SNAPSHOT.jar ex22_SpringBootstrap-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/ex22_SpringBootstrap-0.0.1-SNAPSHOT.jar"]