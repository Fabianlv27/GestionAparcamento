package gestionaparcamiento;

import static gestionaparcamiento.GestionAparcamiento.apar;
import static gestionaparcamiento.GestionAparcamiento.rutaArchivo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    public void CalcularTiempo() {
        System.out.println("Introduce Matricula: ");
        String Mtr = dato.next();
        for (Vehiculo e : vehiculos) {
            if (e.getMatricula().equalsIgnoreCase(Mtr)) {
                LocalDateTime fechaSalida = LocalDateTime.now();
                long minutos = ChronoUnit.MINUTES.
                        between(e.getFecha(), fechaSalida);
                System.out.println("Has estado : " + Colores.VERDE + (minutos) + " Minutos" + Colores.RESET);
                System.out.println("Has estado : " + Colores.VERDE + (minutos / 60) + " Horas" + Colores.RESET);
                System.out.println("Has estado : " + Colores.VERDE + (minutos / 1440) + " Dias" + Colores.RESET);
                return;
            }
        }
        System.out.println("No se encontro su vehiculo :(");
    }

    public void verDisponibles() {
        System.out.println(Colores.VERDE + "Hay " + capacidad + " Espacios libres" + Colores.RESET);
    }

    //metodos de la clase
    public void introducir_vehiculo() {
        if (capacidad == 0) {
            System.out.println(Colores.ROJO + "No hay espacio disponible !" + Colores.RESET);
            return;
        }
        boolean encontrado = false;

        System.out.print(Colores.CYAN + "Introduzca la matricula del vehiculo: " + Colores.RESET);
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
            System.out.println(" 0 para Automovil \n 1 para Camion");
            int respuesta = dato.nextInt();
            System.out.println("Tiene abono? S/N");
            String respuesta2 = dato.next();
            if (respuesta2.equalsIgnoreCase("S")) {
                abono = true;
            }

            if (respuesta == 0) {
                String Tipo="Thun thung Sahur";
                System.out.println("Tipo:");
                System.out.println("1) TodoTerreno");
                System.out.println("2) Turismo");
                System.out.println("3) Furgoneta");

                int TipoOpcion = dato.nextInt();
                if (TipoOpcion == 1) {
                    Tipo = "TodoTerreno";
                } else if (TipoOpcion == 2) {
                    Tipo = "Turismo";

                }else if (TipoOpcion == 3) {
                    Tipo = "Furgoneta";

                }else{
                    System.out.println(Colores.ROJO+"Has introducido un valor no valido" + Colores.RESET);
                }
                Automovil NewAuto = new Automovil(Tipo, matr, LocalDateTime.now(), abono);
                vehiculos.add(NewAuto);
            }
            if (respuesta == 1) {
                System.out.println("Numero de ejes: ");
                int ejes = dato.nextInt();
                Camion NewCamion = new Camion(ejes, matr, LocalDateTime.now(), abono);
                vehiculos.add(NewCamion);
            }
            actualizarArchivo();
            capacidad = capacidad - 1;
            System.out.println(Colores.VERDE + "Vehiculo añadido correctamente." + Colores.RESET);
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
                        System.out.println(Colores.VERDE + "Importe a pagar: ");
                        System.out.println(e.calcularImporte() + Colores.RESET);
                        vehiculos.remove(e);

                        // Ahora reescribimos el archivo con la lista actualizada
                        actualizarArchivo();
                        capacidad++;
                        System.out.println( Colores.ROJO+"Vehiculo eliminado correctamente."+Colores.RESET);
                    }
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println(Colores.ROJO+"El vehiculo con esa matrícula no fue encontrado."+Colores.RESET);
            }
        } catch (Exception e) {
            System.out.println(Colores.ROJO+"Hubo un error al intentar eliminar el vehiculo."+Colores.RESET);
            e.printStackTrace();
        }
    }

    public void actualizarArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Vehiculo v : apar.getVehiculos()) {
                bw.write(v.String2());  // Convierte el objeto en texto
                bw.newLine();  // Nueva línea para cada vehículo
            }
          //  System.out.println("Archivo actualizado correctamente.");
        } catch (IOException e) {
            System.out.println( Colores.ROJO+"Error al actualizar el archivo."+Colores.RESET);
            e.printStackTrace();
        }

    }

    public void buscarPorFecha() {
        System.out.println("Buscar por Fecha");
        String option = "";
        LocalDateTime date = LocalDateTime.now();
        System.out.println("Seleccione fecha: dd-mm-yyyy");
        option = dato.next();
        date = date.withDayOfMonth(Integer.parseInt(option.split("-")[0])).
                withMonth(Integer.parseInt(option.split("-")[1])).
                withYear(Integer.parseInt(option.split("-")[2]));
        System.out.println("Seleccione la Hora: hh:mm");
        option = dato.next();
        date = date.withHour(Integer.parseInt(option.split(":")[0])).
                withMinute(Integer.parseInt(option.split(":")[1])).
                withSecond(0).
                withNano(0);
        System.out.println("Vehiclulos ");
        for (Vehiculo e : vehiculos) {
            if (e.getFecha().isAfter(date)) {
                System.out.println(e);
            }
        }
    }

    public double porcentaje(int cant, int total) {

            double porcentaje = Math.round(((cant * 100) / total) * 10.0) / 10.0;
            return porcentaje;
        

    }

    public int caracteres(double porcent) {
        int cantidadAutosCarcteres = (int) (30 * (porcent / 100));
        return cantidadAutosCarcteres;

    }

    public void verEstadisticas() {
        int cAutos = 0;
        if (vehiculos.size() == 0) {
            System.out.println("El Aparcamento se encuentra vacio xd");
            return;
        }
        for (Vehiculo e : vehiculos) {
            if (e.getClass().getSimpleName().equals("Automovil")) {
                cAutos++;
            }
        }
        double Aporcentaje = porcentaje(cAutos, vehiculos.size());
        int Acarcateres = caracteres(Aporcentaje);
        System.out.print("\u001B[31m" + "Autos: " + Math.round(Aporcentaje) + "% " + "(" + cAutos + ")" + "\u001B[31m");
        System.out.println("\t" + "\u001B[32m" + "Camiones: " + (100 - Math.round(Aporcentaje)) + "% " + "(" + (vehiculos.size() - cAutos) + ")" + "\u001B[32m");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < Acarcateres; j++) {
                System.out.print("\u001B[31m" + "|" + "\u001B[31m");

            }
            for (int j = 0; j < 30 - Acarcateres; j++) {
                System.out.print("\u001B[32m" + "|" + "\u001B[32m");

            }
            System.out.println("");
        }

        int cTTerreno = 0;
        int cTurismo = 0;
        int cFurgoneta = 0;
        for (Vehiculo e : vehiculos) {
            if (e instanceof Automovil) {
                Automovil a = (Automovil) e;
                if (a.getTipo().equals("TodoTerreno")) {
                    cTTerreno++;
                } else if (a.getTipo().equals("Furgoneta")) {
                    cFurgoneta++;
                } else {
                    cTurismo++;
                }
            }
        }
        int suma = cTTerreno + cTurismo + cFurgoneta;
        double PTTerreno = porcentaje(cTTerreno, suma);
        int ccTTerreno = caracteres(PTTerreno);

        double PTurismo = porcentaje(cTurismo, suma);
        int ccTurismo = caracteres(PTurismo);

        double PFurgoneta = porcentaje(cFurgoneta, suma);
        int ccFurgoneta = caracteres(PFurgoneta);
        int cctotal = ccTTerreno + ccFurgoneta + ccTurismo;
        while (cctotal < 30) {
            ccTTerreno++;
            ccFurgoneta++;
            ccTurismo++;
            cctotal += 3;
        }
        System.out.print("\n" + "\u001B[31m" + "Todo Terreno: " + Math.round(PTTerreno) + "% " + "(" + cTTerreno + ")" + "\u001B[31m");
        System.out.println("\t" + "\u001B[32m" + "Turismo: " + (Math.round(PTurismo)) + "% " + "(" + (cTurismo) + ")" + "\u001B[32m");
        System.out.println("\t" + "\u001B[35m" + "Furgoneta: " + (Math.round(PFurgoneta)) + "% " + "(" + (cFurgoneta) + ")" + "\u001B[35m" + "\n");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < ccTTerreno; j++) {
                System.out.print("\u001B[31m" + "|" + "\u001B[31m");

            }
            for (int j = 0; j < ccTurismo; j++) {
                System.out.print("\u001B[32m" + "|" + "\u001B[32m");

            }
            for (int j = 0; j < ccFurgoneta; j++) {
                System.out.print("\u001B[35m" + "|" + "\u001B[35m");

            }
            System.out.println("");
        }
    }

}
