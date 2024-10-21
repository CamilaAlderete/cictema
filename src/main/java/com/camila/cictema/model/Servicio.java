package com.camila.cictema.model;

import jakarta.persistence.*;

@Entity
@Table(name = "servicios", schema = "public")
@SequenceGenerator(name = "servicios_seq", sequenceName = "cictema.public.servicios_id_seq", allocationSize = 1)
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servicios_seq")
    @Column(name = "id", nullable = false )
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 30)
    private String descripcion;

    @Column(name = "costo", nullable = false, columnDefinition = "int default 0")
    private Integer costo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }
}
