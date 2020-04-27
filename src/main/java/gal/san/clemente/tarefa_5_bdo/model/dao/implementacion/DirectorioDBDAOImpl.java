package gal.san.clemente.tarefa_5_bdo.model.dao.implementacion;

import gal.san.clemente.tarefa_5_bdo.exception.ModelException;
import gal.san.clemente.tarefa_5_bdo.model.Arquivo;
import gal.san.clemente.tarefa_5_bdo.model.Directorio;
import gal.san.clemente.tarefa_5_bdo.model.dao.IDirectorioDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DirectorioDBDAOImpl extends DAOImpl<Directorio, Long> implements IDirectorioDAO {
    
    public DirectorioDBDAOImpl(SessionFactory session) {
        super(Directorio.class, session);
    }

    @Override
    public void insertarOrEdit(Directorio entidad) throws ModelException {
        Transaction tran = null;
        Session session = null;
        Directorio directorio = null;
        try {
            //Collemos a sesión de Hibernate
            session = sessionFactory.openSession();
            
            //Comenzamos unha transacción
            tran = session.beginTransaction();
            
            TypedQuery<Directorio> query_dir = session
                .createQuery("from directorio where nomeDirectorio = :nome_directorio")
                    .setParameter("nome_directorio", entidad.getNomeDirectorio());

            if (!query_dir.getResultList().isEmpty()) {
                directorio = query_dir.getResultList().get(0);
                for(Arquivo ar_novo : entidad.getArquivos()) {
                    TypedQuery<Directorio> query_arq = session.createQuery("SELECT a FROM arquivo a where a.nomeArquivo = :nome_arquivo AND a.directorio = :directorio");
                    query_arq.setParameter("nome_arquivo", ar_novo.getNomeArquivo());
                    query_arq.setParameter("directorio", directorio);
                    if(query_arq.getResultList().isEmpty()) {
                        ar_novo.setDirectorio(directorio);
                        session.save(ar_novo);
                    } 
                }
                
            } else {
                session.save(entidad);
            }

            //Facemos un commit da transacción
            tran.commit();

        } catch (Exception e) {
            throw new ModelException("Error en BBDD " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }
    
    @Override
    public List<Directorio> obtenerTodos() throws ModelException {
        Transaction tran = null;
        Session session = null;
        List<Directorio> entidades = new ArrayList<>();
        try {
            //Collemos a sesión de Hibernate
            session = sessionFactory.openSession();
            TypedQuery<Directorio> query = session.createQuery("from directorio");

            //Comenzamos unha transacción
            tran = session.beginTransaction();

            if (query.getResultList() != null) {
                entidades = query.getResultList();
            } else {
                throw new ModelException("No hay datos");
            }

            for(Directorio entidad: entidades) {
                entidad.getArquivos();
            }

            //Facemos un commit da transacción
            tran.commit();

            return entidades;

        } catch (Exception e) {
            throw new ModelException("Error al buscar en BBDD " + e.getMessage(), e);
        }
    }
    
}
