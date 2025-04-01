package gestionaparcamiento;

import java.time.LocalDateTime;
import java.util.Objects;

//Clase abstracta porque no habrá manera para disponer de objetos
public abstract class Vehiculo implements Comparable<Vehiculo>{

    protected String matricula;
    //usamos localDateTime
    protected LocalDateTime fecha;
    protected boolean abono;

    //Getter and Setter
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public boolean isAbono() {
        return abono;
    }

    public void setAbono(boolean abono) {
        this.abono = abono;
    }

    //Constructor
    public Vehiculo() {
    }

    //todo Quitar parametro fecha por que no es necesario ya que la fecha la coloca automaticamente
    public Vehiculo(String matricula, LocalDateTime fecha, boolean abono) {
       
        this.matricula = matricula;
        //localDateTime.now para usar la hora presente
        this.fecha = fecha;
        this.abono = abono;
    }

    //toString
    @Override
    public String toString() {
        return "\nmatricula: " + matricula
                + "\nfecha: " + fecha.getDayOfMonth()+"-"+fecha.getMonthValue()+
                "-"+fecha.getYear() +"\nHora:"+ fecha.getHour()+": "+ fecha.getMinute()+
                ": "+fecha.getSecond()+"\nabono: " + abono;
    }
   public String String2() {
        return matricula+","+fecha+
                ","+ abono +",";
    } 
   

    // hashCode and Equals 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.matricula);
        hash = 17 * hash + Objects.hashCode(this.fecha);
        hash = 17 * hash + (this.abono ? 1 : 0);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehiculo other = (Vehiculo) obj;
        return Objects.equals(this.matricula, other.matricula);
    }

    //compareTo
    @Override
    public int compareTo(Vehiculo obj) {
        return this.matricula.compareTo(obj.matricula);
    }
    
    //CalcularImporte
    public abstract double calcularImporte();
}
