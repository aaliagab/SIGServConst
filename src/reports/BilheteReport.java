/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import java.util.Date;
import pojos.Bilhete;

/**
 *
 * @author 
 */
public class BilheteReport {
    private String nome;
    private String tipo;
    private Date data;
    private double iva;
    private double valor;
    private int quantidade;

    public BilheteReport(Bilhete p) {
        this.nome = p.getNome();
        this.tipo = p.getTipo().getNome();
        this.data = p.getDataCriacao();
        this.iva = p.getIva().getPorcetagem();
        this.valor = p.getValor();
        this.quantidade = p.getQuantidade();
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public Date getData() {
        return data;
    }

    public double getIva() {
        return iva;
    }

    public double getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

   

    
    
    
    
}
