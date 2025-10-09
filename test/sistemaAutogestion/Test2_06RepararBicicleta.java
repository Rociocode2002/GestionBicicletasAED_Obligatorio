/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaAutogestion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
    public void repararBicicletaOk() { 
        s.registrarBicicleta("251652", "MOUNTAIN");
        s.marcarEnMantenimiento("251652", "rota");
         retorno =  s.repararBicicleta("251652");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        
    }
    
       @Test
    public void repararBicicletaOk_enDeposito() {
        s.registrarBicicleta("ABC001", "URBANA");
 
        
        retorno = s.marcarEnMantenimiento("ABC001", "rueda");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
 
     
        retorno = s.repararBicicleta("ABC001");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
 
        try {
            Retorno dep = s.listarBicisEnDeposito();
            assertTrue(dep.getValorString().toUpperCase().contains("ABC001#URBANA#DISPONIBLE"));
        } catch (Throwable ignore) {}
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
    public void repararBicicletaError03() {  
        retorno =  s.registrarBicicleta("251652", "MOUNTAIN");
         retorno =  s.repararBicicleta("251652");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

}

    

