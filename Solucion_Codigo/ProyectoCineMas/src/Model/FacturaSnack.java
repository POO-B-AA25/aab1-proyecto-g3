package Model;

import java.util.Date;

public class FacturaSnack {
    private String numero;
    private Date fecha;
    private Cliente cliente;
    private Snack snack; 
    private double subtotal;
    private double valorTotal;

    public FacturaSnack() {}

    public FacturaSnack(String numero, Date fecha, Cliente cliente, Snack snack) {
        this.numero = numero;
        this.fecha = fecha;
        this.cliente = cliente;
        this.snack = snack;
        this.subtotal = calcularSubtotalInterno();
        this.valorTotal = calcularValorTotalInterno(); 
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Snack getSnack() {
        return snack;
    }

    public void setSnack(Snack snack) {
        this.snack = snack;
        // Recalcular si el snack cambia
        this.subtotal = calcularSubtotalInterno();
        this.valorTotal = calcularValorTotalInterno();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    
    public double calcularSubtotalInterno() {
        if (snack != null) {
            return snack.calcularTotal(); 
        }
        return 0.0;
    }

    public double calcularValorTotalInterno() {
       
        return calcularSubtotalInterno();
    }

    public double calcularSubtotal() {
        this.subtotal = calcularSubtotalInterno();
        return this.subtotal;
    }

    public double calcularValorTotal() {
        this.valorTotal = calcularValorTotalInterno();
        return this.valorTotal;
    }

    @Override
    public String toString() {
        return "FacturaSnack{" +
               "numero='" + numero + '\'' +
               ", fecha=" + fecha +
               ", cliente=" + (cliente != null ? cliente.getNombreApellidoCliente() : "N/A") +
               ", snack=" + snack +
               ", subtotal=" + String.format("%.2f", subtotal) +
               ", valorTotal=" + String.format("%.2f", valorTotal) +
               '}';
    }
}
