FROM openjdk:12
VOLUME /tmp
EXPOSE 8001
ADD ./target/web_lista_deseos-0.0.1-SNAPSHOT.jar web_lista_deseos.jar
ENTRYPOINT ["java","-jar","/web_lista_deseos.jar"]