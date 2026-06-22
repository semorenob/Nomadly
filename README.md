# Nomadly

### Ejecución
Primero hay que construir la imagen Docker:

```bash
docker build -t nomadly:latest .
```

Luego, se ejecuta la imagen construida (El puerto está configurado en 3307, pero se puede modificar en application.yml en la carpeta resources):

```bash
docker run -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3307/nomadly_db nomadly:latest  
```