package Model;

public class Pelicula {
    
    public String titulo;
    public String sinopsis;
    public String genero;
    public String clasificacion;

    public Pelicula() {}

    public Pelicula(String titulo, String sinopsis, String genero, String clasificacion) {
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.genero = genero;
        this.clasificacion = clasificacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    @Override
    public String toString() {
            return "-------------------------------\n" +
           "Titulo       : " + titulo + "\n" +
           "Género       : " + genero + "\n" +
           "Clasificacion: " + clasificacion + "\n" +
           "Sinopsis     : " + sinopsis + "\n" +
           "-------------------------------";
        }
    
}
