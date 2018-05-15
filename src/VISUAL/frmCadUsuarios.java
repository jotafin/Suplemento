package VISUAL;
import CONETAR.ConectaBD;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class frmCadUsuarios extends javax.swing.JInternalFrame {

        Connection conecta;
        PreparedStatement pst;
        ResultSet rs;
    
    public frmCadUsuarios() throws ClassNotFoundException 
    {
        initComponents();
        this.setLocation(500,200);
        conecta = ConectaBD.conectabd();
        listarUsuarios();
    }

    
    // atualiza a tabela
    public void listarUsuarios()
    {
        String sql = "Select *from usuarios order by codigo Asc";
        try
        {
            pst = conecta.prepareStatement(sql);  //recebe a conexão
            rs = pst.executeQuery();        
            tblUsuarios.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, error);
        
        }
    
    
    
    }
    
    
    public void cadastrarUsuarios()
    {
        String sql = "Insert into usuarios(nome, telefone, endereco) values (? , ? , ?)";
        try
        {
            pst = conecta.prepareStatement(sql);
            pst.setString(1,txtNome.getText());
            pst.setString(2,txtTelefone.getText());
            pst.setString(3,txtEndereco.getText());
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!","Cadastrado com Sucesso",JOptionPane.INFORMATION_MESSAGE);
            listarUsuarios(); // ATUALIZA A TABELA
            
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null,error);
            
        }
}
    
    public void pesquisarUsuarios()
    {
        String sql = "Select *from usuarios where nome like ?";
        try
        {
            pst = conecta.prepareStatement(sql);
            pst.setString(1,txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblUsuarios.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch(SQLException error)
        {
                JOptionPane.showMessageDialog(null, error);
        }
    
    }
    
    public void mostrarItens()
    {
        int seleciona = tblUsuarios.getSelectedRow();
        txtCodigo.setText(tblUsuarios.getModel().getValueAt(seleciona,0).toString());
        txtNome.setText(tblUsuarios.getModel().getValueAt(seleciona, 1).toString());
        txtTelefone.setText(tblUsuarios.getModel().getValueAt(seleciona, 2).toString());
        txtEndereco.setText(tblUsuarios.getModel().getValueAt(seleciona, 3).toString());
    
    }
    
    public void editarUsuarios()
    {
        String sql = "Update usuarios set nome = ?, telefone = ?, endereco = ? where codigo = ?";
        try
        {
            pst = conecta.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtTelefone.getText());
            pst.setString(3, txtEndereco.getText());
            pst.setInt(4,Integer.parseInt(txtCodigo.getText()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Cadastro atualizado com Sucesso!", "Cadastro Atualizado", JOptionPane.INFORMATION_MESSAGE );
            listarUsuarios(); // ATUALIZA A TABELA
            
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, error);
        
        }
    }
    
    public void deletarUsuarios()
    {
        String sql = "Delete from usuarios where codigo = ?";
        try
        {
                pst = conecta.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(txtCodigo.getText()));
                pst.execute();
                JOptionPane.showMessageDialog(null, "Usúario Deletado com Sucesso!", "Usúario Deletado", JOptionPane.INFORMATION_MESSAGE);
                listarUsuarios(); //ATUALIZA A TABELA
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    
    }
    
    public void limparCampos()
    {
        txtCodigo.setText("");
        txtNome.setText("");
        txtTelefone.setText("");
        txtEndereco.setText("");
       
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        CADASTRAR = new javax.swing.JButton();
        Editar = new javax.swing.JButton();
        DELETAR = new javax.swing.JButton();
        Limpar = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de Usúarios");

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        jLabel1.setText("Código:");

        txtCodigo.setBackground(new java.awt.Color(0, 0, 0));
        txtCodigo.setEnabled(false);

        jLabel2.setText("Nome:");

        jLabel3.setText("Telefone:");

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####.####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel4.setText("Endereço:");

        CADASTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/add.png"))); // NOI18N
        CADASTRAR.setText("Cadastrar");
        CADASTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CADASTRARActionPerformed(evt);
            }
        });

        Editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/user_edit.png"))); // NOI18N
        Editar.setText("Editar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });

        DELETAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/user_delete.png"))); // NOI18N
        DELETAR.setText("Deletar");
        DELETAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETARActionPerformed(evt);
            }
        });

        Limpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/delete.png"))); // NOI18N
        Limpar.setText("Limpar");
        Limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimparActionPerformed(evt);
            }
        });

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/zoom.png"))); // NOI18N
        jLabel5.setText("Buscar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CADASTRAR, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78)
                                .addComponent(Editar)
                                .addGap(35, 35, 35)
                                .addComponent(DELETAR, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(Limpar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtEndereco))
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {CADASTRAR, DELETAR, Editar, Limpar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DELETAR)
                    .addComponent(Limpar)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(CADASTRAR, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {CADASTRAR, DELETAR, Editar, Limpar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CADASTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CADASTRARActionPerformed
            // TODO add your handling code here:
            cadastrarUsuarios();
    }//GEN-LAST:event_CADASTRARActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        // TODO add your handling code here:
        pesquisarUsuarios();
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        // TODO add your handling code here:
        mostrarItens();
        
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void LimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimparActionPerformed
        // TODO add your handling code here:
        limparCampos();
        
    }//GEN-LAST:event_LimparActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO add your handling code here:
        editarUsuarios();
    }//GEN-LAST:event_EditarActionPerformed

    private void DELETARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETARActionPerformed
        // TODO add your handling code here:
        deletarUsuarios();
    }//GEN-LAST:event_DELETARActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CADASTRAR;
    private javax.swing.JButton DELETAR;
    private javax.swing.JButton Editar;
    private javax.swing.JButton Limpar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
