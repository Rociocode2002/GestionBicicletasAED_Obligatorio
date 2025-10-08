/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaAutogestion;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author rocio
 */

   
public class Test2_06RepararBicicleta {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void repararBicicletaOk() { // NO FUNCIONA
        s.registrarBicicleta("251652", "MOUNTAIN");
        s.marcarEnMantenimiento("251652", "rota");
         retorno =  s.repararBicicleta("251652");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        
    }

    @Test
    public void repararBicicletaError01() {
         s.registrarBicicleta("251652", "MOUNTAIN");
        s.marcarEnMantenimiento("251652", "rota");
         retorno =  s.repararBicicleta("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void repararBicicletaError02() { 
             s.registrarBicicleta("251652", "MOUNTAIN");
        s.marcarEnMantenimiento("251652", "rota");
         retorno =  s.repararBicicleta("736860");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        
    }

    @Test
    public void repararBicicletaError03() {  //NO FUNCIONA
        retorno =  s.registrarBicicleta("251652", "MOUNTAIN");
         retorno =  s.repararBicicleta("251652");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

}

    

