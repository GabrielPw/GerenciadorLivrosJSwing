import model.Livro;
import service.LivroService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPanel extends JPanel {

    private JLabel lblTituloDoPainel, lblNovoLivro, lblAutor, lblMessage;
    private JTextField txtTituloLivro, txtAutorLivro;
    private JButton btnAdicionarNovoLivro;
    private ListaLivrosPanel listaLivrosPanel;
    ConfigPanel(ListaLivrosPanel listaLivrosPanel, LivroService livroService){

        setLayout(new BorderLayout());

        JPanel newBookPanel = new JPanel(new GridBagLayout());
        lblNovoLivro = new JLabel("Titulo");
        lblAutor = new JLabel("Autor");
        lblMessage = new JLabel();

        txtTituloLivro = new JTextField();
        txtAutorLivro = new JTextField();

        btnAdicionarNovoLivro = new JButton("+ Adicionar novo livro");
        btnAdicionarNovoLivro.setFocusPainted(false);
        lblTituloDoPainel = new JLabel("Adicionar livro");
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
        newBookPanel.add(btnAdicionarNovoLivro, constraints);
        constraints.gridy++;
        newBookPanel.add(lblMessage, constraints);
        add(newBookPanel, BorderLayout.NORTH);
        this.listaLivrosPanel = listaLivrosPanel;

        btnAdicionarNovoLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tituloNovoLivro = txtTituloLivro.getText();
                String autorDigitado = txtAutorLivro.getText();

                System.out.printf("novo Livro: " + tituloNovoLivro + " - " + autorDigitado);

                if ( tituloNovoLivro.equals("") || autorDigitado.equals("")){
                    lblMessage.setForeground(ColorPaletteEnum.VERMELHOESCURO.getColor());
                    lblMessage.setText("Preecha todos os campos para add. livro");
                }else{
                    Livro novoLivro = new Livro(tituloNovoLivro, autorDigitado);
                    listaLivrosPanel.getTitulosLivros().add(novoLivro);
                    listaLivrosPanel.getListaLivrosModel().addElement(tituloNovoLivro);
                    livroService.inserirLivro(novoLivro);

                    lblMessage.setForeground(ColorPaletteEnum.VERDEESCURO.getColor());
                    lblMessage.setText("Livro adicionado com sucesso!");
                }
            }
        });
    }

    public JLabel getLblMessage() {
        return lblMessage;
    }

    public JTextField getTxtTituloLivro() {
        return txtTituloLivro;
    }

    public JTextField getTxtAutorLivro() {
        return txtAutorLivro;
    }
}
