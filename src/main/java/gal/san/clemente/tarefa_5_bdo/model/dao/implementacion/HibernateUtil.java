package gal.san.clemente.tarefa_5_bdo.model.dao.implementacion;

import gal.san.clemente.tarefa_5_bdo.exception.ModelException;
import gal.san.clemente.tarefa_5_bdo.model.Arquivo;
import gal.san.clemente.tarefa_5_bdo.model.Directorio;
import gal.san.clemente.tarefa_5_bdo.model.dao.IConfigurationDAO;
import gal.san.clemente.tarefa_5_bdo.model.dao.IDAOManagerJSON;
import gal.san.clemente.tarefa_5_bdo.model.dao.utils.configuration.ConfigurationBBDD;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

//configuración de hibernate
//implementamos patrón singleton para devolver una instancia
public class HibernateUtil {
   
    private static SessionFactory sessionFactory = null;
    private Configuration conf;
    private Properties settings;
    private static HibernateUtil instance;
    
    private HibernateUtil() throws ModelException {
        conf = new Configuration();
        settings = new Properties();
        sessionFactory = getSessionFactory();
    }
    
    private static Object[] annotaticonClass = new Object[]{
        Arquivo.class, Directorio.class
    };
    
    public static SessionFactory getInstance() throws ModelException{
        if(sessionFactory == null){
            new HibernateUtil();
        }
        return sessionFactory;
    }
    
    //Este método devolve a sesión para poder facer operacións coa base de datos
    private SessionFactory getSessionFactory() throws ModelException{
        
        //Se a sesion non se configurou, creamolo
        if(sessionFactory == null){
            try{
                addClassAnotated();
                setProperies();
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
                sessionFactory = conf.buildSessionFactory(serviceRegistry);
            }catch(HibernateException e){
                throw new ModelException(e.getMessage());
            }
        }
        return sessionFactory;
    }
    
    private void addClassAnotated() {
        //Engaidmos aquelas clases nas que queremos facer persistencia
        for(Object clase : annotaticonClass) {
            conf.addAnnotatedClass((Class) clase);
        }
    }
    
    private void setProperies() throws ModelException {
        ConfigurationBBDD propertiesConf = getPropertiesBBDD();
        String url = "jdbc:postgresql://" + propertiesConf.getDbConnection().getAddress()  
                + "/" + propertiesConf.getDbConnection().getName();
        //Engadimos as propiedades
        //Indicamos o conector da base de datos que vamos a usar
        settings.put(Environment.DRIVER, propertiesConf.getHibernate().getDriver());
 
        //Indicamos a localización da base de datos que vamos a utilizar
        settings.put(Environment.URL,url);

        //Indicamos o usuario da base de datos con cal nos vamos conectar e o seu contrasinal
        settings.put(Environment.USER,propertiesConf.getDbConnection().getUser());
        settings.put(Environment.PASS,propertiesConf.getDbConnection().getPassword());

        //Indicamos o dialecto que ten que usar Hibernate 
        settings.put(Environment.DIALECT,propertiesConf.getHibernate().getDialect());

        //Indicamos que se as táboas todas se borren e se volvan crear
        settings.put(Environment.HBM2DDL_AUTO, propertiesConf.getHibernate().getHBM2DDL_AUTO());

        //Indicamos que se mostre as operacións SQL que Hibernate leva a cabo
        settings.put(Environment.SHOW_SQL, propertiesConf.getHibernate().getSHOW_SQL());
        conf.setProperties(settings);
    }
    
    private ConfigurationBBDD getPropertiesBBDD() throws ModelException {
        IDAOManagerJSON manager = (IDAOManagerJSON) DAOManagerFactory.getDAOManager("json");
        IConfigurationDAO DAOConfiguration = manager.getConfigurationDAO("./configuration.json");
        return DAOConfiguration.obtener(Long.valueOf(1));
    }
     
}
