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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "establecimiento")
@NamedQueries({
    @NamedQuery(name = "Establecimiento.findAll", query = "SELECT e FROM Establecimiento e")})
public class Establecimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "COD_ESTABLECIMIENTO", nullable = false, length = 10)
    private String codEstablecimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_ESTABLECIMIENTO", nullable = false, length = 50)
    private String nombreEstablecimiento;
    @Size(max = 100)
    @Column(name = "DESC_ESTABLECIMIENTO", length = 100)
    private String descEstablecimiento;
    @JoinColumn(name = "COD_UBICACION", referencedColumnName = "COD_UBICACION", nullable = false)
    @ManyToOne(optional = false)
    private Ubicacion codUbicacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codEstablecimiento")
    private List<Visita> visitaList;

    public Establecimiento() {
    }

    public Establecimiento(String codEstablecimiento) {
        this.codEstablecimiento = codEstablecimiento;
    }

    public Establecimiento(String codEstablecimiento, String nombreEstablecimiento) {
        this.codEstablecimiento = codEstablecimiento;
        this.nombreEstablecimiento = nombreEstablecimiento;
    }

    public String getCodEstablecimiento() {
        return codEstablecimiento;
    }

    public void setCodEstablecimiento(String codEstablecimiento) {
        this.codEstablecimiento = codEstablecimiento;
    }

    public String getNombreEstablecimiento() {
        return nombreEstablecimiento;
    }

    public void setNombreEstablecimiento(String nombreEstablecimiento) {
        this.nombreEstablecimiento = nombreEstablecimiento;
    }

    public String getDescEstablecimiento() {
        return descEstablecimiento;
    }

    public void setDescEstablecimiento(String descEstablecimiento) {
        this.descEstablecimiento = descEstablecimiento;
    }

    public Ubicacion getCodUbicacion() {
        return codUbicacion;
    }

    public void setCodUbicacion(Ubicacion codUbicacion) {
        this.codUbicacion = codUbicacion;
    }

    public List<Visita> getVisitaList() {
        return visitaList;
    }

    public void setVisitaList(List<Visita> visitaList) {
        this.visitaList = visitaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEstablecimiento != null ? codEstablecimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Establecimiento)) {
            return false;
        }
        Establecimiento other = (Establecimiento) object;
        if ((this.codEstablecimiento == null && other.codEstablecimiento != null) || (this.codEstablecimiento != null && !this.codEstablecimiento.equals(other.codEstablecimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.espe.edu.distribuidas.proyecto.model.Establecimiento[ codEstablecimiento=" + codEstablecimiento + " ]";
    }
    
}
