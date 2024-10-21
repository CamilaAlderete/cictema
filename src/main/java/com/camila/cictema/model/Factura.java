package com.camila.cictema.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "facturas", schema = "public")
@SequenceGenerator(name="facturas_seq", sequenceName = "facturas_id_seq", allocationSize = 1)
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facturas_seq")
    private Integer id;

    @Column(name="fecha")
    private LocalDateTime fecha;

    @Column(name="total")
    private Integer total;

    @Column(name="ganancia_empleado")
    private Float gananciaEmpleado;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Usuario empleado;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Persona cliente;

    // mappedBy permite que una entidad gestione una relación sin tener explícitamente una clave externa en su tabla.
    // mappedBy = "factura" indica que en la entidad DetalleFactura existe un atributo llamado factura que establece la relación con la entidad Factura.
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DetalleFactura> detalles = new ArrayList<>();

    //El operador :: es una referencia de método en Java.
    // Permite referirse a un método específico de una clase o instancia de una manera concisa y directa.
    public void actualizarTotal() {
        this.total = detalles.stream().mapToInt(DetalleFactura::getSubtotal).sum();
        //this.total = detalles.stream().mapToInt(detalle -> detalle.getSubtotal()).sum();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Float getGananciaEmpleado() {
        return gananciaEmpleado;
    }

    public void setGananciaEmpleado(Float gananciaEmpleado) {
        this.gananciaEmpleado = gananciaEmpleado;
    }

    public Usuario getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Usuario empleado) {
        this.empleado = empleado;
    }

    public Persona getCliente() {
        return cliente;
    }

    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
    }

    public void addDetalle(DetalleFactura detalle) {
        detalles.add(detalle);
        detalle.setFactura(this);
    }

    public void removeDetalle(DetalleFactura detalle) {
        detalles.remove(detalle);
        detalle.setFactura(null);
    }
}
