package Model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale; 

public class Promocion {
    public String descripcionPromo;
    public String diaAplica; 
    public double porcentajeDescuento; 

    public Promocion() {}

    public Promocion(String descripcionPromo, String diaAplic, double porcentajeDescuento) {
        this.descripcionPromo = descripcionPromo;
        this.diaAplica = diaAplic.toLowerCase();
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getDescripcionPromo() {
        return descripcionPromo;
    }

    public void setDescripcionPromo(String descripcionPromo) {
        this.descripcionPromo = descripcionPromo;
    }

    public String getDiaAplic() {
        return diaAplica;
    }

    public void setDiaAplic(String diaAplic) {
        this.diaAplica = diaAplic.toLowerCase();
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public boolean aplicaDescuento(Date fecha) {
        if (fecha == null || diaAplica == null || diaAplica.trim().isEmpty()) {
            return false;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", new Locale("es", "ES"));
        String diaSemana = sdf.format(fecha).toLowerCase();
        return diaSemana.equalsIgnoreCase(this.diaAplica);
    }

    @Override
    public String toString() {
        return "Promocion{" +
               "descripcionPromo='" + descripcionPromo + '\'' +
               ", diaAplic='" + diaAplica + '\'' +
               ", porcentajeDescuento=" + porcentajeDescuento +
               '}';
    }
}