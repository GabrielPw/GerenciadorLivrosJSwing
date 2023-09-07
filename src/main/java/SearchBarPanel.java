import model.Livro;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBarPanel extends JPanel{

    private JButton btnSearch;
    private PlaceholderTextField searchTextField;
    private String buscaDigitada = "";

    SearchBarPanel(ListaLivrosPanel listaLivrosPanel){

        setLayout(new BorderLayout());
        btnSearch = new JButton("Buscar");
        btnSearch.setBorderPainted(false);
        btnSearch.setFocusPainted(false);
        searchTextField = new PlaceholderTextField(70);
        //searchTextField.setBorder(new LineBorder(Color.black, 2));
        searchTextField.setPlaceholder("Livros, Autores");

        add(searchTextField, BorderLayout.CENTER);
        add(btnSearch, BorderLayout.EAST);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscaDigitada = searchTextField.getText();
                if (!buscaDigitada.trim().equals("")){
                    System.out.println("PESQUISANDO: " + buscaDigitada);
                    listaLivrosPanel.atualizaListaConformePesquisa(buscaDigitada);
                }else {
                    listaLivrosPanel.getListaLivrosModel().clear();
                    for (Livro livro : listaLivrosPanel.getTitulosLivros()){
                        listaLivrosPanel.getListaLivrosModel().addElement(livro.getNome());
                    }
                }
            }
        });
    }
}