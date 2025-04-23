package services;

import models.entities.Cliente;
import repositories.impl.ClienteRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService() {
        this.clienteRepository = new ClienteRepository();
    }

    public List<Cliente> listarTodos() {
        try {
            return clienteRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los clientes", e);
        }
    }

    public Optional<Cliente> buscarPorId(int id) {
        try {
            Cliente cliente = clienteRepository.findById(id);
            return Optional.ofNullable(cliente);
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cliente por ID", e);
        }
    }

    public List<Cliente> buscarPorLocalidad(String localidad) {
        return listarTodos().stream()
                .filter(c -> c.getLocalidad().equalsIgnoreCase(localidad))
                .toList();
    }
}
