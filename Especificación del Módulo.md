Especificación del módulo que vamos a implementar.

### Módulo BestEffort implementa SistemaEmpresarial {

```
var trasladosPorGanancia: max-heap ( Traslado )
		// Heap que almacena 'traslados'.
		// Ordena por 'gananciaNeta'.
		// En caso de empate, ordena por 'id'.
```

```
var trasladosPorAntiguedad: max-heap ( Traslado )
		// Heap que almacena 'traslados'.
		// Ordena por 'timestamp'.
		// En caso de empate, ordena por 'id'.
```

```
var mayorSuperavit: max-heap ( Ciudad ) 
		// Heap que almacena 'Ciudades'
		// Ordena por 'superavit'.
		// En caso de empate, ordena por 'nombre' (que es un número único).
```

```
var ciudadesTotales: array < Ciudad > 
		// 'Ciudades' ubicadas en el index de su 'nombre'.
		// La idea de esta variable es poder acceder de forma rápida a cada ciudad cuando necesitamos modificar
		// su ganancia o pérdida.
		// OBS -> Si quisiesemos buscar en el heap, debemos iterar (ya que no garantiza que la ciudad está en el
		//        index de su nombre).
```

```
var totalDespachados: int
		// Suma total de los 'Traslados' despachados.
		// Se actualiza cada vez que se despacha un Traslado.
```

```
var sumaDeGananciaDeDespachos: int
		// Suma total de las ganancias de cada traslado despachado.
		// Se actualiza cada vez que se despacha un Traslado.
```


```
var mayorPerdida: array < int > 
		// Lista que tiene los nombres de la ciudad que presenta mayor pérdida.
		// ¿Por qué es un array? Porque cuando hay mas de una ciudad con mayor pérdida, también va.
		// Se actualiza cada vez que se despacha un Traslado.
```

```
var mayorGanancia: array < int > 
		// Lista que tiene los nombres de la ciudad que presenta mayor ganancia.
		// ¿Por qué es un array? Porque cuando hay mas de una ciudad con mayor ganancia, también va.
		// Se actualiza cada vez que se despacha un Traslado.
```

> [!NOTE]
> La siguiente *variable de estado* está en **veremos**.
> ```
>	var trasladosTotales: arrayList < Traslados >
>		// Bolsa de 'Traslados'.
>		// No estan ordednados, es sólo el lugar donde ir a buscar el traslado.
>```

---
**Información Extra de las Clases que vamos a Utilizar :**

```
Class Ciudad {
	var nombre: int 
	var gananciaNeta: int 
	var perdidaNeta: int 
	var superavit: int = gananciaNeta - perdidaNeta
	var indiceAHeapSuperavit: int
}

// 'gananciaNeta' y 'perdidaNeta' se inicializan en cero.
// Se actualizan a al estar involucradas en el despacho de un traslado.
```

```
Class Traslado
	var id: int
	var origen: int
	var destino: int
	var gananciaNeta: int
	var timestamp: int

	// Estas dos variables tiene el índice donde se encuentra el Traslado en los heaps.
	var indiceAHeapGanancia : int
	var indiceAHeapAntiguedad : int
```
---

### `nuevoSistema`

```
Proc nuevoSistema ( in cantCiudades: int , in traslados: seq< InfoTraslados > : BestEffort {

	BestEffort res = new BestEffort () ;

	// Llenamos todas las variables de estado.
	int index = 0 ;                                                                    -> O(1)
	res.mayorPerdida = new ArrayList(int) ;					           -> O(1)
	res.mayorGanancia = new ArrayList(int) ;                                           -> O(1)
	while ( index < cantCiudades )                                                     -> Ciclo que se ejecuta C  veces: O(|C|)
		Ciudad añadirCiudad = new Ciudad ( index , 0 , 0 , index ) ;                       	-> O(1)
		res.mayorSuperavit.add( añadirCiudad ) ;			           	-> O(1)
 		res.ciudadesTotales.add( añadirCiudad ) ;                 	                -> O(1)
		res.mayorPerdida.add( añadirCiudad.nombre ) ;                              	-> O(1)
		res.mayorGanancia.add( añadirCiudad.nombre ) ;                             	-> O(1)
		index ++ ;                                                                 	-> O(1)
		
	index = 0 ;                                                                        -> O(1)
	while ( index < traslados.size() )                                     		   -> Ciclo que se ejecuta T  veces: O(|T|)
		tupla trasladoEncuestion = traslados[ index ] ;                      	   	-> O(1), igual que el de abajo (muy laga la línea jejeje)
		Traslado añadirTranslados = new Traslado ( trasladoEnCuestion[0] , trasladoEnCuestion[1] , trasladoEnCuestion[2] , trasladoEnCuestion[3] , trasladoEnCuestion[4] ,
                                                           index, index ) ;
		res.trasladosPorGanancia.add( añadirTranslados ) ;                         	-> O(1)
		res.trasladosPorAntiguedad.add( añadirTranslados ) ;	                   	-> O(1)
		index ++ ;                                                                 	-> O(1)

	res.totalDespachados = 0 ;						           -> O(1)

	floydAlgorithm ( res.mayorSuperavit ) ;						   -> O(|C|)
	floydAlgorithm ( res.trasladosPorGanancia ) ;					   -> O(|T|)
	floydAlgorithm ( res.trasladosPorAntiguedad ) ;					   -> O(|T|)

	
	return res ;								           -> O(1)
}
```

