FROM openjdk:11
EXPOSE 8090
COPY ["./AccountSystemWeb/target/AccountSystemWeb-1.0-SNAPSHOT.war", "test.war"]
ENTRYPOINT ["java","-jar","-Dspring.datasource.url=jdbc:postgresql://postgress:54319/cmpg323","/test.war"]