/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto;

import ec.espe.edu.distribuidas.proyecto.model.Establecimiento;
import ec.espe.edu.distribuidas.proyecto.model.Usuario;
import ec.espe.edu.distribuidas.proyecto.service.EstablecimientoService;
import ec.espe.edu.distribuidas.proyecto.service.UsuarioService;
import ec.espe.edu.distribuidas.proyecto.util.Valores;
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
public class UsuarioBean {

    private List<Usuario> usuarios;
    private Usuario usuario;
    private Usuario usuarioSeleccionado;
    private String tituloFormulario;
    private boolean enNuevo;
    private boolean enModificar;
    private boolean enCambiarEstado;
    private boolean enDetalles;
    private boolean enModificarClave;
    private List<Establecimiento> establecimientos;
    private Establecimiento establecimientoSeleccionado;

    private String tipoUsuario;

    @EJB
    private UsuarioService usuarioService;
    @EJB
    private EstablecimientoService establecimientoService;

    @PostConstruct
    public void listarUsuarios() {
        this.usuarios = this.usuarioService.obtnerAllUsuarios();
        this.establecimientos = this.establecimientoService.obtenerAllEstablecimiento();
    }

    public void nuevoUsuario() {
        this.usuario = new Usuario();
        this.enNuevo = true;
        this.tituloFormulario = "Creacion de Usuario";
    }

    public void modificarUsuario() {
        if (this.usuarioSeleccionado != null) {
            this.tituloFormulario = "Modificación de Usuario";
            this.copiarUsuarioSeleccionado();
            this.enModificar = true;
        }
    }

    public void guardarUsuario() {
        if (this.enNuevo) {
            try {
                this.validarTipoUsuario();
                this.usuarioService.crearUsuario(this.usuario);
                this.usuarios = this.usuarioService.obtnerAllUsuarios();
                this.enNuevo = false;
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Creado.", "Se ha creado el: " + this.usuario);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", " Usuario no creado: " + e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }

        if (this.enModificar) {
            try {
                this.validarTipoUsuario();
                this.usuarioService.actualizarUsuario(this.usuario);
                this.usuarios = this.usuarioService.obtnerAllUsuarios();
                this.enModificar = false;
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Modificado.", "Se ha modificado el: " + this.usuario);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", " Usuario no modificado: " + e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public void cancelar() {
        this.enNuevo = false;
        this.enModificar = false;
        this.enDetalles = false;
        this.enModificarClave=false;

    }

    public void cambiarEstadoUsuario() {
        if (this.usuarioSeleccionado != null) {
            try {
                if (this.usuarioSeleccionado.getEstado().equals(Valores.ESTADO_INHABILITADO)) {
                    this.usuarioService.habilitarUsuario(this.usuarioSeleccionado.getCodigo());
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Activado Exitosamente.", "Se ha modificado el Usuario ");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else if (this.usuarioSeleccionado.getEstado().equals(Valores.ESTADO_HABILITADO)) {
                    this.usuarioService.inhabilitarUsuario(this.usuarioSeleccionado.getCodigo());
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Desactivado Exitosamente.", "Se ha modificado el Usuario ");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "El Usuario debe primero cambiar su clave para ser activado o desactivado");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                this.usuarios = this.usuarioService.obtnerAllUsuarios();
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al modificar Usuario.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Debe seleccionar al Usuario que desea Activar o Desactivar.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void modificarClave() {
        if (this.usuarioSeleccionado != null) {
            this.tituloFormulario = "Resetear Clave de Usuario";
            this.copiarUsuarioSeleccionado();
            this.enModificarClave = true;
            this.enNuevo = false;
            this.enDetalles = false;

        }
    }

    public void resetearClave() {
        if (this.usuarioSeleccionado != null) {
            try {
                this.usuarioService.resetearClave(this.usuario);
                this.usuarios=this.usuarioService.obtnerAllUsuarios();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Modificado Exitosamente.", "Clave Reseteada " + this.usuario);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                this.enModificarClave = false;
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al resetear clave usuario. ", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    private void validarTipoUsuario() {
        if (this.usuario.getCodigoEstablecimiento().equals("REC")) {
            this.usuario.setTipo(Valores.TIPO_USUARIO_RECEPCION);
            String aux = null;
            this.usuario.setCodigoEstablecimiento(aux);

        } else {
            this.establecimientoSeleccionado = new Establecimiento();
            this.establecimientoSeleccionado.setCodigo(this.usuario.getCodigoEstablecimiento());
            this.usuario.setEstablecimiento(this.establecimientoSeleccionado);
            this.usuario.setTipo(Valores.TIPO_USUARIO_ESTABLECIMIENTO);

        }
    }

    public boolean isEnModificarClave() {
        return enModificarClave;
    }

    public void setEnModificarClave(boolean enModificarClave) {
        this.enModificarClave = enModificarClave;
    }

    public boolean isEnDetalles() {
        return enDetalles;
    }

    public void setEnDetalles(boolean enDetalles) {
        this.enDetalles = enDetalles;
    }

    public Establecimiento getEstablecimientoSeleccionado() {
        return establecimientoSeleccionado;
    }

    public void setEstablecimientoSeleccionado(Establecimiento establecimientoSeleccionado) {
        this.establecimientoSeleccionado = establecimientoSeleccionado;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
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

    public boolean isEnCambiarEstado() {
        return enCambiarEstado;
    }

    public void setEnCambiarEstado(boolean enCambiarEstado) {
        this.enCambiarEstado = enCambiarEstado;
    }

    public String getTituloFormulario() {
        return tituloFormulario;
    }

    public List<Establecimiento> getEstablecimientos() {
        return establecimientos;
    }

    private void copiarUsuarioSeleccionado() {
        this.usuario = new Usuario();
        this.usuario.setCodigo(this.usuarioSeleccionado.getCodigo());
        this.usuario.setNombre(this.usuarioSeleccionado.getNombre());
        this.usuario.setCodigoEstablecimiento(this.usuarioSeleccionado.getCodigoEstablecimiento());
        this.usuario.setEstado(this.usuarioSeleccionado.getEstado());
        this.usuario.setNickname(this.usuarioSeleccionado.getNickname());
        this.usuario.setClave(this.usuarioSeleccionado.getClave());
        this.usuario.setTipo(this.usuarioSeleccionado.getTipo());

    }

}
