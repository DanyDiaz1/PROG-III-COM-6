package services;

import models.entities.Bebida;
import models.entities.Cliente;
import models.entities.Pedido;
import models.enums.EstadoPedido;
import models.exceptions.PedidoInvalidoException;
import repositories.impl.PedidoRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final BebidaService bebidaService;
    private final ClienteService clienteService;

    public PedidoService() {
        this.pedidoRepository = new PedidoRepository();
        this.bebidaService = new BebidaService();
        this.clienteService = new ClienteService();
    }

    public Optional<Pedido> buscarPorId(int id) {
        try {
            Pedido pedido = pedidoRepository.findById(id);
            return Optional.ofNullable(pedido);
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el pedido con ID " + id, e);
        }
    }

    public List<Pedido> listarPedidosPorCliente(int idCliente) {
        try {
            Optional<Cliente> cliente = clienteService.buscarPorId(idCliente);
            if (cliente.isPresent()){
                System.out.println(cliente.get());
                return pedidoRepository.findAll().stream()
                        .filter(pedido -> pedido.getIdCliente() == idCliente)
                        .toList();
            }else{
                System.out.println("El usuario indicado no existe");
                return List.of();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener pedidos del cliente con ID " + idCliente, e);
        }
    }

    public void cambiarEstadoPedido(int idPedido, EstadoPedido nuevoEstado) {
        Optional<Pedido> pedidoOpt = buscarPorId(idPedido);
        if (pedidoOpt.isEmpty()) {
            System.out.println("El pedido no existe.");
        } else {
            Pedido pedido = pedidoOpt.get();
            EstadoPedido estadoActual = pedido.getEstado();

            if (estadoActual == EstadoPedido.PENDIENTE && nuevoEstado == EstadoPedido.PREPARADO ||
                    estadoActual == EstadoPedido.PREPARADO && nuevoEstado == EstadoPedido.ENTREGADO) {
                pedido.setEstado(nuevoEstado);
                try {
                    pedidoRepository.update(pedido);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Transicion de estado realizada: " + estadoActual + " → " + nuevoEstado);
            } else {
                System.out.println("Transicion de estado no válida: " + estadoActual + " → " + nuevoEstado);
            }
        }
    }

    public void registrarPedido(int idCliente, int idBebida, int cantidad) {
        if (cantidad <= 0) {
            throw new PedidoInvalidoException("La cantidad debe ser mayor que cero.");
        }

        Optional<Cliente> clienteOpt = clienteService.buscarPorId(idCliente);
        Optional<Bebida> bebidaOpt = bebidaService.buscarPorId(idBebida);

        if (clienteOpt.isEmpty()) {
            throw new PedidoInvalidoException("El cliente con ID " + idCliente + " no existe.");
        }

        if (bebidaOpt.isEmpty()) {
            throw new PedidoInvalidoException("La bebida con ID " + idBebida + " no existe.");
        }

        Bebida bebida = bebidaOpt.get();
        if (bebida.getStock() < cantidad) {
            throw new PedidoInvalidoException("Stock insuficiente. Disponible: " + bebida.getStock());
        }

        // Descontar stock
        bebida.setStock(bebida.getStock() - cantidad);
        bebidaService.actualizarBebida(bebida);

        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setIdCliente(idCliente);
        nuevoPedido.setIdBebida(idBebida);
        nuevoPedido.setCantidad(cantidad);
        nuevoPedido.setEstado(EstadoPedido.PENDIENTE);

        try {
            pedidoRepository.create(nuevoPedido);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Pedido registrado con éxito.");
    }

    public Map<EstadoPedido, Long> contarPedidosPorEstado() {
        List<Pedido> pedidos;
        try {
            pedidos = pedidoRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pedidos.stream()
                .collect(Collectors.groupingBy(
                        Pedido::getEstado,
                        Collectors.counting()
                ));
    }

    public void eliminarPedido(int id) {
        try {
            pedidoRepository.delete(id);
            System.out.println("✅ Pedido eliminado.");
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar pedido: " + e.getMessage());
        }
    }
}
