package MODELO;

import CONEXIONSQL.ConexionBD;
import java.sql.*;

public class UsuarioDAO implements IUsuario{
    private final ConexionBD conexion;

    public UsuarioDAO(ConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        String sql = "{CALL sp_RegistrarUsuario(?, ?, ?, ?, ?)}";
        try (Connection con = conexion.establecerConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, usuario.getNombre());
            cs.setString(2, usuario.getTipoUsuario());
            cs.setString(3, usuario.getTelefono());
            cs.setString(4, usuario.getDireccion());
            cs.setString(5, usuario.getCorreo());
            cs.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar usuario: " + e.getMessage(), e);
        }
    }
    
    public int registrarYDevolverId(Usuario usuario) {
        int idGenerado = -1;
        String sql = "{CALL sp_RegistrarUsuario(?, ?, ?, ?, ?)}";
        String selectId = "SELECT TOP 1 Id FROM Usuarios ORDER BY Id DESC";

        try (Connection con = conexion.establecerConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, usuario.getNombre());
            cs.setString(2, usuario.getTipoUsuario());
            cs.setString(3, usuario.getTelefono());
            cs.setString(4, usuario.getDireccion());
            cs.setString(5, usuario.getCorreo());
            cs.execute();

            // Recuperar el último ID generado
            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(selectId)) {
                if (rs.next()) {
                    idGenerado = rs.getInt("Id");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al registrar y obtener ID del usuario: " + e.getMessage(), e);
        }
        return idGenerado;
    }

    @Override
    public String obtenerCorreoPorId(int usuarioId) {
        String sql = "SELECT Correo FROM Usuarios WHERE Id = ?";
        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("Correo");
            } else {
                throw new RuntimeException("No se encontró correo para el usuario con ID: " + usuarioId);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener correo del usuario: " + e.getMessage(), e);
        }
    }

}
