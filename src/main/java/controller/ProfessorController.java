/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Professor;
import dao.ProfessorDAO;
import java.util.List;

/**
 *
 * @author figueiredodavi
 */
public class ProfessorController {
    // Instância do DAO
    private ProfessorDAO professorDAO = new ProfessorDAO();
    
    public List<Professor> listarTodosProfessores() {
        return professorDAO.listarTodos();
    }
    
    public Professor buscarPorMatricula(int matricula) {
        return professorDAO.buscarPorMatricula(matricula);
    }
    
    public void inserirProfessor(int matricula, 
            String nome, 
            String endereco, 
            String telefone, 
            double valorHora, 
            List<String> linguas
    ) {
        Professor professor = new Professor(matricula, nome, endereco, telefone, valorHora, linguas);
        professorDAO.inserir(professor);
    }
    
    public void atualizarProfessor(int matricula,
            String nome,
            String endereco,
            String telefone,
            double valorHora,
            List<String> linguas
    ) {
        Professor professor = new Professor(matricula, nome, endereco, telefone, valorHora, linguas);
        professorDAO.atualizar(professor);
    }
    
    public void excluirProfessor(int matricula) {
        professorDAO.excluir(matricula);
    }
}
