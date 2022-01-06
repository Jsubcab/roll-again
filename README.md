# Roll again - Backend

Proyecto de Trabajo de Final 2020-2021 de Grado para la Universitat Oberta de Catalunya por el estudiante Jsubcab que cursa el grado de Ingenieria en Informática.

### 1. Descripción

Aplicativo web destinado a la compra/venta de productos de primera/segunda mano de juegos de mesa. Desarrollado en Java (Backend) y React (Frontend) con bases de datos propia en localhost (PostgreSQL).

Este repositorio contiene toda la información del Backend del aplicativo en Java que permitirá emular el aplicativo y solicitar desde cualquier Frontend los datos pertinentes.

Hay que tener en cuenta que el proyecto sigue en desarrollo y falta finalizar la parte Frontend para que sea funcional la parte visual del cliente.

### 2. Como instalar y hacer funcionar el proyecto

#### 2.1 Software requerido

El proyecto esta subido para que funcione en localhost, pero es necesario tener instalado 2 tecnologias:

- Gradle > 6.
- Java > 14.
- PostgreSQL > 13.

#### 2.2 Instrucciones para instalar

##### 2.2.1 Base de datos
- Crear una base de datos con PostgreSQL en el terminal (Nombre recomendado: rollagain).
- (Opcional) Crear un usuario en PostgreSQL con permisos a la base de datos rollagain.

##### 2.2.2 IDE
- Abrir en el marketplace del IDE la extensión Kotlin.
- Clonar repositorio.
- Abrir con IDE (Recomendable IntelliJ).
- Refrescar Gradle del proyecto dentro del IDE.
- Actualizar el application.proporties con los datos que apunten a la base de datos de PostgreSQL creada en el punto 1.
- Actualizar, si es necesario, el usuario y contraseña.

A continuación, si se inicia la clase main el proyecto debería quedar funcionando detrás.

##### 2.2.3 Posibles errores

- En el caso que se tenga instalado Java y Gradle en el terminal, revisar que dentro del proyecto este seleccionado el SDK correctamente.


### 3. Como usar el proyecto

Una vez el aplicativo esta en funcionamiento se pueden realizar llamadas desde cualquier tipo de software externo, ya sea Postman, otro sistema local, etc. Hay que tener en cuenta que los permisos para poder realizar las llamadas desde un Frontend pueden ser modificados en la clase DemoApplication.

### 4. Pruebas

Las pruebas se pueden realizar via Postman.
