/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.dao;

import com.persist.common.dao.DefaultGenericDAOImple;
import ec.espe.edu.distribuidas.proyecto.model.Factura;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Daniel
 */
@LocalBean
@Stateless
public class FacturaDAO extends DefaultGenericDAOImple<Factura, Integer> {

    public FacturaDAO() {
        super(Factura.class);
    }
}
