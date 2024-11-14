package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapCiudad {
    private ArrayList<Ciudad> heapPorSuperavit;
    private Comparator<Ciudad> comparator;
    private Ciudad[] arrayCiudad;

    public HeapCiudad(int cantCiudades) {
        comparator = Comparator.comparing(Ciudad :: superavit);
        comparator = comparator.thenComparing(Ciudad :: nombre);

        arrayCiudad = new Ciudad[cantCiudades];
        heapPorSuperavit = new ArrayList<>(cantCiudades);
        for(int i = 0; i < cantCiudades; i++) {
            Ciudad ciudadAAñadir = new Ciudad (i);
            this.arrayCiudad[i] = ciudadAAñadir;
            heapPorSuperavit.add(ciudadAAñadir);
        }
        //Nota, no hace falta ordenarlo pues todas tienen ganancias y perdidas de 0 al inicio y solo tras
        // despacharlas las tenemos que modificar
        // AHora solo están ordenadas por el id
    }

    public Ciudad mayorSuperavit() {

    }
}