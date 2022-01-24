# Alpine Linux with OpenJDK JRE
FROM adoptopenjdk/openjdk11:alpine-jre
RUN apk add --no-cache bash

EXPOSE 8080

# copy JAR into image
COPY target/card-payments-0.0.1-SNAPSHOT.jar /app.jar

# copy cacert for ldaps into image
COPY docker/cacerts /opt/java/openjdk/lib/security/cacerts

COPY docker/run.sh /run.sh

#For permissions
WORKDIR /
RUN chmod +x run.sh

ENTRYPOINT ["/run.sh"]
