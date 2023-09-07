import model.Livro;
import service.LivroService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaLivrosPanel extends JPanel {

    private List<Livro> titulosLivros = new ArrayList<>();
    private DefaultListModel<String> listaLivrosModel;
    private JList<String> listaLivroComponent;
    private LivroDetailsPanel livroDetailsPanel;
    private LivroService livroService;
    ListaLivrosPanel(LivroDetailsPanel detailsPanel, LivroService livroService){

        this.livroService = livroService;
        setLayout(new BorderLayout());
        setBackground(ColorPaletteEnum.ROSA.getColor());

        listaLivrosModel = new DefaultListModel<>(); // Criar um modelo de lista e adicionar itens de exemplo
        titulosLivros.addAll(livroService.obterTodos());
        for(Livro livro: titulosLivros){
            listaLivrosModel.addElement(livro.getNome());
        }

        listaLivroComponent = new JList<>(listaLivrosModel);
        JScrollPane scrollPane = new JScrollPane(listaLivroComponent); // Adicione a lista a um JScrollPane
        add(scrollPane, BorderLayout.CENTER);

        this.livroDetailsPanel = detailsPanel;

        listaLivroComponent.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String livroSelecionado = listaLivroComponent.getSelectedValue();
                livroDetailsPanel.setLblTituloLivro(livroSelecionado);
                String autor = "";
                if (titulosLivros.stream().filter(livro -> livro.getNome().equals(livroSelecionado)).findFirst().isPresent()){
                    autor = titulosLivros.stream().filter(livro -> livro.getNome().equals(livroSelecionado)).findFirst().get().getAutor();
                }

                livroDetailsPanel.setLblAutorLivro("(" + autor + ")");
                livroDetailsPanel.setLblTituloBackground(ColorPaletteEnum.AZULESCURO.getColor());
                livroDetailsPanel.getBtnDeletar().setEnabled(true);
                livroDetailsPanel.getBtnEditar().setEnabled(true);
            }
        });
    }

    public JList<String> getListaLivroComponent() {
        return listaLivroComponent;
    }

    public DefaultListModel<String> getListaLivrosModel() {
        return listaLivrosModel;
    }

    public List<Livro> getTitulosLivros() {
        return titulosLivros;
    }

    public void setTitulosLivros(List<Livro> titulosLivros) {
        this.titulosLivros = titulosLivros;
    }

    private void addBooksSample(){
        titulosLivros.add(new Livro("A Revolução dos Bichos", "George Orwell"));
        titulosLivros.add(new Livro("Frankeinstein", "Mary Shelley"));
        titulosLivros.add(new Livro("1984", "George Orwell"));
        titulosLivros.add(new Livro("Crime e Castigo", "Fiodor Dostoyevisky"));

        for (Livro livro : titulosLivros ){
            listaLivrosModel.addElement(livro.getNome());
        }
    }

    public void atualizaListaConformePesquisa(String pesquisaDigitada){

        List<Livro> livrosFiltrados = new ArrayList<>();

        for (Livro livro : titulosLivros) {
            if (livro.getNome().toLowerCase().contains(pesquisaDigitada.toLowerCase())) {
                livrosFiltrados.add(livro); // Adicione o livro à lista de livros filtrados se o nome contiver a pesquisa
            }
        }

        // Atualize o modelo da lista para refletir os livros filtrados
        listaLivrosModel.clear();
        for (Livro livro : livrosFiltrados) {
            listaLivrosModel.addElement(livro.getNome());
        }
    }
}
