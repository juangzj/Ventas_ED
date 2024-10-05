
package modelo;

/**
 *
 * @author Usuario 1
 */
public class Articulo {
    
    //Atributos de un articulo
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;

    //Metodo contructor con atributos
    public Articulo(int id, String nombre, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    //Metodo constructor vacio
    public Articulo() {
    }

    //Metodos getter y setter para acceder a los atributos de un articulo
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
    
    
    
    
            
}
