package com.camila.cictema.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "usuarios", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "username"})
})
@SequenceGenerator(name = "usuario_seq", sequenceName = "usuarios_id_seq", allocationSize = 1)
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @Column(name = "id", nullable = false )
    private Integer id;

    @Column(name = "nombres", nullable = false, length = 30)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;

    @Column(name = "telefono", length = 10)
    private String telefono;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false, length = 10)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "porcentaje_ganancia", nullable = false, columnDefinition = "real default 0.0")
    private Float porcentajeGanancia;

    @Column(name = "fecha_creacion", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean deleted = false;


    /*
       * PrePersist garantiza que el valor esté presente en la entidad de Java antes de ser persistida.
       * En caso de no definir esto, se confía completamente en el valor por defecto de la base de datos, lo que significa que el valor se asignará cuando la entidad se inserte en la base de datos. La entidad en memoria no tendrá este valor hasta que se recargue.
       * esto se ejecuta solo cuando la entidad es nueva
     */
    @PrePersist
    public void prePersist() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Float getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(Float porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

}
