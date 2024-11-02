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

 + Array de ciudades:
      + Deberían de implementar: getters y despachador de traslados

 + Handles, deberían de estar siempre iguales