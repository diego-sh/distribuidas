/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto;

import ec.espe.edu.distribuidas.proyecto.model.Establecimiento;
import ec.espe.edu.distribuidas.proyecto.model.Ubicacion;
import ec.espe.edu.distribuidas.proyecto.service.EstablecimientoService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Diego
 */
@ManagedBean
@ViewScoped
public class EstablecimientoBean {

    private List<Establecimiento> establecimientos;
    private Establecimiento establecimiento;
    private Establecimiento establecimientoSeleccionado;
    private List<Ubicacion> ubicaciones;
    private Ubicacion ubicacion;
    private Ubicacion ubicacionSeleccionada;

    private String tituloFormulario;
    private boolean enNuevo;
    private boolean enModificar;
    private boolean enDetalles;
    private boolean enEliminar;

    private boolean enNuevoU;
    private boolean enModificarU;

    @EJB
    private EstablecimientoService establecimientoService;

    @PostConstruct
    public void listarEstablecimientos() {

        this.establecimientos = this.establecimientoService.obtenerAllEstablecimiento();
        this.ubicaciones = this.establecimientoService.obtenerAllUbicacion();

    }

    public void nuevoEstablecimiento() {
        this.establecimiento = new Establecimiento();
        this.enNuevo = true;
        this.tituloFormulario = "Creación de Establecimiento";
    }

    public void modificarEstablecimiento() {
        if (this.establecimientoSeleccionado != null) {
            this.tituloFormulario = "Modificación de Establecimiento";
            this.copiarEstablecimientoSeleccionado();
            this.enModificar = true;
        }
    }

    public void detallesEstablecimiento() {
        if (this.establecimientoSeleccionado != null) {
            this.tituloFormulario = "Detalle Establecimiento";
            this.copiarEstablecimientoSeleccionado();
            this.enDetalles = true;
        }
    }

    public void nuevaUbicacion() {
        this.ubicacion = new Ubicacion();
        this.enNuevoU = true;
        this.tituloFormulario = "Crear Ubicación";
    }

    public void modificarUbicacion() {
        if (this.ubicacionSeleccionada != null) {
            this.tituloFormulario = "Modificar Ubicación";
            this.copiarUbicacionSeleccionada();
            this.enModificarU = true;
        }
    }

