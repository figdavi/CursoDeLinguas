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

    public boolean inserir(Aula aula) {
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
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir aula: " + e.getMessage());
            return false;
        }
    }

    // ATUALIZAR (com professor)
    public boolean atualizar(Aula aula) {
        String sql = "UPDATE aula SET turma_id=?, data=?, horaInicio=?, horaFim=?, professor_matricula=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, aula.getTurma().getId());
            pstmt.setString(2, aula.getData().toString());
            pstmt.setString(3, aula.getHoraInicio().toString());
            pstmt.setString(4, aula.getHoraFim().toString());
            if (aula.getProfessor() != null) {
                pstmt.setInt(5, aula.getProfessor().getMatricula());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }
            pstmt.setInt(6, aula.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aula: " + e.getMessage());
            return false;
        }
    }

    // ATUALIZAR APENAS PROFESSOR
    public boolean atualizarProfessor(int aulaId, Integer professorMatricula) {
        String sql = "UPDATE aula SET professor_matricula=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (professorMatricula != null) {
                pstmt.setInt(1, professorMatricula);
            } else {
                pstmt.setNull(1, Types.INTEGER);
            }
            pstmt.setInt(2, aulaId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar professor da aula: " + e.getMessage());
            return false;
        }
    }

    // ATUALIZAR APENAS DATA
    public boolean atualizarData(int aulaId, LocalDate novaData) {
        String sql = "UPDATE aula SET data=? WHERE id=? AND professor_matricula IS NULL";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, novaData.toString());
            pstmt.setInt(2, aulaId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao adiar data da aula: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int aulaId) {
        String sql = "DELETE FROM aula WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, aulaId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir aula: " + e.getMessage());
            return false;
        }
    }

    public Aula buscarPorId(int id) {
        String sql = "SELECT * FROM aula WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
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
                return new Aula(id, turma, data, inicio, fim, professor);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aula: " + e.getMessage());
        }
        return null;
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
                aulas.add(new Aula(id, turma, data, inicio, fim, professor));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar aulas: " + e.getMessage());
        }
        return aulas;
    }
    
    public List<Aula> listarAulasDoProfessorNoDia(int professorMatricula, LocalDate data) {
        List<Aula> aulas = new ArrayList<>();
        String sql = "SELECT * FROM aula WHERE professor_matricula = ? AND data = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, professorMatricula);
            pstmt.setString(2, data.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Aula a = new Aula(
                    rs.getInt("id"),
                    // ajuste os campos conforme seu construtor
                    null, // turma
                    LocalDate.parse(rs.getString("data")),
                    LocalTime.parse(rs.getString("horaInicio")),
                    LocalTime.parse(rs.getString("horaFim")),
                    null // professor (ou carregue, se necessário)
                );
                aulas.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aulas do professor: " + e.getMessage());
        }
        return aulas;
    }

}
