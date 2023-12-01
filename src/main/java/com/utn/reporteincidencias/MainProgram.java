package com.utn.reporteincidencias;

import controlador.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import modelo.Cliente;
import modelo.Especialidad;
import modelo.OperadorMesaAyuda;
import modelo.ReporteIncidencia;
import modelo.Servicio;
import modelo.Tecnico;
import vista.ClienteVista;
import vista.OperadorVista;
import vista.ReporteIncidenciaVista;
import vista.TecnicoVista;

/**
 *
 * @author Juan Pablo Lopez
 */
public class MainProgram {

    public static void main(String[] args) {

        try {

            login();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void login() throws Exception {
        do {
            System.out.println("SISTEMA DE REPORTE DE INCIDENCIAS");
            System.out.println("1 - Ingresar al sistema");
            System.out.println("2 - Salir");
            int opcion = new Scanner(System.in).nextInt();
            int legajo;
            if (opcion == 1) {
                System.out.println("\n\nLogIn");
                System.out.println("1 - Ingreso Administrador");
                System.out.println("2 - Ingreso Tecnico");
                System.out.println("3 - Ingreso Operador");
                System.out.println("4 - Atras");
                opcion = new Scanner(System.in).nextInt();
                switch (opcion) {
                    case 1:
                        menuAdministrador();
                        break;
                    case 2:
                        System.out.println("Ingrese su legajo (1001, 1002, 1003, 1004)");
                        legajo = new Scanner(System.in).nextInt();
                        GestorTecnico gTecnico = new GestorTecnico();
                        Tecnico tecnico = gTecnico.getTecnicoXLegajo(legajo);
                        if (tecnico == null) {
                            System.out.println("Acceso incorrecto");
                        } else {
                            System.out.println( "\n\nBienvenido " + tecnico.getNombreCompleto());
                            menuTecnico(legajo);
                        }
                        break;
                    case 3:
                        System.out.println("\n\nIngrese su legajo (2001)");
                        legajo = new Scanner(System.in).nextInt();
                        GestorOperador gOperador = new GestorOperador();
                        OperadorMesaAyuda operador = gOperador.getOperadorXLegajo(legajo);
                        if (operador == null) {
                            System.out.println("Acceso incorrecto");
                        } else {
                            System.out.println("Bienvenido " + operador.getNombreCompleto());
                            menuOperadorMesaAyuda(legajo);
                        }
                        break;
                    default:
                        System.out.println("");
                        ;
                }
            } else if (opcion == 2) {
                System.out.println("Adios");
                System.exit(0);
            } else {
                System.out.println("Opcion incorrecta\n\n");
            }
        } while (true);
    }

    public static void menuAdministrador() {

        do {

            System.out.println("\n\n\nMENU ADMINISTRADOR");
            System.out.println("1 - Administrar Tecnico");
            System.out.println("2 - Administrar OperadorMesaAyuda");
            System.out.println("3 - Administrar Servicio");
            System.out.println("4 - Administrar Especialidad");
            System.out.println("5 - Cerrar Sesion");
            int opcion = new Scanner(System.in).nextInt();
            switch (opcion) {
                case 1:
                    abmTecnico();
                    break;
                case 2:
                    abmOperador();
                    break;
                case 3:
                    abmServicio();
                    break;
                case 4:
                    abmEspecialidad();
                    break;
                case 5:
                    System.out.println("Hasta luego!\n\n\n");
                    return;
                default:
                    System.out.println("Opcion incorrecta.");
            }
        } while (true);
    }

    public static void menuTecnico(int legajoIngresado) throws Exception {
        GestorTecnico gTecnico = new GestorTecnico();
        Tecnico tecnico = gTecnico.getTecnicoXLegajo(legajoIngresado);
        List<ReporteIncidencia> reportes;

        do {
            System.out.println("\n\n\nMENU TECNICO");
            System.out.println("1 - Consultar todos los Reportes de Incidencia");
            System.out.println("2 - Consultar Reportes de Incidencia Pendientes");
            System.out.println("3 - Cambiar Estado a Reporte de Incidencia");
            System.out.println("4 - Cerrar Sesion");
            int opcion = new Scanner(System.in).nextInt();
            switch (opcion) {
                case 1:
                    reportes = tecnico.getReportesIncidencia();
                    if (reportes.size() == 0) {
                        System.out.println("No hay reportes");
                        break;
                    }
                    System.out.println("Reportes de incidencia atendidos por " + tecnico.getNombreCompleto());
                    for (ReporteIncidencia reporte : reportes) {
                        System.out.println(reporte.getInformacionReporte());
                    }
                    break;
                case 2:
                    reportes = tecnico.getReportesIncidencia();
                    if (reportes.size() == 0) {
                        System.out.println("No hay reportes");
                        break;
                    }
                    List<ReporteIncidencia>listaPendientes = new ArrayList<>();
                    for (ReporteIncidencia reporte : reportes) {
                        if (reporte.getEstado().equalsIgnoreCase("PENDIENTE")) {
                            listaPendientes.add(reporte);
                        }
                    }
                    if (listaPendientes.size() == 0) {
                        System.out.println("No hay reportes");
                        break;
                    }
                    for (ReporteIncidencia listaPendiente : listaPendientes) {
                        System.out.println(listaPendiente.getInformacionReporte());
                    }
                    break;
                case 3:
                    reportes = tecnico.getReportesIncidencia();
                    if (reportes.size() == 0) {
                        System.out.println("No hay reportes");
                        break;
                    }
                    int i = 1;
                    Map<Integer, ReporteIncidencia> mapa = new HashMap<>();
                    for (ReporteIncidencia reporte : reportes) {
                        if (reporte.getEstado().equalsIgnoreCase("PENDIENTE") || reporte.getEstado().equalsIgnoreCase("EN PROCESO")) {
                            mapa.put(i, reporte);
                            i++;
                        }
                    }
                    if (mapa.size() == 0) {
                        System.out.println("No hay reportes");
                        break;
                    }
                    System.out.println("Seleccione un reporte de incidencia pendiente de resolucion");
                    for (Map.Entry<Integer, ReporteIncidencia> entry : mapa.entrySet()) {
                        Integer key = entry.getKey();
                        ReporteIncidencia value = entry.getValue();
                        System.out.println(key + " - " + value.getInformacionReporte());
                    }
                    int numeroRepInc = new Scanner(System.in).nextInt();
                    ReporteIncidencia reporteIncidencia = mapa.get(numeroRepInc);

                    System.out.println("Seleccione una opcion. (Estado actual: "+reporteIncidencia.getEstado()+")");
                    System.out.println("1 - PENDIENTE");
                    System.out.println("2 - EN PROCESO");
                    System.out.println("3 - ANULADO");
                    i = new Scanner(System.in).nextInt();
                    reporteIncidencia.setEstado(((i == 1) ? "PENDIENTE" : ((i == 2) ? "EN PROCESO" : "ANULADO")));
                    GestorReporteIncidencia gReporteIncidencia = new GestorReporteIncidencia();
                    gReporteIncidencia.guardar(reporteIncidencia);
                    System.out.println("Se modifico el reporte de incidencia");
                    break;
                case 4:
                    System.out.println("Hasta luego!\n\n\n");
                    return;
                default:
                    System.out.println("Opcion incorrecta.");
            }
        } while (true);
    }

    public static void menuOperadorMesaAyuda(int legajoIngresado) {

        do {

            System.out.println("\n\n\nMENU OPERARIO");
            System.out.println("1 - Administrar Cliente");
            System.out.println("2 - Administrar Reporte de Incidencia");
            System.out.println("3 - Realizar consultas");
            System.out.println("4 - Cerrar Sesion");
            int opcion = new Scanner(System.in).nextInt();
            switch (opcion) {
                case 1:
                    abmCliente();
                    break;
                case 2:
                    abmReporteIncidencia(legajoIngresado);
                    break;
                case 3:
                    menuConsultas();
                    break;
                case 4:
                    System.out.println("Hasta luego!\n\n\n");
                    return;
                default:
                    System.out.println("Opcion incorrecta.");
            }
        } while (true);
    }

    public static void abmTecnico() {
        System.out.println("\nSELECCIONE UNA OPCION");
        System.out.println("1 - Cargar Tecnico nuevo");
        System.out.println("2 - Modificar Tecnico");
        System.out.println("3 - Eliminar Tecnico");
        System.out.println("4 - Atras");
        int opcion = new Scanner(System.in).nextInt();
        switch (opcion) {
            case 1:
                try {

                    GestorTecnico gTecnico = new GestorTecnico();
                    System.out.println("Ingrese el legajo del tecnico");
                    Integer legajoTecnico = new Scanner(System.in).nextInt();
                    Tecnico tecnico = gTecnico.getTecnicoXLegajo(legajoTecnico);
                    TecnicoVista vistaTecnico = new TecnicoVista();
                    if (tecnico != null) {

                        System.out.println("El tecnico " + tecnico.getNombreCompleto() + " ya se encuentra cargado en el sistema");
                        return;
                    }
                    tecnico = vistaTecnico.cargarTecnicoNuevo(legajoTecnico);
                    gTecnico.guardar(tecnico);
                    System.out.println("El tecnico " + tecnico.getNombreCompleto() + " se cargo exitosamente");
                } catch (Exception ex) {

                    ex.printStackTrace();
                }
                break;
            case 2:
                try {

                    GestorTecnico gTecnico = new GestorTecnico();
                    System.out.println("Ingrese el legajo del tecnico");
                    Integer legajoTecnico = new Scanner(System.in).nextInt();
                    Tecnico tecnico = gTecnico.getTecnicoXLegajo(legajoTecnico);
                    TecnicoVista vistaTecnico = new TecnicoVista();
                    if (tecnico == null) {

                        System.out.println("El tecnico NO se encuentra cargado en el sistema");
                        return;
                    }
                    tecnico = vistaTecnico.modificarTecnico(tecnico, legajoTecnico);
                    gTecnico.guardar(tecnico);
                    System.out.println("Se modifico el tecnico");
                } catch (Exception ex) {

                    ex.printStackTrace();
                }
                break;
            case 3:
                try {

                    GestorTecnico gTecnico = new GestorTecnico();
                    System.out.println("Ingrese el legajo del tecnico");
                    Integer legajoTecnico = new Scanner(System.in).nextInt();
                    Tecnico tecnico = gTecnico.getTecnicoXLegajo(legajoTecnico);
                    TecnicoVista vistaTecnico = new TecnicoVista();
                    if (tecnico == null) {

                        System.out.println("El tecnico NO se encuentra cargado en el sistema");
                        return;
                    }
                    gTecnico.eliminar(tecnico);
                    System.out.println("Se elimino el tecnico");
                } catch (Exception ex) {

                    ex.printStackTrace();
                }
                break;
            default:
                System.out.println("");
        }
    }

    public static void abmOperador() {
        System.out.println("\nSELECCIONE UNA OPCION");
        System.out.println("1 - Cargar Operador nuevo");
        System.out.println("2 - Modificar Operador");
        System.out.println("3 - Eliminar Operador");
        System.out.println("4 - Atras");
        int opcion = new Scanner(System.in).nextInt();
        switch (opcion) {
            case 1:
                try {

                    GestorOperador gOperador = new GestorOperador();
                    System.out.println("Ingrese el legajo del Operador");
                    Integer legajoOperador = new Scanner(System.in).nextInt();
                    OperadorMesaAyuda operador = gOperador.getOperadorXLegajo(legajoOperador);
                    OperadorVista vistaOperador = new OperadorVista();
                    if (operador != null) {

                        System.out.println("El operador " + operador.getNombreCompleto() + " ya se encuentra cargado en el sistema");
                        return;
                    }
                    operador = vistaOperador.cargarOperadorNuevo(legajoOperador);
                    gOperador.guardar(operador);
                    System.out.println("El operador " + operador.getNombreCompleto() + " se cargo exitosamente");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {

                    GestorOperador gOperador = new GestorOperador();
                    System.out.println("Ingrese el legajo del Operador");
                    Integer legajoOperador = new Scanner(System.in).nextInt();
                    OperadorMesaAyuda operador = gOperador.getOperadorXLegajo(legajoOperador);
                    OperadorVista vistaOperador = new OperadorVista();
                    if (operador == null) {

                        System.out.println("El operador NO se encuentra cargado en el sistema");
                        return;
                    }
                    operador = vistaOperador.modificarOperador(operador, legajoOperador);
                    gOperador.guardar(operador);
                    System.out.println("Se modifico el operador");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {

                    GestorOperador gOperador = new GestorOperador();
                    System.out.println("Ingrese el legajo del Operador");
                    Integer legajoOperador = new Scanner(System.in).nextInt();
                    OperadorMesaAyuda operador = gOperador.getOperadorXLegajo(legajoOperador);
                    OperadorVista vistaOperador = new OperadorVista();
                    if (operador == null) {

                        System.out.println("El operador NO se encuentra cargado en el sistema");
                        return;
                    }
                    gOperador.eliminar(operador);
                    System.out.println("Se elimino el operador");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("");
        }
    }

    public static void abmEspecialidad() {
        System.out.println("\nSELECCIONE UNA OPCION");
        System.out.println("1 - Cargar Especialidad nueva");
        System.out.println("2 - Modificar Especialidad");
        System.out.println("3 - Eliminar Especialidad");
        System.out.println("4 - Atras");
        int opcion = new Scanner(System.in).nextInt();
        switch (opcion) {
            case 1:
                try {

                    GestorEspecialidad gEspecialidad = new GestorEspecialidad();
                    System.out.println("Ingrese el nombre de la especialidad");
                    String nombreEspecialidad = new Scanner(System.in).nextLine();
                    Especialidad especialidad = gEspecialidad.getEspecialidadXNombre(nombreEspecialidad);
                    if (especialidad != null) {

                        System.out.println("La especialidad  " + especialidad.getDenominacion() + " ya existe.");
                        return;
                    }
                    especialidad = new Especialidad();
                    especialidad.setDenominacion(nombreEspecialidad);
                    gEspecialidad.guardar(especialidad);
                    System.out.println("Se agrego la especialidad");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    
                    GestorEspecialidad gEspecialidad = new GestorEspecialidad();
                    System.out.println("Seleccione una especialidad");
                    List<Especialidad> especialidades = gEspecialidad.listar(Especialidad.class);
                    for (Especialidad esp : especialidades) {

                        System.out.println(esp.getId() + " " + esp.getDenominacion());
                    }
                    long idEspecialidad = new Scanner(System.in).nextLong();
                    System.out.println("Ingrese la nueva denominacion");
                    String denominacion = new Scanner(System.in).nextLine();
                    Especialidad especialidad = gEspecialidad.getEspecialidadXNombre(denominacion);
                    if (especialidad != null) {
                        System.out.println("La especialidad  " + especialidad.getDenominacion() + " ya existe.");
                        return;
                    }
                    especialidad = gEspecialidad.getEspecialidadXId(idEspecialidad);
                    especialidad.setDenominacion(denominacion);
                    gEspecialidad.guardar(especialidad);
                    System.out.println("Se modifico la especialidad");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    GestorEspecialidad gEspecialidad = new GestorEspecialidad();
                    System.out.println("Seleccione una especialidad");
                    List<Especialidad> especialidades = gEspecialidad.listar(Especialidad.class);
                    for (Especialidad esp : especialidades) {

                        System.out.println(esp.getId() + " " + esp.getDenominacion());
                    }
                    long idEspecialidad = new Scanner(System.in).nextLong();
                    Especialidad especialidad = gEspecialidad.getEspecialidadXId(idEspecialidad);
                    gEspecialidad.eliminar(especialidad);
                    System.out.println("Se elimino la especialidad");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("");
        }
    }

    public static void abmServicio() {
        System.out.println("\nSELECCIONE UNA OPCION");
        System.out.println("1 - Cargar Servicio nuevo");
        System.out.println("2 - Modificar Servicio");
        System.out.println("3 - Eliminar Servicio");
        System.out.println("4 - Atras");
        int opcion = new Scanner(System.in).nextInt();
        switch (opcion) {
            case 1:
                try {

                    GestorServicio gServicio = new GestorServicio();
                    System.out.println("Ingrese el nombre del servicio");
                    String denominacion = new Scanner(System.in).nextLine();
                    Servicio servicio = gServicio.getServicioXNombre(denominacion);
                    if (servicio != null) {

                        System.out.println("El servicio " + denominacion + " ya existe.");
                        return;
                    }
                    servicio = new Servicio();
                    servicio.setDenominacion(denominacion);
                    gServicio.guardar(servicio);
                    System.out.println("Se agrego el servicio");
                } catch (Exception e) {

                    e.printStackTrace();
                }
                break;
            case 2:
                try {

                    GestorServicio gServicio = new GestorServicio();
                    System.out.println("Seleccione un servicio");
                    List<Servicio> servicios = gServicio.listar(Servicio.class);
                    for (Servicio servicio : servicios) {

                        System.out.println(servicio.getId() + " " + servicio.getDenominacion());
                    }
                    long idServicio = new Scanner(System.in).nextLong();
                    System.out.println("Ingrese la nueva denominacion");
                    String denominacion = new Scanner(System.in).nextLine();
                    Servicio servicio = gServicio.getServicioXNombre(denominacion);
                    if (servicio != null) {

                        System.out.println("El servicio " + denominacion + " ya existe.");
                        return;
                    }
                    servicio = gServicio.getServicioXId(idServicio);
                    servicio.setDenominacion(denominacion);
                    gServicio.guardar(servicio);
                    System.out.println("Se modifico el servicio");
                } catch (Exception e) {

                    e.printStackTrace();
                }
                break;
            case 3:
                try {

                    GestorServicio gServicio = new GestorServicio();
                    System.out.println("Seleccione un servicio");
                    List<Servicio> servicios = gServicio.listar(Servicio.class);
                    for (Servicio servicio : servicios) {

                        System.out.println(servicio.getId() + " " + servicio.getDenominacion());
                    }
                    long idServicio = new Scanner(System.in).nextLong();

                    Servicio servicio = gServicio.getServicioXId(idServicio);
                    gServicio.eliminar(servicio);
                    System.out.println("Se elimino el servicio");
                } catch (Exception e) {

                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("");
        }
    }

    public static void abmCliente() {
        System.out.println("\nSELECCIONE UNA OPCION");
        System.out.println("1 - Cargar Cliente nuevo");
        System.out.println("2 - Modificar Cliente");
        System.out.println("3 - Eliminar Cliente");
        System.out.println("4 - Atras");
        int opcion = new Scanner(System.in).nextInt();
        switch (opcion) {
            case 1:
                try {

                    GestorCliente gCliente = new GestorCliente();
                    System.out.println("Ingrese el cuit del cliente");
                    Long cuit = new Scanner(System.in).nextLong();
                    Cliente cliente = gCliente.getClienteXCUIT(cuit);
                    ClienteVista vistaCliente = new ClienteVista();
                    if (cliente != null) {
                        System.out.println("El cliente " + cliente.getRazonSocial() + "ya existe.");
                        return;
                    }
                    cliente = vistaCliente.cargarClienteNuevo(cuit);
                    gCliente.guardar(cliente);
                    System.out.println("El cliente " + cliente.getRazonSocial() + " se cargo exitosamente");
                } catch (Exception e) {

                    e.printStackTrace();
                }
                break;
            case 2:
                try {

                    GestorCliente gCliente = new GestorCliente();
                    System.out.println("Ingrese el cuit del cliente");
                    Long cuit = new Scanner(System.in).nextLong();
                    Cliente cliente = gCliente.getClienteXCUIT(cuit);
                    ClienteVista vistaCliente = new ClienteVista();
                    if (cliente == null) {
                        System.out.println("El cliente buscado NO existe.");
                        return;
                    }
                    cliente = vistaCliente.modificarCliente(cliente);
                    gCliente.guardar(cliente);
                    System.out.println("Se modifico el cliente");
                } catch (Exception e) {

                    e.printStackTrace();
                }
                break;
            case 3:
                try {

                    GestorCliente gCliente = new GestorCliente();
                    System.out.println("Ingrese el cuit del cliente");
                    Long cuit = new Scanner(System.in).nextLong();
                    Cliente cliente = gCliente.getClienteXCUIT(cuit);
                    if (cliente == null) {
                        System.out.println("El cliente buscado NO existe.");
                        return;
                    }
                    gCliente.eliminar(cliente);
                    System.out.println("Se elimino el cliente");
                } catch (Exception e) {

                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("");
        }
    }

    public static void abmReporteIncidencia(int legajo) {
        System.out.println("\nSELECCIONE UNA OPCION");
        System.out.println("1 - Cargar Reporte de Incidencia nuevo");
        System.out.println("2 - Modificar Reporte de Incidencia");
        System.out.println("3 - Eliminar Reporte de Incidencia");
        System.out.println("4 - Atras");
        int opcion = new Scanner(System.in).nextInt();
        switch (opcion) {
            case 1:
                try {

                    GestorCliente gCliente = new GestorCliente();

                    System.out.println("Ingrese el CUIT del cliente");
                    Long cuit = new Scanner(System.in).nextLong();
                    Cliente cliente = gCliente.getClienteXCUIT(cuit);

                    if (cliente == null) {

                        System.out.println("El cliente buscado no existe.");
                        return;
                    }
                    if (cliente.getServicios().isEmpty()) {

                        System.out.println("El cliente no tiene servicios asociados.");
                        return;
                    }

                    ReporteIncidenciaVista reporteIncidenciaVista = new ReporteIncidenciaVista();
                    ReporteIncidencia reporteIncidencia = reporteIncidenciaVista.cargarReporteIncidenciaNuevo(cliente, legajo);

                    GestorReporteIncidencia gReporteIncidencia = new GestorReporteIncidencia();
                    gReporteIncidencia.guardar(reporteIncidencia);
                    System.out.println("Se creo el reporte de incidencia con el codigo " + reporteIncidencia.getCodigoReporte());

                    Tecnico tecnico = reporteIncidencia.getTecnico();
                    tecnico.addReporteIncidencia(reporteIncidencia);
                    cliente.addReporteIncidencia(reporteIncidencia);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {

                    GestorReporteIncidencia gReporteIncidencia = new GestorReporteIncidencia();

                    System.out.println("Ingrese el codigo de reporte de incidencia");
                    String codigo = new Scanner(System.in).nextLine();
                    ReporteIncidencia reporteIncidencia = gReporteIncidencia.getReporteIncidenciaActivasXCodigo(codigo);

                    if (reporteIncidencia == null) {

                        System.out.println("El reporte buscado no existe, o ya fue solucionado/cancelado");
                        return;
                    }

                    ReporteIncidenciaVista reporteIncidenciaVista = new ReporteIncidenciaVista();
                    reporteIncidencia = reporteIncidenciaVista.modificarReporteIncidenciaNuevo(reporteIncidencia);
                    gReporteIncidencia.guardar(reporteIncidencia);
                    System.out.println("Se modifico el reporte de incidencia");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {

                    GestorReporteIncidencia gReporteIncidencia = new GestorReporteIncidencia();

                    System.out.println("Ingrese el codigo de reporte de incidencia");
                    String codigo = new Scanner(System.in).nextLine();
                    ReporteIncidencia reporteIncidencia = gReporteIncidencia.getReporteIncidenciaActivasXCodigo(codigo);

                    if (reporteIncidencia == null) {

                        System.out.println("El reporte buscado no existe, o ya fue solucionado/cancelado");
                        return;
                    }

                    gReporteIncidencia.eliminar(reporteIncidencia);
                    System.out.println("Se elimino el reporte de incidencia");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("");
        }
    }

    private static void menuConsultas() {

        ReporteIncidenciaVista reporteIncidenciaVista = new ReporteIncidenciaVista();
        System.out.println("\nSELECCIONE UNA OPCION");
        System.out.println(" 1 - Mostrar reporte de incidentes por tecnico entre fechas");
        System.out.println(" 2 - Mostrar reporte de incidentes resueltos por especialidad");
        System.out.println(" 3 - Mostrar Tecnico con mayor Incidentes resueltos");
        System.out.println(" 4 - Mostrar Tecnico con mayor Incidentes resueltos por fecha");
        System.out.println(" 5 - Mostrar Tecnico con mayor Incidentes resueltos por especialidad");
        System.out.println(" 6 - Mostrar Tecnico con mayor Incidentes resueltos por especialidad por fecha");
        System.out.println(" 7 - Mostrar Tecnico mas eficiente");
        System.out.println(" 8 - Mostrar Tecnico mas eficiente entre fechas");
        System.out.println(" 9 - Mostrar Tecnico menos eficiente");
        System.out.println("10 - Mostrar Tecnico menos eficiente entre fechas");
        System.out.println("11 - Atras");
        int opcion = new Scanner(System.in).nextInt();
        switch (opcion) {
            case 1:
                reporteIncidenciaVista.mostrarReportePorTecnicoEntreFechas();
                break;
            case 2:
                reporteIncidenciaVista.mostrarReportesResueltosPorEspecialidad();
                break;
            case 3:
                reporteIncidenciaVista.mostrarTecnicoConMasIncidenciasResueltas(false);
                break;
            case 4:
                reporteIncidenciaVista.mostrarTecnicoConMasIncidenciasResueltasXFecha(false);
                break;
            case 5:
                reporteIncidenciaVista.mostrarTecnicoConMasIncidenciasResueltas(true);
                break;
            case 6:
                reporteIncidenciaVista.mostrarTecnicoConMasIncidenciasResueltasXFecha(true);
                break;
            case 7:
                reporteIncidenciaVista.mostrarTecnicoEficiente(true);
                break;
            case 8:
                reporteIncidenciaVista.mostrarTecnicoEficienteXFechas(true);
                break;
            case 9:
                reporteIncidenciaVista.mostrarTecnicoEficiente(false);
                break;
            case 10:
                reporteIncidenciaVista.mostrarTecnicoEficienteXFechas(false);
                break;
            default:
                System.out.println("");
        }
    }
}
