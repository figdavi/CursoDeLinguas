package controller;

import model.Turma;
import dao.TurmaDAO;
import java.time.LocalDate;
import java.util.List;

public class TurmaController {
    private TurmaDAO turmaDAO = new TurmaDAO();

    public List<Turma> listarTodasTurmas() {
        return turmaDAO.listarTodos();
    }
    
    public Turma buscarPorId(int matricula) {
        return turmaDAO.buscarPorId(matricula);
    }
    
    public void inserirTurma(
            int id,
            LocalDate dataInicio,
            LocalDate dataFim,
            String lingua,
            String nivel,
            double preco
    ) {
        Turma turma = new Turma(id, dataInicio, dataFim, lingua, nivel, preco);
        turmaDAO.inserir(turma);
    }

    public void atualizarTurma(
            int id,
            LocalDate dataInicio,
            LocalDate dataFim,
            String lingua,
            String nivel,
            double preco
    ) {
        Turma turma = new Turma(id, dataInicio, dataFim, lingua, nivel, preco);
        turmaDAO.atualizar(turma);
    }

    public void excluirTurma(int id) {
        turmaDAO.excluir(id);
    }
    
    public void registrarNota(int turmaId, int alunoMatricula, double nota) {
        turmaDAO.registrarNota(turmaId, alunoMatricula, nota);
    }
}