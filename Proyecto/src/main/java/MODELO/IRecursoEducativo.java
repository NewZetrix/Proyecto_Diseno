package MODELO;

import java.util.List;

public interface IRecursoEducativo {
    List<RecursoEducativo> listarRecursos();

    void guardarRecurso(RecursoEducativo recurso);
}
