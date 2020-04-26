package gal.san.clemente.tarefa_5_bdo.model.dao.implementacion;

import gal.san.clemente.tarefa_5_bdo.model.Directorio;
import gal.san.clemente.tarefa_5_bdo.model.dao.IDirectorioDAO;
import org.hibernate.SessionFactory;

public class DirectorioDBDAOImpl extends DAOImpl<Directorio, Long> implements IDirectorioDAO {
    
    public DirectorioDBDAOImpl(SessionFactory session) {
        super(Directorio.class, session);
    }
    
}
