package gal.san.clemente.tarefa_5_bdo.fios;

import gal.san.clemente.tarefa_5_bdo.controller.Controller;
import gal.san.clemente.tarefa_5_bdo.exception.ModelException;
import gal.san.clemente.tarefa_5_bdo.main.ReadFile;
import gal.san.clemente.tarefa_5_bdo.model.dao.IDAOManagerDB;
import gal.san.clemente.tarefa_5_bdo.model.dao.implementacion.DAOManagerFactory;
import gal.san.clemente.tarefa_5_bdo.model.dao.implementacion.JDBCUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.PGConnection;
import org.postgresql.PGNotification;

//problemas con hibernate https://stackoverflow.com/questions/24423142/hibernate-postgresql-notify-functionality

public class GrabarArquivoDirectorioPorNotificacions extends Thread {
    private Controller controller;
    private Connection conexion;
    private PGConnection conexionPG;

    public GrabarArquivoDirectorioPorNotificacions() throws ModelException {
        try {
            this.controller = new Controller();
            controller.getManager_bbdd().getDirectorioDAO().createFunctionNotify();
            controller.getManager_bbdd().getDirectorioDAO().createTriggerNotify();
            conexion = JDBCUtils.getConexion();
            conexionPG = conexion.unwrap(PGConnection.class);
        } catch (SQLException ex) {
            throw new ModelException("Error conexión postgres" + ex.getMessage(), ex);
        }
    }
    
    public void run() {
        try {
            while (true) {
                PGNotification notificacions[] = conexionPG.getNotifications();
                if (notificacions != null) {
                    for (int i=0; i < notificacions.length; i++) {
                        if (notificacions[i].getName().equals("aviso_novo_arquivo")) {
                            controller.writeFilesIsNotExist();
                        } 
                    }                  
                }

                Thread.sleep(2000);
            }
        } catch (InterruptedException | SQLException | ModelException ex){
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
