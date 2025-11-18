
package sistemaAutogestion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test2_10DevolverBicicleta {
     private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }
    
     @Test
    public void devolverBicicletaOk(){
        // Preparar escenario
        s.registrarEstacion("Estacion01", "Ciudad Vieja", 5);
        s.registrarUsuario("12345678", "Ana");
        s.registrarBicicleta("BICI01", "URBANA");
        s.asignarBicicletaAEstacion("BICI01", "Estacion01");
        s.alquilarBicicleta("12345678", "Estacion01");

        // Ejecutar método a testear
        retorno = s.devolverBicicleta("12345678", "Centro");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        
    }
    
}
