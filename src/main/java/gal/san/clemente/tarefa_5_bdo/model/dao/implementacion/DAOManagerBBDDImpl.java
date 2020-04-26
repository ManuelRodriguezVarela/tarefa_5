package gal.san.clemente.tarefa_5_bdo.model.dao.implementacion;

import gal.san.clemente.tarefa_5_bdo.exception.ModelException;
import java.io.File;
import org.hibernate.SessionFactory;
import gal.san.clemente.tarefa_5_bdo.model.dao.IDAOManagerDB;
import gal.san.clemente.tarefa_5_bdo.model.dao.IArquivoDAO;
import gal.san.clemente.tarefa_5_bdo.model.dao.IDirectorioDAO;

public class DAOManagerBBDDImpl extends DAOManagerFactory implements IDAOManagerDB {
    
    private SessionFactory session;
    
    private File file;
    
    private IArquivoDAO arquivoDAO = null;
    
    private IDirectorioDAO directorioDAO = null;

    public DAOManagerBBDDImpl() throws ModelException {
        session = HibernateUtil.getInstance();
    }

    @Override
    public IArquivoDAO getArquivoDAO() {
        if(arquivoDAO == null) {
            arquivoDAO = new ArquivoDBDAOImpl(session);
        }
        return arquivoDAO;
    }
    
    @Override
    public IDirectorioDAO getDirectorioDAO() {
        if(directorioDAO == null) {
            directorioDAO = new DirectorioDBDAOImpl(session);
        }
        return directorioDAO;
    }

}
