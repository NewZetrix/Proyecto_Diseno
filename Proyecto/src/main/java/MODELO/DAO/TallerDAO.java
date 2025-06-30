package MODELO.DAO;

import CONEXIONSQL.ConexionBD;
import MODELO.INTERFACES.ITaller;
import MODELO.Taller;
import java.sql.*;
import java.util.*;

public class TallerDAO implements ITaller{
    private final ConexionBD conexion;

    public TallerDAO(ConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Taller> obtenerTalleres() {
        List<Taller> lista = new ArrayList<>();
        String sql = "SELECT Id, Nombre, Descripcion, Fecha, Cupo FROM Talleres";

        try (Connection con = conexion.establecerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("Id");
                String nombre = rs.getString("Nombre");
                String descripcion = rs.getString("Descripcion");
                String fecha = rs.getString("Fecha");
                int cupo = rs.getInt("Cupo");

                Taller taller = new Taller(id, nombre, descripcion, fecha, cupo);
                lista.add(taller);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener talleres: " + e.getMessage(), e);
        }

        return lista;
    }

    @Override
    public void inscribirTaller(int idTaller, String nombreTaller) {
        String sql = "UPDATE Talleres SET Cupo = Cupo - 1 WHERE Id = ? AND Cupo > 0";

        try (Connection con = conexion.establecerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idTaller);
            int actualizado = ps.executeUpdate();

            if (actualizado == 0) {
                throw new RuntimeException("No hay cupo disponible para el taller: " + nombreTaller);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al inscribirse en taller: " + e.getMessage(), e);
        }
    }
}
