/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Toast;
import dao.BussinessException;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import pojos.Cliente;
import pojos.Funcionario;
import pojos.Bilhete;
import pojos.SolicitudeBilhete;
import pojos.Solicitude;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import reports.SolicitudeBilheteReport;

/**
 *
 * @author
 */
public class SolicitudeDialog extends javax.swing.JDialog {

    /**
     * Creates new form SolicitudeDialog
     */
    private final String busca1 = "Busca por código",
            /**
             * Creates new form SolicitudeDialog
             */
            busca2 = "Busca por nome";
    Main pai;
    Toast msg;
    List<Bilhete> list_atual;
    double preco_total = 0;
    Funcionario func;
    List<SolicitudeBilhete> solicitudes_servico;
    List<Cliente> cls;
    Bilhete pscanner = null;

    public SolicitudeDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pai = (Main) parent;
        this.setLocationRelativeTo(null);
        emcherComboBox();
        inicializarForm();
        //Atualizar tabela
        updateJTable();
        AutoCompleteDecorator.decorate(combo);

    }

    public void inicializarForm() {
        quantidade.setText("");
        servico.setText("   ");
    }

    public void emcherComboBox() {
        try {
            cls = pai.control.getClienteDAO().findAll();
            for (Cliente cl : cls) {
                combo.addItem(cl.getPessoa().getNome() + " - " + cl.getId());
            }
        } catch (BussinessException ex) {
            pai.control.messageErroBussiness(ex);
        }

    }
    
    public void printPedido(){
        List<SolicitudeBilheteReport> objs = new ArrayList<>();
        double total = 0;
        for (SolicitudeBilhete produtos_venda : solicitudes_servico) {
            objs.add(new SolicitudeBilheteReport(produtos_venda));
            total += produtos_venda.getQuantidade() * produtos_venda.getBilhete().getValor();
        }
        try {
            JasperReport reporte = null;
            URL path = this.getClass().getResource("/reports/pedido_report.jasper");
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("logo", this.getClass().getResource("/resource/logo.jpeg").toString());
            parametros.put("total", total);
            parametros.put("numero", solicitudes_servico.get(0).getSolicitude().getNum());
            parametros.put("funcionario", pai.getUsuario_login().getFuncionario()==null?"":pai.getUsuario_login().getFuncionario().getPessoa().getNome().toUpperCase());
            parametros.put("cliente", cls.get(combo.getSelectedIndex()).getPessoa().getNome().toUpperCase());
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

    public void updateJTable() {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        while (model.getRowCount() != 0) {
            model.removeRow(model.getRowCount() - 1);
        }
        try {
            List<Bilhete> list = pai.control.getBilheteDAO().findAll();
            list_atual = list;
            for (Bilhete obj : list) {
                model.addRow(new Object[]{
                    obj.getNome(),
                    obj.getTipo().getNome(),
                    obj.getQuantidade(),
                    obj.getValor()
                });
            }
        } catch (BussinessException ex) {
            pai.control.messageErroBussiness(ex);
        }
    }

    public void updateJTableBusca(List<Bilhete> list) {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();

        while (model.getRowCount() != 0) {
            model.removeRow(model.getRowCount() - 1);
        }
        if (list.size() == 1) {
            pscanner = list.get(0);
        }

        for (Bilhete obj : list) {
            model.addRow(new Object[]{
                obj.getNome(),
                obj.getTipo().getNome(),
                obj.getQuantidade(),
                obj.getValor()
            });
        }
    }

    public String generateNum(int id) {
        String num = "PED-BILH";
        String idcad = id + "";
        int cant_cero = 8 - idcad.length();
        while (cant_cero-- > 0) {
            num += "0";
        }
        num += id;
        return num;
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

    public boolean alfaNumerico(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        busca_nome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        combo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        quantidade = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaSolicitude = new javax.swing.JTable();
        inserirBtn = new javax.swing.JButton();
        apagarBtn = new javax.swing.JButton();
        labelValor = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        servico = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GERENCIAR PEDIDO");

        busca_nome.setForeground(new java.awt.Color(153, 153, 153));
        busca_nome.setText("Busca por nome");
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

        tabela.setAutoCreateRowSorter(true);
        tabela.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Tipo", "Quantidade", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tabelaFocusLost(evt);
            }
        });
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        jLabel7.setText("Cliente:");

        combo.setEditable(true);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Bilhete:");

        jLabel6.setText("Quantidade:");

        tabelaSolicitude.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bilhete", "Quantidade", "Valor Unitario", "Valor Total", "ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabelaSolicitude);

        inserirBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_add_new_28px.png"))); // NOI18N
        inserirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inserirBtnActionPerformed(evt);
            }
        });

        apagarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_remove_28px.png"))); // NOI18N
        apagarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apagarBtnActionPerformed(evt);
            }
        });

        labelValor.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        labelValor.setText("Valor: ");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8_money_bag_28px.png"))); // NOI18N
        jButton3.setText("Fazer Pedido");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        servico.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelValor, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(busca_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(109, 109, 109)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(554, 554, 554)
                                .addComponent(servico, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 23, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(346, 346, 346)
                .addComponent(inserirBtn)
                .addGap(18, 18, 18)
                .addComponent(apagarBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(busca_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(servico, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inserirBtn)
                    .addComponent(apagarBtn))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(labelValor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        List<Bilhete> list = new ArrayList<>();
        boolean flag = false;
        for (Bilhete list1 : list_atual) {
            if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE
                    && !list1.getNome().contains(busca_nome.getText().substring(0, busca_nome.getText().length() - 1))) {
                flag = true;
            } else if (evt.getKeyCode() != KeyEvent.VK_BACK_SPACE
                    && !list1.getNome().contains(busca_nome.getText() + evt.getKeyChar())) {
                flag = true;
            } else {
                list.add(list1);
            }
        }
        updateJTableBusca(list);
    }//GEN-LAST:event_busca_nomeKeyPressed

    private void inserirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inserirBtnActionPerformed
        DefaultTableModel model = (DefaultTableModel) tabelaSolicitude.getModel();
        int[] rows = tabela.getSelectedRows();
        Bilhete p = null;
        if (pscanner == null) {
            p = list_atual.get(rows[0]);
        } else {
            p = pscanner;
        }
        preco_total += Integer.parseInt(quantidade.getText()) * p.getValor();
        labelValor.setText("Valor: " + preco_total);
        model.addRow(new Object[]{
            servico.getText(),
            quantidade.getText(),
            p.getValor(),
            Integer.parseInt(quantidade.getText()) * p.getValor(),
            p.getId()
        });
        pscanner = null;

    }//GEN-LAST:event_inserirBtnActionPerformed

    private void apagarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apagarBtnActionPerformed
        try {
            int[] filas = tabelaSolicitude.getSelectedRows();
            DefaultTableModel model = (DefaultTableModel) tabelaSolicitude.getModel();
            if (filas.length != 0) {
                for (int indice : filas) {
                    Bilhete p = pai.control.getBilheteDAO().get(
                            Integer.parseInt(model.getValueAt(indice, 4).toString())
                    );

                    preco_total -= Integer.parseInt(model.getValueAt(indice, 1).toString()) * p.getValor();
                    labelValor.setText("Valor: " + preco_total);
                    model.removeRow(indice);
                }
            } else {
                pai.control.messageLinhasExcluir();
            }

        } catch (BussinessException ex) {
            pai.control.messageErroBussiness(ex);
        }
    }//GEN-LAST:event_apagarBtnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            DefaultTableModel model = (DefaultTableModel) tabelaSolicitude.getModel();
            //Solicitude v = new Solicitude(func, new Date(), preco_total);
            Solicitude v = new Solicitude();
            v.setCliente(cls.get(combo.getSelectedIndex()));
            v.setData(new Date());
            v.setEstadoSolicitude(pai.control.getEstadoSolicitudeDAO().getByNome("Novo"));
            solicitudes_servico = new ArrayList<>();
            
            pai.control.getSolicitudeDAO().save(v);
            v.setNum(generateNum(v.getId()));
            pai.control.getSolicitudeDAO().update(v);
            for (int indice = model.getRowCount() - 1; indice >= 0; indice--) {
                Bilhete p = pai.control.getBilheteDAO().get(
                        Integer.parseInt(model.getValueAt(indice, 4).toString())
                );
                
                SolicitudeBilhete pv = new SolicitudeBilhete(p, v,
                        Integer.parseInt(model.getValueAt(indice, 1).toString()));
                pai.control.getSolicitudeBilheteDAO().save(pv);
                solicitudes_servico.add(pv);
                p.setQuantidade(p.getQuantidade()-pv.getQuantidade());
                pai.control.getBilheteDAO().update(p);
                model.removeRow(indice);
            }
            

            msg = new Toast("Solicitude realizada com successo", 2000);
            msg.showToast();
            if (solicitudes_servico.size() > 0) {
                printPedido();
            }
            updateJTable();
            labelValor.setText("Valor: ");
            preco_total = 0;

        } catch (BussinessException ex) {
            pai.control.messageErroBussiness(ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tabelaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabelaFocusLost

    }//GEN-LAST:event_tabelaFocusLost

    private void tabelaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMousePressed

    }//GEN-LAST:event_tabelaMousePressed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        int[] rows = tabela.getSelectedRows();
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        if (rows.length != 0) {
            if (rows.length > 1) {
                pai.control.messageUmaLinha();
            } else {
                Bilhete p = list_atual.get(rows[0]);
                servico.setText(p.getNome()+" / "+p.getTipo().getNome());
            }
        }
    }//GEN-LAST:event_tabelaMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void pai(String args[]) {
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
            java.util.logging.Logger.getLogger(SolicitudeDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SolicitudeDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SolicitudeDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SolicitudeDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SolicitudeDialog dialog = new SolicitudeDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton apagarBtn;
    private javax.swing.JTextField busca_nome;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JButton inserirBtn;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelValor;
    private javax.swing.JTextField quantidade;
    private javax.swing.JLabel servico;
    private javax.swing.JTable tabela;
    private javax.swing.JTable tabelaSolicitude;
    // End of variables declaration//GEN-END:variables
}
