/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espe.edu.distribuidas.proyecto.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "consumo_actividad")
public class ConsumoActividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "COD_CONSUMOACTIVIDAD", nullable = false)
    private Integer codigo;
    
    @Column(name = "VALOR", nullable = false, precision = 3, scale = 2)
    private BigDecimal valor;
    
    @Column(name = "DETALLE", nullable = false, length = 200)
    private String detalle;
    
    @Column(name = "COD_ACTIVIDAD", nullable = false)
    private String codigoActividad;
    
    @Column(name = "COD_VISITA", nullable = false)
    private Integer codigoVisita;
    
    @JoinColumn(name = "COD_ACTIVIDAD", referencedColumnName = "COD_ACTIVIDAD", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private Actividad actividad;
    @JoinColumn(name = "COD_VISITA", referencedColumnName = "COD_VISITA", insertable = false, updatable = false)
    @ManyToOne
    private Visita visita;

    public ConsumoActividad() {
    }

    public ConsumoActividad(Integer codConsumoactividad) {
        this.codigo = codConsumoactividad;
    }

    public ConsumoActividad(Integer codConsumoactividad, BigDecimal valor, String detalle) {
        this.codigo = codConsumoactividad;
        this.valor = valor;
        this.detalle = detalle;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public String getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public Integer getCodigoVisita() {
        return codigoVisita;
    }

    public void setCodigoVisita(Integer codigoVisita) {
        this.codigoVisita = codigoVisita;
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
        if (!(object instanceof ConsumoActividad)) {
            return false;
        }
        ConsumoActividad other = (ConsumoActividad) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Consumoactividad=" + codigo;
    }
    
}
