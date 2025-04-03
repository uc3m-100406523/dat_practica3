Práctica 3: Bases de datos
===
Diseño de Aplicaciones Telemáticas

## Autores

- Juan Manuel Espinosa Moral
- José Manuel García Núñez

## Descripción de la práctica

En esta práctica se debe realizar una aplicación en modo texto que modele un sistema "Gestor de Actividades deportivas". La aplicación a realizar debe modelar un sistema en el cual hay un conjunto de actividades deportivas disponibles que se ofrecen a un determinado precio, tienen un número limitado de plazas, se desarrollan en un determinado pabellón y a las cuales diferentes usuarios se pueden matricular. Usuarios, actividades y pabellones se pueden crear y eliminar por medio de la persona que representa la figura del gestor. A la aplicación se podrá acceder con dos tipos diferente de perfil: gestor o usuario.
- Con el perfil de gestor la aplicación debe poder mostrar un listado de todos los usuarios que hay en el sistema en un determinado momento, un listado de todas las actividades disponibles que hay en el sistema en un determinado momento, un listado de todos los pabellones disponibles en un determinado momento, un listado de todas las actividades de las que quedan plazas libres junto con el número de plazas que quedan libres en la actividad y poder añadir y eliminar tanto usuarios como actividades como pabellones.
- Con el perfil de usuario se deben poder listar todas las actividades en las que queden plazas disponibles o bien aquellas actividades que cumplan una determinada condición y además ese determinado usuario se podrá apuntar o desinscribir en aquellas actividades que considere oportunas y podrá listar las actividades en las cuales está actualmente matriculado.

La aplicación se implementará como un conjunto de clases (el número de clases, forma de las clases, relaciones entre ellas, etc. dependerá de la solución específica propuesta) y tendrá una clase donde se ubicará el método main que deberá llamarse Control.java. La aplicación pedirá por pantalla que perfil queremos o la posibilidad de registrarse como nuevo usuario, y seguidamente mostrará las opciones asociadas a cada perfil o las opciones de registro. La aplicación deberá comunicarse con la base de datos para efectuar las operaciones que se vayan seleccionando al introducirlas por pantalla.

Al ejecutar la aplicación a partir del fichero Control, se debe mostrar por pantalla lo siguiente:

```
Por favor, seleccione una opción:

    A. Entrar como Gestor

    B. Entrar como Usuario

    C. Registrar como Usuario
```

Seguidamente el usuario debe introducir por teclado una A, una B, o bien una C. En caso de que introduzca una A, la aplicación en primer lugar le pedirá la contraseña de gestor y en caso de que sea correcta deberá mostrar por pantalla otro menú, que tenga las opciones que puede tomar un Gestor. Dicho menú debe ser como sigue:

```
1. Añadir usuario

2. Eliminar usuario

3. Añadir actividad

4. Eliminar actividad

5. Añadir pabellón

6. Eliminar pabellón

7. Listar todos los usuarios

8. Listar todas las actividades

9. Listar todos los pabellones

10. Listar actividades en las que quedan plazas disponibles

11. Salir de la aplicación
```

Quien esté ejecutando la aplicación y haya elegido el perfil de gestor y haya podido acceder deberá introducir por teclado una de las siguientes opciones (de la 1 a la 11). Para cada elección particular se deberán realizar las operaciones oportunas en la base de datos. Si se elige la opción 1, entonces se deberán pedir seguidamente todos los campos necesarios de la tabla de usuarios para crear ese nuevo usuario. Si se elige la opción 3, del mismo modo se deberán pedir todos los campos necesarios de la tabla de actividades para crear la nueva actividad y si se elige la opción 5 se deberán pedir todos los datos necesarios de la tabla de pabellones para crear un nuevo pabellón en la tabla. Para las opciones de eliminar usuarios (2), actividades (4) y pabellones (6), la aplicación pedirá el valor de aquel campo que es la clave primaria de la tabla respectiva. En cualquiera de las otras opciones que son listar usuarios (7), actividades (8), pabellones (9) y listar actividades con plazas disponibles (10), no se deberá pedir más información por teclado y se deberá mostrar por pantalla el resultado de la acción seleccionada, mostrando todos los campos de cada tabla. En el caso de listar actividades en las que quedan plazas disponibles se deberá mostrar adicionalmente el número de plazas que quedan libres en cada actividad.

