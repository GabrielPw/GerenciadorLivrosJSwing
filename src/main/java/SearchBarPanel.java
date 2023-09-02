import javax.swing.*;
import java.awt.*;

public class SearchBarPanel extends JPanel{

    private JButton btnSearch;
    private JTextField searchTextField;

    SearchBarPanel(){

        setLayout(new BorderLayout());
        btnSearch = new JButton("Buscar");
        btnSearch.setBorderPainted(false);
        btnSearch.setFocusPainted(false);
        searchTextField = new JTextField(70);

        add(searchTextField, BorderLayout.CENTER);
        add(btnSearch, BorderLayout.EAST);
    }
}