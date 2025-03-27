/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionaparcamiento;

import java.time.LocalDateTime;

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

        double tiempoTotal=0;
        LocalDateTime entrada= super.getFecha();
        LocalDateTime salida=LocalDateTime.now();
        
        
        double total_pagar = 0.0;
        if (numEjes <= 3) {
            //todo logic
            //Ejes ≤ 3 → minutos * 4.5 € / 60 
        } else {
            //Ejes > 3 → minutos * 6.5 € / 60
        }
        return total_pagar;
    }

    @Override
    public String toString() {
        return "Camion:\n" + super.toString() + "\nnumEjes=" + numEjes+"\n";
    }
    public String String2() {
        return super.String2()+ numEjes+"\n";
    }
}
