package pojos;
// Generated 29-abr-2022 10:39:04 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Bairro generated by hbm2java
 */
public class Bairro  implements java.io.Serializable {


     private Integer id;
     private Municipio municipio;
     private String nome;
     private Set<Endereco> enderecos = new HashSet<Endereco>(0);

    public Bairro() {
    }

	
    public Bairro(Municipio municipio, String nome) {
        this.municipio = municipio;
        this.nome = nome;
    }
    public Bairro(Municipio municipio, String nome, Set<Endereco> enderecos) {
       this.municipio = municipio;
       this.nome = nome;
       this.enderecos = enderecos;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Municipio getMunicipio() {
        return this.municipio;
    }
    
    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Set<Endereco> getEnderecos() {
        return this.enderecos;
    }
    
    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }




}


