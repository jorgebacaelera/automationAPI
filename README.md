## Pruebas automatizadas de los APIs PET

En el feature Mascotas se detalla los pasos a seguir de los flujo de creacion, actualización, consulta y eliminación del registro. 
Se trabajó con la librería SerenityRest propio de Serenity.



## Como requisito a la ejecución contar Java desde la versión 8 en adelante

Para verificar la versión de java, abrir una ventana de comando de window y ejecutar:

    java -version 

## Contar con Maven instalado

Para verificar la versión de Maven, abrir una ventana de comando de window y ejecutar:

    mvn --version 

## Ejecutar los casos de prueba automatizados

Para ejecutar el escenario de prueba ejecutar el siguiente comando Maven:

    mvn clean verify -pl pet-api -am 
    
## Visualizar los reportes

Para visualizar el  reporte de la prueba, se creará el reporte index.html automáticamente en el directorio 

    /target/site/serenity/index.html

