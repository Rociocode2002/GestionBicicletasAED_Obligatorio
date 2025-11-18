
package sistemaAutogestion;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class Test2_11DeshacerUltimosRetiros {
    private Retorno retorno;
   private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }
    
    @Test
    public void deshacerUltimosRetirosOk_parcial() {
        // Preparar usuarios, estaciones y bicicletas
        s.registrarEstacion("Estacion01", "Centro", 2);
        s.registrarUsuario("12345678", "Juan");
        s.registrarUsuario("87654321", "Ana");
        s.registrarBicicleta("BICI01", "URBANA");
        s.registrarBicicleta("BICI02", "MOUNTAIN");

        s.asignarBicicletaAEstacion("BICI01", "Estacion01");
        s.asignarBicicletaAEstacion("BICI02", "Estacion01");

        s.alquilarBicicleta("12345678", "Estacion01");
        s.alquilarBicicleta("87654321", "Estacion01");

        // Deshacer solo el último retiro
        retorno = s.deshacerUltimosRetiros(1);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertTrue(retorno.getValorString().contains("BICI02#87654321#Estacion01"));

        // Verificar que la otra bicicleta sigue alquilada
        retorno = s.deshacerUltimosRetiros(1);
        assertTrue(retorno.getValorString().contains("BICI01#12345678#Estacion01"));
    }

    @Test
    public void deshacerUltimosRetirosOk_todos() {
        // Preparar sistema con 2 retiros
        s.registrarEstacion("Estacion01", "Centro", 2);
        s.registrarUsuario("12345678", "Juan");
        s.registrarUsuario("87654321", "Ana");
        s.registrarBicicleta("BICI01", "URBANA");
        s.registrarBicicleta("BICI02", "MOUNTAIN");

        s.asignarBicicletaAEstacion("BICI01", "Estacion01");
        s.asignarBicicletaAEstacion("BICI02", "Estacion01");

        s.alquilarBicicleta("12345678", "Estacion01");
        s.alquilarBicicleta("87654321", "Estacion01");

        // Deshacer más retiros de los que hay (debería deshacer todos)
        retorno = s.deshacerUltimosRetiros(5);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertTrue(retorno.getValorString().contains("BICI01#12345678#Estacion01"));
        assertTrue(retorno.getValorString().contains("BICI02#87654321#Estacion01"));
    }

    @Test
    public void deshacerUltimosRetirosOk_sinRetiros() {
        retorno = s.deshacerUltimosRetiros(1);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertTrue(retorno.getValorString().contains("No hay retiros"));
    }

    @Test
    public void deshacerUltimosRetirosError01_parametroInvalido() {
        retorno = s.deshacerUltimosRetiros(0);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.deshacerUltimosRetiros(-5);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }
     
}
