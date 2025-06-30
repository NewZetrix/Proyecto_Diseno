package MODELO.INTERFACES;

import MODELO.RecursoEducativo;
import java.util.List;

public interface IRecursoEducativo {
    List<RecursoEducativo> listarRecursos();

    void guardarRecurso(RecursoEducativo recurso);
}
