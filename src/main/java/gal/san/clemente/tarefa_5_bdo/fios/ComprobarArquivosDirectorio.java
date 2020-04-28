package gal.san.clemente.tarefa_5_bdo.fios;

import gal.san.clemente.tarefa_5_bdo.controller.Controller;
import gal.san.clemente.tarefa_5_bdo.exception.ModelException;
import gal.san.clemente.tarefa_5_bdo.main.ReadFile;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComprobarArquivosDirectorio extends Thread {
    private Controller controller;
	
    public ComprobarArquivosDirectorio() throws ModelException {
        this.controller = new Controller();
    }
	
    public void run() {
        while (true) {   
            try {
            	controller.saveFiles();
                Thread.sleep(20000);
            } catch (Exception ex) {
                Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
}
