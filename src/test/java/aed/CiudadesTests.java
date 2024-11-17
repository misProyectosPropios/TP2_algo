package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class CiudadesTests {

    int cantCiudades = 7;
    Traslado[] listaTraslados;
    ArrayList<Integer> actual;





    @BeforeEach
    void init(){
        //Reiniciamos los valores de las ciudades y traslados antes de cada test
       Ciudad[] listaCiudades = new Ciudad[] {
                                            new Ciudad(1),
                                            new Ciudad(2),
                                            new Ciudad(3),
                                            new Ciudad(4),
                                            new Ciudad(5),
                                            new Ciudad(6),
                                            new Ciudad(7)
                                        };
    }

    void assertSetEquals(ArrayList<Integer> s1, ArrayList<Integer> s2) {
        assertEquals(s1.size(), s2.size());
        for (int e1 : s1) {
            boolean encontrado = false;
            for (int e2 : s2) {
                if (e1 == e2) encontrado = true;
            }
            assertTrue(encontrado, "No se encontró el elemento " +  e1 + " en el arreglo " + s2.toString());
        }
    }
    
    @Test 
    void sinCambios() {
        int cantCiudades = 10;
        HeapCiudad ciudad = new HeapCiudad(cantCiudades);

        assertEquals(0, ciudad.mayorSuperavit().nombre());
        
        for(int i = 0; i < cantCiudades; i++) {
            assertEquals(0, ciudad.getGanancia(i));
            assertEquals(0, ciudad.getPerdida(i));
        }
    }

    @Test
    void unSoloTraslado() {
        int cantCiudades = 10;
        HeapCiudad ciudad = new HeapCiudad(cantCiudades);
        Traslado traslado = new Traslado(1, 0, 1, 1000, 10);
        ciudad.despacharTraslados(traslado);

        assertEquals(0, ciudad.mayorSuperavit().nombre());
    }

    @Test
    void variosTraslados() {
        int cantCiudades = 10;
        HeapCiudad ciudad = new HeapCiudad(cantCiudades);
        Traslado traslado = new Traslado(1, 0, 1, 1000, 10);
        ciudad.despacharTraslados(traslado);

        assertEquals(0, ciudad.mayorSuperavit().nombre());
    }

    @Test
    void ciudadesCadaUnaDespachadaConElSiguiente(){
        int cantCiudades = 10;
        HeapCiudad ciudad = new HeapCiudad(cantCiudades);
        for(int i = 0; i < cantCiudades - 1; i++) {
            Traslado traslado = new Traslado(i, i, i + 1, 1000, 10);
            ciudad.despacharTraslados(traslado);
        }
        assertEquals(0, ciudad.mayorSuperavit().nombre());
    }

    @Test
    void ciudadesConMismoSuperavitSeparadasPorID() {
        int cantCiudades = 10;
        HeapCiudad ciudad = new HeapCiudad(cantCiudades);

        ciudad.despacharTraslados(new Traslado(0, 4, 5, 100, 20));
        ciudad.despacharTraslados(new Traslado(1, 7, 8, 100, 20));

        assertEquals(4, ciudad.mayorSuperavit().nombre());
    }

    @Test
    void ciudadesDespachandoMuchosTraslados() {
        int cantCiudades = 10;
        HeapCiudad ciudad = new HeapCiudad(cantCiudades);
        ciudad.despacharTraslados(new Traslado(0, 1, 2, 90, 19));
        ciudad.despacharTraslados(new Traslado(1, 3, 1, 300, 18));
        ciudad.despacharTraslados(new Traslado(2, 5, 0, 400, 13));
        ciudad.despacharTraslados(new Traslado(3, 3, 6, 540, 32));
        ciudad.despacharTraslados(new Traslado(4, 2, 5, 10, 23));

        assertEquals(3, ciudad.mayorSuperavit().nombre());
        assertEquals(0, ciudad.getGanancia(0));
        assertEquals(400, ciudad.getPerdida(0));

    }
    
}
