package aed;

import java.util.ArrayList;

public class BestEffort {
    HeapsTraslado traslados;
    HeapCiudad mayorSuperavit;
    int totalDespachados = 0;
    int sumaDeGananciaDeDespachos = 0;
    ArrayList<Integer> mayorGanancia;
    ArrayList<Integer> mayorPerdida;


    // O(|C| + |T |)
    public BestEffort (int cantCiudades, Traslado[] traslados){
        mayorSuperavit = new HeapCiudad(cantCiudades);                                  // O (|C|)

        //Evitar aliasing con traslados
        Traslado[] arrayCopiaTraslados = new Traslado[traslados.length];                // O(1)

        for(int i = 0; i < arrayCopiaTraslados.length; i++) {                           // |Traslados| iteraciones
            arrayCopiaTraslados[i] = new Traslado(traslados[i]);                        // O(1)
        }
        //                                                                              Este ciclo pertenece a O(|Traslado|)
        
        mayorGanancia = new ArrayList<>(cantCiudades);                                  // O(1)                 
        mayorPerdida = new ArrayList<>(cantCiudades);                                   // O(1)

        for(int i = 0; i < cantCiudades; i++) {                                         // O(|Ciudades|)
            mayorGanancia.add(i);                                                       // O(1)
            mayorPerdida.add(i);                                                        // O(1)
        }   
        //                                                                               Este ciclo pertenece a O(|Ciudades|)      
        this.traslados = new HeapsTraslado(traslados);                                  // O(|Traslados|)
    }

    // O(|traslados| log(|T |))
    public void registrarTraslados(Traslado[] traslados){
        //Evitamso el aliasing
        Traslado[] arrayCopiaTraslados = new Traslado[traslados.length];            // O(1)
        for(int i = 0; i < arrayCopiaTraslados.length; i++) {                       // |T| iteraciones
            arrayCopiaTraslados[i] = new Traslado(traslados[i]);                    // O(1)
        }   
        //                                                                         Este ciclo pertenece a O(|T|)

        for(Traslado traslado : arrayCopiaTraslados) {                              // |T| iteraciones
            this.traslados.encolar(traslado);                                       // O(1)
        }
        //                                                                         Este ciclo pertenece a O(|T|)
    }

    // O(n (log(|T |) + log(|C|)))
    public int[] despacharMasRedituables(int n){
        int[] res;                                                                  // O(1)
        if (n > traslados.length()) {                                               // O(1)
            res = new int[traslados.length()];                                      // O(1)
        } else {    
            res = new int[n];                                                       // O(1)
        }
        // O(max(O(1), O(1))) = O(1)

        int index = 0;                                                              // O(1)
        while (n > 0 && traslados.length() > 0) {                                   // O(n)

            Traslado despachado = traslados.desencolarPorGanancia();                // O(log |T|)
            res[index] = despachado.id();                                           // O(1)
            int gananciaMaxima = mayorSuperavit.getGanancia(mayorGanancia.get(0));  // O(1)
            int perdidaMaxima = mayorSuperavit.getPerdida(mayorPerdida.get(0));     // O(1)

            sumaDeGananciaDeDespachos += despachado.gananciaNeta();                 // O(1)
            totalDespachados++;                                                     // O(1)

            mayorSuperavit.despacharTraslados(despachado);                          // O(1)

            //Modifico el de mayorGanancia si cambia
            int otraGanancia = mayorSuperavit.getGanancia(despachado.obtenerCiudadOrigen());  // O(1)
            if (otraGanancia > gananciaMaxima) {                                              // O(1)
                mayorGanancia = new ArrayList<>(1);                                           // O(1)
                mayorGanancia.add(despachado.obtenerCiudadOrigen());                          // O(1)
            }
            else if (otraGanancia == gananciaMaxima) {                                        // O(1)
                mayorGanancia.add(despachado.obtenerCiudadOrigen());                          // O(1)
            }
            // O(max(O(1), O(1), O(1))) = O(1)

            int otraPerdida = mayorSuperavit.getPerdida(despachado.obtenerCiudadDestino());   // O(1)

            if (otraPerdida > perdidaMaxima) {                                                // O(1)
                mayorPerdida = new ArrayList<>(1);                                            // O(1)
                mayorPerdida.add(despachado.obtenerCiudadDestino());                          // O(1)
            }
            else if (otraPerdida == perdidaMaxima) {                                          // O(1)
                mayorPerdida.add(despachado.obtenerCiudadDestino());                          // O(1)
            }
            // O(max(O(1),O(1))) = O(1)
            n--;                                                                              // O(1)
            index++;                                                                          // O(1)
        }
        return res;                                                                           // O(1)
    }

    // O(n (log(|T |) + log(|C|)))
    //Este codigo es igual al otro, solo que utilizamos otras variables
    public int[] despacharMasAntiguos(int n){
        int[] res;                                                                          // O(1)
        if (n > traslados.length()) {                                                       // O(1)
            res = new int[traslados.length()];                                              // O(1)
        } else {
            res = new int[n];                                                               // O(1)
        }

        int index = 0;
        while (n > 0 && traslados.length() > 0) {
            Traslado despachado = traslados.desencolarPorTiempo();                                  // O(log n)
            res[index] = despachado.id();                                                           // O(1)
            int gananciaMaxima = mayorSuperavit.getGanancia(mayorGanancia.get(0));                  // O(1)
            int perdidaMaxima = mayorSuperavit.getPerdida(mayorPerdida.get(0));                     // O(1)
            sumaDeGananciaDeDespachos += despachado.gananciaNeta();                                 // O(1)
            totalDespachados++;                                                                     // O(1)

            mayorSuperavit.despacharTraslados(despachado);                                          // O(log n)

            //Modifico el de mayorGanancia si cambia
            int otraGanancia = mayorSuperavit.getGanancia(despachado.obtenerCiudadOrigen());        // O(1)

            if (otraGanancia > gananciaMaxima) {                                                    // O(1)
                mayorGanancia = new ArrayList<>(1);                                                 // O(1)
                mayorGanancia.add(despachado.obtenerCiudadOrigen());                                // O(1) amortizado
            }
            else if (otraGanancia == gananciaMaxima) {                                              // O(1)
                mayorGanancia.add(despachado.obtenerCiudadOrigen());                                // O(1) amortizado
            }

            int otraPerdida = mayorSuperavit.getPerdida(despachado.obtenerCiudadDestino());         // O(1)

            if (otraPerdida > perdidaMaxima) {                                                      // O(1)
                mayorPerdida = new ArrayList<>(1);                                                  // O(1)
                mayorPerdida.add(despachado.obtenerCiudadDestino());                                // O(1) amortizado
            }
            else if (otraPerdida == perdidaMaxima) {
                mayorPerdida.add(despachado.obtenerCiudadDestino());                                // O(1) amortizado
            }
            index++;                                                                                 // O(1)
            n--;                                                                                     // O(1)
        }
        return res;                                                                                  // O(1)
    }

    // O(1)
    public int ciudadConMayorSuperavit(){
        return mayorSuperavit.mayorSuperavit().nombre();                            // O(1)
    }

    // O(1)
    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return this.mayorGanancia;                                                  // O(1)
    }

    // O(1)
    public ArrayList<Integer> ciudadesConMayorPerdida(){
        return this.mayorPerdida;                                                   // O(1)
    }

    // O(1)s
    public int gananciaPromedioPorTraslado(){
        return this.sumaDeGananciaDeDespachos / this.totalDespachados;              // O(1)
    }

}