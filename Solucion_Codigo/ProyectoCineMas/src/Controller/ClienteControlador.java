package Controller;

import Model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteControlador {
    
    public List<Cliente> clientes;

    public ClienteControlador() {
        clientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        if (cliente != null) {
           
            clientes.add(cliente);
        }
    }

    public List<Cliente> obtenerClientes() {
        return new ArrayList<>(clientes);
    }

    public Cliente buscarPorNombre(String nombre) {
        if (nombre == null) return null;
        for (Cliente c : clientes) {
            if (c.getNombreApellidoCliente().equalsIgnoreCase(nombre)) {
                return c; 
            }
        }
        return null;
    }
    
    public Cliente buscarPorEmail(String email) {
        if (email == null) return null;
        for (Cliente c : clientes) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }

    public boolean eliminarCliente(String email) { 
        Cliente c = buscarPorEmail(email);
        if (c != null) {
            return clientes.remove(c);
        }
        return false;
    }
    
    public boolean actualizarCliente(String emailExistente, Cliente clienteActualizado) {
        Cliente cExistente = buscarPorEmail(emailExistente);
        if (cExistente != null && clienteActualizado != null) {
            cExistente.setNombreApellidoCliente(clienteActualizado.getNombreApellidoCliente());
            cExistente.setEmail(clienteActualizado.getEmail()); 
            cExistente.setTelefono(clienteActualizado.getTelefono());
            return true;
        }
        return false;
    }
}
