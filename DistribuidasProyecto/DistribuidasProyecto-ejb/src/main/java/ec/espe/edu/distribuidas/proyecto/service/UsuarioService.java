/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.service;

import ec.espe.edu.distribuidas.proyecto.dao.UsuarioDAO;
import ec.espe.edu.distribuidas.proyecto.model.Usuario;
import ec.espe.edu.distribuidas.proyecto.util.EncripcionUtil;
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
        List<Usuario> usuarios=this.usuarioDAO.find(usuarioTMP, false);
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
        String clave = EncripcionUtil.encriptarMD5(usuario.getClave());
        usuario.setClave(clave);
        this.usuarioDAO.insert(usuario);
    }

    public void actualizarUsuario(Usuario usuario) {
        this.usuarioDAO.update(usuario);
    }
    
    
}
