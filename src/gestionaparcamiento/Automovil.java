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
public class Automovil extends Vehiculo {

    private String Tipo;

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public Automovil(String Tipo, String matricula, LocalDateTime fecha, boolean abono) {
        super(matricula, fecha, abono);
        this.Tipo = Tipo;

    }
    @Override
    public String toString() {
        return super.toString() + Colores.VERDE + "\nTipo: " +  Colores.RESET+ Tipo  + "\n";
    }
    public String String2(){
            return  super.String2()+ Tipo;
    }

    @Override
    public double calcularImporte() {
        //Damos valor a la fecha de salida con la fecha que es cuando introduce de nuevo la matirucla
        LocalDateTime fechaSalida = LocalDateTime.now();
        //Convertimos a minutos y hacemso la diferencia entre la entrada (Fecha) y Salida
        //Para calcular el tiempo total en minutos
        //A VER SIS E CAMBIA
        long minutos = ChronoUnit.MINUTES.between(this.getFecha(), fechaSalida);
        System.out.println(Colores.CYAN+"Fecha de Inicio: "+Colores.RESET);
                System.out.print(this.getFecha().withNano(0).withSecond(0));
        System.out.println(Colores.CYAN+"Tiempo en minutos: "+Colores.RESET+minutos);
        double tasa=0;
        double total=0;
        System.out.println(Colores.CYAN+"Tipo: "+Colores.RESET+ Tipo);
        //Tasa equivale a lo que cuesta según las caracteristicas del vehiculo
        switch(this.Tipo){
            case "Turismo":
                tasa=1.5;
                break;
            case "TodoTerreno":
                tasa=2.5;
                break;
            case "Furgoneta":
                tasa=3.5;
                break;    
        }
        //Calculamos el precio a pagar por el usuario
        total= Math.round((minutos * tasa / 60)*100.0) / 100.0; 
        if(this.isAbono()){
            //Si tiene abono a ese total le aplicamos el 40% de descuento
            total -= (total*0.4);
        }
        return total;
    }

}
