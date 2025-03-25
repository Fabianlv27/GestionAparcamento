
package gestionaparcamiento;

import java.util.ArrayList;

/**
 *
 * @author CEEP
 */
public class Aparcamiento {
    
    public ArrayList <Vehiculo> vehiculos = new ArrayList <>();
    
    private int capacidad=10;

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
    
    //metodos de la clase
    public void introducir_vehiculo(){
        //todoLogic
    }
    
    public void sacar_vehiculo(){
        //toDoLogic
    }

    @Override
    public String toString() {
        return "Aparcamiento{" + "\nvehiculos:" + vehiculos + ",\n capacidad:" + capacidad + '}';
    }
    
}
