/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package grupo8.view;

/**
 *
 * @author carpi
 */
public class JDialogCadastroPessoas extends javax.swing.JDialog {

    /**
     * Creates new form JDialogCadastroPessoas
     */
    private String tipoPessoa; // "PF" ou "PJ"
    private grupo8.pessoas.Pessoa pessoaEdicao; // Se null, é novo

    private java.util.ArrayList<grupo8.pessoas.PessoaFisica> listaSociosPF = new java.util.ArrayList<>();
    private java.util.ArrayList<grupo8.pessoas.PessoaJuridica> listaSociosPJ = new java.util.ArrayList<>();

    public JDialogCadastroPessoas(java.awt.Frame parent, boolean modal, String tipo, grupo8.pessoas.Pessoa p) {
        super(parent, modal);
        initComponents();
        this.tipoPessoa = tipo;
        this.pessoaEdicao = p;
        this.setLocationRelativeTo(parent);
        configurarTela();
    }

    // Construtor Padrão (para não quebrar o NetBeans, mas não será usado)
    public JDialogCadastroPessoas(java.awt.Frame parent, boolean modal) {
        this(parent, modal, "PF", null);
    }

    private void configurarTela() {
        // 1. Oculta a aba errada
        if (tipoPessoa.equals("PF")) {
            // Remove a aba de PJ (índice 0 na sua ordem, verifique!)
            // Se PJ for a primeira aba e PF a segunda:
            jTabbedPane1.removeTabAt(0); // Remove PJ
            this.setTitle("Cadastro de Pessoa Física");
        } else {
            // Remove PF (índice 1)
            jTabbedPane1.removeTabAt(1); // Remove PF
            this.setTitle("Cadastro de Pessoa Jurídica");
        }

        // 2. Se for edição, preenche
        if (pessoaEdicao != null) {
            preencherDados();
        }
    }

    private void preencherDados() {
        // Preenche PF
        if (pessoaEdicao instanceof grupo8.pessoas.PessoaFisica) {
            grupo8.pessoas.PessoaFisica pf = (grupo8.pessoas.PessoaFisica) pessoaEdicao;
            TextFieldNomePF.setText(pf.getNome());
            TextFieldCpfPF.setText(pf.getCPF());

            java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            TextFieldDtNascimetnoPF.setText(pf.getDtNasc().format(fmt));

            TextFieldEmailPF.setText(pf.getEmail());
            TextFieldTelefone1PF.setText(pf.getTelefone1());
            TextFieldTelefone2PF.setText(pf.getTelefone2());
            TextAreaEnderecoPF.setText(pf.getEndereco());
        } // Preenche PJ
        else if (pessoaEdicao instanceof grupo8.pessoas.PessoaJuridica) {
            grupo8.pessoas.PessoaJuridica pj = (grupo8.pessoas.PessoaJuridica) pessoaEdicao;

            // Campos de Texto
            TextFieldRasaoSocialPJ.setText(pj.getRazaoSocial());
            TextFieldNomeFantasiaPJ.setText(pj.getNomeFantasia());
            TextFieldCnpjPJ.setText(pj.getCnpj());
            TextFieldInscricaoPJ.setText(pj.getInscricao());
            TextFieldAtividadePJ.setText(pj.getAtividade());

            java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            TextFieldDtAbertura.setText(pj.getDtAbertura().format(fmt));

            TextFieldEmailPJ.setText(pj.getEmail());
            TextFieldTelefone1PJ.setText(pj.getTelefone1());
            TextFieldTelefone2PJ.setText(pj.getTelefone2());
            TextAreaEnderecoPJ.setText(pj.getEndereco());

            // --- CARREGAR SÓCIOS (AQUI ESTÁ A MÁGICA) ---
            // Limpa as listas da tela para evitar duplicidade
            listaSociosPF.clear();
            listaSociosPJ.clear();

            // Carrega Sócios PF se existirem
            if (pj.getSociosPF() != null) {
                // Converte Array [] para ArrayList
                java.util.Collections.addAll(listaSociosPF, pj.getSociosPF());
            }

            // Carrega Sócios PJ se existirem
            if (pj.getSociosPJ() != null) {
                java.util.Collections.addAll(listaSociosPJ, pj.getSociosPJ());
            }

            // Atualiza as JTables visuais
            atualizarTabelasSocios();
        }
    }

