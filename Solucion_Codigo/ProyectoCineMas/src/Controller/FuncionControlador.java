package Controller;

import Model.Funcion;
import Model.Pelicula;
import Model.Sala;   
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FuncionControlador {
    private List<Funcion> funciones;

    public FuncionControlador() {
        funciones = new ArrayList<>();
    }

    public void agregarFuncion(Funcion funcion) {
        if (funcion != null && funcion.getPelicula() != null && funcion.getSala()
                != null && funcion.getHorario() != null) {
            funciones.add(funcion);
        }
    }

    public List<Funcion> obtenerFunciones() {
        return new ArrayList<>(funciones);
    }

    public boolean eliminarFuncion(Funcion funcion) {
        if (funcion == null) return false;
        return funciones.remove(funcion);
    }

    public List<Funcion> buscarFuncionesPorPelicula(Pelicula pelicula) {
        if (pelicula == null) return new ArrayList<>();
        return funciones.stream()
                        .filter(f -> f.getPelicula().equals(pelicula)) 
                        .collect(Collectors.toList());
    }

    public List<Funcion> buscarFuncionesPorFecha(Date fecha) {
        if (fecha == null) return new ArrayList<>();
       
        return funciones.stream()
                        .filter(f -> {
                            if (f.getHorario() == null || f.getHorario().getFecha() == null) return false;
                            return f.getHorario().getFecha().getYear() == fecha.getYear() &&
                                   f.getHorario().getFecha().getMonth() == fecha.getMonth() &&
                                   f.getHorario().getFecha().getDate() == fecha.getDate();
                        })
                        .collect(Collectors.toList());
    }
}