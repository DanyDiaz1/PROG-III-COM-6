package model.services;

import model.entities.CuentaEntity;
import model.entities.UsuarioEntity;
import model.entities.enums.Permiso;
import model.exceptions.NoAutorizadoException;
import model.repositories.impl.CuentaRepository;
import model.repositories.impl.UsuarioRepository;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final UsuarioRepository usuarioRepository;

    public CuentaService() {
        cuentaRepository = new CuentaRepository();
        usuarioRepository = new UsuarioRepository();
    }


    public void depositar(Integer idCuenta, UsuarioEntity depositante, Double monto) {
        CuentaEntity cuenta;
        UsuarioEntity propietario;
        try {
            cuenta = cuentaRepository.findById(idCuenta)
                    .orElseThrow(() -> new NoSuchElementException("Cuenta no encontrada"));

            propietario = usuarioRepository.findById(cuenta.getUsuario_id())
                    .orElseThrow(NoSuchElementException::new);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (esClienteYNoEsPropietario(depositante, cuenta) ||
                (depositante.getCredencial().getPermiso() == Permiso.GESTOR
                        && propietario.getCredencial().getPermiso() != Permiso.CLIENTE) ) {
            throw new NoAutorizadoException("Clientes solo pueden depositar en sus propias cuentas");
        }

        actualizarSaldo(cuenta, monto);
    }

    public List<CuentaEntity> findByUserId(Integer userId, UsuarioEntity loggedUser){
        if (!Objects.equals(loggedUser.getId(), userId) ||
                loggedUser.getCredencial().getPermiso() == Permiso.CLIENTE)
            throw new NoAutorizadoException("Operacion no autorizada");
        try {
            return cuentaRepository.findAllByUserId(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean esClienteYNoEsPropietario(UsuarioEntity usuario, CuentaEntity cuenta) {
        return usuario.getCredencial().getPermiso() == Permiso.CLIENTE &&
                !cuenta.getUsuario_id().equals(usuario.getId());
    }

    private void actualizarSaldo(CuentaEntity cuenta, Double monto) {
        cuenta.setSaldo(cuenta.getSaldo() + monto);
        try {
            cuentaRepository.update(cuenta);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void transferir(Integer idCuentaOrigen, Integer idCuentaDestino,
                           Double monto, UsuarioEntity transferente) {

        CuentaEntity cuentaOrigen = null;
        CuentaEntity cuentaDestino = null;
        try {
            cuentaOrigen = cuentaRepository.findById(idCuentaOrigen)
                    .orElseThrow(() -> new NoSuchElementException("Cuenta origen no encontrada"));
            cuentaDestino = cuentaRepository.findById(idCuentaDestino)
                    .orElseThrow(() -> new NoSuchElementException("Cuenta destino no encontrada"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        validarPermisoTransferencia(cuentaOrigen, transferente);
        validarSaldoSuficiente(cuentaOrigen, monto);

        actualizarSaldo(cuentaOrigen, -monto);
        actualizarSaldo(cuentaDestino, monto);
    }

    private void validarPermisoTransferencia(CuentaEntity origen, UsuarioEntity transferente) {
        if (transferente.getCredencial().getPermiso() == Permiso.CLIENTE && !origen.getUsuario_id().equals(transferente.getId())) {
            throw new NoAutorizadoException("Clientes solo pueden transferir entre sus cuentas o a otros clientes");
        }
    }

    private void validarSaldoSuficiente(CuentaEntity cuenta, Double monto) {
        if (cuenta.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente para la transferencia");
        }
    }

    private List<CuentaEntity> findAll(Permiso permiso) {
        if (permiso == Permiso.CLIENTE) throw new NoAutorizadoException("No autorizado");

        try {
            return cuentaRepository.findAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public void viewCuentaCountByTipo(Permiso permiso) {


        List<CuentaEntity> cuentas = findAll(permiso);

        cuentas.stream()
                .collect(Collectors.groupingBy(
                        CuentaEntity::getTipo,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> Map.entry(list.size(), list)
                        )
                ))
                .forEach((perm, entry) -> {
                    System.out.println(perm + " -> Cantidad: " + entry.getKey() + ", Usuarios: " + entry.getValue());
                });
    }

}