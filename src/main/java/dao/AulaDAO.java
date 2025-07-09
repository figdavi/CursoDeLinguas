/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Aula;
import model.Professor;
import model.Turma;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AulaDAO {

    private ProfessorDAO professorDAO = new ProfessorDAO();
    private TurmaDAO turmaDAO = new TurmaDAO();

    public void inserir(Aula aula) {
        String sql = "INSERT INTO aula (id, turma_id, data, horaInicio, horaFim, professor_matricula) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, aula.getId());
            pstmt.setInt(2, aula.getTurma().getId());
            pstmt.setString(3, aula.getData().toString());
            pstmt.setString(4, aula.getHoraInicio().toString());
            pstmt.setString(5, aula.getHoraFim().toString());

            if (aula.getProfessor() != null) {
                pstmt.setInt(6, aula.getProfessor().getMatricula());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }

            pstmt.executeUpdate();
            System.out.println("Aula inserida com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir aula: " + e.getMessage());
        }
    }

    public List<Aula> listarTodos() {
        List<Aula> aulas = new ArrayList<>();
        String sql = "SELECT * FROM aula";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int turmaId = rs.getInt("turma_id");
                LocalDate data = LocalDate.parse(rs.getString("data"));
                LocalTime inicio = LocalTime.parse(rs.getString("horaInicio"));
                LocalTime fim = LocalTime.parse(rs.getString("horaFim"));

                int profMatricula = rs.getInt("professor_matricula");
                Professor professor = null;
                if (!rs.wasNull()) {
                    professor = professorDAO.buscarPorMatricula(profMatricula);
                }

                Turma turma = turmaDAO.buscarPorId(turmaId);
                Aula aula = new Aula(id, turma, data, inicio, fim);
                if (professor != null) {
                    aula.definirProfessor(professor);
                }
                aulas.add(aula);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar aulas: " + e.getMessage());
        }

        return aulas;
    }

    public void atualizarProfessor(int aulaId, Integer professorMatricula) {
        String sql = "UPDATE aula SET professor_matricula = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (professorMatricula != null) {
                pstmt.setInt(1, professorMatricula);
            } else {
                pstmt.setNull(1, Types.INTEGER);
            }

            pstmt.setInt(2, aulaId);
            pstmt.executeUpdate();
            System.out.println("Professor atualizado para a aula " + aulaId);

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar professor: " + e.getMessage());
        }
    }

    public void adiarData(int aulaId, LocalDate novaData) {
        String sql = "UPDATE aula SET data = ? WHERE id = ? AND professor_matricula IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, novaData.toString());
            pstmt.setInt(2, aulaId);

            int linhas = pstmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Data da aula adiada com sucesso.");
            } else {
                System.out.println("Não foi possível adiar (a aula já tem professor?).");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao adiar data: " + e.getMessage());
        }
    }

    public void excluir(int aulaId) {
        String sql = "DELETE FROM aula WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, aulaId);
            pstmt.executeUpdate();
            System.out.println("Aula excluída.");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir aula: " + e.getMessage());
        }
    }
}