package controlador;

import modelo.Especialidad;
import org.hibernate.Query;
import persistencia.ConfiguracionHibernate;

/**
 *
 * @author Juan Pablo Lopez
 */
public class GestorEspecialidad extends Gestor {

    public GestorEspecialidad() {
        if (sesion == null || !sesion.isOpen()) {
            sesion = ConfiguracionHibernate.openSession();
        }
    }

    /*
    public GestorEspecialidad(Session sesionParam) {
        sesion = sesionParam;
    }*/
    public Especialidad getEspecialidadXId(Long idEspecialidad) {
        try {

            Query consulta = sesion.createQuery("FROM Especialidad WHERE id = :idEspecialidad");
            consulta.setParameter("idEspecialidad", idEspecialidad);

            Especialidad especialidad = (Especialidad) consulta.uniqueResult();
            return especialidad;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Especialidad getEspecialidadXNombre(String nombreEspecialidad) {
        try {

            Query consulta = sesion.createQuery("FROM Especialidad WHERE denominacion = :nombreEspecialidad");
            consulta.setParameter("nombreEspecialidad", nombreEspecialidad);

            Especialidad especialidad = (Especialidad) consulta.uniqueResult();
            return especialidad;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
