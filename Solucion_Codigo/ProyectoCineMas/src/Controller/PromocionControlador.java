package Controller;

import Model.Promocion;
import java.util.ArrayList;
import java.util.List;

public class PromocionControlador {
    public List<Promocion> promociones;

    public PromocionControlador() {
        promociones = new ArrayList<>();
    }

    public void agregarPromocion(Promocion promocion) {
        if (promocion != null) {
            promociones.add(promocion);
        }
    }

    public List<Promocion> obtenerPromociones() {
        return new ArrayList<>(promociones);
    }
    
    public Promocion buscarPromocionPorDescripcion(String descripcion) {
        if (descripcion == null) return null;
        for (Promocion p : promociones) {
            if (p.getDescripcionPromo().equalsIgnoreCase(descripcion)) {
                return p;
            }
        }
        return null;
    }

    public boolean eliminarPromocion(String descripcion) {
        Promocion p = buscarPromocionPorDescripcion(descripcion);
        if (p != null) {
            return promociones.remove(p);
        }
        return false;
    }
}