**Complejidad Total** -> O(|C| + |T|) 
- Al ser operaciones (ciclos) consecutivas, se suman sus complejidades.

### `registrarTraslados`

```
Proc registrarTraslados ( inout sistema: BestEffort , in traslados: seq<InfoTraslado> ) { 

	int index = 0 ;                                                       		-> O(1)
	while ( index < |traslados| )                                         		-> Este ciclo se ejecuta n = |traslados| veces: O(n log(T))
		
		// Tomo la tupla y fabrico el traslado.
		tupla cuestion = traslados[ indice ] ;                               		-> O(1), igual que el de abajo (muy larga la línea jejeje)
		Traslado añadir = new Traslado ( trasladoEnCuestion[0] , trasladoEnCuestion[1] , trasladoEnCuestion[2] , trasladoEnCuestion[3] , trasladoEnCuestion[4], transladoEnCuestion[5], sistema.trasladosPorGanancia.size() , sistema.trasladosPorAntiguedad.size() ) ;

		// Lo añado a los heaps.
		sistema.trasladosPorGanancia.encolar( añadir ) ;                            	-> O(log(T))
		sistema.trasladosPorAntiguedad.encolar( añadir ) ;                   		-> O(log(T))
		
		index ++ ;                                                           	-> O(1)
		
  }
```

**Complejidad Total** -> O(n log(T)) con n = |traslados|

> [!NOTE]
> El heap `mayorSuperavit` no se toca en este proc, ya que no se está despachando nada.
> Recordar que una ciudad modifica su ganancia y pérdida (y por ende su superavit) sólo cuando se despacha un traslado.

### `despacharMasRedituables`

```
Proc despacharMasRedituables ( inout sistema: BestEffort , in n: int ) : seq<int> {

	arrayList<int> res = new ArrayList() ;                                             -> O( 1 )
	
	int index = 0 ;                                                                    -> O( 1 )
	while ( (index != n) && (not sistema.trasladoPorGanancia.estaVacio()) )          -> Ciclo se ejecuta n veces: O(n (log (T) + log (C))) 
		
		Traslado despachado = sistema.trasladosPorGanancia.desencolar() ;           	     -> O(log (T))
		sistema.trasladosPorAntiguedad.eliminar( despachado.indiceAHeapAntiguedad ) ;        -> O(log (T)) 

		res.add(despachado.id) ;                                                   	     -> O(1)
		sistema.totalDespachados ++ ;                                              	     -> O(1)
		sistema.sumaDeGananciaDeDespachos += despachado.ganaciaNeta;                         -> O(1)
		
		sistema.ciudadesTotales[ despachado.origen ].ganaciaNeta += despacho.ganaciaNeta ;   -> O(1)
		sistema.mayorSuperavit.reOrdenar(sistema.ciudadesTotales[ despachado.origen ].indiceAHeapSuperavit) ;  -> O(log (C))

		sistema.ciudadesTotales[ despacho.destino ].perdidaNeta += despacho.gananciaNeta ;   -> O(1)
		sistema.mayorSuperavit.reOrdenar(sistema.ciudadesTotales[ despacho.destino ].indiceAHeapSuperavit) ;   -> O(log (C))
		
		// Actualizamos 'mayorPerdida' y 'mayorGanancia'
		if ( sistema.mayorGanancia[0].gananciaNeta < despachado.origen.gananciaNeta )        -> O(1)
			{sistema.mayorGanancia.clear();					     	     -> O(1)
			sistema.mayorGanancia.add(despachado.origen)}
		else if(sistema.mayorGanancia[0].gananciaNeta==despachado.origen.gananciaNeta){	     -> O(1)
			sistema.mayorGanancia.add(despachado.origen.gananciaNeta)}		     -> O(1)
		else{}

		if ( sistema.mayorPerdida[0].perdidaNeta < despachado.destino.perdidaNeta )         -> O(1)
			sistema.mayorPerdida.clear();						     -> O(1)
			sistema.mayorPerdida.add(despachado.origen)				     -> O(1)
		else if(sistema.mayorPerdida[0].perdidaNeta==despachado.destino.perdidaNeta)	      -> O(1)
			sistema.mayorPerdida.add(despachado.destino.perdidaNeta)
		else{}
		index ++ ;                                                                           -> O(1)

	return res ;                                                                       -> O(1)
}
```

**Complejidad Total** -> O(n (log (T) + log (C)))

