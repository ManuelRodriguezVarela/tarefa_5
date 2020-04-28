package gal.san.clemente.tarefa_5_bdo.model.dao.utils.configuration;

import gal.san.clemente.tarefa_5_bdo.exception.ModelException;
import gal.san.clemente.tarefa_5_bdo.model.dao.IConfigurationDAO;
import gal.san.clemente.tarefa_5_bdo.model.dao.IDAOManagerJSON;
import gal.san.clemente.tarefa_5_bdo.model.dao.implementacion.DAOManagerFactory;

public class ConfigurationBBDD {
    
    private ConnectionConf dbConnection;
    
    private HibernateConfiguration hibernate;
    
    private App app;

    private ConfigurationBBDD(ConnectionConf dbConnection, HibernateConfiguration hibernate, App app) {
        this.dbConnection = dbConnection;
        this.hibernate = hibernate;
        this.app = app;
    }
    
    public ConnectionConf getDbConnection() {
        return dbConnection;
    }

    private void setDbConnection(ConnectionConf dbConnection) {
        this.dbConnection = dbConnection;
    }

    public HibernateConfiguration getHibernate() {
        return hibernate;
    }

    private void setHibernate(HibernateConfiguration hibernate) {
        this.hibernate = hibernate;
    }

    public App getApp() {
        return app;
    }

    private void setApp(App app) {
        this.app = app;
    }
    
    public static ConfigurationBBDD getPropertiesBBDD() throws ModelException {
        IDAOManagerJSON manager = (IDAOManagerJSON) DAOManagerFactory.getDAOManager("json");
        IConfigurationDAO DAOConfiguration = manager.getConfigurationDAO("./configuration.json");
        return DAOConfiguration.obtener(Long.valueOf(1));
    }
}
