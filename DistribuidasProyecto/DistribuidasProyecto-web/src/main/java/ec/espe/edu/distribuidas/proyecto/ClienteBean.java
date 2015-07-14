/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.distribuidas.proyecto;

import ec.espe.edu.distribuidas.proyecto.model.Cliente;
import ec.espe.edu.distribuidas.proyecto.service.ClienteService;
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
public class ClienteBean {

    private List<Cliente> clientes;
    private Cliente cliente;
    private Cliente clienteSeleccionado;
    private String tituloFormulario;

    private boolean enNuevoCliente;
    private boolean enModificarCliente;
    private boolean enDetalleCliente;
    private boolean enEliminarCliente;

    @EJB
    private ClienteService clienteService;

    @PostConstruct
    public void postConstructor() {
        this.clientes = this.clienteService.obtenerClientes();
    }

    public void nuevoCliente() {
        this.cliente = new Cliente();
        this.enNuevoCliente = true;
        this.tituloFormulario = "Crear Cliente";
    }

    public void modificarCliente() {
        if (this.clienteSeleccionado != null) {
            this.tituloFormulario = "Modificar Cliente";
            this.copiarClienteSelecionado();
            this.enModificarCliente = true;
        }

    }

    public void eliminarCliente() {
        if (this.clienteSeleccionado != null) {
            this.tituloFormulario = "Elminar Cliente";
            this.copiarClienteSelecionado();
            this.enEliminarCliente = true;
        }
    }

    public void detallesCliente() {
        if (clienteSeleccionado != null) {
            this.tituloFormulario = "Detalle Cliente";
            this.copiarClienteSelecionado();
            this.enDetalleCliente = true;
        }
    }

    public void guardarCliente() {
        if (this.enNuevoCliente) {
            try {
                this.clienteService.crearCliente(this.cliente);
                this.enNuevoCliente = false;
                this.clientes = this.clienteService.obtenerClientes();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Creado", " Se ha creado el :" + this.cliente);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cliente", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else if (this.enModificarCliente) {
            try {
                this.clienteService.actualizarCliente(this.cliente);
                this.enModificarCliente = false;
                this.clientes = this.clienteService.obtenerClientes();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Actualizado.", "Se ha actualizado el " + this.cliente);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar cliente", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        } else if (this.enEliminarCliente) {
            try {
                this.clienteService.eliminarCliente(this.cliente.getCedula());
                this.enEliminarCliente = false;
                this.clientes = this.clienteService.obtenerClientes();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Eliminado.", "Se ha eliminado con éxito " + this.cliente);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar cliente", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }

    }

    public void cancelar() {
        this.enNuevoCliente = false;
        this.enModificarCliente = false;
        this.enDetalleCliente = false;
        this.enEliminarCliente=false;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public boolean isEnNuevoCliente() {
        return enNuevoCliente;
    }

    public boolean isEnModificarCliente() {
        return enModificarCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isEnDetalleCliente() {
        return enDetalleCliente;
    }

    public String getTituloFormulario() {
        return tituloFormulario;
    }

    public boolean isEnEliminarCliente() {
        return enEliminarCliente;
    }

    private void copiarClienteSelecionado() {
        this.cliente = new Cliente();
        this.cliente.setCedula(this.clienteSeleccionado.getCedula());
        this.cliente.setNombre(this.clienteSeleccionado.getNombre());
        this.cliente.setTelefono(this.clienteSeleccionado.getTelefono());
        this.cliente.setEmail(this.clienteSeleccionado.getEmail());
        this.cliente.setDireccion(this.clienteSeleccionado.getDireccion());
    }

}
