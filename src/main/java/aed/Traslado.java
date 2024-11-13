package aed;

public class Traslado {
    
    private int id ;
    private int origen ;
    private int destino ;
    private int gananciaNeta ;
    private int timestamp ;
    private int indiceAHeapGanancia ;
    private int indiceAHeapAntiguedad ;

    // Constructor.
    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp) {

        this.id = id ;
        this.origen = origen ;
        this.destino = destino ;
        this.gananciaNeta = gananciaNeta ;
        this.timestamp = timestamp ;
        
    } 

    // Constructor por copia.
    public Traslado(Traslado copiado) {

        this.id = copiado.id ;
        this.origen = copiado.origen ;
        this.destino = copiado.destino ;
        this.gananciaNeta = copiado.gananciaNeta ;
        this.timestamp = copiado.timestamp ;
        this.indiceAHeapGanancia = copiado.indiceAHeapGanancia ;
        this.indiceAHeapAntiguedad = copiado.indiceAHeapAntiguedad ;
        
    } 

    public int id() {

        return this.id ;
        
    }

    public int origen() {

        return this.origen ;

    }

    public int destino() {

        return this.destino ;

    }

    public int gananciaNeta() { 
        
        return this.gananciaNeta ;

    }

    public int timestamp() {
        
        return this.timestamp ; 
    
    }

    public int indiceAHeapGanancia() {

        return this.indiceAHeapGanancia ;

    }

    public int indexAHeapAntiguedad() {

        return this.indiceAHeapAntiguedad ;

    }

}
