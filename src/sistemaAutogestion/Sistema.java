package sistemaAutogestion;

//Integrantes del equipo: Rocío González nro est. 264963 - Analía López nro est. 331850

import dominio.Alquiler;
import dominio.Bicicleta;
import dominio.Estacion;
import dominio.Estado_Bicicleta;
import dominio.Tipo_Bicicleta;
import dominio.Usuario;
import java.util.Date;
import sistemaAutogestion.Retorno.Resultado;
import tads.ILista;
import tads.ListaDE;
import tads.ListaSE;
import tads.ICola;
import tads.IPila;
import tads.Cola;

public class Sistema implements IObligatorio {

    private ILista<Usuario> usuarios; 
    private ILista<Estacion> estaciones;
    private ILista<Bicicleta> bicicletas;
    private ListaSE<Bicicleta> listaDeposito;
    private ILista<Alquiler> alquileres;
    private ICola<Usuario> ColaEspera;
    private ICola<Alquiler> ColaAlquiler;
    private ICola<Usuario> ColaDevolucion;
    private ListaSE<Bicicleta> bicicletasAncladas;
    
  
    
    //2.1. Crear Sistema de Gestión:
    @Override
    public Retorno crearSistemaDeGestion() {

         
            usuarios = new ListaSE<Usuario>(); 
            estaciones = new ListaSE<Estacion>(); 
            bicicletas = new ListaSE<Bicicleta>();
            listaDeposito = new ListaSE<Bicicleta>();
            ColaEspera = new Cola<Usuario>();
            ColaAlquiler = new Cola<Alquiler>();
            ColaDevolucion = new Cola<Usuario>();
            bicicletasAncladas = new ListaSE<Bicicleta>();
            return Retorno.ok();
        
    }
    
    //2.2. Registrar Estación:
    @Override
    public Retorno registrarEstacion(String nombre, String barrio, int capacidad) {
        //return Retorno.noImplementada();

        // Validamos si alguno de los parámetros es null o vacío
        
        if (nombre == null || nombre.trim().isEmpty()|| barrio == null || barrio.trim().isEmpty()) {
            return Retorno.error1(); 
        }

        // Validamos capacidad <= 0
        if (capacidad <= 0) {
            return Retorno.error2();
        }

        // Verificar que no exista estación con el mismo nombre
        // Creamos estación temporal para verificar existencia
        Estacion nueva = new Estacion(nombre, barrio, capacidad);

        if (estaciones.existeElemento(nueva)) {
            return Retorno.error3(); 
        }

        // Agregamos la estación:    
        estaciones.Adicionar(nueva);

        return Retorno.ok();
    }
    
