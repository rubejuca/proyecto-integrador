# PostgreSQL en Docker

1. Instalar Docker Desktop

2. Desde la terminal

Descarga la imagen Docker de Postgres

`docker pull postgres:15.7`

Crear el volumen donde almacenará los datos

`docker volume create postgres_data`

Crear directorio:
- En Linux o MacOS: `/tmp/postgresdata`
- En Windows: `c:\tmp\postgresdata`

Inicia el docker con el volumen creado

`docker run -e POSTGRES_PASSWORD=password -d -p 5432:5432 -v postgres_data:/tmp/postgresdata postgres:15.7`

Verifica que este corriendo

`docker ps`

Conectarse a
```
host: localhost
port: 5432
database: postgres
username: postgres
password: password
```

# Ejecutar desde la terminal

`./gradlew clean bootRun`