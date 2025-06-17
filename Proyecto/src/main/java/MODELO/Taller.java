package MODELO;

public class Taller {
    private String nombre;
    private String participante;

    public Taller(String nombre, String participante) {
        this.nombre = nombre;
        this.participante = participante;
    }

    public String getNombre() { 
        return nombre; 
    }
    public String getParticipante() { 
        return participante; 
    }
}
