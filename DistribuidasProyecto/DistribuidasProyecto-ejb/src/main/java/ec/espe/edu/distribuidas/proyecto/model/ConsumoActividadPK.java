/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Diego
 */
@Embeddable
public class ConsumoActividadPK implements Serializable {

    @Column(name = "COD_ACTIVIDAD", nullable = false, length = 4)
    private String codigoActividad;
    @Column(name = "COD_VISITA", nullable = false, length = 8)
    private String codigoVisita;

    public ConsumoActividadPK() {
    }

    public ConsumoActividadPK(String codActividad, String codVisita) {
        this.codigoActividad = codActividad;
        this.codigoVisita = codVisita;
    }

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public String getCodigoVisita() {
        return codigoVisita;
    }

    public void setCodigoVisita(String codigoVisita) {
        this.codigoVisita = codigoVisita;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoActividad != null ? codigoActividad.hashCode() : 0);
        hash += (codigoVisita != null ? codigoVisita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsumoActividadPK)) {
            return false;
        }
        ConsumoActividadPK other = (ConsumoActividadPK) object;
        if ((this.codigoActividad == null && other.codigoActividad != null) || (this.codigoActividad != null && !this.codigoActividad.equals(other.codigoActividad))) {
            return false;
        }
        if ((this.codigoVisita == null && other.codigoVisita != null) || (this.codigoVisita != null && !this.codigoVisita.equals(other.codigoVisita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConsumoActividadPK{" + "codigoActividad=" + codigoActividad + ", codigoVisita=" + codigoVisita + '}';
    }

}
