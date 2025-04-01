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

    static String rutaArchivo = "lista.txt";

    public static void main(String[] args) {
        LocalDateTime Now = LocalDateTime.now();
        //Automovil A1 = new Automovil("TodoTerreno", "AAA555", Now, true);
        // insertarVehiculo(A1);

  
        
        mainMenu();
    }

    public static void insertarVehiculo(Automovil a) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            bw.write(a.String2()); // Convierte el objeto en texto
            bw.newLine(); // Nueva línea para cada objeto
            System.out.println("Archivo escrito correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void insertarVehiculo() {
            apar.introducir_vehiculo();
    }

    public static void sacarVehiculo() {
        apar.sacar_vehiculo();
    }
    
    
    
    public static void mainMenu() {
        //clearConsole();
        int Action = 1;
        Scanner dato = new Scanner(System.in);
        while (Action != 0) {
            limpiarConsola();
            System.out.println("-//-/-/-/-/-//-/-/Bienvenido Al Parking/-/-/-//-/-/-/-//-/-/");
            System.out.println("Que desea hacer?");
            System.out.println("(1) Menu Admin");
            System.out.println("(2) Menu Cliente");
            System.out.println("(0) Salir");
            System.out.print("SELECCIONE OPCION QUE DESEA:");
            Action = dato.nextInt();
            switch (Action) {
                case 1:
                    menuAdmin();
                    break;
                case 2:
                    menuCliente();
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
    
    public static void menuAdmin() {
        //clearConsole();
        int Action = 1;
        Scanner dato = new Scanner(System.in);
        while (Action != 0) {
            limpiarConsola();
            System.out.println("-//-/-/-/-/-//-/-/Bienvenido Al Menu Admin/-/-/-//-/-/-/-//-/-/");
            System.out.println("Que desea hacer?");
            System.out.println("(1) Ver cuantos coches hay");
            System.out.println("(2) Ver cuantas plazas quedan disponibles");
            System.out.println("(3) Editar vehiculo");     
            System.out.println("(0) Regresar");
             System.out.print("SELECCIONE OPCION QUE DESEA:");
            Action = dato.nextInt();
            switch (Action) {
                case 1:
                    //insertarVehiculo();
                    break;
                case 2:
                    //sacarVehiculo();
                    break;
                case 3:
                    //CancelarCita();
                    break;
                case 0:
                    limpiarConsola();
                    break;
                default:
                    System.out.println("Selecciona un Numero valido");
                    ;
            }

        }

    }
    
    

    public static void menuCliente() {
        //clearConsole();
        int Action = 1;
        Scanner dato = new Scanner(System.in);
        while (Action != 0) {
            limpiarConsola();
            System.out.println("-//-/-/-/-/-//-/-/Bienvenido Al Menu Cliente/-/-/-//-/-/-/-//-/-/");
            System.out.println("Que desea hacer?");
            System.out.println("(1) Entrar en el Parking");
            System.out.println("(2) Salir del Parking");
            System.out.println("(3) Cuantas horas llevo en el parking?");
            System.out.println("(0) Regresar");
            System.out.print("SELECCIONE OPCION QUE DESEA:");
            Action = dato.nextInt();
            switch (Action) {
                case 1:
                    insertarVehiculo();
                    break;
                case 2:
                    sacarVehiculo();
                    break;
                case 3:
                    //CancelarCita();
                    break;
                case 0:
                    limpiarConsola();
                    break;
                default:
                    System.out.println("Selecciona un Numero valido");
                    ;
            }

        }

    }
    
    public static void limpiarConsola(){
        for (int i = 0; i < 1; i++) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                    + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
    }

}
