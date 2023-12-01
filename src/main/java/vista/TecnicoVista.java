package vista;

import java.util.Scanner;
import modelo.DatosContacto;
import modelo.Tecnico;

/**
 *
 * @author Juan Pablo Lopez
 */
public class TecnicoVista {

    public Tecnico cargarTecnicoNuevo(int legajo) throws Exception {

        Tecnico tecnico = new Tecnico();

        ingresarDatosPersonales(tecnico);
        tecnico.setLegajo(legajo);

        DatosContacto datosContacto = new DatosContacto();

        modificarDatosContacto(datosContacto);

        tecnico.setDatosContacto(datosContacto);

        agregarEspecialidades(tecnico);

        System.out.println("Que metodo prefiere pare recibir sus notificaciones?");
        cambiarNotificaciones(tecnico);

        return tecnico;
    }

    public Tecnico modificarTecnico(Tecnico tecnico, int legajo) throws Exception {

        System.out.println("Desea modificar los datos personales? S/N");
        String respuesta = new Scanner(System.in).nextLine();
        if (respuesta.toUpperCase().equals("S")) {
            ingresarDatosPersonales(tecnico);
        }
        tecnico.setLegajo(legajo);

        System.out.println("Desea modificar los datos de contacto? S/N");
        respuesta = new Scanner(System.in).nextLine();
        if (respuesta.toUpperCase().equals("S")) {

            DatosContacto datosContacto = new DatosContacto();

            modificarDatosContacto(datosContacto);

            tecnico.setDatosContacto(datosContacto);
        }

        agregarEspecialidades(tecnico);

        System.out.println("Desea modificar el metodo de notificacion? S/N");
        respuesta = new Scanner(System.in).nextLine();
        if (respuesta.toUpperCase().equals("S")) {
            cambiarNotificaciones(tecnico);
        }
        return tecnico;
    }

    private void ingresarDatosPersonales(Tecnico tecnico) {

        System.out.println("Ingrese el apellido del Tecnico");
        tecnico.setApellido(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el nombre del tecnico");
        tecnico.setNombre(new Scanner(System.in).nextLine());

    }

    private void modificarDatosContacto(DatosContacto datosContacto) {

        System.out.println("Ingrese el celular del Tecnico");
        datosContacto.setCelular(new Scanner(System.in).nextLong());
        System.out.println("Ingrese el Email del Tecnico");
        datosContacto.setEmail(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el telefono del Tecnico");
        datosContacto.setTelefono(new Scanner(System.in).nextLong());
    }

    private void agregarEspecialidades(Tecnico tecnico) throws Exception {

        System.out.println("Desea gestionar las especialidades? S/N");
        String respuesta = new Scanner(System.in).nextLine();
        if (respuesta.toUpperCase().equals("S")) {

            EspecialidadVista vistaEspecialidad = new EspecialidadVista();
            vistaEspecialidad.cargarEspecialidadesTecnico(tecnico);
        }
    }

    private void cambiarNotificaciones(Tecnico tecnico) {

        System.out.println("1 - Email\n2 - Whatsapp");
        int opcion = new Scanner(System.in).nextInt();
        if (opcion == 1) {

            tecnico.setMetodoContacto("EMAIL");
        } else {

            tecnico.setMetodoContacto("WHATSAPP");
        }
        System.out.println("Ahora recibira sus notificaciones por " + tecnico.getMetodoContacto());
    }
}
