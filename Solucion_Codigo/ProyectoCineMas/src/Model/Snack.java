package Model;

public class Snack {
    
    public String nombre;
    public double precio;
    public int cantidad; 

    public Snack() {}

    public Snack(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double calcularTotal() {
        return precio * cantidad;
    }

    @Override
    public String toString() {
       return String.format(
            "------------SNACK----------\n" +
            " Snack:        %s\n" +
            " Precio Unit.: $%.2f\n" +
            " Cantidad:     %d\n" +
            " Total:        $%.2f\n" +
            "-----------------------------",
            nombre, precio, cantidad, calcularTotal()
        );
    }
}
    