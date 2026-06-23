# Proyecto: Nomadly

## Descripción del Contexto
Nomadly es una solución de arquitectura de microservicios desarrollada para la gestión integral de un dominio de **ventas** (incluyendo contratos, pagos, seguros y servicios adicionales), aplicando el patrón de diseño CSR (Controller-Service-Repository). El sistema busca garantizar la separación funcional de responsabilidades, la integridad de los datos a través de migraciones automatizadas y una comunicación distribuida eficiente y síncrona mediante **FeignClient** bajo un ecosistema de microservicios.

## Integrantes
* Carlos Joaquin Ramirez Gonzalez
* Sebastian Moreno

## Listado de Microservicios Implementados
* **Nomadly-Service**: Microservicio principal para la gestión central del negocio (usuarios, contratos, pagos). Incluye reglas de negocio, validaciones y acceso a base de datos MySQL gestionada con Flyway.
* **Eureka-Server**: Central de registro y descubrimiento de servicios, encargada de mantener el inventario de microservicios activos en tiempo real.
* **API-Gateway**: Componente encargado de centralizar el enrutamiento y administrar el flujo de solicitudes externas hacia las distintas instancias de Nomadly.

## Documentación Técnica
La documentación de los endpoints ha sido generada de forma automatizada mediante **Swagger/OpenAPI**.
* **Documentación local**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Instrucciones de Ejecución

### Entorno Local
1. Asegúrese de tener **Laragon** en ejecución con el motor de **MySQL** activo en el puerto `3307`. No es necesario crear tablas manualmente; Flyway se encargará de las migraciones al iniciar.
2. Clone el repositorio y abra el proyecto en su editor.
3. Compile el proyecto mediante Maven (`clean` y `compile`).
4. Inicie los servicios en el siguiente orden estricto:
   * Inicie el proyecto `eureka-server` (Puerto 8761).
   * Inicie el proyecto `Nomadly` (Puerto 8080 o dinámico).
   * Inicie el proyecto `api-gateway`.
5. Verifique el registro exitoso en el panel de Eureka: [http://localhost:8761](http://localhost:8761).

### Despliegue Remoto
* **Plataforma utilizada:** Railway
* **Base de Datos Remota:** MySQL (Aprovisionada en Railway)
* **Estado:** Operativo. El sistema utiliza variables de entorno dinámicas (`${MYSQL_URL}`, `${PORT}`, etc.) para adaptarse automáticamente a la nube.

## Pruebas Unitarias
Se han implementado pruebas unitarias utilizando **JUnit** y **Mockito** para asegurar la calidad del código, comprobando la lógica de los servicios aislados de la base de datos siguiendo la estructura Given-When-Then.
* **Cobertura actual:** [Insertar % de cobertura, ej: >80%].
* **Ejecución:** `mvn test`