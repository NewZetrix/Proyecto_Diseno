package MODELO.DAO;

import CONEXIONSQL.ConexionBD;
import MODELO.DatosComplementarios;
import MODELO.INTERFACES.IDatosComplementarios;
import java.sql.CallableStatement;
import java.sql.Connection;

public class DatosComplementariosDAO implements IDatosComplementarios{
    private final ConexionBD conexion;

    public DatosComplementariosDAO(ConexionBD conexion) {
        this.conexion = conexion;
    }
    
    public void registrarDatos(DatosComplementarios datos) {
        String sql = "{CALL sp_RegistrarDatosComplementarios(?, ?, ?, ?, ?, ?)}";
        try (Connection con = conexion.establecerConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, datos.getUsuarioId());
            cs.setString(2, datos.getNivelEducativo());
            cs.setString(3, datos.getOcupacion());
            cs.setInt(4, datos.getEdad());
            cs.setString(5, datos.getGenero());
            cs.setString(6, datos.getInteresTaller());
            cs.execute();

        } catch (Exception e) {
            throw new RuntimeException("Error al registrar datos complementarios: " + e.getMessage(), e);
        }
    }
}