    //2.3. Registrar Usuario:
    @Override 
    public Retorno registrarUsuario(String cedula, String nombre) {
        
        // Validamos Si alguno de los parámetros es null o vacío.
        if (cedula == null || nombre == null || cedula.trim().isEmpty()|| nombre.trim().isEmpty() ) {
            return Retorno.error1();
        }

        // Validamos formato de cédula: exactamente 8 dígitos numéricos
        if (!cedula.trim().matches("\\d{8}")) {
          return Retorno.error2(); // formato cédula inválido
        }

        // Validamos que no exista usuario con la misma cédula
        Usuario nuevo = new Usuario(cedula, nombre);
        if (usuarios.existeElemento(nuevo)) {
            return Retorno.error3(); // usuario ya existe
        }

        // Agregamos usuario a la lista
        usuarios.AdicionarOrdenado(nuevo);

        return Retorno.ok();

    }
    
    
    //2.4. Registrar Bicicleta
    @Override 
    public Retorno registrarBicicleta(String codigo, String tipo) {
        
        // Validamos Si alguno de los parámetros es null o vacío.
        if (codigo == null || codigo.trim().isEmpty() || tipo == null || tipo.trim().isEmpty()) {
            return Retorno.error1();
        }

        // Validamos formato de código: exactamente 6 caracteres
        if (codigo.trim().length() != 6 || !codigo.trim().matches("[A-Za-z0-9]+")) {
            return Retorno.error2();
        }

        // Validamos tipo permitido:
 
       Tipo_Bicicleta tipoBici;
        try {
            tipoBici = Tipo_Bicicleta.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Retorno.error3();
        }

        //  Verificar que no exista bici con el mismo código
        Bicicleta nueva = new Bicicleta(codigo, tipoBici);
        if (listaDeposito.existeElemento(nueva)) {
            return Retorno.error4();
        }

        // Agregar bicicleta al depósito       
        listaDeposito.Adicionar(nueva);

        return Retorno.ok();
    }

    
    //2.5. Poner bicicleta en mantenimiento:
    @Override
        public Retorno marcarEnMantenimiento(String codigo, String motivo) {
        if(codigo == null || motivo == null || codigo.trim().isEmpty()|| motivo.trim().isEmpty()){
            return Retorno.error1();
        }

    Bicicleta bicicletaEncontrada = null;
        int longitud = listaDeposito.Longitud();

    for (int i = 0; i < longitud; i++) {

        if (i < longitud) { 
         Bicicleta bicicletaBuscar;
            bicicletaBuscar = (Bicicleta) listaDeposito.Obtener(i);
            if (bicicletaBuscar.getCodigo().equals(codigo)) {
                bicicletaEncontrada = bicicletaBuscar;
                break; 
        }
     }
    }


    if (bicicletaEncontrada == null) {
    return Retorno.error2();
    }

    /* if ("ALQUILADA".equals(bicicletaEncontrada.getEstado())) {
    return Retorno.error3();
    } Para la primer entrega no hay bicis alquiladas*/
    if (bicicletaEncontrada.getEstado() == Estado_Bicicleta.MANTENIMIENTO)
    {
     return Retorno.error4();
    }


    bicicletaEncontrada.setEstado(Estado_Bicicleta.MANTENIMIENTO);
    listaDeposito.Adicionar(bicicletaEncontrada); 

        return Retorno.ok();
    }

    
  //2.6. Reparar bicicleta
    @Override
    public Retorno repararBicicleta(String codigo) {
     if (codigo == null || codigo.trim().isEmpty()) {
        return Retorno.error1();
        }
    
        Bicicleta bicicletaEncontrada = null;
        int longitud = listaDeposito.Longitud();
    
    
        for (int i = 0; i < longitud; i++) {
        // Validar que el índice esté dentro del rango
        if (i >= 0 && i < longitud) {
            Bicicleta bicicletaBuscar = (Bicicleta) listaDeposito.Obtener(i);
            if (bicicletaBuscar.getCodigo().equals(codigo)) {
                bicicletaEncontrada = bicicletaBuscar;
                break; 
            }
        }
        }
    
        if (bicicletaEncontrada == null) {
        return Retorno.error2(); 
        }
    
        if (bicicletaEncontrada.getEstado() != Estado_Bicicleta.MANTENIMIENTO) {
          return Retorno.error3(); 
        }
 

        bicicletaEncontrada.setEstado(Estado_Bicicleta.DISPONIBLE);
        listaDeposito.Adicionar(bicicletaEncontrada); 
    
        return Retorno.ok();
    }

    
    
    //2.7
    @Override
    public Retorno eliminarEstacion(String nombre) {
       if(nombre== null || nombre.trim().isEmpty()){
       
       return Retorno.error1();
       
       
       }
       
       
       
       
       Estacion EstacionEncontrada = new Estacion();
       EstacionEncontrada.setNombre(nombre);
     boolean  existeEstacion = estaciones.existeElemento(EstacionEncontrada);
       
       
       if(!existeEstacion){
           return Retorno.error2();
       
       }
       
       
       if(ColaEspera.esVacia() ||EstacionEncontrada.getCntBicicletasAncladas() > 0 ){
       
           
             return Retorno.error3();
       
       }
       estaciones.borrarElemento(EstacionEncontrada);
       return Retorno.ok();
       
      
      
    }
    
