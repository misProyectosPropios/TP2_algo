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
var mayorSuperavit: max-heap ( (1) ) 
		// Heap que almacena 'ciudades' indirectamente (porque en realidad tiene el index de la ciudad en el array 'ciudadesTotales').
		// Ordena por 'superavit'.
		// En caso de empate, ordena por 'nombre' (que es un número único).
		// (1) -> index de la ciudad en 'ciudadesTotales'.
```

```
var ciudadesTotales: array < tuplas< Ciudad , (1) > > 
		// 'Ciudades' ubicadas en el index de su 'nombre'.
		// (1) -> index de la ciudad en 'mayorSuperavit'.
```

```
var totalDespachados: int
		// Suma total de los 'Traslados' despachados.
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
	var perdidaNeta : int 
	var superavit: int = gananciaNeta - perdidaNeta
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
		Ciudad añadirCiudad = new Ciudad ( index , 0 , 0 ) ;                       	-> O(1)
		res.mayorSuperavit.add( index ) ;			           	   	-> O(1)
 		res.ciudadesTotales.add( tupla< añadirCiudad , index > ) ;                 	-> O(1)
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
		Traslado añadir = new Traslado ( trasladoEnCuestion[0] , trasladoEnCuestion[1] , trasladoEnCuestion[2] , trasladoEnCuestion[3] , trasladoEnCuestion[4]
						 sistema.trasladosPorGanancia.size() , sistema.trasladosPorAntiguedad.size() ) ;

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
	while ( (index != n) && (not sistema.trasladoPorAntiguedad.estaVacio()) )          -> Ciclo se ejecuta n veces: O(n (log (T) + log (C))) 
		
		Traslado despachado = sistema.trasladosPorGanancia.desencolar() ;           	     -> O(log (T))
		sistema.trasladosPorAntiguedad.eliminar( despachado.indiceAHeapAntiguedad ) ;        -> O(log (T)) 

		res.add(despachado.id) ;                                                   	     -> O(1)
		sistema.totalDespachados ++ ;                                              	     -> O(1)
		
		sistema.ciudadesTotales[ despachado.origen ].ganaciaNeta += despacho.ganaciaNeta ;   -> O(1)
		sistema.mayorSuperavit.reOrdenar() ;                                                 -> O(log (C))

		sistema.ciudadesTotales[ despacho.destino ].perdidaNeta += despacho.gananciaNeta ;   -> O(1)
		sistema.mayorSuperavit.reOrdenar() ;                                                 -> O(log (C))
		
		// Actualizamos 'mayorPerdida' y 'mayorGanancia'
		if ( sistema.mayorPerdida[1].gananciaNeta >= despachado.origen.gananciaNeta )        -> O(1)
			ArrayList update = new ArrayList[int] ;					     -> O(1)
			update.add(despachado.origen) ;						     -> O(1)
			sistema.mayorGanancia = update ;					     -> O(1)

		if ( sistema.mayorPerdida[1].perdidaNeta >= despachado.destino.perdidaNeta )         -> O(1)
			ArrayList update2 = new ArrayList[int] ;				     -> O(1)
			update2.add(despachado.destino) ;					     -> O(1)
			sistema.mayorPerdida = update2 ; 					     -> O(1)

		index ++ ;                                                                           -> O(1)

	return res ;                                                                       -> O(1)
}
```

**Complejidad Total** -> O(n (log (T) + log (C)))

> [!NOTE] 
> - `.eliminar(i)` de un heap -> este método toma el elemento de índice **i** (sabemos cuál es) , lo intercambia con el último (del array que modela el heap), mueve el puntero de `ultimoElemento` (del heap) y re-acomoda el heap.
> Básicamente, es lo mismo que el `desencolar()`, pero con un elemento del medio (no la raíz).
> - `reOrdenar()` es hacer `heapify`. En este algoritmo, el `heapify` se hace en función de `ciudad.superavit`.

>[!WARNING]
> - No estamos acomodando el heap de trasladosMasAntiguos a medida que vamos sacando elemento, ni los handles

**Comentarios Generales**
-`floyd` debe modificar handles cuando acomoda.
- `encolar` debe devolver la posición (en el heap-array) donde queda acomodado el elemento añadido (para poder mantener el handles).

**Fin**... por ahora.
