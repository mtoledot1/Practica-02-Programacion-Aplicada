package ec.edu.ups.modelo;
public class Telefono implements Comparable<Telefono>{
    private int id;
    private String numero;
    private String tipo;
    private String operadora;

    public Telefono() {
    }

    public Telefono(int id, String numero, String tipo, String operadora) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.operadora = operadora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the operadora
     */
    public String getOperadora() {
        return operadora;
    }

    /**
     * @param operadora the operadora to set
     */
    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
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
        final Telefono other = (Telefono) obj;
        if (this.id != other.id) {
            return false;
        }
	if (!this.numero.equalsIgnoreCase(other.numero)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Teléfono:\n" + 
                "Id: " + id + "\n" + 
                "Número: " + numero + "\n" +
                "Tipo: " + tipo + "\n" +
                "Operadora: " + operadora + "\n";
    }
    
    public String toStringVentana() {
        return "Id: " + id + ", " + 
                "Número: " + numero + ", " +
                "Tipo: " + tipo + ", " +
                "Operadora: " + operadora;
    }

    @Override
    public int compareTo(Telefono t) {
	if (this.numero.equalsIgnoreCase(t.numero)) {
            return 0;
        }
	return 1;
    }
}
