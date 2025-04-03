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

    static Aparcamiento apar = new Aparcamiento();

    static String rutaArchivo = ".\\lista.txt";

    public static void main(String[] args) {
             LocalDateTime Now = LocalDateTime.now();
       // Automovil A1 = new Automovil("TodoTerreno", "AAA555", Now, true);
       //insertarVehiculo(A1);
        //Camion C1 = new Camion(3,"1234AAA",Now,false);
        //insertarVehiculo(C1);
       LLenarArray();
       //sacarVehiculo();
       //apar.buscarPorFecha();
       apar.verEstadisticas();
    }

    public static void insertarVehiculo(Vehiculo a) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo ,true))) {
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
                System.out.println(datos[3]);
                
                if (datos[3].equals("TodoTerreno")||datos[3].equals("Furgoneta")||datos[3].equals("Turismo")) {
                    System.out.println("Es un auto");
                    String tipo = datos[3];
                    V = new Automovil(tipo, matr, fecha, abono);
                } else {
                    System.out.println("Es un camion");
                    int nEjes = Integer.parseInt(datos[3]);
                    V = new Camion(nEjes, matr, fecha, abono);
                }
                // Crear objeto a partir de los datos leídos
                apar.getVehiculos().add(V);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Array LLenado: ");
        //System.out.println(apar.getVehiculos());
        for (Vehiculo e : apar.getVehiculos()) {
            System.out.println(e.getClass());
        }
    }

    public static void sacarVehiculo(){
        apar.sacar_vehiculo();
}
    /*
    public static void menu() {
        //clearConsole();
        int Action = 1;
        Scanner dato = new Scanner(System.in);
        while (Action != 0) {
            System.out.println("-//-/-/-/-/-//-/-/Bienvenido/-/-/-//-/-/-/-//-/-/");
            System.out.println("Quien Deseas Hacer?");
            System.out.println("(1) Añadir Auto");
            // System.out.println("(2)Eliminar Auto");
            System.out.println("(2) Ver Historial");
            System.out.println("(3) Cancelar Cita");
            System.out.println("(0) Regresar");
            Action = dato.nextInt();
            switch (Action) {
                case 1:
                    //insertarVehiculo();
                    break;
                case 2:
                    //History();
                    break;
                case 3:
                    //CancelarCita();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Selecciona un Numero valido");
                    ;
            }

        }

    }
     */
}
