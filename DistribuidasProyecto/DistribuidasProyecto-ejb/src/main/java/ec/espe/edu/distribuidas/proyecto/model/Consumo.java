/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espe.edu.distribuidas.proyecto.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "consumo")
public class Consumo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id   
    @Column(name = "COD_CONSUMO", nullable = false, length = 5)
    private String codigo;    
    @Column(name = "CANTIDAD_PRODUCTO", nullable = false)
    private int cantidad;    
    @Column(name = "DETALLE", length = 100)
    private String detalle;
    
    @JoinColumn(name = "COD_VISITA", referencedColumnName = "COD_VISITA")
    @ManyToOne
    private Visita codVisita;
    @JoinColumn(name = "COD_PRODUCTO", referencedColumnName = "COD_PRODUCTO")
    @ManyToOne
    private Producto codProducto;

    public Consumo() {
    }

    public Consumo(String codConsumo) {
        this.codigo = codConsumo;
    }

    public Consumo(String codConsumo, int cantidadProducto) {
        this.codigo = codConsumo;
        this.cantidad = cantidadProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Visita getCodVisita() {
        return codVisita;
    }

    public void setCodVisita(Visita codVisita) {
        this.codVisita = codVisita;
    }

    public Producto getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(Producto codProducto) {
        this.codProducto = codProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consumo)) {
            return false;
        }
        Consumo other = (Consumo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.espe.edu.distribuidas.proyecto.model.Consumo[ codConsumo=" + codigo + " ]";
    }
    
}
