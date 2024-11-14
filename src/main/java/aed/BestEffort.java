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

        for(Traslado traslado : traslados) {
            this.traslados.encolar(traslado);
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
