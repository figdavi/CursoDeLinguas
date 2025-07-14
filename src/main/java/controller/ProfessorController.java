/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Professor;
import dao.ProfessorDAO;
import java.util.List;

public class ProfessorController {
    private final ProfessorDAO professorDAO = new ProfessorDAO();

    public List<Professor> listarTodosProfessores() {
        return professorDAO.listarTodos();
    }

    public Professor buscarPorMatricula(int matricula) {
        return professorDAO.buscarPorMatricula(matricula);
    }

    public String inserirProfessor(
            int matricula,
            String nome,
            String endereco,
            String telefone,
            double valorHora,
            List<Professor.Lingua> linguas
    ) {
        if (matricula <= 0) return "Matrícula deve ser positiva.";
        if (nome == null || nome.trim().isEmpty()) return "Nome obrigatório.";
        if (valorHora < 0) return "Valor/hora deve ser positivo.";
        if (linguas == null || linguas.isEmpty()) return "Professor deve ter ao menos uma língua.";
        if (professorDAO.buscarPorMatricula(matricula) != null) return "Já existe professor com essa matrícula.";

        try {
            Professor professor = new Professor(matricula, nome, endereco, telefone, valorHora, linguas);
            boolean ok = professorDAO.inserir(professor);
            return ok ? "Professor cadastrado com sucesso!" : "Erro ao cadastrar professor.";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }

    public String atualizarProfessor(
            int matricula,
            String nome,
            String endereco,
            String telefone,
            double valorHora,
            List<Professor.Lingua> linguas
    ) {
        if (matricula <= 0) return "Matrícula deve ser positiva.";
        if (nome == null || nome.trim().isEmpty()) return "Nome obrigatório.";
        if (valorHora < 0) return "Valor/hora deve ser positivo.";
        if (linguas == null || linguas.isEmpty()) return "Professor deve ter ao menos uma língua.";
        if (professorDAO.buscarPorMatricula(matricula) == null) return "Professor não encontrado para atualização.";

        try {
            Professor professor = new Professor(matricula, nome, endereco, telefone, valorHora, linguas);
            boolean ok = professorDAO.atualizar(professor);
            return ok ? "Professor atualizado com sucesso!" : "Erro ao atualizar professor.";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }

    public String excluirProfessor(int matricula) {
        boolean ok = professorDAO.excluir(matricula);
        return ok ? "Professor excluído com sucesso!" : "Erro ao excluir professor (matrícula não encontrada).";
    }
}