package Model;

public class Boleto {
    
    public Funcion funcion;
    public int cantidad;
    public double precioUnitario;

    public Boleto() {}

    public Boleto(Funcion funcion, int cantidad, double precioUnitario) {
        this.funcion = funcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double calcularSubtotal() {
        return cantidad * precioUnitario;
    }

    @Override
    public String toString() {
    String funcionStr = (funcion != null && funcion.getPelicula() != null) 
                        ? funcion.getPelicula().getTitulo() 
                        : "N/A";

    double total = cantidad * precioUnitario;

    return String.format(
        "Boleto:\n" +
        "  Función:        %s\n" +
        "  Cantidad:       %d\n" +
        "  Precio Unit.:   $%.2f\n" +
        "  Total:          $%.2f",
        funcionStr, cantidad, precioUnitario, total);
    }
}
