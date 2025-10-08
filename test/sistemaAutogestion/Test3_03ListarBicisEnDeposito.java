/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaAutogestion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author rocio
 */
public class Test3_03ListarBicisEnDeposito {
        private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void listarBicisDepositoVacio() {
        retorno = s.listarUsuarios();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    public void listarBicisDepositoSoloUnaBici() {
        s.registrarBicicleta("357537", "MOUNTAIN");
        retorno = s.listarBicisEnDeposito();
       
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("357537#MOUNTAIN#Disponible", retorno.getValorString());
    }
    
       @Test
    public void variasBicis_OrdenIngreso() {
        s.registrarBicicleta("ABC001", "URBANA");
        s.registrarBicicleta("ABC002", "MOUNTAIN");
        s.registrarBicicleta("ABC003", "ELECTRICA");

        retorno = s.listarBicisEnDeposito();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(
            "ABC001#URBANA#Disponible|ABC002#MOUNTAIN#Disponible|ABC003#ELECTRICA#Disponible",
            retorno.getValorString()
        );
    }
   @Test
    public void estadoMantenimiento_ApareceComoMantenimiento() {
        s.registrarBicicleta("ABC001", "URBANA");
        s.marcarEnMantenimiento("ABC001", "rueda");
        retorno = s.listarBicisEnDeposito();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertTrue(retorno.getValorString().contains("ABC001#URBANA#Mantenimiento"));
    }
    
     @Test
    public void recursividad_NoAgregaSeparadorFinal() {
        s.registrarBicicleta("A00001", "URBANA");
        s.registrarBicicleta("A00002", "URBANA");
        String sdep = s.listarBicisEnDeposito().getValorString();
        assertFalse("No debe terminar con '|'", sdep.endsWith("|"));
        assertTrue("Debe tener separador entre elementos", sdep.contains("|"));
    }
   
}


    