    //2.8
    @Override
    public Retorno asignarBicicletaAEstacion(String codigo, String nombreEstacion) {
         if(codigo == null || codigo.trim().isEmpty() ||nombreEstacion == null || nombreEstacion.trim().isEmpty() ){
       
       return Retorno.error1();}

      Bicicleta bicicletaEncontrada = new Bicicleta();
       boolean biciExiste = bicicletas.existeElemento(bicicletaEncontrada);
       
       if(!biciExiste || bicicletaEncontrada.getEstado() != Estado_Bicicleta.DISPONIBLE ){
           return Retorno.error2();
       
       }
       
       Estacion estacionEncontrada = new Estacion ();
       estacionEncontrada.setNombre(nombreEstacion);
       
       boolean estacionExiste = estaciones.existeElemento(estacionEncontrada);
       
       if(!estacionExiste){
       
            return Retorno.error3();
       
       
       }
       // la lista de "Anclajes" es la lista de alquileres?
       if(!estacionEncontrada.tieneAnclajeLibre()){
       
       return Retorno.error4();
       
       }
   
        bicicletasAncladas.Adicionar(bicicletaEncontrada);// esta bien esto?
        return Retorno.ok();
      
    }

    // !!!! ver si sirve usar metodos auxiliares para buscar usuario por ci y bici por codigo??
    //2.9. Alquilar bicicleta:
    @Override
    public Retorno alquilarBicicleta(String cedula, String nombreEstacion) {

        // --- Validaciones básicas ---
        if (cedula == null || cedula.isEmpty() || nombreEstacion == null || nombreEstacion.isEmpty()) {
            return Retorno.error1(); // ERROR_1: parámetros inválidos
        }

        // --- Buscar usuario ---
        Usuario usuarioEncontrado = null;
        for (int i = 0; i < usuarios.Longitud() && usuarioEncontrado == null; i++) {
            Usuario u = usuarios.Obtener(i);
            if (u.getCedula().equalsIgnoreCase(cedula)) {
                usuarioEncontrado = u;
            }
        }

        if (usuarioEncontrado == null) {
            return Retorno.error2(); // ERROR_2: usuario inexistente
        }

        // --- Buscar estación ---
        Estacion estacionEncontrada = null;
        for (int i = 0; i < estaciones.Longitud() && estacionEncontrada == null; i++) {
            Estacion e = estaciones.Obtener(i);
            if (e.getNombre().equalsIgnoreCase(nombreEstacion)) {
                estacionEncontrada = e;
            }
        }

        if (estacionEncontrada == null) {
            return Retorno.error3(); // ERROR_3: estación inexistente
        }

        // --- Buscar bicicleta disponible ---
        Bicicleta biciDisponible = null;
        ListaSE<Bicicleta> bicicletas = estacionEncontrada.getBicicletas();

        for (int i = 0; i < bicicletas.Longitud() && biciDisponible == null; i++) {
            Bicicleta b = bicicletas.Obtener(i);
            if (b.getEstado() == Estado_Bicicleta.DISPONIBLE) {
                biciDisponible = b;
            }
        }

        //  Si hay bicicleta disponible, registrar alquiler ---
        if (biciDisponible != null) {
            biciDisponible.setEstado(Estado_Bicicleta.ALQUILADA);
            usuarioEncontrado.setBicicletaAlquilada(biciDisponible);

            Alquiler nuevo = new Alquiler();
            nuevo.setCedulaUsuario(usuarioEncontrado.getCedula());
            nuevo.setCodigoBicicleta(biciDisponible.getCodigo());
            nuevo.setNombreEstacionOrigen(estacionEncontrada.getNombre());
            nuevo.setFechaAlquiler(new Date()); // ?? consultar a la profe si está bien usar este metodo date???

            alquileres.Adicionar(nuevo);
            return Retorno.ok();
        }

        // --- Si no hay bicicletas disponibles ---
        estacionEncontrada.getColaEspera().encolar(usuarioEncontrado);
        return Retorno.ok("Usuario en espera");
    }





