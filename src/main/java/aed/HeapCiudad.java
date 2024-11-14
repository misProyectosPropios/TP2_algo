package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapCiudad {
    private ArrayList<Ciudad> heapPorSuperavit;
    private Comparator<Ciudad> comparator;
    private Ciudad[] arrayCiudad;
    private int cantCiudades;

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
        return this.heapPorSuperavit.get(0);
    }

    public void despacharTraslados(Traslado[] traslados) {
        //Implementar
        //Debe modificar tanto al que compra como al que vende
        for(Traslado traslado : traslados) {
            int posicionOrigenEnHeap = traslado.obtenerCiudadOrigen();
            posicionOrigenEnHeap = arrayCiudad[posicionOrigenEnHeap].getPosicionEnHeap();

            int posicionDestinoEnHeap = traslado.obtenerCiudadDestino();
            posicionDestinoEnHeap = arrayCiudad[posicionDestinoEnHeap].getPosicionEnHeap();

            arrayCiudad[traslado.obtenerCiudadOrigen()].aumentarGanancia(traslado.gananciaNeta());
            modificar(posicionOrigenEnHeap);

            arrayCiudad[traslado.obtenerCiudadDestino()].aumnetarPerdida(traslado.gananciaNeta());
            modificar(posicionDestinoEnHeap);
        }
    }

    public int getGanancia(int index) {
        return this.arrayCiudad[index].gananciaNeta();
    }

    public int getPerdida(int index) {
        return this.arrayCiudad[index].perdidaNeta();
    }

    private void modificar(int index) {
        //M
        //  this.heap.set(index, newValue);
        this.mover(index);
    }

    private void swap(int position1, int position2) {
        Ciudad guardarCiudad1 = this.heapPorSuperavit.get(position1);
        Ciudad guardarCiudad2 = this.heapPorSuperavit.get(position2);

        //Vamso con los handles
        guardarCiudad1.setPosicionEnHeap(position2);
        guardarCiudad2.setPosicionEnHeap(position1);

        //Los intercambiosamos
        this.heapPorSuperavit.set(position1, this.heapPorSuperavit.get(position2));
        this.heapPorSuperavit.set(position2, guardarCiudad1);
    }

    private void mover(int index) {
        if (index == 0) {
            bajarSuperavit(index);
        } else if (prioridadDeAlgunHijoEsMayorSuperavit(index)) {
            bajarSuperavit(index);
        } else {
            subirElementoSuperavit(index);
        }
    }

    private void bajarSuperavit(int index) {
        while (!this.esHoja(index) && this.prioridadDeAlgunHijoEsMayorSuperavit(index)){
            if (tieneHijoIzquierdo(index) && !tieneHijoDerecho(index)) {
                swap(index, calcularPosicionHijoIzquierdo(index));
                index =calcularPosicionHijoIzquierdo (index);
            } else if (comparator.compare(this.heapPorSuperavit.get(calcularPosicionHijoIzquierdo(index)), this.heapPorSuperavit.get(calcularPosicionHijoDerecho(index))) > 0) {
                swap(index, calcularPosicionHijoIzquierdo(index));
                index = calcularPosicionHijoIzquierdo(index);
            } else {
                swap(index, calcularPosicionHijoDerecho(index));
                index = calcularPosicionHijoDerecho(index);
            }
        }
    }

    private void subirElementoSuperavit(int index) {
        int positionParent = calcularPosicionPadre(index);
        while (index != 0 && prioridadMayorQuePadrePorSuperavit(index)) {
            swap(index, positionParent);
            index = positionParent;
            positionParent = calcularPosicionPadre(index);
        }
    }

    private boolean prioridadMayorQuePadrePorSuperavit(int index) {
        int positionPadre = calcularPosicionPadre(index);
        return comparator.compare(this.heapPorSuperavit.get(index), this.heapPorSuperavit.get(positionPadre)) > 0;
    }

    private boolean prioridadDeAlgunHijoEsMayorSuperavit(int index) {
        boolean res = false;

        if (tieneHijoDerecho(index)){
            Ciudad valueRightChild = heapPorSuperavit.get(calcularPosicionHijoDerecho(index));
            Ciudad valuePosition = heapPorSuperavit.get(index);
            if (comparator.compare(valueRightChild, valuePosition) > 0) {
                res = true;
            }
        }
        if (tieneHijoIzquierdo(index)) {
            Ciudad valueLeftChild = heapPorSuperavit.get(calcularPosicionHijoIzquierdo(index));
            Ciudad valuePosition = heapPorSuperavit.get(index);
            if (comparator.compare(valueLeftChild, valuePosition) > 0) {
                res = true;
            }
        }
        return true;
    }

    private boolean esHoja(int indice) {
        return indice * 2 + 1 >= this.arrayCiudad.length;
    }

    private boolean tieneHijoIzquierdo(int indice) {
        return indice >= 0 && indice * 2 + 1 < this.arrayCiudad.length;
    }

    private boolean tieneHijoDerecho(int indice) {
        return indice >= 0 && indice * 2 + 2 < this.arrayCiudad.length;
    }

    // Metodos estáticos
    private static int calcularPosicionPadre(int position) {
        int positionParent;
        if (position % 2 == 0) {
            positionParent = (position - 2) / 2;
        } else {
            positionParent = (position - 1) / 2;
        }
        return positionParent;
    }

    private static int calcularPosicionHijoIzquierdo(int position) {
        return position * 2 + 1;
    }

    private static int calcularPosicionHijoDerecho(int position) {
        return position * 2 + 2;
    }


}