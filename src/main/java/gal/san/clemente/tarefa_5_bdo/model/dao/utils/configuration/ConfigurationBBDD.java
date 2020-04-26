package gal.san.clemente.tarefa_5_bdo.model.dao.utils.configuration;

public class ConfigurationBBDD {
    
    private ConnectionConf dbConnection;
    
    private HibernateConfiguration hibernate;
    
    private App app;

    public ConfigurationBBDD(ConnectionConf dbConnection, HibernateConfiguration hibernate, App app) {
        this.dbConnection = dbConnection;
        this.hibernate = hibernate;
        this.app = app;
    }

    public ConfigurationBBDD() {
    }

    public ConnectionConf getDbConnection() {
        return dbConnection;
    }

    public void setDbConnection(ConnectionConf dbConnection) {
        this.dbConnection = dbConnection;
    }

    public HibernateConfiguration getHibernate() {
        return hibernate;
    }

    public void setHibernate(HibernateConfiguration hibernate) {
        this.hibernate = hibernate;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }
     
}
