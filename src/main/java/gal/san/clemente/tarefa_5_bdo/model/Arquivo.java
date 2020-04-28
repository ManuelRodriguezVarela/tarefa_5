package gal.san.clemente.tarefa_5_bdo.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "arquivo")
@Table(name = "ARQUIVOS")
public class Arquivo implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_ARQUIVO")
    private Long id;
    
    @Column(name = "NOME_ARQUIVO")
    private String nomeArquivo;
    
    @Column(name = "ARQUIVO", length = 100000)
    private byte[] arquivo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="DIRECTORIO", nullable = true)
    private Directorio directorio;

    public Arquivo(Long id, String nomeArquivo, byte[] arquivo, Directorio directorio) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.arquivo = arquivo;
        this.directorio = directorio;
    }

    public Arquivo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public Directorio getDirectorio() {
        return directorio;
    }

    public void setDirectorio(Directorio directorio) {
        this.directorio = directorio;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arquivo arquivo = (Arquivo) o;
        return Objects.equals(id, arquivo.id);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Arquivo{" + "id=" + id + ", nomeArquivo=" + nomeArquivo + ", directorio=" + directorio + '}';
    }

}
