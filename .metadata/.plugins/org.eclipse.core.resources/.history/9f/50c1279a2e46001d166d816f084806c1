FROM openjdk:12
VOLUME /tmp
ADD ./target/ms_libros-0.0.1-SNAPSHOT.jar servicio-libros.jar
ENTRYPOINT ["java","-jar","/servicio-libros.jar"]