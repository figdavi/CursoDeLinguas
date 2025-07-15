/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Lingua;
import model.Turma;
import model.Aluno;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class TurmaDAO {

    // --- CRUD BÁSICO TURMA ---------------------------------------------------
    public boolean inserir(Turma turma) {
        String sql = "INSERT INTO turma (id, dataInicio, dataFim, lingua, nivel, preco) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, turma.getId());
            pstmt.setString(2, turma.getDataInicio().toString());
            pstmt.setString(3, turma.getDataFim().toString());
            pstmt.setString(4, turma.getLingua().name());
            pstmt.setString(5, turma.getNivel().name());
            pstmt.setDouble(6, turma.getPreco());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir turma: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizar(Turma turma) {
        String sql = "UPDATE turma SET dataInicio=?, dataFim=?, lingua=?, nivel=?, preco=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, turma.getDataInicio().toString());
            pstmt.setString(2, turma.getDataFim().toString());
            pstmt.setString(3, turma.getLingua().name());
            pstmt.setString(4, turma.getNivel().name());
            pstmt.setDouble(5, turma.getPreco());
            pstmt.setInt(6, turma.getId());
            int linhas = pstmt.executeUpdate();
            return linhas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar turma: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM turma WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int linhas = pstmt.executeUpdate();
            return linhas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir turma: " + e.getMessage());
            return false;
        }
    }

    public Turma buscarPorId(int id) {
        String sql = "SELECT * FROM turma WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Turma(
                    rs.getInt("id"),
                    LocalDate.parse(rs.getString("dataInicio")),
                    LocalDate.parse(rs.getString("dataFim")),
                    Lingua.valueOf(rs.getString("lingua")),
                    Turma.Nivel.valueOf(rs.getString("nivel")),
                    rs.getDouble("preco")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar turma: " + e.getMessage());
        }
        return null;
    }

    public List<Turma> listarTodos() {
        List<Turma> turmas = new ArrayList<>();
        String sql = "SELECT * FROM turma";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                turmas.add(new Turma(
                    rs.getInt("id"),
                    LocalDate.parse(rs.getString("dataInicio")),
                    LocalDate.parse(rs.getString("dataFim")),
                    Lingua.valueOf(rs.getString("lingua")),
                    Turma.Nivel.valueOf(rs.getString("nivel")),
                    rs.getDouble("preco")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar turmas: " + e.getMessage());
        }
        return turmas;
    }

    // --- RELACIONAMENTO: MATRÍCULA DE ALUNO ----------------------------------

    // Matricular aluno na turma
    public boolean matricularAluno(int turmaId, int alunoMatricula) {
        String sql = "INSERT INTO turma_aluno (turma_id, aluno_matricula) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, turmaId);
            pstmt.setInt(2, alunoMatricula);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao matricular aluno na turma: " + e.getMessage());
            return false;
        }
    }

    // Remover matrícula do aluno
    public boolean desmatricularAluno(int turmaId, int alunoMatricula) {
        String sql = "DELETE FROM turma_aluno WHERE turma_id = ? AND aluno_matricula = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, turmaId);
            pstmt.setInt(2, alunoMatricula);
            int linhas = pstmt.executeUpdate();
            return linhas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao desmatricular aluno da turma: " + e.getMessage());
            return false;
        }
    }

    // Listar todos os alunos matriculados em uma turma
    public List<Aluno> listarAlunosMatriculados(int turmaId) {
        List<Aluno> alunos = new ArrayList<>();
        String sql = """
            SELECT a.matricula, a.nome, a.endereco, a.telefone, a.email
            FROM aluno a
            INNER JOIN turma_aluno ta ON a.matricula = ta.aluno_matricula
            WHERE ta.turma_id = ?
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, turmaId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                alunos.add(new Aluno(
                    rs.getInt("matricula"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos da turma: " + e.getMessage());
        }
        return alunos;
    }

    // --- RELACIONAMENTO: NOTAS FINAIS ----------------------------------------

    // Registrar ou atualizar nota final do aluno na turma
    public boolean registrarNotaFinal(int turmaId, int alunoMatricula, double nota) {
        String sql = """
            INSERT INTO nota_final (turma_id, aluno_matricula, nota)
            VALUES (?, ?, ?)
            ON CONFLICT(turma_id, aluno_matricula) DO UPDATE SET nota = excluded.nota
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, turmaId);
            pstmt.setInt(2, alunoMatricula);
            pstmt.setDouble(3, nota);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao registrar nota final: " + e.getMessage());
            return false;
        }
    }

    // Buscar nota final de um aluno em uma turma
    public Double buscarNotaFinal(int turmaId, int alunoMatricula) {
        String sql = "SELECT nota FROM nota_final WHERE turma_id = ? AND aluno_matricula = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, turmaId);
            pstmt.setInt(2, alunoMatricula);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("nota");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar nota final: " + e.getMessage());
        }
        return null;
    }

    // Listar todas as notas finais da turma (Map<Aluno, Nota>)
    public Map<Aluno, Double> listarNotasFinais(int turmaId) {
        Map<Aluno, Double> notas = new LinkedHashMap<>();
        String sql = """
            SELECT a.matricula, a.nome, a.endereco, a.telefone, a.email, nf.nota
            FROM nota_final nf
            INNER JOIN aluno a ON nf.aluno_matricula = a.matricula
            WHERE nf.turma_id = ?
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, turmaId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Aluno aluno = new Aluno(
                    rs.getInt("matricula"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
                Double nota = rs.getDouble("nota");
                notas.put(aluno, nota);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar notas finais: " + e.getMessage());
        }
        return notas;
    }
}