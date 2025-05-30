package Model;
import java.util.Date;

public class FacturaFuncion {
    public String numero;
    public Date fecha;
    public Cliente cliente;
    public Boleto boleto;
    public Promocion promocion; 
    public double subtotal;   
    public double valorTotal; 

    public FacturaFuncion() {}

    public FacturaFuncion(String numero, Date fecha, Cliente cliente, Boleto boleto, Promocion promocion) {
        this.numero = numero;
        this.fecha = fecha;
        this.cliente = cliente;
        this.boleto = boleto;
        this.promocion = promocion;
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
        this.subtotal = calcularSubtotalInterno();
        this.valorTotal = calcularValorTotalInterno();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
        // Recalcular si el boleto cambia
        this.subtotal = calcularSubtotalInterno();
        this.valorTotal = calcularValorTotalInterno();
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
        // Recalcular si la promoción cambia
        this.valorTotal = calcularValorTotalInterno();
    }
    
    public double getSubtotal() {
        return subtotal;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double calcularSubtotalInterno() {
        if (boleto != null) {
            return boleto.calcularSubtotal();
        }
        return 0.0;
    }

    public double calcularValorTotalInterno() {
        double sub = calcularSubtotalInterno();
        if (promocion != null && fecha != null && promocion.aplicaDescuento(fecha)) {
            return sub * (1 - promocion.getPorcentajeDescuento());
        }
        return sub;
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
        return "FacturaFuncion{" +
               "numero='" + numero + '\'' +
               ", fecha=" + fecha +
               ", cliente=" + (cliente != null ? cliente.getNombreApellidoCliente() : "N/A") +
               ", boleto=" + boleto +
               ", promocion=" + (promocion != null ? promocion.getDescripcionPromo() : "N/A") +
               ", subtotal=" + String.format("%.2f", subtotal) +
               ", valorTotal=" + String.format("%.2f", valorTotal) +
               '}';
    }
}
