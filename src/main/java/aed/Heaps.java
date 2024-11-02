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
    }

    public T desencolar() throws Exception {
        if (this.heap.size() == 0) {
            throw new Exception("Heaps doens't have any elements"); 
        }
        return this.heap.get(0);
    }

    

}