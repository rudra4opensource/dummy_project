FROM openjdk:17
ADD target/CustomerService.jar CustomerService.jar.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","/CustomerService.jar"]