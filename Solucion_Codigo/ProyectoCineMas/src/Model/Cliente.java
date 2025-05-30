package Model;

public class Cliente {
    private String nombreApellidoCliente;
    private String email;
    private String telefono;

    public Cliente() {}

    public Cliente(String nombreApellidoCliente, String email, String telefono) {
        this.nombreApellidoCliente = nombreApellidoCliente;
        this.email = email;
        this.telefono = telefono;
    }

    public String getNombreApellidoCliente() {
        return nombreApellidoCliente;
    }

    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" +
               "nombreApellidoCliente='" + nombreApellidoCliente + '\'' +
               ", email='" + email + '\'' +
               ", telefono='" + telefono + '\'' +
               '}';
    }
}
