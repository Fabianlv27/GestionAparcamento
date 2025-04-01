package gestionaparcamiento;

import static gestionaparcamiento.GestionAparcamiento.apar;
import static gestionaparcamiento.GestionAparcamiento.rutaArchivo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author CEEP
 */
public class Aparcamiento {
    //todo pasar escaner como parámetro

    static Scanner dato = new Scanner(System.in);

    public ArrayList<Vehiculo> vehiculos = new ArrayList<>();

    private int capacidad = 10;

    //Getters y setters
    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    //Constructor
    public Aparcamiento() {
    }

    public Aparcamiento(int capacidad) {
        this.capacidad = capacidad;
    }

    //toString
    @Override
    public String toString() {
        return "Aparcamiento{" + "\nvehiculos:" + vehiculos + ",\n capacidad:" + capacidad + '}';
    }

    //metodos de la clase
    public void introducir_vehiculo() {
        boolean encontrado = false;

        System.out.print("Introduzca la matricula del vehiculo");
        String matr = dato.next();
        for (Vehiculo e : vehiculos) {
            if (e.getMatricula().equalsIgnoreCase(matr)) {
                System.out.println("El vehiculo ya existe en el aparcamiento.");
                System.out.print("Quieres salir del parking?" + "S/N");
                String respuesta = dato.next();
                if (respuesta.equalsIgnoreCase("S")) {
                    sacar_vehiculo();
                }
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            boolean abono = false;
            System.out.println("Que tipo de vehiculo quiere introducir");
            System.out.println("Pulse 0 para Automovil o 1 para Camion");
            int respuesta = dato.nextInt();
            System.out.println("Tiene abono? S/N");
            String respuesta2 = dato.next();
            if (!respuesta2.equalsIgnoreCase("S")) {
                abono = true;
            }
            if (respuesta == 0) {
                System.out.println("Tipo:");
                String tipo = dato.next();
                Automovil NewAuto = new Automovil(tipo, matr, LocalDateTime.now(), abono);
            }
        }

    }

    public void sacar_vehiculo() {
    //toDoLogic
    boolean encontrado = false;
    try {
        System.out.print("Introduzca la matricula del vehiculo: ");
        String matr = dato.next();
        for (Vehiculo e : vehiculos) {
            if (e.getMatricula().equalsIgnoreCase(matr)) {
                System.out.println("El vehiculo ha sido encontrado con exito.");
                System.out.print("Quieres salir del parking? (S/N): ");
                String respuesta = dato.next();
                if (respuesta.equalsIgnoreCase("S")) {
                    // Eliminar el vehículo de la lista
                    vehiculos.remove(e);

                    // Ahora reescribimos el archivo con la lista actualizada
                    actualizarArchivo();

                    System.out.println("Vehículo eliminado correctamente.");
                }
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("El vehículo con esa matrícula no fue encontrado.");
        }
    } catch (Exception e) {
        System.out.println("Hubo un error al intentar eliminar el vehículo.");
        e.printStackTrace();
    }
}
    
    public void actualizarArchivo() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
        for (Vehiculo v : apar.getVehiculos()) {
            bw.write(v.String2());  // Convierte el objeto en texto
            bw.newLine();  // Nueva línea para cada vehículo
        }
        System.out.println("Archivo actualizado correctamente.");
    } catch (IOException e) {
        System.out.println("Error al actualizar el archivo.");
        e.printStackTrace();
    }


}
}
