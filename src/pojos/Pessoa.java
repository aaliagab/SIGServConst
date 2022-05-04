package pojos;
// Generated 29-abr-2022 10:39:04 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Pessoa generated by hbm2java
 */
public class Pessoa  implements java.io.Serializable {


     private Integer id;
     private Endereco endereco;
     private Genero genero;
     private String nome;
     private String sobrenome;
     private Date dataIngreso;
     private String email;
     private String telefone;
     private Cliente cliente;
     private Funcionario funcionario;

    public Pessoa() {
    }

	
    public Pessoa(Endereco endereco, Genero genero, String nome, String sobrenome, Date dataIngreso, String telefone) {
        this.endereco = endereco;
        this.genero = genero;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataIngreso = dataIngreso;
        this.telefone = telefone;
    }
    public Pessoa(Endereco endereco, Genero genero, String nome, String sobrenome, Date dataIngreso, String email, String telefone, Cliente cliente, Funcionario funcionario) {
       this.endereco = endereco;
       this.genero = genero;
       this.nome = nome;
       this.sobrenome = sobrenome;
       this.dataIngreso = dataIngreso;
       this.email = email;
       this.telefone = telefone;
       this.cliente = cliente;
       this.funcionario = funcionario;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Endereco getEndereco() {
        return this.endereco;
    }
    
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public Genero getGenero() {
        return this.genero;
    }
    
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobrenome() {
        return this.sobrenome;
    }
    
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public Date getDataIngreso() {
        return this.dataIngreso;
    }
    
    public void setDataIngreso(Date dataIngreso) {
        this.dataIngreso = dataIngreso;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return this.telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Funcionario getFuncionario() {
        return this.funcionario;
    }
    
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }




}


