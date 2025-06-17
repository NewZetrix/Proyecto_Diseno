package MODELO;

public class RecursoEducativo {
    private int id;
    private String titulo;
    private String contenido;

    public RecursoEducativo(int id, String titulo, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
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
}
