package br.com.professorfacil.telas;

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
import br.com.professorfacil.classes.ManipularImagem;

public class TelaAdministrador extends javax.swing.JInternalFrame {

    int salvo = 1;

    public static TelaAdministrador telaAdministrador;
    Connection conexao = null;//usando o metodo de conexao e atribuindo a conexao limpa para iniciar
    PreparedStatement pst = null; //usado para preparar a conexao com o banco de dados
    ResultSet rs = null;//exibe o resultado das instruçoes sql que sera usado no java

    public static TelaAdministrador getInstancia() {
        if (telaAdministrador == null) {

            telaAdministrador = new TelaAdministrador();
        }
        return telaAdministrador;
    }

    public void formatatabela() {
        //colocando a largura maxima na coluna
        //Coluna ID
        tblBuscaCadastro.getColumnModel().getColumn(0).setPreferredWidth(30);
        //tblBuscaCadastro.getColumnModel().getColumn(0).setMaxWidth(30);
        tblBuscaCadastro.getColumnModel().getColumn(0).setMaxWidth(30);
        tblBuscaCadastro.getColumnModel().getColumn(0).setResizable(false);
        //Coluna Telefone
        tblBuscaCadastro.getColumnModel().getColumn(2).setPreferredWidth(90);
        //tblBuscaCadastro.getColumnModel().getColumn(0).setMaxWidth(30);
        tblBuscaCadastro.getColumnModel().getColumn(2).setMaxWidth(90);
        tblBuscaCadastro.getColumnModel().getColumn(2).setResizable(false);

        //Coluna Licensa
        tblBuscaCadastro.getColumnModel().getColumn(3).setPreferredWidth(75);
        //tblBuscaCadastro.getColumnModel().getColumn(0).setMaxWidth(30);
        tblBuscaCadastro.getColumnModel().getColumn(3).setMaxWidth(75);
        tblBuscaCadastro.getColumnModel().getColumn(3).setResizable(false);

        //Coluna Data
        tblBuscaCadastro.getColumnModel().getColumn(4).setPreferredWidth(120);
        //tblBuscaCadastro.getColumnModel().getColumn(0).setMaxWidth(30);
        tblBuscaCadastro.getColumnModel().getColumn(4).setMaxWidth(120);
        tblBuscaCadastro.getColumnModel().getColumn(4).setResizable(false);
        //nomemando a coluna da tabela
        tblBuscaCadastro.getColumnModel().getColumn(4).setHeaderValue("Data Cadastro");

    }

    private void formatatblcomp() {

        tblComp.getColumnModel().getColumn(0).setMaxWidth(30);
        tblComp.getColumnModel().getColumn(0).setResizable(false);

    }

    private void formatatabelacomp1() {

        tblComp1.getColumnModel().getColumn(0).setMaxWidth(30);
        tblComp1.getColumnModel().getColumn(0).setResizable(false);
        tblComp1.getColumnModel().getColumn(0).setHeaderValue("ID");
        tblComp1.getColumnModel().getColumn(1).setHeaderValue("Componente");

    }

    private void formatatabelaLivro() {

        tblLivro.getColumnModel().getColumn(0).setMaxWidth(30);
        tblLivro.getColumnModel().getColumn(0).setResizable(false);
        tblLivro.getColumnModel().getColumn(0).setHeaderValue("ID");
        tblLivro.getColumnModel().getColumn(1).setResizable(false);
        tblLivro.getColumnModel().getColumn(1).setHeaderValue("Nome do Livro");
        tblLivro.getColumnModel().getColumn(2).setMaxWidth(90);
        tblLivro.getColumnModel().getColumn(2).setResizable(false);
        tblLivro.getColumnModel().getColumn(2).setHeaderValue("Componente");
        tblLivro.getColumnModel().getColumn(3).setMaxWidth(75);
        tblLivro.getColumnModel().getColumn(3).setResizable(false);
        tblLivro.getColumnModel().getColumn(3).setHeaderValue("Editora");
        tblLivro.getColumnModel().getColumn(4).setMaxWidth(70);
        tblLivro.getColumnModel().getColumn(4).setResizable(false);
        tblLivro.getColumnModel().getColumn(4).setHeaderValue("Série");
        tblLivro.getColumnModel().getColumn(5).setMaxWidth(85);
        tblLivro.getColumnModel().getColumn(5).setResizable(false);
        tblLivro.getColumnModel().getColumn(5).setHeaderValue("Período");

    }

    private void formatatabelaedit1() {

        tblEditora.getColumnModel().getColumn(0).setMaxWidth(30);
        tblEditora.getColumnModel().getColumn(0).setResizable(false);
        tblEditora.getColumnModel().getColumn(0).setHeaderValue("ID");
        tblEditora.getColumnModel().getColumn(1).setHeaderValue("Editora");

    }

    private void formatatabelaserie1() {

        tblSerie.getColumnModel().getColumn(0).setMaxWidth(30);
        tblSerie.getColumnModel().getColumn(0).setResizable(false);
        tblSerie.getColumnModel().getColumn(0).setHeaderValue("ID");
        tblSerie.getColumnModel().getColumn(1).setHeaderValue("Série/Turma");

    }

    private void preenchercomp1() {

        String sql = "select * from componentes";

        try {
            pst = conexao.prepareCall(sql);
            rs = pst.executeQuery();

            tblComp1.setModel((DbUtils.resultSetToTableModel(rs)));
            formatatabelacomp1();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao buscar componentes: " + e);
        }

    }

    private void preencherlivros1() {

        String sql = "select idliv,liv_nome,liv_componente,liv_editora,liv_serie,liv_periodo from livro";

        try {
            pst = conexao.prepareCall(sql);
            rs = pst.executeQuery();

            tblLivro.setModel((DbUtils.resultSetToTableModel(rs)));
            formatatabelaLivro();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao buscar Livros: " + e);
        }

    }

    private void preenchereditora1() {

        String sql = "select * from editora";

        try {
            pst = conexao.prepareCall(sql);
            rs = pst.executeQuery();

            tblEditora.setModel((DbUtils.resultSetToTableModel(rs)));
            formatatabelaedit1();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao buscar Editoras: " + e);
        }

    }

    private void preencherserie1() {

        String sql = "select * from serie";

        try {
            pst = conexao.prepareCall(sql);
            rs = pst.executeQuery();

            tblSerie.setModel((DbUtils.resultSetToTableModel(rs)));
            formatatabelaserie1();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao buscar Editoras: " + e);
        }

    }

    private void preenchercadcomp() {
        String sql = "select * from componentes where idcomponente= ?";
        int line = tblComp1.getSelectedRow();
        String tabcomp = tblComp1.getValueAt(line, 0).toString();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tabcomp);
            rs = pst.executeQuery();

            if (rs.next()) {

                txtIdComp.setText(rs.getString("idcomponente"));
                txtNomeComp.setText(rs.getString("comp_nome"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar componentes: " + e);
        }
    }

    private void preenchercamplivro() {
        String sql = "select * from livro where idliv= ?";
        int line = tblLivro.getSelectedRow();
        String tabcomp = tblLivro.getValueAt(line, 0).toString();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tabcomp);
            rs = pst.executeQuery();

            if (rs.next()) {

                txtIdLivro.setText(rs.getString("idliv"));
                cmbCompLivro.setSelectedItem(rs.getString("liv_componente"));
                txtNomeAutLivro.setText(rs.getString("liv_nomeautor"));
                txtNomeLivro.setText(rs.getString("liv_nome"));
                cmbEditLivro.setSelectedItem(rs.getString("liv_editora"));
                txtEdicaoLivro.setText(rs.getString("liv_edicao"));
                txtAnoLivro.setText(rs.getString("liv_ano"));
                cmbSerieLivro.setSelectedItem(rs.getString("liv_serie"));
                txtPeriodoLivro.setText(rs.getString("liv_periodo"));
                txtObservaLivro.setText(rs.getString("liv_observacoes"));
                if (rs.getBytes("liv_foto") != null) {
                    ImageIcon foto1 = new ImageIcon(rs.getBytes("liv_foto"));
                    vfoto2 = rs.getBytes("liv_foto");
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
            JOptionPane.showMessageDialog(null, "Erro ao buscar informações do livro " + e);
        }
    }

    private void preenchercampeditora() {
        String sql = "select * from editora where ideditora= ?";
        int line = tblEditora.getSelectedRow();
        String tabcomp = tblEditora.getValueAt(line, 0).toString();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tabcomp);
            rs = pst.executeQuery();

            if (rs.next()) {

                txtIdedit.setText(rs.getString("ideditora"));
                txtNomeedit.setText(rs.getString("edit_nome"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar editora: " + e);
        }
    }

    private void preenchercampserie() {
        String sql = "select * from serie where idserie= ?";
        int line = tblSerie.getSelectedRow();
        String tabcomp = tblSerie.getValueAt(line, 0).toString();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tabcomp);
            rs = pst.executeQuery();

            if (rs.next()) {

                txtIdSerie.setText(rs.getString("idserie"));
                txtNomeSerie.setText(rs.getString("seri_nivel"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar editora: " + e);
        }
    }

    public void preencher() {
        String sql = "select * from usuarios where iduso= ?";
        int linha = tblBuscaCadastro.getSelectedRow();

        String tabela = tblBuscaCadastro.getValueAt(linha, 0).toString();//linha é alinha selecionada e 0 é a coluna

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tabela);
            rs = pst.executeQuery();

            if (rs.next()) {

                txtIdUso.setText(rs.getString("iduso"));
                txtNomeUso.setText(rs.getString("uso_nome"));
                txtCidadeUso.setText(rs.getString("uso_cidade"));
                txtUfUso.setText(rs.getString("uso_estado"));
                txtEnderecoUso.setText(rs.getString("uso_endereco"));
                txtNumUso.setText(rs.getString("uso_end_num"));
                txtEmailUso.setText(rs.getString("uso_email"));
                txtCpfUso.setText(rs.getString("uso_cpf"));
                txtCelUso.setText(rs.getString("uso_celular"));
                txtCepUso.setText(rs.getString("uso_cep"));
                txtUsuarioUso.setText(rs.getString("uso_usuario"));
                txtSenhaUso.setText(rs.getString("uso_senha"));
                cmbLicencaUso.setSelectedItem(rs.getString("uso_licenca"));
                txtDataLicenca.setText(rs.getString("uso_data_licensa"));
                txtValidadeLicensa.setText(rs.getString("uso_data_validade"));
                cmbPerfilUso.setSelectedItem(rs.getString("uso_perfil"));
                txtDataCadastro.setText(rs.getString("uso_datacadastro"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na busca: " + e);
        }

    }

    //abaixo e feita a busca dos cadastros e colocados diretamente na tabela 
    private void pesquisaUsuarios() {

        String sql = "select iduso as ID,uso_nome as Nome,uso_celular as Celular,uso_licenca as Licença,uso_datacadastro as Data_Cadastro from usuarios where uso_nome like ?;";

        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtBusca.getText() + "%");
            rs = pst.executeQuery();

            tblBuscaCadastro.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro na busca: " + e);
        }

    }

    private void pesquisaLivros() {

        String sql = "select idliv,liv_nome,liv_componente,liv_editora,liv_serie,liv_periodo from livro where liv_nome like ?;";

        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtBuscaLivros.getText() + "%");
            rs = pst.executeQuery();

            tblLivro.setModel(DbUtils.resultSetToTableModel(rs));
            formatatabelaLivro();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro na busca dos Livros: " + e);
        }

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

    public void preenchecmb() {

        //o metodo abaixo preenche a combobox com os itens cadastrados
        //https://www.youtube.com/watch?v=yxpgplPTIPY
        String sql = "select * from componentes";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();

            //cmbcomp1.removeAllItems();
            do {
                cmbcomp.addItem(rs.getString("comp_nome"));

            } while (rs.next());

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao preencher componentes: " + e);
        }
    }

