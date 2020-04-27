package gal.san.clemente.tarefa_5_bdo.model.dao;

import gal.san.clemente.tarefa_5_bdo.exception.ModelException;
import gal.san.clemente.tarefa_5_bdo.model.Directorio;

public interface IDirectorioDAO extends IDAO<Directorio, Long> {
    void insertarOrEdit(Directorio entidad) throws ModelException;
}
