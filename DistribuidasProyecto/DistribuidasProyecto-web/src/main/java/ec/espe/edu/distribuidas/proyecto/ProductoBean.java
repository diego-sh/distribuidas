/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto;

import ec.espe.edu.distribuidas.proyecto.model.Producto;
import ec.espe.edu.distribuidas.proyecto.service.ProductoService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Diego
 */
@ManagedBean
@ViewScoped
public class ProductoBean {

    private List<Producto> productos;
    private Producto producto;
    private Producto productoSeleccionado;
    private String tituloFormulario;

    private boolean enNuevoProducto;
    private boolean enModificarProducto;
    private boolean enDetalleProducto;
    private boolean enEliminarProducto;
    
    @EJB
    private ProductoService productoService;
    
    @PostConstruct
    public void postConstructor() {
        this.productos = this.productoService.obtenerProductos();
    }

    public void nuevoProducto() {
        this.producto= new Producto();
        this.tituloFormulario = "Crear Producto";
        this.enNuevoProducto=true;
    }

    public void modificarProducto() {
        if(productoSeleccionado!=null){
            this.tituloFormulario = "Modificar Producto";
            this.copiarProductoSelecionado();
            this.enModificarProducto=true;
            
        }
    }

    public void eliminarProducto() {
        if(productoSeleccionado!=null){
            this.tituloFormulario = "Elminar Producto";
            this.copiarProductoSelecionado();
            this.enEliminarProducto = true;
        }

    }

    public void detallesProducto() {
        if(productoSeleccionado!=null){
            this.tituloFormulario = "Detalle Producto";
            this.copiarProductoSelecionado();
            this.enDetalleProducto = true;
        }
    }
    
    public void guardarProducto(){
        if (this.enNuevoProducto) {
            try {
                this.productoService.crearProducto(this.producto);
                this.enNuevoProducto = false;
                this.productos = this.productoService.obtenerProductos();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto Creado", " Se ha creado el :" + this.producto);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear producto", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }else if (this.enModificarProducto) {
            try {
                this.productoService.actualizarProducto(this.producto);
                this.enModificarProducto = false;
                this.productos = this.productoService.obtenerProductos();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto Actualizado.", "Se ha actualizado el " + this.producto);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar producto", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        }else if (this.enEliminarProducto) {
            try {
                this.productoService.eliminarProducto(this.producto.getCodigo());
                this.enEliminarProducto = false;
                this.productos = this.productoService.obtenerProductos();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto Eliminado.", "Se ha eliminado con éxito " + this.producto);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar producto", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    
    }
    
    public void cancelar(){
        this.enNuevoProducto=false;
        this.enModificarProducto=false;
        this.enDetalleProducto=false; 
        this.enEliminarProducto=false;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public String getTituloFormulario() {
        return tituloFormulario;
    }   
    

    public boolean isEnNuevoProducto() {
        return enNuevoProducto;
    }

    public boolean isEnModificarProducto() {
        return enModificarProducto;
    }

    public boolean isEnDetalleProducto() {
        return enDetalleProducto;
    }

    public boolean isEnEliminarProducto() {
        return enEliminarProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
     private void copiarProductoSelecionado() {
        this.producto = new Producto();
        this.producto.setCodigo(this.productoSeleccionado.getCodigo());
        this.producto.setNombre(this.productoSeleccionado.getNombre());
        this.producto.setPrecio(this.productoSeleccionado.getPrecio());
        this.producto.setDescripcion(this.productoSeleccionado.getDescripcion());
    }

}