    //2.10. Devolver bicicleta:
    @Override
    public Retorno devolverBicicleta(String cedula, String nombreEstacionDestino) {

        // --- Validaciones básicas ---
        if (cedula == null || cedula.isEmpty() || nombreEstacionDestino == null || nombreEstacionDestino.isEmpty()) {
            return Retorno.error1(); // ERROR_1: parámetros inválidos
        }

        // --- Buscar usuario ---
        Usuario usuarioEncontrado = null;
        for (int i = 0; i < usuarios.Longitud() && usuarioEncontrado == null; i++) {
            Usuario u = usuarios.Obtener(i);
            if (u.getCedula().equalsIgnoreCase(cedula)) {
                usuarioEncontrado = u;
            }
        }

        // --- Verificar existencia y que tenga bici alquilada ---
        if (usuarioEncontrado == null || usuarioEncontrado.getBicicletaAlquilada() == null) {
            return Retorno.error2(); // ERROR_2: usuario inexistente o no tiene bici alquilada
        }

        // --- Buscar estación destino ---
        Estacion estacionDestino = null;
        for (int i = 0; i < estaciones.Longitud() && estacionDestino == null; i++) {
            Estacion e = estaciones.Obtener(i);
            if (e.getNombre().equalsIgnoreCase(nombreEstacionDestino)) {
                estacionDestino = e;
            }
        }

        if (estacionDestino == null) {
            return Retorno.error3(); // ERROR_3: estación inexistente
        }

        // --- Obtener la bici que el usuario está devolviendo ---
        Bicicleta biciDevuelta = usuarioEncontrado.getBicicletaAlquilada();

        // --- Si hay lugar libre, anclar directamente ---
        if (estacionDestino.tieneAnclajeLibre()) {

            // Marcar bici como disponible
            biciDevuelta.setEstado(Estado_Bicicleta.DISPONIBLE);
            biciDevuelta.setEstacionActual(estacionDestino);

            // Agregar bicicleta a la estación
            estacionDestino.getBicicletas().Adicionar(biciDevuelta);

            // Liberar al usuario (ya no tiene bici alquilada)
            usuarioEncontrado.setBicicletaAlquilada(null);

            // --- Si hay alguien esperando para alquilar en esta estación ---
            if (!estacionDestino.getColaEspera().esVacia()) {
                Usuario siguienteUsuario = estacionDestino.getColaEspera().desencolar();

                // Asignar bicicleta automáticamente
                biciDevuelta.setEstado(Estado_Bicicleta.ALQUILADA);
                siguienteUsuario.setBicicletaAlquilada(biciDevuelta);

                // Registrar el nuevo alquiler
                Alquiler nuevo = new Alquiler();
                nuevo.setCedulaUsuario(siguienteUsuario.getCedula());
                nuevo.setCodigoBicicleta(biciDevuelta.getCodigo());
                nuevo.setNombreEstacionOrigen(estacionDestino.getNombre());
                nuevo.setFechaAlquiler(new Date());
                alquileres.Adicionar(nuevo);
            }

            return Retorno.ok("Bicicleta devuelta correctamente");
        }

        // --- Si no hay lugar libre, usuario queda esperando por anclaje ---
        estacionDestino.getColaEspera().encolar(usuarioEncontrado);
        return Retorno.ok("Usuario en espera por anclaje");
    }

