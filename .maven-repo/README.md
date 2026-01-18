# REPOSITORIO LOCAL AL PROYECTO

Este directorio es un repositorio local al proyecto del artefacto **org.hagane.blindchallenge-solution**.

Los artefactos que aquí se encuentran estarán disponibles para maven, ya sea a modo de dependencias de código o plugins o cualquier otro tipo de fin que pueda tener el pom.xml para un artefacto.

Maven sabe de la exiustencia de este repositorio porque viene indicado en el pom.xml:

```xml
	<repositories>
		<repository>
			<id>local-repo-blindchallenge</id>
			<url>file://${project.basedir}/.maven-repo</url>
		</repository>
	</repositories>
```

> **${project.basedir}** es una variable que indica el path del directorio raíz del proyecto. De esta forma podemos tener una especie de rutas "relativas" (al proyecto) en la configuración. En este caso la ruta es la carpeta **.maven-repo** situada en el directorio raíz del proyecto.

El objetivo concreto de hacer esto en este proyecto es poder incluir como dependencia de código los proyectos hermanos de este:

* org.hagane.blindchallenge-framework

  * En el que está toda la lógica que construye la aplicación cliente-servidor que envía los challenges, recoge la respuesta y compara con lo esperado.

* org.hagane.blindchallenge-challenges

  * Este proyecto contiene la colección de desafíos que se mandan al cliente para probar la solución que ha diseñado.

Como el repositorio tiene los artefactos ya compilados en un jar, el código fuente de estos proyectos no está disponible, sólo el código java compilado. Esto es necesario, ya que el objetivo del desafio es razonar sobre lo que se espera de nuestra solución paso a paso, y si se viera el código de **blindchallenge-challenges** perdería este sentido.

El código de **blindchallenge-framework** se podría haber dejado, pero es un proyecto bastante grande y habría distraído del objetivo del ejercicio que es escribir la solución. Además, de esta forma, lo que ven los programadores que van a intentar diseñar la solución "universal" para los desafíos enviados son sólo 4 clases bastante sencillas, con lo que se focaliza la atención en los logs del programa y en el razonamiento.
