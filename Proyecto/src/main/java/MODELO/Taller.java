package MODELO;

public class Taller {
    private int id;
    private String nombre;
    private String descripcion;
    private String fecha;
    private int cupo;

    public Taller(int id, String nombre, String descripcion, String fecha, int cupo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.cupo = cupo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public int getCupo() {
        return cupo;
    }
}
