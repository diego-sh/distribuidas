/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "visita")

public class Visita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "COD_VISITA", nullable = false, length = 8)
    private String codigo;
    @Column(name = "FECHA_VISITA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "VALOR_VISITA", precision = 6, scale = 2)
    private BigDecimal valor;
    @Column(name = "ESTADO_FACTURA")
    private boolean estadoFactura;
    @Column(name = "CEDULA")
    private String cedula;
    @Column(name = "COD_TRANSPORTE")
    private String codigoTransporte;
    @Column(name = "COD_USUARIO")
    private String codigoUsuario;
    @Column(name = "COD_ESTABLECIMIENTO")
    private String codidoEstablecimiento;

    @JoinColumn(name = "CEDULA", referencedColumnName = "CEDULA", nullable = false)
    @ManyToOne
    private Cliente cliente;
    @JoinColumn(name = "COD_TRANSPORTE", referencedColumnName = "COD_TRANSPORTE")
    @ManyToOne
    private Transporte transporte;
    @JoinColumn(name = "COD_USUARIO", referencedColumnName = "COD_USUARIO")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "COD_ESTABLECIMIENTO", referencedColumnName = "COD_ESTABLECIMIENTO", nullable = false)
    @ManyToOne
    private Establecimiento establecimiento;

    public Visita() {
    }

    public Visita(String codVisita) {
        this.codigo = codVisita;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean isEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(boolean estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCodigoTransporte() {
        return codigoTransporte;
    }

    public void setCodigoTransporte(String codigoTransporte) {
        this.codigoTransporte = codigoTransporte;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getCodidoEstablecimiento() {
        return codidoEstablecimiento;
    }

    public void setCodidoEstablecimiento(String codidoEstablecimiento) {
        this.codidoEstablecimiento = codidoEstablecimiento;
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
        if (!(object instanceof Visita)) {
            return false;
        }
        Visita other = (Visita) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Visita{" + "codigo=" + codigo + ", fecha=" + fecha + ", valor=" + valor + ", estadoFactura=" + estadoFactura + ", cedula=" + cedula + ", codigoTransporte=" + codigoTransporte + ", codigoUsuario=" + codigoUsuario + ", codidoEstablecimiento=" + codidoEstablecimiento + '}';
    }

}
