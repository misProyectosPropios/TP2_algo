package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class Heaps<T> implements ColaDePrioridad<T>{
    private ArrayList<T> heap = new ArrayList<T>(0);
    private Comparator<T> comparador;

    public Heaps (Comparator<T> comparador) {
        this.comparador = comparador;
    }

    public T proximo() {
        return this.heap.get(0);
    }

    public void Encolar(T element) {
        //Needs to be implemented
        //Adds element to the last position
        this.heap.add(element);
        this.subirElemento();
    }

    private void subirElemento() {
        int position = this.heap.size() - 1;
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
        return true;
    }

    private void swap(int position1, int position2) {
        T guardar = this.heap.get(position1);
        this.heap.set(position1, this.heap.get(position2));
        this.heap.set(position2, guardar);

    }

    public T desencolar(){
        T returnValue = this.heap.get(0); //Obtenemos el Objecto a devovler O(1)
        this.heap.set(0, this.heap.get(this.heap.size())); // O(1) Pasamos el ultimo al primeor
        
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
        //Implementarlo
        return true;
    }

    //Asumimos que no es un null
    private boolean prioridadDeHijoDerecho(int position) {
        //Implementarlo
        return true;
    }

    //Devuelve true si es izquierda, si es false es derecha
    private boolean compararPrioridadHijos(int position) {
        //implementar
        return true;
    }

    private boolean esHoja(int indice) {
        return indice * 2 + 2 >= this.heap.size(); //Verificar si es correcta esta cuenta
    }

    private boolean esNodoCompleto(int indice) {
        return Heaps.calcularPosicionHijoDerecho(indice) < this.heap.size();
    }

    public T quitar(int pos) {
        return this.heap.get(0);
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

    /* 
    private void bajarElemento(int i){
        //CASO BASE
        if (this.esHoja(i)) {
            return;
        }

        //Es lo mismo que acá
        if(izq(i)>=heap.size() && der(i)>=this.heap.size()){
            return;
        }

        //No es hoja, veamos si tiene un hijo o dos
        if (this.esNodoCompleto(i)) {

        } 
        else  //Tiene 1 solo nodo izquierdo. Por invariante, no puede ser nucna el derecho e que tenga
        {

        }
        //CASO TIENE 2 hijos
        if (izq(i)<heap.size() && der(i)<this.heap.size()){
            if(this.heap.get(i)<this.heap.get(izq(i)) || this.heap.get(i)<this.heap.get(der(i))){
                if(heap.get(izq(i))>this.heap.get(der(i))){
                    swap(i , izq(i));
                    bajarElemento( izq(i));} else{
                    swap(i, der(i));
                    bajarElemento(der(i));
                        }
                } else{return;}
            } else{
    
            //Caso Izq null
            if (izq(i)>=this.heap.size()){
                //Nos fijamos si i es menor que su hijo derecho, si lo es hacemos swap y recursion
                if (this.heap.get(i)<this.heap.get(der(i))){
                    swap(i, der(i));
                    bajarElemento(der(i));}
                //Si no es menor, return
                    else{ return;}}
            //Caso Der null
            if (der(i)>=this.heap.size()){
                if(this.heap.get(i)<this.heap.get(izq(i))){
                    swap( i, izq(i));
                    bajarElemento( izq(i)); 
                } else{return;}
            }
        }
        
            
    }
    
    */
    private static int der(int i){
        return  i*2+2;
     }
     
    private static int izq(int i){
         return i*2+1;
     }

    


}

}