    //2.11 Deshacer últimos retiros:
    @Override
    public Retorno deshacerUltimosRetiros(int n) {
        // --- Validaciones ---
        if (n <= 0) {
            return Retorno.error1(); // ERROR_1: valor inválido
        }

        if (alquileres.Vacia()) {
            return Retorno.ok("No hay retiros para deshacer");
        }

        String resultado = "";
        int cantidadDeshechos = 0;

        // Determinar cuántos retiros se pueden realmente deshacer
        int total = alquileres.Longitud();
        int aDeshacer = Math.min(n, total);

        // Procesar desde el último hacia atrás
        for (int i = total - 1; i >= total - aDeshacer; i--) {
            Alquiler alquiler = alquileres.Obtener(i);

            // Buscar usuario
            Usuario usuarioEncontrado = null;
            for (int j = 0; j < usuarios.Longitud() && usuarioEncontrado == null; j++) {
                Usuario u = usuarios.Obtener(j);
                if (u.getCedula().equalsIgnoreCase(alquiler.getCedulaUsuario())) {
                    usuarioEncontrado = u;
                }
            }

            // Buscar estación origen
            Estacion estacionOrigen = null;
            for (int k = 0; k < estaciones.Longitud() && estacionOrigen == null; k++) {
                Estacion e = estaciones.Obtener(k);
                if (e.getNombre().equalsIgnoreCase(alquiler.getNombreEstacionOrigen())) {
                    estacionOrigen = e;
                }
            }

            // Buscar bicicleta
            Bicicleta biciDevuelta = null;
            for (int m = 0; m < bicicletas.Longitud() && biciDevuelta == null; m++) {
                Bicicleta b = bicicletas.Obtener(m);
                if (b.getCodigo().equalsIgnoreCase(alquiler.getCodigoBicicleta())) {
                    biciDevuelta = b;
                }
            }

            // Si todos los objetos están OK, revertimos el alquiler
            if (usuarioEncontrado != null && estacionOrigen != null && biciDevuelta != null) {
                // Intentar devolver la bici a la estación de origen
                if (estacionOrigen.tieneAnclajeLibre()) {
                    biciDevuelta.setEstado(Estado_Bicicleta.DISPONIBLE);
                    biciDevuelta.setEstacionActual(estacionOrigen);
                    estacionOrigen.getBicicletas().Adicionar(biciDevuelta);
                } else {
                    estacionOrigen.getColaEspera().encolar(usuarioEncontrado);
                }

                // Liberar la bici del usuario
                usuarioEncontrado.setBicicletaAlquilada(null);

                // Quitar el alquiler de la lista
                alquileres.Eliminar(i);

                // Agregar al string de resultado
                if (cantidadDeshechos > 0) {
                    resultado = "|" + resultado;
                }
                resultado = alquiler.getCodigoBicicleta() + "#" +
                            alquiler.getCedulaUsuario() + "#" +
                            alquiler.getNombreEstacionOrigen() + resultado;

                cantidadDeshechos++;
            }
        }

        return Retorno.ok(resultado);
    }


    //3.1. Obtener Usuario:
    @Override 
    public Retorno obtenerUsuario(String cedula) {
        
        // validamos que no se null o vacío:
        if (cedula == null || cedula.trim().isEmpty()) {
         return Retorno.error1(); 
        }

        // validamos que la cédula tenga 8 dígitos
        if (!cedula.trim().matches("\\d{8}")) {
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

    //3.2. Listar usuarios:
    @Override
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
  System.out.print(resultado);
   return new Retorno(Resultado.OK, resultado);
     
        
    }

    //3.3. Listar bicis en depósito:
    @Override
    public Retorno listarBicisEnDeposito() {
       
        String resultado = "";
        
        for(int i = 0; i<listaDeposito.Longitud();i++){
        
          Bicicleta bicicleta = listaDeposito.Obtener(i);
          
          
        if (i > 0) {
            resultado += "|";
        }
        
        
   
        resultado += bicicleta.toString();
        }
        
             return new Retorno(Resultado.OK, resultado);
         
        
    }
    

    //3.4. Mapa de estaciones:
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
             if (mapa[i][j] != null && !mapa[i][j].trim().isEmpty() && !mapa[i][j].trim().equals("o")) {
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


    //3.5
    @Override
    public Retorno listarBicicletasDeEstacion(String nombreEstacion) {
        
        Estacion estacion = new Estacion ();
        
      Estacion  estacionAux = estaciones.Obtener(0);
      // obtenemos la estacion que se esta buscando y luego retornamos las bicicletas
      
    ListaSE<Bicicleta> bicicletasAListar =  estacionAux.getBicicletas();
  //  for(int i = 0; i<)
        
        
        
        
        return Retorno.noImplementada();
    }
//3.6
    @Override
    public Retorno estacionesConDisponibilidad(int n) {
        return Retorno.noImplementada();
    }
//3.7
    @Override
    public Retorno ocupacionPromedioXBarrio() {
        return Retorno.noImplementada();
    }
//3.8
    @Override
    public Retorno rankingTiposPorUso() {
        return Retorno.noImplementada();
    }
//3.9
    @Override
    public Retorno usuariosEnEspera(String nombreEstacion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno usuarioMayor() {
        return Retorno.noImplementada();
    }

    

}
