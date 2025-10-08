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
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());// NO FUNCIONA
       
    }

    @Test
    public void marcarEnMantenimientoError01() {

        retorno = s.registrarBicicleta("123123", "MOUNTAIN");
        retorno = s.marcarEnMantenimiento("", "rota");
        retorno = s.marcarEnMantenimiento(null, "rota");
        retorno = s.marcarEnMantenimiento("1221212", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

    }

    @Test
    public void marcarEnMantenimientoError02() {
        retorno = s.registrarBicicleta("12993123", "MOUNTAIN");
        retorno = s.marcarEnMantenimiento("123123222", "rota");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void marcarEnMantenimientoError03() {
          //retorno = s.registrarBicicleta("123123", "MOUNTAIN");
        retorno = s.marcarEnMantenimiento("123123", "rota");
         retorno = s.marcarEnMantenimiento("123123", "rota");
            assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        
        
    }
    
    @Test
    public void marcarEnMantenimientoError04() {
        retorno = s.marcarEnMantenimiento("123123", "rota");
        retorno = s.marcarEnMantenimiento("123123", "rota");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    
    };

}
