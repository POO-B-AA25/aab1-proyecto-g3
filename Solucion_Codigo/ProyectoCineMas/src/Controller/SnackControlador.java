package Controller;

import Model.Snack;
import java.util.ArrayList;
import java.util.List;

public class SnackControlador {
    public List<Snack> snacksDisponibles; 

    public SnackControlador() {
        snacksDisponibles = new ArrayList<>();
    }

    public void agregarSnackACatalogo(Snack snack) {
        if (snack != null) {
           
            snacksDisponibles.add(snack);
        }
    }

    public List<Snack> obtenerSnacksDeCatalogo() {
        return new ArrayList<>(snacksDisponibles);
    }

    public Snack buscarPorNombreEnCatalogo(String nombre) {
        if (nombre == null) return null;
        for (Snack s : snacksDisponibles) {
            if (s.getNombre().equalsIgnoreCase(nombre)) {
                return s;
            }
        }
        return null;
    }
    
    public boolean eliminarSnackDeCatalogo(String nombre) {
        Snack s = buscarPorNombreEnCatalogo(nombre);
        if (s != null) {
            return snacksDisponibles.remove(s);
        }
        return false;
    }
    
    public boolean actualizarSnackEnCatalogo(String nombreAntiguo, Snack snackActualizado) {
        Snack sExistente = buscarPorNombreEnCatalogo(nombreAntiguo);
         if (sExistente != null && snackActualizado != null) {
            sExistente.setNombre(snackActualizado.getNombre());
            sExistente.setPrecio(snackActualizado.getPrecio());
            return true;
        }
        return false;
    }
}
