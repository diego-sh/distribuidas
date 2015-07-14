/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.service;

import ec.espe.edu.distribuidas.proyecto.dao.UsuarioDAO;
import ec.espe.edu.distribuidas.proyecto.model.Usuario;
import ec.espe.edu.distribuidas.proyecto.util.EncripcionUtil;
import ec.espe.edu.distribuidas.proyecto.util.Valores;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Daniel
 */
@LocalBean
@Stateless
public class UsuarioService {

    @EJB
    private UsuarioDAO usuarioDAO;

    public Usuario login(String nickname, String clave) {
        Usuario usuarioTMP = new Usuario();
        usuarioTMP.setNickname(nickname);
        List<Usuario> usuarios = this.usuarioDAO.find(usuarioTMP, false);
        if (usuarios != null) {
            String claveEnc = EncripcionUtil.encriptarMD5(clave);
            if (claveEnc.equals(usuarios.get(0).getClave())) {
                return usuarios.get(0);
            }
        }
        return null;
    }

    public Usuario obtenerPorCodigo(Integer codigo) {
        return this.usuarioDAO.findById(codigo, false);
    }

    public void crearUsuario(Usuario usuario) {
        //validar
        String clave = EncripcionUtil.encriptarMD5(Valores.CLAVE_DEFAULT);
        usuario.setClave(clave);
        usuario.setEstado(Valores.ESTADO_PRIMER);
        this.usuarioDAO.insert(usuario);
    }

    public void actualizarUsuario(Usuario usuario) {
        this.usuarioDAO.update(usuario);
    }

    public void resetearClave(Usuario usuario) {
        Usuario usuarioTMP = this.usuarioDAO.findById(usuario.getCodigo(), false);
        if (usuarioTMP != null) {
            usuario.setClave(EncripcionUtil.encriptarMD5(Valores.CLAVE_DEFAULT));
            usuario.setEstado(Valores.ESTADO_PRIMER);
            this.usuarioDAO.update(usuario);            
        }
    }
    
    public void cambiarClave(Integer codigo, String claveNueva){
        Usuario usuarioTMP=this.usuarioDAO.findById(codigo,false);
        if(usuarioTMP !=null){
            usuarioTMP.setClave(EncripcionUtil.encriptarMD5(claveNueva));
            usuarioTMP.setEstado(Valores.ESTADO_HABILITADO);
            this.usuarioDAO.update(usuarioTMP);            
        }else{
            throw new RuntimeException("El usuario no existe");
        }
    }

    public List<Usuario> obtnerAllUsuarios() {
        return this.usuarioDAO.findAll();
    }

    public void habilitarUsuario(Integer codigo) {
        Usuario usuarioTMP = this.usuarioDAO.findById(codigo, false);
        usuarioTMP.setEstado(Valores.ESTADO_HABILITADO);
    }

    public void inhabilitarUsuario(Integer codigo) {
        Usuario usuarioTMP = this.usuarioDAO.findById(codigo, false);
        usuarioTMP.setEstado(Valores.ESTADO_INHABILITADO);
    }

}
