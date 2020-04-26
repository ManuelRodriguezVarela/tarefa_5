package gal.san.clemente.tarefa_5_bdo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "directorio")
@Table(name = "DIRECTORIOS")
public class Directorio implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_DIRECTORIO")
    private Long id;
    
    @Column(name = "NOME_DIRECTORIO")
    private String nomeDirectorio;
    
    @OneToMany(mappedBy="directorio",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<Arquivo> arquivos = new ArrayList<>();

    public Directorio(Long id, String nomeDirectorio, List<Arquivo> arquivos) {
        this.id = id;
        this.nomeDirectorio = nomeDirectorio;
        this.arquivos = arquivos;
    }

    public Directorio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDirectorio() {
        return nomeDirectorio;
    }

    public void setNomeDirectorio(String nomeDirectorio) {
        this.nomeDirectorio = nomeDirectorio;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }
    
    public void addArquivo(Arquivo arquivo) {
        this.getArquivos().add(arquivo);
        arquivo.setDirectorio(this);
    }
    
    public void removeArquivo(Arquivo arquivo) {
        this.getArquivos().remove(arquivo);
        arquivo.setDirectorio(null);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directorio directorio = (Directorio) o;
        return Objects.equals(id, directorio.id);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Directorio{" + "id=" + id + ", nomeDirectorio=" + nomeDirectorio + '}';
    }

}
