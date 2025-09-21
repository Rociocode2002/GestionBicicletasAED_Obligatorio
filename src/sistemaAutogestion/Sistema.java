package sistemaAutogestion;

//Integrantes del equipo: Rocío González nro est. 264963 - Analía López num est. 331850

import dominio.Bicicleta;
import dominio.Estacion;
import dominio.Usuario;
import tads.ILista;
import tads.ListaDE;
import tads.ListaSE;


public class Sistema implements IObligatorio {

    private ILista<Usuario> usuarios; //para el metodo crearSistemaDeGestion, necesitamos tener estas listas inicializadas
    private ILista<Estacion> estaciones;
    private ILista<Bicicleta> bicicletas;
    @Override
    public Retorno crearSistemaDeGestion() {
        
//return Retorno.noImplementada();
        
        try { //ver si usamos try catch o como??
            usuarios = new ListaSE<>();
            estaciones = new ListaSE<>(); //ver luego si corresponde DE??
            bicicletas = new ListaSE<>();
            return Retorno.ok();
        } catch (Exception e) {
            return Retorno.error1(); // si algo fallara
        }
    }

    @Override
    public Retorno registrarEstacion(String nombre, String barrio, int capacidad) {
        //return Retorno.noImplementada();

    // 1. Validar parámetros
    if (nombre == null || nombre.isEmpty() || barrio == null || barrio.isEmpty()) {
        return Retorno.error1(); // parámetro inválido
    }

    // 2. Validar capacidad
    if (capacidad <= 0) {
        return Retorno.error2(); // capacidad inválida
    }

    // 3. Verificar que no exista estación con el mismo nombre
    for (int i = 0; i < estaciones.Longitud(); i++) {
        try {
            Estacion e = estaciones.Obtener(i);
            if (e.getNombre().equalsIgnoreCase(nombre)) {// ---------revisar que metodo usar!!
                return Retorno.error3(); // estación ya existe
            }
        } catch (Exception ex) {
            return Retorno.error1(); // error inesperado
        }
    }

    // 4. Crear y agregar la estación 
    Estacion nueva = new Estacion(nombre, barrio, capacidad);
    estaciones.Adicionar(nueva);

    return Retorno.ok();
    }

    @Override // Analía
    public Retorno registrarUsuario(String cedula, String nombre) {
        return Retorno.noImplementada();
    }

    @Override // Analía
    public Retorno registrarBicicleta(String codigo, String tipo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno marcarEnMantenimiento(String codigo, String motivo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno repararBicicleta(String codigo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno eliminarEstacion(String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno asignarBicicletaAEstacion(String codigo, String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno alquilarBicicleta(String cedula, String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno devolverBicicleta(String cedula, String nombreEstacionDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno deshacerUltimosRetiros(int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno obtenerUsuario(String cedula) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarUsuarios() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarBicisEnDeposito() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno informaciónMapa(String[][] mapa) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarBicicletasDeEstacion(String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno estacionesConDisponibilidad(int n) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno ocupacionPromedioXBarrio() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno rankingTiposPorUso() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno usuariosEnEspera(String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno usuarioMayor() {
        return Retorno.noImplementada();
    }
    
    public static void main(String[] args) {
 //puse un main aca para poder ejecutar el proyecto, ver si va en otro lado!
    } 

}
