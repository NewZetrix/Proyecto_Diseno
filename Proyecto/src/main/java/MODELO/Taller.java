/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author User
 */
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
