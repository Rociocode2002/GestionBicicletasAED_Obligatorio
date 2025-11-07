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
public class Test2_08AsignarBicicletaEstacion {
    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }
    
    @Test
    public void AsignarBicicletaAEstacionOk() {
    // Registrar estación
    retorno = s.registrarEstacion("Estacion01", "Centro", 5);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    // Registrar bicicleta
    retorno = s.registrarBicicleta("123123", "MOUNTAIN");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    // Asignar bicicleta a estación
    retorno = s.asignarBicicletaAEstacion("123123", "Estacion01");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
  
}
    @Test
public void AsignarBicicletaAEstacionOk_DesdeDeposito() {
    // Registrar estación
    retorno = s.registrarEstacion("Estacion01", "Centro", 5);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    // Registrar bicicleta en depósito
    retorno = s.registrarBicicleta("BIC001", "ELECTRICA");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    // Asignar bicicleta a estación
    retorno = s.asignarBicicletaAEstacion("BIC001", "Estacion01");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
}

@Test
public void AsignarBicicletaAEstacionOk_DesdeOtraEstacion() {
    // Registrar dos estaciones
    retorno = s.registrarEstacion("EstacionA", "Centro", 5);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    retorno = s.registrarEstacion("EstacionB", "Cordón", 5);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    // Registrar bicicleta y asignarla a EstacionA
    retorno = s.registrarBicicleta("BICI01", "URBANA");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    retorno = s.asignarBicicletaAEstacion("BICI01", "EstacionA");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    // Mover la misma bicicleta de EstacionA a EstacionB NO FUNCIONAA
    retorno = s.asignarBicicletaAEstacion("BICI01", "EstacionB");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
}
@Test
public void AsignarBicicletaAEstacionOk_MultiplesEstaciones() {
    // Registrar múltiples estaciones
    retorno = s.registrarEstacion("EstacionA", "Centro", 2);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    retorno = s.registrarEstacion("EstacionB", "Cordón", 2);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    // Registrar bicicletas en depósito
    retorno = s.registrarBicicleta("123456", "MOUNTAIN");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    retorno = s.registrarBicicleta("123457", "ELECTRICA");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    // Asignar a diferentes estaciones
    retorno = s.asignarBicicletaAEstacion("123456", "EstacionA");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    retorno = s.asignarBicicletaAEstacion("123457", "EstacionB");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
}

@Test
public void AsignarBicicletaAEstacionError1_DatosInvalidos() {
 
    retorno = s.asignarBicicletaAEstacion(null, "Estacion01");
    assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    
    
    retorno = s.asignarBicicletaAEstacion("", "Estacion01");
    assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    
   
    retorno = s.asignarBicicletaAEstacion("BICI001", null);
    assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    
    
    retorno = s.asignarBicicletaAEstacion("BICI001", "");
    assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
}

@Test
public void AsignarBicicletaAEstacionError2_BicicletaNoExiste() {
  
    retorno = s.registrarEstacion("Estacion01", "Centro", 5);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
  
    retorno = s.asignarBicicletaAEstacion("B76765", "Estacion01");
    assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
}
@Test
public void AsignarBicicletaAEstacionError2_BicicletaEnMantenimiento() {
    
    retorno = s.registrarEstacion("Estacion01", "Centro", 5);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    
    retorno = s.registrarBicicleta("BICI12", "ELECTRICA");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
   
   s.marcarEnMantenimiento("BICI12", "se rompio");
    
   
    retorno = s.asignarBicicletaAEstacion("BICI12", "Estacion01");
    assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
}
@Test
public void AsignarBicicletaAEstacionError3_EstacionNoExiste() {
  
    retorno = s.registrarBicicleta("BI2312", "URBANA");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    
    retorno = s.asignarBicicletaAEstacion("BI2312", "ESTACION_INEXISTENTE");
    assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
}

@Test
public void AsignarBicicletaAEstacionError4_SinAnclajesLibres() {
   
    retorno = s.registrarEstacion("EstacionPequena", "Centro", 1);
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    
    retorno = s.registrarBicicleta("BICI22", "MOUNTAIN");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    retorno = s.registrarBicicleta("BICI23", "ELECTRICA");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
   
    retorno = s.asignarBicicletaAEstacion("BICI22", "EstacionPequena");
    assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    
    
    retorno = s.asignarBicicletaAEstacion("BICI23", "EstacionPequena");
    assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
}






    
 
    
}
