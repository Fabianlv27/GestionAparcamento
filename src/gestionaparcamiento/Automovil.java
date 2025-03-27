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
        
        System.out.println("Automoviles: \n");
        return  super.toString() + "\nTipo: " + Tipo;
    }

    @Override
    public double calcularImporte() {
        double total_pagar = 0.0;
        switch (this.Tipo) {
            case "Turismo":

                break;
            case "TodoTerreno":

                break;
            case "Furgoneta":

                break;

        }
        return total_pagar;
    }

}
