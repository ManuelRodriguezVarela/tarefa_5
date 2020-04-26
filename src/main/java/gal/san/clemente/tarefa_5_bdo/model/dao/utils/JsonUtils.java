package gal.san.clemente.tarefa_5_bdo.model.dao.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;

public class JsonUtils implements Serializable {
    public static String toJson(Object o) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String data = gson.toJson(o);
        return data;
    } 
}
