import model.Livro;
import service.LivroService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBookPanel extends JPanel {

    private LivroDetailsPanel livroDetailsPanel;
    private ListaLivrosPanel listaLivrosPanel;
    private LivroService livroService;
    private JLabel lblTituloDoPainel, lblNovoLivro, lblAutor, lblMessage;
    private JTextField txtTituloLivro, txtAutorLivro;
    private JButton btnSalvarEdicao;
    public EditBookPanel(LivroService livroService, LivroDetailsPanel detailsPanel, ListaLivrosPanel listaLivrosPanel, CardLayout cardLayout, JPanel cardPanel){
        setLayout(new BorderLayout());

        JPanel newBookPanel = new JPanel(new GridBagLayout());
        lblNovoLivro = new JLabel("Titulo");
        lblAutor = new JLabel("Autor");
        lblMessage = new JLabel();

        txtTituloLivro = new JTextField();
        txtAutorLivro = new JTextField();

        btnSalvarEdicao = new JButton("Salvar edição");
        btnSalvarEdicao.setFocusPainted(false);
        lblTituloDoPainel = new JLabel("Editar Livro");
        lblTituloDoPainel.setBorder(new EmptyBorder(10, 0, 15, 0));

        txtTituloLivro.setPreferredSize(new Dimension(170, txtTituloLivro.getPreferredSize().height));
        txtAutorLivro.setPreferredSize(new Dimension(170, txtTituloLivro.getPreferredSize().height));

        lblMessage.setBorder(new EmptyBorder(20, 0, 0, 0));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;

        newBookPanel.add(lblTituloDoPainel, constraints);
        constraints.gridy++;
        newBookPanel.add(lblNovoLivro, constraints);
        constraints.gridx++;
        newBookPanel.add(txtTituloLivro, constraints);
        constraints.gridy++;
        constraints.gridx--;
        newBookPanel.add(lblAutor, constraints);
        constraints.gridx++;
        newBookPanel.add(txtAutorLivro, constraints);
        constraints.gridy++;
        newBookPanel.add(btnSalvarEdicao, constraints);
        constraints.gridy++;
        newBookPanel.add(lblMessage, constraints);
        add(newBookPanel, BorderLayout.NORTH);

        this.livroDetailsPanel = detailsPanel;
        this.livroService = livroService;
        btnSalvarEdicao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicou em salvar.");

                String tituloLivroSelecionado = livroDetailsPanel.getLblTituloLivro().getText();
                String autorLivroSelecionado = livroDetailsPanel.getLblAutorLivro().getText().replace("(", "").replace(")", "");

                // A edição só será efetuada se houverem diferenças entre o texto digitado na edição e o nome original do livro/autor.
                if (!tituloLivroSelecionado.equals(txtTituloLivro.getText()) || !autorLivroSelecionado.equals(txtAutorLivro.getText())){

                    Long idLivro = livroService.obterIdLivroPorTitulo(tituloLivroSelecionado);
                    livroService.atualizarLivro(idLivro, txtTituloLivro.getText(), txtAutorLivro.getText());

                    // Atualizando lista.
                    listaLivrosPanel.getListaLivrosModel().clear();
                    listaLivrosPanel.setTitulosLivros(livroService.obterTodos());
                    for (Livro livro : listaLivrosPanel.getTitulosLivros()){
                        listaLivrosPanel.getListaLivrosModel().addElement(livro.getNome());
                    }

                    livroDetailsPanel.setLblTituloLivro("");
                    livroDetailsPanel.setLblAutorLivro("");

                    // Exibindo janela informando usuário edição bem-sucedida.
                    JOptionPane.showConfirmDialog(null,
                            "Edição bem sucedida!",
                            "Edição",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE
                    );

                    int indice = 0;
                    for (Livro livro : listaLivrosPanel.getTitulosLivros()){
                        if (livro.getNome().equals(txtTituloLivro.getText())){
                            listaLivrosPanel.getListaLivroComponent().setSelectedIndex(indice);
                            break;
                        }
                        indice++;
                    }
                    cardLayout.show(cardPanel, "listaLivros");
                }
            }
        });
    }

    public JTextField getTxtTituloLivro() {
        return txtTituloLivro;
    }

    public JTextField getTxtAutorLivro() {
        return txtAutorLivro;
    }
}
