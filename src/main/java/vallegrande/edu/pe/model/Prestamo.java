package vallegrande.edu.pe.model;

public class Prestamo {

    private String libro;
    private String usuario;
    private String fecha;

    public Prestamo(String libro, String usuario, String fecha) {
        this.libro = libro;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    // GETTERS
    public String getLibro() { return libro; }
    public String getUsuario() { return usuario; }
    public String getFecha() { return fecha; }

    // SETTERS 🔥 (IMPORTANTE)
    public void setLibro(String libro) { this.libro = libro; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}