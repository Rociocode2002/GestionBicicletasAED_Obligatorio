/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author rocio
 */
public class Test3_05ListarBicisEstacion {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }
 
     @Test
    public void listarBicicletasDeEstacionOk_EstacionConBicicletas() {
        // Configuración
        s.registrarEstacion("EstacionCentro", "Centro", 5);
        s.registrarBicicleta("BI3003", "URBANA");
        s.registrarBicicleta("BI1001", "MOUNTAIN");
        s.registrarBicicleta("BI2002", "ELECTRICA");
        
        s.asignarBicicletaAEstacion("BI3003", "EstacionCentro");
        s.asignarBicicletaAEstacion("BI1001", "EstacionCentro");
        s.asignarBicicletaAEstacion("BI2002", "EstacionCentro");
        
       
        Retorno retorno = s.listarBicicletasDeEstacion("EstacionCentro");
        
        
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("BI1001|BI2002|BI3003", retorno.getValorString());
    }
     @Test
    public void listarBicicletasDeEstacionOk_OrdenCorrecto() {
        s.registrarEstacion("EstacionOrden", "Centro", 3);
        
        // Registrar en orden desordenado
        s.registrarBicicleta("BI3003", "URBANA");
        s.registrarBicicleta("BI1001", "URBANA");
        s.registrarBicicleta("BI2002", "URBANA");
        
        s.asignarBicicletaAEstacion("BI3003", "EstacionOrden");
        s.asignarBicicletaAEstacion("BI1001", "EstacionOrden");
        s.asignarBicicletaAEstacion("BI2002", "EstacionOrden");
        
        Retorno retorno = s.listarBicicletasDeEstacion("EstacionOrden");
        
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("BI1001|BI2002|BI3003", retorno.getValorString());
    }
     @Test
    public void listarBicicletasDeEstacionOk_DosBicicletas() {
     
        s.registrarEstacion("EstacionDos", "Centro", 2);
        s.registrarBicicleta("BI1001", "URBANA");
        s.registrarBicicleta("BI2002", "URBANA");
        
        s.asignarBicicletaAEstacion("BI1001", "EstacionDos");
        s.asignarBicicletaAEstacion("BI2002", "EstacionDos");
        
        Retorno retorno = s.listarBicicletasDeEstacion("EstacionDos");
        
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        
        String resultado = retorno.getValorString();
        

      
        assertEquals("BI1001|BI2002", resultado);
    }
      @Test
    public void listarBicicletasDeEstacionOk_EstacionVacia() {
        s.registrarEstacion("EstacionVacia", "Centro", 3);
        
        Retorno retorno = s.listarBicicletasDeEstacion("EstacionVacia");
        
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

      
    
    
    
}
