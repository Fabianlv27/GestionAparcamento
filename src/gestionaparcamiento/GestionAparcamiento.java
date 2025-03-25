/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestionaparcamiento;

import java.time.LocalDateTime;

public class GestionAparcamiento {

    
    public static void main(String[] args) {
        Aparcamiento apar=new Aparcamiento();
        LocalDateTime Now=LocalDateTime.now();
        Automovil A1=new Automovil("TodoTerreno","1234AAA", Now, true);
   //     System.out.println(A1);
        
     apar.getVehiculos().add(A1);
        System.out.println(apar);
        
        
    }
}
