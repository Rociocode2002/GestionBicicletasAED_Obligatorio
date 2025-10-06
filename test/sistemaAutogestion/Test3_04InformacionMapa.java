package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_04InformacionMapa {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void informacionMapaOk() {
        String[][] mapa1 = {
            {"0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "E3", "0", "0"},
            {"0", "0", "0", "0", "0", "0"},
            {"E1", "0", "0", "0", "E5", "0"},
            {"0", "0", "0", "0", "0", "0"},
            {"0", "0", "E2", "0", "E6", "0"},
            {"0", "0", "0", "0", "E7", "0"},
            {"0", "0", "0", "E4", "0", "0"}
        };
        
        String[][] mapa2 = {
            {"0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "E3", "0", "0"},
            {"0", "0", "0", "0", "0", "0"},
            {"E1", "0", "0", "0", "E5", "0"},
            {"0", "0", "0", "0", "0", "0"},
            {"0", "0", "E2", "0", "E6", "0"},
            {"0", "0", "0", "0", "0", "0"},
            {"0", "0", "0", "E4", "0", "0"}
        };
        
        retorno = s.informaciónMapa(mapa1);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        
        retorno = s.informaciónMapa(mapa2);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
}
