package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapsTraslado {
    private ArrayList<Traslado> heapPorGanancia;
    private ArrayList<Traslado> heapPorTiempo;
    private Comparator<Traslado> comparadorPorGanancia;
    private Comparator<Traslado> comparadorPorTiempo;

    //HeapsTraslado cuesta O(1)
    public HeapsTraslado() {
        //Inicializando los arrays
        heapPorGanancia = new ArrayList<>(0);                                                     //O(1)
        heapPorTiempo = new ArrayList<>(0);                                                       //O(1)

        //Craaciòn del comparador por ganancia
        comparadorPorGanancia = Comparator.comparing(Traslado::gananciaNeta);                     //O(1)
        comparadorPorGanancia = comparadorPorGanancia.reversed();
        comparadorPorGanancia = comparadorPorGanancia.thenComparing(Traslado::id);                //O(1)
        comparadorPorGanancia = comparadorPorGanancia.reversed();
        
        //Craaciòn del comparador por tiempo
        comparadorPorTiempo = Comparator.comparing(Traslado::timestamp);                          //O(1)
        comparadorPorTiempo = comparadorPorTiempo.reversed();                                     //O(1)
        comparadorPorTiempo = comparadorPorTiempo.thenComparing(Traslado::id);                    //O(1)
    }

    //Heaps Traslado cuesta O(t)
    public HeapsTraslado(Traslado[] array) {
        heapPorGanancia = new ArrayList<>(array.length);                                          //O(1)
        heapPorTiempo = new ArrayList<>(array.length);                                            //O(1)

        //Agrego los elementos al array de heapPorGanancia y heapPorTiempo para despuès ordenarlo
        for (int i = 0; i < array.length; i++) {                                                 //itera t veces
            array[i].setIndiceAHeapAntiguedad(i);                                                //O(1)
            array[i].setIndiceAHeapGanancia(i);                                                  //O(1)
            this.heapPorGanancia.add(array[i]);                                                  //O(1)      
            this.heapPorTiempo.add(array[i]);                                                    //O(1)
        }
        //el for loop cuesta O(t)

        //Craaciòn del comparador por ganancia
        comparadorPorGanancia = Comparator.comparing(Traslado::gananciaNeta);                    //O(1)
        comparadorPorGanancia = comparadorPorGanancia.reversed();                                //O(1)
        comparadorPorGanancia = comparadorPorGanancia.thenComparing(Traslado::id);               //O(1)
        comparadorPorGanancia = comparadorPorGanancia.reversed();                                //O(1)

        //Craaciòn del comparador por tiempo
        comparadorPorTiempo = Comparator.comparing(Traslado::timestamp);                        //O(1)
        comparadorPorTiempo = comparadorPorTiempo.reversed();                                   //O(1)
        comparadorPorTiempo = comparadorPorTiempo.thenComparing(Traslado::id);                  //O(1)    

        //Ordenarlos usando el heapify
        floydGanancia();                                                                        //O(t)
        floydTiempo();                                                                          //O(t)
    }

    //floydGanancia cuesta O (n)
    private void floydGanancia() {
        for (int i = this.length() / 2 + 1; i >= 0; i--) {                                     // O (n)
            this.bajarGanancia(i);                                                             // O (log i)
        }
    }
    //Demostracion informal: como vamos de abajo a arriba, la sumatoria de logs (i) es pequeño y solo hay pocos que sea grandes
    
    //floydTiempo cuesta O(n)
    private void floydTiempo() {
        for (int i = this.length() / 2 + 1; i >= 0; i--) {
            this.bajarTiempo(i);
        }
    }


    // proximoPorGanancia O(1)
    public Traslado proximoPorGanancia() {
        if (this.heapPorGanancia.size() > 0) {                                                 // O(1)
            return this.heapPorGanancia.get(0);                                                // O(1)
        }
        return null;                                                                           // O(1)
    }

    // proximoPorTiempo O(1)
    public Traslado proximoPorTiempo() {
        if (this.heapPorTiempo.size() > 0) {                                                   // O(1)
            return this.heapPorTiempo.get(0);                                                  // O(1)
        }
        return null;                                                                           // O(1)
    }

    // encolar O(log n)
    public void encolar(Traslado element) {
        Traslado elementoNuevo = new Traslado(element);
        this.heapPorGanancia.add(elementoNuevo);                                                      // O(1) 
        this.heapPorTiempo.add(elementoNuevo);                                                        // O(1)

        elementoNuevo.setIndiceAHeapAntiguedad(this.length() - 1);
        elementoNuevo.setIndiceAHeapGanancia(this.length() - 1);
        
        //Ordenarlo
        this.subirElementoGanancia(this.heapPorGanancia.size() - 1);                            // O(log n)
        this.subirElementoTiempo(this.heapPorGanancia.size() - 1);                              // O(log n)
    }

    // O(log n)
    public Traslado desencolarPorGanancia() {
        return this.eliminarGanancia(0);                                                  // O(log n)
    }

    // desencolarPorTiempo O(log n)
    public Traslado desencolarPorTiempo() {
        return this.eliminarTiempo(0);                                                    // O(log n)
    }

    // O(1)
    public int length() {
        // Tendrían los mismos elementos el heap de tiempo y ganancia
        return this.heapPorGanancia.size();                                                     // O(1)
        
    }

    // subirElementoGanancia O(log n)
    private void subirElementoGanancia(int posicion) {
        int positionParent = calcularPosicionPadre(posicion);                                   // O(1)
        while (posicion != 0 && prioridadMayorQuePadrePorGanancia(posicion)) {                  // O(log n)
            swapGanancia(posicion, positionParent);                                             // O(1)
            posicion = positionParent;                                                          // O(1)
            positionParent = calcularPosicionPadre(posicion);                                   // O(1)
        }   
    }

    // prioridadMayorQuePadrePorGanancia O(1)
    private boolean prioridadMayorQuePadrePorGanancia(int positionElement) {
        //Si el elemento es menor que el parent, debe de devolver numero negativo
        int positionPadre = calcularPosicionPadre(positionElement);                                                                     // O(1)
        return comparadorPorGanancia.compare(this.heapPorGanancia.get(positionElement), this.heapPorGanancia.get(positionPadre)) > 0;   // O(1)
    }

    // swapGanancia O(1)
    private void swapGanancia(int position1, int position2) {
        //Guardamos los traslados a swapear
        Traslado guardarPosicion1 = this.heapPorGanancia.get(position1);                        // O(1)
        Traslado guardarPosicion2 = this.heapPorGanancia.get(position2);                        // O(1)

        //Ponemos sus nuevas posiciones
        guardarPosicion1.setIndiceAHeapGanancia(position2);                                     // O(1)
        guardarPosicion2.setIndiceAHeapGanancia(position1);                                     // O(1)

        this.heapPorGanancia.set(position1, this.heapPorGanancia.get(position2));               // O(1)
        this.heapPorGanancia.set(position2, guardarPosicion1);                                  // O(1)
    }

    // swapTiempo O(1)
    private void swapTiempo(int position1, int position2) {
        //Falta adaptar los handles todavia del heap por tiempo
        Traslado guardarPosicion1 = this.heapPorTiempo.get(position1);                          // O(1)
        Traslado guardarPosicion2 = this.heapPorTiempo.get(position2);                          // O(1)

        //Ponemos sus nuevas posiciones
        guardarPosicion1.setIndiceAHeapAntiguedad(position2);                                   // O(1)
        guardarPosicion2.setIndiceAHeapAntiguedad(position1);                                   // O(1)

        this.heapPorTiempo.set(position1, this.heapPorTiempo.get(position2));                   // O(1)
        this.heapPorTiempo.set(position2, guardarPosicion1);                                    // O(1)
    }

    // subirElementoTiempo O(log n)
    private void subirElementoTiempo(int posicion) {
        int positionParent = calcularPosicionPadre(posicion);                                   // O (1)
        while (posicion != 0 && prioridadMayorQuePadrePorTiempo(posicion)) {                    // O (log n)
            swapTiempo(positionParent, posicion);                                               // O(1)
            posicion = positionParent;                                                          // O(1) 
            positionParent = calcularPosicionPadre(posicion);                                   // O(1) 
        }
    }

    // prioridadMayorQuePadrePorTiempo O(1)
    private boolean prioridadMayorQuePadrePorTiempo(int positionElement) {
        //Si el elemento es menor que el parent, debe de devolver numero negativo
        int positionPadre = calcularPosicionPadre(positionElement);                                                              // O(1) 
        return comparadorPorTiempo.compare(this.heapPorTiempo.get(positionElement), this.heapPorTiempo.get(positionPadre)) > 0;  // O(1) 
    }

    // eliminarGanancia O(log n)
    private Traslado eliminarGanancia(int index) {        Traslado returnValue = this.heapPorGanancia.get(index);                                    // O(1) 
        int indexTiempo = returnValue.obtenerIndexAHeapAntiguedad();                               // O(1) 
        //Forzamos a que ambos estén en la ultima posición para evitar perdida de información
        swapGanancia(index, this.length() - 1);                                                    // O(1) 

        swapTiempo(indexTiempo, this.length() - 1);                                                // O(1) 

        //Borramos los valores
        this.heapPorGanancia.remove(this.heapPorGanancia.size() - 1);                              // O(log n) 
        this.heapPorTiempo.remove(this.heapPorTiempo.size() - 1);                                  // O(log n) 

        //Ajustamos los heaps
        if (index >= length() && indexTiempo >= length()) {                                         // O(1)
            return returnValue;
        }
        if (index >= length()) {                                                                    // O(1)
            this.moverTiempo(indexTiempo);                                                          // O(log n) 
        } else if (indexTiempo >= length()) {
            this.moverGanancia(index);                                                              // O(log n) 
        } else {    
            this.moverGanancia(index);                                                              // O(log n) 
            this.moverTiempo(indexTiempo);                                                          // O(log n) 
        }
        // O (max de todos ifs) En este caso es O (log n)
        return returnValue;                                                                         // O(1)
    }

    // eliminarTiempo O(log n)
    private Traslado eliminarTiempo(int index) {
        Traslado returnValue = this.heapPorTiempo.get(index);                                       // O(1)
        int indexGanancia = returnValue.obtenerIndiceAHeapGanancia();                               // O(1)

        //Forzamos a que ambos sean 0
        swapTiempo(index, this.length() - 1);                                                       // O(1)
        swapGanancia(indexGanancia, this.length() - 1);                                             // O(1)

        //Borramos los valores
        this.heapPorGanancia.remove(this.heapPorGanancia.size() - 1);                               // O(log n)
        this.heapPorTiempo.remove(this.heapPorTiempo.size() - 1);                                   // O(log n)

        if (index >= length() && indexGanancia >= length()) {                                       // O(1)
            return returnValue;
        }

        //Ajustamos los heaps
        if (index >= length()) {                                                                    // O(1)
            this.moverGanancia(indexGanancia);                                                      // O(log n)
        } else if (indexGanancia >= length()) {                                                     // O(1)
            this.moverTiempo(index);                                                                // O(log n)
        } else {
            this.moverGanancia(indexGanancia);                                                      // O(log n)
            this.moverTiempo(index);                                                                // O(log n)
        }
        return returnValue;
    }

    // moverGanancia O(log n)
    private void moverGanancia(int index) {
        if (index == 0) {                                                                           // O(1) 
            bajarGanancia(index);                                                                   // O(log n)
        } else if (prioridadDeAlgunHijoEsMayorGanancia(index)) {                                    // O(1)
            bajarGanancia(index);                                                                   // O(log n)
        } else {
            subirElementoGanancia(index);                                                           // O(log n)
        }
    }

    // moverTiempo O(log n)
    private void moverTiempo(int index) {
        if (index == 0) {                                                                           // O(1)
            bajarTiempo(index);
        } else if (prioridadDeAlgunHijoEsMayorTiempo(index)) {                                      // O(1)
            bajarTiempo(index);                                                                     // O(log n)
        } else {
            subirElementoTiempo(index);                                                             // O(log n)
        }
    }

    // bajarGanancia O(log n)
    private void bajarGanancia(int index) {
        while (!this.esHoja(index) && this.prioridadDeAlgunHijoEsMayorGanancia(index)) {            // se ejecuta log n
            if (tieneHijoIzquierdo(index) && !tieneHijoDerecho(index)) {                            // O(1)
                swapGanancia(index, calcularPosicionHijoIzquierdo(index));                          // O(1)
                index =calcularPosicionHijoIzquierdo (index);                                       // O(1)
            } else if (comparadorPorGanancia.compare(this.heapPorGanancia.get(calcularPosicionHijoIzquierdo(index)), this.heapPorGanancia.get(calcularPosicionHijoDerecho(index))) > 0) 
            {                                                                                       // O(1)
                swapGanancia(index, calcularPosicionHijoIzquierdo(index));                          // O(1)
                index = calcularPosicionHijoIzquierdo(index);                                       // O(1)                                 
            } else {
                swapGanancia(index, calcularPosicionHijoDerecho(index));                            // O(1)
                index = calcularPosicionHijoDerecho(index);                                         // O(1)
            }
        }
    }

    // Como el código para bajarGanancia y bajarTiempo es el mismo, tienen igual complejidad
    // bajarTiempo O(log n) 
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

    // prioridadDeAlgunHijoEsMayorGanancia O(1)
    private boolean prioridadDeAlgunHijoEsMayorGanancia(int position) {
        boolean res = false;                                                                        // O(1)
        if (tieneHijoDerecho(position)){                                                            // O(1)
            Traslado valueRightChild = heapPorGanancia.get(calcularPosicionHijoDerecho(position));  // O(1)
            Traslado valuePosition = heapPorGanancia.get(position);                                 // O(1)
            if (comparadorPorGanancia.compare(valueRightChild, valuePosition) > 0) {                // O(1)
                res = true;                                                                         // O(1)
            }
        }
        if (tieneHijoIzquierdo(position)) {                                                         // O(1)
            Traslado valueLeftChild = heapPorGanancia.get(calcularPosicionHijoIzquierdo(position)); // O(1)
            Traslado valuePosition = heapPorGanancia.get(position);                                 // O(1)
            if (comparadorPorGanancia.compare(valueLeftChild, valuePosition) > 0) {                 // O(1)
                res = true;                                                                         // O(1)
            }
        }
        return res;                                                                                 // O(1)
    }

    // Como el código para prioridadDeAlgunHijoEsMayorGanancia y prioridadDeAlgunHijoEsMayorTiempo es el mismo, 
    // tienen igual complejidad
    // prioridadDeAlgunHijoEsMayorTiempo O(1)
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

    // esHoja O(1)
    private boolean esHoja(int indice) {
        return indice * 2 + 1 >= this.length();                                                          // O(1)
    }

    // tieneHijoIzquierdo O(1)
    private boolean tieneHijoIzquierdo(int indice) {
        return indice >= 0 && indice * 2 + 1 < this.length();                                            // O(1)
    }

    // tieneHijoDerecho O(1)
    private boolean tieneHijoDerecho(int indice) {
        return indice >= 0 && indice * 2 + 2 < this.length();                                            // O(1)
    }

    // Metodos estáticos

    // calcularPosicionPadre O(1)
    private static int calcularPosicionPadre(int position) {
        int positionParent;                                                                               // O(1)
        if (position % 2 == 0) {                                                                          // O(1)
            positionParent = (position - 2) / 2;                                                          // O(1)
        } else {
            positionParent = (position - 1) / 2;                                                          // O(1)
        }
        // Agarramos la mayor complejidad de ambas ramas. En este caso ambas cuestan O(1), asi que va a ser O(1)
        return positionParent;                                                                            // O(1)
    } 

    // calcularPosicionHijoIzquierdo O(1)
    private static int calcularPosicionHijoIzquierdo(int position) {
        return position * 2 + 1;                                                                          // O(1)
    }

    // calcularPosicionHijoDerecho O(1)
    private static int calcularPosicionHijoDerecho(int position) {
        return position * 2 + 2;                                                                          // O(1)
    }

}