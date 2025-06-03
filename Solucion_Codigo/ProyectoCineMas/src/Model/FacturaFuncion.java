package Model;

import java.util.Date;

public class FacturaFuncion {
    public String numero;
    public Date fecha;
    public Cliente cliente;
    public Boleto boleto;
    private Promocion promocion; 
    public double subtotal;
    public double valorDescuento; 
    public double valorTotal;   

    public FacturaFuncion(String numero, Date fecha, Cliente cliente, Boleto boleto, Promocion promocion) {
        this.numero = numero;
        this.fecha = fecha;
        this.cliente = cliente;
        this.boleto = boleto;
        this.promocion = promocion; 

        if (this.boleto != null) {
            this.subtotal = this.boleto.getCantidad() * this.boleto.getPrecioUnitario();
        } else {
            this.subtotal = 0.0;
        }

        this.valorDescuento = 0.0;
        if (this.promocion != null && this.promocion.getPorcentajeDescuento() > 0 && this.subtotal > 0) {
            this.valorDescuento = this.subtotal * this.promocion.getPorcentajeDescuento();
            this.valorTotal = this.subtotal - this.valorDescuento;
        } else {
            this.valorTotal = this.subtotal; 
        }
    }

    public String getNumero() {
        return numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public Promocion getPromocion() {
        return promocion; 
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getValorDescuentoAplicado() {
        return valorDescuento;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
        if (this.boleto != null) {
            this.subtotal = this.boleto.getCantidad() * this.boleto.getPrecioUnitario();
        } else {
            this.subtotal = 0.0;
        }
        if (this.promocion != null && this.promocion.getPorcentajeDescuento() > 0 && this.subtotal > 0) {
            this.valorDescuento = this.subtotal * this.promocion.getPorcentajeDescuento();
            this.valorTotal = this.subtotal - this.valorDescuento;
        } else {
            this.valorDescuento = 0.0;
            this.valorTotal = this.subtotal;
        }
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
        if (this.promocion != null && this.promocion.getPorcentajeDescuento() > 0 && this.subtotal > 0) {
            this.valorDescuento = this.subtotal * this.promocion.getPorcentajeDescuento();
            this.valorTotal = this.subtotal - this.valorDescuento;
        } else {
            this.valorDescuento = 0.0;
            this.valorTotal = this.subtotal;
        }
    }
}