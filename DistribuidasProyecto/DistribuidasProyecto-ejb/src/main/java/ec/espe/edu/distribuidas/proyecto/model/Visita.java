/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espe.edu.distribuidas.proyecto.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "visita")
@NamedQueries({
    @NamedQuery(name = "Visita.findAll", query = "SELECT v FROM Visita v")})
public class Visita implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "COD_VISITA", nullable = false, length = 8)
    private String codVisita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_VISITA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVisita;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_VISITA", precision = 6, scale = 2)
    private BigDecimal valorVisita;
    @OneToMany(mappedBy = "visita")
    private List<Consumo> consumoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "visita")
    private List<Factura> facturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "visita")
    private List<ConsumoActividad> consumoActividadList;
    @JoinColumn(name = "CEDULA", referencedColumnName = "CEDULA", nullable = false)
    @ManyToOne(optional = false)
    private Cliente cedula;
    @JoinColumn(name = "COD_TRANSPORTE", referencedColumnName = "COD_TRANSPORTE")
    @ManyToOne
    private Transporte codTransporte;
    @JoinColumn(name = "COD_USUARIO", referencedColumnName = "COD_USUARIO")
    @ManyToOne
    private Usuario codUsuario;
    @JoinColumn(name = "COD_ESTABLECIMIENTO", referencedColumnName = "COD_ESTABLECIMIENTO", nullable = false)
    @ManyToOne(optional = false)
    private Establecimiento codEstablecimiento;

    public Visita() {
    }

    public Visita(String codVisita) {
        this.codVisita = codVisita;
    }

    public Visita(String codVisita, Date fechaVisita) {
        this.codVisita = codVisita;
        this.fechaVisita = fechaVisita;
    }

    public String getCodVisita() {
        return codVisita;
    }

    public void setCodVisita(String codVisita) {
        this.codVisita = codVisita;
    }

    public Date getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public BigDecimal getValorVisita() {
        return valorVisita;
    }

    public void setValorVisita(BigDecimal valorVisita) {
        this.valorVisita = valorVisita;
    }

    public List<Consumo> getConsumoList() {
        return consumoList;
    }

    public void setConsumoList(List<Consumo> consumoList) {
        this.consumoList = consumoList;
    }

    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    public List<ConsumoActividad> getConsumoActividadList() {
        return consumoActividadList;
    }

    public void setConsumoActividadList(List<ConsumoActividad> consumoActividadList) {
        this.consumoActividadList = consumoActividadList;
    }

    public Cliente getCedula() {
        return cedula;
    }

    public void setCedula(Cliente cedula) {
        this.cedula = cedula;
    }

    public Transporte getCodTransporte() {
        return codTransporte;
    }

    public void setCodTransporte(Transporte codTransporte) {
        this.codTransporte = codTransporte;
    }

    public Usuario getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Usuario codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Establecimiento getCodEstablecimiento() {
        return codEstablecimiento;
    }

    public void setCodEstablecimiento(Establecimiento codEstablecimiento) {
        this.codEstablecimiento = codEstablecimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codVisita != null ? codVisita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visita)) {
            return false;
        }
        Visita other = (Visita) object;
        if ((this.codVisita == null && other.codVisita != null) || (this.codVisita != null && !this.codVisita.equals(other.codVisita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.espe.edu.distribuidas.proyecto.model.Visita[ codVisita=" + codVisita + " ]";
    }
    
}
