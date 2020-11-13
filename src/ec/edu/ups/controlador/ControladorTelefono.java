/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.Telefono;
import java.util.Optional;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tano
 */
public class ControladorTelefono extends AbstractControladores<Telefono>{

    public ControladorTelefono() {
        super();
    }
    
    public boolean crear(String numero, String tipo, String operadora){
        return create(new Telefono(generarId(), numero, tipo, operadora));
    }
    
    public Optional<Telefono> buscar(int id){
        return read(new Telefono(id, "", "" , ""));
    }
    
    public boolean actualizar(int id, String numero, String operadora, String tipo){
        Optional<Telefono> telefono = read(new Telefono(id, numero, tipo, operadora));
        if(telefono.isPresent()){
            return update(telefono.get(), new Telefono(id, numero, tipo, operadora));
        }
        return false;
    }
    
    public boolean eliminar(int id){
        Optional<Telefono> telefono = read(new Telefono(id, "", "", ""));
        if(telefono.isPresent()){
            return remove(telefono.get());
        }
        return false;
    }
    
    public void verTelefonos(DefaultTableModel tabla){
	tabla.setNumRows(0);
	for(int i = 0; i < getList().size(); i++){
	    tabla.addRow(new Object[]{
		getList().get(i).getId(),
		getList().get(i).getTipo(),
		getList().get(i).getNumero(),
		getList().get(i).getOperadora()
	    });
	}
    }
    
    public int generarId() {
        if(this.getList().size()>0)
            return this.getList().get(this.getList().size() -1).getId() +1;
        return 1;
    }
}
