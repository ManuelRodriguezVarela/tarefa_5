package gal.san.clemente.tarefa_5_bdo.main;

import gal.san.clemente.tarefa_5_bdo.controller.Controller;
import gal.san.clemente.tarefa_5_bdo.exception.ModelException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadFile {
    
    public static void main(String[] args) {
        Controller controller;
        try {
            controller = new Controller();
            controller.saveFiles();
            controller.writeFilesIsNotExist();
        } catch (ModelException ex) {
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
