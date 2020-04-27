package gal.san.clemente.tarefa_5_bdo.controller;

import gal.san.clemente.tarefa_5_bdo.exception.ModelException;
import gal.san.clemente.tarefa_5_bdo.model.Arquivo;
import gal.san.clemente.tarefa_5_bdo.model.Directorio;
import gal.san.clemente.tarefa_5_bdo.model.dao.IConfigurationDAO;
import gal.san.clemente.tarefa_5_bdo.model.dao.IDAOManagerDB;
import gal.san.clemente.tarefa_5_bdo.model.dao.IDAOManagerJSON;
import gal.san.clemente.tarefa_5_bdo.model.dao.implementacion.DAOManagerFactory;
import gal.san.clemente.tarefa_5_bdo.model.dao.utils.FileUtils;
import gal.san.clemente.tarefa_5_bdo.model.dao.utils.configuration.App;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    private static final String BBDD = "bbdd";
    private static final String JSON = "json";
    private static final String CONFIGURATION = "./configuration.json";
    private IDAOManagerDB manager_bbdd;
    private IDAOManagerJSON manager_json;
    private IConfigurationDAO daoConfiguration;
    private App app;
    
    public Controller() throws ModelException {
        manager_bbdd = (IDAOManagerDB) DAOManagerFactory.getDAOManager(BBDD);
        manager_json = (IDAOManagerJSON) DAOManagerFactory.getDAOManager(JSON);
        daoConfiguration = manager_json.getConfigurationDAO(CONFIGURATION);
        app = daoConfiguration.obtener(Long.valueOf(1)).getApp();
    }
                  
    public void saveFiles() throws ModelException {
        List<File> listaFicheros = new ArrayList<>();
        Path path = Paths.get(app.getDirectory());
        
        save(FileUtils.getFileNames(listaFicheros, path));
    }
    
    public void writeFilesIsNotExist() throws ModelException {
        List<Directorio> directorios = manager_bbdd.getDirectorioDAO().obtenerTodos();
        for (Directorio d : directorios) {
            for(Arquivo a : d.getArquivos()) {
                String ruta = d.getNomeDirectorio().concat(a.getNomeArquivo());
                File file = new File(ruta);
                if (!file.exists()){
                    byte[] file_bbdd = a.getArquivo();
                    FileUtils.ArrayByteToFile(ruta, file_bbdd);
                }
            }
        }
    }
    
    private void save(List<File> listaFicheros) throws ModelException {
        for(File f : listaFicheros) {
            String dir = f.getPath().substring(0, f.getPath().lastIndexOf(File.separator) + 1);
            String dirBefore = "";
            Directorio directorio = null;
            if(!dir.equals(dirBefore)) {
                dirBefore = dir;
                directorio = new Directorio();
                directorio.setNomeDirectorio(dirBefore);
            }
            if(directorio != null) {
                addArquivoToDirectorio(directorio, f);
                manager_bbdd.getDirectorioDAO().insertarOrEdit(directorio);
            }
        }
    }
            
    private Directorio addArquivoToDirectorio(Directorio directorio, File f) throws ModelException {
        Arquivo arquivo = new Arquivo();
        arquivo.setNomeArquivo(f.getName());
        arquivo.setArquivo(FileUtils.fileToArrayByte(f));
        directorio.addArquivo(arquivo);
        return directorio;
    }     
            
}
