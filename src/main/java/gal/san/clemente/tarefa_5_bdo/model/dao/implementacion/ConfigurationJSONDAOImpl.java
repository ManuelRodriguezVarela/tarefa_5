package gal.san.clemente.tarefa_5_bdo.model.dao.implementacion;

import gal.san.clemente.tarefa_5_bdo.model.dao.IConfigurationDAO;
import java.io.File;
import gal.san.clemente.tarefa_5_bdo.model.dao.utils.configuration.ConfigurationBBDD;

public class ConfigurationJSONDAOImpl extends DAOImplJson<ConfigurationBBDD, Long> implements IConfigurationDAO {
    
    public ConfigurationJSONDAOImpl(File file) {
        super(ConfigurationBBDD.class, file);
    }

    
}
