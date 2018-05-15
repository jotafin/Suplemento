package VISUAL;
import CONETAR.ConectaBD;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;


public class frmCadProdutos extends javax.swing.JInternalFrame {
    
    Connection conecta;
    PreparedStatement pst;
    ResultSet rs;
    
  
    public frmCadProdutos() throws ClassNotFoundException {
        initComponents();
        this.setLocation(500,200);
        conecta = ConectaBD.conectabd();
        listarProdutos();
    }
   
    
    // atualiza produtos
    public void listarProdutos()
    {
        String sql = "Select *from produtos order by codigo Asc";
        try
        {
            pst = conecta.prepareStatement(sql);
            rs = pst.executeQuery();
            tblProdutos.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    
    }
    
    public void cadastrarProdutos()
    {
        String sql = "Insert into produtos(marca, nome, preco, tipo) values (?, ?, ?, ?)";
        try
        {
            pst = conecta.prepareStatement(sql);
            pst.setString(1, txtMarca.getText());
            pst.setString(2, txtNomeProduto.getText());
            pst.setString(3, txtPreco.getText());
            pst.setString(4, txtTipo.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null,"Cadastrado com Sucesso!","Produto Cadastrado",JOptionPane.INFORMATION_MESSAGE );
            listarProdutos(); //atualiza a tabela
            
        }
        catch(SQLException error)
        {
                JOptionPane.showMessageDialog(null, error);
        }
    
    
    }
    
   
    public void pesquisarProdutos()
    {
        String sql = "Select *from produtos where nome like ?";
      
        try
        {
            pst = conecta.prepareStatement(sql);
            pst.setString(1, txtPesquisarProduto.getText() + "%");
            rs = pst.executeQuery();
            tblProdutos.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void mostrarProdutos()
    {
        int seleciona = tblProdutos.getSelectedRow();
        txtcodigo.setText(tblProdutos.getModel().getValueAt(seleciona,0).toString());
        txtMarca.setText(tblProdutos.getModel().getValueAt(seleciona, 1).toString());
        txtNomeProduto.setText(tblProdutos.getModel().getValueAt(seleciona, 2).toString());
        txtPreco.setText(tblProdutos.getModel().getValueAt(seleciona, 3 ).toString());
        txtTipo.setText(tblProdutos.getModel().getValueAt(seleciona, 4).toString());
    
    }
    
    public void editarProduto()
    {
        String sql = "Update produtos set marca = ?, nome = ?, preco = ?, tipo = ? where codigo = ?";
        try
        {
            pst = conecta.prepareStatement(sql);
            pst.setString(1, txtMarca.getText());
            pst.setString(2, txtNomeProduto.getText());
            pst.setString(3, txtPreco.getText());
            pst.setString(4, txtTipo.getText());
            pst.setInt(5,Integer.parseInt(txtcodigo.getText()));
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro Atualizado com Sucesso!", "Produto Atualizado!", JOptionPane.INFORMATION_MESSAGE);
            listarProdutos(); //atualiza produtos
            
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, error);
        }
    
    }
    
    public void deletarProdutos()
    {
        String sql = "Delete from produtos where codigo = ?";
        try
        {
            pst = conecta.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtcodigo.getText()));
            pst.execute();
            JOptionPane.showMessageDialog(null, "Produto Deletado com Sucesso!","Produto Deletado", JOptionPane.INFORMATION_MESSAGE);
            listarProdutos(); 
        }
        catch(SQLException error)
        {
            JOptionPane.showMessageDialog(null, error);
                   
        }
    
    
    }
    
    
    
    
    public void limparCampos()
    {
        txtcodigo.setText("");
        txtMarca.setText("");
        txtNomeProduto.setText("");
        txtPreco.setText("");
        txtTipo.setText("");
    
     }
    
   
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNomeProduto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        cadProdutos = new javax.swing.JButton();
        ediProdutos = new javax.swing.JButton();
        DeletarProduto = new javax.swing.JButton();
        LIMPARCAMPOS = new javax.swing.JButton();
        txtPesquisarProduto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de Produtos");

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProdutos);

        jLabel2.setText("Código:");

        txtcodigo.setBackground(new java.awt.Color(0, 0, 0));
        txtcodigo.setEnabled(false);

        jLabel3.setText("Nome do Produto:");

        jLabel4.setText("Preço R$:");

        txtPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecoActionPerformed(evt);
            }
        });

        jLabel5.setText("Tipo:");

        cadProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/add.png"))); // NOI18N
        cadProdutos.setText("Cadastrar");
        cadProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadProdutosActionPerformed(evt);
            }
        });

        ediProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/user_edit.png"))); // NOI18N
        ediProdutos.setText("Editar");
        ediProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ediProdutosActionPerformed(evt);
            }
        });

        DeletarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/user_delete.png"))); // NOI18N
        DeletarProduto.setText("Deletar");
        DeletarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletarProdutoActionPerformed(evt);
            }
        });

        LIMPARCAMPOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/delete.png"))); // NOI18N
        LIMPARCAMPOS.setText("Limpar");
        LIMPARCAMPOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPARCAMPOSActionPerformed(evt);
            }
        });

        txtPesquisarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarProdutoActionPerformed(evt);
            }
        });
        txtPesquisarProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarProdutoKeyReleased(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/zoom.png"))); // NOI18N
        jLabel6.setText("Buscar");

        jLabel1.setText("Marca:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(cadProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(ediProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(DeletarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(LIMPARCAMPOS, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(77, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPesquisarProduto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtMarca)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LIMPARCAMPOS)
                    .addComponent(DeletarProduto)
                    .addComponent(ediProdutos)
                    .addComponent(cadProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {DeletarProduto, LIMPARCAMPOS, cadProdutos, ediProdutos});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecoActionPerformed

    private void cadProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadProdutosActionPerformed
        // TODO add your handling code here:
        cadastrarProdutos();
    }//GEN-LAST:event_cadProdutosActionPerformed

    private void txtPesquisarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarProdutoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtPesquisarProdutoActionPerformed

    private void txtPesquisarProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarProdutoKeyReleased
        // TODO add your handling code here:
        pesquisarProdutos();
    }//GEN-LAST:event_txtPesquisarProdutoKeyReleased

    private void LIMPARCAMPOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPARCAMPOSActionPerformed
        // TODO add your handling code here:
        limparCampos();
    }//GEN-LAST:event_LIMPARCAMPOSActionPerformed

    private void tblProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutosMouseClicked
        // TODO add your handling code here:
        mostrarProdutos();
    }//GEN-LAST:event_tblProdutosMouseClicked

    private void ediProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ediProdutosActionPerformed
        // TODO add your handling code here:
        editarProduto();
    }//GEN-LAST:event_ediProdutosActionPerformed

    private void DeletarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletarProdutoActionPerformed
        // TODO add your handling code here:
        deletarProdutos();
    }//GEN-LAST:event_DeletarProdutoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeletarProduto;
    private javax.swing.JButton LIMPARCAMPOS;
    private javax.swing.JButton cadProdutos;
    private javax.swing.JButton ediProdutos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtNomeProduto;
    private javax.swing.JTextField txtPesquisarProduto;
    private javax.swing.JTextField txtPreco;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtcodigo;
    // End of variables declaration//GEN-END:variables
}
