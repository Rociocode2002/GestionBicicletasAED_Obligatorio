package sistemaAutogestion;

//Integrantes del equipo: Rocío González nro est. 264963 - Analía López nro est. 331850
import dominio.Bicicleta;
import dominio.Estacion;
import dominio.Estado_Bicicleta;
import dominio.Usuario;
import sistemaAutogestion.Retorno.Resultado;
import tads.ILista;
import tads.ListaDE;
import tads.ListaSE;

public class Sistema implements IObligatorio {

    private ILista<Usuario> usuarios; //para el metodo crearSistemaDeGestion, necesitamos tener estas listas inicializadas
    private ILista<Estacion> estaciones;
    private ILista<Bicicleta> bicicletas;
    private ListaDE<Bicicleta> listaDeposito;

    @Override
    public Retorno crearSistemaDeGestion() {

         
            usuarios = new ListaSE<Usuario>(); //Es necesario inicializar con el tipo de dato??
            estaciones = new ListaSE<Estacion>(); //ver luego si corresponde DE??
            bicicletas = new ListaSE<Bicicleta>();
            listaDeposito = new ListaDE <Bicicleta>();
            return Retorno.ok();
        
    }

    @Override
    public Retorno registrarEstacion(String nombre, String barrio, int capacidad) {
        //return Retorno.noImplementada();

        // Validamos si alguno de los parámetros es null o vacío
        /* Consultar a la profe:  Acá alcanza con esto o falta validar que estos parametros no sean vacios?? 
  Y está bien usar los métodos de la clase String .trim().isEmpty()??
    (lo mismo pasa para registrar usuario y bicicleta)*/
        if (nombre == null || nombre == "" || barrio == null || barrio == "") {
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
        if (cedula == null || nombre == null || cedula == "" || nombre == "" ) {
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
        if (codigo == null || tipo == null) {
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

    
    
    @Override// Rocio
public Retorno marcarEnMantenimiento(String codigo, String motivo) {
    if(codigo == null || motivo == null || codigo == "" || motivo == ""){
        return Retorno.error1();
    }
    
    Bicicleta bicicletaEncontrada = null;
    int longitud = bicicletas.Longitud();
    
    for (int i = 0; i < longitud; i++) {
        
        if (i < longitud) { 
            Bicicleta bicicletaBuscar;
            bicicletaBuscar = (Bicicleta) bicicletas.Obtener(i);
            if (bicicletaBuscar.getCodigo().equals(codigo)) {
                bicicletaEncontrada = bicicletaBuscar;
                break; 
            }
        }
    }
    

    if (bicicletaEncontrada == null) {
        return Retorno.error2();
    }
    
    if ("ALQUILADA".equals(bicicletaEncontrada.getEstado())) {
        return Retorno.error3();
    }
    
    if ("MANTENIMIENTO".equals(bicicletaEncontrada.getEstado())) {
        return Retorno.error4();
    }
    
    bicicletaEncontrada.setEstado(Estado_Bicicleta.MANTENIMIENTO);
    
        listaDeposito.Adicionar(bicicletaEncontrada); 
    
    return Retorno.ok();
}
    
    
  
 @Override//Rocio
public Retorno repararBicicleta(String codigo) {
    if (codigo == null || codigo == "" ) {
        return Retorno.error1();
    }
    
    Bicicleta bicicletaEncontrada = null;
    int longitud = bicicletas.Longitud();
    
    
    for (int i = 0; i < longitud; i++) {
        // Validar que el índice esté dentro del rango
        if (i >= 0 && i < longitud) {
            Bicicleta bicicletaBuscar = (Bicicleta) bicicletas.Obtener(i);
            if (bicicletaBuscar.getCodigo().equals(codigo)) {
                bicicletaEncontrada = bicicletaBuscar;
                break; 
            }
        }
    }
    
    if (bicicletaEncontrada == null) {
        return Retorno.error2(); 
    }
    
    if (!"MANTENIMIENTO".equals(bicicletaEncontrada.getEstado())) {
        return Retorno.error3(); 
    }
  
    bicicletaEncontrada.setEstado(Estado_Bicicleta.DISPONIBLE);
     listaDeposito.Adicionar(bicicletaEncontrada); 
    
    return Retorno.ok();
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

    @Override//3.2 Rocio
    public Retorno listarUsuarios() {
     
        ILista<Usuario> Usuarios = this.usuarios;
        String resultado = "";
        
        for(int i= 0; i< Usuarios.Longitud();i++){
        
          Usuario usuario = Usuarios.Obtener(i);
        
        if (i > 0) {
            resultado += "|";
        }
        
   
        resultado += usuario.toString();
    
}

   return new Retorno(Resultado.OK, resultado);
     
        
    }

    @Override
    public Retorno listarBicisEnDeposito() {
        ListaDE<Bicicleta> BicicletasEnDeposito = this.listaDeposito;
        String resultado = "";
        
        for(int i = 0; i<BicicletasEnDeposito.Longitud();i++){
        
          Bicicleta bicicleta = BicicletasEnDeposito.Obtener(i);
          
          
        if (i > 0) {
            resultado += "|";
        }
        
        
   
        resultado += bicicleta.toString();
        }
             return new Retorno(Resultado.OK, resultado);
         
        
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
