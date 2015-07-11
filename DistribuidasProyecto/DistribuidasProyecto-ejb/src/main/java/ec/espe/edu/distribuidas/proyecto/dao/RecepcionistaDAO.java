/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espe.edu.distribuidas.proyecto.dao;

import com.persist.common.dao.DefaultGenericDAOImple;
import ec.espe.edu.distribuidas.proyecto.model.Recepcionista;

/**
 *
 * @author Diego
 */
public class RecepcionistaDAO extends DefaultGenericDAOImple<Recepcionista,Integer> {

    public RecepcionistaDAO() {
        super(Recepcionista.class);
    }
}