    public void preenchecmbdoslivros() {
        cmbSerieLivro.removeAllItems();
        cmbCompLivro.removeAllItems();
        cmbEditLivro.removeAllItems();

        cmbSerieLivro.addItem("Selecione");
        cmbEditLivro.addItem("Selecione");
        cmbCompLivro.addItem("Selecione");

        //o metodo abaixo preenche a combobox com os itens cadastrados
        //https://www.youtube.com/watch?v=yxpgplPTIPY
        String sql = "select * from componentes";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.first();

            //cmbcomp1.removeAllItems();
            do {
                cmbCompLivro.addItem(rs.getString("comp_nome"));

            } while (rs.next());

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao preencher componentes: " + e);
        }

        String sql2 = "select * from serie";

        try {
            pst = conexao.prepareStatement(sql2);
            rs = pst.executeQuery();
            rs.first();

            //cmbcomp1.removeAllItems();
            do {
                cmbSerieLivro.addItem(rs.getString("seri_nivel"));

            } while (rs.next());

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao preencher Séries: " + e);
        }
        String sql3 = "select * from editora";

        try {
            pst = conexao.prepareStatement(sql3);
            rs = pst.executeQuery();
            rs.first();

            //cmbcomp1.removeAllItems();
            do {
                cmbEditLivro.addItem(rs.getString("edit_nome"));

            } while (rs.next());

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao preencher Séries: " + e);
        }

    }

    private void ativacampousuario() {

        txtNomeUso.setEnabled(true);
        txtCidadeUso.setEnabled(true);
        txtUfUso.setEnabled(true);
        txtEnderecoUso.setEnabled(true);
        txtNumUso.setEnabled(true);
        txtEmailUso.setEnabled(true);
        txtCpfUso.setEnabled(true);
        txtCelUso.setEnabled(true);
        txtCepUso.setEnabled(true);
        txtUsuarioUso.setEnabled(true);
        txtSenhaUso.setEnabled(true);
        cmbLicencaUso.setEnabled(true);
        txtDataLicenca.setEnabled(true);
        txtValidadeLicensa.setEnabled(true);
        cmbPerfilUso.setEnabled(true);
        btnCancela.setEnabled(true);
        btnSalvar.setEnabled(true);

    }

    private void limpacampos() {
        txtIdUso.setText("");
        txtNomeUso.setText("");
        txtCidadeUso.setText("");
        txtUfUso.setText("");
        txtEnderecoUso.setText("");
        txtNumUso.setText("");
        txtEmailUso.setText("");
        txtCpfUso.setText("");
        txtCelUso.setText("");
        txtCepUso.setText("");
        txtUsuarioUso.setText("");
        txtSenhaUso.setText("");
        cmbLicencaUso.setSelectedItem("Inativo");
        txtDataLicenca.setText("");
        txtValidadeLicensa.setText("");
        cmbPerfilUso.setSelectedItem("Usuario");
        txtDataCadastro.setText("");
    }

    private void desativacampouso() {

        txtNomeUso.setEnabled(false);
        txtCidadeUso.setEnabled(false);
        txtUfUso.setEnabled(false);
        txtEnderecoUso.setEnabled(false);
        txtNumUso.setEnabled(false);
        txtEmailUso.setEnabled(false);
        txtCpfUso.setEnabled(false);
        txtCelUso.setEnabled(false);
        txtCepUso.setEnabled(false);
        txtUsuarioUso.setEnabled(false);
        txtSenhaUso.setEnabled(false);
        cmbLicencaUso.setEnabled(false);
        txtDataLicenca.setEnabled(false);
        txtValidadeLicensa.setEnabled(false);
        cmbPerfilUso.setEnabled(false);
        btnCancela.setEnabled(false);
        btnSalvar.setEnabled(false);

    }

    private void buscandoultimo() {

        // String sql = "select  max(iduso) as max from usuarios";
        String sql = "select max(iduso) as max from usuarios";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery(sql);

            if (rs.next()) {

                int ultimo = rs.getInt(1);
                txtIdUso.setText(String.valueOf(ultimo));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao obter número da avaliação!" + e);
        }

    }

    private void cadastraruso() {

        String sql = "insert into usuarios (uso_nome,uso_cidade,uso_estado,uso_endereco,uso_end_num,uso_email,uso_cpf,uso_celular,uso_cep,uso_usuario,uso_senha,uso_componente,uso_licenca,uso_data_licensa,uso_data_validade,uso_perfil) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtNomeUso.getText());
            pst.setString(2, txtCidadeUso.getText());
            pst.setString(3, txtUfUso.getText());
            pst.setString(4, txtEnderecoUso.getText());
            pst.setString(5, txtNumUso.getText());
            pst.setString(6, txtEmailUso.getText());
            pst.setString(7, txtCpfUso.getText());
            pst.setString(8, txtCelUso.getText());
            pst.setString(9, txtCepUso.getText());
            pst.setString(10, txtUsuarioUso.getText());
            pst.setString(11, txtSenhaUso.getText());
            pst.setString(12, cmbcomp.getSelectedItem().toString());
            pst.setString(13, cmbLicencaUso.getSelectedItem().toString());
            pst.setString(14, txtDataLicenca.getText());
            pst.setString(15, txtValidadeLicensa.getText());
            pst.setString(16, cmbPerfilUso.getSelectedItem().toString());

            int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso!");
                limpacampos();
                pesquisaUsuarios();
                desativacampouso();
                formatatabela();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao inserir cadastro: " + e);
        }

    }

    private void editauso() {

        String sql = "update usuarios set uso_nome=?,uso_cidade=?,uso_estado=?,uso_endereco=?,uso_end_num=?,uso_email=?,uso_cpf=?,uso_celular=?,uso_cep=?,uso_usuario=?,uso_senha=?,uso_licenca=?,uso_data_licensa=?,uso_data_validade=?,uso_perfil=? where iduso=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeUso.getText());
            pst.setString(2, txtCidadeUso.getText());
            pst.setString(3, txtUfUso.getText());
            pst.setString(4, txtEnderecoUso.getText());
            pst.setString(5, txtNumUso.getText());
            pst.setString(6, txtEmailUso.getText());
            pst.setString(7, txtCpfUso.getText());
            pst.setString(8, txtCelUso.getText());
            pst.setString(9, txtCepUso.getText());
            pst.setString(10, txtUsuarioUso.getText());
            pst.setString(11, txtSenhaUso.getText());
            pst.setString(12, cmbLicencaUso.getSelectedItem().toString());
            pst.setString(13, txtDataLicenca.getText());
            pst.setString(14, txtValidadeLicensa.getText());
            pst.setString(15, cmbPerfilUso.getSelectedItem().toString());
            pst.setString(16, txtIdUso.getText());

            int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
                limpacampos();
                pesquisaUsuarios();
                desativacampouso();
                formatatabela();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao alterar cadastro: " + e);
        }

    }

    public TelaAdministrador() {
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBuscaCadastro = new javax.swing.JTable();
        txtBusca = new javax.swing.JTextField();
        btnBusca = new javax.swing.JButton();
        abaComponentes = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNomeUso = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEnderecoUso = new javax.swing.JTextField();
        txtNumUso = new javax.swing.JTextField();
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
        jLabel11 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        txtUsuarioUso = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtSenhaUso = new javax.swing.JTextField();
        cmbLicencaUso = new javax.swing.JComboBox<>();
        txtDataLicenca = new javax.swing.JFormattedTextField();
        txtValidadeLicensa = new javax.swing.JFormattedTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtIdUso = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        cmbPerfilUso = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtDataCadastro = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnNovoUso = new javax.swing.JButton();
        btnCancela = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        cmbcomp = new javax.swing.JComboBox<>();
        btnadd = new javax.swing.JButton();
        btnDelComp = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblComp = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblComp1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        txtNomeComp = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtIdComp = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btnEditComp = new javax.swing.JButton();
        btnSalvaComp = new javax.swing.JButton();
        btnInseirComp = new javax.swing.JButton();
        btnCancelarcomp = new javax.swing.JButton();
        btnDeletarRegisto = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblEditora = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        txtNomeedit = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtIdedit = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        btnEditedit = new javax.swing.JButton();
        btnSalvaedit = new javax.swing.JButton();
        btnInseiredit = new javax.swing.JButton();
        btnCancelaredit = new javax.swing.JButton();
        btnDeletaredit = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        txtNomeSerie = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtIdSerie = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        btnEditSerie = new javax.swing.JButton();
        btnSalvaSerie = new javax.swing.JButton();
        btnInseirSerie = new javax.swing.JButton();
        btnCancelarSerie = new javax.swing.JButton();
        btnDeletarSerie = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblSerie = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtNomeAutLivro = new javax.swing.JTextField();
        txtNomeLivro = new javax.swing.JTextField();
        txtEdicaoLivro = new javax.swing.JTextField();
        txtAnoLivro = new javax.swing.JTextField();
        txtPeriodoLivro = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtIdLivro = new javax.swing.JTextField();
        btnSalvaLivro = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        cmbSerieLivro = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        cmbEditLivro = new javax.swing.JComboBox<>();
        cmbCompLivro = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtObservaLivro = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        lblFoto2 = new javax.swing.JLabel();
        btnBuscaLivroFoto = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        btnCancelaLivro = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblLivro = new javax.swing.JTable();
        txtBuscaLivros = new javax.swing.JTextField();
        btnBuscaLivro = new javax.swing.JButton();
        btnNovoLivro = new javax.swing.JButton();
        btnEditLivro = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtContCont = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtHabCont = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtAtvCOnt = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtRecCont = new javax.swing.JTextArea();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        btnSalvarCont = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        txtCompCont = new javax.swing.JTextField();
        txtSerieCont = new javax.swing.JTextField();
        txtIdContAdd = new javax.swing.JTextField();
        btnExcluirCont = new javax.swing.JButton();
        btnCancelarCont = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        txtIdLivroCont = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        btnbuscCont = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblCodCont = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        btnOkCont = new javax.swing.JButton();
        txtCodCont = new javax.swing.JTextField();
        txtUnidadeTematica = new javax.swing.JTextField();

        setClosable(true);

        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
        });

        tblBuscaCadastro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Telefone", "Licença", "Data Cadastro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBuscaCadastro.getTableHeader().setReorderingAllowed(false);
        tblBuscaCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBuscaCadastroMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblBuscaCadastroMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblBuscaCadastro);
        if (tblBuscaCadastro.getColumnModel().getColumnCount() > 0) {
            tblBuscaCadastro.getColumnModel().getColumn(0).setMinWidth(30);
            tblBuscaCadastro.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblBuscaCadastro.getColumnModel().getColumn(0).setMaxWidth(30);
            tblBuscaCadastro.getColumnModel().getColumn(2).setMinWidth(90);
            tblBuscaCadastro.getColumnModel().getColumn(2).setPreferredWidth(90);
            tblBuscaCadastro.getColumnModel().getColumn(2).setMaxWidth(90);
            tblBuscaCadastro.getColumnModel().getColumn(3).setMinWidth(75);
            tblBuscaCadastro.getColumnModel().getColumn(3).setPreferredWidth(75);
            tblBuscaCadastro.getColumnModel().getColumn(3).setMaxWidth(75);
            tblBuscaCadastro.getColumnModel().getColumn(4).setMinWidth(120);
            tblBuscaCadastro.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblBuscaCadastro.getColumnModel().getColumn(4).setMaxWidth(120);
        }

        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaKeyReleased(evt);
            }
        });

        btnBusca.setText("Buscar");
        btnBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Informações do Usuário"));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("* Nome Completo:");

        txtNomeUso.setEnabled(false);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nº:");
        jLabel2.setEnabled(false);

        txtEnderecoUso.setEnabled(false);

        txtNumUso.setEnabled(false);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("* Endereço:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("* Cidade:");

        txtCidadeUso.setEnabled(false);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("UF:");
        jLabel5.setEnabled(false);

        txtUfUso.setEnabled(false);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("CEP:");

        try {
            txtCepUso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCepUso.setEnabled(false);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("* CPF:");

        try {
            txtCpfUso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpfUso.setEnabled(false);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("* Celuilar:");

        try {
            txtCelUso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #.####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCelUso.setEnabled(false);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("* E-mail:");

        txtEmailUso.setEnabled(false);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("* Senha:");

        btnEditar.setText("Editar Informações");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        txtUsuarioUso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuarioUso.setEnabled(false);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("* Usuário:");

        jLabel30.setText("(*) Campos obrigatórios");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("* Licença:");

        txtSenhaUso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSenhaUso.setEnabled(false);

        cmbLicencaUso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Ativa", "Inativa" }));
        cmbLicencaUso.setEnabled(false);

        try {
            txtDataLicenca.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataLicenca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataLicenca.setEnabled(false);

        try {
            txtValidadeLicensa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtValidadeLicensa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValidadeLicensa.setEnabled(false);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("Válido Até:");

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Data do licenciamento:");

        txtIdUso.setEditable(false);
        txtIdUso.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("ID:");

        cmbPerfilUso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Administrador", "Usuario" }));
        cmbPerfilUso.setEnabled(false);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Data Cadastro:");

        txtDataCadastro.setEditable(false);
        txtDataCadastro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataCadastro.setEnabled(false);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("* Perfil de Usuário:");

        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnNovoUso.setText("Novo Usuário");
        btnNovoUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoUsoActionPerformed(evt);
            }
        });

        btnCancela.setText("Cancelar");
        btnCancela.setEnabled(false);
        btnCancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeUso))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnNovoUso, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdUso, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel30))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDataLicenca, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbLicencaUso, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtValidadeLicensa, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCepUso, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtCelUso, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                            .addComponent(txtUsuarioUso)
                                            .addComponent(txtSenhaUso)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCpfUso, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtEmailUso, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtEnderecoUso, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtCidadeUso, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUfUso, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNumUso, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(cmbPerfilUso, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(btnCancela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txtIdUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnEditar)
                        .addGap(1, 1, 1)
                        .addComponent(btnNovoUso)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCidadeUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUfUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEnderecoUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmailUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCpfUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCepUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuarioUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSenhaUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbLicencaUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataLicenca, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValidadeLicensa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPerfilUso, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancela))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        abaComponentes.addTab("Informações do Cliente", jPanel5);

        cmbcomp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Não Selecionado" }));
        cmbcomp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbcompActionPerformed(evt);
            }
        });

        btnadd.setText("+");
        btnadd.setEnabled(false);
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnDelComp.setText("Deletar Componente");
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
        if (tblComp.getColumnModel().getColumnCount() > 0) {
            tblComp.getColumnModel().getColumn(0).setMinWidth(30);
            tblComp.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblComp.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(cmbcomp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnadd))
                    .addComponent(btnDelComp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbcomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnadd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelComp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        abaComponentes.addTab("Componentes", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusca, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(abaComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 39, Short.MAX_VALUE))
                    .addComponent(abaComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Usuários ", jPanel1);

        tblComp1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Componente Curricular"
            }
        ));
        tblComp1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblComp1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblComp1);
        if (tblComp1.getColumnModel().getColumnCount() > 0) {
            tblComp1.getColumnModel().getColumn(0).setMinWidth(30);
            tblComp1.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblComp1.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Cadastrar/Alterar Componente"));

        txtNomeComp.setEnabled(false);

        jLabel10.setText("Nome do Componente:");

        txtIdComp.setEnabled(false);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("ID:");

        btnEditComp.setText("Editar");
        btnEditComp.setEnabled(false);
        btnEditComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCompActionPerformed(evt);
            }
        });

        btnSalvaComp.setText("Salvar");
        btnSalvaComp.setEnabled(false);
        btnSalvaComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvaCompActionPerformed(evt);
            }
        });

        btnInseirComp.setText("Inserir");
        btnInseirComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInseirCompActionPerformed(evt);
            }
        });

        btnCancelarcomp.setText("Cancelar");
        btnCancelarcomp.setEnabled(false);
        btnCancelarcomp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarcompActionPerformed(evt);
            }
        });

        btnDeletarRegisto.setText("Deletar");
        btnDeletarRegisto.setEnabled(false);
        btnDeletarRegisto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarRegistoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnInseirComp, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditComp, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(btnDeletarRegisto)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCancelarcomp)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSalvaComp))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel15))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtIdComp, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNomeComp, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInseirComp)
                    .addComponent(btnEditComp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtIdComp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeComp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(34, 34, 34)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvaComp)
                    .addComponent(btnCancelarcomp)
                    .addComponent(btnDeletarRegisto))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(436, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastrar Componentes", jPanel3);

        tblEditora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Editora"
            }
        ));
        tblEditora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEditoraMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblEditora);
        if (tblEditora.getColumnModel().getColumnCount() > 0) {
            tblEditora.getColumnModel().getColumn(0).setMinWidth(30);
            tblEditora.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblEditora.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Cadastrar/Alterar Editora"));

        txtNomeedit.setEnabled(false);

        jLabel42.setText("Nome do Componente:");

        txtIdedit.setEnabled(false);

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("ID:");

        btnEditedit.setText("Editar");
        btnEditedit.setEnabled(false);
        btnEditedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditeditActionPerformed(evt);
            }
        });

        btnSalvaedit.setText("Salvar");
        btnSalvaedit.setEnabled(false);
        btnSalvaedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvaeditActionPerformed(evt);
            }
        });

        btnInseiredit.setText("Inserir");
        btnInseiredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInseireditActionPerformed(evt);
            }
        });

        btnCancelaredit.setText("Cancelar");
        btnCancelaredit.setEnabled(false);
        btnCancelaredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelareditActionPerformed(evt);
            }
        });

        btnDeletaredit.setText("Deletar");
        btnDeletaredit.setEnabled(false);
        btnDeletaredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletareditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(btnInseiredit, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditedit, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addComponent(btnDeletaredit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCancelaredit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSalvaedit))
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel42)
                                .addComponent(jLabel43))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtIdedit, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNomeedit, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInseiredit)
                    .addComponent(btnEditedit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(txtIdedit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeedit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addGap(34, 34, 34)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvaedit)
                    .addComponent(btnCancelaredit)
                    .addComponent(btnDeletaredit))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(436, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastrar Editora", jPanel9);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Cadastrar/Alterar Séries"));

        txtNomeSerie.setEnabled(false);

        jLabel44.setText("Nome do Componente:");

        txtIdSerie.setEnabled(false);

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel45.setText("ID:");

        btnEditSerie.setText("Editar");
        btnEditSerie.setEnabled(false);
        btnEditSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSerieActionPerformed(evt);
            }
        });

        btnSalvaSerie.setText("Salvar");
        btnSalvaSerie.setEnabled(false);
        btnSalvaSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvaSerieActionPerformed(evt);
            }
        });

        btnInseirSerie.setText("Inserir");
        btnInseirSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInseirSerieActionPerformed(evt);
            }
        });

        btnCancelarSerie.setText("Cancelar");
        btnCancelarSerie.setEnabled(false);
        btnCancelarSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarSerieActionPerformed(evt);
            }
        });

        btnDeletarSerie.setText("Deletar");
        btnDeletarSerie.setEnabled(false);
        btnDeletarSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarSerieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(btnInseirSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel18Layout.createSequentialGroup()
                            .addComponent(btnDeletarSerie)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCancelarSerie)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSalvaSerie))
                        .addGroup(jPanel18Layout.createSequentialGroup()
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel44)
                                .addComponent(jLabel45))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtIdSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNomeSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInseirSerie)
                    .addComponent(btnEditSerie))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(txtIdSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addGap(34, 34, 34)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvaSerie)
                    .addComponent(btnCancelarSerie)
                    .addComponent(btnDeletarSerie))
                .addContainerGap())
        );

        tblSerie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Série/Turma"
            }
        ));
        tblSerie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSerieMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tblSerie);
        if (tblSerie.getColumnModel().getColumnCount() > 0) {
            tblSerie.getColumnModel().getColumn(0).setMinWidth(30);
            tblSerie.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblSerie.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(436, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastrar Séries", jPanel14);

        jPanel4.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Nome do Livro:");
        jLabel18.setEnabled(false);

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Nome do Autor:");
        jLabel19.setEnabled(false);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Editora:");
        jLabel20.setEnabled(false);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Ano:");
        jLabel21.setEnabled(false);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Período:");
        jLabel22.setEnabled(false);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Observações:");
        jLabel23.setEnabled(false);

        txtNomeAutLivro.setEnabled(false);

        txtNomeLivro.setEnabled(false);

        txtEdicaoLivro.setEnabled(false);

        txtAnoLivro.setEnabled(false);

        txtPeriodoLivro.setEnabled(false);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("ID:");

        txtIdLivro.setEnabled(false);

        btnSalvaLivro.setText("SALVAR");
        btnSalvaLivro.setEnabled(false);
        btnSalvaLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvaLivroActionPerformed(evt);
            }
        });

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("Série/Ano:");
        jLabel41.setEnabled(false);

        cmbSerieLivro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        cmbSerieLivro.setEnabled(false);

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("Edição:");
        jLabel33.setEnabled(false);

        cmbEditLivro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        cmbEditLivro.setEnabled(false);

        cmbCompLivro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        cmbCompLivro.setEnabled(false);

        jScrollPane4.setEnabled(false);

        txtObservaLivro.setColumns(20);
        txtObservaLivro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtObservaLivro.setRows(5);
        jScrollPane4.setViewportView(txtObservaLivro);

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Componente:");
        jLabel26.setEnabled(false);

        lblFoto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/professorfacil/icones/buscarlivro200x268.jpg"))); // NOI18N

        btnBuscaLivroFoto.setText("Buscar Foto");
        btnBuscaLivroFoto.setEnabled(false);
        btnBuscaLivroFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaLivroFotoActionPerformed(evt);
            }
        });

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Foto LxA = 200x268px");

        btnCancelaLivro.setText("Cancelar");
        btnCancelaLivro.setEnabled(false);
        btnCancelaLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelaLivroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbEditLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeAutLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtEdicaoLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(txtAnoLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(cmbSerieLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(txtPeriodoLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbCompLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblFoto2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(btnBuscaLivroFoto)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCancelaLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvaLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbCompLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomeAutLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomeLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAnoLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbSerieLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(cmbEditLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEdicaoLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPeriodoLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 89, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFoto2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscaLivroFoto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalvaLivro)
                            .addComponent(btnCancelaLivro))
                        .addContainerGap())))
        );

        jPanel4.add(jPanel2);
        jPanel2.setBounds(514, 41, 640, 500);

        tblLivro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome do Livro", "Componente", "Editora", "Série", "Periodo"
            }
        ));
        tblLivro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLivroMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblLivro);
        if (tblLivro.getColumnModel().getColumnCount() > 0) {
            tblLivro.getColumnModel().getColumn(0).setMinWidth(30);
            tblLivro.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblLivro.getColumnModel().getColumn(0).setMaxWidth(30);
            tblLivro.getColumnModel().getColumn(2).setMinWidth(90);
            tblLivro.getColumnModel().getColumn(2).setPreferredWidth(90);
            tblLivro.getColumnModel().getColumn(2).setMaxWidth(90);
            tblLivro.getColumnModel().getColumn(3).setMinWidth(75);
            tblLivro.getColumnModel().getColumn(3).setPreferredWidth(75);
            tblLivro.getColumnModel().getColumn(3).setMaxWidth(75);
            tblLivro.getColumnModel().getColumn(4).setMinWidth(75);
            tblLivro.getColumnModel().getColumn(4).setPreferredWidth(75);
            tblLivro.getColumnModel().getColumn(4).setMaxWidth(75);
            tblLivro.getColumnModel().getColumn(5).setMinWidth(80);
            tblLivro.getColumnModel().getColumn(5).setPreferredWidth(80);
            tblLivro.getColumnModel().getColumn(5).setMaxWidth(80);
        }

        jPanel4.add(jScrollPane5);
        jScrollPane5.setBounds(35, 41, 469, 500);
        jPanel4.add(txtBuscaLivros);
        txtBuscaLivros.setBounds(35, 11, 380, 24);

        btnBuscaLivro.setText("Buscar");
        btnBuscaLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaLivroActionPerformed(evt);
            }
        });
        jPanel4.add(btnBuscaLivro);
        btnBuscaLivro.setBounds(421, 12, 83, 23);

        btnNovoLivro.setText("NOVO LIVRO");
        btnNovoLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoLivroActionPerformed(evt);
            }
        });
        jPanel4.add(btnNovoLivro);
        btnNovoLivro.setBounds(520, 10, 177, 23);

        btnEditLivro.setText("EDITAR LIVRO");
        btnEditLivro.setEnabled(false);
        btnEditLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditLivroActionPerformed(evt);
            }
        });
        jPanel4.add(btnEditLivro);
        btnEditLivro.setBounds(710, 10, 177, 23);

        jTabbedPane1.addTab("Cadastrar Livros", jPanel4);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jTextField22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField22.setText("Conteúdo(s)");
        jTextField22.setFocusable(false);
        jTextField22.setOpaque(false);

        jTextField23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField23.setText("Habilidade(s)");
        jTextField23.setFocusable(false);
        jTextField23.setOpaque(false);

        jTextField24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField24.setText("Atividade(s)");
        jTextField24.setFocusable(false);
        jTextField24.setOpaque(false);

        jTextField25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField25.setText("Recursos(s)");
        jTextField25.setFocusable(false);
        jTextField25.setOpaque(false);

        txtContCont.setColumns(20);
        txtContCont.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtContCont.setLineWrap(true);
        txtContCont.setRows(5);
        txtContCont.setWrapStyleWord(true);
        txtContCont.setEnabled(false);
        jScrollPane7.setViewportView(txtContCont);

        txtHabCont.setColumns(20);
        txtHabCont.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtHabCont.setLineWrap(true);
        txtHabCont.setRows(5);
        txtHabCont.setWrapStyleWord(true);
        txtHabCont.setEnabled(false);
        jScrollPane8.setViewportView(txtHabCont);

        txtAtvCOnt.setColumns(20);
        txtAtvCOnt.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtAtvCOnt.setLineWrap(true);
        txtAtvCOnt.setRows(5);
        txtAtvCOnt.setWrapStyleWord(true);
        txtAtvCOnt.setEnabled(false);
        jScrollPane9.setViewportView(txtAtvCOnt);

        txtRecCont.setColumns(20);
        txtRecCont.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtRecCont.setLineWrap(true);
        txtRecCont.setRows(5);
        txtRecCont.setWrapStyleWord(true);
        txtRecCont.setEnabled(false);
        jScrollPane10.setViewportView(txtRecCont);

        jLabel35.setText("Disciplina");

        jLabel36.setText("Ano/Turma");

        btnSalvarCont.setText("SALVAR");
        btnSalvarCont.setEnabled(false);

        jLabel38.setText("ID Conteúdo");

        txtCompCont.setEditable(false);
        txtCompCont.setBackground(new java.awt.Color(255, 255, 204));
        txtCompCont.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCompCont.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCompCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCompContActionPerformed(evt);
            }
        });

        txtSerieCont.setEditable(false);
        txtSerieCont.setBackground(new java.awt.Color(255, 255, 204));
        txtSerieCont.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSerieCont.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSerieCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSerieContActionPerformed(evt);
            }
        });

        txtIdContAdd.setEditable(false);
        txtIdContAdd.setBackground(new java.awt.Color(255, 255, 204));
        txtIdContAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIdContAdd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdContAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdContAddActionPerformed(evt);
            }
        });

        btnExcluirCont.setText("EXCLUIR");
        btnExcluirCont.setEnabled(false);

        btnCancelarCont.setText("CANCELAR");
        btnCancelarCont.setEnabled(false);
        btnCancelarCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarContActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCompCont, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSerieCont, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdContAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(jTextField22))
                            .addComponent(btnExcluirCont))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField23)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCancelarCont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField24)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnSalvarCont, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jTextField25))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(jLabel38)
                    .addComponent(txtCompCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSerieCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdContAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                    .addComponent(jScrollPane9)
                    .addComponent(jScrollPane10)
                    .addComponent(jScrollPane7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarCont)
                    .addComponent(btnExcluirCont)
                    .addComponent(btnCancelarCont))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Inserir Conteúdo", jPanel12);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("ID do Livro:");
        jLabel34.setToolTipText("");

        txtIdLivroCont.setBackground(new java.awt.Color(255, 255, 204));
        txtIdLivroCont.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtIdLivroCont.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Unidade Temática");
        jLabel37.setToolTipText("");

        btnbuscCont.setText("Buscar");
        btnbuscCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscContActionPerformed(evt);
            }
        });

        tblCodCont.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Cód. Cont.", "Unid. Tem."
            }
        ));
        jScrollPane13.setViewportView(tblCodCont);
        if (tblCodCont.getColumnModel().getColumnCount() > 0) {
            tblCodCont.getColumnModel().getColumn(0).setMinWidth(30);
            tblCodCont.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblCodCont.getColumnModel().getColumn(0).setMaxWidth(30);
            tblCodCont.getColumnModel().getColumn(1).setMinWidth(100);
            tblCodCont.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblCodCont.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText("Cód. Cont.:");
        jLabel39.setToolTipText("");

        btnOkCont.setText("OK");
        btnOkCont.setEnabled(false);
        btnOkCont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkContActionPerformed(evt);
            }
        });

        txtCodCont.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCodCont.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodCont.setEnabled(false);

        txtUnidadeTematica.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUnidadeTematica.setEnabled(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addContainerGap(76, Short.MAX_VALUE)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdLivroCont)
                                    .addComponent(txtCodCont))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnOkCont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnbuscCont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(txtUnidadeTematica, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(13, 13, 13)))
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addComponent(btnbuscCont, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addComponent(txtIdLivroCont, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnOkCont, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCodCont))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(txtUnidadeTematica, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(14, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Cadastrar Conteúdo", jPanel11);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyReleased
// TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaKeyReleased

    private void btnBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaActionPerformed
        pesquisaUsuarios();
// TODO add your handling code here:
    }//GEN-LAST:event_btnBuscaActionPerformed

    private void tblBuscaCadastroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBuscaCadastroMouseClicked

        if (salvo == 0) {

            int confirma = JOptionPane.showConfirmDialog(null, "Você você ainda não salvou continuar?", "Atenção", JOptionPane.YES_NO_OPTION);

            if (confirma == JOptionPane.YES_OPTION) {

                preencher();
                preenchetabelacomp();
                desativacampouso();
                abaComponentes.setEnabled(true);
                btnEditar.setEnabled(true);
                btnNovoUso.setEnabled(true);
                salvo = 1;
            }
        } else {
            preencher();
            preenchetabelacomp();
            desativacampouso();
            abaComponentes.setEnabled(true);
            btnEditar.setEnabled(true);
            btnNovoUso.setEnabled(true);
        }
    }//GEN-LAST:event_tblBuscaCadastroMouseClicked

    private void tblBuscaCadastroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBuscaCadastroMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblBuscaCadastroMousePressed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (txtIdUso.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "(*)Selecione um usuário para editar!");

        } else {
            salvo = 0;
            ativacampousuario();
            btnNovoUso.setEnabled(false);
            btnEditar.setEnabled(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNovoUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoUsoActionPerformed
        salvo = 0;
        limpacampos();
        ativacampousuario();
        abaComponentes.setEnabled(false);
        btnEditar.setEnabled(false);
        btnNovoUso.setEnabled(false);
    }//GEN-LAST:event_btnNovoUsoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if ((txtNomeUso.getText().isEmpty()) || (txtCidadeUso.getText().isEmpty()) || (txtUfUso.getText().isEmpty()) || (txtEnderecoUso.getText().isEmpty()) || (txtNumUso.getText().isEmpty()) || (txtEmailUso.getText().isEmpty()) || (txtCpfUso.getText().equals("   .   .   -  ")) || (txtCelUso.getText().equals("(  )  .    -    ")) || (txtUsuarioUso.getText().isEmpty()) || (txtSenhaUso.getText().isEmpty()) || (cmbLicencaUso.getSelectedItem().equals("Selecione")) || (cmbPerfilUso.getSelectedItem().equals("Selecione"))) {

            JOptionPane.showMessageDialog(null, "Todos os campos marcados com (*) são obrigatórios!");
        } else {

            if (txtIdUso.getText().isEmpty()) {
                cadastraruso();
                salvo = 1;
            } else {
                editauso();
                salvo = 1;

            }

        }

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void cmbcompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbcompActionPerformed
        if (cmbcomp.getSelectedItem().equals("Não Selecionado")) {
            btnadd.setEnabled(false);
        } else {
            btnadd.setEnabled(true);
        }
    }//GEN-LAST:event_cmbcompActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        if (txtIdUso.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "(*)Selecione um usuário para adicioar ou deletar componentes!");
        } else {
            addcomponenteuso();
            preenchetabelacomp();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnaddActionPerformed

    private void tblCompFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblCompFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCompFocusGained

    private void tblCompFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblCompFocusLost

    }//GEN-LAST:event_tblCompFocusLost

    private void btnDelCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelCompActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Deletar componente selecionado?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {
            delcomponente();
            preenchetabelacomp();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDelCompActionPerformed

    private void tblCompMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCompMouseClicked
        btnDelComp.setEnabled(true);
    }//GEN-LAST:event_tblCompMouseClicked

    private void btnCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelaActionPerformed
        if (salvo == 0) {

            int confirma = JOptionPane.showConfirmDialog(null, "Você você ainda não salvou continuar?", "Atenção", JOptionPane.YES_NO_OPTION);

            if (confirma == JOptionPane.YES_OPTION) {
                desativacampouso();
                limpacampos();
                salvo = 1;
                btnNovoUso.setEnabled(true);
                btnEditar.setEnabled(true);
                abaComponentes.setEnabled(true);
                tblComp.setModel((DbUtils.resultSetToTableModel(rs)));

            }

        } else {
            desativacampouso();
            limpacampos();
            btnNovoUso.setEnabled(true);
            btnEditar.setEnabled(true);
            abaComponentes.setEnabled(true);
            tblComp.setModel((DbUtils.resultSetToTableModel(rs)));

        }
    }//GEN-LAST:event_btnCancelaActionPerformed

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
        preenchercomp1();
        preenchereditora1();
        preencherserie1();
        preencherlivros1();// TODO add your handling code here:
        preenchecmbdoslivros();
    }//GEN-LAST:event_jTabbedPane1FocusGained

    private void tblComp1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComp1MouseClicked
        preenchercadcomp();
        btnEditComp.setEnabled(true);
    }//GEN-LAST:event_tblComp1MouseClicked
    int salvo1 = 1;//tomada de decisao salvar o campo
    private void btnInseirCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInseirCompActionPerformed
        salvo1 = 0;

        txtIdComp.setText(null);
        txtNomeComp.setText(null);
        txtNomeComp.setEnabled(true);
        btnEditComp.setEnabled(false);
        btnInseirComp.setEnabled(false);
        btnSalvaComp.setEnabled(true);
        btnCancelarcomp.setEnabled(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnInseirCompActionPerformed

    private void btnEditCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCompActionPerformed
        salvo1 = 0;
        txtNomeComp.setEnabled(true);
        btnEditComp.setEnabled(false);
        btnInseirComp.setEnabled(false);
        btnSalvaComp.setEnabled(true);
        btnCancelarcomp.setEnabled(true);
        btnDeletarRegisto.setEnabled(true);// TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditCompActionPerformed

    private void btnCancelarcompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarcompActionPerformed
        if (salvo1 == 0) {

            int confirma = JOptionPane.showConfirmDialog(null, "Você você ainda não salvou continuar?", "Atenção", JOptionPane.YES_NO_OPTION);

            if (confirma == JOptionPane.YES_OPTION) {

                salvo1 = 1;
                txtIdComp.setText(null);
                txtNomeComp.setText(null);
                txtNomeComp.setEnabled(false);
                btnInseirComp.setEnabled(true);
                btnSalvaComp.setEnabled(false);
                btnCancelarcomp.setEnabled(false);
                btnDeletarRegisto.setEnabled(false);

            }
        }// TODO add your handling code here:                // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarcompActionPerformed

    private void btnSalvaCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvaCompActionPerformed
        if (txtNomeComp.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O nome do Componente não pode ser em branco!");

        } else {

            if (txtIdComp.getText().isEmpty()) {

                String sql = "insert into componentes (comp_nome) values (?)";

                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtNomeComp.getText());

                    int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Componente adicionado com sucesso!");

                        preenchercomp1();
                        txtNomeComp.setEnabled(false);
                        btnInseirComp.setEnabled(true);
                        btnSalvaComp.setEnabled(false);
                        btnCancelarcomp.setEnabled(false);
                        btnDeletarRegisto.setEnabled(false);
                        salvo1 = 1;
                    }

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, "Erro ao inserir componente: " + e);
                }

            } else {

                String sql = "update componentes set comp_nome=? where idcomponente=?";

                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtNomeComp.getText());
                    pst.setString(2, txtIdComp.getText());

                    int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Componente alterado com sucesso!");
                        preenchercomp1();
                        txtNomeComp.setEnabled(false);
                        btnInseirComp.setEnabled(true);
                        btnSalvaComp.setEnabled(false);
                        btnCancelarcomp.setEnabled(false);
                        btnDeletarRegisto.setEnabled(false);
                        salvo1 = 1;

                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao alterar componente: " + e);
                }

            }
        }
    }//GEN-LAST:event_btnSalvaCompActionPerformed

    private void btnDeletarRegistoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarRegistoActionPerformed

        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que quer deletar o registro?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from componentes where idcomponente=?";

            try {
                salvo1 = 1;
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdComp.getText());
                pst.executeUpdate();
                preenchercomp1();
                txtNomeComp.setEnabled(false);
                txtNomeComp.setText(null);
                txtIdComp.setText(null);
                btnInseirComp.setEnabled(true);
                btnSalvaComp.setEnabled(false);
                btnCancelarcomp.setEnabled(false);
                btnDeletarRegisto.setEnabled(false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao apagar componentes: " + e);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnDeletarRegistoActionPerformed

    private void tblEditoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEditoraMouseClicked
        preenchercampeditora();
        btnEditedit.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_tblEditoraMouseClicked

    private void btnEditeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditeditActionPerformed
        salvo2 = 0;
        txtNomeedit.setEnabled(true);
        btnEditedit.setEnabled(false);
        btnInseiredit.setEnabled(false);
        btnSalvaedit.setEnabled(true);
        btnCancelaredit.setEnabled(true);
        btnDeletaredit.setEnabled(true);//        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditeditActionPerformed

    private void btnSalvaeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvaeditActionPerformed
        if (txtNomeedit.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O nome da Editora não pode ser em branco!");

        } else {

            if (txtIdedit.getText().isEmpty()) {

                String sql = "insert into editora (edit_nome) values (?)";

                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtNomeedit.getText());

                    int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Editora adicionada com sucesso!");

                        preenchereditora1();
                        txtNomeedit.setEnabled(false);
                        btnInseiredit.setEnabled(true);
                        btnSalvaedit.setEnabled(false);
                        btnCancelaredit.setEnabled(false);
                        btnDeletaredit.setEnabled(false);
                        salvo2 = 1;

                    }

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, "Erro ao inserir editora: " + e);
                }

            } else {

                String sql = "update editora set edit_nome=? where ideditora=?";

                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtNomeedit.getText());
                    pst.setString(2, txtIdedit.getText());

                    int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Componente alterado com sucesso!");
                        preenchereditora1();
                        txtNomeedit.setEnabled(false);
                        btnInseiredit.setEnabled(true);
                        btnSalvaedit.setEnabled(false);
                        btnCancelaredit.setEnabled(false);
                        btnDeletaredit.setEnabled(false);
                        salvo2 = 1;

                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao alterar editora: " + e);
                }

            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvaeditActionPerformed
    int salvo2 = 1;
    private void btnInseireditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInseireditActionPerformed
        salvo2 = 0;

        txtIdedit.setText(null);
        txtNomeedit.setText(null);
        txtNomeedit.setEnabled(true);
        btnEditedit.setEnabled(false);
        btnInseiredit.setEnabled(false);
        btnSalvaedit.setEnabled(true);
        btnCancelaredit.setEnabled(true);
