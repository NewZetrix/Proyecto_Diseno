/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import CONEXIONSQL.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;

/**
 *
 * @author User
 */
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
