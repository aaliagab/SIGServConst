/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import pojos.SolicitudeBilhete;


/**
 *
 * @author 
 */
public class SolicitudeBilheteReport {
    private String nome;
    private String tipo;
    private int quantidade;
    private double valor;

    public SolicitudeBilheteReport(SolicitudeBilhete obj) {
        this.nome = obj.getBilhete().getNome();
        this.tipo = obj.getBilhete().getTipo().getNome();
        this.quantidade = obj.getQuantidade();
        this.valor = obj.getBilhete().getValor();
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }
    
    
    
}
