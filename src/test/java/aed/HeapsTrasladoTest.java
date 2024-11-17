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
        listaTraslados = new Traslado[] {
            new Traslado(1, 0, 1, 100, 10),
            new Traslado(2, 0, 1, 400, 20),
            new Traslado(3, 3, 4, 500, 50),
            new Traslado(4, 4, 3, 500, 11),
            new Traslado(5, 1, 0, 1000, 40),
            new Traslado(6, 1, 0, 1500, 41),
            new Traslado(7, 6, 3, 2000, 42)
    };

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
        assertEquals(listaTraslados[2], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[3], cola.desencolarPorGanancia());
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
    void crearHeapAPartirDeTrasladosConMismoTiempo() {
        listaTraslados = new Traslado[] {
                new Traslado(1, 0, 1, 1000, 10),
                new Traslado(2, 0, 1, 1000, 20),
                new Traslado(3, 3, 4, 1000, 50),
                new Traslado(4, 4, 3, 1000, 11),
                new Traslado(5, 1, 0, 1000, 40),
                new Traslado(6, 1, 0, 1000, 41),
                new Traslado(7, 6, 3, 1000, 42)
        };
        HeapsTraslado cola = new HeapsTraslado(listaTraslados);
        assertEquals(listaTraslados[0], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[1], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[2], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[3], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[4], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[5], cola.desencolarPorGanancia());
        assertEquals(listaTraslados[6], cola.desencolarPorGanancia());
    }

    

    @Test
    void encolarObjetosContiguos() {
        Traslado[] trasladosListas = new Traslado[] {
                    new Traslado(1, 0, 1, 1000, 10),
                    new Traslado(2, 0, 1, 1000, 20),
                    new Traslado(3, 3, 4, 1000, 50),
                    new Traslado(4, 4, 3, 1000, 11),
                    new Traslado(5, 1, 0, 1000, 40),
                    new Traslado(6, 1, 0, 1000, 41),
                    new Traslado(7, 6, 3, 1000, 42)
            };
            HeapsTraslado cola = new HeapsTraslado();
            cola.encolar(trasladosListas[0]);
            cola.encolar(trasladosListas[1]);
            cola.encolar(trasladosListas[2]);
            cola.encolar(trasladosListas[3]);
            cola.encolar(trasladosListas[4]);
            cola.encolar(trasladosListas[5]);
            cola.encolar(trasladosListas[6]);

            int index = 0 ;
            while (index != trasladosListas.length) {
                Traslado trasladoActual = cola.desencolarPorGanancia() ;
                assertEquals(trasladosListas[index].id(), trasladoActual.id());
                index ++;
            }             
    }

    @Test
    void encolarObjetosSinOrden() {
        Traslado[] trasladosListas = new Traslado[] {
            new Traslado(1, 0, 1, 1000, 10),
            new Traslado(2, 0, 1, 1000, 20),
            new Traslado(3, 3, 4, 1000, 50),
            new Traslado(4, 4, 3, 1000, 11),
            new Traslado(5, 1, 0, 1000, 40),
            new Traslado(6, 1, 0, 1000, 41),
            new Traslado(7, 6, 3, 1000, 42)
        };
        HeapsTraslado cola = new HeapsTraslado();
            cola.encolar(trasladosListas[3]);
            cola.encolar(trasladosListas[6]);
            cola.encolar(trasladosListas[4]);
            cola.encolar(trasladosListas[1]);
            cola.encolar(trasladosListas[0]);
            cola.encolar(trasladosListas[2]);
            cola.encolar(trasladosListas[5]);
            
            int index = 0 ;
            while (index != trasladosListas.length) {
                Traslado trasladoActual = cola.desencolarPorGanancia() ;
                assertEquals(trasladosListas[index].id(), trasladoActual.id());
                index ++;
            }
    }

    @Test
    void encolarObjectosTrasDescolar() {
        Traslado[] listaTraslados = new Traslado[] {
            new Traslado(1, 0, 1, 1000, 10),
            new Traslado(2, 0, 1, 1000, 20),
            new Traslado(3, 3, 4, 1000, 50),
            new Traslado(4, 4, 3, 1000, 11),
            new Traslado(5, 1, 0, 1000, 40),
            new Traslado(6, 1, 0, 1000, 41),
            new Traslado(7, 6, 3, 1000, 42)
        };
        HeapsTraslado cola = new HeapsTraslado(listaTraslados);
        cola.desencolarPorGanancia();
        cola.desencolarPorGanancia();
        cola.desencolarPorGanancia();
        cola.desencolarPorGanancia();
        cola.encolar(listaTraslados[0]);
        cola.encolar(listaTraslados[1]);
        cola.encolar(listaTraslados[2]);
        cola.encolar(listaTraslados[3]);

        int index = 0 ;
            while (index != listaTraslados.length) {
                Traslado trasladoActual = cola.desencolarPorGanancia() ;
                assertEquals(listaTraslados[index].id(), trasladoActual.id());
                index ++;
            }
        
        cola = new HeapsTraslado(listaTraslados);
        cola.desencolarPorTiempo();
        cola.desencolarPorTiempo();
        cola.desencolarPorTiempo();
        cola.desencolarPorTiempo();
        cola.encolar(listaTraslados[3]);
        cola.encolar(listaTraslados[1]);
        cola.encolar(listaTraslados[2]);
        cola.encolar(listaTraslados[0]);
        
        cola = new HeapsTraslado(listaTraslados);
        index = 0 ;
            while (index != listaTraslados.length) {
                Traslado trasladoActual = cola.desencolarPorGanancia() ;
                assertEquals(listaTraslados[index].id(), trasladoActual.id());
                index ++;
            }
    }

    @Test
    void desencolarTodosLosObjetos() {
        Traslado[] listaTraslados = new Traslado[] {
            new Traslado(1, 0, 1, 1000, 10),
            new Traslado(2, 0, 1, 1000, 20),
            new Traslado(3, 3, 4, 1000, 50),
            new Traslado(4, 4, 3, 1000, 11),
            new Traslado(5, 1, 0, 1000, 40),
            new Traslado(6, 1, 0, 1000, 41),
            new Traslado(7, 6, 3, 1000, 42)
        };

        HeapsTraslado cola = new HeapsTraslado(listaTraslados);
        int index = 0 ;
            while (index != listaTraslados.length) {
                cola.desencolarPorGanancia() ;
                index ++;
            }
        assertTrue (cola.length() == 0) ;

        cola.encolar(listaTraslados[5]) ;
        assertTrue (cola.length() == 1) ;
        assertEquals(listaTraslados[5].id(), cola.desencolarPorGanancia().id() );
        assertTrue (cola.length() == 0) ;
    }
}
