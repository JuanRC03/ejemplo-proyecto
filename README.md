# Practica
## Abrir proyecto con Docker
Para abir el proyecto con docker debes tener instalado previamnte GIT y Docker Desktop(Tenerlo ya abierto)
### 1. Descarga el proyecto
Descarga el proyecto el proyecto de github y descomprime.

### 2. Abre Git Bash
Abre Git Bash en la carpeta "Practica". Puedes hacerlo haciendo clic derecho en la carpeta y seleccionando "Git Bash Here".

### 3. Ejecuta los siguientes comandos:
- Construir el contenedor de la aplicación Angular:
```
docker-compose build angular_app
```
- Iniciar los contenedores de la aplicación (Antes de ejecutar este comado tomar en cuenta que los siguentes puertos esten disponibles: 80, 8080, 8081, 3306):
```
docker-compose up
```
Esto ejecutará tanto el backend (base de datos y Java Spring Boot) como el frontend (aplicación Angular).

###  4. Comprobación de la conexión a la base de datos
Si experimentas problemas de conexión entre la base de datos (java_db) y el backend (java_app), puede ser porque la base de datos aún no se ha creado completamente cuando el backend (java_app) intento conectarse con la base de datos. Por ende debe esperar en el "Git Bash" a que salgan estas lienas donde se identifique que el puerto "3306" de la base de datos ya esta abierto y que la base de datos se ha creado con exito:
```
java_db | 2023-10-03T01:48:35.743382Z 0 [System] [MY-011323] [Server] X Plugin ready for connections. Bind-address: '::' port: 33060, socket: /var/run/mysqld/mysqlx.sock
```
```
java_db | 2023-10-03T01:48:35.743512Z 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '8.0.34' socket: '/var/run/mysqld/mysqld.sock' port: 3306 MySQL
```
```
Community Server - GPL.
```

### 5. Iniciar el contenedor de la aplicación Java (En caso que no este iniciado)
Una vez que la base de datos se haya creado correctamente (Paso 4), procede a abrir Docker Desktop y accede al contenedor llamado "practicanoguiada". Luego, inicia el contenedor "java_app". En el "Git Bash" espera hasta que aparezca la siguinte linea 
```
java_app     | ---------------------------Java Spring Boot Iniciado-------------------------------------
```

### 6. Acceder a la aplicación web
Desde un navegador accede a la aplicacion web a traves de http://localhost:8081/

### 7. Acceso como administrador
Para acceder como administrador, tiene que ingresar las siguintes credenciales en el login:
- Email
```
admin@gmail.com
```
- Contraseña:
```
12345
```




## Instrucciones para ejecutar la aplicación de forma local

Estas son las instrucciones para ejecutar la aplicación de forma local en tu sistema.

### Pasos:

1. Descargar el proyecto y descomprimirlo.

2. Ingresa a la carpeta "practica".

3. Haz clic derecho sobre la carpeta "practica-front" y ábrela con un editor de código, por ejemplo, Visual Studio Code.

4. Abre un terminal dentro del editor de código (Visual Studio Code) y ejecuta los siguientes comandos para cargar todas las dependencias e iniciar la aplicación Angular:

   ```
   npm install
   ng serve
   ```
5. Para ejecutar el backend, necesitas MySQL/XAMPP. Accede al panel de control de XAMPP y haz clic en "Start" para Apache y MySQL. Luego, en un navegador, accede a phpMyAdmin y crea una base de datos con el nombre "db-practica".

6. Abre el backend (practica-back) con un IDE o editor de código, como NetBeans. Accede al archivo "application.properties" y realiza las siguientes modificaciones:

    - Comenta las siguientes líneas que sirven para ejecutar a traves de docker:

    ```
    spring.datasource.url=jdbc:mysql://java_db:3306/db-practica
    spring.datasource.username=root
    spring.datasource.password=12345
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    ```
    - Descomenta las siguientes líneas:

    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/db-practica
    spring.datasource.username=root
    spring.datasource.password=12345
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    ```
    - Configura el usuario y la contraseña de MySQL en esta parte:
    ```
    spring.datasource.username=root #usuario que tengas
    spring.datasource.password=12345 #contraseña del usuario
    ```
7. Dentro del archivo "Main.java", haz clic derecho y selecciona "Run File" para iniciar el backend.

8. En el navegador, accede a la aplicación a través de http://localhost:4200/.

9. Acceso como administrador. Para acceder como administrador, tiene que ingresar las siguintes credenciales en el login:
    - Email
    ```
    admin@gmail.com
    ```
    - Contraseña:
    ```
    12345
    ```
