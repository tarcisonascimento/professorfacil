package br.com.professorfacil.telas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import br.com.professorfacil.dal.ModuloConexao;
import br.com.professorfacil.classes.FormatData;
import java.sql.*;

public class TelaLogin extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void logar() {

        String sql = "select * from usuarios where uso_usuario=? and uso_senha=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUso.getText());
            pst.setString(2, txtSenha.getText());

            rs = pst.executeQuery();

            if (rs.next()) {

                TelaPrincipal tela = new TelaPrincipal();
                tela.setVisible(true);
                TelaPrincipal.lblNome.setText(rs.getString("uso_nome"));
                FormatData p = new FormatData();
                TelaPrincipal.lblData.setText(p.data());//pega os parametros que eu quero
                TelaPrincipal.lblPerfil.setText(rs.getString("uso_perfil"));
                this.dispose();
                conexao.close();

            } else {
                JOptionPane.showMessageDialog(null, "Usuario e/ou senha inválido(s)");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Falha no banco de dados\n erro:" + e);

        }

    }

    public TelaLogin() {
        initComponents();

        conexao = ModuloConexao.conector();
        FormatData d = new FormatData();
        if (conexao != null) {

            lblStatus.setText(d.data() + " | A conexão com banco de dados foi estabelecida!");
        } else {
            lblStatus.setText(d.data() + " | A conexão com banco de dados falhou!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLogin = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        txtUso = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Professor Fácil");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        btnLogin.setText("ENTRAR");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        txtSenha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtUso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtUso.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/professorfacil/icones/logo160x125.png"))); // NOI18N

        jLabel2.setText("Login");

        jLabel3.setText("Senha");

        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("00/00/0000 | Status da conexao");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(211, 211, 211))
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtSenha, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtUso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))))
            .addComponent(lblStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUso, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel3)
                .addGap(1, 1, 1)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(338, 420));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        logar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoginActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        getRootPane().setDefaultButton(btnLogin);         // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUso;
    // End of variables declaration//GEN-END:variables
}
