/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author User
 */
public class Usuario implements Cloneable{
    private String nombre;
    private String tipoUsuario;

    public Usuario(String nombre, String tipoUsuario) {
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    @Override
    public Usuario clone() {
        try {
            return (Usuario) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar usuario", e);
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                '}';
    }
}
