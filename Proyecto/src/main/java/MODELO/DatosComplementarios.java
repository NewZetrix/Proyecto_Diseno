package MODELO;

public class DatosComplementarios {
    private int usuarioId;
    private String nivelEducativo;
    private String ocupacion;
    private int edad;
    private String genero;
    private String interesTaller;

    public DatosComplementarios(int usuarioId, String nivelEducativo, String ocupacion, int edad, String genero, String interesTaller) {
        this.usuarioId = usuarioId;
        this.nivelEducativo = nivelEducativo;
        this.ocupacion = ocupacion;
        this.edad = edad;
        this.genero = genero;
        this.interesTaller = interesTaller;
    }

    public int getUsuarioId() { 
        return usuarioId; 
    }
    
    public String getNivelEducativo() { 
        return nivelEducativo; 
    }
    
    public String getOcupacion() { 
        return ocupacion; 
    }
    
    public int getEdad() { 
        return edad; 
    }
    
    public String getGenero() { 
        return genero; 
    }
    
    public String getInteresTaller() { 
        return interesTaller; 
    }
}
