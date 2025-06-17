
package MODELO;

import CONEXIONSQL.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecursoEducativoDAO implements IRecursoEducativo{
    private final ConexionBD conexion;

    public RecursoEducativoDAO(ConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<RecursoEducativo> listarRecursos() {
        List<RecursoEducativo> recursos = new ArrayList<>();
        String sql = "SELECT Id, Titulo, Descripcion FROM Recursos";

        try (Connection con = conexion.establecerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("Id");
                String titulo = rs.getString("Titulo");
                String descripcion = rs.getString("Descripcion");

                RecursoEducativo recurso = new RecursoEducativo(id, titulo, descripcion);
                recursos.add(recurso);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar recursos: " + e.getMessage(), e);
        }

        return recursos;
    }

    @Override
    public void guardarRecurso(RecursoEducativo recurso) {
        String sql = "{CALL sp_AgregarRecursosEducativo(?, ?)}";
        try (Connection con = conexion.establecerConexion();
            CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, recurso.getTitulo());
            cs.setString(2, recurso.getContenido());
            cs.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar recurso educativo: " + e.getMessage(), e);
        }
       }
}
