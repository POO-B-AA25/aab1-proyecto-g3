package Controller;

import Model.Sala;
import java.util.ArrayList;
import java.util.List;

public class SalaControlador {
    public List<Sala> salas;

    public SalaControlador() {
        salas = new ArrayList<>();
    }

    public void agregarSala(Sala sala) {
        if (sala != null) {
            
            salas.add(sala);
        }
    }

    public List<Sala> obtenerSalas() {
        return new ArrayList<>(salas);
    }

    public Sala buscarPorNumero(int numSala) {
        for (Sala s : salas) {
            if (s.getNumSala() == numSala) {
                return s;
            }
        }
        return null;
    }

    public boolean eliminarSala(int numSala) {
        Sala s = buscarPorNumero(numSala);
        if (s != null) {
            return salas.remove(s);
        }
        return false;
    }
    
    public boolean actualizarSala(int numSalaExistente, Sala salaActualizada) {
        Sala sExistente = buscarPorNumero(numSalaExistente);
        if (sExistente != null && salaActualizada != null) {
            sExistente.setNombre(salaActualizada.getNombre());
            sExistente.setCapacidad(salaActualizada.getCapacidad());
            sExistente.setDisponibilidad(salaActualizada.isDisponible());
            return true;
        }
        return false;
    }
}
