package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_02ListarUsuarios {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }
    
   

    @Test
    public void listarUsuariosVacio() {
        retorno = s.listarUsuarios();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    public void listarUsuariosSoloUnUsuario() {
        s.registrarUsuario("12345678", "Usuario01");
        retorno = s.listarUsuarios();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("Usuario01#12345678", retorno.getValorString());
    }

    @Test
    public void listarUsuariosIngresoOrdenado() { 
        s.registrarUsuario("11111111", "Usuario01");
        s.registrarUsuario("31221111", "Usuario02");
        s.registrarUsuario("11331111", "Usuario03");
        retorno = s.listarUsuarios();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("Usuario01#11111111|Usuario02#31221111|Usuario03#11331111", retorno.getValorString());
    }

    @Test
    public void listarUsuariosIngresoDesordenado() { 
        s.registrarUsuario("11111111", "Usuario01");
        s.registrarUsuario("11331111", "Usuario03");
        s.registrarUsuario("31221111", "Usuario02");
        retorno = s.listarUsuarios();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("Usuario01#11111111|Usuario02#31221111|Usuario03#11331111", retorno.getValorString());
    }
    
    @Test
    public void listarUsuariosVerificarFormato() {
    
    s.registrarUsuario("12345678", "Juan");
    s.registrarUsuario("87654321", "Maria");
    retorno = s.listarUsuarios();
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    String resultado = retorno.getValorString();
    assertTrue(resultado.contains("Juan#12345678"));
    assertTrue(resultado.contains("Maria#87654321"));
    assertTrue(resultado.contains("|")); 
    }
    
    @Test
    public void listarUsuariosNombresConMayusculas() {
    // Test con mayúsculas y minúsculas
    s.registrarUsuario("11111111", "ana");
    s.registrarUsuario("22222222", "Beatriz");
    s.registrarUsuario("33333333", "CARLOS");
    retorno = s.listarUsuarios();
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
  
    assertEquals("ana#11111111|Beatriz#22222222|CARLOS#33333333", retorno.getValorString());
    }
}
