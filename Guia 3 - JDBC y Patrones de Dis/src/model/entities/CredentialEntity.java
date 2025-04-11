package model.entities;


import model.entities.enums.Permiso;

public class CredentialEntity {
    private Integer id;
    private Integer id_usuario;
    private String username;
    private String password;
    private Permiso permiso;

    public CredentialEntity(Integer id, Integer id_usuario, String username, String password, Permiso permiso) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.username = username;
        this.password = password;
        this.permiso = permiso;
    }

    public CredentialEntity(String username, String password, Permiso permiso) {
        this.username = username;
        this.password = password;
        this.permiso = permiso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "CredentialEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", permiso=" + permiso +
                '}';
    }
}
