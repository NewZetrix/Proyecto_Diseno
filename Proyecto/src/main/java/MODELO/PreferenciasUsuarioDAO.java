package MODELO;

import CONEXIONSQL.ConexionBD;
import java.sql.*;

public class PreferenciasUsuarioDAO implements IPreferenciasUsuario{
    private final ConexionBD conexion;

    public PreferenciasUsuarioDAO(ConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public void registrarPreferencias(PreferenciasUsuario preferencias) {
        String sql = "{CALL sp_RegistrarPreferenciasUsuario(?, ?, ?, ?, ?)}";
        try (Connection con = conexion.establecerConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, preferencias.getUsuarioId());
            cs.setString(2, preferencias.getHorarioDisponible());
            cs.setString(3, preferencias.getMedioContacto());
            cs.setString(4, preferencias.getRecibeMateriales());
            cs.setString(5, preferencias.getComentario());
            cs.execute();

        } catch (Exception e) {
            throw new RuntimeException("Error al registrar preferencias del usuario: " + e.getMessage(), e);
        }
    }
}
