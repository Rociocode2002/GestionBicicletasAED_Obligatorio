package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test2_05PonerBicicletaMantenimiento {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }
  


    @Test
    public void marcarEnMantenimientoOK() {
        retorno = s.registrarBicicleta("555655", "MOUNTAIN");
        retorno = s.marcarEnMantenimiento("555655", "rota");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
       
    }

  
    
    @Test
    public void marcarEnMantenimientoError01() {
        s.registrarBicicleta("ABC001", "URBANA");

        retorno = s.marcarEnMantenimiento(null, "motivo");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento("ABC001", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento("", "motivo");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento("   ", "motivo");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento("ABC001", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.marcarEnMantenimiento("ABC001", "   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void marcarEnMantenimientoError02() {
        
        retorno = s.marcarEnMantenimiento("123123222", "rota");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
    
    /*Segun lo hablado en clase, 
    El error 3 no lo podemos testear porque aun no tenemos implementado un metodo para 
    Alquilar bicicletas
    @Test
    public void marcarEnMantenimientoError03() { 
          //retorno = s.registrarBicicleta("123123", "MOUNTAIN");
        retorno = s.marcarEnMantenimiento("123123", "rota");
         retorno = s.marcarEnMantenimiento("123123", "rota");
            assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        
        
    } */

    
    @Test
    public void marcarEnMantenimientoError04() {
        retorno = s.registrarBicicleta("123123", "MOUNTAIN");
        retorno = s.marcarEnMantenimiento("123123", "rota");
        retorno = s.marcarEnMantenimiento("123123", "rota");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    };
    

}
