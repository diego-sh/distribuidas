/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.dao;

import com.persist.common.dao.DefaultGenericDAOImple;
import ec.espe.edu.distribuidas.proyecto.model.Detalle;
import ec.espe.edu.distribuidas.proyecto.model.DetallePK;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Daniel
 */
@LocalBean
@Stateless
public class DetalleDAO extends DefaultGenericDAOImple<Detalle, DetallePK> {

    public DetalleDAO() {
        super(Detalle.class);
    }
}
