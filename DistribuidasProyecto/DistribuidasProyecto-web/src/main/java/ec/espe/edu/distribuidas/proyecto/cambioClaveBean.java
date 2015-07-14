/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto;


import ec.espe.edu.distribuidas.proyecto.model.Usuario;
import ec.espe.edu.distribuidas.proyecto.service.UsuarioService;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Diego
 */
@ManagedBean
@ViewScoped
public class cambioClaveBean {

    private String nombre;
    private String claveNueva;
    private String confirmacinoClaveNueva;
    private Usuario usuario;
    

    @EJB
    private UsuarioService usuarioService;
    

    @PostConstruct
    public void obtnerSesion() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        this.usuario = (Usuario) session.getAttribute("usuario");   
        this.nombre=this.usuario.getNombre();

    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }

    public String getConfirmacinoClaveNueva() {
        return confirmacinoClaveNueva;
    }

    public void setConfirmacinoClaveNueva(String confirmacinoClaveNueva) {
        this.confirmacinoClaveNueva = confirmacinoClaveNueva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }    

  
    public String getNombre() {
        return nombre;
    }

    public String cambiarClave() {
        if (this.claveNueva.equals(this.confirmacinoClaveNueva)) {
            this.usuarioService.cambiarClave(this.usuario.getCodigo(), this.claveNueva);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ÉXITO", "Se ha cambiado la clave correctamente");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "login";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Usuario o Contraseña Incorrecta");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "cambioClave";
        }

    }    

}
