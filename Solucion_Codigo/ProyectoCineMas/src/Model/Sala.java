package Model;

public class Sala {
    
    public int numSala;
    public String nombre;
    public int capacidad;
    public boolean disponibilidad;

    public Sala() {}

    public Sala(int numSala, String nombre, int capacidad) {
        this.numSala = numSala;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.disponibilidad = true; 
    }

    public int getNumSala() {
        return numSala;
    }

    public void setNumSala(int numSala) {
        this.numSala = numSala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isDisponible() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Override
    public String toString() {
    return "-------------------------------\n" +
           "Numero de Sala : " + numSala + "\n" +
           "Nombre         : " + nombre + "\n" +
           "Capacidad      : " + capacidad + "\n" +
           "Disponible     : " + (disponibilidad ? "Si" : "No") + "\n" +
           "-------------------------------";
    }
}
