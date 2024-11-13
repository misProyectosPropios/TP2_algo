package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapsTraslado<Traslado> implements ColaDePrioridad<Traslado>{
     ArrayList<Traslado> heap;
    private Comparator<Traslado> comparador;
    
    public HeapsTraslado (Comparator<Traslado> comparador) {
        this.comparador = comparador;
        this.heap = new ArrayList<Traslado>(0);
    }

    public HeapsTraslado(Comparator<Traslado> comparador, Traslado[] array) {
        this.comparador = comparador;
        this.heap = new ArrayList<>(array.length);
        for (Traslado element : array) {
            this.heap.add(element);
        }
        this.FloydAlgorithm();
    }

    public Traslado proximo() {

        if (this.heap.size() > 0) {
            return this.heap.get(0);
        }
        return null;
    }

    public void encolar(Traslado element) {
        //Needs to be implemented
        //Adds element to the last position
        this.heap.add(element);
        this.subirElemento(this.heap.size() - 1);
    }

    public boolean estaVacio(){
        return this.heap.size()==0;
    }

    public int length() {
        return this.heap.size();
    }

    public void modificar(int index, Traslado newValue) {
        this.heap.set(index, newValue);
        this.mover(index);
    }

    public Traslado desencolar() {
        Traslado returnValue = this.heap.get(0); //Obtenemos el Objecto a devovler O(1)
        this.heap.set(0, this.heap.get(this.heap.size() - 1)); // O(1) Pasamos el ultimo al primeor
        //Bajar el elemento hasta la posición deseada
        this.bajar(0);

        return returnValue;
    }

    public Traslado eliminar(int pos) {
        Traslado returnValue = this.heap.get(pos);
        swap(pos, this.heap.size() - 1);
        this.heap.remove(this.heap.size() - 1);
        mover(pos);
        return returnValue;
    }

    private void subirElemento(int position) {
        int positionParent = calcularPosicionPadre(position);
        while (position != 0 && prioridadMayorQuePadre(position)) {
            swap(position, positionParent);
            position = positionParent;
            positionParent = calcularPosicionPadre(position);
        }
    }

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

    private boolean prioridadMayorQuePadre(int positionElement) {
        //Si el elemento es menor que el parent, debe de devolver numero negativo
        int positionPadre = calcularPosicionPadre(positionElement);
        return comparador.compare(this.heap.get(positionElement), this.heap.get(positionPadre)) > 0;
    }

    private void swap(int position1, int position2) {
        Traslado guardar = this.heap.get(position1);
        this.heap.set(position1, this.heap.get(position2));
        this.heap.set(position2, guardar);

    }

    //Asumimos que no va a ser una hoja
    private boolean prioridadDeAlgunHijoEsMayor(int position) {
        boolean res = false;
        if (tieneHijoDerecho(position)){
            Traslado valueRightChild = heap.get(calcularPosicionHijoDerecho(position));
            Traslado valuePosition = heap.get(position);
            if (comparador.compare(valueRightChild, valuePosition) > 0) {
                res = true;
            }
        }
        if (tieneHijoIzquierdo(position)) {
            Traslado valueLeftChild = heap.get(calcularPosicionHijoIzquierdo(position));
            Traslado valuePosition = heap.get(position);
            if (comparador.compare(valueLeftChild, valuePosition) > 0) {
                res = true;
            }
        }
        return res;
    }

    //Asumimos que no es un null
    private boolean prioridadDeHijoIzquierdo(int position) {
        //Returns true if the left child is greater than the position value
        return tieneHijoIzquierdo(position) && comparador.compare(this.heap.get(HeapsTraslado.calcularPosicionHijoIzquierdo(position)), this.heap.get(position)) > 0;
    }

    //Asumimos que no es un null
    private boolean prioridadDeHijoDerecho(int position) {
        //Returns true if the right child is greater than the position value
        return tieneHijoDerecho(position) && comparador.compare(this.heap.get(HeapsTraslado.calcularPosicionHijoDerecho(position)), this.heap.get(position)) > 0;
    }

    //Devuelve true si es izquierda, si es false es derecha
    private boolean compararPrioridadHijos(int position) {
        if (tieneHijoIzquierdo(position) && !tieneHijoDerecho(position)) {
            return true;
        } 
        //Suponemos que tiene hijoDerecho ahora, así como izquierdo
        Traslado rightChild = this.heap.get(HeapsTraslado.calcularPosicionHijoDerecho(position));
        Traslado leftChild = this.heap.get(HeapsTraslado.calcularPosicionHijoIzquierdo(position));
        return comparador.compare(rightChild, leftChild) >= 0; 
        //Return true iff the leftChild is greater or equal than the rigthChild
    }

    private boolean esHoja(int indice) {
        return indice * 2 + 2 >= this.heap.size(); //Verificar si es correcta esta cuenta
    }

    private boolean tieneHijoIzquierdo(int indice) {
        return indice >= 0 && indice * 2 + 1 < this.heap.size();
    }

    private boolean tieneHijoDerecho(int indice) {
        return indice >= 0 && indice * 2 + 2 < this.heap.size();
    }

    private boolean esNodoCompleto(int indice) {
        return HeapsTraslado.calcularPosicionHijoDerecho(indice) < this.heap.size();
    }

    private void mover(int index) {
        if (index == 0) {
            bajar(index);
        } else if (prioridadDeAlgunHijoEsMayor(index)) {
            bajar(index);
        } else {
            subirElemento(index);
        }
    }

    //TIme complexity: O(n)
    private void FloydAlgorithm() {
        for(int i = this.heap.size() / 2 + 1; i >= 0; i--) {
            this.bajar(i);
        }
    }

    private void bajar(int index) {
        while (!this.esHoja(index) && this.prioridadDeAlgunHijoEsMayor(index)) {
            if (tieneHijoIzquierdo(index) && !tieneHijoDerecho(index)) {
                swap(index, calcularPosicionHijoIzquierdo(index));
                index =calcularPosicionHijoIzquierdo (index);
                //El hijo izquierdo es mayor que el derecho
            } else if (comparador.compare(this.heap.get(calcularPosicionHijoIzquierdo(index)), this.heap.get(calcularPosicionHijoDerecho(index))) > 0 ){
                swap(index, calcularPosicionHijoIzquierdo(index));
                index = calcularPosicionHijoIzquierdo(index);
                //El hijo derecho o es mayor o es igual al izquierdo
            } else {
                swap(index, calcularPosicionHijoDerecho(index));
                index = calcularPosicionHijoDerecho(index);
            }

        }
    }
}
