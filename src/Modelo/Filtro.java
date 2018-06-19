package Modelo;

/**
 *
 * @author LN710Q
 */
public class Filtro {
    private int id;
    private String carne;
    private String nombres;
    private String apellidos;
    private int edad;
    private String universidad;
    private boolean estado;

    public Filtro() {
    }

    public Filtro(int id, String carne, String nombres, String apellidos, int edad, String universidad, boolean estado) {
        this.id = id;
        this.carne = carne;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.universidad = universidad;
        this.estado = estado;
    }

    public Filtro(int id, String carne, String nombres, String apellidos, int edad, String universidad) {
        this.id = id;
        this.carne = carne;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.universidad = universidad;
    }

    public Filtro(String carne, String nombres, String apellidos, int edad, String universidad, boolean estado) {
        this.carne = carne;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.universidad = universidad;
        this.estado = estado;
    }

    public Filtro(String carne, String nombres, String apellidos, int edad, String universidad) {
        this.carne = carne;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.universidad = universidad;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
        
}
