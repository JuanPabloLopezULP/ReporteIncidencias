package controlador;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.Especialidad;
import modelo.ReporteIncidencia;
import modelo.Tecnico;
import org.hibernate.Query;
import persistencia.ConfiguracionHibernate;

/**
 *
 * @author Juan Pablo Lopez
 */
public class GestorReporteIncidencia extends Gestor {

    public GestorReporteIncidencia() {

        if (sesion == null || !sesion.isOpen()) {

            sesion = ConfiguracionHibernate.openSession();
        }
    }

    public ReporteIncidencia getReporteIncidenciaXCodigo(String codigoReporte) {

        try {

            Query consulta = sesion.createQuery("SELECT reporte FROM ReporteIncidenia reporte "
                    + "WHERE reporte.codigoReporte = :codigoReporte");
            consulta.setParameter("codigoReporte", codigoReporte);

            ReporteIncidencia reporteIncidencia = (ReporteIncidencia) consulta.uniqueResult();
            return reporteIncidencia;
        } catch (RuntimeException e) {

            e.printStackTrace();
            return null;
        }
    }

    public ReporteIncidencia getReporteIncidenciaActivasXCodigo(String codigoReporte) {

        try {

            Query consulta = sesion.createQuery("SELECT reporte FROM ReporteIncidenia reporte "
                    + "WHERE reporte.codigoReporte = :codigoReporte AND (reporte.estado = PENDIENTE OR reporte.estado = EN PROCESO)");
            consulta.setParameter("codigoReporte", codigoReporte);

            ReporteIncidencia reporteIncidencia = (ReporteIncidencia) consulta.uniqueResult();
            return reporteIncidencia;
        } catch (RuntimeException e) {

            e.printStackTrace();
            return null;
        }
    }

    public List<ReporteIncidencia> getReportesXTecnicoXFecha(Date fechaDesde, Date fechaHasta, Tecnico tecnico) {

        try {

            Query consulta = sesion.createQuery("SELECT reporte FROM ReporteIncidencia reporte "
                    + "WHERE reporte.tecnico.id = :idTec AND (reporte.fechaAlta BETWEEN :fechaAlta AND :fechaBaja)");
            consulta.setParameter("idTec", tecnico.getId());
            consulta.setParameter("fechaAlta", fechaDesde);
            consulta.setParameter("fechaBaja", fechaHasta);
            List<ReporteIncidencia> listaReportes = consulta.list();
            return listaReportes;
        } catch (RuntimeException e) {

            e.printStackTrace();
            return null;
        }
    }

    public List<ReporteIncidencia> getReportesResueltosXEspecialidad(Especialidad especialidad) {

        try {

            Query consulta = sesion.createQuery("SELECT reporte FROM ReporteIncidencia reporte "
                    + "WHERE reporte.especialidad.id = :idEsp AND reporte.estado = :estado");
            consulta.setParameter("idEsp", especialidad.getId());
            consulta.setParameter("estado", "RESUELTO");

            List<ReporteIncidencia> listaReportes = consulta.list();
            return listaReportes;
        } catch (RuntimeException e) {

            e.printStackTrace();
            return null;
        }
    }

    public Map<Tecnico, Long> getTecnicoMayorCantidadIncidenciasResueltas(boolean porEspecialidad, Especialidad especialidad) {

        try {

            String hql = "SELECT reporte.tecnico , COUNT(*) AS cantidad FROM ReporteIncidencia reporte "
                    + "WHERE reporte.estado = :estado ";
            hql += ((porEspecialidad) ? "AND reporte.especialidad.id = :idEsp" : "");
            hql += " GROUP BY reporte.tecnico ORDER BY cantidad DESC";
            Query consulta = sesion.createQuery(hql);
            consulta.setParameter("estado", "RESUELTO");
            if (porEspecialidad) {
                consulta.setParameter("idEsp", especialidad.getId());
            }

            List<Object[]> resultados = (List<Object[]>) consulta.list();

            if (resultados.isEmpty()) {
                System.out.println("Lista Vacia");
                return null;
            }

            Map<Tecnico, Long> tecnicoCantidad = new HashMap();
            llenarMapaLong(tecnicoCantidad, resultados);
            return tecnicoCantidad;
        } catch (RuntimeException e) {

            e.printStackTrace();
            return null;
        }
    }

