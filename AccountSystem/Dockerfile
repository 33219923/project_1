FROM openjdk:11
EXPOSE 8090
COPY ["./AccountSystemWeb/target/AccountSystemWeb-1.0-SNAPSHOT.war", "test.war"]
ENTRYPOINT ["java","-jar","/test.war"]