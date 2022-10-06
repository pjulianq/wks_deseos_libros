FROM openjdk:12
VOLUME /tmp
EXPOSE 0
ADD ./target/ms_usuarios-0.0.1-SNAPSHOT.jar servicio-usuarios.jar
ENTRYPOINT ["java","-jar","/servicio-usuarios.jar"]