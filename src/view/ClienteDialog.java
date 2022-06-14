/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Toast;
import dao.BussinessException;
import dao.ClienteDAOImplement;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import pojos.Bairro;
import pojos.Endereco;
import pojos.Cliente;
import pojos.Municipio;
import pojos.Pessoa;
import pojos.Provincia;
import pojos.Genero;

/**
 *
 * @author
 */
public class ClienteDialog extends javax.swing.JDialog {

    /**
     * Creates new form ClienteDialog
     */
    private final String busca1 = "Busca por código",
            busca2 = "Busca por nome";
    Main pai;
    Toast msg;
    List<Cliente> list_atual;
    List<Municipio> muns;
    List<Bairro> bairros;
    List<Genero> generos;
    List<Provincia> provincias;
    ClienteDAOImplement daoObject;

    public ClienteDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pai = (Main) parent;
        daoObject = pai.control.getClienteDAO();
        this.setLocationRelativeTo(null);
        //Atualizar tabela
        updateJTable();
        AutoCompleteDecorator.decorate(comboGenero);
        data.setDate(new Date());
        preecherCombo();

        String toolTip = "Cliente";
        jButton2.setToolTipText("Inserir " + toolTip);
        jButton4.setToolTipText("Apagar seleção de " + toolTip);
        botao_actualizar1.setToolTipText("Atualizar " + toolTip);
    }
    
    public String generateNum(int id) {
        String num = "CL";
        String idcad = id + "";
        int cant_cero = 8 - idcad.length();
        while (cant_cero-- > 0) {
            num += "0";
        }
        num += id;
        return num;
    }

    public void inicializarForm() {
        nome.setText("");
        sobrenome.setText("");
        telefone.setText("");
        email.setText("");
        rua.setText("");
        numero.setText("");
        data.setDate(new Date());
    }

    public void preecherCombo() {
        try {
            muns = new ArrayList<>();
            provincias = pai.control.getProvinciaDAO().findAll();
            generos = pai.control.getGeneroDAO().findAll();
            bairros = new ArrayList<>();
            for (Genero obj : generos) {
                comboGenero.addItem(obj.getNome());
            }
            for (Provincia obj : provincias) {
                comboProv.addItem(obj.getNome());
            }
            for (Municipio obj : provincias.get(comboProv.getSelectedIndex()).getMunicipios()) {
                comboMun.addItem(obj.getNome());
                muns.add(obj);
            }
            if (muns.size() == 0) {
                comboMun.setEnabled(false);
            } else {
                for (Bairro obj : muns.get(comboMun.getSelectedIndex()).getBairros()) {
                    comboBairro.addItem(obj.getNome());
                    bairros.add(obj);
                }
            }
            if (bairros.size() == 0) {
                comboBairro.setEnabled(false);
            }
        } catch (BussinessException ex) {
            pai.control.messageErroBussiness(ex);
        }
    }

    public void addRowTableModel(DefaultTableModel model, Cliente obj) {
        model.addRow(new Object[]{
            obj.getPessoa().getNome()+" "+obj.getPessoa().getSobrenome(),
            obj.getNum(),
            obj.getPessoa().getEndereco().getBairro().getMunicipio().getNome(),
            obj.getPessoa().getEndereco().getBairro().getNome(),
            obj.getPessoa().getEndereco().getRua(),
            obj.isEmpresa()?"SIM":"NÃO",
            obj.getPessoa().getTelefone(),
            obj.getPessoa().getEmail() == null ? "" : obj.getPessoa().getEmail()
        });
    }

    public void updateJTable() {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        try {
            list_atual = daoObject.findAll();
            for (Cliente obj : list_atual) {
                addRowTableModel(model, obj);
            }
        } catch (BussinessException ex) {
            pai.control.messageErroBussiness(ex);
        }
    }

    public void updateJTableBusca(List<Cliente> list) {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);
        for (Cliente obj : list) {
            addRowTableModel(model, obj);
        }
    }

    public void editar(Cliente obj) {
        if (!nome.getText().equals("")
                && !telefone.getText().equals("(+   )   -   -   ")
                && data.getDate()!=null
                && !rua.getText().equals("")) {
            String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            if (!email.getText().equals("") && !email.getText().matches(EMAIL_PATTERN)) {
                msg = new Toast("O campo email não é correto.", 2000);
                msg.showToast();
            } else {
                try {
                    //dados endereço
                    obj.getPessoa().getEndereco().setRua(rua.getText());
                    obj.getPessoa().getEndereco().setNumCasa(numero.getText());
                    obj.getPessoa().getEndereco().setBairro(bairros.get(comboBairro.getSelectedIndex()));
                    pai.control.getEnderecoDAO().update(obj.getPessoa().getEndereco());

                    //dados pessoa
                    obj.getPessoa().setNome(nome.getText());
                    obj.getPessoa().setSobrenome(sobrenome.getText());
                    obj.getPessoa().setTelefone(telefone.getText());
                    obj.getPessoa().setEmail(email.getText());
                    obj.getPessoa().setGenero(generos.get(comboGenero.getSelectedIndex()));
                    obj.getPessoa().setDataIngreso(data.getDate());
                    pai.control.getPessoaDAO().update(obj.getPessoa());
                    
                    obj.setNiif(niif.getText());                    
                    obj.setEmpresa(empresa.isSelected());
                    
                    daoObject.update(obj);
                    
                    updateJTable();
                    inicializarForm();
                    pai.control.messageOperacaoSucesso();
                } catch (BussinessException ex) {
                    pai.control.messageErroBussiness(ex);
                }
            }
        } else {
            pai.control.messageFieldEmpty();
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
        jLabel1 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        busca_nome = new javax.swing.JTextField();
        botao_actualizar1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        telefone = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        comboGenero = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        comboProv = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        comboMun = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        comboBairro = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        rua = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();
        data = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        sobrenome = new javax.swing.JTextField();
        empresa = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        niif = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GERENCIAR CLIENTES");

        tabela.setAutoCreateRowSorter(true);
        tabela.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Número", "Município", "Bairro", "Rua", "Empresa?", "Telefone", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        jLabel1.setText("Nome:");

        nome.setName("nome"); // NOI18N

        jButton2.setText("Inserir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Remover");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

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

        botao_actualizar1.setText("Editar");
        botao_actualizar1.setToolTipText("Actualizar dados do funcionário");
        botao_actualizar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botao_actualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_actualizar1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Data Ingreso:");

        jLabel8.setText("Telefone:");

        try {
            telefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(+###)###-###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel9.setText("Email:");

        email.setName("nome"); // NOI18N

        jLabel11.setText("Gênero:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("ENDEREÇO"));
        jPanel2.setToolTipText("");

        jLabel3.setText("Província:");

        comboProv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboProvItemStateChanged(evt);
            }
        });

        jLabel4.setText("Município:");

        comboMun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comboMunMouseReleased(evt);
            }
        });

        jLabel12.setText("Bairro:");

        jLabel14.setText("Rua:");

        rua.setName("nome"); // NOI18N

        jLabel15.setText("Número:");

        numero.setName("nome"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboProv, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboMun, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rua, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(comboProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(comboMun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(comboBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jLabel5.setText("Sobrenome");

        sobrenome.setName("nome"); // NOI18N

        empresa.setText("Empresa?");
        empresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empresaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                empresaMouseReleased(evt);
            }
        });

        jLabel6.setText("NIIF / BI:");

        niif.setName("nome"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(14, 14, 14)
                        .addComponent(jButton4)
                        .addGap(14, 14, 14)
                        .addComponent(botao_actualizar1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(busca_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sobrenome)
                                    .addComponent(data, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(empresa)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(niif, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 3, Short.MAX_VALUE)
                .addComponent(busca_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(sobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel11)
                                .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(niif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addComponent(empresa)))
                .addGap(33, 33, 33)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botao_actualizar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
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

    private void botao_actualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_actualizar1ActionPerformed
        int opcao = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja modificar este registro?",
                "CONFIRMAR A MODIFICAÇÃO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcao == JOptionPane.YES_OPTION) {
            int[] rows = tabela.getSelectedRows();
            if (rows.length > 0) {
                if (rows.length == 1) {
                    Cliente obj = list_atual.get(rows[0]);
                    editar(obj);
                } else {
                    pai.control.messageUmaLinha();
                }
            } else {
                pai.control.messageSelecaoEditar();
            }
        }
    }//GEN-LAST:event_botao_actualizar1ActionPerformed

    private void busca_nomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busca_nomeKeyPressed
        List<Cliente> list = new ArrayList<>();
        updateJTable();
        boolean flag = false;
        for (Cliente list1 : list_atual) {
            if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE
                    && !(list1.getPessoa().getNome()+" "+list1.getPessoa().getSobrenome()).toUpperCase().contains(
                            (busca_nome.getText().substring(0, busca_nome.getText().length() - 1)).toUpperCase()
                    )) {
                flag = true;
            } else if (evt.getKeyCode() != KeyEvent.VK_BACK_SPACE
                    && !(list1.getPessoa().getNome()+" "+list1.getPessoa().getSobrenome()).toUpperCase().contains(
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

    private void busca_nomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_busca_nomeFocusLost
        focusLost(busca_nome, busca2);
    }//GEN-LAST:event_busca_nomeFocusLost

    private void busca_nomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_busca_nomeFocusGained
        focusGained(busca_nome, busca2);
    }//GEN-LAST:event_busca_nomeFocusGained

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int opcao = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja excluir esses registros?",
                "CONFIRMAR REMOÇÃO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcao == JOptionPane.YES_OPTION) {
            try {
                int[] rows = tabela.getSelectedRows();
                if (rows.length != 0) {
                    for (int indice : rows) {
                        int idBairro = list_atual.get(indice).getPessoa().getEndereco().getBairro().getId();
                        pai.control.getPessoaDAO().delete(list_atual.get(indice).getPessoa().getId());
                    }
                    inicializarForm();
                    updateJTable();
                    pai.control.messageOperacaoSucesso();
                } else {
                    pai.control.messageLinhasExcluir();
                }

            } catch (BussinessException ex) {
                pai.control.messageErroBussiness(ex);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (nome.getText().equals("")
                || data.getDate()==null
                || telefone.getText().equals("(+   )   -   -   ")
                || rua.getText().equals("")) {
            pai.control.messageFieldEmpty();
        } else {
            String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            if (!email.getText().equals("") && !email.getText().matches(EMAIL_PATTERN)) {
                msg = new Toast("O campo email não é correto.", 2000);
                msg.showToast();
            } else {

                try {
                    Endereco dir = new Endereco(bairros.get(comboBairro.getSelectedIndex()),rua.getText());
                    dir.setNumCasa(numero.getText());
                    pai.control.getEnderecoDAO().save(dir);

                    Pessoa obj = new Pessoa();

                    //dados pessoa
                    obj.setEndereco(dir);
                    obj.setNome(nome.getText());
                    obj.setSobrenome(sobrenome.getText());
                    obj.setTelefone(telefone.getText());
                    obj.setEmail(email.getText());
                    obj.setGenero(generos.get(comboGenero.getSelectedIndex()));
                    obj.setDataIngreso(data.getDate());
                    pai.control.getPessoaDAO().save(obj);

                    Cliente cl = new Cliente();
                    cl.setPessoa(obj);
                    cl.setNum(generateNum(obj.getId()));
                    cl.setNiif(niif.getText());
                    cl.setEmpresa(empresa.isSelected());
                    daoObject.save(cl);
                    updateJTable();
                    pai.control.messageOperacaoSucesso();
                    inicializarForm();
                } catch (BussinessException ex) {
                    pai.control.messageErroBussiness(ex);
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        Cliente obj = list_atual.get(tabela.getSelectedRow());
        nome.setText(obj.getPessoa().getNome());
        sobrenome.setText(obj.getPessoa().getSobrenome());
        telefone.setText(obj.getPessoa().getTelefone());
        email.setText(obj.getPessoa().getEmail());
        rua.setText(obj.getPessoa().getEndereco().getRua());
        numero.setText(obj.getPessoa().getEndereco().getNumCasa());
        comboGenero.setSelectedItem(obj.getPessoa().getGenero());
        data.setDate(obj.getPessoa().getDataIngreso());
        comboProv.setSelectedItem(obj.getPessoa().getEndereco().getBairro().getMunicipio().getProvincia().getNome());
        comboMun.setSelectedItem(obj.getPessoa().getEndereco().getBairro().getMunicipio().getNome());
        comboBairro.setSelectedItem(obj.getPessoa().getEndereco().getBairro().getNome());        

    }//GEN-LAST:event_tabelaMouseClicked

    private void comboProvItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboProvItemStateChanged
        muns = new ArrayList<>();
        comboMun.removeAllItems();
        comboBairro.removeAllItems();
        for (Municipio obj : provincias.get(comboProv.getSelectedIndex()).getMunicipios()) {
                comboMun.addItem(obj.getNome());
                muns.add(obj);
        }
        if(muns.size()>0)
            comboMun.setEnabled(true);
        
    }//GEN-LAST:event_comboProvItemStateChanged

    private void comboMunMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboMunMouseReleased
        comboBairro.removeAllItems();
        for (Bairro obj : muns.get(comboMun.getSelectedIndex()).getBairros()) {
            comboBairro.addItem(obj.getNome());
            bairros.add(obj);
        }
        if (bairros.size() > 0) {
            comboBairro.setEnabled(true);
        }
    }//GEN-LAST:event_comboMunMouseReleased

    private void empresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empresaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_empresaMouseClicked

    private void empresaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empresaMouseReleased
        
    }//GEN-LAST:event_empresaMouseReleased

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
            java.util.logging.Logger.getLogger(ClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClienteDialog dialog = new ClienteDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton botao_actualizar1;
    private javax.swing.JTextField busca_nome;
    private javax.swing.JComboBox<String> comboBairro;
    private javax.swing.JComboBox<String> comboGenero;
    private javax.swing.JComboBox<String> comboMun;
    private javax.swing.JComboBox<String> comboProv;
    private com.toedter.calendar.JDateChooser data;
    private javax.swing.JTextField email;
    private javax.swing.JCheckBox empresa;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField niif;
    private javax.swing.JTextField nome;
    private javax.swing.JTextField numero;
    private javax.swing.JTextField rua;
    private javax.swing.JTextField sobrenome;
    private javax.swing.JTable tabela;
    private javax.swing.JFormattedTextField telefone;
    // End of variables declaration//GEN-END:variables
}
