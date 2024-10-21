package com.camila.cictema.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_facturas")
@SequenceGenerator(name = "detalle_facturas_seq", sequenceName = "detalle_facturas_id_seq", allocationSize = 1)
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_facturas_seq")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_factura")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;

    @Column(name="costo")
    private Integer costo;

    @Column(name="cantidad")

    private Integer cantidad;

    @Column(name="subtotal")

    private Integer subtotal;

    //se ejecuta antes de que la entidad se inserte o actualice en la base de datos
    @PrePersist
    @PreUpdate
    public void calcularSubtotal() {
        this.subtotal = this.cantidad * this.costo;
    }

    // Este método actualiza el total en la entidad Factura.
    // Llama al método actualizarTotalFactura después de que una entidad DetalleFactura se inserte, actualice o elimine.
    @PostPersist
    @PostUpdate
    @PostRemove
    public void actualizarTotalFactura() {
        if (factura != null) {
            factura.actualizarTotal();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }
}
