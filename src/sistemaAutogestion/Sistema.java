package sistemaAutogestion;

//Integrantes del equipo: Rocío González nro est. 264963 - Analía López nro est. 331850

import dominio.Bicicleta;
import dominio.Estacion;
import dominio.Estado_Bicicleta;
import dominio.Tipo_Bicicleta;
import dominio.Usuario;
import sistemaAutogestion.Retorno.Resultado;
import tads.ILista;
import tads.ListaDE;
import tads.ListaSE;

public class Sistema implements IObligatorio {

    private ILista<Usuario> usuarios; 
    private ILista<Estacion> estaciones;
    private ILista<Bicicleta> bicicletas;
    private ListaDE<Bicicleta> listaDeposito;

    @Override
    public Retorno crearSistemaDeGestion() {

         
            usuarios = new ListaSE<Usuario>(); 
            estaciones = new ListaSE<Estacion>(); 
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
    
    

    @Override 
    public Retorno registrarBicicleta(String codigo, String tipo) {
        
        // Validamos Si alguno de los parámetros es null o vacío.
        if (codigo == null || codigo =="" || tipo == null || tipo =="") {
            return Retorno.error1();
        }

        // Validamos formato de código: exactamente 6 caracteres
        if (codigo.length() != 6) {
            return Retorno.error2();
        }

        // Validamos tipo permitido:
 // ??? Es correcto usar .valueOf para Convertir el String a Tipo_Bicicleta???
       Tipo_Bicicleta tipoBici;
        try {
            tipoBici = Tipo_Bicicleta.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Retorno.error3();
        }

        //  Verificar que no exista bici con el mismo código
        Bicicleta nueva = new Bicicleta(codigo, tipoBici);
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

    
    @Override //Analía
    public Retorno obtenerUsuario(String cedula) {
        
        // validamos que no se null o vacío:
        if (cedula == null || cedula.trim().isEmpty()) {
         return Retorno.error1(); 
        }

        // validamos que la cédula tenga 8 dígitos
        if (cedula.length() != 8) {
           return Retorno.error2(); 
        }

        // buscamos el usuario en la lista:
        for (int i = 0; i < usuarios.Longitud(); i++) {
            Usuario u = usuarios.Obtener(i); 
            if (u.getCedula().equals(cedula)) {
                String resultado = u.getNombre() + "#" + u.getCedula();
                return Retorno.ok(resultado);
            }
        }

     // si no se encontró:
        return Retorno.error3(); 
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
    // Validación básica
        if (mapa == null || mapa.length == 0 || mapa[0].length == 0) {
           return Retorno.ok("0#ambas|no existe");
        }

        int filas = mapa.length;
        int columnas = mapa[0].length;

        int[] estacionesFila = new int[filas];
        int[] estacionesColumna = new int[columnas];

     // Contar estaciones en cada fila y columna
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
             if (mapa[i][j] != null && !mapa[i][j].trim().isEmpty() && !mapa[i][j].equals("0")) {
                    estacionesFila[i]++;
                    estacionesColumna[j]++;
                }
            }
        }

        // Máximos
        int maxFila = 0;
        for (int valor : estacionesFila)
            if (valor > maxFila) maxFila = valor;

        int maxColumna = 0;
        for (int valor : estacionesColumna)
            if (valor > maxColumna) maxColumna = valor;

        // Determinar tipo de máximo
        String tipoMax;
        if (maxFila == 0 && maxColumna == 0) {
            tipoMax = "ambas"; // mapa vacío
        } else if (maxFila > maxColumna) {
            tipoMax = "fila";
        } else if (maxColumna > maxFila) {
         tipoMax = "columna";
        } else {
            tipoMax = "ambas";
        }

        // Verificar si existen 3 columnas consecutivas con conteo ascendente
        boolean existeAscendente = false;
        int j = 0;

        while (j < columnas - 2 && !existeAscendente) {
            if (estacionesColumna[j] < estacionesColumna[j + 1] &&
                estacionesColumna[j + 1] < estacionesColumna[j + 2]) {
                existeAscendente = true;
            }
            j++;
        }


        // Formar salida
        String resultado = (Math.max(maxFila, maxColumna)) + "#" + tipoMax + "|" +
        (existeAscendente ? "existe" : "no existe");

        return Retorno.ok(resultado);
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
