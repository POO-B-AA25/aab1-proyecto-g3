package Model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Horario {
    private Date fecha;
    private String hora;
    private List<Pelicula> peliculas;
    private List<Sala> salas;

    public Horario() {
        peliculas = new ArrayList<>();
        salas = new ArrayList<>();
    }

    public Horario(Date fecha, String hora) {
        this();
        this.fecha = fecha;
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void agregarPelicula(Pelicula pelicula) {
        if (pelicula != null) {
            peliculas.add(pelicula);
        }
    }

    public void agregarSala(Sala sala) {
        if (sala != null) {
            salas.add(sala);
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();

        sb.append("====================================\n");
        sb.append(" Fecha        : ").append(fecha != null ? sdf.format(fecha) : "Sin fecha").append("\n");
        sb.append(" Hora         : ").append(hora != null ? hora : "Sin hora").append("\n");

        sb.append(" Películas    :\n");
        if (peliculas == null || peliculas.isEmpty()) {
            sb.append("   - No hay películas asignadas\n");
        } else {
            for (Pelicula p : peliculas) {
                sb.append("   - ").append(p.getTitulo()).append(" (").append(p.getGenero()).append(")\n");
            }
        }

        sb.append(" Salas        :\n");
        if (salas == null || salas.isEmpty()) {
            sb.append("   - No hay salas asignadas\n");
        } else {
            for (Sala s : salas) {
                sb.append("   - Sala ").append(s.getNumSala())
                  .append(" - ").append(s.getNombre())
                  .append(" (Capacidad: ").append(s.getCapacidad())
                  .append(", Disponible: ").append(s.isDisponible() ? "Sí" : "No").append(")\n");
            }
        }

        sb.append("====================================");
        return sb.toString();
    }
    
}

    

