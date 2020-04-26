package gal.san.clemente.tarefa_5_bdo.model.dao.implementacion;

import gal.san.clemente.tarefa_5_bdo.model.dao.IConfigurationDAO;
import java.io.File;
import gal.san.clemente.tarefa_5_bdo.model.dao.IDAOManagerJSON;

public class DAOManagerJSONImpl extends DAOManagerFactory implements IDAOManagerJSON {
    
    private IConfigurationDAO configurationDAO = null;
    
    public DAOManagerJSONImpl() {
        
    }
    
    @Override
    public IConfigurationDAO getConfigurationDAO(String url) {
        File file = new File(url);
        if(configurationDAO == null) {
           configurationDAO = new ConfigurationJSONDAOImpl(file);
        }
        return configurationDAO;
    }
    
}
