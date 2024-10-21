package com.camila.cictema.model;
import jakarta.persistence.*;

/*
    - Clase base que contiene datos personales básicos
    - De esta clase saldra la clase hija Usuario
    - Existen diferentes estrategias de herencia en spring pero se empleará TABLE_PER_CLASS
    - Con InheritanceType.TABLE_PER_CLASS se especifica que cada subclase tendrá su propia tabla en la base de datos.
 */

@Entity
@Table(name = "clientes", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"telefono"})
})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "clientes_seq", sequenceName = "clientes_id_seq", allocationSize = 1)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientes_seq")
    @Column(name = "id", nullable = false )
    private Integer id;

    @Column(name = "nombres", nullable = false, length = 30)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;

    @Column(name = "telefono", length = 10)
    private String telefono;

    @Column(name = "deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean deleted = false;

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
}
