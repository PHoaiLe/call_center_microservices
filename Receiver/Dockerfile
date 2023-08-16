FROM eclipse-temurin
COPY target/*.jar receiver.jar
EXPOSE 8070
ENTRYPOINT ["java","-jar","/receiver.jar"]