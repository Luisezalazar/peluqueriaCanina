
package com.mycompany.peluqueriacanina.logica;

import com.mycompany.peluqueriacanina.persistencia.ControladoraPersistencia;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class Controladora {
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void guardar(String nombreMasco, String raza, String color, String observaciones, String NombreDuenio, String CelDuenio, String alergico, String atencion) {
         //Creacion del Dueño
         Duenio duenio = new Duenio();
         duenio.setNombre(NombreDuenio);
         duenio.setCelDuenio(CelDuenio);
         // Crear la mascota
         Mascota masco = new Mascota();
         masco.setNombre(nombreMasco);
         masco.setRaza(raza);
         masco.setColor(color);
         masco.setAlergico(alergico);
         masco.setAtencion_especial(atencion);
         masco.setObservaciones(observaciones);
         masco.setUnDuenio(duenio);
         //Guardar ambos
         controlPersis.guardar(duenio,masco);
         
         //Crear alerta visible
         JOptionPane optionPane = new JOptionPane("Se guardó correctamente");
         optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
         JDialog dialog = optionPane.createDialog("Guardado exitoso");
         dialog.setAlwaysOnTop(true);
         dialog.setVisible(true);
    }

    public List<Mascota> traerMascotas() {
        
        return controlPersis.traerMascotas();
        
        }

    public void borrarMascota(int num_cliente) {
       controlPersis.borrarMascota(num_cliente);
    }

   

    public Mascota traerMascota(int num_cliente) {
       return controlPersis.traerMascota(num_cliente);
    }

    public void modificarMascota(Mascota masco, String nombreMasco, String raza, String color, String observaciones, String alergico, String atencion, String NombreDuenio, String CelDuenio) {
       //Mascota
        masco.setNombre(nombreMasco);
        masco.setRaza(raza);
        masco.setColor(color);
        masco.setObservaciones(observaciones);
        masco.setAtencion_especial(atencion);
        masco.setAlergico(alergico);
        //Modificar mascota
        controlPersis.modificarMascota(masco);
        //Valores de Dueño
        Duenio dueno = this.buscarDuenio(masco.getUnDuenio().getId_duenio());
        dueno.setCelDuenio(CelDuenio);
        dueno.setNombre(NombreDuenio);
        //Llamar al modificar Dueño
        this.modificarDuenio(dueno);
        
        
    }

    private Duenio buscarDuenio(int id_duenio) {
            return controlPersis.traerDuenio(id_duenio);
    }

    private void modificarDuenio(Duenio dueno) {
        controlPersis.modificarDuenio(dueno);
    }

    

    
    
}
