# SpringBoot con JasperReport

Proyecto para demostrar integración de JasperReport y con Spring Boot.

## Tecnologías:

- Java 21.
- Spring Boot 3.2.1.
- Jasperreport 6.20.0
- H2 (Memoria)

## Generaración Docker

Compilar la imagen docker

```
docker build -t springboot-jaspereport 
```

Ejecutar la imagen

```
docker run --rm -p 8080:8080 springboot-jaspereport
```

## Probar la imagen

La URL para acceder en:

```
http://localhost:8080/mvc/
```

![Vista de la Imagen](/img/vista-web.png)

Versión Petición / Respuesta:

* http://localhost:8080/mvc/simple
* http://localhost:8080/mvc/conexion
* http://localhost:8080/mvc/parametros/100000
* http://localhost:8080/mvc/jrdatasource

La versión API REST:

Versión Simple:
```
curl --location 'http://localhost:8080/api/simple'
```

Versión Conexión:
```
curl --location 'http://localhost:8080/api/conexion'
```

Versión Parametros:
```
curl --location 'http://localhost:8080/api/parametros/1000'
```

Versión JRDataSource:
```
curl --location 'http://localhost:8080/api/jrdatasource'
```