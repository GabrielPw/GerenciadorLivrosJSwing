package service;

import model.Livro;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class LivroService {
    private EntityManager entityManager;

    public LivroService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Livro> obterTodos(){

        // Execute uma consulta JPQL para buscar todos os livros
        Query query = entityManager.createQuery("SELECT l FROM Livro l");
        List<Livro> livros = query.getResultList();

        return livros;
    }

    public void inserirLivro(Livro livro){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            entityManager.persist(livro);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public boolean deletarLivroPorId(Long livroId) {

        boolean deletouComSucesso = true;
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            // Encontre o livro pelo ID
            Livro livro = entityManager.find(Livro.class, livroId);

            if (livro != null) {
                // Remova o livro do banco de dados
                entityManager.remove(livro);
                transaction.commit();
            } else {
                // Lidar com o caso em que o livro com o ID fornecido não foi encontrado
                // Pode lançar uma exceção ou lidar com isso de acordo com a sua lógica de negócios
                System.out.println("Livro com ID " + livroId + " não encontrado.");
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            deletouComSucesso = false;
            e.printStackTrace();
            return deletouComSucesso;
        } finally {
            entityManager.close();
            return deletouComSucesso;
        }
    }

    public Long obterIdLivroPorTitulo(String titulo) {
        Query query = entityManager.createQuery("SELECT l.id FROM Livro l WHERE l.nome = :titulo");
        query.setParameter("titulo", titulo);

        try {
            return (Long) query.getSingleResult();
        } catch (NoResultException e) {
            // Lidar com o caso em que nenhum livro com o título fornecido foi encontrado
            // Pode lançar uma exceção ou lidar com isso de acordo com a sua lógica de negócios
            System.out.println("Nenhum livro com o título '" + titulo + "' foi encontrado.");
            return null;
        }
    }

    public boolean atualizarLivro(Long livroId, String novoTitulo, String novoAutor) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            // Encontre o livro pelo ID
            Livro livro = entityManager.find(Livro.class, livroId);

            if (livro != null) {
                // Atualize os campos do livro com os novos valores
                livro.setNome(novoTitulo);
                livro.setAutor(novoAutor);

                // Faça um merge para atualizar o livro no banco de dados
                entityManager.merge(livro);

                transaction.commit();
                return true; // Indica que a atualização foi bem-sucedida
            } else {
                // Lidar com o caso em que o livro com o ID fornecido não foi encontrado
                // Pode lançar uma exceção ou lidar com isso de acordo com a sua lógica de negócios
                System.out.println("Livro com ID " + livroId + " não encontrado.");
                return false; // Indica que a atualização falhou
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false; // Indica que a atualização falhou
        }
    }
}
