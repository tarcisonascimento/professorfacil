package br.com.professorfacil.telas;

import br.com.professorfacil.classes.ManipularImagem;
import br.com.professorfacil.dal.ModuloConexao;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.proteanit.sql.DbUtils;
import br.com.professorfacil.classes.Modulo;
import static br.com.professorfacil.classes.Modulo.modulo1;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaConfiguracao extends javax.swing.JInternalFrame {

    Connection conexao = null;//usando o metodo de conexao e atribuindo a conexao limpa para iniciar
    PreparedStatement pst = null; //usado para preparar a conexao com o banco de dados
    ResultSet rs = null;//exibe o resultado das instruçoes sql que sera usado no java

    public static TelaConfiguracao telaConfiguracao;

    public static TelaConfiguracao getInstancia() {
        if (telaConfiguracao == null) {

            telaConfiguracao = new TelaConfiguracao();
        }
        return telaConfiguracao;
    }

    private void preenchecamos() {

        txtIdUso.setText(TelaPrincipal.lbMatricula.getText());

        String sql = "select * from usuarios where iduso=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdUso.getText());
            rs = pst.executeQuery();

            if (rs.next()) {

                txtNomeUso.setText(rs.getString("uso_nome"));
                txtCidadeUso.setText(rs.getString("uso_cidade"));
                txtUfUso.setText(rs.getString("uso_estado"));
                txtEndUso.setText(rs.getString("uso_endereco"));
                txtNumEnd.setText(rs.getString("uso_end_num"));
                txtEmailUso.setText(rs.getString("uso_email"));
                txtCpfUso.setText(rs.getString("uso_cpf"));
                txtCelUso.setText(rs.getString("uso_celular"));
                txtCepUso.setText(rs.getString("uso_cep"));
                txtUso.setText(rs.getString("uso_usuario"));
                txtSenha.setText(rs.getString("uso_senha"));

            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao carregar informações do usuário " + e);
        }

        String sqlcor = "select * from licenca where idcli=?";

        try {
            pst = conexao.prepareCall(sqlcor);
            pst.setString(1, txtIdUso.getText());
            rs = pst.executeQuery();

            if (rs.next()) {

                String situacao = rs.getString("lic_descricao");
                boolean status = rs.getBoolean("lic_status");

                if (status == true && situacao.equals("Software Licenciado, Obrigado!")) {

                    txtLicNome.setText(rs.getString("lic_nomecli"));
                    txtLicEmail.setText(rs.getString("lic_emailcli"));
                    txtLicCpf.setText(rs.getString("lic_cpfcli"));
                    txtLicCel.setText(rs.getString("lic_celularcli"));
                    txtLic.setText(rs.getString("lic_chave"));
                    txtLicAut.setText(rs.getString("lic_licenca"));
                    txtLicDataLic.setText(rs.getString("lic_data_licenca"));
                    txtLicVal.setText(rs.getString("lic_data_valida"));
                    txtLicNome.setEnabled(false);
                    txtLicEmail.setEnabled(false);
                    txtLicCpf.setEnabled(false);
                    txtLicCel.setEnabled(false);
                    txtLic.setEnabled(false);
                    txtLicAut.setEnabled(false);
                    txtLicDataLic.setEnabled(false);
                    txtLicVal.setEnabled(false);
                    btnComprar.setEnabled(false);
                    btnAtiva.setEnabled(false);

                }


                if (situacao.equals("Não Licenciado")) {
                    lblDescricao.setText(situacao);
                    lblDescricao.setForeground(new Color(255, 51, 0));
                }
                if (situacao.equals("Expirado")) {
                    lblDescricao.setText(situacao);
                    lblDescricao.setForeground(new Color(255, 51, 0));

                }
                if (situacao.equals("Em Processamento")) {
                    lblDescricao.setText(situacao);
                    lblDescricao.setForeground(new Color(0, 61, 250));
                    btnComprar.setEnabled(false);
                }
                if (situacao.equals("Software Licenciado, Obrigado!")) {
                    lblDescricao.setText(situacao);
                    lblDescricao.setForeground(new Color(51, 204, 0));
                }
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao localizar licença! " + e);

        }

        String sql1 = "select * from escola where idcli=?";

        try {
            pst = conexao.prepareStatement(sql1);
            pst.setString(1, txtIdUso.getText());
            rs = pst.executeQuery();

            if (rs.next()) {

                txtNomeEstado.setText(rs.getString("esc_estado"));
                txtSecretaria.setText(rs.getString("esc_secretaria"));
                txtNomeEscola.setText(rs.getString("esc_ecola"));
                txtCidadeEscola.setText(rs.getString("esc_cidade"));
                txtEndEsc.setText(rs.getString("esc_endereco"));
                txtUfEscola.setText(rs.getString("esc_uf"));
                txtNumEsco.setText(rs.getString("esc_num"));

                if (rs.getBytes("esc_brasao") != null) {
                    ImageIcon foto1 = new ImageIcon(rs.getBytes("esc_brasao"));
                    vfoto2 = rs.getBytes("esc_brasao");
                    foto1.setImage(foto1.getImage().getScaledInstance(lblFoto2.getWidth(), lblFoto2.getHeight(), Image.SCALE_AREA_AVERAGING));
                    lblFoto2.setIcon(foto1);
                    vfoto2 = null;
                } else {
                    ImageIcon foto1 = new ImageIcon(localfoto2);
                    foto1.setImage(foto1.getImage().getScaledInstance(lblFoto2.getWidth(), lblFoto2.getHeight(), Image.SCALE_AREA_AVERAGING));
                    lblFoto2.setIcon(foto1);
                }

            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao carregar informações da escola " + e);
        }

    }

    private void modulo() throws Exception {
        String sql2 = "select * from usuarios where iduso=?";

        try {
            pst = conexao.prepareStatement(sql2);
            pst.setString(1, txtIdUso.getText());
            rs = pst.executeQuery();

            if (rs.next()) {

                txtLicNome.setText(rs.getString("uso_nome"));
                txtLicEmail.setText(rs.getString("uso_email"));
                txtLicCpf.setText(rs.getString("uso_cpf"));
                txtLicCel.setText(rs.getString("uso_celular"));

            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao carregar informações do usuário " + e);
        }

        String mode = Modulo.modulo1(txtLicNome.getText() + txtLicEmail.getText() + txtLicCpf.getText() + txtIdUso.getText());
        txtLic.setText(mode);

    }

    private void modulogeral() throws Exception {

        String sql = "insert into licenca (idcli,lic_chave) values (?,?)";

        try {

        } catch (Exception e) {
        }

    }

    private void preenchecmb() {

        String sql2 = "select * from componentes";

        try {
            pst = conexao.prepareStatement(sql2);
            rs = pst.executeQuery();
            rs.first();

            do {
                cmbcomp.addItem(rs.getString("comp_nome"));

            } while (rs.next());

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao preencher componentes: " + e);
        }

    }

    public TelaConfiguracao() {
        initComponents();

        conexao = ModuloConexao.conector();
        preenchecmb();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNomeUso = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEndUso = new javax.swing.JTextField();
        txtNumEnd = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCidadeUso = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUfUso = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCepUso = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCpfUso = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCelUso = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEmailUso = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        jLabel11 = new javax.swing.JLabel();
        btnSalvaUso = new javax.swing.JButton();
        btnEditUso = new javax.swing.JButton();
        txtUso = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtIdUso = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtNomeEscola = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtEndEsc = new javax.swing.JTextField();
        txtNumEsco = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtCidadeEscola = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtUfEscola = new javax.swing.JTextField();
        btnSalvaEsc = new javax.swing.JButton();
        btnEditEsc = new javax.swing.JButton();
        txtNomeEstado = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtSecretaria = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnTrocaBras = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        lblFoto2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnDelComp = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblComp = new javax.swing.JTable();
        cmbcomp = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtLic = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblDescricao = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        btnRenova = new javax.swing.JButton();
        txtLicDataLic = new javax.swing.JFormattedTextField();
        txtLicVal = new javax.swing.JFormattedTextField();
        jLabel25 = new javax.swing.JLabel();
        txtLicNome = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtLicEmail = new javax.swing.JTextField();
        txtLicCpf = new javax.swing.JFormattedTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtLicCel = new javax.swing.JFormattedTextField();
        btnComprar = new javax.swing.JButton();
        btnAtiva = new javax.swing.JButton();
        txtLicAut = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();

        setClosable(true);

        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusLost(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nome Completo:");

        txtNomeUso.setEnabled(false);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nº:");

        txtEndUso.setEnabled(false);

        txtNumEnd.setEnabled(false);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Endereço:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Cidade:");

        txtCidadeUso.setEnabled(false);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("UF:");

        txtUfUso.setEnabled(false);
        txtUfUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUfUsoActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("CEP:");

        try {
            txtCepUso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCepUso.setEnabled(false);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("CPF:");

        try {
            txtCpfUso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpfUso.setEnabled(false);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Celuilar:");

        try {
            txtCelUso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #.####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCelUso.setEnabled(false);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("E-mail:");

        txtEmailUso.setEnabled(false);

        txtSenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSenha.setEnabled(false);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Senha:");

        btnSalvaUso.setText("Salvar");
        btnSalvaUso.setEnabled(false);
        btnSalvaUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvaUsoActionPerformed(evt);
            }
        });

        btnEditUso.setText("Editar Informações");
        btnEditUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditUsoActionPerformed(evt);
            }
        });

        txtUso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUso.setEnabled(false);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Usuário:");

        jLabel30.setText("(*) Todos os campos são obrigatórios");

        txtIdUso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdUso.setEnabled(false);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("ID:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCidadeUso, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUfUso, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNomeUso, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtEndUso, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNumEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtIdUso, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmailUso, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCpfUso, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCelUso, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(txtUso)
                            .addComponent(txtSenha)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCepUso, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnEditUso, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvaUso, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCidadeUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUfUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEndUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmailUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCpfUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCepUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditUso, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvaUso, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Informações do Usuário", jPanel3);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Nome da Escola:");

        txtNomeEscola.setEnabled(false);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Nº:");

        txtEndEsc.setEnabled(false);

        txtNumEsco.setEnabled(false);
        txtNumEsco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumEscoActionPerformed(evt);
            }
        });

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Endereço:");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Cidade:");

        txtCidadeEscola.setEnabled(false);
        txtCidadeEscola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCidadeEscolaActionPerformed(evt);
            }
        });

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("UF:");

        txtUfEscola.setEnabled(false);

        btnSalvaEsc.setText("Salvar");
        btnSalvaEsc.setEnabled(false);
        btnSalvaEsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvaEscActionPerformed(evt);
            }
        });

        btnEditEsc.setText("Editar Informações");
        btnEditEsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditEscActionPerformed(evt);
            }
        });

        txtNomeEstado.setEnabled(false);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Nome do Estado:");

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Secretaria:");

        txtSecretaria.setEnabled(false);

        btnTrocaBras.setText("Trocar Brasão");
        btnTrocaBras.setEnabled(false);
        btnTrocaBras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrocaBrasActionPerformed(evt);
            }
        });

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Tamanho 50x71 px");

        lblFoto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/professorfacil/icones/brasao50x71.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFoto2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTrocaBras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(134, 134, 134))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnTrocaBras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addGap(5, 5, 5))
                    .addComponent(lblFoto2))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNomeEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnEditEsc, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalvaEsc, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCidadeEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUfEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEndEsc, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumEsco, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSecretaria, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSecretaria, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCidadeEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUfEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEndEsc, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumEsco, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEditEsc, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(btnSalvaEsc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Informações da Escola", jPanel4);

        btnDelComp.setText("Remover Componente");
        btnDelComp.setEnabled(false);
        btnDelComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelCompActionPerformed(evt);
            }
        });

        tblComp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Componentes"
            }
        ));
        tblComp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblCompFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblCompFocusLost(evt);
            }
        });
        tblComp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCompMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblComp);

        cmbcomp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Não Selecionado" }));
        cmbcomp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbcompActionPerformed(evt);
            }
        });

        btnAdd.setText("Adicionar");
        btnAdd.setEnabled(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cmbcomp, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDelComp, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbcomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelComp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Componentes Curriculares", jPanel6);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("CÓDIGO DO PRODUTO");

        txtLic.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Informações"));

        lblDescricao.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblDescricao.setForeground(new java.awt.Color(0, 204, 204));
        lblDescricao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescricao.setText("Não Licenciado");

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Data do licenciamento:");

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("Válido Até:");

        btnRenova.setText("Renovar");

        try {
            txtLicDataLic.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtLicDataLic.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        try {
            txtLicVal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtLicVal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtLicVal)
                    .addComponent(btnRenova, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addComponent(txtLicDataLic, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLicDataLic, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLicVal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRenova)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("Nome:");

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("E-mail:");

        try {
            txtLicCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("CPF:");

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Celuilar:");

        try {
            txtLicCel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #.####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtLicCel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLicCelActionPerformed(evt);
            }
        });

        btnComprar.setText("Comprar");
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        btnAtiva.setText("Ativar");
        btnAtiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtivaActionPerformed(evt);
            }
        });

        txtLicAut.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("CHAVE DE LICENÇA");

        jLabel10.setText("Atenção!");

        jLabel32.setText("Verifique as informações abaixo se estão todas corretas e clieque em comprar!");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLicNome)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtLicCel, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtLicCpf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtLicEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtLic, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLicAut, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAtiva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnComprar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(108, 108, 108))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLicNome, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLicEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLicCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLicCel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComprar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLicAut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtiva))
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Informações de Licença", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUfUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUfUsoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUfUsoActionPerformed

    private void txtCidadeEscolaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCidadeEscolaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCidadeEscolaActionPerformed

    private void txtNumEscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumEscoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumEscoActionPerformed

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
        preenchecamos();        // TODO add your handling code here:
        preenchetabelacomp();
        try {
            modulo();
        } catch (Exception ex) {
            Logger.getLogger(TelaConfiguracao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jTabbedPane1FocusGained
    String localfoto2 = "src/br/com/professorfacil/icones/brasao50x71.png";
    BufferedImage foto002 = null;
    public static byte[] vfoto2 = null;

    private void btnTrocaBrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrocaBrasActionPerformed
        JFileChooser foto2 = new JFileChooser(); //intanciando o metodo para colocar a foto
        foto2.setDialogTitle("Selecione a Foto de Frente"); //tirulo da caixa de dialogo
        foto2.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.IMAGE", "jpg", "png", "JPEG", "JPG", "PNG");
        foto2.addChoosableFileFilter(filtro);
        foto2.setFileSelectionMode(JFileChooser.FILES_ONLY);//setando para selecionar apenas um arquivo

        // C:\Users\User\Documents\NetBeansProjects\professorfacil\src\br\com\professorfacil\icones\buscarlivro200x268.jpg
        //abaixo eu estou criando uma variavel caso abra o arquivo
        int deucerto = foto2.showOpenDialog(this);
        if (deucerto == JFileChooser.APPROVE_OPTION) {//casoo de certo a busca pelos arquivos ele faz isso 

            File fotoselecionada = foto2.getSelectedFile();//jogando pra dentro da variavel o arquivo
            String caminho = fotoselecionada.getAbsolutePath();//jogando para dentro da string o caminho
            localfoto2 = caminho;

            foto002 = ManipularImagem.setImagemDimensao(caminho, lblFoto2.getWidth(), lblFoto2.getHeight());
            vfoto2 = ManipularImagem.getImgBytes(foto002);

            ImageIcon ft = new ImageIcon(foto2.getSelectedFile().getPath());
            lblFoto2.setIcon(new ImageIcon(ft.getImage().getScaledInstance(lblFoto2.getWidth(), lblFoto2.getHeight(), Image.SCALE_AREA_AVERAGING)));//colocando a foto do tamanho do jlabel foto

        } else {

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTrocaBrasActionPerformed

    private void btnDelCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelCompActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Deletar componente selecionado?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {
            delcomponente();
            preenchetabelacomp();
            btnDelComp.setEnabled(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDelCompActionPerformed

    private void tblCompFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblCompFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCompFocusGained

    private void tblCompFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblCompFocusLost

    }//GEN-LAST:event_tblCompFocusLost

    private void tblCompMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCompMouseClicked
        btnDelComp.setEnabled(true);
    }//GEN-LAST:event_tblCompMouseClicked

    private void cmbcompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbcompActionPerformed
        if (cmbcomp.getSelectedItem().equals("Não Selecionado")) {
            btnAdd.setEnabled(false);
        } else {
            btnAdd.setEnabled(true);
        }

    }//GEN-LAST:event_cmbcompActionPerformed
    private void addcomponenteuso() {

        String sql = "insert into compselecionado (iduso,nomecomponente) values (?,?)";

        try {

            pst = conexao.prepareStatement(sql); //preparando a conexão
            pst.setString(1, txtIdUso.getText());
            pst.setString(2, cmbcomp.getSelectedItem().toString());

            pst.executeUpdate();//caso a adição for concluida cai no if

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao buscar componentes: " + e);
        }

    }

    private void delcomponente() {

        String sql = "delete from compselecionado where idcompselec=?";

        int comp = tblComp.getSelectedRow();
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tblComp.getValueAt(comp, 0).toString());
            pst.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao apagar componentes: " + e);
        }

    }

    private void formatatblcomp() {

        tblComp.getColumnModel().getColumn(0).setMaxWidth(30);
        tblComp.getColumnModel().getColumn(0).setResizable(false);

    }

    private void preenchetabelacomp() {

        String sql = "select idcompselec as ID,nomecomponente as Componente from compselecionado where iduso =?";

        try {
            pst = conexao.prepareCall(sql);
            pst.setString(1, txtIdUso.getText());
            rs = pst.executeQuery();

            tblComp.setModel((DbUtils.resultSetToTableModel(rs)));
            formatatblcomp();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao buscar componentes: " + e);
        }

    }


    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (txtIdUso.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "(*)Selecione um usuário para adicioar ou deletar componentes!");
        } else {
            int confirma = JOptionPane.showConfirmDialog(null, "Deseja adicionar esse componente?", "Atenção", JOptionPane.YES_NO_OPTION);

            if (confirma == JOptionPane.YES_OPTION) {
                addcomponenteuso();
                preenchetabelacomp();
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void jTabbedPane1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1FocusLost

    private void preencheLIC() {

        String sql = "update usuarios set uso_nome=?,uso_email=?,uso_cpf=?,uso_celular=? where iduso=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtLicNome.getText());
            pst.setString(2, txtLicEmail.getText());
            pst.setString(3, txtLicCpf.getText());
            pst.setString(4, txtLicCel.getText());
            pst.setString(5, txtIdUso.getText());
            pst.executeUpdate();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao solicitar compra da licença! " + e);
        }

        String sql2 = "update licenca set lic_nomecli=?,lic_emailcli=?,lic_cpfcli=?,lic_celularcli=?,lic_chave=?,lic_descricao=? where idcli=?";

        try {
            pst = conexao.prepareStatement(sql2);

            pst.setString(1, txtLicNome.getText());
            pst.setString(2, txtLicEmail.getText());
            pst.setString(3, txtLicCpf.getText());
            pst.setString(4, txtLicCel.getText());
            pst.setString(5, txtLic.getText());
            pst.setString(6, "Em Processamento");
            pst.setString(7, txtIdUso.getText());

            int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Licença solicitada com sucesso!");
                lblDescricao.setForeground(new Color(0, 61, 250));
                lblDescricao.setText("Em Processamento");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao solicitar licença " + e);
        }
    }
    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
        if (txtLicNome.getText().isEmpty() || txtLicEmail.getText().isEmpty() || txtLicCpf.getText().isEmpty() || txtLicCel.getText().isEmpty() || txtLic.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Os campos de informações são obrigatórios!");

        }
        if (lblDescricao.getText().equals("Em Processamento")) {

            JOptionPane.showMessageDialog(null, "Solicitação de licença já realizada! \nAguarde que um atendente entrará em contato o mais breve possível!\nCaso necessário pode chamar nos contatos\nPor telefone: (69) 9.9209-9315\nPor e-mail: compra@professorfacil.com.br");

        } else {

            int confirma = JOptionPane.showConfirmDialog(null, "Para comprar sua licença siga os passos!"
                    + "\nVERIFIQUE SE TODAS AS SUAS INFORMAÇÕES ESTÃO CORRETAS"
                    + "\nPor telefone: (69) 9.9209-9315"
                    + "\nPor e-mail: compra@professorfacil.com.br"
                    + "\nInforme a chave do produto:"
                    + "\n" + txtLic.getText()
                    + "\nCONFIRMA A SOLICITAÇÃO DE LICENÇA?", "Atenção", JOptionPane.YES_NO_OPTION);

            if (confirma == JOptionPane.YES_OPTION) {

                preencheLIC();

            }
        }
    }//GEN-LAST:event_btnComprarActionPerformed

    private void txtLicCelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLicCelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLicCelActionPerformed
    private void editauso() {

        String sql = "update usuarios set uso_nome=?,uso_cidade=?,uso_estado=?,uso_endereco=?,uso_end_num=?,uso_email=?,uso_cpf=?,uso_celular=?,uso_cep=?,uso_usuario=?,uso_senha=? where iduso=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeUso.getText());
            pst.setString(2, txtCidadeUso.getText());
            pst.setString(3, txtUfUso.getText());
            pst.setString(4, txtEndUso.getText());
            pst.setString(5, txtNumEnd.getText());
            pst.setString(6, txtEmailUso.getText());
            pst.setString(7, txtCpfUso.getText());
            pst.setString(8, txtCelUso.getText());
            pst.setString(9, txtCepUso.getText());
            pst.setString(10, txtUso.getText());
            pst.setString(11, txtSenha.getText());
            pst.setString(12, txtIdUso.getText());

            int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao alterar cadastro: " + e);
        }

    }

    private void preencheralterado() {

        String sql = "select * from usuarios where iduso=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdUso.getText());
            rs = pst.executeQuery();

            if (rs.next()) {

                txtNomeUso.setText(rs.getString("uso_nome"));
                txtCidadeUso.setText(rs.getString("uso_cidade"));
                txtUfUso.setText(rs.getString("uso_estado"));
                txtEndUso.setText(rs.getString("uso_endereco"));
                txtNumEnd.setText(rs.getString("uso_end_num"));
                txtEmailUso.setText(rs.getString("uso_email"));
                txtCpfUso.setText(rs.getString("uso_cpf"));
                txtCelUso.setText(rs.getString("uso_celular"));
                txtCepUso.setText(rs.getString("uso_cep"));
                txtUso.setText(rs.getString("uso_usuario"));
                txtSenha.setText(rs.getString("uso_senha"));

            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao carregar informações do usuário " + e);
        }
    }

    private void desativacamposuso() {
        txtNomeUso.setEnabled(false);
        txtCidadeUso.setEnabled(false);
        txtUfUso.setEnabled(false);
        txtEndUso.setEnabled(false);
        txtNumEnd.setEnabled(false);
        txtEmailUso.setEnabled(false);
        txtCpfUso.setEnabled(false);
        txtCelUso.setEnabled(false);
        txtCepUso.setEnabled(false);
        txtUso.setEnabled(false);
        txtSenha.setEnabled(false);
        btnSalvaUso.setEnabled(false);
        btnEditUso.setEnabled(true);

    }

    private void ativacamposuso() {
        txtNomeUso.setEnabled(true);
        txtCidadeUso.setEnabled(true);
        txtUfUso.setEnabled(true);
        txtEndUso.setEnabled(true);
        txtNumEnd.setEnabled(true);
        txtEmailUso.setEnabled(true);
        txtCpfUso.setEnabled(true);
        txtCelUso.setEnabled(true);
        txtCepUso.setEnabled(true);
        txtUso.setEnabled(true);
        txtSenha.setEnabled(true);
        btnSalvaUso.setEnabled(true);
        btnEditUso.setEnabled(false);

    }

    private void btnSalvaUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvaUsoActionPerformed
        if (txtNomeUso.getText().isEmpty()
                || txtCidadeUso.getText().isEmpty()
                || txtUfUso.getText().isEmpty()
                || txtEndUso.getText().isEmpty()
                || txtNumEnd.getText().isEmpty()
                || txtEmailUso.getText().isEmpty()
                || txtCpfUso.getText().isEmpty()
                || txtCelUso.getText().isEmpty()
                || txtCepUso.getText().isEmpty()
                || txtUso.getText().isEmpty()
                || txtSenha.getText().isEmpty()
                || btnSalvaUso.getText().isEmpty()
                || btnEditUso.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios e não podem estar em branco!");
        } else {
            editauso();
            preencheralterado();
            desativacamposuso();
        }

    }//GEN-LAST:event_btnSalvaUsoActionPerformed

    private void btnEditUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditUsoActionPerformed
        ativacamposuso();        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditUsoActionPerformed
    private void setaescola() {
        String sql1 = "select * from escola where idcli=?";

        try {
            pst = conexao.prepareStatement(sql1);
            pst.setString(1, txtIdUso.getText());
            rs = pst.executeQuery();

            if (rs.next()) {

                txtNomeEstado.setText(rs.getString("esc_estado"));
                txtSecretaria.setText(rs.getString("esc_secretaria"));
                txtNomeEscola.setText(rs.getString("esc_ecola"));
                txtCidadeEscola.setText(rs.getString("esc_cidade"));
                txtEndEsc.setText(rs.getString("esc_endereco"));
                txtUfEscola.setText(rs.getString("esc_uf"));
                txtNumEsco.setText(rs.getString("esc_num"));

                if (rs.getBytes("esc_brasao") != null) {
                    ImageIcon foto1 = new ImageIcon(rs.getBytes("esc_brasao"));
                    vfoto2 = rs.getBytes("esc_brasao");
                    foto1.setImage(foto1.getImage().getScaledInstance(lblFoto2.getWidth(), lblFoto2.getHeight(), Image.SCALE_AREA_AVERAGING));
                    lblFoto2.setIcon(foto1);
                    vfoto2 = null;
                } else {
                    ImageIcon foto1 = new ImageIcon(localfoto2);
                    foto1.setImage(foto1.getImage().getScaledInstance(lblFoto2.getWidth(), lblFoto2.getHeight(), Image.SCALE_AREA_AVERAGING));
                    lblFoto2.setIcon(foto1);
                }

            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao carregar informações da escola " + e);
        }

    }

    private void editaescola() {
        String sql = "update escola set esc_estado=?,esc_secretaria=?,esc_ecola=?,esc_cidade=?,esc_endereco=?,esc_uf=?,esc_num=?,esc_brasao=? where idcli=?";

        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeEstado.getText());
            pst.setString(2, txtSecretaria.getText());
            pst.setString(3, txtNomeEscola.getText());
            pst.setString(4, txtCidadeEscola.getText());
            pst.setString(5, txtEndEsc.getText());
            pst.setString(6, txtUfEscola.getText());
            pst.setString(7, txtNumEsco.getText());

            if (vfoto2 == null) {

                String caminho = localfoto2;
                foto002 = ManipularImagem.setImagemDimensao(caminho, lblFoto2.getWidth(), lblFoto2.getHeight());
                vfoto2 = ManipularImagem.getImgBytes(foto002);
                pst.setBytes(8, vfoto2);
            } else {
                pst.setBytes(8, vfoto2);
            }
            pst.setString(9, txtIdUso.getText());

            int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Informações da escola alteradas com sucesso!");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao editar escola: " + e);
        }

    }

    private void btnSalvaEscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvaEscActionPerformed
        if (txtNomeEstado.getText().isEmpty()
                || txtSecretaria.getText().isEmpty()
                || txtNomeEscola.getText().isEmpty()
                || txtCidadeEscola.getText().isEmpty()
                || txtEndEsc.getText().isEmpty()
                || txtUfEscola.getText().isEmpty()
                || txtNumEsco.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios e não podem estar em branco!");
        } else {

            editaescola();
            desativacamposescola();
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnSalvaEscActionPerformed
    private void ativacamposescola() {

        txtNomeEstado.setEnabled(true);
        txtSecretaria.setEnabled(true);
        txtNomeEscola.setEnabled(true);
        txtCidadeEscola.setEnabled(true);
        txtEndEsc.setEnabled(true);
        txtUfEscola.setEnabled(true);
        txtNumEsco.setEnabled(true);
        btnSalvaEsc.setEnabled(true);
        btnTrocaBras.setEnabled(true);
        btnEditEsc.setEnabled(false);
    }

    private void desativacamposescola() {
        txtNomeEstado.setEnabled(false);
        txtSecretaria.setEnabled(false);
        txtNomeEscola.setEnabled(false);
        txtCidadeEscola.setEnabled(false);
        txtEndEsc.setEnabled(false);
        txtUfEscola.setEnabled(false);
        txtNumEsco.setEnabled(false);
        btnSalvaEsc.setEnabled(false);
        btnTrocaBras.setEnabled(false);
        btnEditEsc.setEnabled(true);

    }
    private void btnEditEscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditEscActionPerformed
        ativacamposescola();
    }//GEN-LAST:event_btnEditEscActionPerformed
    private void ativalicenca() throws Exception {

        String mode = Modulo.modulo1(txtLicNome.getText() + txtLicEmail.getText() + txtLicCpf.getText() + txtIdUso.getText());
        txtLic.setText(mode);

        String mod2 = Modulo.modulo1(txtLic.getText() + txtIdUso.getText());

        if (txtLicAut.getText().equals(mod2)) {

            JOptionPane.showMessageDialog(null, "Sistema Licenciado obrigado pela compra!");

            System.out.println(mod2);
        } else {

            JOptionPane.showMessageDialog(null, "A chave informada ou as informações de usuário estão erradas!\nVerifique as informações enviadas para o seu e-mail.\nCaso tenha duvidas entrar em contato pelo número (69) 9.99209-9315");
            System.out.println(mod2);
        }
    }
    private void btnAtivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtivaActionPerformed
        try {
            ativalicenca();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "erro interno!" + ex);

        }
    }//GEN-LAST:event_btnAtivaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAtiva;
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnDelComp;
    private javax.swing.JButton btnEditEsc;
    private javax.swing.JButton btnEditUso;
    private javax.swing.JButton btnRenova;
    private javax.swing.JButton btnSalvaEsc;
    private javax.swing.JButton btnSalvaUso;
    private javax.swing.JButton btnTrocaBras;
    private javax.swing.JComboBox<String> cmbcomp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblFoto2;
    private javax.swing.JTable tblComp;
    private javax.swing.JFormattedTextField txtCelUso;
    private javax.swing.JFormattedTextField txtCepUso;
    private javax.swing.JTextField txtCidadeEscola;
    private javax.swing.JTextField txtCidadeUso;
    private javax.swing.JFormattedTextField txtCpfUso;
    private javax.swing.JTextField txtEmailUso;
    private javax.swing.JTextField txtEndEsc;
    private javax.swing.JTextField txtEndUso;
    private javax.swing.JTextField txtIdUso;
    private javax.swing.JTextField txtLic;
    private javax.swing.JTextField txtLicAut;
    private javax.swing.JFormattedTextField txtLicCel;
    private javax.swing.JFormattedTextField txtLicCpf;
    private javax.swing.JFormattedTextField txtLicDataLic;
    private javax.swing.JTextField txtLicEmail;
    private javax.swing.JTextField txtLicNome;
    private javax.swing.JFormattedTextField txtLicVal;
    private javax.swing.JTextField txtNomeEscola;
    private javax.swing.JTextField txtNomeEstado;
    private javax.swing.JTextField txtNomeUso;
    private javax.swing.JTextField txtNumEnd;
    private javax.swing.JTextField txtNumEsco;
    private javax.swing.JTextField txtSecretaria;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUfEscola;
    private javax.swing.JTextField txtUfUso;
    private javax.swing.JTextField txtUso;
    // End of variables declaration//GEN-END:variables
}
