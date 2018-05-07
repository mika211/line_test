FROM gcr.io/google_appengine/openjdk8
VOLUME /tmp
ADD oic-sysdev-2018-0.0.1-SNAPSHOT.jar app.jar
CMD [ "java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=gae","/app.jar"]