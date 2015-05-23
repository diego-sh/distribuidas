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
public class DetallePK implements Serializable {

    @Column(name = "COD_DETALLE", nullable = false)
    private long codigoDetalle;
    @Column(name = "COD_FACTURA", nullable = false)
    private Integer codigoFactura;

    public DetallePK() {
    }

    public DetallePK(long codDetalle, int codFactura) {
        this.codigoDetalle = codDetalle;
        this.codigoFactura = codFactura;
    }

    public long getCodigoDetalle() {
        return codigoDetalle;
    }

    public void setCodigoDetalle(long codigoDetalle) {
        this.codigoDetalle = codigoDetalle;
    }

    public int getCodFactura() {
        return codigoFactura;
    }

    public void setCodFactura(int codFactura) {
        this.codigoFactura = codFactura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoDetalle;
        hash += (int) codigoFactura;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePK)) {
            return false;
        }
        DetallePK other = (DetallePK) object;
        if (this.codigoDetalle != other.codigoDetalle) {
            return false;
        }
        if (this.codigoFactura != other.codigoFactura) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetallePK{" + "codigoDetalle=" + codigoDetalle + ", codigoFactura=" + codigoFactura + '}';
    }

}
