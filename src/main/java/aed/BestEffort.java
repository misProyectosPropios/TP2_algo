package aed;

import java.util.ArrayList;

public class BestEffort {
    Heaps trasladosPorGanancia;
    Heaps trasladosPorAntiguedad;
    Heaps mayorSuperavit;
    Ciudad[] ciudadesTotales;
    int totalDespachados;
    int sumaDeGananciaDeDespachos;
    ArrayList<Integer> mayorGanancia;
    ArrayList<Integer> mayorPerdida;

    public BestEffort (int cantCiudades, Traslado[] traslados){
        // Implementar
        //BestEffort res = new BestEffort () ;
        /*
        int index = 0 ;                                                                    
	    
        this.mayorPerdida = new ArrayList<Integer>();					           
	    
        this.mayorGanancia = new ArrayList<Integer>() ; 
        
        this.ciudadesTotales= new Ciudad[cantCiudades];                                          
	    
        while ( index < cantCiudades )    {                                                 
		    Ciudad añadirCiudad = new Ciudad (index) ; 
            //res.mayorSuperavit.add(añadirCiudad) ;			           	
 		    this.ciudadesTotales[index]= añadirCiudad;                 	                
		    this.mayorPerdida.add(añadirCiudad.nombre) ;                              	
		    this.mayorGanancia.add(añadirCiudad.nombre) ;                             	
		    index ++ ;                         
        } 
        
        index = 0 ;                                                                       
	    while ( index < traslados.length ){
		Traslado trasladoEnCuestion = traslados[ index ] ;                      	   	
		Traslado añadirTranslados = new Traslado ( trasladoEnCuestion.id , trasladoEnCuestion.origen , trasladoEnCuestion.destino , trasladoEnCuestion.gananciaNeta,trasladoEnCuestion.timestamp);
		//this.trasladosPorGanancia.add( añadirTranslados ) ;                         	
		//this.trasladosPorAntiguedad.add( añadirTranslados ) ;	                   	
		index ++ ;   }                                                              	

	    this.totalDespachados = 0 ;						           

	    //this.mayorSuperavit.FloydAlgorithm();						   
	    //this.trasladosPorGanancia.FloydAlgorithm() ;					   
	    //this.trasladosPorAntiguedad.FloydAlgorithm();					   		
*/
    }

    public void registrarTraslados(Traslado[] traslados){
        // Implementar
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
