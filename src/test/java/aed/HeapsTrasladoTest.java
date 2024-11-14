package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HeapsTrasladoTest {
    Traslado[] listaTraslados;

    @BeforeEach
    void init() {
        

    }

    @Test
    void crearHeapVacio() {
        listaTraslados = new Traslado[] {
                new Traslado(1, 0, 1, 100, 10),
                new Traslado(2, 0, 1, 400, 20),
                new Traslado(3, 3, 4, 500, 50),
                new Traslado(4, 4, 3, 500, 11),
                new Traslado(5, 1, 0, 1000, 40),
                new Traslado(6, 1, 0, 1500, 41),
                new Traslado(7, 6, 3, 2000, 42)
        };
        HeapsTraslado cola = new HeapsTraslado();
        assertEquals(0, cola.length());
    }

    @Test 
    void crearHeapAPartirDeArrayOrdenado() {
        HeapsTraslado cola = new HeapsTraslado(listaTraslados);
        assertEquals(listaTraslados[6], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[5], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[4], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[3], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[2], cola.desencolarPorGanancia());
    }

    @Test
    void crearHeapAPartirDeArrayDesordenado() {
        listaTraslados = new Traslado[] {
                new Traslado(1, 0, 1, 1000, 10),
                new Traslado(2, 0, 1, 4000, 20),
                new Traslado(3, 3, 4, 50000, 50),
                new Traslado(4, 4, 3, 5000, 11),
                new Traslado(5, 1, 0, 3000, 40),
                new Traslado(6, 1, 0, 1500, 41),
                new Traslado(7, 6, 3, 2000, 42)
        };
        HeapsTraslado cola = new HeapsTraslado(listaTraslados);
        assertEquals(listaTraslados[2], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[3], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[1], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[4], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[6], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[5], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[0], cola.desencolarPorGanancia());

        cola = new HeapsTraslado(listaTraslados);
        assertEquals(listaTraslados[0], cola.desencolarPorTiempo());
        assertEquals(listaTraslados[3], cola.desencolarPorTiempo());
        assertEquals(listaTraslados[1], cola.desencolarPorTiempo());
    }

    @Test
    void encolarObjetosContiguos() {

    }

    @Test
    void encolarObjetosSinOrden() {

    }

    @Test
    void encolarObjectosTrasDescolar() {

    }

    @Test
    void desencolarTodosLosObjetos() {

    }

    @Test 
    void stress() {

    }
}
