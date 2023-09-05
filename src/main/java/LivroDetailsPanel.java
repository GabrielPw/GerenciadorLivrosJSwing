import service.LivroService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LivroDetailsPanel extends JPanel{
    private Janela janela;

    private JLabel lblTituloLivro;
    private JLabel lblAutorLivro;
    private JLabel lblPrecoLivro;
    private JButton btnEditar;
    private JButton btnDeletar;
    ListaLivrosPanel listaLivrosPanel;

    public LivroDetailsPanel(LivroService livroService, Janela janela) {

        setLayout(new BorderLayout());
        setBackground(ColorPaletteEnum.AZUL.getColor());

        JPanel painelInfoLivro = new JPanel(new GridBagLayout());
        JPanel painelBotoesDeleteUpdate = new JPanel();
        GridBagConstraints constraints = new GridBagConstraints();

        painelInfoLivro.setBackground(ColorPaletteEnum.AZUL.getColor());
        painelBotoesDeleteUpdate.setBackground(ColorPaletteEnum.AZUL.getColor());

        lblTituloLivro = new JLabel("");
        configLabelApparence(lblTituloLivro,ColorPaletteEnum.AZUL.getColor(), 10, 0);

        lblAutorLivro = new JLabel();
        configLabelApparence(lblAutorLivro, ColorPaletteEnum.AZUL.getColor(), 0, 10);

        btnDeletar = new JButton("Deletar");
        btnEditar = new JButton("Editar");

        btnDeletar.setBorderPainted(false); btnDeletar.setFocusPainted(false);
        btnEditar.setBorderPainted(false); btnEditar.setFocusPainted(false);

        // Config Componentes
//        lblTituloLivro.setPreferredSize(new Dimension(200, 50));
//        lblTituloLivro.setBorder(new EmptyBorder(20, 30, 20, 30));
        // ----

        constraints.gridy = 0;
        painelInfoLivro.add(lblTituloLivro, constraints);
        constraints.gridy = 1;
        painelInfoLivro.add(lblAutorLivro, constraints);
        painelBotoesDeleteUpdate.add(btnEditar);
        painelBotoesDeleteUpdate.add(btnDeletar);

        add(painelInfoLivro, BorderLayout.NORTH);
        add(painelBotoesDeleteUpdate, BorderLayout.SOUTH);

        this.janela = janela;
        btnDeletar.setEnabled(false);
        btnEditar.setEnabled(false);
        btnDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String tituloLivroSelecionado = lblTituloLivro.getText();
                int resposta = JOptionPane.showConfirmDialog(
                        null,
                        "Tem certeza que deseja excluir: " + tituloLivroSelecionado + "?",
                        "Confirmar Exclusão", JOptionPane.YES_NO_OPTION
                );
                if (resposta == JOptionPane.YES_OPTION){
                    Long idLivro = livroService.obterIdLivroPorTitulo(tituloLivroSelecionado);
                    if(livroService.deletarLivroPorId(idLivro)){ // método deletarLivroPorId retorna true caso deleção tenha sucesso.
                        System.out.printf("Deletou com sucesso no banco");

                        if (listaLivrosPanel == null){
                            try {
                                throw new Exception("listaLivrosPanel não pode ser nula. certifique-se de injeta-la/instancia-la utilizando do método injetarListaLivrosPanel()");
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        DefaultListModel listModel = listaLivrosPanel.getListaLivrosModel();
                        listModel.removeElement(tituloLivroSelecionado);
                        lblTituloLivro.setText("");
                        lblAutorLivro.setText("");}
                }
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String livroSelecionado = lblTituloLivro.getText();
                String autorSelecionado = lblAutorLivro.getText().replace("(", "").replace(")", "");

                if (livroSelecionado != null && !livroSelecionado.equals("")){
                    janela.getCardLayout().show(janela.getCardPanel(), "editPanel");
                    janela.getBtnConfig().setText("<- Voltar");
                    janela.getEditBookPanel().getTxtTituloLivro().setText(livroSelecionado);
                    janela.getEditBookPanel().getTxtAutorLivro().setText(autorSelecionado);
                }else {
                    lblTituloLivro.setText("selecione um livro.");
                }
            }
        });
    }

    public void setLblTituloLivro(String tituloLivro) {
        this.lblTituloLivro.setText(tituloLivro);
    }
    public void setLblAutorLivro(String autorLivro) {
        this.lblAutorLivro.setText(autorLivro);
    }
    public void setLblTituloBackground(Color color) {
        this.lblTituloLivro.setBackground(color);
    }
    public void setLblAutorBackground(Color color) {
        this.lblAutorLivro.setBackground(color);
    }

    public JButton getBtnDeletar() {
        return btnDeletar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JLabel getLblTituloLivro() {
        return lblTituloLivro;
    }

    public JLabel getLblAutorLivro() {
        return lblAutorLivro;
    }

    public void configLabelApparence(JLabel label, Color corBg, int paddingUp, int paddingBottom){
        label.setForeground(Color.WHITE);
        label.setBackground(corBg);
        label.setOpaque(true);

        label.setPreferredSize(new Dimension(260, 50));
        label.setBorder(new EmptyBorder(paddingUp, 30, paddingBottom, 30));
    }

    public void injetarListaLivrosPanel(ListaLivrosPanel listaLivrosPanel){
        this.listaLivrosPanel = listaLivrosPanel;
    }

}
