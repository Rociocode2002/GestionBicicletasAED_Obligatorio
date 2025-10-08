package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test2_05PonerBicicletaMantenimiento {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void marcarEnMantenimientoOK() {
        retorno = s.registrarBicicleta("555655", "MOUNTAIN");
        retorno = s.marcarEnMantenimiento("555655", "rota");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
       
    }

    @Test
    public void marcarEnMantenimientoError01() {

        retorno = s.registrarBicicleta("123123", "MOUNTAIN");
        retorno = s.marcarEnMantenimiento("", "rota");
        retorno = s.marcarEnMantenimiento(null, "rota");
        retorno = s.marcarEnMantenimiento("1221212", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

    }

    @Test
    public void marcarEnMantenimientoError02() {
        retorno = s.registrarBicicleta("12993123", "MOUNTAIN");
        retorno = s.marcarEnMantenimiento("123123222", "rota");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    /*@Test
    public void marcarEnMantenimientoError03() { 
          //retorno = s.registrarBicicleta("123123", "MOUNTAIN");
        retorno = s.marcarEnMantenimiento("123123", "rota");
         retorno = s.marcarEnMantenimiento("123123", "rota");
            assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        
        
    } */
    
    @Test
    public void marcarEnMantenimientoError04() {
            retorno = s.registrarBicicleta("123123", "MOUNTAIN");
        retorno = s.marcarEnMantenimiento("123123", "rota");
        retorno = s.marcarEnMantenimiento("123123", "rota");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    
    };
    
    
     @Test
    public void ok_BiciEnEstacionPasaAMantenimientoYVaADeposito() {
        s.registrarEstacion("E1", "Centro", 2);
        s.registrarBicicleta("ABC001", "URBANA");
        s.asignarBicicletaAEstacion("ABC001", "E1");

        // Pre: está en estación
        Retorno rListEst = s.listarBicicletasDeEstacion("E1");
        assertTrue(rListEst.getValorString().contains("ABC001"));

        retorno = s.marcarEnMantenimiento("ABC001", "rueda pinchada");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        // Ya no está en estación y sí figura en depósito como MANTENIMIENTO
        rListEst = s.listarBicicletasDeEstacion("E1");
        assertFalse(rListEst.getValorString().contains("ABC001"));

        Retorno rDep = s.listarBicisEnDeposito();
        String dep = rDep.getValorString();
        assertTrue(dep.contains("ABC001"));
        assertTrue(dep.toUpperCase().contains("ABC001#URBANA#MANTENIMIENTO"));
    }


}
