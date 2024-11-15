package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class CiudadesTests {

    int cantCiudades;
    Traslado[] listaTraslados;
    ArrayList<Integer> actual;





    @BeforeEach
    void init(){
        //Reiniciamos los valores de las ciudades y traslados antes de cada test
        cantCiudades = 7;
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
        ciudad.despacharTraslados(new Traslado(0, 7, 8, 100, 20));

        assertEquals(4, ciudad.mayorSuperavit().nombre());
    }


    Integer NCLAVES = 1000;
    
    private int[] generateTwoNumbes(int n) {
        int numero = (int) (Math.random() * n);
        int numero2 = (int) (Math.random() * n);
        return new int[] {numero, numero2};
    }

    @Test
    void stress() {
        int cantCiudades = 100;

        Ciudad[] arrayCiudades = new Ciudad[cantCiudades];
        for(int i = 0; i < cantCiudades; i++) {
            arrayCiudades[i] = new Ciudad(i);
        }
    }
}
