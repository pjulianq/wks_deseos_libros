#Listas de deseos de libros

API que permite crear listas de libros para los usuarios registrados en el sistema: Registro de usuario, registro de lista, asociar libro a la lista (búsqueda de la biblioteca de google)


#Comenzando 

Trataremos de dar instrucciones muy detalladas para poner el aplicativo en funcionamiento en tú ambiente local.


Pre-requisitos 

Java 11 o superior

Docker


#Instalación 

Clona el proyecto en tú máquina local tomandolo de https://github.com/pjulianq/wks_deseos_libros.git	

El repositorio consta de 5 microservicios:

web_lista_deseos: Contiene el front de la aplicación y cliente rest para comunicarse con los demás microservicios.

ms_cfg_eureka_server: Servicio para registro y localización de los demás microservicios.

ms_cfg_zuul_server: "proxy" para las peticiones

ms_usuarios: operaciones de usuarios

ms_libros: operaciones de libros

El primer servicio a levantar es eureka:

Abriendo una consola de comandos debes ubicarte en el microservicio y ejecutar

  cd <ruta_del_workpace>\wks_deseos_libros\ms_cfg_eureka_server
  
Con el comando dir podemos verificar que realmente estemos ubicados allí

  dir
  
Generamos Jar

  .\mvnw clean package (Al final nos debe aparecer BUILD SUCCES y nos debe indicar que se creó el jar en el target.
  
Creamos la imagen  

  docker build -t servicio-eureka-server:v1 .
  
Creamos la red 

  docker network create networklibrary
  
Levantamos una instancia de la imagen
  docker run -p 8761:8761 --name servicio-eureka-server --network networklibrary servicio-eureka-server:v1
  
Probamos que la instancia alla iniciado de manera correcta (miramos el log o probamos con http://localhost:8761/)  

Hacemos lo mismo para los demás microservicios:


  

Dí cómo será ese paso

Da un ejemplo
Y repite

hasta finalizar
Finaliza con un ejemplo de cómo obtener datos del sistema o como usarlos para una pequeña demo

Ejecutando las pruebas ⚙️
Explica como ejecutar las pruebas automatizadas para este sistema

Analice las pruebas end-to-end 🔩
Explica que verifican estas pruebas y por qué

Da un ejemplo
Y las pruebas de estilo de codificación ⌨️
Explica que verifican estas pruebas y por qué

Da un ejemplo
Despliegue 📦
Agrega notas adicionales sobre como hacer deploy

Construido con 🛠️
Menciona las herramientas que utilizaste para crear tu proyecto

Dropwizard - El framework web usado
Maven - Manejador de dependencias
ROME - Usado para generar RSS
Contribuyendo 🖇️
Por favor lee el CONTRIBUTING.md para detalles de nuestro código de conducta, y el proceso para enviarnos pull requests.

Wiki 📖
Puedes encontrar mucho más de cómo utilizar este proyecto en nuestra Wiki

Versionado 📌
Usamos SemVer para el versionado. Para todas las versiones disponibles, mira los tags en este repositorio.

Autores ✒️
Menciona a todos aquellos que ayudaron a levantar el proyecto desde sus inicios

Andrés Villanueva - Trabajo Inicial - villanuevand
Fulanito Detal - Documentación - fulanitodetal
También puedes mirar la lista de todos los contribuyentes quíenes han participado en este proyecto.

Licencia 📄
