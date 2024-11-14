package aed;

import java.util.ArrayList;

public class BestEffort {
    HeapsTraslado traslados;
    HeapCiudad mayorSuperavit;
    int totalDespachados = 0;
    int sumaDeGananciaDeDespachos = 0;
    ArrayList<Integer> mayorGanancia;
    ArrayList<Integer> mayorPerdida;

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

    public void registrarTraslados(Traslado[] traslados){
        // Implementar
        //Evitamso el aliasing
        Traslado[] arrayCopiaTraslados = new Traslado[traslados.length];
        for(int i = 0; i < arrayCopiaTraslados.length; i++) {
            arrayCopiaTraslados[i] = new Traslado(traslados[i]);
        }

        for(Traslado traslado : arrayCopiaTraslados) {
            this.traslados.encolar(traslado);
        }
    }

    public int[] despacharMasRedituables(int n){
        // Implementar
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
            Traslado[] despachados = {new Traslado(despachado)}; //O (1)


            sumaDeGananciaDeDespachos = despachado.gananciaNeta();
            totalDespachados++;

            mayorSuperavit.despacharTraslados(despachados);

            //Modifico el de mayorGanancia si cambia
            int otraGanancia = mayorSuperavit.getGanancia(despachado.obtenerCiudadOrigen());
            int gananciaMaxima = mayorSuperavit.getGanancia(mayorGanancia.get(0));
            if (otraGanancia > gananciaMaxima) {
                mayorGanancia = new ArrayList<>(1);
                mayorGanancia.add(despachado.obtenerCiudadOrigen());
            }
            else if (otraGanancia == gananciaMaxima) {
                mayorGanancia.add(despachado.obtenerCiudadOrigen());
            }

            int otraPerdida = mayorSuperavit.getPerdida(despachado.obtenerCiudadDestino());
            int perdidaMaxima = mayorSuperavit.getGanancia(mayorPerdida.get(0));
            if (otraPerdida > perdidaMaxima) {
                mayorPerdida = new ArrayList<>(1);
                mayorGanancia.add(despachado.obtenerCiudadDestino());
            }
            else if (otraPerdida == perdidaMaxima) {
                mayorGanancia.add(despachado.obtenerCiudadDestino());
            }
            n--;
            index++;
        }
        return res;
    }

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
            Traslado[] despachados = {new Traslado(despachado)}; //O (1)

            sumaDeGananciaDeDespachos = despachado.gananciaNeta();
            totalDespachados++;

            mayorSuperavit.despacharTraslados(despachados);

            //Modifico el de mayorGanancia si cambia
            int otraGanancia = mayorSuperavit.getGanancia(despachado.obtenerCiudadOrigen());
            int gananciaMaxima = mayorSuperavit.getGanancia(mayorGanancia.get(0));
            if (otraGanancia > gananciaMaxima) {
                mayorGanancia = new ArrayList<>(1);
                mayorGanancia.add(despachado.obtenerCiudadOrigen());
            }
            else if (otraGanancia == gananciaMaxima) {
                mayorGanancia.add(despachado.obtenerCiudadOrigen());
            }

            int otraPerdida = mayorSuperavit.getPerdida(despachado.obtenerCiudadDestino());
            int perdidaMaxima = mayorSuperavit.getGanancia(mayorPerdida.get(0));
            if (otraPerdida > perdidaMaxima) {
                mayorPerdida = new ArrayList<>(1);
                mayorGanancia.add(despachado.obtenerCiudadDestino());
            }
            else if (otraPerdida == perdidaMaxima) {
                mayorGanancia.add(despachado.obtenerCiudadDestino());
            }
            index++;
            n--;
        }

        return res;
    }

    public int ciudadConMayorSuperavit(){
        return mayorSuperavit.mayorSuperavit().nombre();
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return this.mayorGanancia;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        return this.mayorPerdida;
    }

    public int gananciaPromedioPorTraslado(){
        return this.sumaDeGananciaDeDespachos / this.totalDespachados;
    }
    
}
