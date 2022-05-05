package pojos;
// Generated 29-abr-2022 10:39:04 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Genero generated by hbm2java
 */
public class Genero  implements java.io.Serializable {


     private Integer id;
     private String nome;
     private Set<Pessoa> pessoas = new HashSet<Pessoa>(0);

    public Genero() {
    }

	
    public Genero(String nome) {
        this.nome = nome;
    }
    public Genero(String nome, Set<Pessoa> pessoas) {
       this.nome = nome;
       this.pessoas = pessoas;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Set<Pessoa> getPessoas() {
        return this.pessoas;
    }
    
    public void setPessoas(Set<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }




}

