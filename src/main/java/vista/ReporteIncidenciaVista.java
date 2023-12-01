package vista;

import controlador.GestorEspecialidad;
import controlador.GestorOperador;
import controlador.GestorReporteIncidencia;
import controlador.GestorServicio;
import controlador.GestorTecnico;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import modelo.Cliente;
import modelo.ClienteServicio;
import modelo.Especialidad;
import modelo.OperadorMesaAyuda;
import modelo.ReporteIncidencia;
import modelo.Servicio;
import modelo.Tecnico;
import modelo.TecnicoEspecialidad;

/**
 *
 * @author Juan Pablo Lopez
 */
public class ReporteIncidenciaVista {

    public ReporteIncidencia cargarReporteIncidenciaNuevo(Cliente cliente, int legajo) throws Exception {

        GestorServicio gServicioRI = new GestorServicio();
        ReporteIncidencia reporteIncidencia = new ReporteIncidencia();
        System.out.println("Seleccione el servicio por el cual esta realizando el reclamo");
        for (ClienteServicio serv : cliente.getServicios()) {

            System.out.println(serv.getServicio().getId() + " - " + serv.getServicio().getDenominacion());
        }
        Long idServicio = new Scanner(System.in).nextLong();
        Servicio servicioSeleccionado = gServicioRI.getServicioXId(idServicio);
        System.out.println("Selecciono el servicio " + servicioSeleccionado.getDenominacion());

        System.out.println("Ingrese la descripcion del problema");
        String descripcion = new Scanner(System.in).nextLine();

        System.out.println("Ingrese el tipo de problema");
        String tipoProblema = obtenerTipoProblema();

        int tiempoEstimado = calcularTiempo(tipoProblema);
        if (tipoProblema.equals("COMPLEJO")) {
            System.out.println("El tipo de problema es complejo, desea agregar un colchon de horas? S/N");
            String respuesta = new Scanner(System.in).nextLine();
            if (respuesta.toUpperCase().equals("S")) {
                tiempoEstimado += 120;
            }
        }

        Tecnico tecnico = new Tecnico();
        GestorTecnico gTecnico = new GestorTecnico();

        List<Tecnico> tecnicos = gTecnico.listar(Tecnico.class);
        Map<Integer, Tecnico> tecnicosDisponibles = new HashMap<>();

        if (tecnicos.isEmpty()) {
            System.out.println("No hay tecnicos disponibles para asignar");
            return null;
        }

        System.out.println("Seleccione el tecnico que atendera el incidente");
        int i = 1;
        for (Tecnico tec : tecnicos) {

            List<TecnicoEspecialidad> especialidades = tec.getTecnicoEspecialidades();
            if (!especialidades.isEmpty()) {
                System.out.print(i + " - " + tec.getNombreCompleto() + " (");
                for (TecnicoEspecialidad especialidad : especialidades) {
                    System.out.print(" " + especialidad.getEspecialidad().getDenominacion());
                }
                System.out.println(" )");
            }
            tecnicosDisponibles.put(i, tec);
            i++;
        }
        int opcion = new Scanner(System.in).nextInt();
        tecnico = tecnicosDisponibles.get(opcion);

        System.out.println("Seleccione la especialidad el tecnico que atendera el incidente");
        i = 1;
        List<TecnicoEspecialidad> especialidades = tecnico.getTecnicoEspecialidades();
        Map<Integer, TecnicoEspecialidad> especialidadesDisponibles = new HashMap<>();
        if (!especialidades.isEmpty()) {

            for (TecnicoEspecialidad especialidad : especialidades) {
                System.out.println(i + " - " + especialidad.getEspecialidad().getDenominacion());
                especialidadesDisponibles.put(i, especialidad);
                i++;
            }
        }

        opcion = new Scanner(System.in).nextInt();
        TecnicoEspecialidad tecnicoEspecialidad = especialidadesDisponibles.get(opcion);

        System.out.println("Ingrese la fecha de posible resolucion (yyyy-MM-dd)");
        Date fecha = obtenerFecha();

        reporteIncidencia.setFechaAlta(new Date());
        reporteIncidencia.setCliente(cliente);
        reporteIncidencia.setServicio(servicioSeleccionado);
        reporteIncidencia.setOperador(obtenerOperador(legajo));
        reporteIncidencia.setDescripcionProblema(descripcion);
        reporteIncidencia.setTipoProblema(tipoProblema);
        reporteIncidencia.setTiempoEstimadoResolucion(tiempoEstimado);
        reporteIncidencia.setFechaPosibleResolucion(fecha);
        reporteIncidencia.setEstado("PENDIENTE");
        reporteIncidencia.setTecnico(tecnico);
        reporteIncidencia.setEspecialidad(tecnicoEspecialidad.getEspecialidad());
        reporteIncidencia.setCodigoReporte(reporteIncidencia.generarCodigo());

        return reporteIncidencia;
    }

