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
    void raiz_cambia_por_hijo_derecho(){

        Traslado t1= new Traslado(1,3,1,1000,1);


        BestEffort sis = new BestEffort(3, this.listaTraslados);
        
       // assertEquals(ciudadConMayorSuperavit(sis),1);

        sis.despacharMasRedituables(1);

        // assertEquals(ciudadConMayorSuperavit(sis),3);
        }
    
    @Test
    void despachar_con_mas_ganancia_de_a_varios(){

        BestEffort sis = new BestEffort(7, this.listaTraslados);
        //Tenemos 7 ciudades organizadas de 1 a 7, hacemos traslados para ordenarlas de 7 a 1 y luego las desencolamos todas
        Traslado t1= new Traslado(1,7,1,100000,1);
        Traslado t2= new Traslado(1,6,2,10000,1);
        Traslado t3= new Traslado(1,5,3,1000,1);
        sis.despacharMasRedituables(3);

        // assertEquals(ciudadConMayorSuperavit(sis),7);
        


     

    }
    
    
}
