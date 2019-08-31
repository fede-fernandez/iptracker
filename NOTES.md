Temas de tiempo:
- Hice los tests contra los servicios reales, tenía que contemplar que estos no esten disponibles a la hora de correrlos, por lo que era necesario disponer de mocks de los mismos.
- Utilicé para los repositorios listas y mapas en memoria, los cuales no son persistentes a la hora de finalizar la aplicación. Podría haber utilizado redis para las monedas, redis/mongodb para los paises. De más está decir que la aplicación solo puede correr en una instancia, ya que en varias no se contabilizarían los reportes.
- Me hubiera gustado también exponer servicios como application/json además de utilizar el MVC con capa de presentación. Esto serviría para poder compartir una API que pueda ser reutilizada por otras personas.
- Vinculado a lo anterior, también hubiera hecho un swagger.
- El frontend es bastante básico y no tiene validaciones dentro del mismo, se accede directo al servidor para validar. 
- El frontend tiene sectores repetidos que podrían haber sido abstraidos y reutilizados. Por ejemplo el header.
- Hacer deploy de la aplicación en algún hosting y utilizar un CI (por ejemplo travis) para realizar el manejo de las builds.
- Incluir coverage, revisarlo y verificar que se cubra al menos el 99% del código en los tests.

En el código hay unos ifs raros (?) porque contemplé de los servicios provistos lo siguiente:

- Malaysia no tiene language code/name (ip de ejemplo 219.93.183.103)
- Virgin Islands (British) no tiene currency code (ip de ejemplo 66.248.180.255)
- United States Minor Outlying Islands no tiene coordenadas (ip de ejemplo 136.22.79.112)

La aplicación está preparada para no fallar frente a estos tres posibles casos:
- No tener idiomas
- No tener monedas
- No tener coordenadas

No tiene en cuenta:
- No tener zonas horarias / horarios
- No tener codigo ISO
