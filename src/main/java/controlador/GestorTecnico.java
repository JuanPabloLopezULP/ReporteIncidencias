package controlador;

import modelo.Tecnico;
import org.hibernate.Query;
import persistencia.ConfiguracionHibernate;

/**
 *
 * @author Juan Pablo Lopez
 */
public class GestorTecnico extends Gestor {

    public GestorTecnico() {
        if (sesion == null || !sesion.isOpen()) {
            sesion = ConfiguracionHibernate.openSession();
        }
    }

    public Tecnico getTecnicoXLegajo(int legajo) {
        try {

            Query consulta = sesion.createQuery("SELECT tecnico FROM Tecnico tecnico WHERE tecnico.legajo = :legajo");
            consulta.setParameter("legajo", legajo);

            Tecnico tecnico = (Tecnico) consulta.uniqueResult();
            return tecnico;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
