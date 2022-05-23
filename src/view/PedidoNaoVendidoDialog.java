/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Toast;
import dao.BussinessException;
import dao.SolicitudeDAOImplement;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import pojos.Solicitude;
import pojos.EstadoSolicitude;
import pojos.MetodoPagamento;
import pojos.SolicitudeBilhete;
import pojos.Venda;
import reports.SolicitudeBilheteReport;

/**
 *
 * @author
 */
public class PedidoNaoVendidoDialog extends javax.swing.JDialog {

    /**
     * Creates new form ClienteDialog
     */
    private final String busca1 = "Busca por código",
            busca2 = "Busca por número";
    Main pai;
    Toast msg;
    List<Solicitude> list_atual;
    List<EstadoSolicitude> estados;
    List<MetodoPagamento> metodos;
    SolicitudeDAOImplement daoObject;

    public PedidoNaoVendidoDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pai = (Main) parent;
        daoObject = pai.control.getSolicitudeDAO();
        this.setLocationRelativeTo(null);
        //Atualizar tabela
        updateJTable();
        AutoCompleteDecorator.decorate(comboEstado);
        preecherCombo();

        String toolTip = "Pedido";
        trocarBtn.setToolTipText("Trocar estado");
        vendaBtn.setToolTipText("Vender " + toolTip);
    }

    public void addRowTableModel(DefaultTableModel model, Solicitude obj) {
        model.addRow(new Object[]{
            obj.getNum(),
            obj.getCliente().getPessoa().getNome()
            + (obj.getCliente().getPessoa().getSobrenome() == null ? "" : " " + obj.getCliente().getPessoa().getSobrenome()),
            obj.getData(),
            obj.getEstadoSolicitude().getNome(),
            valor(obj)
        });
    }

    public float valor(Solicitude obj) {
        float valor = 0;
        for (SolicitudeBilhete solserv : obj.getSolicitudeBilhetes()) {
            valor += solserv.getQuantidade() * solserv.getBilhete().getValor();
        }
        return valor;
    }

    public void printPedido(Solicitude sol) {
        List<SolicitudeBilheteReport> objs = new ArrayList<>();
        double total = 0;
        for (SolicitudeBilhete produtos_venda : sol.getSolicitudeBilhetes()) {
            objs.add(new SolicitudeBilheteReport(produtos_venda));
            total += produtos_venda.getQuantidade() * produtos_venda.getBilhete().getValor();
        }
        try {
            JasperReport reporte = null;
            URL path = this.getClass().getResource("/reports/pedido_report.jasper");
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("logo", this.getClass().getResource("/resource/logo.jpeg").toString());
            parametros.put("total", total);
            parametros.put("numero", "VENDIDO COM O NÚMERO " + sol.getNum());
            parametros.put("funcionario", pai.getUsuario_login().getFuncionario() == null ? "" : pai.getUsuario_login().getFuncionario().getPessoa().getNome().toUpperCase());
            parametros.put("cliente", sol.getCliente().getPessoa().getNome().toUpperCase());
            reporte = (JasperReport) JRLoader.loadObject(path);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(objs));
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setVisible(true);
            jviewer.setTitle("pedido_report");

        } catch (Exception e) {
            msg = new Toast(e.getMessage(), 2000);
            msg.showToast();
        }
    }

    public void preecherCombo() {
        try {
            List<EstadoSolicitude> lista = pai.control.getEstadoSolicitudeDAO().findAll();
            estados = new ArrayList<>();

            for (EstadoSolicitude obj : lista) {
                if (!obj.getNome().equals("Realizado")) {
                    estados.add(obj);
                    comboEstado.addItem(obj.getNome());
                } else {
//                    estados.remove(obj);
                }
            }
            metodos = pai.control.getMetodoPagamentoDAO().findAll();
            metodos.forEach((p) -> {
                comboMetodo.addItem(p.getNome());
            });
        } catch (BussinessException ex) {
            pai.control.messageErroBussiness(ex);
        }
    }

    public void updateJTable() {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        try {
            List<Solicitude> list = daoObject.findAll();
            list_atual = new ArrayList<>();
            for (Solicitude obj : list) {
                if (!obj.getEstadoSolicitude().getNome().equals("Realizado")) {
                    addRowTableModel(model, obj);
                    list_atual.add(obj);
                }
            }
        } catch (BussinessException ex) {
            pai.control.messageErroBussiness(ex);
        }
    }

    public void updateJTableBusca(List<Solicitude> list) {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        for (Solicitude obj : list) {
            addRowTableModel(model, obj);
        }
    }

    public void trocarEstado(Solicitude obj) {
        try {
            obj.setEstadoSolicitude(estados.get(comboEstado.getSelectedIndex()));
            daoObject.update(obj);
            updateJTable();
            pai.control.messageOperacaoSucesso();
        } catch (BussinessException ex) {
            pai.control.messageErroBussiness(ex);
        }
    }

    public void focusGained(JTextField jtf, String field) {
        if (jtf.getText().equals(field)) {
            jtf.setText("");
        }
        jtf.setForeground(Color.BLACK);
    }

    public void focusLost(JTextField jtf, String field) {
        if (jtf.getText().equals("")) {
            jtf.setText(field);
        }
        if (jtf.getText().equals(field)) {
            jtf.setForeground(new Color(153, 153, 153));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        trocarBtn = new javax.swing.JButton();
        busca_nome = new javax.swing.JTextField();
        vendaBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        comboMetodo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GERENCIAR PEDIDOS NÃO VENDIDOS");

        tabela.setAutoCreateRowSorter(true);
        tabela.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número de Pedido", "Cliente", "Data", "Estado", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        trocarBtn.setText("Trocar de Estado");
        trocarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trocarBtnActionPerformed(evt);
            }
        });

        busca_nome.setForeground(new java.awt.Color(153, 153, 153));
        busca_nome.setText("Busca por número");
        busca_nome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                busca_nomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                busca_nomeFocusLost(evt);
            }
        });
        busca_nome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busca_nomeKeyPressed(evt);
            }
        });

        vendaBtn.setText("Fazer venda");
        vendaBtn.setToolTipText("Actualizar dados do funcionário");
        vendaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        vendaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendaBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Estado:");

        jLabel3.setText("Método de Pagamento:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(busca_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(trocarBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(vendaBtn)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(busca_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(comboMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trocarBtn)
                    .addComponent(vendaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void busca_nomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_busca_nomeFocusGained
        focusGained(busca_nome, busca2);
    }//GEN-LAST:event_busca_nomeFocusGained

    private void busca_nomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_busca_nomeFocusLost
        focusLost(busca_nome, busca2);
    }//GEN-LAST:event_busca_nomeFocusLost

    private void busca_nomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busca_nomeKeyPressed
        List<Solicitude> list = new ArrayList<>();
        updateJTable();
        boolean flag = false;
        for (Solicitude list1 : list_atual) {
            if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE
                    && !list1.getNum().toUpperCase().contains(
                            (busca_nome.getText().substring(0, busca_nome.getText().length() - 1)).toUpperCase()
                    )) {
                flag = true;
            } else if (evt.getKeyCode() != KeyEvent.VK_BACK_SPACE
                    && !list1.getNum().toUpperCase().contains(
                            (busca_nome.getText() + evt.getKeyChar()).toUpperCase()
                    )) {
                flag = true;
            } else {
                list.add(list1);
            }
        }
        list_atual = list;
        updateJTableBusca(list);
    }//GEN-LAST:event_busca_nomeKeyPressed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        Solicitude obj = list_atual.get(tabela.getSelectedRow());
        comboEstado.setSelectedItem(obj.getEstadoSolicitude().getNome());
    }//GEN-LAST:event_tabelaMouseClicked

    private void vendaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendaBtnActionPerformed
        try {
            if (pai.control.getMetodoPagamentoDAO().findAll().size() == 0) {
                msg = new Toast("Método de pagamento deve ser inserido antes", 2000);
                msg.showToast();
            } else {
                int opcao = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja modificar este registro?",
                        "CONFIRMAR A MODIFICAÇÃO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (opcao == JOptionPane.YES_OPTION) {
                    int[] rows = tabela.getSelectedRows();
                    if (rows.length > 0) {
                        if (rows.length == 1) {
                            Solicitude obj = list_atual.get(rows[0]);
                            Venda v = new Venda(pai.getUsuario_login().getFuncionario(),
                                    metodos.get(comboMetodo.getSelectedIndex()), obj);
                            v.setTotal(valor(obj));
                            pai.control.getVendaDAO().save(v);
                            obj.setEstadoSolicitude(pai.control.getEstadoSolicitudeDAO().getByNome("Realizado"));
                            daoObject.update(obj);
                            updateJTable();
                            printPedido(obj);
                        } else {
                            pai.control.messageUmaLinha();
                        }
                    } else {
                        pai.control.messageSelecaoEditar();
                    }
                }
            }
        } catch (BussinessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_vendaBtnActionPerformed

    private void trocarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trocarBtnActionPerformed
        int opcao = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja modificar este registro?",
                "CONFIRMAR A MODIFICAÇÃO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcao == JOptionPane.YES_OPTION) {
            int[] rows = tabela.getSelectedRows();
            if (rows.length > 0) {
                if (rows.length == 1) {
                    Solicitude obj = list_atual.get(rows[0]);
                    trocarEstado(obj);
                } else {
                    pai.control.messageUmaLinha();
                }
            } else {
                pai.control.messageSelecaoEditar();
            }
        }
    }//GEN-LAST:event_trocarBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PedidoNaoVendidoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidoNaoVendidoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidoNaoVendidoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidoNaoVendidoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PedidoNaoVendidoDialog dialog = new PedidoNaoVendidoDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField busca_nome;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JComboBox<String> comboMetodo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    private javax.swing.JButton trocarBtn;
    private javax.swing.JButton vendaBtn;
    // End of variables declaration//GEN-END:variables
}
