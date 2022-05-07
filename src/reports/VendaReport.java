/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import java.util.Date;
import pojos.Venda;

/**
 *
 * @author
 */
public class VendaReport {

    private String cliente;
    private String funcionario;
    private Date data;
    private double valor;

    public VendaReport(Venda v) {
        this.cliente = v.getSolicitude().getCliente().getPessoa().getNome();
        this.funcionario = v.getFuncionario().getPessoa().getNome();
        this.data = v.getSolicitude().getData();
        this.valor = v.getTotal();
    }

    public String getCliente() {
        return cliente;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public Date getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }
    
    
    
    
}
