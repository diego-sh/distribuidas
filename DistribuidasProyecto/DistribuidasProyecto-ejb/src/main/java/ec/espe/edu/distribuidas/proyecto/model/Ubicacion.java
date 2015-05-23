/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espe.edu.distribuidas.proyecto.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "ubicacion")
@NamedQueries({
    @NamedQuery(name = "Ubicacion.findAll", query = "SELECT u FROM Ubicacion u")})
public class Ubicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "COD_UBICACION", nullable = false, length = 6)
    private String codUbicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_BLOQUE", nullable = false)
    private int numeroBloque;
    @Size(max = 100)
    @Column(name = "REFERENCIA", length = 100)
    private String referencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codUbicacion")
    private List<Establecimiento> establecimientoList;

    public Ubicacion() {
    }

    public Ubicacion(String codUbicacion) {
        this.codUbicacion = codUbicacion;
    }

    public Ubicacion(String codUbicacion, int numeroBloque) {
        this.codUbicacion = codUbicacion;
        this.numeroBloque = numeroBloque;
    }

    public String getCodUbicacion() {
        return codUbicacion;
    }

    public void setCodUbicacion(String codUbicacion) {
        this.codUbicacion = codUbicacion;
    }

    public int getNumeroBloque() {
        return numeroBloque;
    }

    public void setNumeroBloque(int numeroBloque) {
        this.numeroBloque = numeroBloque;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public List<Establecimiento> getEstablecimientoList() {
        return establecimientoList;
    }

    public void setEstablecimientoList(List<Establecimiento> establecimientoList) {
        this.establecimientoList = establecimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codUbicacion != null ? codUbicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubicacion)) {
            return false;
        }
        Ubicacion other = (Ubicacion) object;
        if ((this.codUbicacion == null && other.codUbicacion != null) || (this.codUbicacion != null && !this.codUbicacion.equals(other.codUbicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.espe.edu.distribuidas.proyecto.model.Ubicacion[ codUbicacion=" + codUbicacion + " ]";
    }
    
}
