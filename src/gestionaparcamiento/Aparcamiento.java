package gestionaparcamiento;

import static gestionaparcamiento.GestionAparcamiento.apar;
import static gestionaparcamiento.GestionAparcamiento.rutaArchivo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
    public static void LLenarArray() {
        apar.getVehiculos().clear(); // 🔹 Limpiar la lista antes de llenarla nuevamente evitando duplicados

        // 🔹 Verificar si el archivo existe antes de intentar leerlo
        File file = new File(rutaArchivo);
        if (!file.exists()) {
            System.out.println("⚠️ Archivo no encontrado. Creando un archivo vacio...");
            try {
                file.createNewFile(); // 🔹 Crea el archivo vacío si no existe
            } catch (IOException e) {
                System.out.println("❌ Error al crear el archivo: " + e.getMessage());
                return; // 🔹 Salimos para evitar leer un archivo inexistente
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            Vehiculo V = null;
            while ((linea = br.readLine()) != null) {
                // Suponiendo que los atributos están separados por coma ","
                String[] datos = linea.split(",");
                String matr = datos[0];
                LocalDateTime fecha = LocalDateTime.parse(datos[1]);
                boolean abono = Boolean.parseBoolean(datos[2]);

                // Vemos si es Automóvil o Camión
                System.out.println(datos[3]);

                if (datos[3].equals("TodoTerreno") || datos[3].equals("Furgoneta") || datos[3].equals("Turismo")) {
                    //System.out.println("Es un auto"); //Lo hace correctamente
                    String tipo = datos[3];
                    V = new Automovil(tipo, matr, fecha, abono);
                } else {
                    //System.out.println("Es un camión"); //Lo hace correctamente
                    int nEjes = Integer.parseInt(datos[3]);
                    V = new Camion(nEjes, matr, fecha, abono);
                }

                // Agregar el vehículo a la lista
                apar.getVehiculos().add(V);
            }
        } catch (IOException e) {
            System.out.println("❌ Error al leer el archivo: " + e.getMessage());
        }

        // 🔹 Verificar si los datos fueron leídos correctamente
        System.out.println("Array Llenado:");
        for (Vehiculo e : apar.getVehiculos()) {
            System.out.println(e); // Muestra cada vehículo en consola
        }
    }

    public void introducir_vehiculo() {
        boolean encontrado = false;

        LLenarArray(); // 🔹 Asegurarse de que la lista está actualizada antes de comparar

        System.out.print("Introduzca la matricula del vehiculo: ");
        String matr = dato.next();

        for (Vehiculo e : vehiculos) {
            if (e.getMatricula().equalsIgnoreCase(matr)) {
                System.out.println("El vehiculo ya existe en el aparcamiento.");
                System.out.print("Quieres salir del parking? (S/N): ");
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

            System.out.println("¿Que tipo de vehiculo quiere introducir?");
            System.out.println("(0) Automóvil");
            System.out.println("(1) Camión");

            int respuesta;
            while (true) {
                try {
                    respuesta = dato.nextInt();
                    if (respuesta == 0 || respuesta == 1) {
                        break;
                    }
                    System.out.println("Opcion no valida. Intente nuevamente.");
                } catch (Exception e) {
                    System.out.println("Error: Debe ingresar un número valido.");
                    dato.next(); // Limpiar buffer
                }
            }

            System.out.print("Tiene abono? (S/N): ");
            String respuesta2 = dato.next();
            if (respuesta2.equalsIgnoreCase("S")) {
                abono = true;
            }

            Vehiculo nuevoVehiculo = null;

            if (respuesta == 0) {
                System.out.println("Tipos de Automovil:");
                System.out.println("1. Turismo");
                System.out.println("2. Todo Terreno");
                System.out.println("3. Furgoneta");
                System.out.print("Seleccione el tipo de automovil (1-3): ");

                int tipoSeleccionado = dato.nextInt();
                String tipo = "";

                switch (tipoSeleccionado) {
                    case 1:
                        tipo = "Turismo";
                        break;
                    case 2:
                        tipo = "TodoTerreno";
                        break;
                    case 3:
                        tipo = "Furgoneta";
                        break;
                    default:
                        System.out.println("Opcion no válida. Se guardara como 'Desconocido'.");
                        tipo = "Desconocido";
                }

                nuevoVehiculo = new Automovil(tipo, matr, LocalDateTime.now(), abono);
            } else {
                System.out.print("Ingrese el numero de ejes del camion: ");
                int ejes = dato.nextInt();
                nuevoVehiculo = new Camion(ejes, matr, LocalDateTime.now(), abono);
            }

            vehiculos.add(nuevoVehiculo);
            System.out.println("Vehiculo agregado con exito.");

            // 🔹 Ahora actualizamos el archivo con el nuevo vehículo
            actualizarArchivo();
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            for (Vehiculo v : vehiculos) {
                if (v instanceof Automovil) {
                    bw.write(v.String2()); // Marcamos que es un Automóvil
                } else if (v instanceof Camion) {
                    bw.write(v.String2()); // Marcamos que es un Camión
                }
                bw.newLine();
            }
            System.out.println("Archivo actualizado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo.");
            e.printStackTrace();
        }
    }

}
