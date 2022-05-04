package pojos;
// Generated 29-abr-2022 10:39:04 by Hibernate Tools 4.3.1



/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private Integer id;
     private Acesso acesso;
     private Funcionario funcionario;
     private String nome;
     private String senha;

    public Usuario() {
    }

	
    public Usuario(Acesso acesso, String nome, String senha) {
        this.acesso = acesso;
        this.nome = nome;
        this.senha = senha;
    }
    public Usuario(Acesso acesso, Funcionario funcionario, String nome, String senha) {
       this.acesso = acesso;
       this.funcionario = funcionario;
       this.nome = nome;
       this.senha = senha;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Acesso getAcesso() {
        return this.acesso;
    }
    
    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }
    public Funcionario getFuncionario() {
        return this.funcionario;
    }
    
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return this.senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }




}


