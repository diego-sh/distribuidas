/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.service;

import ec.espe.edu.distribuidas.proyecto.dao.UsuarioDAO;
import ec.espe.edu.distribuidas.proyecto.model.Usuario;
import ec.espe.edu.distribuidas.proyecto.util.EncripcionUtil;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author hendrix
 */
@LocalBean
@Stateless
public class UsuarioService {

    @EJB
    private UsuarioDAO usuarioDAO;

    public Usuario login(String codigoUsuario, String clave) {
        Usuario usuario = this.usuarioDAO.findById(codigoUsuario, false);
        if (usuario != null) {
            String claveEnc = EncripcionUtil.encriptarMD5(usuario.getClave());
            if (claveEnc.equals(clave)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario obtenerPorCodigo(String codigo) {
        return this.usuarioDAO.findById(codigo, false);
    }

}
