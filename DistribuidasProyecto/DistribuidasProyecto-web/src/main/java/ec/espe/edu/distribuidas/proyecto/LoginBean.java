/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto;

import ec.espe.edu.distribuidas.proyecto.model.Usuario;
import ec.espe.edu.distribuidas.proyecto.service.UsuarioService;
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
public class LoginBean {

    private String usuario;
    private String clave;
    @EJB
    private UsuarioService usuarioService;

    public String doLogin() {
        String pagina = "";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        if ("administrador".equalsIgnoreCase(this.usuario) && "admin123".equals(this.clave)) {
            session.setAttribute("Usuario", "Administrador");
            pagina = "home";
        } else {
            Usuario usuario = this.usuarioService.login(this.usuario, this.clave);
            if (usuario != null) {
                session.setAttribute("usuario", usuario);
                if (usuario.getEstado().equals("PRI")) {
                    pagina = "cambioClave";
                } else {
                    if (usuario.getEstado().equals("ACT")) {
                        pagina = "adminEstablecimiento";
                    }else{
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Usuario no se encuentra activo.\n");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        pagina="login";
                    }
                }
            }else{
                //para recepcion
            }
        }
        return pagina;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
