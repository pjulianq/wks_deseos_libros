#Listas de deseos de libros

API que permite crear listas de libros para los usuarios registrados en el sistema: Registro de usuario, registro de lista, asociar libro a la lista (búsqueda de la biblioteca de google)


#Comenzando 

Trataremos de dar instrucciones muy detalladas para poner el aplicativo en funcionamiento en tú ambiente local.


Pre-requisitos 

Java 11 o superior

Docker


#Instalación 

Clona el proyecto en tú máquina local tomandolo de https://github.com/pjulianq/wks_deseos_libros.git	

El repositorio consta de 5 microservicios, para la puesta en marcha de la versión 1 requerimos eureka y web_lista_deseos.

web_lista_deseos: Contiene el front de la aplicación y cliente rest para comunicarse con los demás microservicios.

ms_cfg_eureka_server: Servicio para registro y localización de los demás microservicios.

ms_cfg_zuul_server: "proxy" para las peticiones

ms_usuarios: operaciones de usuarios

ms_libros: operaciones de libros

Crear red docker

En una consola de comandos ejecuta:

  docker network create networklibrary

Posteriormente inciamos servicio eureka:

Abriendo una consola de comandos debes ubicarte en el directorio dónde alojaste el workspace  (microservcio ms_cfg_eurek)a_server)

  cd <ruta_del_workpace>\wks_deseos_libros\ms_cfg_eureka_server
  
Con el comando dir podemos verificar que realmente estemos ubicados allí

  dir
  
Generamos Jar

  .\mvnw clean package (Al final nos debe aparecer BUILD SUCCES y nos debe indicar que se creó el jar en el target.
  
Creamos la imagen  

  docker build -t servicio-eureka-server:v1 .
  

  
Levantamos una instancia de la imagen
  docker run -p 8761:8761 --name servicio-eureka-server --network networklibrary servicio-eureka-server:v1
  
Probamos que la instancia alla iniciado de manera correcta (miramos el log o probamos con http://localhost:8761/)  

Iniciamos web_lista_deseos

cd <directorio_workspace>\wks_deseos_libros-main\web_lista_deseos
.\mvnw clean package -DskipTests
docker build -t web_lista_deseos:v1 .
docker run -p 8001:8001 --name web_lista_deseos --network networklibrary web_lista_deseos:v1

Creamos base datos en docker

	docker pull mysql:8
	docker run -p 3306:3306 --name bdlibrary --network networklibrary -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=lista_deseos -d mysql:8
  
  Una vez creada e iniciada nos conectamos a la misma:
  docker exec -it bdlibrary mysql -p
		<password> admin
Una ves logueados en la consoloa mysql ejecutamos:  
use lista_deseos;
CREATE TABLE `maestro_usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `s_nombre` varchar(200) NOT NULL,
  `s_loggin` varchar(200) NOT NULL,
  `s_password` varchar(200) NOT NULL,
  `s_estado` varchar(1) DEFAULT NULL,
  `d_fecha_creacion` datetime DEFAULT NULL,
  `d_fecha_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `lista_de_libros` (
  `id_lista_libros` int(11) NOT NULL AUTO_INCREMENT,
  `id_maestro_usuario` int(11) NOT NULL,
  `s_nombre_lista` varchar(200) NOT NULL,
  `d_fecha_creacion` datetime DEFAULT NULL,
  `s_estado` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id_lista_libros`),
  KEY `fk_usuario_idx` (`id_maestro_usuario`),
  CONSTRAINT `fk_usuario` FOREIGN KEY (`id_maestro_usuario`) REFERENCES `maestro_usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `libros_lista_libros` (
  `id_libro_lista_libros` int(11) NOT NULL AUTO_INCREMENT,
  `id_lista_libros` int(11) DEFAULT NULL,
  `s_id_libro_api_google` varchar(200) DEFAULT NULL,
  `s_nombre_libro` varchar(200) DEFAULT NULL,
  `s_autor_libro` varchar(200) DEFAULT NULL,
  `s_editorial_libro` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_libro_lista_libros`),
  KEY `fk_lista_libros_idx` (`id_lista_libros`),
  CONSTRAINT `fk_lista_libros` FOREIGN KEY (`id_lista_libros`) REFERENCES `lista_de_libros` (`id_lista_libros`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
commit;  


Para Iniciar la aplicacion:
  
  http://localhost:8001/libreria
  

Construido con 
Thymeleaf
Spring Boot Framework
Docker
MySql 8





Version 1.0
