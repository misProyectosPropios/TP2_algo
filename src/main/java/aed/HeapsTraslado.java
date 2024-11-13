package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapsTraslado{
    private ArrayList<Traslado> heapPorGanancia;
    private ArrayList<Traslado> heapPorTiempo;
    private Comparator<Traslado> comparadorPorGanancia;
    private Comparator<Traslado> comparadorPorTiempo;
    
    public HeapsTraslado (Comparator<Traslado> comparador) {
        //Inicializando los arrays
        heapPorGanancia = new ArrayList<>(0);
        heapPorTiempo = new ArrayList<>(0);

        //Craaciòn del comparador por ganancia
        comparadorPorGanancia = Comparator.comparing(Traslado :: gananciaNeta);
        comparadorPorGanancia = comparadorPorGanancia.thenComparing(Traslado::id);

        //Craaciòn del comparador por tiempo
        comparadorPorTiempo = Comparator.comparing(Traslado :: timestamp);
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
        comparadorPorGanancia = Comparator.comparing(Traslado :: gananciaNeta);
        comparadorPorGanancia = comparadorPorGanancia.thenComparing(Traslado::id);

        //Craaciòn del comparador por tiempo
        comparadorPorTiempo = Comparator.comparing(Traslado :: timestamp);
        comparadorPorTiempo = comparadorPorTiempo.reversed();
        comparadorPorTiempo = comparadorPorTiempo.thenComparing(Traslado::id);

        //Ordenarlos usando el heapify

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
        
        //this.subirElemento(this.heap.size() - 1);
    }

    public boolean estaVacio(){
        return this.heapPorGanancia.size()==0; // Tendrían los mismos elementos el de tiempo y ganancia
    }

    public int length() {
        return this.heapPorGanancia.size(); // Tendrían los mismos elementos el de tiempo y ganancia
    }

    public void modificar(int index, Traslado newValue) {
      //  this.heap.set(index, newValue);
      //  this.mover(index);
    }

    public Traslado desencolarPorGanancia() {
        Traslado returnValue = this.heapPorGanancia.get(0); //Obtenemos el Objecto a devovler O(1)
        return returnValue;
    }

    public Traslado desencolarPorTiempo() {
        Traslado returnValue = this.heapPorGanancia.get(0); //Obtenemos el Objecto a devovler O(1)
        return returnValue;
    }

    public Traslado desencolar() {
        Traslado returnValue = this.heapPorGanancia.get(0); //Obtenemos el Objecto a devovler O(1)
    //    this.heap.set(0, this.heap.get(this.heap.size() - 1)); // O(1) Pasamos el ultimo al primeor
    //    //Bajar el elemento hasta la posición deseada
    //    this.bajar(0);

        return returnValue;
    }


    //Me parece que no hará falta un eliminar en pos que sea publico, 
    //SI que sea privado
   // public Traslado eliminar(int pos) {
   //     Traslado returnValue = this.heapPorGanancia.get(pos);
   // //    swap(pos, this.heap.size() - 1);
   // //    this.heap.remove(this.heap.size() - 1);
   // //    mover(pos);
   //     return returnValue;
   // }



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

    /* 
    private void subirElemento(int position) {
        int positionParent = calcularPosicionPadre(position);
        while (position != 0 && prioridadMayorQuePadre(position)) {
            swap(position, positionParent);
            position = positionParent;
            positionParent = calcularPosicionPadre(position);
        }
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
    */
}
