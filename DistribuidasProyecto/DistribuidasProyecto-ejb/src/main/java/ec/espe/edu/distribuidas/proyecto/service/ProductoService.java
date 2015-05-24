/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto.service;

import ec.espe.edu.distribuidas.proyecto.dao.ProductoDAO;
import ec.espe.edu.distribuidas.proyecto.model.Producto;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Diego
 */
@LocalBean
@Stateless
public class ProductoService {

    @EJB
    private ProductoDAO productoDAO;

    public void crearProducto(Producto producto) {
        Producto productoTMP = new Producto();
        productoTMP.setCodigo(producto.getCodigo());
        List<Producto> productos = this.productoDAO.find(productoTMP);
        if (productos == null) {
            this.productoDAO.insert(producto);
        } else {
            throw new RuntimeException("El Producto: " + producto.getCodigo() + "ya existe.");
        }

    }

    public void actualizarProducto(Producto producto) {
        this.productoDAO.update(producto);
    }

    public void eliminarProducto(String codigo) {
        Producto productoTMP = this.productoDAO.findById(codigo, false);
        this.productoDAO.remove(productoTMP);
    }

    public Producto obtenerProductoPorCodigo(String codigo) {
        return this.productoDAO.findById(codigo, false);
    }
}
