/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaAutogestion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author rocio
 */
public class Test2_07EliminarEstacion {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }
    @Test
    public void EliminarEstacionOk() {
        retorno = s.registrarEstacion("Estacion01", "Centro", 5);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        
        retorno = s.registrarEstacion("Estacion02", "Cordón", 10);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        
        retorno = s.registrarEstacion("Estacion03", "Ciudad Vieja", 10);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        
        retorno = s.eliminarEstacion("Estacion01");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        
        
    
    
}
    @Test // No funciona todavia
public void EliminarEstacionOk_Multiple() {
    // Registrar varias estaciones
    retorno = s.registrarEstacion("EstacionA", "Centro", 5);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    retorno = s.registrarEstacion("EstacionB", "Cordón", 10);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    retorno = s.registrarEstacion("EstacionC", "Ciudad Vieja", 10);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    // Eliminar una estación sin usuarios ni bicicletas
    retorno = s.eliminarEstacion("EstacionB");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    // Verificar que no existe más
    retorno = s.eliminarEstacion("EstacionB");
    assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    
    // Eliminar otra
    retorno = s.eliminarEstacion("EstacionA");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    // Verificar que la última sigue existiendo
    retorno = s.eliminarEstacion("EstacionC");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
}

@Test// no funciona todavia 
public void EliminarEstacion_CasoBorde() {
    // Test con nombre exacto
    retorno = s.registrarEstacion("EstacionExacta", "Centro", 5);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    // Eliminar con mismo nombre (case sensitive)
    retorno = s.eliminarEstacion("EstacionExacta");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    // Verificar que no se puede eliminar dos veces
    retorno = s.eliminarEstacion("EstacionExacta");
    assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
}



    @Test
public void EliminarEstacionError1_NombreInvalido() {
    
    retorno = s.eliminarEstacion(null);
    assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    
    
    retorno = s.eliminarEstacion("");
    assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    
    
    retorno = s.eliminarEstacion("   ");
    assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
}
    
    
    @Test
public void EliminarEstacionError2_EstacionNoExiste() {
    // Registrar algunas estaciones
    retorno = s.registrarEstacion("Estacion01", "Centro", 5);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    retorno = s.registrarEstacion("Estacion02", "Cordón", 10);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    
    retorno = s.eliminarEstacion("EstacionInexistente");
    assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    
    
}

@Test// todavia no funciona 
public void EliminarEstacionError3_ConUsuariosEnEspera() {
    // Registrar estación
    retorno = s.registrarEstacion("EstacionConEspera", "Centro", 5);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
  
    s.registrarUsuario("12345678", "Usuario1");
    s.alquilarBicicleta("12345678", "EstacionConEspera"); // Esto debería ponerlo en espera si no hay bicis
    
    // Intentar eliminar estación con usuarios en espera
    retorno = s.eliminarEstacion("EstacionConEspera");
    assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
}


@Test
public void EliminarEstacionError3_ConBicicletasAncladas() { // todavia no funciona
    // Registrar estación
    retorno = s.registrarEstacion("EstacionConBicis", "Centro", 5);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    
     s.registrarBicicleta("BICI001", "EstacionConBicis");
     s.registrarBicicleta("BICI002", "EstacionConBicis");
    
    // Intentar eliminar estación con bicicletas
    retorno = s.eliminarEstacion("EstacionConBicis");
    assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
}

@Test
public void EliminarEstacionError3_ConUsuariosYBicicletas() { // todavia no funciona
    
    retorno = s.registrarEstacion("EstacionCompleta", "Centro", 5);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
 
    s.registrarBicicleta("BICI003", "EstacionCompleta");
     s.registrarUsuario("87654321", "Usuario2");
     s.alquilarBicicleta("87654321", "EstacionCompleta");
    
    // Intentar eliminar estación con ambos
    retorno = s.eliminarEstacion("EstacionCompleta");
    assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
}



    
    
    
}