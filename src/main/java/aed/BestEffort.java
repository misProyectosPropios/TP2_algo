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
            Traslado[] despachados = {new Traslado(despachado)}; //O (1)

            mayorSuperavit.despacharTraslados(despachados);

            res[index] = despachado.id();
        }

        //Modificar el heapDeSuperativ



        //Modificar el mayorPerdida

        //Modificar el mayorGanancia

        //Modificar cantDeTraslados realizados asi como su ganancia

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
        }

        //Modificar el heapDeSuperativ

        //Modificar el mayorPerdida


        //Modificar el mayorGanancia

        //Modificar cantDeTraslados realizados asi como su ganancia
        return res;
    }

    public int ciudadConMayorSuperavit(){
        return mayorSuperavit.mayorSuperavit().nombre();
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return this.mayorGanancia;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        // Implementar
        return this.mayorPerdida;
    }

    public int gananciaPromedioPorTraslado(){
        // Implementar
        return this.sumaDeGananciaDeDespachos / this.totalDespachados;
    }
    
}
