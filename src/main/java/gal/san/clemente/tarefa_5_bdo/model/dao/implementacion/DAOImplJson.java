package gal.san.clemente.tarefa_5_bdo.model.dao.implementacion;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import gal.san.clemente.tarefa_5_bdo.exception.ModelException;
import gal.san.clemente.tarefa_5_bdo.model.dao.utils.ModelError;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import gal.san.clemente.tarefa_5_bdo.model.dao.IDAO;

public class DAOImplJson <T, K extends Serializable> implements IDAO<T, K>{
    
    protected File file;

    private Class<T> persistentClass;

    public DAOImplJson(Class<T> persistentClass, File file) {
	this.persistentClass = persistentClass;
        this.file = file;
    }

    public DAOImplJson() {
    } 
    
    @Override
    public List<T> obtenerTodos() throws ModelException {
        T entidad;
        
        List<T> entidades = new ArrayList<>();
        
        BufferedReader buferEntrada;
        
        try {
            //Creamos un fluxo de entrada para o arquivo
            FileReader fluxoDatos = new FileReader(file);
            //Creamos o bufer de entrada
            buferEntrada = new BufferedReader(fluxoDatos);
            //Imos lendo linea a linea
            StringBuilder jsonBuilder = new StringBuilder();
            String linea;
            while ((linea=buferEntrada.readLine()) != null) {
                jsonBuilder.append(linea).append("\n");
            }
            JsonParser parser = new JsonParser();
            //Construimos o String con todalas lineas lidas
            String json = jsonBuilder.toString();
            JsonArray gsonArr = parser.parse(json).getAsJsonArray();
            
            for (JsonElement obj : gsonArr) {
                //Pasamos o json a clase ca cal se corresponde
                Gson gson = new Gson();
                entidad = gson.fromJson(obj, persistentClass);
                entidades.add(entidad);
                 //Temos que cerrar sempre o ficheiro
            }
            
            buferEntrada.close();
            
            
        } catch (IOException ex) {
            throw new ModelException(ModelError.ERROR_GET_ALL_PROVINCIAS, ex);
        }
        
        return entidades;
    }

    @Override
    public void insertar(T entidad) throws ModelException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(T entidad) throws ModelException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(K entidadId) throws ModelException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T obtener(K id) throws ModelException {
        Gson gson = new Gson();
        T object =  null;
        try {
            object = gson.fromJson(new FileReader(file), persistentClass);
        } catch(Exception e) {
            throw new ModelException(e.getMessage(), e);
        }
        return object;
    }
    
    
}
