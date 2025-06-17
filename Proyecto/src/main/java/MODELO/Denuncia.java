package MODELO;

public class Denuncia {
    private String descripcion;
    private boolean anonima;
    private String fecha;

    public Denuncia(String descripcion, boolean anonima, String fecha) {
        this.descripcion = descripcion;
        this.anonima = anonima;
        this.fecha = fecha;
    }

    public String getDescripcion() { 
        return descripcion; 
    }
    public boolean esAnonima() { 
        return anonima; 
    }
    public String getFecha() { 
        return fecha; 
    }
}
