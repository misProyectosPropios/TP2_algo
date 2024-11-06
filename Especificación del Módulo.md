Especificación del módulo que vamos a implementar.

### Módulo BestEffort implementa SistemaEmpresarial {

```
var trasladosPorGanancia: max-heap ( tuplas< Traslado , int , int> )
		// Heap que almacena 'traslados'.
		// Ordena por 'gananciaNeta'.
		// En caso de empate, ordena por 'id'.
```

```
var trasladosPorAntiguedad: max-heap ( tuplas< Traslado , int , int > )
		// Heap que almacena 'traslados'.
		// Ordena por 'timestamp'.
		// En caso de empate, ordena por 'id'.
```

```
var mayorSuperavit: max-heap ( tuplas< int , int , int > ) 
		// Heap que almacena 'ciudades'.
		// Ordena por 'superavit'.
		// En caso de empate, ordena por 'nombre' (que es un número único).
```

```
var ciudadesTotales: array < Ciudades > 
		// 'Ciudades' ubicadas en el index de su 'nombre'.
```

```
var totalDespachados: int
		// Suma total de los 'Traslados' despachados.
		// Se actualiza cada vez que se despacha un Traslado.
```

```
var mayorPerdida: int 
		// Nombre de la ciudad con mayor pérdida.
		// Se actualiza cada vez que se despacha un Traslado.
```

```
var mayorGanancia: int 
		// Nombre de la ciudad con mayor ganancia.
		// // Se actualiza cada vez que se despacha.
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
```
---

### `nuevoSistema`

```
Proc nuevoSistema ( in cantCiudades: int , in traslados: seq< InfoTraslados > : BestEffort {

	BestEffort res = new BestEffort () ;
	
	int index = 0 ;
	while ( index < cantCiudades )                                         -> Ciclo que se ejecuta C  veces: O(|C|)
		Ciudad añadirCiudad = new Ciudad ( index , 0 , 0 ) ;                       -> O(1)
		res.ciudadesTotales.add( añadirCiudad ) ;                                  -> O(1)
		res.mayorSuperavit.add( añadirCiudad ) ;				   -> O(1)
		index ++ ;                                                           -> O(1)
		
	index = 0 ;                                                             -> O(1)
	while ( index < traslados.size() )                                     -> Ciclo que se ejecuta T  veces: O(|T|)
		tupla trasladoEncuestion = traslados[ index ] ;                      -> O(1), igual que el de abajo (muy laga la línea jejeje)
		Traslado añadirTranslados = new Traslado ( trasladoEnCuestion[0] , trasladoEnCuestion[1] , trasladoEnCuestion[2] , trasladoEnCuestion[3] , trasladoEnCuestion[4] ) ;
		res.trasladosPorGanancia.add( añadirTranslados ) ; 			     -> O(1)
		res.trasladosPorAntiguedad.add( añadirTranslados ) ;			     -> O(1)
		index ++ ;                                                           -> O(1)
		
	}

	floyd ( res.mayorSuperavit ) ;						-> O( |C| )
	floyd ( trasladosPorGanancia ) ;					-> O( |T| )
	floyd ( trasladosPorAntiguedad ) ;					-> O( |T| )

	res.mayorPerdida = 0 ;							-> O(1)
	res.mayorGanancia = 0 ;							-> O(1)
	res.totalDespachados = 0 ;						-> O(1)
	return res ;								-> O(1)
```

**Complejidad Total** -> O(|C| + |T|) 
- Al ser operaciones (ciclos) consecutivas, se suman sus complejidades.

### `registratTraslados`

```
Proc registrarTraslados ( inout sistema: BestEffort , in traslados: seq<InfoTraslado> ) { 

	int index = 0 ;                                                       -> O(1)
	while ( index < |traslados| )                                         -> Este ciclo se ejecuta n = |traslados| veces: O(n log(T))
		
		// Tomo la tupla y fabrico el traslado.
		tupla cuestion = traslados[ indice ] ;                               -> O(1), igual que el de abajo (muy larga la línea jejeje)
		Traslado añadir = new Traslado ( trasladoEnCuestion[0] , trasladoEnCuestion[1] , trasladoEnCuestion[2] , trasladoEnCuestion[3] , trasladoEnCuestion[4] ) ;
		
		// Lo enchufo en la lista de 'trasladosTotales' y luego, en cada uno de los heaps que involucran traslados.
		sistema.trasladosTotales.add( añadir ) ;                             -> O(1)
		
		sistema.trasladosPorGanancia.encolar( añadir ) ;                     -> O(log(T))
		sistema.trasladosPorAntiguedad.encolar( añadir ) ;                   -> O(log(T))
		
		index ++ ;                                                           -> O(1)
		
  }
```

**Complejidad Total** -> O(n log(T)) con n = |traslados|

> [!NOTE]
> El heap `mayorSuperavit` no se toca en este proc, ya que no se está despachando nada.
> Recordar que una ciudad modifica su ganancia y pérdida (y por ende su superavit) sólo cuando se despacha un traslado.

> [!IMPORTANT]
> **Corregir** porque `trasladosTotales` (a priori) no vamos a utilizarla mas como variable de estado.

### `despacharMasRedituables`

```
Proc despacharMasRedituables ( inout sistema: BestEffort , in n: int ) : seq<int> {

	arrayList<int> res = new ArrayList() ;                                       -> O( 1 )
	
	int index = 0 ;                                                              -> O( 1 )
	while ( index != n )                                                         -> Ciclo se ejecuta n veces: O(n (log (T) + log (C))) 
		
		Traslado despachado = sistema.trasladosPorGanancia.desencolar() ;          -> O(log (T))
		sistema.trasladosPorAntiguedad.eliminar( despachado[1] ) ;                 -> O(log (T)) 

		res.add(despachado.id) ;                                                   -> O(1)
		sistema.totalDespachados ++ ;                                              -> O(1)
		
		sistema.ciudadesTotales[ despachado.origen ].ganaciaNeta += despacho.ganaciaNeta ;   -> O(1)
		sistema.ciudadesTotales[ despacho.destino ].perdidaNeta += despacho.gananciaNeta ;   -> O(1)
		
		sistema.mayorSuperavit.reOrdenar() ;                                                 -> O(log (C))
		
		if ( sistema.ciudadesTotales[ despachado.destino ].perdidaNeta >=  sistema.ciudadesTotales[ sistema.mayorPerdida ] ).perdidaNeta )    -> O(1)
		sistema.mayorPerdida = sistema.ciudadesTotales[ despachado.destino ] ;                                                                -> O(1)
		
		if ( sistema.ciudadesTotales[ despachado.origen ].gananciaNeta >=  sistema.ciudadesTotales[ sistema.mayorGanancia ] ).gananciaNeta )  -> O(1)
		sistema.mayorGanancia = sistema.ciudadesTotales[ despachado.origen ] ;                                                                -> O(1)
		
		index ++ ;                                                                   -> O(1)
		
	return res ;                                                                   -> O(1)
}
```

**Complejidad Total** -> O(n (log (T) + log (C)))

> [!NOTE] 
> - `.eliminar(i)` de un heap -> este método toma el elemento de índice **i** (sabemos cuál es) , lo intercambia con el último (del array que modela el heap), mueve el puntero de `ultimoElemento` (del heap) y re-acomoda el heap.
> Básicamente, es lo mismo que el `desencolar()`, pero con un elemento del medio (no la raíz).
> - `reOrdenar()` es hacer `heapify`. En este algoritmo, el `heapify` se hace en función de `ciudad.superavit`.

**Fin**... por ahora.
