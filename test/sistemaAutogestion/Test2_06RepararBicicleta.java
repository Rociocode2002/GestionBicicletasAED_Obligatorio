package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test2_06RepararBicicleta {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void repararBicicletaOk() {
        s.registrarBicicleta("2516521", "MOUNTAIN");
        s.marcarEnMantenimiento("2516521", "rota");
         retorno =  s.repararBicicleta("2516521");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        
    }

    @Test
    public void repararBicicletaError01() {
         s.registrarBicicleta("2516521", "MOUNTAIN");
        s.marcarEnMantenimiento("2516521", "rota");
         retorno =  s.repararBicicleta("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void repararBicicletaError02() {
        //Completar
    }

    @Test
    public void repararBicicletaError03() {
        //Completar
    }

}
