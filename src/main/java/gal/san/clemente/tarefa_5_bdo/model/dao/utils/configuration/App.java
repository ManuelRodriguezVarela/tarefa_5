package gal.san.clemente.tarefa_5_bdo.model.dao.utils.configuration;

public class App {
    
    private String directory;

    public App(String directory) {
        this.directory = directory;
    }
    
    public App() {}

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
   
}
