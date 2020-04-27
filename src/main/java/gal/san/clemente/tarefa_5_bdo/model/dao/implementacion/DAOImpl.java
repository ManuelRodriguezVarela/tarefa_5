package gal.san.clemente.tarefa_5_bdo.model.dao.implementacion;

import gal.san.clemente.tarefa_5_bdo.exception.ModelException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import gal.san.clemente.tarefa_5_bdo.model.dao.IDAO;

public class DAOImpl <T, K extends Serializable> implements IDAO<T, K>{
    
    protected SessionFactory sessionFactory;

    private Class<T> persistentClass;

    public DAOImpl(Class<T> persistentClass, SessionFactory sessionFactory) {
	this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
    }

    public DAOImpl() {
    }


    @Override
    public void insertar(T entidad) throws ModelException {
        Transaction tran = null;
        Session session = null;
        try{
            //Collemos a sesión de Hibernate
            session = sessionFactory.openSession();
            //Comenzamos unha transacción
            tran = session.beginTransaction();
            //Gardamos o equipo
            session.save(entidad);
            //Facemos un commit da transacción
            tran.commit();
            
        } catch(Exception e){
            throw new ModelException(e.getMessage());
        } finally {
            //cerramos sesión
            session.close();
        }
    }

    @Override
    public void modificar(T entidad) throws ModelException {
        Transaction tran = null;
        Session session = null;
        try{
            //Collemos a sesión de Hibernate
            session = sessionFactory.openSession();
            //Comenzamos unha transacción
            tran = session.beginTransaction();
            //Gardamos o equipo
            session.update(entidad);
            //Facemos un commit da transacción
            tran.commit();
            
        } catch(Exception e){
            throw new ModelException(e.getMessage());
        } finally {
            //cerramos sesión
            session.close();
        }
    }

    @Override
    public void eliminar(K id) throws ModelException {
        Transaction tran = null;
        Session session = null;
        try{
            //Collemos a sesión de Hibernate
            session = sessionFactory.openSession();
            //Comenzamos unha transacción
            tran = session.beginTransaction();
            T entidad = (T) session.load(persistentClass, id);
            session.delete(entidad);

            //Facemos un commit da transacción
            tran.commit();
            
        } catch(Exception e){
            throw new ModelException("La entidad que intenta borrar no existe: " + id + " - " + e.getMessage());
        } finally {
            //cerramos sesión
            session.close();
        }
    }

    @Override
    public List<T> obtenerTodos() throws ModelException {
        Transaction tran = null;
        Session session = null;

        List<T> entidades = new ArrayList<>();
        try {
             //Collemos a sesión de Hibernate
            session = sessionFactory.openSession();
            TypedQuery<T> query = (TypedQuery<T>) session.createQuery("from " + persistentClass.getSimpleName());
            //Comenzamos unha transacción
            tran = session.beginTransaction();
            if (query.getResultList() != null) {
                entidades = query.getResultList();
            } else {
                throw new ModelException("No hay datos");
            }

            //Facemos un commit da transacción
            tran.commit();
         
            return entidades;

        } catch (Exception e) {
            throw new ModelException("Error al buscar en BBDD " + e.getMessage(), e);
        } finally {
            //cerramos sesión
            session.close();
        }
    }

    @Override
    public T obtener(K id) throws ModelException {
        Transaction tran = null;
        Session session = null;
        T entidad = null;
        try {
             //Collemos a sesión de Hibernate
            session = sessionFactory.openSession();
            //Comenzamos unha transacción
            tran = session.beginTransaction();
            entidad = (T) session.get(persistentClass, id);
            //Facemos un commit da transacción
            tran.commit();
         
            return entidad;

        } catch (Exception e) {
            throw new ModelException("Error al buscar en BBDD " + e.getMessage(), e);
        } finally {
            //cerramos sesión
            session.close();
        }
    }
    
}