En cualquiera de las opciones seleccionadas (excepto en la de "Salir de la aplicación"), una vez ejecutada deberá volver al mismo menú del gestor.

En caso de que introduzca una B, la aplicación en primer lugar le pedirá el nombre de usuario y su password y en caso de que los datos de login sean correctos (de acuerdo con la tabla de usuarios) deberá mostrar por pantalla otro menú, que tenga las opciones que puede tomar un usuario. Dicho menú debe ser como sigue:

```
1. Visualizar todas las actividades

2. Visualizar todos los pabellones

3. Visualizar actividades para las que quedan plazas libres

4. Visualizar las actividades para las que hay plazas libres costando menos que una cierta cantidad

5. Visualizar las actividades para las que hay plazas libres que se imparten en un determinado pabellón

6. Obtener toda la información sobre una determinada actividad

7. Visualizar las actividades en las que estoy inscrito

8. Inscribirse a una actividad

9. Quitarse de una actividad

10. Salir de la aplicación
```

Quien esté ejecutando la aplicación y haya elegido el perfil de usuario y haya podido acceder mediante un login y password de usuario válido, deberá introducir por teclado una de las siguientes opciones (del 1 al 10). Para cada elección particular se deberán realizar las modificaciones oportunas en la base de datos. Si se elige la opción 1, 3, 4, 5, 6, o 7 se deberán visualizar aquellas actividades que reúnan el requisito especificado y en la visualización se deben poder observar todos los campos relacionados con la tabla de actividades y además todos los campos relacionados con el pabellón donde se realiza la actividad. Además para las opciones 4, 5, 6, y 7 tras la elección se deberá pedir al usuario el dato sobre el cual se realizará la búsqueda. Por otro lado si se elige la opción 8 u opción 9 se deberá pedir al usuario el identificador de la actividad en la que se quiere inscribir o de la que se quiere eliminar procediendo a hacer el procesado adecuado en la base de datos.

En cualquiera de las opciones seleccionadas (excepto en la de "Salir de la aplicación"), una vez ejecutada deberá volver al mismo menú del usuario.

Finalmente, si un usuario introduce la opción C, la aplicación le pide su información de registro, que incluye login, contraseña, nombre, apellido, direcció y número de teléfono, y seguidamente la aplicación debe almacenar esa información del usuario en la base de datos.

Adicionalmente, como requisito de la práctica, la solución debe mostrar una clara separación entre el control, la vista en forma de texto, y el modelo de la base de datos. Esta modularidad facilita el reuso; de hecho en una próxima práctica (sobre JEE) reusaremos la parte de código relativa a la interacción con la base de datos para la aplicación basada en web.

Para simplificar la práctica, reduciendo el código necesario para chequeo de errores, podéis suponer para todos los ejercicios a realizar que cuando se inserta una fila en la base de datos, esta fila no existía previamente (quien introduce los datos suponemos que ya tiene cuidado de hacerlo así). También podéis suponer que cuando se elimina una fila de la base de datos, esa fila existe en la base de datos (del mismo modo que antes quien introduce los datos suponemos que ya tiene cuidado de hacerlo así).

## Despliegue

### Acceso

La clave de administrador está fijada a _manager_.

### Base de datos

El sistema requiere de una base de datos SQL en la que se guarda la información.
El modelo de datos requerido puede crearse con las líneas indicadas en el fichero [`files/TABLE_CREATION.txt`](/files/TABLE_CREATION.txt).

La base de datos debe configurarse con los siguientes parámetros:
con las siguientes características:
- Nombre: `sporting_manager`
- URL: `jdbc:mysql://localhost/sporting_manager`
- Usuario: `root`
- Clave de acceso: `password`