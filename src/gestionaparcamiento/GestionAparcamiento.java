/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestionaparcamiento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class GestionAparcamiento {

    static Scanner dato = new Scanner(System.in);

    static Aparcamiento apar = new Aparcamiento();

    static String rutaArchivo = ".\\lista.txt";

    public static void main(String[] args) {
        LLenarArray();
        mainMenu();
    }

    public static void insertarVehiculo(Vehiculo a) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {

            bw.write(a.String2()); // Convierte el objeto en texto
            bw.newLine(); // Nueva línea para cada objeto
            System.out.println("Archivo escrito correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void LLenarArray() {

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            Vehiculo V = null;
            while ((linea = br.readLine()) != null) {
                // Suponiendo que los atributos están separados por coma ","
                String[] datos = linea.split(","); // Dividimos en 2 partes (nombre y edad, por ejemplo)
                String matr = datos[0];
                LocalDateTime fecha = LocalDateTime.parse(datos[1]);
                boolean abono = Boolean.parseBoolean(datos[2]);
                //Vemos si es Automovil o Camion

                if (datos[3].equals("TodoTerreno") || datos[3].equals("Furgoneta") || datos[3].equals("Turismo")) {
                    //  System.out.println("Es un auto");
                    String tipo = datos[3];
                    V = new Automovil(tipo, matr, fecha, abono);
                } else {
                    //  System.out.println("Es un camion");
                    int nEjes = Integer.parseInt(datos[3]);
                    V = new Camion(nEjes, matr, fecha, abono);
                }
                // Crear objeto a partir de los datos leídos
                apar.getVehiculos().add(V);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void mainMenu() {
        int Action = 1;
        Scanner dato = new Scanner(System.in);
        while (Action != 0) {
            System.out.println(Colores.BLANCO + "-//-/-/-/-/-//-/-/Bienvenido Al Parking/-/-/-//-/-/-/-//-/-/" + Colores.RESET);
            System.out.println(Colores.VERDE + "Que desea hacer?" + Colores.RESET);
            System.out.println(Colores.BLANCO + "(1) Menu Admin" + Colores.RESET);
            System.out.println(Colores.BLANCO + "(2) Menu Cliente" + Colores.RESET);
            System.out.println(Colores.ROJO + "(0) Salir" + Colores.RESET);
            System.out.print(Colores.CYAN + "SELECCIONE OPCION QUE DESEA:" + Colores.RESET);
            Action = dato.nextInt();
            switch (Action) {
                case 1:
                    menuAdmin();
                    break;
                case 2:
                    menuCliente();
                    break;
                case 0:
                    break;
                default:
                    System.out.println(Colores.ROJO + "Selecciona un Numero valido" + Colores.RESET);
            }
        }
    }

    public static void preguntarSalir() {
        System.out.println("Desea regresar? (1)SI ");
        int resp = dato.nextInt();
        if (resp == 1) {
            return;
        }
    }

    public static void menuAdmin() {
        int Action = 1;
        Scanner dato = new Scanner(System.in);
        while (Action != 0) {
            limpiarConsola();
            System.out.println(Colores.BLANCO + "-//-/-/-/-/-//-/-/Bienvenido Al Menu Admin/-/-/-//-/-/-/-//-/-/" + Colores.RESET);
            System.out.println(Colores.VERDE + "¿Qué desea hacer?" + Colores.RESET);
            System.out.println(Colores.BLANCO + "(1) Ver cuántos coches hay" + Colores.RESET);
            System.out.println(Colores.BLANCO + "(2) Ver cuántas plazas quedan disponibles" + Colores.RESET);
            System.out.println(Colores.BLANCO + "(3) Buscar por fecha" + Colores.RESET);
            System.out.println(Colores.BLANCO + "(4) Ver todos " + Colores.RESET);
            System.out.println(Colores.ROJO + "(0) Regresar" + Colores.RESET);
            System.out.print(Colores.CYAN + "SELECCIONE OPCIÓN QUE DESEA: " + Colores.RESET);
            Action = dato.nextInt();
            switch (Action) {
                case 1:
                    apar.verEstadisticas();
                    preguntarSalir();
                    break;
                case 2:
                    apar.verDisponibles();
                    preguntarSalir();
                    break;
                case 3:
                    apar.buscarPorFecha();
                    preguntarSalir();
                    break;
                case 4:
                    System.out.println(apar.getVehiculos());
                    preguntarSalir();
                    break;
                case 0:
                    limpiarConsola();
                    break;
                default:
                    System.out.println(Colores.ROJO + "Seleccione un número válido" + Colores.RESET);
            }
        }
    }

    public static void menuCliente() {
        int Action = 1;
        Scanner dato = new Scanner(System.in);
        while (Action != 0) {
            limpiarConsola();
            System.out.println(Colores.BLANCO + "-//-/-/-/-/-//-/-/Bienvenido Al Menu Cliente/-/-/-//-/-/-/-//-/-/" + Colores.RESET);
            System.out.println(Colores.VERDE + "¿Qué desea hacer?" + Colores.RESET);
            System.out.println(Colores.BLANCO + "(1) Entrar en el Parking" + Colores.RESET);
            System.out.println(Colores.BLANCO + "(2) Salir del Parking" + Colores.RESET);
            System.out.println(Colores.BLANCO + "(3) Consultar tiempo en el parking" + Colores.RESET);
            System.out.println(Colores.ROJO + "(0) Regresar" + Colores.RESET);
            System.out.print(Colores.CYAN + "SELECCIONE OPCIÓN QUE DESEA: " + Colores.RESET);
            Action = dato.nextInt();
            switch (Action) {
                case 1:
                    apar.introducir_vehiculo();
                    preguntarSalir();
                    break;
                case 2:
                    apar.sacar_vehiculo();
                    preguntarSalir();
                    break;
                case 3:
                    apar.CalcularTiempo();
                    preguntarSalir();
                    break;
                case 0:
                    limpiarConsola();
                    break;
                default:
                    System.out.println(Colores.ROJO + "Seleccione un número válido" + Colores.RESET);
            }
        }
    }

    public static void limpiarConsola() {
        for (int i = 0; i < 1; i++) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
    }

}
