package gal.san.clemente.tarefa_5_bdo.model.dao;

public interface IDAOManagerDB extends IDAOManager {
    
    IArquivoDAO getArquivoDAO();
    
    IDirectorioDAO getDirectorioDAO();
    
}
