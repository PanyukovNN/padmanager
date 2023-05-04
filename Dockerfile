FROM openjdk:21-slim

COPY ./build/libs/pdfmanager.jar /pdfmanager.jar
COPY ./fonts /fonts

EXPOSE 8080
ENV TZ=Europe/Moscow

ENTRYPOINT ["java", "-jar", "/pdfmanager.jar"]