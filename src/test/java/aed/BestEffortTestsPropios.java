package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class BestEffortTestsPropios {

    Integer nTraslados = 100;
    Integer maximoGanancia = 30000;
    Integer maximoTiempo = 100000;

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

    void assertEqualsSameOrder(ArrayList<Integer> s1, ArrayList<Integer> s2) {
        assertEquals(s1.size(), s2.size());
        for(int i = 0; i < s1.size(); i++) {
            boolean encontrado = false;
            if (s1.get(i) == s2.get(i)) {
                encontrado = true;
            }
            assertTrue(encontrado, "No se encontró el elemento " +  s1.get(i) + " en el arreglo " + s2.toString());
        }
    }

    //Length > 0 como requiere
    int maximoSuperavit(Ciudad[] ciudades) {
        int res = ciudades[0].superavit();
        for(int i = 0; i < ciudades.length; i++) {
            if (res < ciudades[i].superavit()) {
                res = ciudades[i].superavit();
            }
        }
        return res;
    }

    @Test
    void sacarSoloRedituablesPuestosDesdeInicio() {
        int cantCiudades = 100;
        Ciudad[] arrayCiudades = new Ciudad[cantCiudades];
        for(int i = 0; i < arrayCiudades.length; i++) {
            Ciudad ciudadAAñadir = new Ciudad (i);    
            arrayCiudades[i] = ciudadAAñadir;
        }

        Traslado[] trasladosPrevios = {
            new Traslado(0, 1, 54, 800, 10),
            new Traslado(1, 4, 22, 8000, 12),
            new Traslado(2, 6, 2, 900, 105),
            new Traslado(3, 97, 42, 300, 30),
            new Traslado(4, 34, 25, 100, 50),
            new Traslado(5, 23, 27, 9000, 654),
            new Traslado(6, 3, 23, 6000, 1334)
        };

        BestEffort bestEffort = new BestEffort(cantCiudades, trasladosPrevios);

        assertEquals(0, bestEffort.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99
        )), bestEffort.ciudadesConMayorPerdida());

        assertSetEquals(new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99
        )), bestEffort.ciudadesConMayorGanancia());


        //Ahora vamos a encolar y despachar varios lementos

        assertEquals(Arrays.asList(5), Arrays.asList(bestEffort.despacharMasRedituables(1)[0]));
        
        arrayCiudades[23].aumentarGanancia(9000);
        arrayCiudades[27].aumnetarPerdida(9000);


        assertEquals(23, bestEffort.ciudadConMayorSuperavit());
        assertEquals(9000, bestEffort.gananciaPromedioPorTraslado());
        assertSetEquals(new ArrayList<>(Arrays.asList(23)), bestEffort.ciudadesConMayorGanancia());

        int[] listas = bestEffort.despacharMasRedituables(2);

        assertEquals(Arrays.asList(1, 6), Arrays.asList(listas[0], listas[1]));
        assertSetEquals(new ArrayList<>(Arrays.asList(23)), bestEffort.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(27)), bestEffort.ciudadesConMayorPerdida());
    }

    @Test
    void elOrigenDeLosTiempos() {
        int cantCiudades = 100;
        
        Traslado[] trasladosPrevios = {
            new Traslado(0, 1, 54, 800, 10),
            new Traslado(1, 4, 22, 5,2),
            new Traslado(2, 6, 2, 900, 6),
            new Traslado(3, 97, 42, 300, 1),
            new Traslado(4, 34, 25, 4, 0),
            new Traslado(5, 23, 27, 9000, 654),
            new Traslado(6, 3, 23, 6000, 1334)
        };

        BestEffort bestEffort = new BestEffort(cantCiudades, trasladosPrevios);

        int[] despachados = bestEffort.despacharMasAntiguos(2);
        assertSetEquals(new ArrayList<>(Arrays.asList(3, 4)), new ArrayList<>(Arrays.asList(despachados[0], despachados[1])));

    }

    @Test
    void todasGananciasIguales() {
        int cantCiudades = 100;
        
        Traslado[] trasladosPrevios = {
            new Traslado(87, 1, 54, 800, 10),
            new Traslado(1, 4, 22, 800,2),
            new Traslado(2, 6, 2, 800, 6),
            new Traslado(0, 97, 42, 800, 1),
            new Traslado(4, 34, 25, 800, 0),
            new Traslado(5, 23, 27, 800, 654),
            new Traslado(6, 3, 23, 800, 1334)
        };

        BestEffort bestEffort = new BestEffort(cantCiudades, trasladosPrevios);

        int[] despachados = bestEffort.despacharMasRedituables(2);
        assertSetEquals(new ArrayList<>(Arrays.asList(0, 1)), new ArrayList<>(Arrays.asList(despachados[0], despachados[1])));
        assertEqualsSameOrder(new ArrayList<>(Arrays.asList(97, 4)), bestEffort.ciudadesConMayorGanancia());
        assertEqualsSameOrder(new ArrayList<>(Arrays.asList(42, 22)), bestEffort.ciudadesConMayorPerdida());
    }

    @Test
    void sacarMasTrasladosDeLosQueHay() {
        int cantCiudades = 100;
        
        Traslado[] trasladosPrevios = {
            new Traslado(0, 1, 54, 800, 10),
            new Traslado(1, 4, 22, 5,2),
            new Traslado(2, 6, 2, 900, 6),
            new Traslado(3, 97, 42, 300, 1),
            new Traslado(4, 34, 25, 4, 0),
            new Traslado(5, 23, 27, 9000, 654),
            new Traslado(6, 3, 23, 6000, 1334)
        };

        BestEffort bestEffort = new BestEffort(cantCiudades, trasladosPrevios);
        int[] despachados = bestEffort.despacharMasRedituables(100);
        assertTrue(despachados.length == 7);
        assertSetEquals(new ArrayList<>(Arrays.asList(0, 1,2,3,4,5, 6)), new ArrayList<>(Arrays.asList(despachados[0], despachados[1], despachados[2], despachados[3], despachados[4], despachados[5], despachados[6])));
        assertEquals(3, bestEffort.ciudadConMayorSuperavit());

        assertEquals(new ArrayList<>(Arrays.asList(23)), bestEffort.ciudadesConMayorGanancia());
        assertEquals(new ArrayList<>(Arrays.asList(27)), bestEffort.ciudadesConMayorPerdida());
    }
    
    @Test
    void sumasEnCiudadesEsCorrecto() {
        int cantCiudades = 100;
        Traslado[] trasladosPrevios = {
            new Traslado(0, 1, 54, 800, 10),
            new Traslado(1, 4, 22, 5,2),
            new Traslado(2, 6, 2, 900, 6),
            new Traslado(3, 97, 42, 300, 1),
            new Traslado(4, 34, 25, 4, 0),
            new Traslado(5, 23, 27, 9000, 654),
            new Traslado(6, 3, 23, 6000, 1334)
        };

        BestEffort bestEffort = new BestEffort(cantCiudades, trasladosPrevios);

        assertSetEquals(new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99
        )), bestEffort.ciudadesConMayorGanancia());


        Traslado[] traslados = {
            new Traslado(10,5,10,10000,5),
            new Traslado(11,5,10,10000,1000),
            new Traslado(12,5,10,10000,7),
            new Traslado(13,5,10,10000,8),
            new Traslado(14,8,12,10000, 9),
            new Traslado(15,8,12,10000,1032),
            new Traslado(16,8,12,10000,11),
            new Traslado(17,8,12,10000,1033)
        };

        assertSetEquals(new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99
        )), bestEffort.ciudadesConMayorGanancia());
        
        bestEffort.registrarTraslados(traslados);

        bestEffort.despacharMasRedituables(4);
        assertEquals(5, bestEffort.ciudadConMayorSuperavit());
        assertEqualsSameOrder(new ArrayList<>(Arrays.asList(5)), bestEffort.ciudadesConMayorGanancia());

        bestEffort.despacharMasRedituables(4);
        assertEquals(5, bestEffort.ciudadConMayorSuperavit());
        assertEqualsSameOrder(new ArrayList<>(Arrays.asList(5,8)), bestEffort.ciudadesConMayorGanancia());

        assertEquals(10000, bestEffort.gananciaPromedioPorTraslado());
        
    }

    @Test
    void despacharTodosTrasladosAntesDeRegistrarNuevosTraslados() {
        int cantCiudades = 100;
        
        Traslado[] trasladosPrevios = {
            new Traslado(0, 1, 54, 800, 10),
            new Traslado(1, 4, 22, 5,2),
            new Traslado(2, 6, 2, 900, 6),
            new Traslado(3, 97, 42, 300, 1),
            new Traslado(4, 34, 25, 4, 0),
            new Traslado(5, 63, 29, 9000, 654),
            new Traslado(6, 3, 23, 6000, 1334)
        };

        BestEffort bestEffort = new BestEffort(cantCiudades, trasladosPrevios);

        bestEffort.despacharMasAntiguos(1213131);

        Traslado[] traslados = {
            new Traslado(10,5,10,10000,5),
            new Traslado(11,64,20,12000,1000),
            new Traslado(12,4,12,13400,7),
            new Traslado(13,5,30,15600,8),
            new Traslado(14,65,42,120, 9),
            new Traslado(15,3,64,130,1032),
            new Traslado(16,28,65,140,11),
            new Traslado(17,86,42,1420,1033)
        };

        bestEffort.registrarTraslados(traslados);
        assertEquals(63, bestEffort.ciudadConMayorSuperavit());
        assertEqualsSameOrder(new ArrayList<>(Arrays.asList(63)), bestEffort.ciudadesConMayorGanancia());
        assertEqualsSameOrder(new ArrayList<>(Arrays.asList(29)), bestEffort.ciudadesConMayorPerdida());

        assertEquals(2429, bestEffort.gananciaPromedioPorTraslado());

        bestEffort.despacharMasRedituables(2);
        assertEquals(5, bestEffort.ciudadConMayorSuperavit());
        assertEqualsSameOrder(new ArrayList<>(Arrays.asList(5)), bestEffort.ciudadesConMayorGanancia());
        assertEqualsSameOrder(new ArrayList<>(Arrays.asList(30)), bestEffort.ciudadesConMayorPerdida());
    }
}