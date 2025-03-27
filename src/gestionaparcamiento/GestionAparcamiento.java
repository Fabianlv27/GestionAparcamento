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
import java.time.LocalDateTime;
import java.util.Scanner;

public class GestionAparcamiento {

    public static void main(String[] args) {
        
        Aparcamiento apar = new Aparcamiento();
        
        LocalDateTime Now = LocalDateTime.now();

        //System.out.println(apar);
        
        // Especifica la ruta del archivo
        String rutaArchivo = "C:\\Users\\CEEP\\Desktop\\lista.txt";
        
        
        // Leer el archivo y procesar cada línea
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            // Leer línea por línea del archivo
            while ((linea = br.readLine()) != null) {
                // Procesar la línea para extraer el nombre y la edad
                String[] partes = linea.split(", "); // Separar por la coma y espacio

                // Extraer el nombre y la edad
                String matricula = partes[0].split(": ")[1]; // Parte después de "Matricula: "
                //LocalDateTime  fecha = LocalDateTime.parse(partes[1].split(": ")[1].split(":")[2].split(":")[3]); // Parte después de "Edad: "
                boolean  abono = Boolean.parseBoolean(partes[2].split(": ")[1]); // Parte después de "Edad: "

                // Crear un objeto Persona y añadirlo al ArrayList
              //  apar.getVehiculos().add(new Automovil("auto", "123AAA", Now, true));
            }

            // Mostrar los objetos leídos
            for (Vehiculo e : apar.getVehiculos()) {
                System.out.println(e); // Utiliza el método toString()
            }

        } catch (IOException e) {
            System.out.println("error tus muertos");
            e.printStackTrace();
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        /*
        // Escribir el ArrayList en el archivo de texto
        try {
            // Crear un objeto File para representar el archivo
            File archivo = new File(rutaArchivo);
            
            // Si el archivo no existe, se crea
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            
            // Crear un FileWriter y un BufferedWriter
            FileWriter fileWriter = new FileWriter(archivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            // Recorrer el ArrayList y escribir cada elemento en el archivo
            for (Vehiculo item : apar.getVehiculos()) {
                bufferedWriter.write(item.toString());
                bufferedWriter.newLine(); // Añadir una nueva línea después de cada elemento
            }
            
            // Cerrar el BufferedWriter
            bufferedWriter.close();
            
            System.out.println("El ArrayList se ha escrito en el archivo con éxito.");
        } catch (IOException e) {
            // Manejo de excepciones en caso de error al trabajar con archivos
            e.printStackTrace();
            System.out.println("error :(");
        }
        */
        

           
    }
    
    public static void insertarVehiculo(){
        
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
