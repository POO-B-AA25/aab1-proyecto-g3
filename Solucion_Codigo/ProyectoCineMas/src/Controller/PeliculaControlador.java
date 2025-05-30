package Controller;

import Model.Pelicula;
import java.util.ArrayList;
import java.util.List;

public class PeliculaControlador {
    private List<Pelicula> peliculas;

    public PeliculaControlador() {
        peliculas = new ArrayList<>();
    }

    public void agregarPelicula(Pelicula pelicula) {
        if (pelicula != null) {
            peliculas.add(pelicula);
        }
    }

    public List<Pelicula> obtenerPeliculas() {
        return new ArrayList<>(peliculas); 
    }

    public Pelicula buscarPorTitulo(String titulo) {
        if (titulo == null) return null;
        for (Pelicula p : peliculas) {
            if (p.getTitulo().equalsIgnoreCase(titulo)) {
                return p;
            }
        }
        return null;
    }

    public boolean eliminarPelicula(String titulo) {
        Pelicula p = buscarPorTitulo(titulo);
        if (p != null) {
            return peliculas.remove(p);
        }
        return false;
    }

    public boolean actualizarPelicula(String tituloAntiguo, Pelicula peliculaActualizada) {
        Pelicula pExistente = buscarPorTitulo(tituloAntiguo);
        if (pExistente != null && peliculaActualizada != null) {
            
            pExistente.setSinopsis(peliculaActualizada.getSinopsis());
            pExistente.setGenero(peliculaActualizada.getGenero());
            pExistente.setClasificacion(peliculaActualizada.getClasificacion());
            return true;
        }
        return false;
    }
}