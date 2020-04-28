package gal.san.clemente.tarefa_5_bdo.main;

import gal.san.clemente.tarefa_5_bdo.controller.Controller;
import gal.san.clemente.tarefa_5_bdo.exception.ModelException;
import gal.san.clemente.tarefa_5_bdo.fios.ComprobarArquivosDirectorio;
import gal.san.clemente.tarefa_5_bdo.fios.GrabarArquivoDirectorioPorNotificacions;
import gal.san.clemente.tarefa_5_bdo.model.dao.implementacion.JDBCUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadFile {
    
    public static void main(String[] args) {
        Controller controller;
        try {
            controller = new Controller();
            controller.saveFiles();
            controller.writeFilesIsNotExist();
            ComprobarArquivosDirectorio comprobarArquivos = new ComprobarArquivosDirectorio();
            GrabarArquivoDirectorioPorNotificacions grabarArquivoDirectorioPorNotificacions = new GrabarArquivoDirectorioPorNotificacions();
            comprobarArquivos.run();
            grabarArquivoDirectorioPorNotificacions.run();
            JDBCUtils.cerrarConexion();
        } catch (ModelException ex) {
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
