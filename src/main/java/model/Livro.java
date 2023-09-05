package model;

import javax.persistence.*;

@Table(name = "livro")
@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo")
    private String nome;
    @Column(name = "autor")

    private String autor;
    @Column(name = "preco")

    private Double preco;
    public Livro(){}

    public Livro(String nome, String autor) {
        this.nome = nome;
        this.autor = autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
