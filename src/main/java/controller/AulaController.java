/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Aula;
import model.Turma;
import model.Professor;
import dao.AulaDAO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AulaController {
    private final AulaDAO aulaDAO = new AulaDAO();

    public List<Aula> listarTodasAulas() {
        return aulaDAO.listarTodos();
    }

    public Aula buscarAulaPorId(int id) {
        return aulaDAO.buscarPorId(id);
    }

    public String inserirAula(int id, Turma turma, LocalDate data, LocalTime horaInicio, LocalTime horaFim, Professor professor) {
        if (id <= 0) return "ID inválido!";
        if (turma == null) return "Selecione uma turma!";
        if (data == null) return "Data obrigatória!";
        if (horaInicio == null || horaFim == null) return "Horários obrigatórios!";
        if (horaFim.isBefore(horaInicio) || horaFim.equals(horaInicio))
            return "Hora de fim deve ser após a hora de início.";

        if (aulaDAO.buscarPorId(id) != null)
            return "Já existe uma aula com esse ID.";

        try {
            Aula aula = new Aula(id, turma, data, horaInicio, horaFim, professor);
            boolean ok = aulaDAO.inserir(aula);
            return ok ? "Aula cadastrada com sucesso!" : "Erro ao cadastrar aula.";
        } catch (IllegalArgumentException e) {
            return "Erro: " + e.getMessage();
        }
    }

    public String atualizarAula(int id, Turma turma, LocalDate data, LocalTime horaInicio, LocalTime horaFim, Professor professor) {
        if (id <= 0) return "ID inválido!";
        if (turma == null) return "Selecione uma turma!";
        if (data == null) return "Data obrigatória!";
        if (horaInicio == null || horaFim == null) return "Horários obrigatórios!";
        if (horaFim.isBefore(horaInicio) || horaFim.equals(horaInicio))
            return "Hora de fim deve ser após a hora de início.";

        Aula antiga = aulaDAO.buscarPorId(id);
        if (antiga == null)
            return "Aula não encontrada.";

        try {
            Aula nova = new Aula(id, turma, data, horaInicio, horaFim, professor);
            boolean ok = aulaDAO.atualizar(nova);
            return ok ? "Aula atualizada com sucesso!" : "Erro ao atualizar aula.";
        } catch (IllegalArgumentException e) {
            return "Erro: " + e.getMessage();
        }
    }

    // Atualiza apenas o professor da aula
    public String atualizarProfessor(int aulaId, Professor professor) {
        if (aulaDAO.buscarPorId(aulaId) == null)
            return "Aula não encontrada.";

        Integer matricula = (professor != null) ? professor.getMatricula() : null;
        boolean ok = aulaDAO.atualizarProfessor(aulaId, matricula);
        return ok ? "Professor atualizado com sucesso!" : "Erro ao atualizar professor da aula.";
    }

    // Atualiza apenas a data (só se não tiver professor)
    public String atualizarData(int aulaId, LocalDate novaData) {
        Aula aula = aulaDAO.buscarPorId(aulaId);
        if (aula == null) return "Aula não encontrada.";
        if (aula.getProfessor() != null) return "Só é permitido adiar aulas sem professor atribuído.";
        boolean ok = aulaDAO.atualizarData(aulaId, novaData);
        return ok ? "Data da aula adiada com sucesso!" : "Erro ao adiar aula.";
    }

    public String excluirAula(int id) {
        boolean ok = aulaDAO.excluir(id);
        return ok ? "Aula excluída com sucesso!" : "Erro ao excluir aula (ID não encontrado).";
    }
}
