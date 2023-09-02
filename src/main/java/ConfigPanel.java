import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConfigPanel extends JPanel {

    private JLabel lblTituloDoPainel, lblNovoLivro;
    private JTextField txtTituloLivro;
    private JButton btnAdicionarNovoLivro;
    ConfigPanel(){

        setLayout(new BorderLayout());

        JPanel newBookPanel = new JPanel(new GridBagLayout());
        //JLabel lblNovoLivro = new JLabel("Add. Novo livro");
        btnAdicionarNovoLivro = new JButton("+ Adicionar novo livro");
        btnAdicionarNovoLivro.setFocusPainted(false);
        lblTituloDoPainel = new JLabel("Configurações");
        lblTituloDoPainel.setBorder(new EmptyBorder(10, 0, 15, 0));
        lblNovoLivro = new JLabel("Titulo");
        txtTituloLivro = new JTextField();
        txtTituloLivro.setPreferredSize(new Dimension(170, txtTituloLivro.getPreferredSize().height));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;

        //newBookPanel.add(lblNovoLivro);
        newBookPanel.add(lblTituloDoPainel, constraints);
        constraints.gridy++;
        newBookPanel.add(lblNovoLivro, constraints);
        constraints.gridx++;

        newBookPanel.add(txtTituloLivro, constraints);
        constraints.gridy++;
        constraints.gridx--;
        newBookPanel.add(btnAdicionarNovoLivro, constraints);
        add(newBookPanel, BorderLayout.NORTH);
    }
}
