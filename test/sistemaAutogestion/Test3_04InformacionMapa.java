package sistemaAutogestion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3_04InformacionMapa {

    private Retorno retorno;
    private final IObligatorio s = new Sistema();

    @Before
    public void setUp() {
        s.crearSistemaDeGestion();
    }

    @Test
    public void mapa1() {
        String o = "o";
        String[][] mapa1 = {
            {o   ,o,o   ,o   ,o   ,o   ,o},
            {o   ,o,o   ,"E3",o   ,o   ,o},
            {o   ,o,o   ,o   ,o   ,o   ,o},
            {"E1",o,o   ,o   ,o   ,"E5",o},
            {o   ,o,o   ,o   ,o   ,o   ,o},
            {o   ,o,"E2",o   ,"E6",o   ,o},
            {o   ,o,o   ,"E7",o   ,o   ,o},
            {o   ,o,o   ,"E4",o   ,o   ,o},
        };
        Retorno r = s.informaciónMapa(mapa1);
        assertEquals(Retorno.Resultado.OK, r.getResultado());
        assertEquals("3#columna|existe", r.getValorString());
    }

    @Test
    public void mapa2() {
        String o = "o";
        String[][] mapa2 = {
            {o   ,o,o   ,o   ,o,o   ,o},
            {o   ,o,o   ,"E3",o,o   ,o},
            {o   ,o,o   ,o   ,o,o   ,o},
            {"E1",o,o   ,o   ,o,"E5",o},
            {o   ,o,o   ,o   ,o,o   ,o},
            {o   ,o,"E2",o   ,o,o   ,o},
            {o   ,o,o   ,o   ,o,o   ,o},
            {o   ,o,o   ,"E4",o,o   ,o},
        };
        Retorno r = s.informaciónMapa(mapa2);
        assertEquals("2#ambas|existe", r.getValorString());
    }

    @Test
    public void mapa3() {
        String o = "o";
        String[][] mapa3 = {
            {o   ,o   ,o,o   ,o   ,o   ,o},
            {o   ,o   ,o,"E3",o   ,o   ,o},
            {o   ,o   ,o,o   ,o   ,o   ,o},
            {"E1",o   ,o,o   ,o   ,"E5",o},
            {o   ,o   ,o,o   ,o   ,o   ,o},
            {o   ,o   ,o,o   ,"E6",o   ,o},
            {o   ,"E7",o,o   ,o   ,o   ,o},
            {o   ,o   ,o,"E4",o   ,o   ,o},
        };
        Retorno r = s.informaciónMapa(mapa3);
        assertEquals("2#ambas|no existe", r.getValorString());
    }
    
    @Test
    public void ejemplo_sin3ColumnasConsecutivas() {
        String o = "o";
        String[][] mapa = {
            {o,  "E1",  o,  "E2"},
            {o,  "E3",  o,  "E4"},
            {o,    o,  "E5", "E6"},
        };
        Retorno r = s.informaciónMapa(mapa);
        assertEquals("3#columna|no existe", r.getValorString());
    }
    
    @Test
    public void ejemplo_mapaNull() {
        assertEquals("0#ambas|no existe", s.informaciónMapa(null).getValorString());
    }

    @Test
    public void ejemplo_matrizVacia() {
        assertEquals("0#ambas|no existe", s.informaciónMapa(new String[0][0]).getValorString());
    }

    @Test
    public void ejemplo_FilaVacia() {
        assertEquals("0#ambas|no existe", s.informaciónMapa(new String[][] { {} }).getValorString());
    }

    @Test
    public void ejemplo_sinEstaciones() {
        String o="o";
        String[][] m = {
            {o,o,o},
            {o,o,o}
        };
        assertEquals("0#ambas|no existe", s.informaciónMapa(m).getValorString());
    }

    @Test
    public void ejemplo_unaColumna() {
        String[][] m = {
            {"E1"},
            {"o"},
            {"E2"}
        }; 
        assertEquals("2#columna|no existe", s.informaciónMapa(m).getValorString());
    }

  


    @Test
    public void ejemplo_sinTriple() {
        String o="o";
        String[][] m = {
            {"E1", "E2",  o},
            {  o ,   o , "E3"},
            {  o ,   o ,  o }
        }; 
        assertEquals("2#fila|no existe", s.informaciónMapa(m).getValorString());
    }
    
    @Test
    public void ejemplo_Fila() {
        String o="o";
        String[][] m = {
            {"E1","E2","E3"},
            {  o ,  o ,  o  }
        }; 
        assertEquals("3#fila|no existe", s.informaciónMapa(m).getValorString());
    }

    @Test
    public void ejemplo_Columna() {
        String o="o";
        String[][] m = {
            {"E1",o},
            {"E2",o},
            {"E3",o}
        }; 
        assertEquals("3#columna|no existe", s.informaciónMapa(m).getValorString());
    }

    @Test
    public void ejemplo_ambas() {
        String o="o";
        String[][] m2 = {
            {"E1","E2",  o },
            {  o ,  o , "E3"},
            {  o ,  o , "E4"}
        }; 
        assertEquals("2#ambas|no existe", s.informaciónMapa(m2).getValorString());
    }

    @Test
    public void ejemplo_conMinusculasYEspacios() {
        String[][] m = {
            {" e1 ", "o", "o"},
            {" o ",  " e2 ", "o"},
            {" o ",   " o ", " e3 "}
        }; 
        assertEquals("1#ambas|no existe", s.informaciónMapa(m).getValorString());
    }
 
}
