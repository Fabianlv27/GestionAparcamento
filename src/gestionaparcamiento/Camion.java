/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionaparcamiento;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author CEEP
 */
public class Camion extends Vehiculo {

    private int numEjes;

    //Getters and Setters
    public int getNumEjes() {
        return numEjes;
    }

    public void setNumEjes(int numEjes) {
        this.numEjes = numEjes;
    }

    //Constructor
    public Camion() {
    }

    public Camion(int numEjes, String matricula, LocalDateTime fecha, boolean abono) {
        super(matricula, fecha, abono);
        this.numEjes = numEjes;
    }

    @Override
    public double calcularImporte() {
        //Damos valor a la fecha de salida con la fecha que es cuando introduce de nuevo la matirucla
        LocalDateTime fechaSalida = LocalDateTime.now();
        //Convertimos a minutos y hacemso la diferencia entre la entrada (Fecha) y Salida
        //Para calcular el tiempo total en minutos
        long minutos = ChronoUnit.MINUTES.
                between(this.getFecha(), fechaSalida);
        System.out.println(Colores.CYAN+"Fecha de Inicio: "+Colores.RESET);
                System.out.print(this.getFecha().withNano(0).withSecond(0));
        System.out.println(Colores.CYAN+"Tiempo en minutos: "+Colores.RESET+minutos);
        System.out.println(Colores.CYAN+"Numero de Ejes: "+Colores.RESET+this.numEjes);
        double tasa=0;
        double total=0;
        //Tasa equivale a lo que cuesta según las caracteristicas del vehiculo
        if (this.numEjes<=3){
            tasa = 4.5;
        }else{
            tasa = 6.5;
        }
        //Calculamos el precio a pagar por el usuario
        total = Math.round((minutos * tasa / 60)* 100.0) / 100.0; 
        if(this.isAbono()){
            //Si tiene abono a ese total le aplicamos el 40% de descuento
            total -= (total*0.4);
        }
        total=Math.round(total*100.0)/100.0;
        return total;
    }

    @Override
    public String toString() {
        return super.toString() + Colores.VERDE+ "\nnumEjes=" + numEjes + Colores.RESET + "\n";
    }
    public String String2() {
        return super.String2()+ numEjes;
    }
}
