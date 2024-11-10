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


    public BestEffort nuevoSistema(int cantCiudades, Traslado[] traslados){
        // Implementar
        BestEffort res = new BestEffort () ;
        
        int index = 0 ;                                                                    
	    
        res.mayorPerdida = new ArrayList<Integer>();					           
	    
        res.mayorGanancia = new ArrayList<Integer>() ; 
        
        this.ciudadesTotales= new Ciudad[cantCiudades];                                          
	    
        while ( index < cantCiudades )    {                                                 
		    Ciudad añadirCiudad = new Ciudad (index) ; 
            res.mayorSuperavit.add(añadirCiudad) ;			           	
 		    res.ciudadesTotales[index]= añadirCiudad;                 	                
		    res.mayorPerdida.add(añadirCiudad.nombre) ;                              	
		    res.mayorGanancia.add(añadirCiudad.nombre) ;                             	
		    index ++ ;                         
        } 
        
        index = 0 ;                                                                       
	    while ( index < traslados.length ){
		Traslado trasladoEnCuestion = traslados[ index ] ;                      	   	
		Traslado añadirTranslados = new Traslado ( trasladoEnCuestion.id , trasladoEnCuestion.origen , trasladoEnCuestion.destino , trasladoEnCuestion.gananciaNeta,trasladoEnCuestion.timestamp);
		res.trasladosPorGanancia.add( añadirTranslados ) ;                         	
		res.trasladosPorAntiguedad.add( añadirTranslados ) ;	                   	
		index ++ ;   }                                                              	

	    res.totalDespachados = 0 ;						           

	    res.mayorSuperavit.FloydAlgorithm();						   
	    res.trasladosPorGanancia.FloydAlgorithm() ;					   
	    res.trasladosPorAntiguedad.FloydAlgorithm();					   
        return res;				

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
