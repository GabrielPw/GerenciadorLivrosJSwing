
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Janela extends JFrame {

    Janela(){

        setTitle("Gerenciador de Livros - Java/JSwing.");
        setSize(new Dimension(600, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        JPanel mainPanel = new JPanel(new BorderLayout());
        LivroDetailsPanel livroDetailsPanel = new LivroDetailsPanel();
        livroDetailsPanel.setPreferredSize(new Dimension(200, 200));
        JPanel searchPanel = new SearchBarPanel();
        JPanel listaLivrosPanel = new ListaLivrosPanel(livroDetailsPanel);
        JPanel bottomConfigPanel = new JPanel();
        ConfigPanel configPanel = new ConfigPanel();

        cardPanel.add(listaLivrosPanel, "listaLivros");
        cardPanel.add(configPanel, "configPanel");

        cardLayout.show(cardPanel, "listaLivros");

        JButton btnConfig = new JButton("Config");
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
                }else{
                    cardLayout.show(cardPanel, "listaLivros");
                    btnConfig.setLabel("Config");
                }
            }
        });

        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        new Janela();
    }

}
