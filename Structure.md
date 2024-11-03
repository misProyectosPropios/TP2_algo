# Strcture

## Heaps

 ### Methods

 + Encolar: 
    + Agrega un nuevo elemento a la cola de prioridad
    + Tiene una complejidad de O(log (n)) 
 + Desencolar:
    + Devuelve el primer elemento y lo elimina de la cola. 
    + Tiene uan complejidad de ...         Tiene que ser O(log n) 
 + Maximo:
    + Devuelve el primer elemento de la cola
    + Tiene una complidad de O(1)
 + EliminarElemento:
    + Elimina el elemento del heap respetando el invariante
    + Tiene una complejidad de ...         Tiene que ser O(log n)

## Ciudad

## BestEffort

### Methods 

 + nuevoSistema
      + Complejidad: O (|C| + |T|)
 + registrarTraslados
      + Complejidad: O (|traslados| log(|T|))
 + despacharMasRedituables
      + Complejidad: O(n (log(|T |) + log(|C|)))
 + despacharMasAntiguos
      + Complejidad: O(n (log(|T |) + log(|C|)))
 + ciudadConMayorSuperavit
      + Complejidad: O(1)
 + ciudadesConMayorGanancia
      + Complejidad: O(1)
 + ciudadesConMayorPerdida
      + Complejidad: O(1)
 + gananciaPromedioPorTraslado
      + Complejidad: O(1)

# AED

Vamos a tener dos heaps para los traslados:

+ max-heap siendo los más redituables la estructure
+ min-heap siendo los más antiguios 
+ handle para tener una idea de como se relaciona un heap con el otro
+ Array de ciudades, desde el 0 hasta el num-ciudades - 1
+ max-heap siendo el max del superavit 
+ tener un handle que relacionen las ciudades con el max-heap

## Implementaciones de los procs:

 + heaps:
      + Tiene que implementar: encolar, desencolar, quitar(elemento por referencia) y máximo
      + Los elementos del heap deben ser: 
          + Un ArrayList
          + Un comparator que se haya pasado para comparar según decida el usuario

     ### proc encolar (T element):

          array.add(element) //Agrega al final
        
     ### proc desencolar() -> T:

          array.add(element) //Agrega al final
     
     ### proc quitar() -> T:

          array.add(element) //Agrega al final

     ### prox maximo() -> T
          return this.array[0] //Como requiere tomamos que haya algún elemento
          array.add(element) //Agrega al final      
     

+ Ciudades:
     + Variables
          + Nombre
          + Ganancias
          + Perdidas

 + ## Array de ciudades:
      + Deberían de implementar: getters y despachador de traslados

     ### proc getGananciaDeCiudad(int nombre) -> Int:

          return this.array[nombre].ganancia
     
     ### proc getPerdidaDeCiudad(int nombre) -> Int:

          return this.array[nombre].perdida

     ## proc getCiudad(int nombre) -> Ciudad:
          
          return this.array[nombre]

     ### proc despacharTraslado(Traslado tras) -> Int:
          
          this.array[tras.origen] += tras.gananciaNeta
          this.array[tras.destino] -= tras.gananciaNeta

 + Handles, deberían de estar siempre iguales
     + Tenemos 2 handles importantes: 
          + Handle para ciudades con el heap
          + Handle para traslados