    public Map<Tecnico, Long> getTecnicoMayorCantidadIncidenciasResueltasXFecha(Date fechaDesde, Date fechaHasta, boolean porEspecialidad, Especialidad especialidad) {

        try {
            String hql = "SELECT reporte.tecnico , COUNT(*) AS cantidad FROM ReporteIncidencia reporte "
                    + "WHERE reporte.estado = :estado ";
            hql += ((porEspecialidad) ? "AND reporte.especialidad.id = :idEsp" : "");
            hql += " AND (reporte.fechaAlta BETWEEN :fechaAlta AND :fechaBaja) GROUP BY reporte.tecnico ORDER BY cantidad DESC";
            Query consulta = sesion.createQuery(hql);
            consulta.setParameter("estado", "RESUELTO");
            consulta.setParameter("fechaAlta", fechaDesde);
            consulta.setParameter("fechaBaja", fechaHasta);
            if (porEspecialidad) {
                consulta.setParameter("idEsp", especialidad.getId());
            }

            List<Object[]> resultados = (List<Object[]>) consulta.list();

            if (resultados.isEmpty()) {
                System.out.println("Lista Vacia");
                return null;
            }

            Map<Tecnico, Long> tecnicoCantidad = new HashMap();
            llenarMapaLong(tecnicoCantidad, resultados);

            return tecnicoCantidad;
        } catch (RuntimeException e) {

            e.printStackTrace();
            return null;
        }
    }

    public Map<Tecnico, Double> getTecnicoEficiente(boolean ascendente) {

        try {

            String hql = "SELECT reporte.tecnico , AVG(reporte.tiempoEstimadoResolucion) AS promedio FROM ReporteIncidencia reporte "
                    + "WHERE reporte.estado = :estado GROUP BY reporte.tecnico ORDER BY promedio ";
            hql += ((ascendente) ? "ASC" : "DESC");
            Query consulta = sesion.createQuery(hql);
            consulta.setParameter("estado", "RESUELTO");

            List<Object[]> resultados = (List<Object[]>) consulta.list();

            if (resultados.isEmpty()) {
                System.out.println("Lista Vacia");
                return null;
            }

            Map<Tecnico, Double> tecnicoCantidad = new HashMap();
            llenarMapaDouble(tecnicoCantidad, resultados);

            return tecnicoCantidad;
        } catch (RuntimeException e) {

            e.printStackTrace();
            return null;
        }
    }

    public Map<Tecnico, Double> getTecnicoEficienteXFechas(Date fechaDesde, Date fechaHasta, boolean ascendente) {

        try {

            String hql = "SELECT reporte.tecnico , AVG(reporte.tiempoEstimadoResolucion) AS promedio FROM ReporteIncidencia reporte "
                    + "WHERE reporte.estado = :estado AND (reporte.fechaAlta BETWEEN :fechaAlta AND :fechaBaja) GROUP BY reporte.tecnico ORDER BY promedio ";
            hql += ((ascendente) ? "ASC" : "DESC");

            Query consulta = sesion.createQuery(hql);

            consulta.setParameter("estado", "RESUELTO");
            consulta.setParameter("fechaAlta", fechaDesde);
            consulta.setParameter("fechaBaja", fechaHasta);

            List<Object[]> resultados = (List<Object[]>) consulta.list();

            if (resultados.isEmpty()) {
                System.out.println("Lista Vacia");
                return null;
            }

            Map<Tecnico, Double> tecnicoCantidad = new HashMap();
            llenarMapaDouble(tecnicoCantidad, resultados);

            return tecnicoCantidad;
        } catch (RuntimeException e) {

            e.printStackTrace();
            return null;
        }
    }

    private void llenarMapaLong(Map<Tecnico, Long> mapa, List<Object[]> resultados) {

        long anterior = 0;
        Long cantidad;
        Tecnico tecnico;

        for (Object[] resultado : resultados) {

            tecnico = (Tecnico) resultado[0];
            cantidad = (Long) resultado[1];
            if (anterior == 0) {

                anterior = cantidad;
            }
            if (anterior == cantidad) {

                mapa.put(tecnico, cantidad);
            }
        }
    }

    private void llenarMapaDouble(Map<Tecnico, Double> mapa, List<Object[]> resultados) {

        Double anterior = 0.0;
        Double cantidad;
        Tecnico tecnico;

        for (Object[] resultado : resultados) {

            tecnico = (Tecnico) resultado[0];
            cantidad = (Double) resultado[1];
            if (anterior == 0.0) {

                anterior = cantidad;
            }
            if (anterior == cantidad) {

                mapa.put(tecnico, cantidad);
            }
        }
    }
}
