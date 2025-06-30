package MODELO.DAO;

import CONEXIONSQL.ConexionBD;
import MODELO.Denuncia;
import MODELO.INTERFACES.IDenuncia;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class DenunciaDAO implements IDenuncia{
    private final ConexionBD conexion;

    public DenunciaDAO(ConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public void registrarDenuncia(Denuncia denuncia) {
        String sql = "{CALL sp_RegistrarDenuncia(?, ?, ?)}";
        try (Connection con = conexion.establecerConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, denuncia.getDescripcion());
            cs.setBoolean(2, denuncia.esAnonima());
            cs.setString(3, denuncia.getFecha());
            cs.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar denuncia: " + e.getMessage());
        }
    }
}
