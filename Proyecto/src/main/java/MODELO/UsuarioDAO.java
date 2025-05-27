/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONEXIONSQL.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class UsuarioDAO implements IUsuario{
    private final ConexionBD conexion;

    public UsuarioDAO(ConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        String sql = "{CALL sp_RegistrarUsuario(?, ?)}";
        try (Connection con = conexion.establecerConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, usuario.getNombre());
            cs.setString(2, usuario.getTipoUsuario());
            cs.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar usuario: " + e.getMessage(), e);
        }
    }
}