    public ReporteIncidencia modificarReporteIncidenciaNuevo(ReporteIncidencia reporteIncidencia) throws Exception {

        System.out.println("Desea modificar la descripcion? (S/N)");
        String respuesta = new Scanner(System.in).nextLine();
        if (respuesta.equalsIgnoreCase("S")) {
            System.out.println("Ingrese la nueva descripcion");
            String descripcion = new Scanner(System.in).nextLine();
            reporteIncidencia.setDescripcionProblema(descripcion);
        }

        System.out.println("Desea modificar la fecha de posible resolucion? (S/N)");
        respuesta = new Scanner(System.in).nextLine();
        if (respuesta.equalsIgnoreCase("S")) {
            System.out.println("Ingrese la nueva fecha de resolucion (yyyy-MM-dd)");
            Date fecha = obtenerFecha();
            reporteIncidencia.setFechaPosibleResolucion(fecha);
        }

        System.out.println("Desea modificar el estado? (S/N)");
        respuesta = new Scanner(System.in).nextLine();
        if (respuesta.equalsIgnoreCase("S")) {
            System.out.println("Seleccione una opcion");
            System.out.println("1 - PENDIENTE");
            System.out.println("2 - EN PROCESO");
            System.out.println("3 - ANULADO");
            int opcion = new Scanner(System.in).nextInt();
            reporteIncidencia.setEstado(((opcion == 1) ? "PENDIENTE" : ((opcion == 2) ? "EN PROCESO" : "ANULADO")));
        }

        return reporteIncidencia;
    }

    private int calcularTiempo(String tipoProblema) {

        switch (tipoProblema) {
            case "BASICO":
                return 90;
            case "INTERMEDIO":
                return 120;
            default:
                return 150;
        }
    }

    private String obtenerTipoProblema() {

        System.out.println("1 - Basico\n2 - Intermedio\n3 - Complejo");
        int opcion = new Scanner(System.in).nextInt();
        switch (opcion) {
            case 1:
                return "BASICO";
            case 2:
                return "INTERMEDIO";
            default:
                return "COMPLEJO";
        }
    }

    private OperadorMesaAyuda obtenerOperador(int legajo) {

        GestorOperador gOperador = new GestorOperador();
        OperadorMesaAyuda operador = gOperador.getOperadorXLegajo(legajo);
        return operador;
    }

    private Date obtenerFecha() {
        Date fecha;
        try {
            String fechaString = new Scanner(System.in).nextLine();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaLocal = LocalDate.parse(fechaString, formatoFecha);
            fecha = Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } catch (Exception e) {
            System.out.println("Fecha incorrecta");
            return null;
        }
        return fecha;
    }

