/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espe.edu.distribuidas.proyecto.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "consumo_actividad")
@NamedQueries({
    @NamedQuery(name = "ConsumoActividad.findAll", query = "SELECT c FROM ConsumoActividad c")})
public class ConsumoActividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsumoActividadPK consumoActividadPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR", nullable = false, precision = 5, scale = 2)
    private BigDecimal valor;
    @Size(max = 200)
    @Column(name = "DETALLE", length = 200)
    private String detalle;
    @JoinColumn(name = "COD_ACTIVIDAD", referencedColumnName = "COD_ACTIVIDAD", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Actividad actividad;
    @JoinColumn(name = "COD_VISITA", referencedColumnName = "COD_VISITA", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Visita visita;

    public ConsumoActividad() {
    }

    public ConsumoActividad(ConsumoActividadPK consumoActividadPK) {
        this.consumoActividadPK = consumoActividadPK;
    }

    public ConsumoActividad(ConsumoActividadPK consumoActividadPK, BigDecimal valor) {
        this.consumoActividadPK = consumoActividadPK;
        this.valor = valor;
    }

    public ConsumoActividad(String codActividad, String codVisita) {
        this.consumoActividadPK = new ConsumoActividadPK(codActividad, codVisita);
    }

    public ConsumoActividadPK getConsumoActividadPK() {
        return consumoActividadPK;
    }

    public void setConsumoActividadPK(ConsumoActividadPK consumoActividadPK) {
        this.consumoActividadPK = consumoActividadPK;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consumoActividadPK != null ? consumoActividadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsumoActividad)) {
            return false;
        }
        ConsumoActividad other = (ConsumoActividad) object;
        if ((this.consumoActividadPK == null && other.consumoActividadPK != null) || (this.consumoActividadPK != null && !this.consumoActividadPK.equals(other.consumoActividadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.espe.edu.distribuidas.proyecto.model.ConsumoActividad[ consumoActividadPK=" + consumoActividadPK + " ]";
    }

}
