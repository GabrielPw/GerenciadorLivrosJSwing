import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class ListaLivrosPanel extends JPanel {

    private List<String> titulosLivros = List.of("A Revolução dos Bichos", "Frankeinstein", "1984", "Crime e Castigo");
    private JList<String> listaLivroComponent;
    private LivroDetailsPanel livroDetailsPanel;
    ListaLivrosPanel(LivroDetailsPanel detailsPanel){

        setLayout(new BorderLayout());
        setBackground(ColorPaletteEnum.ROSA.getColor());
        DefaultListModel<String> listaLivrosModel = new DefaultListModel<>(); // Criar um modelo de lista e adicionar itens de exemplo

        for (String livro : titulosLivros){
            listaLivrosModel.addElement(livro);
        }

        listaLivroComponent = new JList<>(listaLivrosModel);
        add(listaLivroComponent, BorderLayout.CENTER);

        this.livroDetailsPanel = detailsPanel;

        listaLivroComponent.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String livroSelecionado = listaLivroComponent.getSelectedValue();
                livroDetailsPanel.setLblTituloLivro(livroSelecionado);
            }
        });
    }

    public JList<String> getListaLivroComponent() {
        return listaLivroComponent;
    }
}
