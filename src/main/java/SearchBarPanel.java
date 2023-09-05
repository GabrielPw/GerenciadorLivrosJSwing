import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SearchBarPanel extends JPanel{

    private JButton btnSearch;
    private PlaceholderTextField searchTextField;

    SearchBarPanel(){

        setLayout(new BorderLayout());
        btnSearch = new JButton("Buscar");
        btnSearch.setBorderPainted(false);
        btnSearch.setFocusPainted(false);
        searchTextField = new PlaceholderTextField(70);
        //searchTextField.setBorder(new LineBorder(Color.black, 2));
        searchTextField.setPlaceholder("Livros, Autores");

        add(searchTextField, BorderLayout.CENTER);
        add(btnSearch, BorderLayout.EAST);
    }
}