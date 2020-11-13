package ec.edu.ups.modelo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String correo;
    private String contrasena;
    private LocalDate fechaNacimiento;
    private List<Telefono> listadoTelefonos;
    
    public Persona(){
	listadoTelefonos = new ArrayList<>();
    }

    public Persona(int id, String nombre, String apellido, String cedula, String correo, String contrasena, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.correo = correo;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
        listadoTelefonos = new ArrayList<>();
    }
   

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getApellido() {
	return apellido;
    }

    public void setApellido(String apellido) {
	this.apellido = apellido;
    }

    public String getCedula() {
	return cedula;
    }

    public void setCedula(String cedula) {
	this.cedula = cedula;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<Telefono> getTelefonos() {
        return listadoTelefonos;
    }
    
    public void setTelefonos(List<Telefono> listadoTelefonos){
	this.listadoTelefonos = listadoTelefonos;
    }
    
    public void agregarTelefono(Telefono telefono){
        listadoTelefonos.add(telefono);
    }
    
    public void actualizarTelefono(Telefono telefono){
        Optional<Telefono> telf = listadoTelefonos.stream().filter(tel -> tel.equals(telefono)).findFirst();
        if(telf.isPresent())
            listadoTelefonos.set(listadoTelefonos.indexOf(telf.get()), telefono);
    }
    
    public void eliminarTelefono(Telefono telefono){
        Optional<Telefono> telf = listadoTelefonos.stream().filter(tel -> tel.equals(telefono)).findFirst();
        if(telf.isPresent())
            listadoTelefonos.remove(telf.get());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.getCedula());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.cedula, other.cedula)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario\n" +
                "Cédula: " + getCedula() + "\n" +
                "Nombre: " + getNombre() + "\n" +
                "Apellido: " + getApellido() + "\n";
    }
}
