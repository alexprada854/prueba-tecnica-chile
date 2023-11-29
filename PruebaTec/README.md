# Instalacion Aplicación Registrar Usuario  

### Descripcion General
Esta aplicación fue creada como prueba tecnica de trabajo, fue hecha desde IDE eclipse con Maven, se uso la plantilla que ofrece la 
pagina de Spring Initializr https://start.spring.io/, este proyecto publica un servicio REST el cual recibe el archivo Json como parametro y lo inyecta al 
metodo GuardarRegistro el cual contiene la logica basica para validar los datos ingresados antes de persistirlo en la Base de datos, la cual para efectos
del ejercicio, es en memoria por medio de H2 y JPA, por ultimo se espcifico a traves del pom.xml, el uso de OpenApi3 (antiguo swangger) para generar la documentacion automatica, para este caso en http://localhost:8081/swagger-ui/index.html. <br>
Para este proyecto se cumplierón las siguientes especificaciones:

* [Manejo de dependencias con MAVEN](https://docs.spring.io/spring-boot/docs/3.0.9/maven-plugin/reference/html/)
* [Spring Boot 3](https://docs.spring.io/spring-boot/docs/3.0.9/api/org/springframework/boot/package-summary.html)
* [Java 17](https://docs.oracle.com/en/java/javase/17/docs/api/)
* [OpenApi 3, antinguo Swangger](https://swagger.io/specification/)
* [Json Web Token](https://developer.okta.com/blog/2018/10/31/jwts-with-java)

### Especificaciones del requerimiento
* [Endpoint para insertar el registro] (http://localhost:8081/api/prueba/saveReg)
* [Las respuestas exitosas y fallidas por medio del formato Json] ({"mensaje": "mensaje de error"})
* [El EndPoint publicado recibe una cadena tipo Json con 3 atributos y un cuarto con un arreglo de telefonos]
* [Configuracion de respuestas Http] (200=OK, 208=Correo duplicado, 400=Validaciones de Constraint y demas)
* [En caso de exito retorna el registro con los campos adicionales de fecha en formato unix y vairable booleana que indica si esta activo o no]
* [Correo con validacion desde la anotacion de la definicion del atributo]
* [Password con validacion desde la anotacion pero no se encontro el modo de dejar el patron dinamico, con mas tiempo tal vez]  


### Instalación y despliegue
Los siguientes son los pasos para instalación:

* [Descargar el proyecto de GitHub en https://github.com/alexprada854/prueba-tecnica-chile.git ](https://github.com/alexprada854/prueba-tecnica-chile.git)
* En ../PruebaTec/target/PruebaTec-0.0.1-SNAPSHOT.jar se encuentra el paquete que debe desplegar en el servidor de aplicaciones del ambiente permanente, por defecto despliega en puerto 8081
* [Consumir desde Postaman o en otro entorno en http://localhost:8081/api/prueba/saveReg](http://localhost:8081/api/prueba/saveReg) 
* [Ver documentacion de la Api en http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

