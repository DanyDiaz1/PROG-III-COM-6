package services;

import models.entities.Bebida;
import models.enums.TipoBebida;
import repositories.impl.BebidaRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BebidaService {

    private final BebidaRepository bebidaRepository;

    public BebidaService(){
        bebidaRepository =new BebidaRepository();
    }

    public void registrarBebida(Bebida bebida) {
        try {
            bebidaRepository.create(bebida);
            System.out.println("✅ Bebida registrada correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al guardar bebida: " + e.getMessage());
        }
    }

    public void actualizarBebida(Bebida bebida) {
        try {
            bebidaRepository.update(bebida);
            System.out.println("✅ Bebida actualizada.");
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar bebida: " + e.getMessage());
        }
    }

    public void eliminarBebida(int id) {
        try {
            bebidaRepository.delete(id);
            System.out.println("✅ Bebida eliminada.");
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar bebida: " + e.getMessage());
        }
    }

    public Optional<Bebida> buscarPorId(int id) {
        try {
            Bebida bebida = bebidaRepository.findById(id);
            return Optional.ofNullable(bebida);
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar bebida: " + e.getMessage());
            return Optional.empty();
        }
    }

    public List<Bebida> listarBebidas() {
        try {
            return bebidaRepository.findAll();
        } catch (SQLException e) {
            System.err.println("❌ Error al listar bebidas: " + e.getMessage());
            return List.of();
        }
    }

    public List<Bebida> filtrarPorTipo(TipoBebida tipo) {
        try {
            return bebidaRepository.findAll().stream()
                    .filter(b -> b.getTipo().equals(tipo))
                    .toList();
        } catch (SQLException e) {
            System.out.println("Error al filtrar bebidas por tipo: " + e.getMessage());
            return List.of();
        }
    }

    public List<Bebida> filtrarPorBajoStock(int stock) {
        try {
            return bebidaRepository.findAll().stream()
                    .filter(b -> b.getStock() < stock)
                    .toList();
        } catch (SQLException e) {
            System.out.println("Error al filtrar bebidas por bajo stock: " + e.getMessage());
            return List.of();
        }
    }
}