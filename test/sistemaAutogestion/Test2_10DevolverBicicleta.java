
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
        retorno = s.devolverBicicleta("12345678", "Estacion01");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        //assertTrue(retorno.getValorString().toUpperCase().contains("BICICLETA DEVUELTA"));
    }

    @Test
    public void devolverBicicletaOk_usuarioEnEspera() {
        // Preparar estaciones y usuarios
        s.registrarEstacion("Estacion01", "Centro", 1); // capacidad 1
        s.registrarEstacion("Estacion02", "Cordón", 1); // capacidad 1

        s.registrarUsuario("12345678", "Juan");
        s.registrarUsuario("87654321", "Ana");

        s.registrarBicicleta("BICI01", "URBANA");
        s.registrarBicicleta("BICI02", "MOUNTAIN");

        // Asignar y alquilar bicicleta
        s.asignarBicicletaAEstacion("BICI01", "Estacion01");
        s.asignarBicicletaAEstacion("BICI02", "Estacion02");

        s.alquilarBicicleta("12345678", "Estacion01");
        s.alquilarBicicleta("87654321", "Estacion02");

        // EstacionDestino con anclaje lleno
        retorno = s.devolverBicicleta("12345678", "Estacion02");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertTrue(retorno.getValorString().toUpperCase().contains("USUARIO EN ESPERA"));
    }

    @Test
    public void devolverBicicletaError01_parametrosInvalidos() {
        retorno = s.devolverBicicleta("", "Estacion01");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.devolverBicicleta("12345678", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.devolverBicicleta(null, "Estacion01");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.devolverBicicleta("12345678", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void devolverBicicletaError02_usuarioInexistenteOSinBici() {
        s.registrarUsuario("12345678", "Juan");
        // Usuario no tiene bicicleta alquilada
        retorno = s.devolverBicicleta("12345678", "Estacion01");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        // Usuario inexistente
        retorno = s.devolverBicicleta("87654321", "Estacion01");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void devolverBicicletaError03_estacionInexistente() {
        s.registrarUsuario("12345678", "Juan");
        s.registrarEstacion("Estacion01", "Centro", 5); // ← FALTA EN TU TEST
        s.registrarBicicleta("BICI01", "URBANA");
        s.asignarBicicletaAEstacion("BICI01", "Estacion01");
        s.alquilarBicicleta("12345678", "Estacion01");

        retorno = s.devolverBicicleta("12345678", "EstacionInexistente");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

    }

    
}
