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

    private int calcularPosicionPadre(int position) {
        int positionParent;
        if (position % 2 == 0) {
            positionParent = (position - 2) / 2;
        } else {
            positionParent = (position - 1) / 2;
        }
        return positionParent;
    }

    private int calcularPosicionHijoIzquierdo(int position) {
        return position * 2 + 1;
    }

    private int calcularPosicionHijoDerecho(int position) {
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

    private void bajar(int p) {
        while (!this.esHoja(p)) {

        }
    }

    private boolean prioridadDeAlgunHijoEsMayor(int position) {
        //Implementar
        return !this.esHoja(position); //
    }

    private boolean prioridadDeHijoIzquierdo(int posicionHijoIzquierdo) {
        //Implementarlo
        return true;
    }

    private boolean prioridadDeHijoDerecho(int posicionHijoDerecho) {
        //Implementarlo
        return true;
    }

    private boolean esHoja(int indice) {
        return indice * 2 >= this.heap.size(); //Verificar si es correcta esta cuenta
    }

    public T quitar(int pos) {
        return this.heap.get(0);
    }

    private void bajarElemento(int i){
        //CASO BASE
        if(izq(i)>=heap.size() && der(i)>=this.heap.size()){
            return;}
        
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
    
    private static int der(int i){
        return  i*2+2;
     }
     
    private static int izq(int i){
         return i*2+1;
     }

    


}

}