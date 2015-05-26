/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "establecimiento")

public class Establecimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "COD_ESTABLECIMIENTO", nullable = false, length = 4)
    private String codigo;
    @Column(name = "NOMBRE_ESTABLECIMIENTO", nullable = false, length = 50)
    private String nombre;
    @Column(name = "DESC_ESTABLECIMIENTO", length = 100)
    private String descripcion;
    @Column(name = "COD_UBICACION")
    private String codigoUbicacion;

    @JoinColumn(name = "COD_UBICACION", referencedColumnName = "COD_UBICACION", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ubicacion ubicacion;

    public Establecimiento() {
    }

    public Establecimiento(String codEstablecimiento) {
        this.codigo = codEstablecimiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCodigoUbicacion() {
        return codigoUbicacion;
    }

    public void setCodigoUbicacion(String codigoUbicacion) {
        this.codigoUbicacion = codigoUbicacion;
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
        if (!(object instanceof Establecimiento)) {
            return false;
        }
        Establecimiento other = (Establecimiento) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Establecimiento{" + "codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", codigoUbicacion=" + codigoUbicacion + '}';
    }

}
