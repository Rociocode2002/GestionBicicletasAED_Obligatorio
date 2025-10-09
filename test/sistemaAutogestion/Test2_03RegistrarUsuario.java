
package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class Test2_03RegistrarUsuario {
     private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void registrarUsuarioOk() {
        retorno = s.registrarUsuario("12345454", "Jose");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
    
    @Test
    public void registrarUsuarioOk_empezandoConCero() {
        retorno = s.registrarUsuario("01234567", "Ana");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
    
    @Test
    public void registrarUsuarioOk_casosBordes() {
        
        retorno = s.registrarUsuario("00000000", "Cero");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarUsuario("99999999", "Max");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
    
    @Test
    public void registrarUsuarioOk_conEspacios() {
        retorno = s.registrarUsuario(" 12345678 ", "  Juan Perez  ");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void registrarUsuarioError01() {
        retorno  = s.registrarUsuario("56565656", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.registrarUsuario(null, "Jose");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.registrarUsuario( "98923945" ,null );
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = s.registrarUsuario("", "maria");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
     
    }

    @Test
    public void registrarUsuarioError02() {
       retorno  = s.registrarUsuario("5656565", "Maria");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno  = s.registrarUsuario("565656522", "Jose");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
    
    @Test
    public void registrarUsuarioError02_conCaracteresEspeciales() {
        retorno = s.registrarUsuario("12A45678", "User");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarUsuario("12-45678", "User");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarUsuario("1234 5678", "User");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
    
    @Test
    public void registrarUsuarioError02_NoASCII() {
        
        retorno = s.registrarUsuario("١٢٣٤٥٦٧٨", "User"); // U+0661..U+0668
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarUsuario("１２３４５６７８", "User");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
    

    @Test
    public void registrarUsuarioError03() {
        
        s.registrarUsuario("12345454", "Jose");
        
          retorno = s.registrarUsuario("12345454", "Jose");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

    }

}