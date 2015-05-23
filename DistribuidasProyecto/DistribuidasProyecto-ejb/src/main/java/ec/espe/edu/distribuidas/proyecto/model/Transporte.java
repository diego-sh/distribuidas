/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espe.edu.distribuidas.proyecto.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "transporte")
@NamedQueries({
    @NamedQuery(name = "Transporte.findAll", query = "SELECT t FROM Transporte t")})
public class Transporte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "COD_TRANSPORTE", nullable = false, length = 4)
    private String codTransporte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CHOFER", nullable = false, length = 100)
    private String chofer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "PLACA", nullable = false, length = 7)
    private String placa;
    @Size(max = 200)
    @Column(name = "DESC_TRANSPORTE", length = 200)
    private String descTransporte;
    @OneToMany(mappedBy = "codTransporte")
    private List<Visita> visitaList;

    public Transporte() {
    }

    public Transporte(String codTransporte) {
        this.codTransporte = codTransporte;
    }

    public Transporte(String codTransporte, String chofer, String placa) {
        this.codTransporte = codTransporte;
        this.chofer = chofer;
        this.placa = placa;
    }

    public String getCodTransporte() {
        return codTransporte;
    }

    public void setCodTransporte(String codTransporte) {
        this.codTransporte = codTransporte;
    }

    public String getChofer() {
        return chofer;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getDescTransporte() {
        return descTransporte;
    }

    public void setDescTransporte(String descTransporte) {
        this.descTransporte = descTransporte;
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
        hash += (codTransporte != null ? codTransporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transporte)) {
            return false;
        }
        Transporte other = (Transporte) object;
        if ((this.codTransporte == null && other.codTransporte != null) || (this.codTransporte != null && !this.codTransporte.equals(other.codTransporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.espe.edu.distribuidas.proyecto.model.Transporte[ codTransporte=" + codTransporte + " ]";
    }
    
}