// TODO add your handling code here:
    }//GEN-LAST:event_btnInseireditActionPerformed

    private void btnCancelareditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelareditActionPerformed
        if (salvo2 == 0) {

            int confirma = JOptionPane.showConfirmDialog(null, "Você você ainda não salvou continuar?", "Atenção", JOptionPane.YES_NO_OPTION);

            if (confirma == JOptionPane.YES_OPTION) {

                salvo2 = 1;
                txtIdedit.setText(null);
                txtNomeedit.setText(null);
                txtNomeedit.setEnabled(false);
                btnInseiredit.setEnabled(true);
                btnSalvaedit.setEnabled(false);
                btnCancelaredit.setEnabled(false);
                btnDeletaredit.setEnabled(false);

            }
        }
    }//GEN-LAST:event_btnCancelareditActionPerformed

    private void btnDeletareditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletareditActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que quer deletar o registro?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from editora where ideditora=?";

            try {
                salvo2 = 1;
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdedit.getText());
                pst.executeUpdate();
                preenchereditora1();
                txtNomeedit.setEnabled(false);
                txtNomeedit.setText(null);
                txtIdedit.setText(null);
                btnInseiredit.setEnabled(true);
                btnSalvaedit.setEnabled(false);
                btnCancelaredit.setEnabled(false);
                btnDeletaredit.setEnabled(false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao apagar componentes: " + e);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeletareditActionPerformed

    private void btnEditSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSerieActionPerformed
        salvo3 = 0;
        txtNomeSerie.setEnabled(true);
        btnEditSerie.setEnabled(false);
        btnInseirSerie.setEnabled(false);
        btnSalvaSerie.setEnabled(true);
        btnCancelarSerie.setEnabled(true);
        btnDeletarSerie.setEnabled(true);//           // TODO add your handling code here:
    }//GEN-LAST:event_btnEditSerieActionPerformed

    private void btnSalvaSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvaSerieActionPerformed
        if (txtNomeSerie.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O nome da Série não pode ser em branco!");

        } else {

            if (txtIdSerie.getText().isEmpty()) {

                String sql = "insert into serie (seri_nivel) values (?)";

                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtNomeSerie.getText());

                    int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Série adicionada com sucesso!");

                        preencherserie1();
                        txtNomeSerie.setEnabled(false);
                        btnInseirSerie.setEnabled(true);
                        btnSalvaSerie.setEnabled(false);
                        btnCancelarSerie.setEnabled(false);
                        btnDeletarSerie.setEnabled(false);
                        salvo3 = 1;

                    }

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, "Erro ao inserir Série: " + e);
                }

            } else {

                String sql = "update serie set seri_nivel=? where idserie=?";

                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1, txtNomeSerie.getText());
                    pst.setString(2, txtIdSerie.getText());

                    int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Série alterada com sucesso!");
                        preenchereditora1();
                        txtNomeSerie.setEnabled(false);
                        btnInseirSerie.setEnabled(true);
                        btnSalvaSerie.setEnabled(false);
                        btnCancelarSerie.setEnabled(false);
                        btnDeletarSerie.setEnabled(false);
                        salvo3 = 1;

                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao alterar editora: " + e);
                }

            }
        }
    }//GEN-LAST:event_btnSalvaSerieActionPerformed
    int salvo3 = 1;
    private void btnInseirSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInseirSerieActionPerformed
        salvo3 = 0;

        txtIdSerie.setText(null);
        txtNomeSerie.setText(null);
        txtNomeSerie.setEnabled(true);
        btnEditSerie.setEnabled(false);
        btnInseirSerie.setEnabled(false);
        btnSalvaSerie.setEnabled(true);
        btnCancelarSerie.setEnabled(true);
    }//GEN-LAST:event_btnInseirSerieActionPerformed

    private void btnCancelarSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarSerieActionPerformed
        if (salvo3 == 0) {

            int confirma = JOptionPane.showConfirmDialog(null, "Você você ainda não salvou continuar?", "Atenção", JOptionPane.YES_NO_OPTION);

            if (confirma == JOptionPane.YES_OPTION) {

                salvo3 = 1;
                txtIdSerie.setText(null);
                txtNomeSerie.setText(null);
                txtNomeSerie.setEnabled(false);
                btnInseirSerie.setEnabled(true);
                btnSalvaSerie.setEnabled(false);
                btnCancelarSerie.setEnabled(false);
                btnDeletarSerie.setEnabled(false);

            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarSerieActionPerformed

    private void btnDeletarSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarSerieActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que quer deletar o registro?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from serie where idserie=?";

            try {
                salvo3 = 1;
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtIdSerie.getText());
                pst.executeUpdate();
                preencherserie1();
                txtNomeSerie.setEnabled(false);
                txtNomeSerie.setText(null);
                txtIdSerie.setText(null);
                btnInseirSerie.setEnabled(true);
                btnSalvaSerie.setEnabled(false);
                btnCancelarSerie.setEnabled(false);
                btnDeletarSerie.setEnabled(false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao apagar Série: " + e);
            }
        }           // TODO add your handling code here:
    }//GEN-LAST:event_btnDeletarSerieActionPerformed

    private void tblSerieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSerieMouseClicked
        preenchercampserie();
        btnEditSerie.setEnabled(true);        // TODO add your handling code here:
    }//GEN-LAST:event_tblSerieMouseClicked

    private void btnBuscaLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaLivroActionPerformed
        pesquisaLivros();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscaLivroActionPerformed

    String localfoto2 = "src/br/com/professorfacil/icones/buscarlivro200x268.jpg";
    BufferedImage foto002 = null;
    public static byte[] vfoto2 = null;

    private void btnBuscaLivroFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaLivroFotoActionPerformed
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

    }//GEN-LAST:event_btnBuscaLivroFotoActionPerformed

    private void tblLivroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLivroMouseClicked
        preenchercamplivro();
        desativacamposlivro();
        btnNovoLivro.setEnabled(true);
        btnEditLivro.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_tblLivroMouseClicked

    private void ativacamposlivro() {

        cmbCompLivro.setEnabled(true);
        txtNomeAutLivro.setEnabled(true);
        txtNomeLivro.setEnabled(true);
        cmbEditLivro.setEnabled(true);
        txtEdicaoLivro.setEnabled(true);
        txtAnoLivro.setEnabled(true);
        cmbSerieLivro.setEnabled(true);
        txtPeriodoLivro.setEnabled(true);
        txtObservaLivro.setEnabled(true);
        btnSalvaLivro.setEnabled(true);
        btnCancelaLivro.setEnabled(true);
        btnBuscaLivroFoto.setEnabled(true);
        btnEditLivro.setEnabled(false);
        btnNovoLivro.setEnabled(false);

    }

    private void limpacamposlivro() {

        txtIdLivro.setText(null);
        cmbCompLivro.setSelectedItem("Selecione");
        txtNomeAutLivro.setText(null);
        txtNomeLivro.setText(null);
        cmbEditLivro.setSelectedItem("Selecione");
        txtEdicaoLivro.setText(null);
        txtAnoLivro.setText(null);
        cmbSerieLivro.setSelectedItem("Selecione");
        txtPeriodoLivro.setText(null);
        txtObservaLivro.setText(null);

    }

    private void desativacamposlivro() {

        cmbCompLivro.setEnabled(false);
        txtNomeAutLivro.setEnabled(false);
        txtNomeLivro.setEnabled(false);
        cmbEditLivro.setEnabled(false);
        txtEdicaoLivro.setEnabled(false);
        txtAnoLivro.setEnabled(false);
        cmbSerieLivro.setEnabled(false);
        txtPeriodoLivro.setEnabled(false);
        txtObservaLivro.setEnabled(false);
        btnSalvaLivro.setEnabled(false);
        btnCancelaLivro.setEnabled(false);
        btnBuscaLivroFoto.setEnabled(false);
        btnEditLivro.setEnabled(false);
        btnNovoLivro.setEnabled(true);

    }


    private void btnNovoLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoLivroActionPerformed
        ativacamposlivro();
        limpacamposlivro();

        ImageIcon foto1 = new ImageIcon(localfoto2);
        foto1.setImage(foto1.getImage().getScaledInstance(lblFoto2.getWidth(), lblFoto2.getHeight(), Image.SCALE_AREA_AVERAGING));
        lblFoto2.setIcon(foto1);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnNovoLivroActionPerformed

    private void btnCancelaLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelaLivroActionPerformed
        limpacamposlivro();
        desativacamposlivro();// TODO add your handling code here:
    }//GEN-LAST:event_btnCancelaLivroActionPerformed

    private void btnEditLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditLivroActionPerformed
        ativacamposlivro();        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditLivroActionPerformed
    private void inserenovolivro() {
        String sql = "insert into livro (liv_componente,liv_nomeautor,liv_nome,liv_editora,liv_edicao,liv_ano,liv_serie,liv_periodo,liv_observacoes,liv_foto) values (?,?,?,?,?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql); //preparando a conexão
            pst.setString(1, cmbCompLivro.getSelectedItem().toString());
            pst.setString(2, txtNomeAutLivro.getText());
            pst.setString(3, txtNomeLivro.getText());
            pst.setString(4, cmbEditLivro.getSelectedItem().toString());
            pst.setString(5, txtEdicaoLivro.getText());
            pst.setString(6, txtAnoLivro.getText());
            pst.setString(7, cmbSerieLivro.getSelectedItem().toString());
            pst.setString(8, txtPeriodoLivro.getText());
            pst.setString(9, txtObservaLivro.getText());

            if (vfoto2 == null) {

                String caminho = localfoto2;
                foto002 = ManipularImagem.setImagemDimensao(caminho, lblFoto2.getWidth(), lblFoto2.getHeight());
                vfoto2 = ManipularImagem.getImgBytes(foto002);
                pst.setBytes(10, vfoto2);
            } else {
                pst.setBytes(10, vfoto2);
            }

            int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Livro inserido com sucesso!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir informações do livro " + e);
        }
    }

    private void alteralivro() {

        String sql = "update livro set liv_componente=?,liv_nomeautor=?,liv_nome=?,liv_editora=?,liv_edicao=?,liv_ano=?,liv_serie=?,liv_periodo=?,liv_observacoes=?,liv_foto=? where idliv=?";

        try {
            pst = conexao.prepareStatement(sql); //preparando a conexão
            pst.setString(1, cmbCompLivro.getSelectedItem().toString());
            pst.setString(2, txtNomeAutLivro.getText());
            pst.setString(3, txtNomeLivro.getText());
            pst.setString(4, cmbEditLivro.getSelectedItem().toString());
            pst.setString(5, txtEdicaoLivro.getText());
            pst.setString(6, txtAnoLivro.getText());
            pst.setString(7, cmbSerieLivro.getSelectedItem().toString());
            pst.setString(8, txtPeriodoLivro.getText());
            pst.setString(9, txtObservaLivro.getText());

            if (vfoto2 == null) {

                String caminho = localfoto2;
                foto002 = ManipularImagem.setImagemDimensao(caminho, lblFoto2.getWidth(), lblFoto2.getHeight());
                vfoto2 = ManipularImagem.getImgBytes(foto002);
                pst.setBytes(10, vfoto2);
            } else {
                pst.setBytes(10, vfoto2);
            }
            pst.setString(11, txtIdLivro.getText());

            int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Livro alterado com sucesso!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar informações do livro " + e);
        }
    }

    private void btnSalvaLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvaLivroActionPerformed
        if ((cmbCompLivro.getSelectedItem().equals("Selecione")) || (txtNomeAutLivro.getText().isEmpty()) || (txtNomeLivro.getText().isEmpty()) || (cmbEditLivro.getSelectedItem().equals("Selecione")) || (txtEdicaoLivro.getText().isEmpty()) || (txtAnoLivro.getText().isEmpty()) || (cmbSerieLivro.getSelectedItem().equals("Selecione")) || (txtPeriodoLivro.getText().isEmpty())) {

            JOptionPane.showMessageDialog(null, "(*)Todos os campos são obrigatórios");

        } else {

            if (txtIdLivro.getText().isEmpty()) {
                inserenovolivro();
                preencherlivros1();
                limpacamposlivro();
                desativacamposlivro();

            } else {
                alteralivro();
                preencherlivros1();
                limpacamposlivro();
                desativacamposlivro();

            }

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvaLivroActionPerformed

    private void btnOkContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkContActionPerformed

        consexistcont();
    }//GEN-LAST:event_btnOkContActionPerformed

    private void btnbuscContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscContActionPerformed
        if (txtIdLivroCont.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o Id do Livro para continuar!");

        } else {
            preencherareacont();
            btnOkCont.setEnabled(true);
            txtCodCont.setEnabled(true);

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscContActionPerformed

    private void txtIdContAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdContAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdContAddActionPerformed

    private void txtSerieContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSerieContActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSerieContActionPerformed

    private void txtCompContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCompContActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCompContActionPerformed
    int salvo4 = 1;

    private void desativaaddcont() {
        txtCodCont.setEnabled(true);
        btnOkCont.setEnabled(true);
        txtIdLivroCont.setEnabled(true);
        btnbuscCont.setEnabled(true);

        txtUnidadeTematica.setEnabled(false);
        btnExcluirCont.setEnabled(false);
        txtContCont.setEnabled(false);
        txtHabCont.setEnabled(false);
        txtAtvCOnt.setEnabled(false);
        txtRecCont.setEnabled(false);
        btnSalvarCont.setEnabled(false);
        btnCancelarCont.setEnabled(false);

    }

    private void limpacamposcont() {

        txtUnidadeTematica.setText(null);
        txtContCont.setText(null);
        txtHabCont.setText(null);
        txtAtvCOnt.setText(null);
        txtRecCont.setText(null);

    }
    private void btnCancelarContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarContActionPerformed
       
       if (salvo4 == 0){ 
        
        
        int adicionar = JOptionPane.showConfirmDialog(null, "Você ainda não salvou deseja cancelar?", "ATENÇÃO!", JOptionPane.YES_NO_OPTION);

        if (adicionar == JOptionPane.YES_OPTION) {
            
            salvo4 = 1;
            desativaaddcont();
            limpacamposcont();
        }
       }
    }//GEN-LAST:event_btnCancelarContActionPerformed
    

    private void preencherareacont() {

        String sql = "select * from livro where idliv=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdLivroCont.getText());
            rs = pst.executeQuery();

            if (rs.next()) {

                txtCompCont.setText(rs.getString("liv_componente"));
                txtSerieCont.setText(rs.getString("liv_serie"));
                preenchertblcodcont();

            } else {

                JOptionPane.showMessageDialog(null, "Livro não Localizado!");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao localizar livro na base de dados" + e);
        }

    }

    private void preenchertblcodcont() {

        String sql = "select idconteudo,cont_code_conteudo,cont_unidadetematica from conteudo where idliv = ? and cont_componente = ? and cont_serie = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdLivroCont.getText());
            pst.setString(2, txtCompCont.getText());
            pst.setString(3, txtSerieCont.getText());

            rs = pst.executeQuery();

            tblCodCont.setModel((DbUtils.resultSetToTableModel(rs)));
            formatatabelacod();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao localizar livro na base de dados" + e);
        }

    }

    private void formatatabelacod() {

        tblCodCont.getColumnModel().getColumn(0).setMaxWidth(30);
        tblCodCont.getColumnModel().getColumn(0).setResizable(false);
        tblCodCont.getColumnModel().getColumn(0).setHeaderValue("ID");
        tblCodCont.getColumnModel().getColumn(1).setMaxWidth(100);
        tblCodCont.getColumnModel().getColumn(1).setResizable(false);
        tblCodCont.getColumnModel().getColumn(1).setHeaderValue("Cód. Cont.");
        tblCodCont.getColumnModel().getColumn(2).setHeaderValue("Unid. Tema.");

    }

    // o metodo abaixo consulta se existe o componente a ser inserido
    private void consexistcont() {
        String sql = "select * from conteudo where cont_code_conteudo=? and cont_serie=? and cont_componente=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCodCont.getText());
            pst.setString(2, txtSerieCont.getText());
            pst.setString(3, txtCompCont.getText());

            rs = pst.executeQuery();

            if (rs.next()) {

                int adicionar = JOptionPane.showConfirmDialog(null, "O Código " + txtCodCont.getText() + " Já existe!\n Deseja edita-lo Agora?", "ATENÇÃO!", JOptionPane.YES_NO_OPTION);

                if (adicionar == JOptionPane.YES_OPTION) {
                    
                    salvo4 = 0;
                    
                    txtIdContAdd.setText(rs.getString("idconteudo"));
                    txtUnidadeTematica.setText(rs.getString("cont_unidadetematica"));
                    txtContCont.setText(rs.getString("cont_conteudo"));
                    txtHabCont.setText(rs.getString("cont_habilidade"));
                    txtAtvCOnt.setText(rs.getString("cont_atividade"));
                    txtRecCont.setText(rs.getString("cont_recurso"));
                    btnOkCont.setEnabled(false);
                    txtCodCont.setEnabled(false);
                    btnCancelarCont.setEnabled(true);
                    btnExcluirCont.setEnabled(true);
                    txtContCont.setEnabled(true);
                    txtHabCont.setEnabled(true);
                    txtAtvCOnt.setEnabled(true);
                    txtRecCont.setEnabled(true);
                    txtUnidadeTematica.setEnabled(true);
                    btnSalvarCont.setEnabled(true);
                    txtIdLivroCont.setEnabled(false);
                    btnbuscCont.setEnabled(false);

                }

            } else {

                int adicionar = JOptionPane.showConfirmDialog(null, "O Código " + txtCodCont.getText() + " Não existe!\n Quer adicionar Agora?", "ATENÇÃO!", JOptionPane.YES_NO_OPTION);

                if (adicionar == JOptionPane.YES_OPTION) {

                    btnOkCont.setEnabled(false);
                    txtCodCont.setEnabled(false);
                    btnCancelarCont.setEnabled(true);
                    txtContCont.setEnabled(true);
                    txtHabCont.setEnabled(true);
                    txtAtvCOnt.setEnabled(true);
                    txtRecCont.setEnabled(true);
                    txtUnidadeTematica.setEnabled(true);
                    btnSalvarCont.setEnabled(true);
                    txtIdLivroCont.setEnabled(false);
                    btnbuscCont.setEnabled(false);

                }

            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao iniciar inclusão " + e);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane abaComponentes;
    private javax.swing.JButton btnBusca;
    private javax.swing.JButton btnBuscaLivro;
    private javax.swing.JButton btnBuscaLivroFoto;
    private javax.swing.JButton btnCancela;
    private javax.swing.JButton btnCancelaLivro;
    private javax.swing.JButton btnCancelarCont;
    private javax.swing.JButton btnCancelarSerie;
    private javax.swing.JButton btnCancelarcomp;
    private javax.swing.JButton btnCancelaredit;
    private javax.swing.JButton btnDelComp;
    private javax.swing.JButton btnDeletarRegisto;
    private javax.swing.JButton btnDeletarSerie;
    private javax.swing.JButton btnDeletaredit;
    private javax.swing.JButton btnEditComp;
    private javax.swing.JButton btnEditLivro;
    private javax.swing.JButton btnEditSerie;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditedit;
    private javax.swing.JButton btnExcluirCont;
    private javax.swing.JButton btnInseirComp;
    private javax.swing.JButton btnInseirSerie;
    private javax.swing.JButton btnInseiredit;
    private javax.swing.JButton btnNovoLivro;
    private javax.swing.JButton btnNovoUso;
    private javax.swing.JButton btnOkCont;
    private javax.swing.JButton btnSalvaComp;
    private javax.swing.JButton btnSalvaLivro;
    private javax.swing.JButton btnSalvaSerie;
    private javax.swing.JButton btnSalvaedit;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSalvarCont;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnbuscCont;
    private javax.swing.JComboBox<String> cmbCompLivro;
    private javax.swing.JComboBox<String> cmbEditLivro;
    private javax.swing.JComboBox<String> cmbLicencaUso;
    private javax.swing.JComboBox<String> cmbPerfilUso;
    private javax.swing.JComboBox<String> cmbSerieLivro;
    private javax.swing.JComboBox<String> cmbcomp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JLabel lblFoto2;
    private javax.swing.JTable tblBuscaCadastro;
    private javax.swing.JTable tblCodCont;
    private javax.swing.JTable tblComp;
    private javax.swing.JTable tblComp1;
    private javax.swing.JTable tblEditora;
    private javax.swing.JTable tblLivro;
    private javax.swing.JTable tblSerie;
    private javax.swing.JTextField txtAnoLivro;
    private javax.swing.JTextArea txtAtvCOnt;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtBuscaLivros;
    private javax.swing.JFormattedTextField txtCelUso;
    private javax.swing.JFormattedTextField txtCepUso;
    private javax.swing.JTextField txtCidadeUso;
    private javax.swing.JTextField txtCodCont;
    private javax.swing.JTextField txtCompCont;
    private javax.swing.JTextArea txtContCont;
    private javax.swing.JFormattedTextField txtCpfUso;
    private javax.swing.JTextField txtDataCadastro;
    private javax.swing.JFormattedTextField txtDataLicenca;
    private javax.swing.JTextField txtEdicaoLivro;
    private javax.swing.JTextField txtEmailUso;
    private javax.swing.JTextField txtEnderecoUso;
    private javax.swing.JTextArea txtHabCont;
    private javax.swing.JTextField txtIdComp;
    private javax.swing.JTextField txtIdContAdd;
    private javax.swing.JTextField txtIdLivro;
    private javax.swing.JTextField txtIdLivroCont;
    private javax.swing.JTextField txtIdSerie;
    private javax.swing.JTextField txtIdUso;
    private javax.swing.JTextField txtIdedit;
    private javax.swing.JTextField txtNomeAutLivro;
    private javax.swing.JTextField txtNomeComp;
    private javax.swing.JTextField txtNomeLivro;
    private javax.swing.JTextField txtNomeSerie;
    private javax.swing.JTextField txtNomeUso;
    private javax.swing.JTextField txtNomeedit;
    private javax.swing.JTextField txtNumUso;
    private javax.swing.JTextArea txtObservaLivro;
    private javax.swing.JTextField txtPeriodoLivro;
    private javax.swing.JTextArea txtRecCont;
    private javax.swing.JTextField txtSenhaUso;
    private javax.swing.JTextField txtSerieCont;
    private javax.swing.JTextField txtUfUso;
    private javax.swing.JTextField txtUnidadeTematica;
    private javax.swing.JTextField txtUsuarioUso;
    private javax.swing.JFormattedTextField txtValidadeLicensa;
    // End of variables declaration//GEN-END:variables
}
