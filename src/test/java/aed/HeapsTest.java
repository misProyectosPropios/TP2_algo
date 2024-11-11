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
    }

}
