package sistemaAutogestion;

//Integrantes del equipo: Rocío González nro est. 264963 - Analía López num est. 331850

import dominio.Bicicleta;
import dominio.Estacion;
import dominio.Usuario;
import tads.ILista;
//import tads.ListaDE;
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

    // Validamos si alguno de los parámetros es null o vacío
 // ?? hay que agregar alguna validacion de que no sean vacios similar al isEmpty??
    if (nombre == null || barrio == null) {
        return Retorno.error1(); // parámetro inválido
    }

    // Validamos capacidad <= 0
    if (capacidad <= 0) {
        return Retorno.error2(); // capacidad inválida
    }

    // Verificar que no exista estación con el mismo nombre
    // Creamos estación temporal para verificar existencia
    Estacion nueva = new Estacion(nombre, barrio, capacidad);

    if (estaciones.existeElemento(nueva)) {
        return Retorno.error3(); // estación ya existe
    }

    // Agregamos la estación:    
    estaciones.Adicionar(nueva);
    
    return Retorno.ok();
    }

    @Override // Analía
    public Retorno registrarUsuario(String cedula, String nombre) {
   //return Retorno.noImplementada();
        
    // Validamos Si alguno de los parámetros es null o vacío.
    if (cedula == null || nombre == null ) {
        return Retorno.error1(); 
    }

    // Validamos formato de cédula: exactamente 8 dígitos
    if (cedula.length() != 8) {
        return Retorno.error2(); // formato cédula inválido
    }

    // Validamos que no exista usuario con la misma cédula
    Usuario nuevo = new Usuario(cedula, nombre);
    if (usuarios.existeElemento(nuevo)) {
        return Retorno.error3(); // usuario ya existe
    }

    // Agregamos usuario a la lista
    usuarios.Adicionar(nuevo);

    return Retorno.ok();
        
    }


    @Override // Analía
    public Retorno registrarBicicleta(String codigo, String tipo) {
        //return Retorno.noImplementada();
        
    // Validamos Si alguno de los parámetros es null o vacío.
    if (codigo == null || tipo == null ) {
        return Retorno.error1();
    }

    // Validamos formato de código: exactamente 6 caracteres
    if (codigo.length() != 6) {
        return Retorno.error2();
    }

    // Validamos tipo permitido
 // Podemos usar el equals de String??
    String tipoUpper = tipo.toUpperCase(); // para comparar sin importar mayúsculas/minúsculas
    if (!tipoUpper.equals("URBANA") && !tipoUpper.equals("MOUNTAIN") && !tipoUpper.equals("ELECTRICA")) {
        return Retorno.error3();
    }

    //  Verificar que no exista bici con el mismo código
    Bicicleta nueva = new Bicicleta(codigo, tipoUpper);
    if (bicicletas.existeElemento(nueva)) {
        return Retorno.error4();
    }

    // Agregar bicicleta al depósito
    bicicletas.Adicionar(nueva);

    return Retorno.ok();
    }

    
    
    @Override//Rocioo
    public Retorno marcarEnMantenimiento(String codigo, String motivo) {
        if(codigo == null|| motivo == null ){
        return Retorno.error1();
        }
        //return Retorno.error1();
        
        Bicicleta bicicleta;
        bicicleta = new Bicicleta();
        bicicleta.setCodigo(codigo);
        boolean bicicletaExiste = bicicletas.existeElemento(bicicleta);

        if(!bicicletaExiste){
        
        return Retorno.error2();
        
        }
        
         
                return Retorno.error2();

    }

    
    @Override // Rocio
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
