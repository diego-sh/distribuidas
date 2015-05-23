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
import javax.persistence.Table;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "transporte")

public class Transporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "COD_TRANSPORTE", nullable = false, length = 4)
    private String codigo;
    @Column(name = "CHOFER", nullable = false, length = 100)
    private String nombreChofer;
    @Column(name = "PLACA", nullable = false, length = 7)
    private String placa;
    @Column(name = "DESC_TRANSPORTE", length = 200)
    private String descripcion;

    public Transporte() {
    }

    public Transporte(String codTransporte) {
        this.codigo = codTransporte;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreChofer() {
        return nombreChofer;
    }

    public void setNombreChofer(String nombreChofer) {
        this.nombreChofer = nombreChofer;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof Transporte)) {
            return false;
        }
        Transporte other = (Transporte) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transporte{" + "codigo=" + codigo + ", nombreChofer=" + nombreChofer + ", placa=" + placa + ", descripcion=" + descripcion + '}';
    }

}
