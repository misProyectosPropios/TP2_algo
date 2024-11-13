package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class HeapsTest {
    Comparator<Ciudad> comparatorGananciaNeta = Comparator.comparing(Ciudad::gananciaNeta); //hace falta el getter de ese tipo 

    //Comparator<Traslado> comparator = Comparator.comparingInt(Traslado::gananciaNeta);
    //Heaps<Integer> heap = new Heaps<>();

    @BeforeEach
    void init() {
         
    }

    @Test
    void crearHeapVacio() {
        Comparator<Integer> comp = Comparator.naturalOrder();
        Heaps<Integer> heap = new Heaps<>(comp);

        assertTrue(heap.estaVacio());
    }

    @Test 
    void crearHeapAPartirDeArray() {
        Integer[] array = {8, 7, 2, 3, 5, 6, 4, 10, 1};
        Comparator<Integer> comp = Comparator.naturalOrder();
        Heaps<Integer> heap = new Heaps<>(comp, array);
        assertEquals(heap.length(), 9);
        assertEquals(10, heap.desencolar());
        assertEquals(8, heap.desencolar());
        assertEquals(7, heap.desencolar());
        assertEquals(6, heap.desencolar());
        assertEquals(5, heap.desencolar());
        assertEquals(4, heap.desencolar());
    }

}