    private Especialidad obtenerEspecialidad() {

        try {
            System.out.println("Seleccione una Especialidad");
            GestorEspecialidad gEspecialidad = new GestorEspecialidad();
            List<Especialidad> especialidades;
            especialidades = gEspecialidad.listar(Especialidad.class);
            for (Especialidad esp : especialidades) {

                System.out.println(esp.getId() + " " + esp.getDenominacion());
            }
            long idEspecialidad = new Scanner(System.in).nextLong();
            Especialidad especialidad = gEspecialidad.getEspecialidadXId(idEspecialidad);
            return especialidad;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public void mostrarReportePorTecnicoEntreFechas() {

        System.out.println("Ingrese el legajo del tecnico");
        int legajo = new Scanner(System.in).nextInt();
        GestorTecnico gTecnico = new GestorTecnico();
        Tecnico tecnico = gTecnico.getTecnicoXLegajo(legajo);
        if (tecnico == null) {
            System.out.println("El tecnico no existe");
            return;
        }

        System.out.println("Ingrese la fecha de inicio");
        Date fechaInicio = obtenerFecha();

        System.out.println("Ingrese la fecha de fin");
        Date fechaFin = obtenerFecha();

        GestorReporteIncidencia gReporteIncidencia = new GestorReporteIncidencia();
        ArrayList<ReporteIncidencia> reportes = (ArrayList<ReporteIncidencia>) gReporteIncidencia.getReportesXTecnicoXFecha(fechaInicio, fechaFin, tecnico);
        System.out.println("\nReportes de Incidencia de " + tecnico.getNombreCompleto());
        for (ReporteIncidencia reporte : reportes) {
            System.out.println(reporte.getInformacionReporte());
        }
    }

    public void mostrarReportesResueltosPorEspecialidad() {

        Especialidad especialidad = obtenerEspecialidad();

        GestorReporteIncidencia gReporteIncidencia = new GestorReporteIncidencia();
        ArrayList<ReporteIncidencia> reportes = (ArrayList<ReporteIncidencia>) gReporteIncidencia.getReportesResueltosXEspecialidad(especialidad);
        System.out.println("\nReportes de Incidencia de " + especialidad.getDenominacion() + " RESUELTOS");
        for (ReporteIncidencia reporte : reportes) {
            System.out.println(reporte.getInformacionReporte() + " - Tecnico Asignado: " + reporte.getTecnico().getNombreCompleto());
        }
    }

    public void mostrarTecnicoConMasIncidenciasResueltas(boolean porEspecialidad) {

        GestorReporteIncidencia gReporteIncidencia = new GestorReporteIncidencia();
        Especialidad especialidad = new Especialidad();
        String titulo = "Tecnico/s con mas incidentes resueltos";
        if (porEspecialidad) {
            especialidad = obtenerEspecialidad();
            titulo = "Tecnico/s con mas incidentes de " + especialidad.getDenominacion() + " resueltos";
        }
        Map<Tecnico, Long> tecnicos = gReporteIncidencia.getTecnicoMayorCantidadIncidenciasResueltas(porEspecialidad, especialidad);

        if (tecnicos == null) {
            System.out.println("No hay tecnicos");
            return;
        }
        System.out.println(titulo);
        for (Map.Entry<Tecnico, Long> entry : tecnicos.entrySet()) {
            Tecnico key = entry.getKey();
            Long value = entry.getValue();
            System.out.println("Tecnico: " + key.getNombreCompleto() + " - Cantidad de incidentes resueltos: " + value);
        }
    }

    public void mostrarTecnicoConMasIncidenciasResueltasXFecha(boolean porEspecialidad) {

        System.out.println("Ingrese la fecha de inicio");
        Date fechaInicio = obtenerFecha();

        System.out.println("Ingrese la fecha de fin");
        Date fechaFin = obtenerFecha();

        GestorReporteIncidencia gReporteIncidencia = new GestorReporteIncidencia();
        Especialidad especialidad = new Especialidad();
        String titulo = "Tecnico/s con mas incidentes resueltos entre las fechas " + fechaInicio + " y " + fechaFin;
        if (porEspecialidad) {
            especialidad = obtenerEspecialidad();
            titulo = "Tecnico/s con mas incidentes de " + especialidad.getDenominacion() + " resueltos entre las fechas " + fechaInicio + " y " + fechaFin;
        }
        Map<Tecnico, Long> tecnicos = gReporteIncidencia.getTecnicoMayorCantidadIncidenciasResueltasXFecha(fechaInicio, fechaFin, porEspecialidad, especialidad);

        if (tecnicos == null) {
            System.out.println("No hay tecnicos");
            return;
        }
        System.out.println(titulo);
        for (Map.Entry<Tecnico, Long> entry : tecnicos.entrySet()) {
            Tecnico key = entry.getKey();
            Long value = entry.getValue();
            System.out.println("Tecnico: " + key.getNombreCompleto() + " - Cantidad de incidentes resueltos: " + value);
        }
    }

    public void mostrarTecnicoEficiente(boolean eficiente) {

        GestorReporteIncidencia gReporteIncidencia = new GestorReporteIncidencia();
        String palabra = ((eficiente) ? "mas" : "menos");
        Map<Tecnico, Double> tecnicos = gReporteIncidencia.getTecnicoEficiente(eficiente);

        if (tecnicos == null) {
            System.out.println("No hay tecnicos");
            return;
        }

        System.out.println("Tecnico/s " + palabra + " eficiente/s");
        for (Map.Entry<Tecnico, Double> entry : tecnicos.entrySet()) {
            Tecnico key = entry.getKey();
            Double value = entry.getValue();
            System.out.println("Tecnico: " + key.getNombreCompleto() + " - Promedio de minutos: " + value);
        }
    }

    public void mostrarTecnicoEficienteXFechas(boolean eficiente) {

        System.out.println("Ingrese la fecha de inicio");
        Date fechaInicio = obtenerFecha();

        System.out.println("Ingrese la fecha de fin");
        Date fechaFin = obtenerFecha();

        GestorReporteIncidencia gReporteIncidencia = new GestorReporteIncidencia();
        Map<Tecnico, Double> tecnicos = gReporteIncidencia.getTecnicoEficienteXFechas(fechaInicio, fechaFin, eficiente);

        if (tecnicos == null) {
            System.out.println("No hay tecnicos");
            return;
        }

        String palabra = ((eficiente) ? "mas" : "menos");
        System.out.println("Tecnico/s " + palabra + " eficiente/s entre las fechas " + fechaInicio + " y " + fechaFin);
        for (Map.Entry<Tecnico, Double> entry : tecnicos.entrySet()) {
            Tecnico key = entry.getKey();
            Double value = entry.getValue();
            System.out.println("Tecnico: " + key.getNombreCompleto() + " - Promedio de minutos: " + value);
        }
    }
}
