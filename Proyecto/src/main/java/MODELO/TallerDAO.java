package MODELO;

import CONEXIONSQL.ConexionBD;
import java.sql.*;

public class TallerDAO implements ITaller{
    private final ConexionBD conexion;

    public TallerDAO(ConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public void registrarInscripcion(Taller taller) {
        String sql = "{CALL sp_RegistrarTaller(?, ?)}";
        try (Connection con = conexion.establecerConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, taller.getNombre());
            cs.setString(2, taller.getParticipante());
            cs.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar taller: " + e.getMessage());
        }
    }

    @Override
    public int contarParticipantes() {
        String sql = "SELECT COUNT(*) AS total FROM Talleres";
        try (Connection con = conexion.establecerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al contar participantes: " + e.getMessage());
        }
        return 0;
    }
}
