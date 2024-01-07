#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

#Information around who maintains the image
MAINTAINER smosky.com

# Add the application's jar to the image
COPY target/blog-server-0.0.1-SNAPSHOT.jar blog-server-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "blog-server-0.0.1-SNAPSHOT.jar"]