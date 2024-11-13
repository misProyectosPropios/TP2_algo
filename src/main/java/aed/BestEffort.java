package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class BestEffort {

    private Heaps<Traslado> trasladosPorGanancia;
    private Heaps<Traslado> trasladosPorAntiguedad;
    private Heaps<Ciudad> mayorSuperavit;
    private Ciudad[] ciudadesTotales;
    private int totalDespachados;
    private int sumaDeGananciaDeDespachos;
    private ArrayList<Integer> mayorGanancia;
    private ArrayList<Integer> mayorPerdida;




    public BestEffort (int cantCiudades, Traslado[] traslados) {                                                                    
	    
        // Creo las variables auxiliares (que me van a ayudar a llenar los atributos).
        this.mayorPerdida = new ArrayList<Integer>() ;					           
	    
        this.mayorGanancia = new ArrayList<Integer>() ; 

        Ciudad[] mayorSuperavitAux = new Ciudad[cantCiudades] ;

        Traslado[] trasladosPorGananciaAux = new Traslado[traslados.length] ;

        Traslado[] trasladosPorAntiguedadAux = new Traslado[traslados.length] ;
        
        this.ciudadesTotales= new Ciudad[cantCiudades] ;                                          
	    
        // Llenamos las variables auxiliares y algunos atributos.
        int index = 0 ;
        while ( index < cantCiudades ) {

		    Ciudad añadirCiudad = new Ciudad (index) ; 
            mayorSuperavitAux[index] = añadirCiudad ;			           	
 		    this.ciudadesTotales[index] = añadirCiudad ;                 	                
		    this.mayorPerdida.add(añadirCiudad.nombre()) ;                              	
		    this.mayorGanancia.add(añadirCiudad.nombre()) ;                             	
		    index ++ ;

        } 
        
        index = 0 ;                                                                       
	    while ( index < traslados.length ) {

		    Traslado trasladoEnCuestion = traslados[index] ;                      	   	
		    Traslado añadirTranslados = new Traslado (trasladoEnCuestion) ;
		    trasladosPorGananciaAux[index] = añadirTranslados ;                         	
		    trasladosPorAntiguedadAux[index] = añadirTranslados ;	                   	
		    index ++ ;   
    
        }                                                              	

	    this.totalDespachados = 0 ;
        this.sumaDeGananciaDeDespachos = 0 ;

        // Transformamos a Heaps las variables auxiliares (definiendo los comparadores) que corresponden a los atributos Heaps.

        Comparator<Ciudad> comparador1 = Comparator.comparing (Ciudad :: superavit) ;
        comparador1 = comparador1.thenComparing (Ciudad :: nombre) ; 
        Heaps<Ciudad> mayorSuperavit = new Heaps<Ciudad> (comparador1 , mayorSuperavitAux) ;
        this.mayorSuperavit = mayorSuperavit ;

        Comparator<Traslado> comparador2 = Comparator.comparing (Traslado :: gananciaNeta) ;
        comparador2 = comparador2.thenComparing (Traslado :: id) ; 
        Heaps<Traslado> trasladosPorGanancia = new Heaps<Traslado> (comparador2 , trasladosPorGananciaAux) ;
        this.trasladosPorGanancia = trasladosPorGanancia ;

        Comparator<Traslado> comparador3 = Comparator.comparing (Traslado :: timestamp) ;
        comparador2 = comparador3.thenComparing (Traslado :: id) ; 
        Heaps<Traslado> trasladosPorAntiguedad = new Heaps<Traslado> (comparador3 , trasladosPorAntiguedadAux) ;
        this.trasladosPorAntiguedad = trasladosPorAntiguedad ;						   					   					   		

    }

    public void registrarTraslados(Traslado[] traslados){
        
        int index = 0 ;
        while (index < traslados.length) {

            // Fabrico el traslado.
            Traslado añadirTraslado = new Traslado (traslados[index]) ;

            // Lo enchufo en los Heaps.
            this.trasladosPorGanancia.encolar(añadirTraslado) ;
            this.trasladosPorAntiguedad.encolar(añadirTraslado) ;

            index ++ ;

        }
    }

    public int[] despacharMasRedituables(int n){
        // Implementar
        return null;
    }

    public int[] despacharMasAntiguos(int n){
        // Implementar
        return null;
    }

    public int ciudadConMayorSuperavit(){
        // Implementar
        return 0;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        // Implementar
        return null;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        // Implementar
        return null;
    }

    public int gananciaPromedioPorTraslado(){
        // Implementar
        return 0;
    }
    
}
