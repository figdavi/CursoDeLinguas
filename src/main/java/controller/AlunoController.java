/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Aluno;
import dao.AlunoDAO;
import java.util.List;

/**
 *
 * @author figueiredodavi
 */
public class AlunoController {
    // Instância do DAO
    private AlunoDAO alunoDAO = new AlunoDAO();

    public List<Aluno> listarTodosAlunos() {
        return alunoDAO.listarTodos();
    }
    
    public void excluirAluno(int matricula) {
        alunoDAO.excluir(matricula);
    }
    
    public void inserirAluno(int matricula, String nome, String endereco, String telefone, String email) {
        Aluno aluno = new Aluno(matricula, nome, endereco, telefone, email);
        alunoDAO.inserir(aluno);
    }
    
    public void atualizarAluno (int matricula, String nome, String endereco, String telefone, String email) {
        Aluno aluno = new Aluno(matricula, nome, endereco, telefone, email);
        alunoDAO.atualizar(aluno);
    }

}
