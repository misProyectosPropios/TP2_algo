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
        mayorSuperavit = new HeapCiudad(cantCiudades);

        //Evitar aliasing con traslados
        Traslado[] arrayCopiaTraslados = new Traslado[traslados.length];

        for(int i = 0; i < arrayCopiaTraslados.length; i++) {
            arrayCopiaTraslados[i] = new Traslado(traslados[i]);
        }

        mayorGanancia = new ArrayList<>(cantCiudades);
        mayorPerdida = new ArrayList<>(cantCiudades);

        for(int i = 0; i < cantCiudades; i++) {
            mayorGanancia.add(i);
            mayorPerdida.add(i);
        }

        this.traslados = new HeapsTraslado(traslados);
    }

    // O(|traslados| log(|T |))
    public void registrarTraslados(Traslado[] traslados){
        //Evitamso el aliasing
        Traslado[] arrayCopiaTraslados = new Traslado[traslados.length];
        for(int i = 0; i < arrayCopiaTraslados.length; i++) {
            arrayCopiaTraslados[i] = new Traslado(traslados[i]);
        }

        for(Traslado traslado : arrayCopiaTraslados) {
            this.traslados.encolar(traslado);
        }
    }

    // O(n (log(|T |) + log(|C|)))
    public int[] despacharMasRedituables(int n){
        int[] res;
        if (n > traslados.length()) {
            res = new int[traslados.length()];
        } else {
            res = new int[n];
        }

        int index = 0;
        while (n > 0 && traslados.length() > 0) {

            Traslado despachado = traslados.desencolarPorGanancia();
            res[index] = despachado.id();
            int gananciaMaxima = mayorSuperavit.getGanancia(mayorGanancia.get(0));
            int perdidaMaxima = mayorSuperavit.getPerdida(mayorPerdida.get(0));

            sumaDeGananciaDeDespachos += despachado.gananciaNeta();
            totalDespachados++;

            mayorSuperavit.despacharTraslados(despachado);

            //Modifico el de mayorGanancia si cambia
            int otraGanancia = mayorSuperavit.getGanancia(despachado.obtenerCiudadOrigen());
            if (otraGanancia > gananciaMaxima) {
                mayorGanancia = new ArrayList<>(1);
                mayorGanancia.add(despachado.obtenerCiudadOrigen());
            }
            else if (otraGanancia == gananciaMaxima) {
                mayorGanancia.add(despachado.obtenerCiudadOrigen());
            }

            int otraPerdida = mayorSuperavit.getPerdida(despachado.obtenerCiudadDestino());

            if (otraPerdida > perdidaMaxima) {
                mayorPerdida = new ArrayList<>(1);
                mayorPerdida.add(despachado.obtenerCiudadDestino());
            }
            else if (otraPerdida == perdidaMaxima) {
                mayorPerdida.add(despachado.obtenerCiudadDestino());
            }
            n--;
            index++;
        }
        return res;
    }

    // O(n (log(|T |) + log(|C|)))
    public int[] despacharMasAntiguos(int n){
        // Implementar
        int[] res;
        if (n > traslados.length()) {
            res = new int[traslados.length()];
        } else {
            res = new int[n];
        }

        int index = 0;
        while (n > 0 && traslados.length() > 0) {
            Traslado despachado = traslados.desencolarPorTiempo();
            res[index] = despachado.id();
            int gananciaMaxima = mayorSuperavit.getGanancia(mayorGanancia.get(0));
            int perdidaMaxima = mayorSuperavit.getPerdida(mayorPerdida.get(0));
            sumaDeGananciaDeDespachos += despachado.gananciaNeta();
            totalDespachados++;

            mayorSuperavit.despacharTraslados(despachado);

            //Modifico el de mayorGanancia si cambia
            int otraGanancia = mayorSuperavit.getGanancia(despachado.obtenerCiudadOrigen());

            if (otraGanancia > gananciaMaxima) {
                mayorGanancia = new ArrayList<>(1);
                mayorGanancia.add(despachado.obtenerCiudadOrigen());
            }
            else if (otraGanancia == gananciaMaxima) {
                mayorGanancia.add(despachado.obtenerCiudadOrigen());
            }

            int otraPerdida = mayorSuperavit.getPerdida(despachado.obtenerCiudadDestino());

            if (otraPerdida > perdidaMaxima) {
                mayorPerdida = new ArrayList<>(1);
                mayorPerdida.add(despachado.obtenerCiudadDestino());
            }
            else if (otraPerdida == perdidaMaxima) {
                mayorPerdida.add(despachado.obtenerCiudadDestino());
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

    public int gananciaPromedioPorTraslado(){
        return this.sumaDeGananciaDeDespachos / this.totalDespachados;              // O(1)
    }

}