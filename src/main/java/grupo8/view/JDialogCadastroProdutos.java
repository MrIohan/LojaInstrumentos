/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package grupo8.view;

import grupo8.produto.Produto;
import grupo8.produto.Acessorio;
import grupo8.produto.Instrumento;
import grupo8.produto.instrumentos.InstrumentoCorda;
import grupo8.produto.instrumentos.InstrumentoSopro;
import grupo8.produto.instrumentos.Bateria;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author carpi
 */
public class JDialogCadastroProdutos extends javax.swing.JDialog {

    /**
     * Creates new form JDialogCadastroProdutos
     */
    // Variável que vai guardar o produto que estamos editando.
    // Se for NULL, significa que é um cadastro NOVO.
    private Produto produtoEdicao = null;

    public JDialogCadastroProdutos(java.awt.Frame parent, boolean modal) {
        this(parent, modal, null);
    }

    // Construtor principal que aceita um produto para edição
    public JDialogCadastroProdutos(java.awt.Frame parent, boolean modal, Produto p) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        this.produtoEdicao = p;

        configurarTelaInicial();
    }

    private void configurarTelaInicial() {
        // Limpa abas para começar do zero
        TabbedPane.remove(PainelCordas);
        TabbedPane.remove(PainelSopro);
        TabbedPane.remove(PainelBateria);
        TabbedPane.remove(PainelAcessorio);
        TabbedPane.remove(PainelInstrumentos);

        if (produtoEdicao != null) {
            // MODO EDIÇÃO
            this.setTitle("Editando Produto: " + produtoEdicao.getIdProduto());
            preencherDadosNoFormulario(produtoEdicao);
        } else {
            // MODO NOVO
            this.setTitle("Novo Produto");
            ComboTipo.setSelectedItem("Acessorio"); // Padrão
            // O evento do combo vai adicionar a aba Acessorio
        }
    }

    public void preencherDadosNoFormulario(Produto p) {
        this.produtoEdicao = p; 

        // 1. Define o Tipo no Combo (Isso dispara o evento que cria as abas certas!)
        if (p instanceof Acessorio) {
            ComboTipo.setSelectedItem("Acessorio");
        } else if (p instanceof InstrumentoCorda) {
            ComboTipo.setSelectedItem("Instrumento Corda");
        } else if (p instanceof InstrumentoSopro) {
            ComboTipo.setSelectedItem("Instrumento Sompro");
        } else if (p instanceof Bateria) {
            ComboTipo.setSelectedItem("Instrumento Percussão");
        }

        // 2. Preenche Dados Gerais (Produto)
        TextFieldCodigo.setText(p.getCodigo());
        TextFieldDescricao.setText(p.getDescricao());
        TextFieldMarca.setText(p.getMarca());

        if (p.getDataFabricacao() != null) {
            java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            TextFieldDataFabricacao.setText(p.getDataFabricacao().format(fmt));
        }

        TextFieldPaisFabricacao.setText(p.getPaisFabricacao());
        TextFieldMaterial.setText(p.getMaterial());
        TextFieldCor.setText(p.getCor());
        TextFieldPeso.setText(String.valueOf(p.getPeso()));
        TextFieldQntEstoque.setText(String.valueOf(p.getQntEstoque()));
        TextFieldPrazoGarantia.setText(String.valueOf(p.getPrazoGarantia()));
        TextFieldPreco.setText(String.format("%.2f", p.getPreco()).replace(".", ","));

        // 3. Preenche Dados de Instrumento (Pai) - AQUI ESTAVA O ERRO, FALTAVA ISSO
        if (p instanceof Instrumento) {
            Instrumento inst = (Instrumento) p;
            TextFieldFabricante.setText(inst.getFabricante());
            TextFieldNumSerie.setText(inst.getNumSerie());
            TextFieldTipoProducaoSom.setText(inst.getTipoProducaoSom());
            TextFieldNivelProfissional.setText(inst.getNivelProfissional());
        }

        // 4. Preenche Dados Específicos (Filhos)
        if (p instanceof Acessorio) {
            Acessorio a = (Acessorio) p;
            TextFieldTipo.setText(a.getTipo());
            TextFieldIndicacao.setText(a.getIndicacao());
        } 
        else if (p instanceof InstrumentoCorda) {
            InstrumentoCorda ic = (InstrumentoCorda) p;
            TextFieldTipoInstrumentoCorda.setText(ic.getTipo());
            TextFieldNumCordaInstrumentoCorda.setText(String.valueOf(ic.getNumCorda()));
            TextFieldEncrodamentoInstrumentoCorda.setText(ic.getEncordoamento());
            TextFieldCaptadoresInstrumentoCorda.setText(ic.getCaptadores());
            TextFieldNumTrastesInstrumentoCorda.setText(String.valueOf(ic.getNumTrastes()));
            TextFieldMaterialBracoInstrumentoCorda.setText(ic.getMaterialBraco());
            TextFieldEncaixeBracoInstrumentoCorda.setText(ic.getEncaixeBraco());
            TextFieldFormatoCorpoInstrumentoCorda.setText(ic.getFormatoCorpo());
            TextFieldCorEscudoInstrumentoCorda.setText(ic.getCorEscudo());
        }
        else if (p instanceof InstrumentoSopro) {
            InstrumentoSopro is = (InstrumentoSopro) p;
            TextFieldTipoInstrumentoSopro.setText(is.getTipo());
            TextFieldAfinacaoInstrumentoSopro.setText(is.getAfinacao());
            TextFieldEmbocaduraInstrumentoSopro.setText(is.getEmbocadura());
            TextFieldChaveInstrumentoSopro.setText(is.getChave());
            TextAreaInfoAdicionaisInstrumentoSopro.setText(is.getInfoAdicionais());
        }
        else if (p instanceof Bateria) {
            Bateria b = (Bateria) p;
            TextFieldNumTonsBateria.setText(String.valueOf(b.getNumTons()));
            TextFieldNumPecasBateria.setText(String.valueOf(b.getNumPecas()));
            TextFieldMaterialPeleBateria.setText(b.getMaterialPele());
            TextFieldTamanhoCaixaBateria.setText(String.valueOf(b.getTamanhoCaixa()));
            TextFieldTamnhoBumboBateria.setText(String.valueOf(b.getTamanhoBumbo()));
            // Atenção: Variável com nome trocado no design (Tipo Prato)
            TextFieldMaterialBracoInstrumentoCorda1.setText(b.getTipoPrato()); 
            TextFieldTipoBaquetaBateria.setText(b.getTipoBaqueta());
            TextFieldTipoArcoBateria.setText(b.getTipoAro());
        }
    }
    
    // Mantive esse método público para compatibilidade caso o JFrameGestao chame ele diretamente
    public void preencherDados(Produto p) {
        preencherDadosNoFormulario(p);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabbedPane = new javax.swing.JTabbedPane();
        PainelGeral = new javax.swing.JDesktopPane();
        ComboTipo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        TextFieldDescricao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TextFieldCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TextFieldMarca = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TextFieldPaisFabricacao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TextFieldDataFabricacao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TextFieldMaterial = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TextFieldCor = new javax.swing.JTextField();
        TextFieldPeso = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TextFieldQntEstoque = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TextFieldPrazoGarantia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TextFieldPreco = new javax.swing.JTextField();
        ButtonSalver = new javax.swing.JButton();
        ButtonCancelar = new javax.swing.JButton();
        PainelInstrumentos = new javax.swing.JDesktopPane();
        jLabel24 = new javax.swing.JLabel();
        TextFieldFabricante = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        TextFieldNumSerie = new javax.swing.JTextField();
        TextFieldTipoProducaoSom = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        TextFieldNivelProfissional = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        PainelCordas = new javax.swing.JDesktopPane();
        TextFieldTipoInstrumentoCorda = new javax.swing.JTextField();
        TextFieldNumCordaInstrumentoCorda = new javax.swing.JTextField();
        TextFieldEncrodamentoInstrumentoCorda = new javax.swing.JTextField();
        TextFieldCaptadoresInstrumentoCorda = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        TextFieldNumTrastesInstrumentoCorda = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        TextFieldMaterialBracoInstrumentoCorda = new javax.swing.JTextField();
        TextFieldEncaixeBracoInstrumentoCorda = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        TextFieldFormatoCorpoInstrumentoCorda = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        TextFieldCorEscudoInstrumentoCorda = new javax.swing.JTextField();
        PainelSopro = new javax.swing.JDesktopPane();
        jLabel28 = new javax.swing.JLabel();
        TextFieldTipoInstrumentoSopro = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        TextFieldAfinacaoInstrumentoSopro = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        TextFieldEmbocaduraInstrumentoSopro = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        TextFieldChaveInstrumentoSopro = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextAreaInfoAdicionaisInstrumentoSopro = new javax.swing.JTextArea();
        PainelBateria = new javax.swing.JDesktopPane();
        jLabel32 = new javax.swing.JLabel();
        TextFieldNumTonsBateria = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        TextFieldNumPecasBateria = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        TextFieldMaterialPeleBateria = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        TextFieldTamanhoCaixaBateria = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        TextFieldTamnhoBumboBateria = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        TextFieldMaterialBracoInstrumentoCorda1 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        TextFieldTipoBaquetaBateria = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        TextFieldTipoArcoBateria = new javax.swing.JTextField();
        PainelAcessorio = new javax.swing.JDesktopPane();
        jLabel12 = new javax.swing.JLabel();
        TextFieldTipo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        TextFieldIndicacao = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        ComboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acessorio", "Instrumento Corda", "Instrumento Sompro", "Instrumento Percussão" }));
        ComboTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboTipoActionPerformed(evt);
            }
        });

        jLabel1.setText("Descrição");

        TextFieldDescricao.setToolTipText("digite a descrção do produto aqui");

        jLabel2.setText("Código");

        TextFieldCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldCodigoActionPerformed(evt);
            }
        });

        jLabel3.setText("Marca");

        jLabel4.setText("Pais de Fabricação");

        jLabel5.setText("Data de Fabricação");

        TextFieldDataFabricacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldDataFabricacaoActionPerformed(evt);
            }
        });

        jLabel6.setText("Material");

        TextFieldMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldMaterialActionPerformed(evt);
            }
        });

        jLabel7.setText("Cor");

        TextFieldCor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldCorActionPerformed(evt);
            }
        });

        jLabel9.setText("Peso");

        jLabel8.setText("Quantidade Estoque");

        jLabel10.setText("Prazo de Garantia");

        TextFieldPrazoGarantia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldPrazoGarantiaActionPerformed(evt);
            }
        });

        jLabel11.setText("Tipo produto");

        jLabel14.setText("Preço");

        TextFieldPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldPrecoActionPerformed(evt);
            }
        });

        ButtonSalver.setText("SALVAR");
        ButtonSalver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSalverActionPerformed(evt);
            }
        });

        ButtonCancelar.setText("CANCELAR");
        ButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCancelarActionPerformed(evt);
            }
        });

        PainelGeral.setLayer(ComboTipo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(TextFieldDescricao, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(TextFieldCodigo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(TextFieldMarca, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(TextFieldPaisFabricacao, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(TextFieldDataFabricacao, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(TextFieldMaterial, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(TextFieldCor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(TextFieldPeso, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(TextFieldQntEstoque, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(TextFieldPrazoGarantia, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(TextFieldPreco, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(ButtonSalver, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelGeral.setLayer(ButtonCancelar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout PainelGeralLayout = new javax.swing.GroupLayout(PainelGeral);
        PainelGeral.setLayout(PainelGeralLayout);
        PainelGeralLayout.setHorizontalGroup(
            PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelGeralLayout.createSequentialGroup()
                        .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextFieldQntEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(TextFieldPrazoGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(TextFieldPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(ComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(168, 168, 168))
                    .addGroup(PainelGeralLayout.createSequentialGroup()
                        .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(TextFieldDescricao))
                        .addGap(18, 18, 18)
                        .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(TextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(TextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5))
                    .addGroup(PainelGeralLayout.createSequentialGroup()
                        .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(TextFieldPaisFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(TextFieldDataFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(TextFieldMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PainelGeralLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TextFieldCor))
                        .addGap(18, 18, 18)
                        .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(TextFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelGeralLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonSalver, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PainelGeralLayout.setVerticalGroup(
            PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PainelGeralLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextFieldDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PainelGeralLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(PainelGeralLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(28, 28, 28)))
                    .addComponent(TextFieldCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PainelGeralLayout.createSequentialGroup()
                        .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextFieldPaisFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldDataFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PainelGeralLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelGeralLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelGeralLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldQntEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldPrazoGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addGroup(PainelGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonSalver)
                    .addComponent(ButtonCancelar))
                .addContainerGap())
        );

        TabbedPane.addTab("Geral", PainelGeral);

        jLabel24.setText("Fabricante:");

        jLabel25.setText("Numero de Serie:");

        TextFieldTipoProducaoSom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldTipoProducaoSomActionPerformed(evt);
            }
        });

        jLabel26.setText("Tipo Produção Som:");

        TextFieldNivelProfissional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldNivelProfissionalActionPerformed(evt);
            }
        });

        jLabel27.setText("Nivél Profissional:");

        PainelInstrumentos.setLayer(jLabel24, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelInstrumentos.setLayer(TextFieldFabricante, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelInstrumentos.setLayer(jLabel25, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelInstrumentos.setLayer(TextFieldNumSerie, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelInstrumentos.setLayer(TextFieldTipoProducaoSom, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelInstrumentos.setLayer(jLabel26, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelInstrumentos.setLayer(TextFieldNivelProfissional, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelInstrumentos.setLayer(jLabel27, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout PainelInstrumentosLayout = new javax.swing.GroupLayout(PainelInstrumentos);
        PainelInstrumentos.setLayout(PainelInstrumentosLayout);
        PainelInstrumentosLayout.setHorizontalGroup(
            PainelInstrumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelInstrumentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelInstrumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(TextFieldFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelInstrumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25)
                    .addComponent(TextFieldNumSerie, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelInstrumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextFieldTipoProducaoSom, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelInstrumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(TextFieldNivelProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        PainelInstrumentosLayout.setVerticalGroup(
            PainelInstrumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelInstrumentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelInstrumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PainelInstrumentosLayout.createSequentialGroup()
                        .addGroup(PainelInstrumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelInstrumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextFieldNumSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldTipoProducaoSom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldNivelProfissional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PainelInstrumentosLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(408, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Instrumentos", PainelInstrumentos);

        jLabel15.setText("Tipo:");

        jLabel16.setText("Numero de Cordas:");

        jLabel17.setText("Encordamento:");

        jLabel18.setText("Captadores:");

        jLabel19.setText("Material do Braço:");

        jLabel20.setText("Numero de Trastes:");

        TextFieldNumTrastesInstrumentoCorda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldNumTrastesInstrumentoCordaActionPerformed(evt);
            }
        });

        jLabel21.setText("Encaixe do Braço:");

        jLabel22.setText("Formato do Corpo:");

        jLabel23.setText("Cor do Escudo:");

        PainelCordas.setLayer(TextFieldTipoInstrumentoCorda, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(TextFieldNumCordaInstrumentoCorda, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(TextFieldEncrodamentoInstrumentoCorda, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(TextFieldCaptadoresInstrumentoCorda, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(TextFieldNumTrastesInstrumentoCorda, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(jLabel21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(TextFieldMaterialBracoInstrumentoCorda, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(TextFieldEncaixeBracoInstrumentoCorda, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(jLabel22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(TextFieldFormatoCorpoInstrumentoCorda, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(jLabel23, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelCordas.setLayer(TextFieldCorEscudoInstrumentoCorda, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout PainelCordasLayout = new javax.swing.GroupLayout(PainelCordas);
        PainelCordas.setLayout(PainelCordasLayout);
        PainelCordasLayout.setHorizontalGroup(
            PainelCordasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelCordasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelCordasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(TextFieldMaterialBracoInstrumentoCorda, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(TextFieldTipoInstrumentoCorda)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelCordasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelCordasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(TextFieldEncaixeBracoInstrumentoCorda)
                        .addComponent(TextFieldNumCordaInstrumentoCorda, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelCordasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextFieldFormatoCorpoInstrumentoCorda)
                    .addComponent(TextFieldNumTrastesInstrumentoCorda)
                    .addGroup(PainelCordasLayout.createSequentialGroup()
                        .addGroup(PainelCordasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel22))
                        .addGap(0, 81, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelCordasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(PainelCordasLayout.createSequentialGroup()
                        .addGroup(PainelCordasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(TextFieldCorEscudoInstrumentoCorda, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextFieldEncrodamentoInstrumentoCorda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelCordasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(TextFieldCaptadoresInstrumentoCorda, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        PainelCordasLayout.setVerticalGroup(
            PainelCordasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelCordasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelCordasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelCordasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldTipoInstrumentoCorda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldNumCordaInstrumentoCorda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldEncrodamentoInstrumentoCorda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldCaptadoresInstrumentoCorda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldNumTrastesInstrumentoCorda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelCordasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelCordasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldMaterialBracoInstrumentoCorda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldEncaixeBracoInstrumentoCorda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldFormatoCorpoInstrumentoCorda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldCorEscudoInstrumentoCorda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(346, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Cordas", PainelCordas);

        jLabel28.setText("Tipo:");

        jLabel29.setText("Afinação:");

        jLabel30.setText("Embocadura:");

        jLabel31.setText("Chave:");

        jLabel33.setText("Informações Adicionais:");

        TextAreaInfoAdicionaisInstrumentoSopro.setColumns(20);
        TextAreaInfoAdicionaisInstrumentoSopro.setRows(5);
        jScrollPane1.setViewportView(TextAreaInfoAdicionaisInstrumentoSopro);

        PainelSopro.setLayer(jLabel28, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelSopro.setLayer(TextFieldTipoInstrumentoSopro, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelSopro.setLayer(jLabel29, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelSopro.setLayer(TextFieldAfinacaoInstrumentoSopro, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelSopro.setLayer(jLabel30, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelSopro.setLayer(TextFieldEmbocaduraInstrumentoSopro, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelSopro.setLayer(jLabel31, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelSopro.setLayer(TextFieldChaveInstrumentoSopro, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelSopro.setLayer(jLabel33, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelSopro.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout PainelSoproLayout = new javax.swing.GroupLayout(PainelSopro);
        PainelSopro.setLayout(PainelSoproLayout);
        PainelSoproLayout.setHorizontalGroup(
            PainelSoproLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelSoproLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelSoproLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelSoproLayout.createSequentialGroup()
                        .addGroup(PainelSoproLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextFieldTipoInstrumentoSopro, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelSoproLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextFieldAfinacaoInstrumentoSopro, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelSoproLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PainelSoproLayout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(112, 112, 112)
                                .addComponent(jLabel31))
                            .addGroup(PainelSoproLayout.createSequentialGroup()
                                .addComponent(TextFieldEmbocaduraInstrumentoSopro, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextFieldChaveInstrumentoSopro, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel33)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        PainelSoproLayout.setVerticalGroup(
            PainelSoproLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelSoproLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelSoproLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelSoproLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldTipoInstrumentoSopro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldAfinacaoInstrumentoSopro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldEmbocaduraInstrumentoSopro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldChaveInstrumentoSopro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Sopro", PainelSopro);

        jLabel32.setText("Numero de Tons:");

        jLabel34.setText("Numero de Peças:");

        jLabel35.setText("Material Pele:");

        TextFieldMaterialPeleBateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldMaterialPeleBateriaActionPerformed(evt);
            }
        });

        jLabel36.setText("Tamanho Caixa:");

        jLabel37.setText("Tamanho Bumbo:");

        jLabel38.setText("Tipo Prato:");

        jLabel39.setText("Tipo Baqueta:");

        jLabel40.setText("Tipo Aro:");

        PainelBateria.setLayer(jLabel32, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelBateria.setLayer(TextFieldNumTonsBateria, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelBateria.setLayer(jLabel34, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelBateria.setLayer(TextFieldNumPecasBateria, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelBateria.setLayer(jLabel35, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelBateria.setLayer(TextFieldMaterialPeleBateria, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelBateria.setLayer(jLabel36, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelBateria.setLayer(TextFieldTamanhoCaixaBateria, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelBateria.setLayer(jLabel37, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelBateria.setLayer(TextFieldTamnhoBumboBateria, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelBateria.setLayer(jLabel38, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelBateria.setLayer(TextFieldMaterialBracoInstrumentoCorda1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelBateria.setLayer(jLabel39, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelBateria.setLayer(TextFieldTipoBaquetaBateria, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelBateria.setLayer(jLabel40, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelBateria.setLayer(TextFieldTipoArcoBateria, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout PainelBateriaLayout = new javax.swing.GroupLayout(PainelBateria);
        PainelBateria.setLayout(PainelBateriaLayout);
        PainelBateriaLayout.setHorizontalGroup(
            PainelBateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelBateriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelBateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(TextFieldMaterialBracoInstrumentoCorda1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(TextFieldNumTonsBateria)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelBateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelBateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(TextFieldTipoBaquetaBateria)
                        .addComponent(TextFieldNumPecasBateria, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelBateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextFieldTipoArcoBateria)
                    .addComponent(TextFieldMaterialPeleBateria)
                    .addGroup(PainelBateriaLayout.createSequentialGroup()
                        .addGroup(PainelBateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel40))
                        .addGap(0, 113, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelBateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextFieldTamanhoCaixaBateria, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelBateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(TextFieldTamnhoBumboBateria, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        PainelBateriaLayout.setVerticalGroup(
            PainelBateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelBateriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelBateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel34)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelBateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldNumTonsBateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldNumPecasBateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldTamanhoCaixaBateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldTamnhoBumboBateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldMaterialPeleBateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelBateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelBateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldMaterialBracoInstrumentoCorda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldTipoBaquetaBateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldTipoArcoBateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(346, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Bateria", PainelBateria);

        jLabel12.setText("Tipo");

        jLabel13.setText("Indicação");

        TextFieldIndicacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldIndicacaoActionPerformed(evt);
            }
        });

        PainelAcessorio.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelAcessorio.setLayer(TextFieldTipo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelAcessorio.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PainelAcessorio.setLayer(TextFieldIndicacao, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout PainelAcessorioLayout = new javax.swing.GroupLayout(PainelAcessorio);
        PainelAcessorio.setLayout(PainelAcessorioLayout);
        PainelAcessorioLayout.setHorizontalGroup(
            PainelAcessorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelAcessorioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelAcessorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(TextFieldTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelAcessorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(TextFieldIndicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(618, Short.MAX_VALUE))
        );
        PainelAcessorioLayout.setVerticalGroup(
            PainelAcessorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelAcessorioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelAcessorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelAcessorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextFieldIndicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(408, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Acessorio", PainelAcessorio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabbedPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboTipoActionPerformed
        String selecionado = (String) ComboTipo.getSelectedItem();

        // Remove todas as abas para limpar
        TabbedPane.remove(PainelCordas);
        TabbedPane.remove(PainelSopro);
        TabbedPane.remove(PainelBateria);
        TabbedPane.remove(PainelAcessorio);
        TabbedPane.remove(PainelInstrumentos); // Aba de Instrumentos genéricos

        if (selecionado != null) {
            if (selecionado.equals("Acessorio")) {
                TabbedPane.addTab("Acessorio", PainelAcessorio);
                TabbedPane.setSelectedComponent(PainelAcessorio);
            } else {
                // Se for qualquer instrumento, mostra a aba de dados comuns primeiro
                TabbedPane.addTab("Instrumentos", PainelInstrumentos);

                switch (selecionado) {
                    case "Instrumento Corda":
                        TabbedPane.addTab("Cordas", PainelCordas);
                        TabbedPane.setSelectedComponent(PainelCordas);
                        break;
                    case "Instrumento Sompro":
                        TabbedPane.addTab("Sopro", PainelSopro);
                        TabbedPane.setSelectedComponent(PainelSopro);
                        break;
                    case "Instrumento Percussão":
                        TabbedPane.addTab("Percussão", PainelBateria);
                        TabbedPane.setSelectedComponent(PainelBateria);
                        break;
                }
            }
        }
    }//GEN-LAST:event_ComboTipoActionPerformed

    private void TextFieldCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldCodigoActionPerformed

    private void TextFieldDataFabricacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldDataFabricacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldDataFabricacaoActionPerformed

    private void TextFieldMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldMaterialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldMaterialActionPerformed

    private void TextFieldCorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldCorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldCorActionPerformed

    private void TextFieldPrazoGarantiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldPrazoGarantiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldPrazoGarantiaActionPerformed

    private void TextFieldIndicacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldIndicacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldIndicacaoActionPerformed

    private void ButtonSalverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSalverActionPerformed
        // TODO add your handling code here:
        try {
            // --- 1. DADOS GERAIS (Produto) ---
            String codigo = TextFieldCodigo.getText();
            String descricao = TextFieldDescricao.getText();
            String marca = TextFieldMarca.getText();
            String dataFab = TextFieldDataFabricacao.getText();
            String pais = TextFieldPaisFabricacao.getText();
            String material = TextFieldMaterial.getText();
            String cor = TextFieldCor.getText();
            String peso = TextFieldPeso.getText();
            String estoque = TextFieldQntEstoque.getText();
            String garantia = TextFieldPrazoGarantia.getText();
            String preco = TextFieldPreco.getText().replace(",", ".");

            // --- 2. DADOS DE INSTRUMENTO (Comum) ---
            String fabricante = TextFieldFabricante.getText();
            String numSerie = TextFieldNumSerie.getText();
            String tipoSom = TextFieldTipoProducaoSom.getText();
            String nivel = TextFieldNivelProfissional.getText();

            String tipoSelecionado = (String) ComboTipo.getSelectedItem();
            Produto novoProduto = null;

            switch (tipoSelecionado) {
                case "Acessorio":
                    novoProduto = new Acessorio(
                        TextFieldTipo.getText(), 
                        TextFieldIndicacao.getText(),
                        codigo, descricao, marca, dataFab, pais, material, cor, preco, peso, estoque, garantia
                    );
                    break;

                case "Instrumento Corda":
                    String tipoCorda = TextFieldTipoInstrumentoCorda.getText();
                    String numCordas = TextFieldNumCordaInstrumentoCorda.getText();
                    String encordoamento = TextFieldEncrodamentoInstrumentoCorda.getText();
                    String captadores = TextFieldCaptadoresInstrumentoCorda.getText();
                    String numTrastes = TextFieldNumTrastesInstrumentoCorda.getText();
                    String matBraco = TextFieldMaterialBracoInstrumentoCorda.getText();
                    String encaixe = TextFieldEncaixeBracoInstrumentoCorda.getText();
                    String formato = TextFieldFormatoCorpoInstrumentoCorda.getText();
                    String corEscudo = TextFieldCorEscudoInstrumentoCorda.getText();

                    novoProduto = new InstrumentoCorda(
                        tipoCorda, numCordas, encordoamento, captadores, numTrastes, 
                        matBraco, encaixe, formato, corEscudo,
                        fabricante, numSerie, tipoSom, nivel, 
                        codigo, descricao, marca, dataFab, pais, material, cor, preco, peso, estoque, garantia
                    );
                    break;

                case "Instrumento Sompro": 
                    String tipoSopro = TextFieldTipoInstrumentoSopro.getText();
                    String afinacao = TextFieldAfinacaoInstrumentoSopro.getText();
                    String embocadura = TextFieldEmbocaduraInstrumentoSopro.getText();
                    String chave = TextFieldChaveInstrumentoSopro.getText();
                    String infoAdd = TextAreaInfoAdicionaisInstrumentoSopro.getText();

                    novoProduto = new InstrumentoSopro(
                        tipoSopro, afinacao, embocadura, chave, infoAdd,
                        fabricante, numSerie, tipoSom, nivel,
                        codigo, descricao, marca, dataFab, pais, material, cor, preco, peso, estoque, garantia
                    );
                    break;

                case "Instrumento Percussão":
                    String numTons = TextFieldNumTonsBateria.getText();
                    String numPecas = TextFieldNumPecasBateria.getText();
                    String matPele = TextFieldMaterialPeleBateria.getText();
                    String tamCaixa = TextFieldTamanhoCaixaBateria.getText();
                    String tamBumbo = TextFieldTamnhoBumboBateria.getText();
                    String tipoPrato = TextFieldMaterialBracoInstrumentoCorda1.getText(); 
                    String tipoBaqueta = TextFieldTipoBaquetaBateria.getText();
                    String tipoAro = TextFieldTipoArcoBateria.getText();

                    novoProduto = new Bateria(
                        numTons, numPecas, matPele, tamCaixa, tamBumbo, tipoPrato, tipoBaqueta, tipoAro,
                        fabricante, numSerie, tipoSom, nivel,
                        codigo, descricao, marca, dataFab, pais, material, cor, preco, peso, estoque, garantia
                    );
                    break;

                default:
                    throw new IllegalArgumentException("Selecione um tipo de produto válido!");
            }

            // --- 3. PERSISTÊNCIA ---
            if (novoProduto != null) {
                grupo8.persistencia.GerenciadorDados g = new grupo8.persistencia.GerenciadorDados();
                ArrayList<Produto> lista = g.carregarLista();

                if (produtoEdicao == null) {
                    // NOVO
                    lista.add(novoProduto);
                } else {
                    // EDITAR
                    int index = -1;
                    for (int i = 0; i < lista.size(); i++) {
                        if (lista.get(i).getIdProduto() == produtoEdicao.getIdProduto()) {
                            index = i;
                            break;
                        }
                    }
                    if (index != -1) {
                        // Dica: Se tiver setIdProduto na classe Produto, use aqui para manter o ID:
                        // novoProduto.setIdProduto(produtoEdicao.getIdProduto());
                        lista.set(index, novoProduto);
                    }
                }

                g.salvarLista(lista);
                javax.swing.JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
                this.dispose();
            }

        } catch (IllegalArgumentException e) {
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de Validação", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro crítico: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_ButtonSalverActionPerformed

    private void ButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_ButtonCancelarActionPerformed

    private void TextFieldPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldPrecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldPrecoActionPerformed

    private void TextFieldNumTrastesInstrumentoCordaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldNumTrastesInstrumentoCordaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldNumTrastesInstrumentoCordaActionPerformed

    private void TextFieldTipoProducaoSomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldTipoProducaoSomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldTipoProducaoSomActionPerformed

    private void TextFieldNivelProfissionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldNivelProfissionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldNivelProfissionalActionPerformed

    private void TextFieldMaterialPeleBateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldMaterialPeleBateriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldMaterialPeleBateriaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogCadastroProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogCadastroProdutos dialog = new JDialogCadastroProdutos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton ButtonSalver;
    private javax.swing.JComboBox<String> ComboTipo;
    private javax.swing.JDesktopPane PainelAcessorio;
    private javax.swing.JDesktopPane PainelBateria;
    private javax.swing.JDesktopPane PainelCordas;
    private javax.swing.JDesktopPane PainelGeral;
    private javax.swing.JDesktopPane PainelInstrumentos;
    private javax.swing.JDesktopPane PainelSopro;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JTextArea TextAreaInfoAdicionaisInstrumentoSopro;
    private javax.swing.JTextField TextFieldAfinacaoInstrumentoSopro;
    private javax.swing.JTextField TextFieldCaptadoresInstrumentoCorda;
    private javax.swing.JTextField TextFieldChaveInstrumentoSopro;
    private javax.swing.JTextField TextFieldCodigo;
    private javax.swing.JTextField TextFieldCor;
    private javax.swing.JTextField TextFieldCorEscudoInstrumentoCorda;
    private javax.swing.JTextField TextFieldDataFabricacao;
    private javax.swing.JTextField TextFieldDescricao;
    private javax.swing.JTextField TextFieldEmbocaduraInstrumentoSopro;
    private javax.swing.JTextField TextFieldEncaixeBracoInstrumentoCorda;
    private javax.swing.JTextField TextFieldEncrodamentoInstrumentoCorda;
    private javax.swing.JTextField TextFieldFabricante;
    private javax.swing.JTextField TextFieldFormatoCorpoInstrumentoCorda;
    private javax.swing.JTextField TextFieldIndicacao;
    private javax.swing.JTextField TextFieldMarca;
    private javax.swing.JTextField TextFieldMaterial;
    private javax.swing.JTextField TextFieldMaterialBracoInstrumentoCorda;
    private javax.swing.JTextField TextFieldMaterialBracoInstrumentoCorda1;
    private javax.swing.JTextField TextFieldMaterialPeleBateria;
    private javax.swing.JTextField TextFieldNivelProfissional;
    private javax.swing.JTextField TextFieldNumCordaInstrumentoCorda;
    private javax.swing.JTextField TextFieldNumPecasBateria;
    private javax.swing.JTextField TextFieldNumSerie;
    private javax.swing.JTextField TextFieldNumTonsBateria;
    private javax.swing.JTextField TextFieldNumTrastesInstrumentoCorda;
    private javax.swing.JTextField TextFieldPaisFabricacao;
    private javax.swing.JTextField TextFieldPeso;
    private javax.swing.JTextField TextFieldPrazoGarantia;
    private javax.swing.JTextField TextFieldPreco;
    private javax.swing.JTextField TextFieldQntEstoque;
    private javax.swing.JTextField TextFieldTamanhoCaixaBateria;
    private javax.swing.JTextField TextFieldTamnhoBumboBateria;
    private javax.swing.JTextField TextFieldTipo;
    private javax.swing.JTextField TextFieldTipoArcoBateria;
    private javax.swing.JTextField TextFieldTipoBaquetaBateria;
    private javax.swing.JTextField TextFieldTipoInstrumentoCorda;
    private javax.swing.JTextField TextFieldTipoInstrumentoSopro;
    private javax.swing.JTextField TextFieldTipoProducaoSom;
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
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
