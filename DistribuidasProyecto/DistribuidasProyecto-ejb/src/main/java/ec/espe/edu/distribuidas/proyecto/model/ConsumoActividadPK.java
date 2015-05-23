/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espe.edu.distribuidas.proyecto.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Diego
 */
@Embeddable
public class ConsumoActividadPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "COD_ACTIVIDAD", nullable = false, length = 4)
    private String codActividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "COD_VISITA", nullable = false, length = 8)
    private String codVisita;

    public ConsumoActividadPK() {
    }

    public ConsumoActividadPK(String codActividad, String codVisita) {
        this.codActividad = codActividad;
        this.codVisita = codVisita;
    }

    public String getCodActividad() {
        return codActividad;
    }

    public void setCodActividad(String codActividad) {
        this.codActividad = codActividad;
    }

    public String getCodVisita() {
        return codVisita;
    }

    public void setCodVisita(String codVisita) {
        this.codVisita = codVisita;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codActividad != null ? codActividad.hashCode() : 0);
        hash += (codVisita != null ? codVisita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsumoActividadPK)) {
            return false;
        }
        ConsumoActividadPK other = (ConsumoActividadPK) object;
        if ((this.codActividad == null && other.codActividad != null) || (this.codActividad != null && !this.codActividad.equals(other.codActividad))) {
            return false;
        }
        if ((this.codVisita == null && other.codVisita != null) || (this.codVisita != null && !this.codVisita.equals(other.codVisita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.espe.edu.distribuidas.proyecto.model.ConsumoActividadPK[ codActividad=" + codActividad + ", codVisita=" + codVisita + " ]";
    }
    
}
