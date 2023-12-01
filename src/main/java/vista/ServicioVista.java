package vista;

import controlador.GestorServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.Cliente;
import modelo.ClienteServicio;
import modelo.Servicio;

/**
 *
 * @author Juan Pablo Lopez
 */
public class ServicioVista {

    public void cargarServiciosCliente(Cliente cliente) throws Exception {

        GestorServicio gServicio = new GestorServicio();
        System.out.println("Lista de Servicios");
        List<Servicio> servicios = gServicio.listar(Servicio.class);
        for (Servicio serv : servicios) {

            System.out.println(serv.getId() + " " + serv.getDenominacion());
        }
        List<Long> idServicios = new ArrayList<Long>();
        while (true) {

            System.out.println("Seleccione el servicio a asignar al cliente");
            long idServicio = new Scanner(System.in).nextLong();
            if (idServicios.contains(idServicio)) {

                System.out.println("El servicio seleccionado ya fue asignado");
            } else {

                idServicios.add(idServicio);
                Servicio servicio = (Servicio) gServicio.buscarPorId(Servicio.class, idServicio);
                if (servicio != null) {

                    ClienteServicio clienteServicio = new ClienteServicio();
                    clienteServicio.setServicio(servicio);
                    clienteServicio.setCliente(cliente);
                    cliente.addServicio(clienteServicio);
                    System.out.println("El servicio " + servicio.getDenominacion() + " fue agregado exitosamente");
                } else {

                    System.out.println("El id del servicio ingresado no existe");
                }
            }
            System.out.println("Desea agregar otro servicio?? S/N");
            String continuar = new Scanner(System.in).nextLine();
            if (!continuar.toUpperCase().equals("S")) {

                break;
            }
        }
    }
}
