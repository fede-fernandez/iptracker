# iptracker
# Federico Fernandez Barra
# mail: fede.a25@gmail.com - federicofernandezbarra@yahoo.com
# Instrucciones

Correr los comandos sobre la carpeta donde se encuentra el Dockerfile

```
docker build -t iptracker .
docker run -p 3783:3783 -t iptracker
```

Acceder desde http://localhost:3783
(En caso de estar en una vm, verificar con que host se resuelve)

Para matar el proceso
```
docker ps
docker kill <PID>
```