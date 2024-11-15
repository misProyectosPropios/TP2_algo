package aed;
 
// Todos los Procs de esta clase cuestan O(1) (son todas asignaciones y operaciones 
// aritméticas básicas, sin ningún ciclo).

public class Traslado {
    
    private int id ;
    private int origen ;
    private int destino ;
    private int gananciaNeta ;
    private int timestamp ;
    private int indiceAHeapGanancia ;
    private int indiceAHeapAntiguedad ;

    // Constructor
    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp) {
        this.id = id ;
        this.origen = origen ;
        this.destino = destino ;
        this.gananciaNeta = gananciaNeta ;
        this.timestamp = timestamp ;
    } 

    // Constructor por copia
    public Traslado(Traslado copiado) {
        this.id = copiado.id ;
        this.origen = copiado.origen ;
        this.destino = copiado.destino ;
        this.gananciaNeta = copiado.gananciaNeta ;
        this.timestamp = copiado.timestamp ;
    } 

    public int id() {
        return this.id ;
    }

    public int obtenerCiudadOrigen() {
        return this.origen ;
    }

    public int obtenerCiudadDestino() {
        return this.destino ;
    }

    public int gananciaNeta() { 
        return this.gananciaNeta ;
    }

    public int timestamp() {
        return this.timestamp ; 
    }

    public int obtenerIndiceAHeapGanancia() {
        return this.indiceAHeapGanancia ;
    }

    public int obtenerIndexAHeapAntiguedad() {
        return this.indiceAHeapAntiguedad ;
    }

    public void setIndiceAHeapAntiguedad(int index) {
        this.indiceAHeapAntiguedad = index ;
    }

    public void setIndiceAHeapGanancia(int index) {
        this.indiceAHeapGanancia = index ;
    }

}