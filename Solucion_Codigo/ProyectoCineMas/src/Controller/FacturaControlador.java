package Controller;
import Model.FacturaFuncion;
import Model.FacturaSnack;
import java.util.ArrayList;
import java.util.List;

public class FacturaControlador {
    private List<FacturaFuncion> facturasFuncion;
    private List<FacturaSnack> facturasSnack;

    public FacturaControlador() {
        facturasFuncion = new ArrayList<>();
        facturasSnack = new ArrayList<>();
    }

    public void agregarFacturaFuncion(FacturaFuncion factura) {
        if (factura != null) {
            facturasFuncion.add(factura);
        }
    }

    public void agregarFacturaSnack(FacturaSnack factura) {
        if (factura != null) {
            facturasSnack.add(factura);
        }
    }

    public List<FacturaFuncion> obtenerFacturasFuncion() {
        return new ArrayList<>(facturasFuncion);
    }

    public List<FacturaSnack> obtenerFacturasSnack() {
        return new ArrayList<>(facturasSnack);
    }

    public FacturaFuncion buscarFacturaFuncionPorNumero(String numero) {
        if (numero == null) return null;
        for (FacturaFuncion ff : facturasFuncion) {
            if (ff.getNumero().equalsIgnoreCase(numero)) {
                return ff;
            }
        }
        return null;
    }

    public FacturaSnack buscarFacturaSnackPorNumero(String numero) {
        if (numero == null) return null;
        for (FacturaSnack fs : facturasSnack) {
            if (fs.getNumero().equalsIgnoreCase(numero)) {
                return fs;
            }
        }
        return null;
    }
}