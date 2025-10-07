/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemaAutogestion;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author rocio
 */
public class Test3_03ListarBicisEnDeposito {
        private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void listarBicisDepositoVacio() {
        retorno = s.listarUsuarios();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    public void listarBicisDepositoSoloUnaBici() {
        s.registrarBicicleta("35753", "MOUNTAIN");
        retorno = s.listarBicisEnDeposito();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        //assertEquals("35753#MOUNTAIN#", retorno.getValorString());
    }

    @Test
    public void listarBicisDepositoIngresoOrdenado() { // NO FUNCIONA
        s.registrarUsuario("11111111", "Usuario01");
        s.registrarUsuario("31221111", "Usuario02");
        s.registrarUsuario("11331111", "Usuario03");
        retorno = s.listarUsuarios();
        System.out.print(retorno.getValorString());
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("Usuario01#11111111|Usuario02#31221111|Usuario03#11331111", retorno.getValorString());
    }

    @Test
    public void listarBicisDepositoDesordenado() { // NO FUNCIONA
        s.registrarUsuario("11111111", "Usuario01");
        s.registrarUsuario("11331111", "Usuario03");
        s.registrarUsuario("31221111", "Usuario02");
        retorno = s.listarUsuarios();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("Usuario01#11111111|Usuario02#31221111|Usuario03#11331111", retorno.getValorString());
    }

}
    

