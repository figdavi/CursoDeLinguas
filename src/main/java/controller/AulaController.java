/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import dao.AulaDAO;
import dao.TurmaDAO;
import dao.ProfessorDAO;
import model.Aula;
import model.Turma;
import model.Professor;

/**
 *
 * @author figueiredodavi
 */
public class AulaController {
    private AulaDAO aulaDAO = new AulaDAO();
    private TurmaDAO turmaDAO = new TurmaDAO();
    private ProfessorDAO professorDAO = new ProfessorDAO();
    
    public List<Aula> listarTodasAulas() {
        return aulaDAO.listarTodos();
    }
    
    public void cadastrarAula(int id, 
            LocalDate data, 
            LocalTime horaInicio, 
            LocalTime horaFim,
            Turma turma, 
            Professor professor) {
        
        Aula aula = new Aula(id, turma, data, horaInicio, horaFim);
        if (professor != null) {
            aula.definirProfessor(professor);
        }

        aulaDAO.inserir(aula);
    }
    
        public void atualizarAula(int id, 
            LocalDate data, 
            LocalTime horaInicio, 
            LocalTime horaFim,
            Turma turma, 
            Professor professor) {
            
            Aula aula = new Aula(id, turma, data, horaInicio, horaFim);
            if (professor != null) {
                aula.definirProfessor(professor);
            }
            //Update?
            
            aulaDAO.excluir(id); // remove o antigo
            aulaDAO.inserir(aula); // insere atualizado
        }
}
