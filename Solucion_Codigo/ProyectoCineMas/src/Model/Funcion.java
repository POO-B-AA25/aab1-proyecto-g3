package Model;

public class Funcion {
    public Pelicula pelicula;
    public Horario horario;
    public Sala sala;

    public Funcion() {}

    public Funcion(Pelicula pelicula, Horario horario, Sala sala) {
        this.pelicula = pelicula;
        this.horario = horario;
        this.sala = sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
    String titulo = (pelicula != null) ? pelicula.getTitulo() : "N/A";
    String nombreSala = (sala != null) ? sala.getNombre() : "N/A";
    String hora = (horario != null) ? horario.getHora() : "N/A";
    String fecha = (horario != null && horario.getFecha() != null) ? horario.getFecha().toString() : "N/A";

    return String.format(
        "Función:\n" +
        "  Película:   %s\n" +
        "  Fecha:      %s\n" +
        "  Hora:       %s\n" +
        "  Sala:       %s",
        titulo, fecha, hora, nombreSala
    );
}
}
    
