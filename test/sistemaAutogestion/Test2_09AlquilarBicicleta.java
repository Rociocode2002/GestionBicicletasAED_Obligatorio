
package sistemaAutogestion;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


  
public class Test2_09AlquilarBicicleta {
  private Retorno retorno;
   private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }
    
    @Test
    public void alquilarBicicletaOk_BicicletaDisponible() { 
        // Configuración inicial
        s.registrarUsuario("12345678", "Juan");
        s.registrarEstacion("EstacionCentro", "Centro", 3);
        s.registrarBicicleta("BIC001", "URBANA");
        s.asignarBicicletaAEstacion("BIC001", "EstacionCentro");
        
        // Ejecución
        Retorno retorno = s.alquilarBicicleta("12345678", "EstacionCentro");
        
        // Verificación
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
    
    @Test
    public void alquilarBicicletaError_CedulaNull() {
        Retorno retorno = s.alquilarBicicleta(null, "EstacionCentro");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }
@Test
    public void alquilarBicicletaError_CedulaVacia() {
        Retorno retorno = s.alquilarBicicleta("", "EstacionCentro");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void alquilarBicicletaError_NombreEstacionNull() {
        s.registrarUsuario("12345678", "Juan");
        Retorno retorno = s.alquilarBicicleta("12345678", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void alquilarBicicletaError_NombreEstacionVacia() {
        s.registrarUsuario("12345678", "Juan");
        Retorno retorno = s.alquilarBicicleta("12345678", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void alquilarBicicletaError_UsuarioInexistente() {
        s.registrarEstacion("EstacionCentro", "Centro", 3);
        Retorno retorno = s.alquilarBicicleta("99999999", "EstacionCentro");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
      @Test
    public void alquilarBicicletaError_EstacionInexistente() {
        s.registrarUsuario("12345678", "Juan");
        Retorno retorno = s.alquilarBicicleta("12345678", "EstacionInexistente");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
    
    

}
