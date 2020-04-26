package gal.san.clemente.tarefa_5_bdo.model.dao;

public interface IDAOManagerJSON extends IDAOManager {
    
    IConfigurationDAO getConfigurationDAO(String url);
    
}
