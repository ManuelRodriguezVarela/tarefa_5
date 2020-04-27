package gal.san.clemente.tarefa_5_bdo.model.dao.utils;

import gal.san.clemente.tarefa_5_bdo.exception.ModelException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtils {
    public static void writeFile(String data, String url) {
        //Vamos comezar declarando o ficheiro
        File arquivo = new File(url);

        try {

            //Creamos un fluxo de saida para o arquivo
            FileOutputStream fileOut = new FileOutputStream(arquivo);
            ObjectOutputStream fluxoDatos = new ObjectOutputStream(fileOut);
            
            fluxoDatos.writeObject(data);
         
            //Temos que cerrar sempre o ficheiro
            fluxoDatos.close();
            
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public static String getFileName(String name, String extension) {
       return "./" + Instant.now().toEpochMilli() + "_" + name + "." + extension;
    }
    
    public static List<File> getFileNames(List<File> fileNames, Path dir) {
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path path : stream) {
                if(path.toFile().isDirectory()) {
                    getFileNames(fileNames, path);
                } else {
                    fileNames.add(path.toFile());
                }
            }
        } catch(IOException e) {
           Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, e);
        }
        return fileNames;
    }
    
    public static byte[] fileToArrayByte(File file) throws ModelException {
        byte[] ArrayByteData = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(ArrayByteData);
            fileInputStream.close();
        } catch (IOException e) {
            throw new ModelException("Error crear array de bytes " + e.getMessage(), e);
        }
        return ArrayByteData;
    }
    
    public static void ArrayByteToFile(String dir, byte [] arrayByte) throws ModelException {
        try{
            FileOutputStream fos = new FileOutputStream(dir); 
            fos.write(arrayByte);
            fos.close();
        }catch(IOException e){
            throw new ModelException("Error crear archivo " + e.getMessage(), e);
        }
    }
    
}