    // Método auxiliar para desenhar as tabelas
    private void atualizarTabelasSocios() {
        // Atualiza Tabela PF
        javax.swing.table.DefaultTableModel modelPF = (javax.swing.table.DefaultTableModel) TableSociosPF_PJ.getModel();
        modelPF.setRowCount(0);
        for (grupo8.pessoas.PessoaFisica pf : listaSociosPF) {
            modelPF.addRow(new Object[]{pf.getNome(), pf.getCPF()});
        }

        // Atualiza Tabela PJ
        javax.swing.table.DefaultTableModel modelPJ = (javax.swing.table.DefaultTableModel) TableSociosPJ_PJ.getModel();
        modelPJ.setRowCount(0);
        for (grupo8.pessoas.PessoaJuridica pj : listaSociosPJ) {
            modelPJ.addRow(new Object[]{pj.getNomeFantasia(), pj.getCnpj()});
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jLabel4 = new javax.swing.JLabel();
        TextFieldRasaoSocialPJ = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TextFieldNomeFantasiaPJ = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TextFieldCnpjPJ = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TextFieldInscricaoPJ = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TextFieldDtAbertura = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TextFieldAtividadePJ = new javax.swing.JTextField();
        PanelSessaoSociosPF = new javax.swing.JPanel();
        PanelHeadSociosPF = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableSociosPF_PJ = new javax.swing.JTable();
        PanelSessaoSociosPJ = new javax.swing.JPanel();
        PanelHeadSociosPJ = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableSociosPJ_PJ = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        TextFieldTelefone1PJ = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        TextFieldTelefone2PJ = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        TextFieldEmailPJ = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TextAreaEnderecoPJ = new javax.swing.JTextArea();
        ButtonIncluirSocio = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        TextFieldNomePF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TextFieldCpfPF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TextFieldDtNascimetnoPF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        TextFieldTelefone1PF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        TextFieldTelefone2PF = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        TextFieldEmailPF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TextAreaEnderecoPF = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        ButtonSalvar = new javax.swing.JButton();
        ButtonCancelar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel4.setText("Rasão Social:");

        jLabel5.setText("Nome Fantasia:");

        jLabel6.setText("CNPJ:");

        jLabel7.setText("Instrição Estadual:");

        jLabel8.setText("Data de Abertura:");

        jLabel9.setText("Atividade:");

        jLabel10.setText("Socios Pessoa Fisica:");

        javax.swing.GroupLayout PanelHeadSociosPFLayout = new javax.swing.GroupLayout(PanelHeadSociosPF);
        PanelHeadSociosPF.setLayout(PanelHeadSociosPFLayout);
        PanelHeadSociosPFLayout.setHorizontalGroup(
            PanelHeadSociosPFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeadSociosPFLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelHeadSociosPFLayout.setVerticalGroup(
            PanelHeadSociosPFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeadSociosPFLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        TableSociosPF_PJ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CPF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TableSociosPF_PJ);

        javax.swing.GroupLayout PanelSessaoSociosPFLayout = new javax.swing.GroupLayout(PanelSessaoSociosPF);
        PanelSessaoSociosPF.setLayout(PanelSessaoSociosPFLayout);
        PanelSessaoSociosPFLayout.setHorizontalGroup(
            PanelSessaoSociosPFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelHeadSociosPF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelSessaoSociosPFLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelSessaoSociosPFLayout.setVerticalGroup(
            PanelSessaoSociosPFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSessaoSociosPFLayout.createSequentialGroup()
                .addComponent(PanelHeadSociosPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel12.setText("Socios Pessoa Juridica:");

        javax.swing.GroupLayout PanelHeadSociosPJLayout = new javax.swing.GroupLayout(PanelHeadSociosPJ);
        PanelHeadSociosPJ.setLayout(PanelHeadSociosPJLayout);
        PanelHeadSociosPJLayout.setHorizontalGroup(
            PanelHeadSociosPJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeadSociosPJLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelHeadSociosPJLayout.setVerticalGroup(
            PanelHeadSociosPJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeadSociosPJLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        TableSociosPJ_PJ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CNPJ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TableSociosPJ_PJ);

        javax.swing.GroupLayout PanelSessaoSociosPJLayout = new javax.swing.GroupLayout(PanelSessaoSociosPJ);
        PanelSessaoSociosPJ.setLayout(PanelSessaoSociosPJLayout);
        PanelSessaoSociosPJLayout.setHorizontalGroup(
            PanelSessaoSociosPJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSessaoSociosPJLayout.createSequentialGroup()
                .addGroup(PanelSessaoSociosPJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelHeadSociosPJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelSessaoSociosPJLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelSessaoSociosPJLayout.setVerticalGroup(
            PanelSessaoSociosPJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSessaoSociosPJLayout.createSequentialGroup()
                .addComponent(PanelHeadSociosPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel16.setText("Telefone 1:");

        jLabel17.setText("Telefone 2:");

        jLabel18.setText("Email:");

        TextFieldEmailPJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldEmailPJActionPerformed(evt);
            }
        });

        jLabel19.setText("Endereço:");

        TextAreaEnderecoPJ.setColumns(20);
        TextAreaEnderecoPJ.setRows(5);
        jScrollPane4.setViewportView(TextAreaEnderecoPJ);

        ButtonIncluirSocio.setText("INCLUIR SOCIO");
        ButtonIncluirSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonIncluirSocioActionPerformed(evt);
            }
        });

        jDesktopPane2.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(TextFieldRasaoSocialPJ, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(TextFieldNomeFantasiaPJ, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(TextFieldCnpjPJ, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(TextFieldInscricaoPJ, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(TextFieldDtAbertura, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(TextFieldAtividadePJ, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(PanelSessaoSociosPF, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(PanelSessaoSociosPJ, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(TextFieldTelefone1PJ, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(TextFieldTelefone2PJ, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(TextFieldEmailPJ, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(ButtonIncluirSocio, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(PanelSessaoSociosPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelSessaoSociosPJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(TextFieldTelefone1PJ, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextFieldTelefone2PJ, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(TextFieldEmailPJ)))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(TextFieldCnpjPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextFieldInscricaoPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextFieldDtAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(TextFieldAtividadePJ)))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(TextFieldRasaoSocialPJ))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(TextFieldNomeFantasiaPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ButtonIncluirSocio)))
                .addContainerGap())
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldRasaoSocialPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldNomeFantasiaPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldCnpjPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldInscricaoPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldDtAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldAtividadePJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldTelefone1PJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldTelefone2PJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldEmailPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(ButtonIncluirSocio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelSessaoSociosPF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelSessaoSociosPJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pessoa Juridica", jDesktopPane2);

        jLabel1.setText("Nome:");

        TextFieldNomePF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldNomePFActionPerformed(evt);
            }
        });

        jLabel2.setText("CPF:");

        jLabel3.setText("Data de Nacimento:");

        jLabel13.setText("Telefone 1:");

        jLabel14.setText("Telefone 2:");

        jLabel15.setText("Email:");

        TextFieldEmailPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldEmailPFActionPerformed(evt);
            }
        });

        jLabel11.setText("Endereço:");

        TextAreaEnderecoPF.setColumns(20);
        TextAreaEnderecoPF.setRows(5);
        jScrollPane3.setViewportView(TextAreaEnderecoPF);

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(TextFieldNomePF, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(TextFieldCpfPF, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(TextFieldDtNascimetnoPF, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(TextFieldTelefone1PF, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(TextFieldTelefone2PF, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(TextFieldEmailPF, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(TextFieldNomePF, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(TextFieldTelefone1PF, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextFieldTelefone2PF, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(TextFieldCpfPF))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(TextFieldDtNascimetnoPF, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel15))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(TextFieldEmailPF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6)
                        .addComponent(TextFieldNomePF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextFieldCpfPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextFieldDtNascimetnoPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldEmailPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextFieldTelefone1PF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextFieldTelefone2PF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(290, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pessoa Fisica", jDesktopPane1);

        ButtonSalvar.setText("Salvar");
        ButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSalvarActionPerformed(evt);
            }
        });

        ButtonCancelar.setText("Cancelar");
        ButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(576, Short.MAX_VALUE)
                .addComponent(ButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonSalvar)
                    .addComponent(ButtonCancelar))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 63, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 495, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextFieldEmailPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldEmailPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldEmailPFActionPerformed

    private void TextFieldNomePFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldNomePFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldNomePFActionPerformed

    private void TextFieldEmailPJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldEmailPJActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldEmailPJActionPerformed

    private void ButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSalvarActionPerformed
        // TODO add your handling code here:
        try {
            grupo8.persistencia.GerenciadorDados g = new grupo8.persistencia.GerenciadorDados();
            java.util.ArrayList<grupo8.pessoas.Pessoa> lista = g.carregarPessoas();

            // LÓGICA PF
            if (tipoPessoa.equals("PF")) {
                String nome = TextFieldNomePF.getText();
                String cpf = TextFieldCpfPF.getText();
                String dtNasc = TextFieldDtNascimetnoPF.getText();
                String email = TextFieldEmailPF.getText();
                String tel1 = TextFieldTelefone1PF.getText();
                String tel2 = TextFieldTelefone2PF.getText();
                String end = TextAreaEnderecoPF.getText();

                if (pessoaEdicao == null) {
                    grupo8.pessoas.PessoaFisica novaPF = new grupo8.pessoas.PessoaFisica(
                        nome, cpf, dtNasc, end, tel1, tel2, email
                    );
                    lista.add(novaPF);
                } else {
                    grupo8.pessoas.PessoaFisica pf = (grupo8.pessoas.PessoaFisica) pessoaEdicao;
                    pf.setNome(nome);
                    pf.setCPF(cpf);
                    pf.setDtNasc(dtNasc);
                    pf.setEndereco(end);
                    pf.setTelefone1(tel1);
                    pf.setTelefone2(tel2);
                    pf.setEmail(email);
                }
            } 
            
            // LÓGICA PJ
            else {
                String razao = TextFieldRasaoSocialPJ.getText();
                String fantasia = TextFieldNomeFantasiaPJ.getText();
                String cnpj = TextFieldCnpjPJ.getText();
                String inscricao = TextFieldInscricaoPJ.getText();
                String dtAbertura = TextFieldDtAbertura.getText();
                String atividade = TextFieldAtividadePJ.getText();
                String email = TextFieldEmailPJ.getText();
                String tel1 = TextFieldTelefone1PJ.getText();
                String tel2 = TextFieldTelefone2PJ.getText();
                String end = TextAreaEnderecoPJ.getText();
                
                // Converte as listas para Arrays (pois sua classe PJ exige Array)
                grupo8.pessoas.PessoaFisica[] arrayPF = listaSociosPF.toArray(new grupo8.pessoas.PessoaFisica[0]);
                grupo8.pessoas.PessoaJuridica[] arrayPJ = listaSociosPJ.toArray(new grupo8.pessoas.PessoaJuridica[0]);

                if (pessoaEdicao == null) {
                    // --- NOVO ---
                    if (listaSociosPF.isEmpty() && listaSociosPJ.isEmpty()) {
                        throw new IllegalArgumentException("É obrigatório adicionar ao menos um sócio (PF ou PJ).");
                    }
                    
                    // Prepara array pro construtor (evita null pointer se vazio)
                    grupo8.pessoas.PessoaFisica[] pfParaConstrutor = arrayPF.length > 0 ? arrayPF : 
                            new grupo8.pessoas.PessoaFisica[] { new grupo8.pessoas.PessoaFisica("Temp", "00000000000", "01/01/2000", "End", "000", "", "a@a.com") };

                    grupo8.pessoas.PessoaJuridica novaPJ = new grupo8.pessoas.PessoaJuridica(
                        cnpj, inscricao, razao, fantasia, dtAbertura, atividade,
                        pfParaConstrutor, 
                        end, tel1, tel2, email
                    );
                    
                    // Ajusta os sócios reais
                    if (arrayPF.length > 0) novaPJ.setSociosPF(arrayPF);
                    if (arrayPJ.length > 0) novaPJ.setSociosPJ(arrayPJ);
                    
                    lista.add(novaPJ);
                    
                } else {
                    // --- EDITAR ---
                    grupo8.pessoas.PessoaJuridica pj = (grupo8.pessoas.PessoaJuridica) pessoaEdicao;
                    pj.setRazaoSocial(razao);
                    pj.setNomeFantasia(fantasia);
                    pj.setCnpj(cnpj);
                    pj.setInscricao(inscricao);
                    pj.setDtAbertura(dtAbertura);
                    pj.setAtividade(atividade);
                    pj.setEndereco(end);
                    pj.setTelefone1(tel1);
                    pj.setTelefone2(tel2);
                    pj.setEmail(email);
                    
                    // ATUALIZA OS SÓCIOS NA EDIÇÃO TAMBÉM!
                    if (arrayPF.length > 0) pj.setSociosPF(arrayPF);
                    if (arrayPJ.length > 0) pj.setSociosPJ(arrayPJ);
                }
            }

            // Salva se for novo (se for edição, o objeto na memória da lista principal foi alterado, 
            // precisamos salvar a lista inteira de lá ou recarregar e salvar aqui. 
            // O mais seguro é salvar a lista carregada AQUI e AGORA).
            g.salvarPessoas(lista);
            
            javax.swing.JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
            this.dispose();

        } catch (IllegalArgumentException e) {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de Validação", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_ButtonSalvarActionPerformed

    private void ButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_ButtonCancelarActionPerformed

    private void ButtonIncluirSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonIncluirSocioActionPerformed
        // TODO add your handling code here:
        // 1. Abre a busca
        JDialogBuscaPessoa dialog = new JDialogBuscaPessoa(null, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        
        grupo8.pessoas.Pessoa selecionado = dialog.getPessoaSelecionada();
        
        if (selecionado != null) {
            // 2. Verifica quem é e adiciona na lista correta
            if (selecionado instanceof grupo8.pessoas.PessoaFisica) {
                // Evita duplicados (opcional)
                if (!listaSociosPF.contains(selecionado)) {
                     listaSociosPF.add((grupo8.pessoas.PessoaFisica) selecionado);
                }
            } 
            else if (selecionado instanceof grupo8.pessoas.PessoaJuridica) {
                // Evita loop infinito (Empresa ser sócia dela mesma)
                if (pessoaEdicao != null && selecionado == pessoaEdicao) { // Comparação de referência funciona aqui
                    javax.swing.JOptionPane.showMessageDialog(this, "A empresa não pode ser sócia dela mesma!");
                    return;
                }
                // Evita duplicados
                if (!listaSociosPJ.contains(selecionado)) {
                    listaSociosPJ.add((grupo8.pessoas.PessoaJuridica) selecionado);
                }
            }
            
            // 3. Atualiza o visual
            atualizarTabelasSocios();
        }
    }//GEN-LAST:event_ButtonIncluirSocioActionPerformed

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
            java.util.logging.Logger.getLogger(JDialogCadastroPessoas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogCadastroPessoas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogCadastroPessoas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogCadastroPessoas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogCadastroPessoas dialog = new JDialogCadastroPessoas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton ButtonCancelar;
    private javax.swing.JButton ButtonIncluirSocio;
    private javax.swing.JButton ButtonSalvar;
    private javax.swing.JPanel PanelHeadSociosPF;
    private javax.swing.JPanel PanelHeadSociosPJ;
    private javax.swing.JPanel PanelSessaoSociosPF;
    private javax.swing.JPanel PanelSessaoSociosPJ;
    private javax.swing.JTable TableSociosPF_PJ;
    private javax.swing.JTable TableSociosPJ_PJ;
    private javax.swing.JTextArea TextAreaEnderecoPF;
    private javax.swing.JTextArea TextAreaEnderecoPJ;
    private javax.swing.JTextField TextFieldAtividadePJ;
    private javax.swing.JTextField TextFieldCnpjPJ;
    private javax.swing.JTextField TextFieldCpfPF;
    private javax.swing.JTextField TextFieldDtAbertura;
    private javax.swing.JTextField TextFieldDtNascimetnoPF;
    private javax.swing.JTextField TextFieldEmailPF;
    private javax.swing.JTextField TextFieldEmailPJ;
    private javax.swing.JTextField TextFieldInscricaoPJ;
    private javax.swing.JTextField TextFieldNomeFantasiaPJ;
    private javax.swing.JTextField TextFieldNomePF;
    private javax.swing.JTextField TextFieldRasaoSocialPJ;
    private javax.swing.JTextField TextFieldTelefone1PF;
    private javax.swing.JTextField TextFieldTelefone1PJ;
    private javax.swing.JTextField TextFieldTelefone2PF;
    private javax.swing.JTextField TextFieldTelefone2PJ;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
