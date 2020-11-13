/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author tano
 */
public class ControladorPersona extends AbstractControladores<Persona>{
    private Persona sesion;
    private ControladorTelefono controladorTelefono;

    public ControladorPersona(ControladorTelefono controladorTelefono) {
        super();
        this.controladorTelefono = controladorTelefono;
    }
    
    public boolean crear(String cedula, String nombre, String apellido, LocalDate fechaNacimiento, String correo, String pass){
        return create(new Persona(generarId(), nombre, apellido, cedula, correo, pass, fechaNacimiento));
    }
    
    public Optional<Persona> buscar(String cedula){
        return read(new Persona(0, cedula, cedula, cedula, cedula, cedula, LocalDate.now()));
    }
    
    public Optional<Persona> buscarCorreo(String correo){
        return super.getList().stream().filter(per -> per.getCorreo().equals(correo)).findFirst();
    }
    
    public boolean actualizar(String cedula, String nombre, String apellido, LocalDate fechaNacimiento, String correo){
        Optional<Persona> persona = read(new Persona(0, nombre, apellido, cedula, correo, "", fechaNacimiento));
        if(persona.isPresent()){
            persona.get().setContrasena(correo);
            Persona per = new Persona(persona.get().getId(), nombre, apellido, cedula, correo, persona.get().getContrasena(), fechaNacimiento);
            per.setTelefonos(persona.get().getTelefonos());
            return update(persona.get(), per);
        }
        return false;
    }
    
    public boolean eliminar(String cedula){
        Optional<Persona> persona = read(new Persona(0, cedula, cedula, cedula, cedula, cedula, LocalDate.now()));
        if(persona.isPresent()){
            persona.get().getTelefonos().stream().forEach(t -> controladorTelefono.eliminar(t.getId()));
            return remove(persona.get());
        }
        return false;
    }
    
    public void verPersonas(DefaultTableModel tabla){
        tabla.setRowCount(0);
	for(int i = 0; i < this.getList().size(); i++){
	    tabla.addRow(new Object[]{
		this.getList().get(i).getCedula(),
		this.getList().get(i).getNombre(),
		this.getList().get(i).getApellido(),
                this.getList().get(i).getFechaNacimiento().format(DateTimeFormatter.ISO_LOCAL_DATE),
                this.getList().get(i).getCorreo()
	    });
	}
    }
    
    public int generarId() {
        if(this.getList().size()>0)
            return this.getList().get(this.getList().size() -1).getId() +1;
        return 1;
    }
    
    public boolean iniciarSesion(String correo, String pass){
        Optional<Persona> login = this.getList().stream().filter(per -> per.getCorreo().equals(correo) && per.getContrasena().equals(pass)).findFirst();
        if(login.isPresent()){
            sesion = login.get();
            return true;
        }
        return false;
    }
    
    public void agregarTelefono(Telefono telefono){
        sesion.agregarTelefono(telefono);
    }
    
    public void actualizarTelefono(Telefono telefono){
        sesion.actualizarTelefono(telefono);
    }
    
    public void eliminarTelefono(Telefono telefono){
        sesion.eliminarTelefono(telefono);
    }
    
    public List<Persona> obtenerListado(){
        return super.getList();
    }

    public Persona getSesion() {
        return sesion;
    }
    
    public void verTelefonosSesion(DefaultTableModel tabla){
	tabla.setNumRows(0);
	for(int i = 0; i < sesion.getTelefonos().size(); i++){
	    tabla.addRow(new Object[]{
		sesion.getTelefonos().get(i).getId(),
		sesion.getTelefonos().get(i).getTipo(),
		sesion.getTelefonos().get(i).getNumero(),
		sesion.getTelefonos().get(i).getOperadora()
	    });
	}
    }
    
    public void telefonosPersona(Persona persona, DefaultTableModel tabla){
        tabla.setNumRows(0);
	for(int i = 0; i < persona.getTelefonos().size(); i++){
	    tabla.addRow(new Object[]{
		persona.getTelefonos().get(i).getId(),
		persona.getTelefonos().get(i).getTipo(),
		persona.getTelefonos().get(i).getNumero(),
		persona.getTelefonos().get(i).getOperadora()
	    });
	}
    }
}
