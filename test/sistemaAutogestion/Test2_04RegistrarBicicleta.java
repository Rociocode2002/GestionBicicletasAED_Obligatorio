package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class Test2_04RegistrarBicicleta {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void registrarBicicletaOk() {
        retorno = s.registrarBicicleta("123123", "MOUNTAIN");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
    
    @Test
    public void registrarBicicletaOk_Alfanumerico() {
        retorno = s.registrarBicicleta("BIC001", "URBANA");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void registrarBicicletaOk_ConEspaciosYCaseInsensitive() {
        
        retorno = s.registrarBicicleta("  ABC123  ", "mountain");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void registrarBicicletaOk_casosBorde() {
        retorno = s.registrarBicicleta("000000", "ELECTRICA");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarBicicleta("ZZZZZZ", "URBANA");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
    
    @Test
    public void registrarBicicletaError01() {
        retorno = s.registrarBicicleta("", "MOUNTAIN");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        
        retorno = s.registrarBicicleta("   ", "ELECTRICA");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarBicicleta("123456", "  ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarBicicleta(null, "URBANA");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarBicicleta("123123", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarBicicleta(null, "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void registrarBicicletaError02() {
        retorno = s.registrarBicicleta("123", "MOUNTAIN");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarBicicleta("1234567", "URBANA");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
    
    @Test
    public void registrarBicicletaError02_conGuionesYEspacios() {
        retorno = s.registrarBicicleta("AB_123", "URBANA");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarBicicleta("AB-123", "URBANA");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarBicicleta("AB 123", "URBANA");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void registrarBicicletaError02_NoASCII() {
        
        retorno = s.registrarBicicleta("AB١٢٣٤", "URBANA");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarBicicleta("ＡＢ１２３", "URBANA");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarBicicleta("ÁB1234", "URBANA");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
    
    /* consultado a la profe si es necesaria
    @Test
    public void error2_MinusculasNoPermitidas() {
        // Como NO conviertes a mayúsculas antes de validar, debe fallar
        retorno = s.registrarBicicleta("ab123c", "URBANA");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }*/
    
    @Test
    public void registrarBicicletaError03() {
        retorno = s.registrarBicicleta("123123", "montania");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        
        retorno = s.registrarBicicleta("123456", "plegable");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        
        retorno = s.registrarBicicleta("ABC123", "HÍBRIDA");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.registrarBicicleta("ABC124", "E-BIKE");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        
        retorno = s.registrarBicicleta("ABC125", "URBANA!"); 
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    public void registrarBicicletaError04() {
        s.registrarBicicleta("123123", "URBANA");
        retorno = s.registrarBicicleta("123123", "URBANA");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

    }

}

