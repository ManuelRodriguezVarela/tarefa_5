package gal.san.clemente.tarefa_5_bdo.model.dao.implementacion;

import gal.san.clemente.tarefa_5_bdo.exception.ModelException;
import gal.san.clemente.tarefa_5_bdo.model.dao.IDAOManager;

public class DAOManagerFactory {
    
    public static IDAOManager getDAOManager(String type) throws ModelException {
        if ( type.equals("json") )
            return new DAOManagerJSONImpl();
        else if ( type.equals("bbdd") )
            return new DAOManagerBBDDImpl();
        return null;
    }
    
}
