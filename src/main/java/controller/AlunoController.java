/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Aluno;
import dao.AlunoDAO;
import java.util.List;

public class AlunoController {
    private AlunoDAO alunoDAO = new AlunoDAO();

    public List<Aluno> listarTodosAlunos() {
        return alunoDAO.listarTodos();
    }

    public String inserirAluno(int matricula, String nome, String endereco, String telefone, String email) {
        if (matricula <= 0) return "Matrícula deve ser positiva.";
        if (nome == null || nome.trim().isEmpty()) return "Nome obrigatório.";
        if (endereco == null || endereco.trim().isEmpty()) return "Endereço obrigatório.";
        if (telefone == null || telefone.trim().isEmpty()) return "Telefone obrigatório.";
        if (email == null || email.trim().isEmpty()) return "Email obrigatório.";

        if (alunoDAO.buscarPorMatricula(matricula) != null)
            return "Já existe um aluno com essa matrícula.";

        Aluno aluno = new Aluno(matricula, nome, endereco, telefone, email);
        boolean sucesso = alunoDAO.inserir(aluno);
        return sucesso ? "Aluno cadastrado com sucesso!" : "Erro ao cadastrar aluno.";
    }

    public String atualizarAluno(int matricula, String nome, String endereco, String telefone, String email) {
        if (matricula <= 0) return "Matrícula deve ser positiva.";
        if (nome == null || nome.trim().isEmpty()) return "Nome obrigatório.";
        if (endereco == null || endereco.trim().isEmpty()) return "Endereço obrigatório.";
        if (telefone == null || telefone.trim().isEmpty()) return "Telefone obrigatório.";
        if (email == null || email.trim().isEmpty()) return "Email obrigatório.";

        if (alunoDAO.buscarPorMatricula(matricula) == null)
            return "Aluno não encontrado para atualização.";

        Aluno aluno = new Aluno(matricula, nome, endereco, telefone, email);
        boolean sucesso = alunoDAO.atualizar(aluno);
        return sucesso ? "Aluno atualizado com sucesso!" : "Erro ao atualizar aluno.";
    }

    public String excluirAluno(int matricula) {
        boolean sucesso = alunoDAO.excluir(matricula);
        return sucesso ? "Aluno excluído com sucesso!" : "Erro ao excluir aluno (matrícula não encontrada).";
    }
}