    public void guardarEstablecimiento() {
        if (this.enNuevo) {
            try {
                this.ubicacionSeleccionada = new Ubicacion();
                this.ubicacionSeleccionada.setCodigo(this.establecimiento.getCodigoUbicacion());
                this.establecimiento.setUbicacion(this.ubicacionSeleccionada);
                this.establecimientoService.crearEstablecimiento(this.establecimiento);
                this.enNuevo = false;
                this.establecimientos = this.establecimientoService.obtenerAllEstablecimiento();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Establecimiento Creado", " Se ha creado el:" + this.establecimiento);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear Establecimiento.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
        if (this.enModificar) {
            try {
                this.ubicacionSeleccionada = new Ubicacion();
                this.ubicacionSeleccionada.setCodigo(this.establecimiento.getCodigoUbicacion());
                this.establecimiento.setUbicacion(this.ubicacionSeleccionada);
                this.establecimientoService.actualizarEstablecimiento(this.establecimiento);
                this.enModificar = false;
                this.establecimientos = this.establecimientoService.obtenerAllEstablecimiento();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Establecimiento Modificado", " Se ha modificado el:" + this.establecimiento);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al modificar Establecimiento.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }

        if (this.enEliminar) {
            try {
                this.copiarEstablecimientoSeleccionado();
                this.establecimientoService.eliminarEstablecimiento(this.establecimiento.getCodigo());
                this.enEliminar=false;
                this.establecimientos = this.establecimientoService.obtenerAllEstablecimiento();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Establecimiento Eliminado.", "Se ha eliminado el establecimiento " + this.establecimiento);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar establecimiento. ", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }

    }

    public void cancelar() {
        this.enModificar = false;
        this.enNuevo = false;
        this.enEliminar = false;
        this.enDetalles = false;

    }

    public void cancelarUbicacion() {
        this.enNuevoU = false;
        this.enModificarU = false;
    }

    public void guardarUbicacion() {
        if (this.enNuevoU) {
            try {
                this.establecimientoService.crearUbicacion(this.ubicacion);
                this.ubicaciones = this.establecimientoService.obtenerAllUbicacion();
                this.enNuevoU = false;
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ubicación Creada", " Se ha creado la    :" + this.ubicacion);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear Ubicacion.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
        if (this.enModificarU) {
            try {
                this.establecimientoService.actualizarUbicacion(this.ubicacion);
                this.enModificarU = false;
                this.ubicaciones = this.establecimientoService.obtenerAllUbicacion();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ubicación Modificada", " Se ha modificado la :" + this.ubicacion);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al modificar Ubicación.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }

    }

    public void eliminarEstablecimiento() {
        if (this.establecimientoSeleccionado != null) {
            this.tituloFormulario = "Elminar Establecimiento";
            this.copiarEstablecimientoSeleccionado();
            this.enEliminar = true;
        }
    }

    public boolean isEnNuevoU() {
        return enNuevoU;
    }

    public void setEnNuevoU(boolean enNuevoU) {
        this.enNuevoU = enNuevoU;
    }

    public boolean isEnModificarU() {
        return enModificarU;
    }

    public void setEnModificarU(boolean enModificarU) {
        this.enModificarU = enModificarU;
    }

    public List<Establecimiento> getEstablecimientos() {
        return establecimientos;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public Establecimiento getEstablecimientoSeleccionado() {
        return establecimientoSeleccionado;
    }

    public void setEstablecimientoSeleccionado(Establecimiento establecimientoSeleccionado) {
        this.establecimientoSeleccionado = establecimientoSeleccionado;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Ubicacion getUbicacionSeleccionada() {
        return ubicacionSeleccionada;
    }

    public void setUbicacionSeleccionada(Ubicacion ubicacionSeleccionada) {
        this.ubicacionSeleccionada = ubicacionSeleccionada;
    }

    public EstablecimientoService getEstablecimientoService() {
        return establecimientoService;
    }

    public void setEstablecimientoService(EstablecimientoService establecimientoService) {
        this.establecimientoService = establecimientoService;
    }

    public String getTituloFormulario() {
        return tituloFormulario;
    }

    public void setTituloFormulario(String tituloFormulario) {
        this.tituloFormulario = tituloFormulario;
    }

    public boolean isEnNuevo() {
        return enNuevo;
    }

    public void setEnNuevo(boolean enNuevo) {
        this.enNuevo = enNuevo;
    }

    public boolean isEnModificar() {
        return enModificar;
    }

    public void setEnModificar(boolean enModificar) {
        this.enModificar = enModificar;
    }

    public boolean isEnDetalles() {
        return enDetalles;
    }

    public void setEnDetalles(boolean enDetalles) {
        this.enDetalles = enDetalles;
    }

    public boolean isEnEliminar() {
        return enEliminar;
    }

    public void setEnEliminar(boolean enEliminar) {
        this.enEliminar = enEliminar;
    }

    private void copiarEstablecimientoSeleccionado() {
        this.establecimiento = new Establecimiento();
        this.establecimiento.setCodigo(this.establecimientoSeleccionado.getCodigo());
        this.establecimiento.setNombre(this.establecimientoSeleccionado.getNombre());
        this.establecimiento.setCodigoUbicacion(this.establecimientoSeleccionado.getCodigoUbicacion());
        this.establecimiento.setDescripcion(this.establecimientoSeleccionado.getDescripcion());
    }

    private void copiarUbicacionSeleccionada() {
        this.ubicacion = new Ubicacion();
        this.ubicacion.setCodigo(this.ubicacionSeleccionada.getCodigo());
        this.ubicacion.setNombre(this.ubicacionSeleccionada.getNombre());
        this.ubicacion.setNumeroBloque(this.ubicacionSeleccionada.getNumeroBloque());
        this.ubicacion.setReferencia(this.ubicacionSeleccionada.getReferencia());

    }

}
