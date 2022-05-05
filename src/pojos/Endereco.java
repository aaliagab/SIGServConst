package pojos;
// Generated 29-abr-2022 10:39:04 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Endereco generated by hbm2java
 */
public class Endereco  implements java.io.Serializable {


     private Integer id;
     private Bairro bairro;
     private String rua;
     private String numCasa;
     private Set<Pessoa> pessoas = new HashSet<Pessoa>(0);

    public Endereco() {
    }

	
    public Endereco(Bairro bairro, String rua) {
        this.bairro = bairro;
        this.rua = rua;
    }
    public Endereco(Bairro bairro, String rua, String numCasa, Set<Pessoa> pessoas) {
       this.bairro = bairro;
       this.rua = rua;
       this.numCasa = numCasa;
       this.pessoas = pessoas;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Bairro getBairro() {
        return this.bairro;
    }
    
    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
    public String getRua() {
        return this.rua;
    }
    
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getNumCasa() {
        return this.numCasa;
    }
    
    public void setNumCasa(String numCasa) {
        this.numCasa = numCasa;
    }
    public Set<Pessoa> getPessoas() {
        return this.pessoas;
    }
    
    public void setPessoas(Set<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }




}

