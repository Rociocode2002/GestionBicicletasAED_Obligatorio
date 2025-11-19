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
    


  
   
   
    @Test
    public void ok_EstacionSinBicis_RetornaVacio() {
        s.registrarEstacion("E1", "Centro", 5);
        Retorno r = s.listarBicicletasDeEstacion("E1");
        assertEquals(Retorno.Resultado.OK, r.getResultado());
        assertEquals("", r.getValorString());
    }

 
    @Test
    public void ok_UnaBici_UnicoCodigo() {
        s.registrarEstacion("E1", "Centro", 3);
        s.registrarBicicleta("A00002", "URBANA");
        s.asignarBicicletaAEstacion("A00002", "E1");

        Retorno r = s.listarBicicletasDeEstacion("E1");
        assertEquals(Retorno.Resultado.OK, r.getResultado());
        assertEquals("A00002", r.getValorString());
    }


    @Test
    public void ok_VariasBicis_OrdenadasPorCodigo() {
        s.registrarEstacion("E1", "Centro", 6);

        // Registrar en depósito
        s.registrarBicicleta("UYT123", "URBANA");
        s.registrarBicicleta("AER345", "URBANA");
        s.registrarBicicleta("UTR112", "MOUNTAIN");
        s.registrarBicicleta("B00001", "ELECTRICA");

        // Asignar en orden desordenado
        s.asignarBicicletaAEstacion("UYT123", "E1");
        s.asignarBicicletaAEstacion("AER345", "E1");
        s.asignarBicicletaAEstacion("UTR112", "E1");
        s.asignarBicicletaAEstacion("B00001", "E1");

        Retorno r = s.listarBicicletasDeEstacion("E1");
        assertEquals(Retorno.Resultado.OK, r.getResultado());
        // Esperado lexicográficamente: AER345 | B00001 | UTR112 | UYT123
        assertEquals("AER345|B00001|UTR112|UYT123", r.getValorString());
    }

    // OK: el nombre con espacios a los lados se normaliza (trim)
    @Test
    public void ok_TrimEnNombreDeEstacion() {
        s.registrarEstacion("E1", "Centro", 1);
        s.registrarBicicleta("ABC999", "URBANA");
        s.asignarBicicletaAEstacion("ABC999", "E1");

        Retorno r = s.listarBicicletasDeEstacion("  E1  ");
        assertEquals(Retorno.Resultado.OK, r.getResultado());
        assertEquals("ABC999", r.getValorString());
    }

   
    @Test
    public void ok_CapacidadLlena_ListadoCompleto() {
        s.registrarEstacion("E1", "Centro", 5);
        String[] cods = {"Z00005","A00001","M00003","B00002","K00004"}; 

        for (String c : cods) s.registrarBicicleta(c, "URBANA");
        for (String c : cods) s.asignarBicicletaAEstacion(c, "E1");

        Retorno r = s.listarBicicletasDeEstacion("E1");
        assertEquals(Retorno.Resultado.OK, r.getResultado());
        assertEquals("A00001|B00002|K00004|M00003|Z00005", r.getValorString());
        // Validar separadores: no termina ni empieza con '|'
        assertFalse(r.getValorString().startsWith("|"));
        assertFalse(r.getValorString().endsWith("|"));
    }


      
    
    
    
}
