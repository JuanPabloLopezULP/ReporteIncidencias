package controlador;

import modelo.Servicio;
import org.hibernate.Query;
import persistencia.ConfiguracionHibernate;

/**
 *
 * @author Juan Pablo Lopez
 */
public class GestorServicio extends Gestor {

    public GestorServicio() {
        if (sesion == null || !sesion.isOpen()) {
            sesion = ConfiguracionHibernate.openSession();
        }
    }

    public Servicio getServicioXId(Long idServicio) {
        try {

            Query consulta = sesion.createQuery("FROM Servicio WHERE id = :idServicio");
            consulta.setParameter("idServicio", idServicio);

            Servicio servicio = (Servicio) consulta.uniqueResult();
            return servicio;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Servicio getServicioXNombre(String nombreServicio) {
        try {

            Query consulta = sesion.createQuery("FROM Servicio WHERE denominacion = :nombreServicio");
            consulta.setParameter("nombreServicio", nombreServicio);

            Servicio servicio = (Servicio) consulta.uniqueResult();
            return servicio;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
