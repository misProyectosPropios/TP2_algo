package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapsTraslado {
    private ArrayList<Traslado> heapPorGanancia;
    private ArrayList<Traslado> heapPorTiempo;
    private Comparator<Traslado> comparadorPorGanancia;
    private Comparator<Traslado> comparadorPorTiempo;

    public HeapsTraslado() {
        //Inicializando los arrays
        heapPorGanancia = new ArrayList<>(0);
        heapPorTiempo = new ArrayList<>(0);

        //Craaciòn del comparador por ganancia
        comparadorPorGanancia = Comparator.comparing(Traslado::gananciaNeta);
        comparadorPorGanancia = comparadorPorGanancia.thenComparing(Traslado::id);

        //Craaciòn del comparador por tiempo
        comparadorPorTiempo = Comparator.comparing(Traslado::timestamp);
        comparadorPorTiempo = comparadorPorTiempo.reversed();
        comparadorPorTiempo = comparadorPorTiempo.thenComparing(Traslado::id);
    }

    public HeapsTraslado(Traslado[] array) {
        heapPorGanancia = new ArrayList<>(array.length);
        heapPorTiempo = new ArrayList<>(array.length);

        //Agrego los elementos al array de heapPorGanancia y heapPorTiempo para despuès ordenarlo
        for (int i = 0; i < array.length; i++) {
            array[i].setIndiceAHeapAntiguedad(i);
            array[i].setIndiceAHeapGanancia(i);
            this.heapPorGanancia.add(array[i]);
            this.heapPorTiempo.add(array[i]);
        }

        //Craaciòn del comparador por ganancia
        comparadorPorGanancia = Comparator.comparing(Traslado::gananciaNeta);
        comparadorPorGanancia = comparadorPorGanancia.thenComparing(Traslado::id);

        //Craaciòn del comparador por tiempo
        comparadorPorTiempo = Comparator.comparing(Traslado::timestamp);
        comparadorPorTiempo = comparadorPorTiempo.reversed();
        comparadorPorTiempo = comparadorPorTiempo.thenComparing(Traslado::id);

        //Ordenarlos usando el heapify
        floydGanancia();
        floydTiempo();
    }

    private void floydGanancia() {
        for (int i = this.length() / 2 + 1; i >= 0; i--) {
            this.bajarGanancia(i);
        }
    }

    private void floydTiempo() {
        for (int i = this.length() / 2 + 1; i >= 0; i--) {
            this.bajarTiempo(i);
        }
    }

    public Traslado proximoPorGanancia() {
        if (this.heapPorGanancia.size() > 0) {
            return this.heapPorGanancia.get(0);
        }
        return null;
    }

    public Traslado proximoPorTiempo() {
        if (this.heapPorTiempo.size() > 0) {
            return this.heapPorTiempo.get(0);
        }
        return null;
    }

    public void encolar(Traslado element) {
        //Needs to be implemented
        //Adds element to the last position
        this.heapPorGanancia.add(element);
        this.heapPorTiempo.add(element);

        //Ordenarlo ahora
        this.subirElementoGanancia(this.heapPorGanancia.size() - 1);
        this.subirElementoTiempo(this.heapPorGanancia.size() - 1);
    }


    public void modificar(int index, Traslado newValue) {
        //  this.heap.set(index, newValue);
        //  this.mover(index);
    }

    public Traslado desencolarPorGanancia() {
        Traslado returnValue = this.heapPorGanancia.get(0); //Obtenemos el Objecto a devovler O(1)
        this.eliminarGanancia(0);
        return returnValue;
    }

    public Traslado desencolarPorTiempo() {
        Traslado returnValue = this.heapPorTiempo.get(0); //Obtenemos el Objecto a devovler O(1)
        this.eliminarTiempo(0);
        return returnValue;
    }

    public int length() {
        return this.heapPorGanancia.size(); // Tendrían los mismos elementos el de tiempo y ganancia
    }

    private void subirElementoGanancia(int posicion) {
        int positionParent = calcularPosicionPadre(posicion);
        while (posicion != 0 && prioridadMayorQuePadrePorGanancia(posicion)) {
            swapGanancia(posicion, positionParent);
            posicion = positionParent;
            positionParent = calcularPosicionPadre(posicion);
        }
    }

    private boolean prioridadMayorQuePadrePorGanancia(int positionElement) {
        //Si el elemento es menor que el parent, debe de devolver numero negativo
        int positionPadre = calcularPosicionPadre(positionElement);
        return comparadorPorGanancia.compare(this.heapPorGanancia.get(positionElement), this.heapPorGanancia.get(positionPadre)) > 0;
    }

    private void swapGanancia(int position1, int position2) {
        //Falta adaptar los handles todavia del heap por tiempo
        Traslado guardarPosicion1 = this.heapPorGanancia.get(position1);
        Traslado guardarPosicion2 = this.heapPorGanancia.get(position2);

        //Ponemos sus nuevas posiciones
        guardarPosicion1.setIndiceAHeapGanancia(position2);
        guardarPosicion2.setIndiceAHeapGanancia(position1);

        this.heapPorGanancia.set(position1, this.heapPorGanancia.get(position2));
        this.heapPorGanancia.set(position2, guardarPosicion1);
    }

    private void swapTiempo(int position1, int position2) {
        //Falta adaptar los handles todavia del heap por tiempo
        Traslado guardarPosicion1 = this.heapPorTiempo.get(position1);
        Traslado guardarPosicion2 = this.heapPorTiempo.get(position2);

        //Ponemos sus nuevas posiciones
        guardarPosicion1.setIndiceAHeapAntiguedad(position2);
        guardarPosicion2.setIndiceAHeapAntiguedad(position1);


        this.heapPorTiempo.set(position1, this.heapPorTiempo.get(position2));
        this.heapPorTiempo.set(position2, guardarPosicion1);
    }

    private void subirElementoTiempo(int posicion) {
        int positionParent = calcularPosicionPadre(posicion);
        while (posicion != 0 && prioridadMayorQuePadrePorTiempo(posicion)) {
            swapTiempo(positionParent, positionParent);
            posicion = positionParent;
            positionParent = calcularPosicionPadre(posicion);
        }
    }

    private boolean prioridadMayorQuePadrePorTiempo(int positionElement) {
        //Si el elemento es menor que el parent, debe de devolver numero negativo
        int positionPadre = calcularPosicionPadre(positionElement);
        return comparadorPorTiempo.compare(this.heapPorTiempo.get(positionElement), this.heapPorTiempo.get(positionPadre)) > 0;
    }


    private Traslado eliminarGanancia(int index) {
        Traslado returnValue = this.heapPorGanancia.get(index);
        int indexTiempo = returnValue.obtenerIndexAHeapAntiguedad();
        //Forzamos a que ambos estén en la ultima posición para evitar perdida de información
        swapGanancia(index, this.length() - 1);
        swapTiempo(indexTiempo, this.length() - 1);

        //Borramos los valores
        this.heapPorGanancia.remove(this.heapPorGanancia.size() - 1);
        this.heapPorTiempo.remove(this.heapPorTiempo.size() - 1);

        //Ajustamos los heaps
        if (index >= length() && indexTiempo >= length()) {
            return returnValue;
        }
        if (index >= length()) {
            this.moverTiempo(indexTiempo);
        } else if (indexTiempo >= length()) {
            this.moverGanancia(index);
        } else {
            this.moverGanancia(index);
            this.moverTiempo(indexTiempo);
        }
        return returnValue;
    }

    private Traslado eliminarTiempo(int index) {
        //Implementar
        Traslado returnValue = this.heapPorTiempo.get(index);
        int indexGanancia = returnValue.obtenerIndiceAHeapGanancia();

        //Forzamos a que ambos sean 0
        swapTiempo(index, this.length() - 1);
        swapGanancia(indexGanancia, this.length() - 1);

        //Borramos los valores
        this.heapPorGanancia.remove(this.heapPorGanancia.size() - 1);
        this.heapPorTiempo.remove(this.heapPorTiempo.size() - 1);

        if (index >= length() && indexGanancia >= length()) {
            return returnValue;
        }

        //Ajustamos los heaps
        if (index >= length()) {
            this.moverGanancia(indexGanancia);
        } else if (indexGanancia >= length()) {
            this.moverTiempo(index);
        } else {
            this.moverGanancia(indexGanancia);
            this.moverTiempo(index);
        }
        return returnValue;
    }

    private void moverGanancia(int index) {
        if (index == 0) {
            bajarGanancia(index);
        } else if (prioridadDeAlgunHijoEsMayorGanancia(index)) {
            bajarGanancia(index);
        } else {
            subirElementoGanancia(index);
        }
    }

    private void moverTiempo(int index) {
        if (index == 0) {
            bajarTiempo(index);
        } else if (prioridadDeAlgunHijoEsMayorTiempo(index)) {
            bajarTiempo(index);
        } else {
            subirElementoTiempo(index);
        }
    }

    private void bajarGanancia(int index) {
        while (!this.esHoja(index) && this.prioridadDeAlgunHijoEsMayorGanancia(index)) {
            if (tieneHijoIzquierdo(index) && !tieneHijoDerecho(index)) {
                swapGanancia(index, calcularPosicionHijoIzquierdo(index));
                index =calcularPosicionHijoIzquierdo (index);
            } else if (comparadorPorGanancia.compare(this.heapPorGanancia.get(calcularPosicionHijoIzquierdo(index)), this.heapPorGanancia.get(calcularPosicionHijoDerecho(index))) > 0) {
                swapGanancia(index, calcularPosicionHijoIzquierdo(index));
                index = calcularPosicionHijoIzquierdo(index);
            } else {
                swapGanancia(index, calcularPosicionHijoDerecho(index));
                index = calcularPosicionHijoDerecho(index);
            }
        }
    }

    private void bajarTiempo(int index) {
        while (!this.esHoja(index) && this.prioridadDeAlgunHijoEsMayorTiempo(index)) {
            if (tieneHijoIzquierdo(index) && !tieneHijoDerecho(index)) {
                swapTiempo(index, calcularPosicionHijoIzquierdo(index));
                index =calcularPosicionHijoIzquierdo (index);
            } else if (comparadorPorTiempo.compare(this.heapPorTiempo.get(calcularPosicionHijoIzquierdo(index)), this.heapPorTiempo.get(calcularPosicionHijoDerecho(index))) > 0) {
                swapTiempo(index, calcularPosicionHijoIzquierdo(index));
                index = calcularPosicionHijoIzquierdo(index);
            } else {
                swapTiempo(index, calcularPosicionHijoDerecho(index));
                index = calcularPosicionHijoDerecho(index);
            }
        }
    }

    private boolean prioridadDeAlgunHijoEsMayorGanancia(int position) {
        boolean res = false;
        if (tieneHijoDerecho(position)){
            Traslado valueRightChild = heapPorGanancia.get(calcularPosicionHijoDerecho(position));
            Traslado valuePosition = heapPorGanancia.get(position);
            if (comparadorPorGanancia.compare(valueRightChild, valuePosition) > 0) {
                res = true;
            }
        }
        if (tieneHijoIzquierdo(position)) {
            Traslado valueLeftChild = heapPorGanancia.get(calcularPosicionHijoIzquierdo(position));
            Traslado valuePosition = heapPorGanancia.get(position);
            if (comparadorPorGanancia.compare(valueLeftChild, valuePosition) > 0) {
                res = true;
            }
        }
        return res;
    }

    private boolean prioridadDeAlgunHijoEsMayorTiempo(int position) {
        boolean res = false;
        if (tieneHijoDerecho(position)){
            Traslado valueRightChild = heapPorTiempo.get(calcularPosicionHijoDerecho(position));
            Traslado valuePosition = heapPorTiempo.get(position);
            if (comparadorPorTiempo.compare(valueRightChild, valuePosition) > 0) {
                res = true;
            }
        }
        if (tieneHijoIzquierdo(position)) {
            Traslado valueLeftChild = heapPorTiempo.get(calcularPosicionHijoIzquierdo(position));
            Traslado valuePosition = heapPorTiempo.get(position);
            if (comparadorPorTiempo.compare(valueLeftChild, valuePosition) > 0) {
                res = true;
            }
        }
        return res;
    }

    private boolean esHoja(int indice) {
        return indice * 2 + 1 >= this.length();
    }

    private boolean tieneHijoIzquierdo(int indice) {
        return indice >= 0 && indice * 2 + 1 < this.length();
    }

    private boolean tieneHijoDerecho(int indice) {
        return indice >= 0 && indice * 2 + 2 < this.length();
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
