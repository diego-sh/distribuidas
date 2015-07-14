/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.model;

import java.io.Serializable;
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
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_USUARIO", nullable = false)
    private Integer codigo;

    @Column(name = "NOMBRE_USUARIO", nullable = false, length = 50)
    private String nombre;

    @Column(name = "CLAVE_USUARIO", nullable = false, length = 128)
    private String clave;

    @Column(name = "ESTADO_USUARIO", nullable = false, length = 3)
    private String estado;

    @Column(name = "NICKNAME", nullable = false, length = 50)
    private String nickname;

    @Column(name = "TIPO_USUARIO", nullable = false, length = 3)
    private String tipo;

    @Column(name = "COD_ESTABLECIMIENTO", nullable = false)
    private String codigoEstablecimiento;

    @JoinColumn(name = "COD_ESTABLECIMIENTO", referencedColumnName = "COD_ESTABLECIMIENTO", insertable = false, updatable = false)
    @ManyToOne
    private Establecimiento establecimiento;

    public Usuario() {
    }

    public Usuario(Integer codUsuario) {
        this.codigo = codUsuario;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstado() {
        return estado;
    }

    public String getEstadoCompleto() {
        String estadoTMP = this.estado;
        if (estadoTMP.equals("PRI")) {
            estadoTMP = "Nuevo";
        }
        if (estadoTMP.equals("ACT")) {
            estadoTMP = "Activo";
        }
        if (estadoTMP.equals("INA")) {
            estadoTMP = "Inactivo";
        }
        return estadoTMP;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getCodigoEstablecimiento() {
        return codigoEstablecimiento;
    }

    public void setCodigoEstablecimiento(String codigoEstablecimiento) {
        this.codigoEstablecimiento = codigoEstablecimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTipoCompleto() {
        String tipoUusario = this.tipo;

        if (tipoUusario.equals("EST")) {
            tipoUusario = "Establecimiento";
        }
        if (tipoUusario.equals("REC")) {
            tipoUusario = "Recepción";
        }

        return tipoUusario;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario=" + nombre;
    }

}
