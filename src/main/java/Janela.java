


import service.LivroService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Janela extends JFrame {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jswingBiblioteca");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    private LivroService livroService = new LivroService(entityManager);
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private EditBookPanel editBookPanel;
    private JButton btnConfig;
    private LivroDetailsPanel livroDetailsPanel;
    private ListaLivrosPanel listaLivrosPanel;
    Janela(){

        setTitle("Gerenciador de Livros - Java/JSwing.");
        setSize(new Dimension(600, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        JPanel mainPanel = new JPanel(new BorderLayout());
        LivroDetailsPanel livroDetailsPanel = new LivroDetailsPanel(livroService, this);
        JPanel searchPanel = new SearchBarPanel();
        listaLivrosPanel = new ListaLivrosPanel(livroDetailsPanel, livroService);
        livroDetailsPanel.injetarListaLivrosPanel(listaLivrosPanel);
        JPanel bottomConfigPanel = new JPanel();
        ConfigPanel configPanel = new ConfigPanel(listaLivrosPanel, livroService);
        editBookPanel = new EditBookPanel(livroService, livroDetailsPanel, listaLivrosPanel, cardLayout, cardPanel);

        livroDetailsPanel.setPreferredSize(new Dimension(260, 200));
        bottomConfigPanel.setLayout(new BoxLayout(bottomConfigPanel, BoxLayout.X_AXIS));
        bottomConfigPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        cardPanel.add(listaLivrosPanel, "listaLivros");
        cardPanel.add(configPanel, "configPanel");
        cardPanel.add(editBookPanel, "editPanel");

        cardLayout.show(cardPanel, "listaLivros");

        btnConfig = new JButton("Config");
        btnConfig.setBorderPainted(false);
        btnConfig.setFocusPainted(false);
        //btnConfig.setContentAreaFilled(false);
        bottomConfigPanel.add(btnConfig, BorderLayout.CENTER);

        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(cardPanel, BorderLayout.CENTER);
        mainPanel.add(livroDetailsPanel, BorderLayout.EAST);
        mainPanel.add(bottomConfigPanel, BorderLayout.SOUTH);

        bottomConfigPanel.setBackground(ColorPaletteEnum.AZULESCURO.getColor());

        btnConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (cardPanel.getComponent(0).isVisible()){
                    cardLayout.show(cardPanel, "configPanel");
                    btnConfig.setLabel("<- Voltar");
                }else {
                    cardLayout.show(cardPanel, "listaLivros");
                    configPanel.getLblMessage().setText("");
                    configPanel.getTxtTituloLivro().setText("");
                    configPanel.getTxtAutorLivro().setText("");
                    btnConfig.setLabel("Config");
                }
            }
        });

        setContentPane(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {

        new Janela();
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public JButton getBtnConfig() {
        return btnConfig;
    }

    public ListaLivrosPanel getListaLivrosPanel() {
        return listaLivrosPanel;
    }

    public EditBookPanel getEditBookPanel() {
        return editBookPanel;
    }

    public LivroDetailsPanel getLivroDetailsPanel() {
        return livroDetailsPanel;
    }

}
