/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.IUsuario;
import MODELO.Usuario;

/**
 *
 * @author User
 */
public class ControladorUsuario {
    private final IUsuario servicioUsuario;

    public ControladorUsuario(IUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    public void registrarNuevoUsuario(String nombre, String tipo, String telefono, String direccion, String correo) {
        Usuario usuario = new Usuario(nombre, tipo, telefono, direccion, correo);
        servicioUsuario.registrarUsuario(usuario);
    }
    
    public int registrarYObtenerId(String nombre, String tipo, String telefono, String direccion, String correo) {
        Usuario usuario = new Usuario(nombre, tipo, telefono, direccion, correo);
        return servicioUsuario.registrarYDevolverId(usuario);
    }
}