> [!NOTE] 
> - `.eliminar(i)` de un heap -> este método toma el elemento de índice **i** (sabemos cuál es) , lo intercambia con el último (del array que modela el heap), mueve el puntero de `ultimoElemento` (del heap) y re-acomoda el heap.
> Básicamente, es lo mismo que el `desencolar()`, pero con un elemento del medio (no la raíz).
> - `reOrdenar()` es hacer `heapify-Up` o 'heapify-Down' segun corresponda. En este algoritmo, se hace ordenando por `ciudad.superavit`.

> [!WARNING]
> -`heapy-Up` y `heapy-Down` debe modificar handles (atributos de Traslados) cuando acomoda.

### `despacharMasAntiguos`

```
Proc despacharMasAntiguos ( inout sistema: BestEffort , in n: int ) : seq<int> {

	arrayList<int> res = new ArrayList() ;                                             -> O( 1 )
	
	int index = 0 ;                                                                    -> O( 1 )
	while ( (index != n) && (not sistema.trasladoPorAntiguedad.estaVacio()) )          -> Ciclo se ejecuta n veces: O(n (log (T) + log (C))) 
		
		Traslado despachado = sistema.trasladosPorAntihuedad.desencolar() ;           	     -> O(log (T))
		sistema.trasladosPorGanancia.eliminar( despachado.indiceAHeapGanancia ) ;            -> O(log (T)) 

		res.add(despachado.id) ;                                                   	     -> O(1)
		sistema.totalDespachados ++ ;                                              	     -> O(1)
		sistema.sumaDeGananciaDeDespachos += despachado.ganaciaNeta;                         -> O(1)
		
		sistema.ciudadesTotales[ despachado.origen ].ganaciaNeta += despacho.ganaciaNeta ;   -> O(1)
		sistema.mayorSuperavit.reOrdenar(sistema.ciudadesTotales[ despachado.origen ].indiceAHeapSuperavit) ;   -> O(log (C))

		sistema.ciudadesTotales[ despacho.destino ].perdidaNeta += despacho.gananciaNeta ;   -> O(1)
		sistema.mayorSuperavit.reOrdenar(sistema.ciudadesTotales[ despachado.destino ].indiceAHeapSuperavit) ;   -> O(log (C))
		
		// Actualizamos 'mayorPerdida' y 'mayorGanancia'
		if ( sistema.mayorGanancia[0].gananciaNeta < despachado.origen.gananciaNeta )        -> O(1)
			{sistema.mayorGanancia.clear();					     	     -> O(1)
			sistema.mayorGanancia.add(despachado.origen)}
		else if(sistema.mayorGanancia[0].gananciaNeta==despachado.origen.gananciaNeta){	     -> O(1)
			sistema.mayorGanancia.add(despachado.origen.gananciaNeta)}		     -> O(1)
		else{}

		if ( sistema.mayorPerdida[0].perdidaNeta < despachado.destino.perdidaNeta )         -> O(1)
			sistema.mayorPerdida.clear();						     -> O(1)
			sistema.mayorPerdida.add(despachado.origen)				     -> O(1)
		else if(sistema.mayorPerdida[0].perdidaNeta==despachado.destino.perdidaNeta)	      -> O(1)
			sistema.mayorPerdida.add(despachado.destino.perdidaNeta)
		else{}
		index ++ ;                                                                           -> O(1)

	return res ;                                                                       -> O(1)
}
```
**Complejidad Total** -> O(n (log (T) + log (C)))


> [!NOTE] 
> - `.eliminar(i)` de un heap -> este método toma el elemento de índice **i** (sabemos cuál es) , lo intercambia con el último (del array que modela el heap), mueve el puntero de `ultimoElemento` (del heap) y re-acomoda el heap.
> Básicamente, es lo mismo que el `desencolar()`, pero con un elemento del medio (no la raíz).
> - `reOrdenar()` es hacer `heapify-Up` o `heapify-Down` segun corresponda. En este algoritmo, se hace ordenando por `ciudad.superavit`.

> [!WARNING]
> -`heapy-Up` y `heapy-Down` debe modificar handles (atributos de Traslados) cuando acomoda.

### `ciudadConMayorSuperavit`

```
Proc ciudadConMayorSuperavit ( in sistema: BestEffort ) : int {
	return sistema.mayorSuperavit.consultarMax() ;     -> O(1)
}
```
**Complejidad Total** -> O(1)

### `ciudadesConMayorGanancia`

```
Proc ciudadesConMayorGanancia ( in sistema: BestEffort ) : array<int> {
	return sistema.mayorGanancia ;                     -> O(1)
}
```
**Complejidad Total** -> O(1)

### `gananciaPromedioPorTraslado`

```
Proc gananciaPromedioPorTraslado(in sistema: BestEffort): int {
	return sistema.sumaDeGananciaDeDespachos / sistema.totalDespachados ;     -> O(1)
}
```

**Complejidad Total** -> O(1)

> [!NOTE]
> Esa división es división entera -> OJO PERRITO MALVADO...

**Fin**.
