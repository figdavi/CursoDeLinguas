/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Turma;
import model.Aluno;
import dao.TurmaDAO;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TurmaController {
    private final TurmaDAO turmaDAO = new TurmaDAO();

    public List<Turma> listarTodasTurmas() {
        return turmaDAO.listarTodos();
    }

    public Turma buscarPorId(int id) {
        return turmaDAO.buscarPorId(id);
    }

    public String inserirTurma(int id, LocalDate dataInicio, LocalDate dataFim, Turma.Lingua lingua, Turma.Nivel nivel, double preco) {
        if (id <= 0) return "ID deve ser positivo.";
        if (dataInicio == null) return "Data de início obrigatória.";
        if (dataFim == null) return "Data de fim obrigatória.";
        if (lingua == null) return "Língua obrigatória.";
        if (nivel == null) return "Nível obrigatório.";
        if (preco < 0) return "Preço não pode ser negativo.";

        if (turmaDAO.buscarPorId(id) != null)
            return "Já existe uma turma com esse ID.";

        Turma turma = new Turma(id, dataInicio, dataFim, lingua, nivel, preco);
        boolean ok = turmaDAO.inserir(turma);
        return ok ? "Turma cadastrada com sucesso!" : "Erro ao cadastrar turma.";
    }

    public String atualizarTurma(int id, LocalDate dataInicio, LocalDate dataFim, Turma.Lingua lingua, Turma.Nivel nivel, double preco) {
        if (id <= 0) return "ID deve ser positivo.";
        if (dataInicio == null) return "Data de início obrigatória.";
        if (dataFim == null) return "Data de fim obrigatória.";
        if (lingua == null) return "Língua obrigatória.";
        if (nivel == null) return "Nível obrigatório.";
        if (preco < 0) return "Preço não pode ser negativo.";

        if (turmaDAO.buscarPorId(id) == null)
            return "Turma não encontrada para atualização.";

        Turma turma = new Turma(id, dataInicio, dataFim, lingua, nivel, preco);
        boolean ok = turmaDAO.atualizar(turma);
        return ok ? "Turma atualizada com sucesso!" : "Erro ao atualizar turma.";
    }

    public String excluirTurma(int id) {
        boolean ok = turmaDAO.excluir(id);
        return ok ? "Turma excluída com sucesso!" : "Erro ao excluir turma (ID não encontrado)";
    }

    // --- MATRÍCULA E DESMATRÍCULA DE ALUNOS ---
    public String matricularAluno(int turmaId, int alunoMatricula) {
        if (turmaId <= 0 || alunoMatricula <= 0) return "Turma e matrícula válidas são obrigatórias!";
        boolean ok = turmaDAO.matricularAluno(turmaId, alunoMatricula);
        return ok ? "Aluno matriculado na turma!" : "Erro ao matricular aluno (já matriculado?)";
    }

    public String desmatricularAluno(int turmaId, int alunoMatricula) {
        if (turmaId <= 0 || alunoMatricula <= 0) return "Turma e matrícula válidas são obrigatórias!";
        boolean ok = turmaDAO.desmatricularAluno(turmaId, alunoMatricula);
        return ok ? "Aluno removido da turma!" : "Erro ao remover aluno (não matriculado?)";
    }

    // --- LISTAR ALUNOS MATRICULADOS ---
    public List<Aluno> listarAlunosMatriculados(int turmaId) {
        if (turmaId <= 0) return List.of();
        return turmaDAO.listarAlunosMatriculados(turmaId);
    }

    // --- NOTAS FINAIS ---
    public String registrarNota(int turmaId, int alunoMatricula, double nota) {
        if (turmaId <= 0 || alunoMatricula <= 0) return "Turma e matrícula válidas são obrigatórias!";
        if (nota < 0 || nota > 10) return "Nota deve ser entre 0 e 10.";
        boolean ok = turmaDAO.registrarNotaFinal(turmaId, alunoMatricula, nota);
        return ok ? "Nota registrada com sucesso!" : "Erro ao registrar nota.";
    }

    public Double buscarNotaFinal(int turmaId, int alunoMatricula) {
        if (turmaId <= 0 || alunoMatricula <= 0) return null;
        return turmaDAO.buscarNotaFinal(turmaId, alunoMatricula);
    }

    public Map<Aluno, Double> listarNotasFinais(int turmaId) {
        if (turmaId <= 0) return Map.of();
        return turmaDAO.listarNotasFinais(turmaId);
    }
}
