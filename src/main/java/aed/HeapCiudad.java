package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapCiudad {
    private ArrayList<Ciudad> heapPorSuperavit;
    private Comparator<Ciudad> comparator;
    private Ciudad[] arrayCiudad;
    private int cantCiudades;


    //HeapCiudad cuesta O(c)
    public HeapCiudad(int cantCiudades) {
        comparator = Comparator.comparing(Ciudad :: superavit);                                     //O(1)
        comparator = comparator.reversed();                                                         //O(1)
        comparator = comparator.thenComparing(Ciudad :: nombre);                                    //O(1)
        comparator = comparator.reversed();                                                         //O(1)

        arrayCiudad = new Ciudad[cantCiudades];                                                     //O(1)
        heapPorSuperavit = new ArrayList<>(cantCiudades);                                           //O(1)
        for(int i = 0; i < cantCiudades; i++) {                                                     //c iteraciones
            Ciudad ciudadAAñadir = new Ciudad (i);                                                  //O(1)
            this.arrayCiudad[i] = ciudadAAñadir;                                                    //O(1)
            heapPorSuperavit.add(ciudadAAñadir);                                                    //O(1)
        }
        //el for loop cuesta O(c)

        //Nota, no hace falta ordenarlo pues todas tienen ganancias y perdidas de 0 al inicio y solo tras
        // despacharlas las tenemos que modificar
        // AHora solo están ordenadas por el id
    }
    
    //mayorSuperavit cuesta O(1)
    public Ciudad mayorSuperavit() {
        return this.heapPorSuperavit.get(0);                                                        //O(1)
    }

    
    public void despacharTraslados(Traslado traslado) {
        //Implementar
        //Debe modificar tanto al que compra como al que vende
        int posicionOrigenEnHeap = traslado.obtenerCiudadOrigen();                                  //O(1)
        posicionOrigenEnHeap = arrayCiudad[posicionOrigenEnHeap].getPosicionEnHeap();               //O(1)

        int posicionDestinoEnHeap = traslado.obtenerCiudadDestino();                                //O(1)
        posicionDestinoEnHeap = arrayCiudad[posicionDestinoEnHeap].getPosicionEnHeap();             //O(1)
    
        arrayCiudad[traslado.obtenerCiudadOrigen()].aumentarGanancia(traslado.gananciaNeta());      //O(1)
        modificar(posicionOrigenEnHeap);                                                            

        arrayCiudad[traslado.obtenerCiudadDestino()].aumnetarPerdida(traslado.gananciaNeta());      //O(1)
        modificar(posicionDestinoEnHeap);

    }

    //getGanancia cuesta //O(1)
    public int getGanancia(int index) {
        return this.arrayCiudad[index].gananciaNeta();                                              //O(1)
    }

    //getPerdida cuesta //O(1)
    public int getPerdida(int index) {
        return this.arrayCiudad[index].perdidaNeta();                                               //O(1)
    }

    
    private void modificar(int index) {
        //M
        //  this.heap.set(index, newValue);
        this.mover(index);
    }

    //swap cuesta O(1)
    private void swap(int position1, int position2) {
        Ciudad guardarCiudad1 = this.heapPorSuperavit.get(position1);                      //O(1)
        Ciudad guardarCiudad2 = this.heapPorSuperavit.get(position2);                      //O(1)

        //Vamos con los handles
        guardarCiudad1.setPosicionEnHeap(position2);                                       //O(1)                                             
        guardarCiudad2.setPosicionEnHeap(position1);                                       //O(1)

        //Los intercambiosamos
        this.heapPorSuperavit.set(position1, this.heapPorSuperavit.get(position2));        //O(1)
        this.heapPorSuperavit.set(position2, guardarCiudad1);                              //O(1)
    }


    //mover cuesta log(n)
    private void mover(int index) {                                                         //log n
        if (index == 0) {                                                                            
            bajarSuperavit(index);                                                          //log n                                                   
        } else if (prioridadDeAlgunHijoEsMayorSuperavit(index)) {
            bajarSuperavit(index);                                                          //log n
        } else {
            subirElementoSuperavit(index);                                                  //log n
        }
    }


    //bajarSuperavit cuesta log(n)
    private void bajarSuperavit(int index) {
        while (!this.esHoja(index) && this.prioridadDeAlgunHijoEsMayorSuperavit(index)){      //en el peor caso itera log n veces
            if (tieneHijoIzquierdo(index) && !tieneHijoDerecho(index)) {                      //O(1)
                swap(index, calcularPosicionHijoIzquierdo(index));
                index =calcularPosicionHijoIzquierdo (index);                                 //O(1)
            } else if (comparator.compare(this.heapPorSuperavit.get(calcularPosicionHijoIzquierdo(index)), this.heapPorSuperavit.get(calcularPosicionHijoDerecho(index))) > 0) {
                swap(index, calcularPosicionHijoIzquierdo(index));
                index = calcularPosicionHijoIzquierdo(index);                                 //O(1)
            } else {
                swap(index, calcularPosicionHijoDerecho(index));
                index = calcularPosicionHijoDerecho(index);                                   //O(1)
            }
        }
    }

    //subirElementoSuperavit cuesta log(n)
    private void subirElementoSuperavit(int index) {
        int positionParent = calcularPosicionPadre(index);                                      //O(1)
        while (index != 0 && prioridadMayorQuePadrePorSuperavit(index)) {                       //en el peor caso itera log n veces
            swap(index, positionParent);                                                        //O(1)
            index = positionParent;                                                             //O(1)
            positionParent = calcularPosicionPadre(index);                                      //O(1)
        }
    }
    
    // prioridadMayorQuePadrePorSuperavit cuesta O(1)
    private boolean prioridadMayorQuePadrePorSuperavit(int index) {
        int positionPadre = calcularPosicionPadre(index);                                       //O(1)
        return comparator.compare(this.heapPorSuperavit.get(index), this.heapPorSuperavit.get(positionPadre)) > 0;  //O(1)
    }

    //prioridadDeAlgunHijoEsMayorSuperavit cuesta O(1)
    private boolean prioridadDeAlgunHijoEsMayorSuperavit(int index) {
        boolean res = false;                                                                     //O(1)

        if (tieneHijoDerecho(index)){                                                            //O(1)
            Ciudad valueRightChild = heapPorSuperavit.get(calcularPosicionHijoDerecho(index));   //O(1)
            Ciudad valuePosition = heapPorSuperavit.get(index);                                  //O(1)
            if (comparator.compare(valueRightChild, valuePosition) > 0) {                        //O(1)
                res = true;                                                                      //O(1)
            }
        }
        if (tieneHijoIzquierdo(index)) {                                                         //O(1)
            Ciudad valueLeftChild = heapPorSuperavit.get(calcularPosicionHijoIzquierdo(index));  //O(1)
            Ciudad valuePosition = heapPorSuperavit.get(index);                                  //O(1)
            if (comparator.compare(valueLeftChild, valuePosition) > 0) {                         //O(1)
                res = true;                                                                      //O(1)
            }
        }
        return res;                                                                              //O(1)
    }

    //esHoja cuesta O(1)
    private boolean esHoja(int indice) {
        return indice * 2 + 1 >= this.arrayCiudad.length;
    }

    //tieneHijoIzquierdo cuesta O(1)
    private boolean tieneHijoIzquierdo(int indice) {
        return indice >= 0 && indice * 2 + 1 < this.arrayCiudad.length;
    }

    //tieneHijoDerecho cuesta O(1)
    private boolean tieneHijoDerecho(int indice) {
        return indice >= 0 && indice * 2 + 2 < this.arrayCiudad.length;
    }

    // Metodos estáticos


    //calcularPosicionPadre cuesta O(1)
    private static int calcularPosicionPadre(int position) {
        int positionParent;
        if (position % 2 == 0) {                                                                //O(1)
            positionParent = (position - 2) / 2;                                                //O(1)
        } else {
            positionParent = (position - 1) / 2;                                                //O(1)
        }
        return positionParent;                                                                  //O(1)
    }

    //calcularPosicionHijoIzquierdo cuesta O(1)
    private static int calcularPosicionHijoIzquierdo(int position) {
        return position * 2 + 1;                                                                //O(1)
    }

    //calcularPosicionHijoDerecho cuesta O(1)
    private static int calcularPosicionHijoDerecho(int position) {
        return position * 2 + 2;                                                                //O(1)
    }


}
