/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MODELO;

import java.util.List;

/**
 *
 * @author User
 */
public interface IRecursoEducativo {
    List<RecursoEducativo> listarRecursos();

    void guardarRecurso(RecursoEducativo recurso);
}
