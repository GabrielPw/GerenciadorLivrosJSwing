import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LivroDetailsPanel extends JPanel{

    private JLabel lblTituloLivro;
    private JLabel lblAutorLivro;
    private JLabel lblPrecoLivro;
    private JButton btnEditar;
    private JButton btnDeletar;

    public LivroDetailsPanel() {

        setLayout(new BorderLayout());
        setBackground(ColorPaletteEnum.AZUL.getColor());

        JPanel painelInfoLivro = new JPanel(new GridBagLayout());
        JPanel painelBotoesDeleteUpdate = new JPanel();

        painelInfoLivro.setBackground(ColorPaletteEnum.AZUL.getColor());
        painelBotoesDeleteUpdate.setBackground(ColorPaletteEnum.AZUL.getColor());

        lblTituloLivro = new JLabel("Info. livro");
        lblTituloLivro.setForeground(Color.WHITE);
        lblTituloLivro.setBackground(ColorPaletteEnum.AZULESCURO.getColor());
        lblTituloLivro.setOpaque(true);
        btnDeletar = new JButton("Deletar");
        btnEditar = new JButton("Editar");

        btnDeletar.setBorderPainted(false); btnDeletar.setFocusPainted(false);
        btnEditar.setBorderPainted(false); btnEditar.setFocusPainted(false);

        // Config Componentes
        lblTituloLivro.setPreferredSize(new Dimension(200, 50));
        lblTituloLivro.setBorder(new EmptyBorder(20, 30, 20, 30));
        // ----

        painelInfoLivro.add(lblTituloLivro);
        painelBotoesDeleteUpdate.add(btnEditar);
        painelBotoesDeleteUpdate.add(btnDeletar);

        add(painelInfoLivro, BorderLayout.NORTH);
        add(painelBotoesDeleteUpdate, BorderLayout.SOUTH);
    }

    public void setLblTituloLivro(String tituloLivro) {
        this.lblTituloLivro.setText(tituloLivro);
    }

}
