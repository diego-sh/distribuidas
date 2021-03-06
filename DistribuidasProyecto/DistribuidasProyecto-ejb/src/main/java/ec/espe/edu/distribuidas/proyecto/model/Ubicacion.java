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
@Table(name = "ubicacion")

public class Ubicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "COD_UBICACION", nullable = false, length = 4)
    private String codigo;
    
    @Column(name = "NUMERO_BLOQUE", nullable = false)
    private Integer numeroBloque;    
    
    @Column(name = "REFERENCIA", length = 100)
    private String referencia;
    
    @Column(name = "NOMBRE_UBICACION", nullable = false, length = 50)
    private String nombre;
    
    

    public Ubicacion() {
    }

    public Ubicacion(String codUbicacion) {
        this.codigo = codUbicacion;
    }

    public Ubicacion(String codUbicacion, int numeroBloque, String referencia) {
        this.codigo = codUbicacion;
        this.numeroBloque = numeroBloque;
        this.referencia = referencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getNumeroBloque() {
        return numeroBloque;
    }

    public void setNumeroBloque(Integer numeroBloque) {
        this.numeroBloque = numeroBloque;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if (!(object instanceof Ubicacion)) {
            return false;
        }
        Ubicacion other = (Ubicacion) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ubicacion=" + codigo ;
    }
    
}
