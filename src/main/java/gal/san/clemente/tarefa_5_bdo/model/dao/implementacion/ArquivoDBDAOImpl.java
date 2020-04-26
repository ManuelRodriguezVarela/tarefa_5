package gal.san.clemente.tarefa_5_bdo.model.dao.implementacion;

import gal.san.clemente.tarefa_5_bdo.model.Arquivo;
import gal.san.clemente.tarefa_5_bdo.model.dao.IArquivoDAO;
import org.hibernate.SessionFactory;

public class ArquivoDBDAOImpl extends DAOImpl<Arquivo, Long> implements IArquivoDAO {
    
    public ArquivoDBDAOImpl(SessionFactory session) {
        super(Arquivo.class, session);
    }
    
}
