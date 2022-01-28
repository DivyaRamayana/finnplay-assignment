FROM openjdk:8
ADD  target/UserApp-2.4.2.jar registrationapp_finnpay.jar
CMD mvn spring-boot:run
EXPOSE 8080
ENTRYPOINT ["java","-jar","registrationapp_finnpay.jar"]