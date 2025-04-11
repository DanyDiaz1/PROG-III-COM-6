package model.services;



import model.entities.CredentialEntity;
import model.entities.CuentaEntity;
import model.entities.UsuarioEntity;
import model.entities.enums.Permiso;
import model.entities.enums.TipoCuenta;
import model.exceptions.NoAutorizadoException;
import model.repositories.impl.CredentialRepository;
import model.repositories.impl.CuentaRepository;
import model.repositories.impl.UsuarioRepository;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CuentaRepository cuentaRepository;
    private final CredentialRepository credentialRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
        this.cuentaRepository = new CuentaRepository();
        this.credentialRepository = new CredentialRepository();
    }

    public void createUsuario(UsuarioEntity usuario, CredentialEntity credential){

        try {
            usuarioRepository.save(usuario);
            Integer id = usuarioRepository.findLastId();
            credential.setId_usuario(id);
            credentialRepository.save(credential);
            cuentaRepository.save(new CuentaEntity(id, TipoCuenta.CAJA_AHORRO,0.0));
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<UsuarioEntity> logUser(String username, String password){

        try {
            Optional<CredentialEntity> credential = credentialRepository.findByUsername(username);
            if (credential.isPresent() && credential.get().getPassword().equals(password)){
                Optional<UsuarioEntity> user = usuarioRepository.findById(credential.get().getId_usuario());
                user.ifPresent(u -> {
                    try {
                        u.setCredencial(credential.get());
                        u.setCuentas(cuentaRepository.findAllByUserId(u.getId()));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<UsuarioEntity> findAll(Permiso permiso){
        if (permiso.equals(Permiso.CLIENTE)) throw new NoAutorizadoException("No autorizado");
        try {
            List<UsuarioEntity> usuarios = usuarioRepository.findAll();

            usuarios.forEach(u -> {
                try {
                    u.setCredencial(credentialRepository.findByUserId(u.getId()).orElseThrow(NoSuchElementException::new));
                    u.setCuentas(cuentaRepository.findAllByUserId(u.getId()));
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public Optional<UsuarioEntity> findByDNI(String dni, Permiso permiso){
        if (permiso.equals(Permiso.CLIENTE)) throw new NoAutorizadoException("No autorizado");
        try {
            return usuarioRepository.findAll().stream()
                    .filter(u -> u.getDni().equals(dni))
                    .peek(u -> {
                        try {
                            u.setCuentas(cuentaRepository.findAllByUserId(u.getId()));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .findFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<UsuarioEntity> findByEmail(String email, Permiso permiso){
        if (permiso.equals(Permiso.CLIENTE)) throw new NoAutorizadoException("No autorizado");
        try {
            return usuarioRepository.findAll().stream()
                    .filter(u -> u.getEmail().equals(email))
                    .peek(u -> {
                        try {
                            u.setCuentas(cuentaRepository.findAllByUserId(u.getId()));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .findFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void updateUser(UsuarioEntity user, UsuarioEntity loggedUser) throws SQLException {
        switch (loggedUser.getCredencial().getPermiso()) {
            case Permiso.ADMINISTRADOR -> usuarioRepository.update(user);
            case Permiso.GESTOR -> {
                if (!user.getCredencial().getPermiso().equals(Permiso.CLIENTE)) {
                    throw new SecurityException("Un GESTOR solo puede modificar CLIENTES.");
                }
                usuarioRepository.update(user);
            }
            case Permiso.CLIENTE -> {
                if (!loggedUser.getId().equals(user.getId())) {
                    throw new SecurityException("Un CLIENTE solo puede modificar sus propios datos.");
                }
                usuarioRepository.update(user);
            }
            default -> throw new SecurityException("Rol desconocido o sin permisos.");
        }
    }

    public void deleteUser(Integer idUsuario, Permiso permiso) {
        UsuarioEntity userToDelete = findUserById(idUsuario);

        if (!hasDeleteUpdatePermission(permiso, userToDelete)) {
            throw new SecurityException("No tiene permisos para eliminar este usuario.");
        }

        try {
            usuarioRepository.delete(userToDelete);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el usuario.", e);
        }
    }

    public UsuarioEntity findUserById(Integer idUsuario) {
        try {
            return usuarioRepository.findById(idUsuario)
                    .map(u -> {
                        try {
                            u.setCredencial(credentialRepository.findByUserId(u.getId())
                                    .orElseThrow(NoSuchElementException::new));
                            u.setCuentas(cuentaRepository.findAllByUserId(u.getId()));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        return u;
                    })
                    .orElseThrow(() -> new NoSuchElementException("El usuario no existe."));
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el usuario.", e);
        }
    }

    private boolean hasDeleteUpdatePermission(Permiso permiso, UsuarioEntity userToDelete) {
        return switch (permiso) {
            case ADMINISTRADOR -> true; // Puede eliminar a cualquier usuario
            case GESTOR -> userToDelete.getCredencial().getPermiso().equals(Permiso.CLIENTE); // Solo clientes
            case CLIENTE -> throw new NoAutorizadoException("No autorizado");
            default -> throw new SecurityException("Rol desconocido o sin permisos.");
        };
    }

    public Double getSaldoTotalPropio(UsuarioEntity user){
        return user.getCuentas().stream()
                .mapToDouble(CuentaEntity::getSaldo)
                .reduce(Double::sum)
                .orElse(0);
    }

    public Double getSaldoTotal(String dni, Permiso permiso){
        Optional<UsuarioEntity> foundUser = findByDNI(dni,permiso);

        if (foundUser.isEmpty()) throw new NoSuchElementException("Usuario inexistente");

        return foundUser.get().getCuentas().stream()
                .mapToDouble(CuentaEntity::getSaldo)
                .sum();
    }

    public void viewUserCountByPermiso(Permiso permiso) {
        List<UsuarioEntity> users = findAll(permiso);

        users.stream()
                .collect(Collectors.groupingBy(
                        u -> u.getCredencial().getPermiso(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> Map.entry(list.size(), list)
                        )
                ))
                .forEach((perm, entry) -> {
                    System.out.println(perm + " -> Cantidad: " + entry.getKey() + ", Usuarios: " + entry.getValue());
                });
    }

    public UsuarioEntity findByMaxSaldo(Permiso permiso){
        if(permiso != Permiso.ADMINISTRADOR) throw new NoAutorizadoException("No autorizado");

        List<UsuarioEntity> users = findAll(permiso);

        return users.stream()
                .max(Comparator.comparingDouble(this::getSaldoTotalPropio))
                .orElse(null);
    }

    public void listByMaxSaldo(Permiso permiso){
        if(permiso != Permiso.ADMINISTRADOR) throw new NoAutorizadoException("No autorizado");

        List<UsuarioEntity> users = findAll(permiso);

        users.stream()
                .sorted(Comparator.comparingDouble(this::getSaldoTotalPropio)
                        .reversed())
                .forEach(System.out::println);
    }

}
