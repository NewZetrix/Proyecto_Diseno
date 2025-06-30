package MODELO;

public class RecursoEducativo {
    private int id;
    private String titulo;
    private String contenido;
    private String categoria;

    public RecursoEducativo(int id, String titulo, String contenido, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.categoria = categoria;
    }

    public int getId() { 
        return id; 
    }
    public String getTitulo() { 
        return titulo; 
    }
    public String getContenido() { 
        return contenido; 
    }
    
    public String getCategoria(){
        return categoria;
    }
}
