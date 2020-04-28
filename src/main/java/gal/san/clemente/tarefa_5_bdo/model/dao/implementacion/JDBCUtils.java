package gal.san.clemente.tarefa_5_bdo.model.dao.implementacion;

import gal.san.clemente.tarefa_5_bdo.exception.ModelException;
import gal.san.clemente.tarefa_5_bdo.model.dao.utils.configuration.ConfigurationBBDD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static final String JDBC = "jdbc:postgresql://";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static Connection conexion = null;
    private  Properties props;
    private ConfigurationBBDD propertiesConf;

    private JDBCUtils() throws ModelException {
        propertiesConf = ConfigurationBBDD.getPropertiesBBDD();
        props = new Properties();
        props.setProperty(USER, propertiesConf.getDbConnection().getUser());
        props.setProperty(PASSWORD, propertiesConf.getDbConnection().getPassword());
        String url = JDBC + propertiesConf.getDbConnection().getAddress()  
                + "/" + propertiesConf.getDbConnection().getName();
        try {
            //creando conexión a través de url
            conexion =  DriverManager.getConnection(url,props);
        } catch (SQLException ex) {
            throw new ModelException("fallo conexión a bbdd" + ex.getMessage(), ex);
        }
    }
    
    public static Connection getConexion() throws ModelException {
        if(conexion == null) {
            new JDBCUtils();
        }
        return conexion;
    }
    
    public static void cerrarConexion() throws ModelException {
        if(conexion != null) {
            try {
                conexion.close();
            } catch (Exception ex) {
                throw new ModelException("fallo conexión a bbdd" + ex.getMessage(), ex);
            }
        }
            
    }
    
}
