/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espe.edu.distribuidas.proyecto.dao;

import com.persist.common.dao.DefaultGenericDAOImple;
import ec.espe.edu.distribuidas.proyecto.model.Registro;

/**
 *
 * @author Diego
 */
public class RegistroDAO extends DefaultGenericDAOImple<Registro, Integer> {

    public RegistroDAO() {
        super(Registro.class);
    }
}
