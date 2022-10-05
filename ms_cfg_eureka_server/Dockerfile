FROM openjdk:12
VOLUME /tmp
EXPOSE 8761
ADD ./target/ms_cfg_eureka_server-0.0.1-SNAPSHOT.jar servicio-eureka-server.jar
ENTRYPOINT ["java","-jar","/servicio-eureka-server.jar"]