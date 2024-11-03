package aed;

import java.util.ArrayList;

public class Heaps<T> {
    private ArrayList<T> heap = new ArrayList<T>(0);

    public void addElement(T element) {

    }

    public T proximo() throws Exception {
        if (this.heap.size() > 0) {
            return this.heap.get(0);
        }
        throw new Exception("Heaps doens't have any elements");
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

    //Implementarlo
    private boolean prioridadMayorQuePadre(int positionElement, int positionParentElement) {
        return true;
    }

    private void swap(int position1, int position2) {
        T guardar = this.heap.get(position1);
        this.heap.set(position1, this.heap.get(position2));
        this.heap.set(position2, guardar);

    }

    public T desencolar() throws Exception {
        if (this.heap.size() == 0) {
            throw new Exception("Heaps doens't have any elements"); 
        }
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
    static int der(int i){
        return  i*2+2;
     }
     
     static int izq(int i){
         return i*2+1;
     }

    


}
