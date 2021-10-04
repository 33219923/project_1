From tomcat:jre11-temurin-focal
RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./AccountSystemWeb/target/AccountSystemWeb-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh","run"]