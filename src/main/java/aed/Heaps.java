package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class Heaps<T> implements ColaDePrioridad<T>{
     ArrayList<T> heap = new ArrayList<T>(0);
    private Comparator<T> comparador;
    

    public Heaps (Comparator<T> comparador) {
        this.comparador = comparador;
    }

    public Heaps(Comparator<T> comparador, T[] array) {
        this.comparador = comparador;
        this.heap = new ArrayList<>(array.length);
        for (T element : array) {
            this.heap.add(element);
        }
        this.FloydAlgorithm();
    }

    public T proximo() {
        if (this.heap.size() > 0) {
            return this.heap.get(0);
        }
        return null;
    }

    public void encolar(T element) {
        //Needs to be implemented
        //Adds element to the last position
        this.heap.add(element);
        this.subirElemento(this.heap.size() - 1);
    }

    private void subirElemento(int position) {
        int positionParent = calcularPosicionPadre(position);
        while (position != 0 && prioridadMayorQuePadre(position, positionParent)) {
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

    //Implementarlo
    private boolean prioridadMayorQuePadre(int positionElement, int positionParentElement) {
        //Si el elemento es menor que el parent, debe de devolver numero negativo
        return comparador.compare(this.heap.get(positionElement), this.heap.get(positionParentElement)) < 0; 
    }

    private void swap(int position1, int position2) {
        T guardar = this.heap.get(position1);
        this.heap.set(position1, this.heap.get(position2));
        this.heap.set(position2, guardar);

    }

    public T desencolar(){
        T returnValue = this.heap.get(0); //Obtenemos el Objecto a devovler O(1)
        this.heap.set(0, this.heap.get(this.heap.size() - 1)); // O(1) Pasamos el ultimo al primeor
        
        //Bajar el elemento hasta la posición deseada
        this.bajar(0);

        return returnValue;
    }

    //Asumimos que no va a ser una hoja
    private boolean prioridadDeAlgunHijoEsMayor(int position) {
        //Implementar usando el comparador
        return this.prioridadDeHijoIzquierdo(position) || this.prioridadDeHijoDerecho(position);
    }

    //Asumimos que no es un null
    private boolean prioridadDeHijoIzquierdo(int position) {
        //Returns true if the left child is greater than the position value
        return tieneHijoIzquierdo(position) && comparador.compare(this.heap.get(Heaps.calcularPosicionHijoIzquierdo(position)), this.heap.get(position)) > 0;
    }

    //Asumimos que no es un null
    private boolean prioridadDeHijoDerecho(int position) {
        //Implementarlo
        //Returns true if the right child is greater than the position value
        return tieneHijoDerecho(position) && comparador.compare(this.heap.get(Heaps.calcularPosicionHijoDerecho(position)), this.heap.get(position)) > 0;
    }

    //Devuelve true si es izquierda, si es false es derecha
    private boolean compararPrioridadHijos(int position) {
        //implementar
        if (tieneHijoIzquierdo(position) && !tieneHijoDerecho(position)) {
            return true;
        } 
        //Suponemos que tiene hijoDerecho ahora, así como izquierdo
        T rightChild = this.heap.get(Heaps.calcularPosicionHijoDerecho(position));
        T leftChild = this.heap.get(Heaps.calcularPosicionHijoIzquierdo(position));
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
        return Heaps.calcularPosicionHijoDerecho(indice) < this.heap.size();
    }

    public T eliminar(int pos) {
        //Implementar
        T returnValue = this.heap.get(pos);
        swap(pos, this.heap.size() - 1);
        this.heap.remove(this.heap.size() - 1);
        if (prioridadMayorQuePadre(pos, Heaps.calcularPosicionPadre(pos))) {
            //Implementarlo para index
            //subirElemento();
        } else {
            bajar(pos);
        }
        return returnValue;
    }

    //TIme complexity: O(n)
    private void FloydAlgorithm() {
        for(int i = this.heap.size() / 2 + 1; i >= 0; i--) {
            this.bajar(i);
        }
    }

    private void bajar(int index) {
        while (!this.esHoja(index) && this.prioridadDeAlgunHijoEsMayor(index)) {
            if (this.compararPrioridadHijos(index)) {
                swap(index, Heaps.calcularPosicionHijoIzquierdo(index));
                index = Heaps.calcularPosicionHijoIzquierdo(index);
            } else {
                swap(index, Heaps.calcularPosicionHijoDerecho(index));
                index = Heaps.calcularPosicionHijoDerecho(index);
            }
        }
    } 
    
    public boolean estaVacio(){
        return this.heap.size()==0;
    }

    public int length() {
        return this.heap.size();
    }


}
