# Proyecto: Nomadly

## Descripción del Contexto
Nomadly es una solución de arquitectura de microservicios desarrollada para la gestión integral de alumnos, apoderados y usuarios, aplicando el patrón de diseño CSR (Controller-Service-Repository). El sistema busca garantizar la separación funcional de responsabilidades, la integridad de los datos y una comunicación distribuida eficiente bajo un ecosistema de microservicios.

## Integrantes
* Carlos Joaquin Ramirez Gonzalez
* Sebastian Moreno

## Listado de Microservicios Implementados
* **Nomadly-Service**: Microservicio principal para la gestión de usuarios, alumnos y apoderados. Incluye reglas de negocio, validaciones y acceso a base de datos MySQL.
* **Eureka-Server**: Central de registro y descubrimiento de servicios, encargada de mantener el inventario de microservicios activos en tiempo real.
* **API-Gateway**: [Por implementar] Componente encargado de centralizar el enrutamiento y administrar el flujo de solicitudes externas.

## Documentación Técnica
La documentación de los endpoints ha sido generada mediante **Swagger/OpenAPI**.
* **Documentación local**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Instrucciones de Ejecución
### Entorno Local
1. Asegúrese de tener **Docker** y **MySQL** (puerto 3307) corriendo.
2. Clone el repositorio y compile los proyectos mediante Maven.
3. Inicie primero el proyecto `eureka-server` en el puerto 8761.
4. Inicie el proyecto `Nomadly` en el puerto 8080.
5. Verifique el registro exitoso en el panel de Eureka: [http://localhost:8761](http://localhost:8761).

### Despliegue Remoto
* Plataformas utilizadas: [Ej: Railway, Render].
* Estado: Operativo.

## Pruebas Unitarias
Se han implementado pruebas unitarias utilizando **JUnit** y **Mockito** para asegurar la calidad del código, siguiendo la estructura Given-When-Then.
* Cobertura actual: [Insertar % de cobertura, ej: >80%].
* Ejecución: `mvn test`.