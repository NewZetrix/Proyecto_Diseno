/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author User
 */
public class PreferenciasUsuario {
    private int usuarioId;
    private String horarioDisponible;
    private String medioContacto;
    private String recibeMateriales;
    private String comentario;

    public PreferenciasUsuario(int usuarioId, String horarioDisponible, String medioContacto, String recibeMateriales, String comentario) {
        this.usuarioId = usuarioId;
        this.horarioDisponible = horarioDisponible;
        this.medioContacto = medioContacto;
        this.recibeMateriales = recibeMateriales;
        this.comentario = comentario;
    }

    public int getUsuarioId() { 
        return usuarioId; 
    }
    
    public String getHorarioDisponible() { 
        return horarioDisponible; 
    }
    
    public String getMedioContacto() { 
        return medioContacto; 
    }
    
    public String getRecibeMateriales() { 
        return recibeMateriales; 
    }
    
    public String getComentario() { 
        return comentario; 
    }
}
