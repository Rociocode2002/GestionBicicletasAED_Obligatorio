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
            alquileres = new ListaSE<Alquiler>();
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
        bicicletas.Adicionar(nueva);// lista global de bicicletas
        

        return Retorno.ok();
    }

    
    //2.5. Poner bicicleta en mantenimiento:
   @Override
public Retorno marcarEnMantenimiento(String codigo, String motivo) {

    // Validaciones iniciales
    if (codigo == null || motivo == null || codigo.trim().isEmpty() || motivo.trim().isEmpty()) {
        return Retorno.error1();
    }

    Bicicleta bicicletaEncontrada = null;

    // --------------------------------------------------------
    // 1) Buscar en la lista general de bicicletas
    // --------------------------------------------------------
    for (int i = 0; i < bicicletas.Longitud(); i++) {
        Bicicleta b = bicicletas.Obtener(i);
        if (b.getCodigo().equalsIgnoreCase(codigo)) {
            bicicletaEncontrada = b;
            break;
        }
    }

    // --------------------------------------------------------
    // 2) Buscar en todas las estaciones
    // --------------------------------------------------------
    if (bicicletaEncontrada == null) {
        for (int i = 0; i < estaciones.Longitud(); i++) {
            Estacion est = estaciones.Obtener(i);

            for (int j = 0; j < est.getBicicletas().Longitud(); j++) {
                Bicicleta b = est.getBicicletas().Obtener(j);
                if (b.getCodigo().equalsIgnoreCase(codigo)) {
                    bicicletaEncontrada = b;
                    break;
                }
            }

            if (bicicletaEncontrada != null)
                break;
        }
    }

    // --------------------------------------------------------
    // 3) Buscar en el depósito
    // --------------------------------------------------------
    if (bicicletaEncontrada == null) {
        for (int i = 0; i < listaDeposito.Longitud(); i++) {
            Bicicleta b = listaDeposito.Obtener(i);
            if (b.getCodigo().equalsIgnoreCase(codigo)) {
                bicicletaEncontrada = b;
                break;
            }
        }
    }

    // Si no se encontró en ningún lado
    if (bicicletaEncontrada == null) {
        return Retorno.error2(); // bicicleta inexistente
    }

    // Ya está en mantenimiento
    if (bicicletaEncontrada.getEstado() == Estado_Bicicleta.MANTENIMIENTO) {
        return Retorno.error4();
    }

    // --------------------------------------------------------
    // Si está en una estación, removerla
    // --------------------------------------------------------
    if (bicicletaEncontrada.getEstacionActual() != null) {
        Estacion est = bicicletaEncontrada.getEstacionActual();
        est.getBicicletas().borrarElemento(bicicletaEncontrada);
        bicicletaEncontrada.setEstacionActual(null);
    }

    // --------------------------------------------------------
    // Marcar estado y enviar a depósito
    // --------------------------------------------------------
    bicicletaEncontrada.setEstado(Estado_Bicicleta.MANTENIMIENTO);

    // Evitar duplicación si ya estaba en depósito
    if (!listaDeposito.existeElemento(bicicletaEncontrada)) {
        listaDeposito.Adicionar(bicicletaEncontrada);
    }

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

    
    
    //2.7 Eliminar Estación:
    @Override
    public Retorno eliminarEstacion(String nombre) {
       if(nombre== null || nombre.trim().isEmpty()){
       
       return Retorno.error1();
       
       
       }
       
        // Buscar la estación real en lugar de crear una nueva
    Estacion estacionEncontrada = null;
    for (int i = 0; i < estaciones.Longitud(); i++) {
        Estacion estacionActual = estaciones.Obtener(i);
        if (estacionActual.getNombre().equals(nombre)) {
            estacionEncontrada = estacionActual;
            
        }
    }
     
       
       if(estacionEncontrada == null){
           return Retorno.error2();
       
       }
     Cola<Usuario> colaEspera = estacionEncontrada.getColaEspera();
    ListaSE<Bicicleta> bicicletas = estacionEncontrada.getBicicletas();
    
    // Verificar si hay usuarios en espera (manejando null)
    boolean tieneUsuariosEnEspera = (colaEspera != null && !colaEspera.esVacia());
    
    // Verificar si tiene bicicletas ancladas (manejando null)
    boolean tieneBicicletas = (bicicletas != null && bicicletas.Longitud() > 0);
    
    if (tieneUsuariosEnEspera || tieneBicicletas) {
        return Retorno.error3();
    }
    
    // Eliminar la estación
    estaciones.borrarElemento(estacionEncontrada);
    return Retorno.ok();
    }
    
    
    
    //2.8 Asignar bicicleta a estación:
   @Override
public Retorno asignarBicicletaAEstacion(String codigo, String nombreEstacion) {
   
    if (codigo == null || codigo.trim().isEmpty() || nombreEstacion == null || nombreEstacion.trim().isEmpty()) {
        return Retorno.error1();
    }

    
    Bicicleta bicicleta = null;
    for (int i = 0; i < bicicletas.Longitud() && bicicleta == null; i++) {
        Bicicleta bici = bicicletas.Obtener(i);
        if (bici.getCodigo().equals(codigo) && bici.getEstado() == Estado_Bicicleta.DISPONIBLE) {
            bicicleta = bici;
        }
    }
    
    for (int i = 0; i < estaciones.Longitud() && bicicleta == null; i++) {
        Estacion estacion = estaciones.Obtener(i);
        for (int j = 0; j < estacion.getBicicletas().Longitud() && bicicleta == null; j++) {
            Bicicleta bici = estacion.getBicicletas().Obtener(j);
            if (bici.getCodigo().equals(codigo) && bici.getEstado() == Estado_Bicicleta.DISPONIBLE) {
                bicicleta = bici;
            }
        }
    }
    
    if (bicicleta == null) {
        return Retorno.error2();
    }

    // Buscar estación destino
    Estacion estacionDestino = null;
    for (int i = 0; i < estaciones.Longitud() && estacionDestino == null; i++) {
        Estacion estacion = estaciones.Obtener(i);
        if (estacion.getNombre().equals(nombreEstacion)) {
            estacionDestino = estacion;
        }
    }
    
    if (estacionDestino == null) {
        return Retorno.error3();
    }

    
    boolean yaEstaEnDestino = false;
    for (int i = 0; i < estacionDestino.getBicicletas().Longitud() && !yaEstaEnDestino; i++) {
        if (estacionDestino.getBicicletas().Obtener(i).getCodigo().equals(codigo)) {
            yaEstaEnDestino = true;
        }
    }
    if (yaEstaEnDestino) {
        return Retorno.ok();
    }

  
    if (estacionDestino.getBicicletas().Longitud() >= estacionDestino.getCapacidad()) {
        return Retorno.error4();
    }

   
    bicicletas.borrarElemento(bicicleta);
    for (int i = 0; i < estaciones.Longitud(); i++) {
        estaciones.Obtener(i).getBicicletas().borrarElemento(bicicleta);
    }
    estacionDestino.getBicicletas().AdicionarOrdenado(bicicleta);
    

    return Retorno.ok();
}


    //2.9. Alquilar bicicleta:
    @Override
    public Retorno alquilarBicicleta(String cedula, String nombreEstacion) {

        if (cedula == null || cedula.isEmpty() || nombreEstacion == null || nombreEstacion.isEmpty()) {
            return Retorno.error1(); // Parámetros inválidos
        }

        Usuario usuario = buscarUsuarioPorCedula(cedula);
        if (usuario == null) {
            return Retorno.error2(); // Usuario inexistente
        }

        Estacion estacion = buscarEstacionPorNombre(nombreEstacion);
        if (estacion == null) {
            return Retorno.error3(); // Estación inexistente
        }

        // Buscar bicicleta disponible
        Bicicleta biciDisponible = null;
        ListaSE<Bicicleta> bicisEstac = estacion.getBicicletas();
        for (int i = 0; i < bicisEstac.Longitud() && biciDisponible == null; i++) {
            Bicicleta b = bicisEstac.Obtener(i);
            if (b.getEstado() == Estado_Bicicleta.DISPONIBLE) {
                biciDisponible = b;
            }
        }

        if (biciDisponible == null) {
            estacion.getColaEspera().encolar(usuario);
            return Retorno.ok("Usuario en espera");
        }

        // Registrar alquiler
        biciDisponible.setEstado(Estado_Bicicleta.ALQUILADA);
        usuario.setBicicletaAlquilada(biciDisponible);
        registrarAlquiler(usuario, biciDisponible, estacion);

        return Retorno.ok();
    }

    //2.10 Devolver Bicicleta:
    @Override
    public Retorno devolverBicicleta(String cedula, String nombreEstacionDestino) {

        // 1) Validación de datos
        if (cedula == null || cedula.isEmpty() || 
            nombreEstacionDestino == null || nombreEstacionDestino.isEmpty()) {
            return Retorno.error1();
        }

        // 2) Usuario existente
        Usuario usuario = buscarUsuarioPorCedula(cedula);
        if (usuario == null) {
            return Retorno.error2(); // Usuario inexistente
        }

        // 3) Usuario tiene bicicleta alquilada
        if (usuario.getBicicletaAlquilada() == null) {
            return Retorno.error2(); // Usuario sin bici
        }

        // 4) Estación destino existente
        Estacion estacionDestino = buscarEstacionPorNombre(nombreEstacionDestino);
        if (estacionDestino == null) {
            return Retorno.error3(); // Estación inexistente
        }

        // ---- LÓGICA DE DEVOLUCIÓN ---- //
        Bicicleta biciDevuelta = usuario.getBicicletaAlquilada();

        // 5) Hay lugar para anclar
        if (estacionDestino.tieneAnclajeLibre()) {

            biciDevuelta.setEstado(Estado_Bicicleta.DISPONIBLE);
            biciDevuelta.setEstacionActual(estacionDestino);

            estacionDestino.getBicicletas().Adicionar(biciDevuelta);
            usuario.setBicicletaAlquilada(null);

            // 5b) Si alguien estaba esperando, asignar bici automáticamente
            if (estacionDestino.getColaEspera() != null && 
                !estacionDestino.getColaEspera().esVacia()) {

                Usuario siguiente = estacionDestino.getColaEspera().desencolar();
                biciDevuelta.setEstado(Estado_Bicicleta.ALQUILADA);
                siguiente.setBicicletaAlquilada(biciDevuelta);

                registrarAlquiler(siguiente, biciDevuelta, estacionDestino);
            }

            return Retorno.ok("Bicicleta devuelta correctamente");
        }

        // 6) No hay lugar → el usuario entra en la cola de espera para anclar
        estacionDestino.getColaEspera().encolar(usuario);
        return Retorno.ok("Usuario en espera por anclaje");
    }

    


    //2.11 Deshacer últimos retiros:
    @Override
    public Retorno deshacerUltimosRetiros(int n) {
        if (n <= 0) {
            return Retorno.error1();
        }

        if (alquileres.Vacia()) {
            return Retorno.ok("No hay retiros para deshacer");
        }

        StringBuilder sb = new StringBuilder();
        int total = alquileres.Longitud();
        int aDeshacer = Math.min(n, total);

        for (int i = total - 1; i >= total - aDeshacer; i--) {
            Alquiler alquiler = alquileres.Obtener(i);

            Usuario usuario = buscarUsuarioPorCedula(alquiler.getCedulaUsuario());
            Estacion estacion = buscarEstacionPorNombre(alquiler.getNombreEstacionOrigen());
            Bicicleta bici = buscarBicicletaPorCodigo(alquiler.getCodigoBicicleta());

            if (usuario != null && estacion != null && bici != null) {
                if (estacion.tieneAnclajeLibre()) {
                    bici.setEstado(Estado_Bicicleta.DISPONIBLE);
                    bici.setEstacionActual(estacion);
                    estacion.getBicicletas().Adicionar(bici);
                } else {
                    estacion.getColaEspera().encolar(usuario);
                }

                usuario.setBicicletaAlquilada(null);
                alquileres.Eliminar(i);

                if (sb.length() > 0) sb.insert(0, "|");
                sb.insert(0, alquiler.getCodigoBicicleta() + "#" + alquiler.getCedulaUsuario() + "#" + alquiler.getNombreEstacionOrigen());
            }
        }

        return Retorno.ok(sb.toString());
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
    String resultado = listarBicisRecursivo(0, "");
    return new Retorno(Resultado.OK, resultado);
}

private String listarBicisRecursivo(int indice, String acumulador) {
    // Caso base: cuando llegamos al final de la lista
    if (indice >= listaDeposito.Longitud()) {
        return acumulador;
    }
    
    
    Bicicleta bicicleta = listaDeposito.Obtener(indice);
    
    
    if (indice > 0) {
        acumulador += "|";
    }
    
    acumulador += bicicleta.toString();
    
   
    return listarBicisRecursivo(indice + 1, acumulador);
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


    //3.5 Listar bicis de estación:
    @Override
    public Retorno listarBicicletasDeEstacion(String nombreEstacion) {
        
        Estacion estacionEncontrada = null;
        
     
      
      for(int i = 0; i < estaciones.Longitud(); i++) {
            Estacion estacionActual = estaciones.Obtener(i);
            if(estacionActual.getNombre().equals(nombreEstacion)) {
                estacionEncontrada = estacionActual;
                
            }
        }
        ListaSE<Bicicleta> bicicletasAlistar = estacionEncontrada.getBicicletas();
      
      String resultado = "";
        for(int i = 0; i < bicicletasAlistar.Longitud(); i++) {
            Bicicleta bicicleta = bicicletasAlistar.Obtener(i);
            resultado += bicicleta.getCodigo();
            
            if(i < bicicletasAlistar.Longitud() - 1) {
                resultado += "|";
            }
        }
      
        return Retorno.ok(resultado);
    }
    
    
    //3.6 Estaciones con disponibilidad mayor:
   
    @Override
    //indicar cantidad de estaciones que cuentan con una disponibilidad mayor a n
        public Retorno estacionesConDisponibilidad(int n) {

        if (n <= 1) {
            return Retorno.error1();
        }

        int contador = 0;

        for (int i = 0; i < estaciones.Longitud(); i++) {

            Estacion estacion = estaciones.Obtener(i);
            int disponibles = 0;

            // Contar solo bicicletas en estado DISPONIBLE
            ListaSE<Bicicleta> bicis = estacion.getBicicletas();

            for (int j = 0; j < bicis.Longitud(); j++) {
                Bicicleta b = bicis.Obtener(j);
                if (b.getEstado() == Estado_Bicicleta.DISPONIBLE) {
                    disponibles++;
                }
            }

            // Verificar si superan el límite
            if (disponibles > n) {
                contador++;
            }
        }

        return Retorno.ok(contador);
    }
    
    
    //3.7 Ocupación promedio por barrio
    
    @Override
    public Retorno ocupacionPromedioXBarrio() {

        // 1) Obtener barrios únicos
        ListaSE<String> barrios = obtenerBarriosUnicos();

        // 2) Ordenar los barrios con Bubble Sort
        bubbleSortListaSE(barrios);

        // 3) Construir salida
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < barrios.Longitud(); i++) {
            String barrio = barrios.Obtener(i);

            int capacidad = capacidadTotalBarrio(barrio);
            int ancladas = bicisAncladasBarrio(barrio);

            int porcentaje = (capacidad > 0)
                    ? Math.round((ancladas * 100f) / capacidad)
                    : 0;

            sb.append(barrio).append("#").append(porcentaje);

            if (i < barrios.Longitud() - 1) sb.append("|");
        }

        return Retorno.ok(sb.toString());
    }

    
    //3.8 Ranking por tipo de uso:
    @Override
    public Retorno rankingTiposPorUso() {
        int totalUrbana = 0;
        int totalMountain = 0;
        int totalElectrica = 0;

        // Contar alquileres por tipo
        for (int i = 0; i < alquileres.Longitud(); i++) {
            Alquiler a = alquileres.Obtener(i);
            Bicicleta bici = buscarBicicletaPorCodigo(a.getCodigoBicicleta());
            if (bici != null) {
                switch (bici.getTipo()) {
                    case URBANA:
                        totalUrbana++;
                        break;
                    case MOUNTAIN:
                        totalMountain++;
                        break;
                    case ELECTRICA:
                        totalElectrica++;
                        break;
                }
            }
        }

        // Arreglo paralelo tipo / cantidad
        String[] tipos = {"URBANA", "MOUNTAIN", "ELECTRICA"};
        int[] cantidades = {totalUrbana, totalMountain, totalElectrica};

        // Ordenamiento con BubleSort descendente (por cantidad, y luego alfabético)
        for (int i = 0; i < 2; i++) {
            for (int j = i + 1; j < 3; j++) {
                if (cantidades[j] > cantidades[i] ||
                   (cantidades[j] == cantidades[i] && tipos[j].compareToIgnoreCase(tipos[i]) < 0)) {
                    // Intercambiar
                    int tempCant = cantidades[i];
                    cantidades[i] = cantidades[j];
                    cantidades[j] = tempCant;

                    String tempTipo = tipos[i];
                    tipos[i] = tipos[j];
                    tipos[j] = tempTipo;
                }
            }
        }

        // Construir resultado
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(tipos[i]).append("#").append(cantidades[i]);
            if (i < 2) sb.append("|");
        }

        return Retorno.ok(sb.toString());
    }

    
    //3.9 Usuarios en espera por alquiler:
    @Override
    public Retorno usuariosEnEspera(String nombreEstacion) {
        // Buscar la estación por nombre
        Estacion estacionEncontrada = null;
        
        for(int i = 0; i < estaciones.Longitud(); i++) {
            Estacion estacionActual = estaciones.Obtener(i);
            if(estacionActual.getNombre().equals(nombreEstacion)) {
                estacionEncontrada = estacionActual;
                
            }
        }
        
        Cola<Usuario> usuariosEnEspera = estacionEncontrada.getColaEspera();
         
        String resultado =  "";
        Cola<Usuario> colaAuxiliar = new Cola<>();
        boolean primerElemento = true;
         
        while(!usuariosEnEspera.esVacia()) {
          Usuario usuario = usuariosEnEspera.desencolar();
          if(!primerElemento) {
              resultado += "|";
            } 
            else {
              primerElemento = false;
            }
            resultado += usuario.getCedula(); // Obtener la CI del usuario
            colaAuxiliar.encolar(usuario); //
        }
        while(!colaAuxiliar.esVacia()) {
           usuariosEnEspera.encolar(colaAuxiliar.desencolar());
        }
        
        return Retorno.ok(resultado);
     
    }

   //3.10 Usuario con mayor cantidad de alquileres:
    @Override
    public Retorno usuarioMayor() {
        // Dos listas paralelas: una para cédulas y otra para los contadores
        ListaSE<String> cedulas = new ListaSE<>();
        ListaSE<Integer> conteos = new ListaSE<>();

        // --- Contar los alquileres por usuario ---
        for (int i = 0; i < alquileres.Longitud(); i++) {
            Alquiler a = alquileres.Obtener(i);
            String ci = a.getCedulaUsuario();

            boolean encontrado = false;
            for (int j = 0; j < cedulas.Longitud() && !encontrado; j++) {
                if (cedulas.Obtener(j).equalsIgnoreCase(ci)) {
                    // Actualizar contador
                    int nuevoValor = conteos.Obtener(j) + 1;
                    conteos.Eliminar(j);          // Eliminar viejo
                    conteos.Insertar(nuevoValor, j); // Insertar nuevo en misma posición
                    encontrado = true;
                }
            }

            // Si no estaba, agregarlo
            if (!encontrado) {
                cedulas.Adicionar(ci);
                conteos.Adicionar(1);
            }
        }

        // --- Buscar el usuario con mayor cantidad ---
        String cedulaMayor = cedulas.Obtener(0);
        int max = conteos.Obtener(0);

        for (int i = 1; i < cedulas.Longitud(); i++) {
            String ci = cedulas.Obtener(i);
            int cantidad = conteos.Obtener(i);

            if (cantidad > max) {
                max = cantidad;
                cedulaMayor = ci;
            } else if (cantidad == max && ci.compareToIgnoreCase(cedulaMayor) < 0) {
                // Desempate: cédula más pequeña alfabéticamente
                cedulaMayor = ci;
            }
        }

        return Retorno.ok(cedulaMayor);
    }


    //metodos auxiliares usados en la resolución:
    private Bicicleta buscarBicicletaPorCodigo(String codigo) {
        for (int i = 0; i < estaciones.Longitud(); i++) {
            Estacion est = estaciones.Obtener(i);
            ListaSE<Bicicleta> bicis = est.getBicicletas();
            for (int j = 0; j < bicis.Longitud(); j++) {
                Bicicleta b = bicis.Obtener(j);
                if (b.getCodigo().equalsIgnoreCase(codigo)) {
                    return b;
                }
            }
        }
        return null;
    }
    
        private Usuario buscarUsuarioPorCedula(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) return null;
        for (int i = 0; i < usuarios.Longitud(); i++) {
            Usuario u = usuarios.Obtener(i);
            if (u.getCedula().equalsIgnoreCase(cedula)) {
                return u;
            }
        }
        return null;
    }

    private Estacion buscarEstacionPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) return null;
        for (int i = 0; i < estaciones.Longitud(); i++) {
            Estacion e = estaciones.Obtener(i);
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }

    private void registrarAlquiler(Usuario usuario, Bicicleta bici, Estacion estacion) {
        Alquiler nuevo = new Alquiler();
        nuevo.setCedulaUsuario(usuario.getCedula());
        nuevo.setCodigoBicicleta(bici.getCodigo());
        nuevo.setNombreEstacionOrigen(estacion.getNombre());
        nuevo.setFechaAlquiler(new Date());
        alquileres.Adicionar(nuevo);
    }
    
     private ListaSE<String> obtenerBarriosUnicos() {
        ListaSE<String> lista = new ListaSE<>();

        for (int i = 0; i < estaciones.Longitud(); i++) {
            String barrio = estaciones.Obtener(i).getBarrio();
            if (!lista.existeElemento(barrio)) {
                lista.Adicionar(barrio);
            }
        }

        return lista;
    }
     
    private int capacidadTotalBarrio(String barrio) {
        int total = 0;

        for (int i = 0; i < estaciones.Longitud(); i++) {
            Estacion e = estaciones.Obtener(i);
            if (e.getBarrio().equalsIgnoreCase(barrio)) {
                total += e.getCapacidad();
            }
        }

        return total;
    }
    private int bicisAncladasBarrio(String barrio) {
        int total = 0;

        for (int i = 0; i < estaciones.Longitud(); i++) {
            Estacion e = estaciones.Obtener(i);

            if (e.getBarrio().equalsIgnoreCase(barrio)) {
                for (int j = 0; j < e.getBicicletas().Longitud(); j++) {
                    Bicicleta b = e.getBicicletas().Obtener(j);
                    if (b.getEstado() != Estado_Bicicleta.MANTENIMIENTO) {
                        total++;
                    }
                }
            }
        }

        return total;
    }
    private void bubbleSortListaSE(ListaSE<String> lista) {
        int n = lista.Longitud();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                String a = lista.Obtener(j);
                String b = lista.Obtener(j + 1);

                if (a.compareToIgnoreCase(b) > 0) {
                    // Swap usando Eliminar + Insertar
                    lista.Eliminar(j);
                    lista.Insertar(a, j + 1);
                }
            }
        }
    }





}
