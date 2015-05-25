/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.service;

import ec.espe.edu.distribuidas.proyecto.dao.ConsumoDAO;
import ec.espe.edu.distribuidas.proyecto.dao.ProductoDAO;
import ec.espe.edu.distribuidas.proyecto.dao.VisitaDAO;
import ec.espe.edu.distribuidas.proyecto.model.Consumo;
import ec.espe.edu.distribuidas.proyecto.model.Visita;
import javax.ejb.EJB;

/**
 *
 * @author Diego
 */
public class ConsumoService {

    @EJB
    private ConsumoDAO consumoDAO;
    @EJB
    private VisitaDAO visitaDAO;
    @EJB
    private ProductoDAO productoDAO;
    
    

}
