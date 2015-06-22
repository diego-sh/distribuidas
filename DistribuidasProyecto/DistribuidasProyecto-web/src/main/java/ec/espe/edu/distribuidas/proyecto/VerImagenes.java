/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.espe.edu.distribuidas.proyecto;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Diego
 */
@ManagedBean
public class VerImagenes {
      private List<String> imagen;
     
    @PostConstruct
    public void init() {
        imagen = new ArrayList<String>();
        for (int i = 1; i <= 4; i++) {
            imagen.add("img" + i + ".jpg");
        }
    }
 
    public List<String> getImagen() {
        return imagen;
    }
    
}
