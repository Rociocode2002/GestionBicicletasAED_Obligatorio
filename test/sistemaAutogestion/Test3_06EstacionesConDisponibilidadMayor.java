
package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class Test3_06EstacionesConDisponibilidadMayor {
    
     private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }
    
       @Test
    public void estacionesConDisponibilidadOk_Normal() {
    
        s.registrarEstacion("EstacionA", "Centro", 5);
        s.registrarEstacion("EstacionB", "Cordón", 3);
        s.registrarEstacion("EstacionC", "Pocitos", 10);
        
      
        s.registrarBicicleta("BIC001", "URBANA");
        s.registrarBicicleta("BIC002", "URBANA");
        s.registrarBicicleta("BIC003", "URBANA");
        s.registrarBicicleta("BIC004", "URBANA");
        s.registrarBicicleta("BIC005", "URBANA");
        s.registrarBicicleta("BIC006", "URBANA");
        s.registrarBicicleta("BIC007", "URBANA");
        
        // EstacionA: 3 bicicletas (de 5)
        s.asignarBicicletaAEstacion("BIC001", "EstacionA");
        s.asignarBicicletaAEstacion("BIC002", "EstacionA");
        s.asignarBicicletaAEstacion("BIC003", "EstacionA");
        
        // EstacionB: 2 bicicletas (de 3)
        s.asignarBicicletaAEstacion("BIC004", "EstacionB");
        s.asignarBicicletaAEstacion("BIC005", "EstacionB");
        
        // EstacionC: 2 bicicletas (de 10)
        s.asignarBicicletaAEstacion("BIC006", "EstacionC");
        s.asignarBicicletaAEstacion("BIC007", "EstacionC");
        
        
        //Retorno retorno1 = s.estacionesConDisponibilidad(1);
        //assertEquals(Retorno.Resultado.OK, retorno1.getResultado());
        //assertEquals(3, retorno1.getValorEntero());
        
     
        Retorno retorno2 = s.estacionesConDisponibilidad(2);
        assertEquals(Retorno.Resultado.OK, retorno2.getResultado());
        assertEquals(1, retorno2.getValorEntero());
        
      
        Retorno retorno3 = s.estacionesConDisponibilidad(3);
        assertEquals(Retorno.Resultado.OK, retorno3.getResultado());
        assertEquals(0, retorno3.getValorEntero());
    }
    
     @Test
    public void estacionesConDisponibilidadError_NMenorIgual1() {
        // n <= 1 debería dar ERROR
        Retorno retorno1 = s.estacionesConDisponibilidad(1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno1.getResultado());
        
        Retorno retorno0 = s.estacionesConDisponibilidad(0);
        assertEquals(Retorno.Resultado.ERROR_1, retorno0.getResultado());
        
        Retorno retornoNegativo = s.estacionesConDisponibilidad(-5);
        assertEquals(Retorno.Resultado.ERROR_1, retornoNegativo.getResultado());
    }
     @Test
    public void estacionesConDisponibilidadOk_SinEstaciones() {
        // Sin estaciones registradas
        Retorno retorno = s.estacionesConDisponibilidad(2);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(0, retorno.getValorEntero());
    }

    @Test
    public void estacionesConDisponibilidadOk_EstacionesVacias() {
        // Estaciones sin bicicletas
        s.registrarEstacion("EstacionA", "Centro", 5);
        s.registrarEstacion("EstacionB", "Cordón", 3);
        
        Retorno retorno = s.estacionesConDisponibilidad(2);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(0, retorno.getValorEntero());
        
    }

    
    
    
}
