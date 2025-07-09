/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Aluno;
import model.Turma;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class TurmaDAO {

    private final AlunoDAO alunoDAO = new AlunoDAO();

    public void inserir(Turma turma) {
        String sql = "INSERT INTO turma (id, dataInicio, dataFim, lingua, nivel, preco) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, turma.getId());
            pstmt.setString(2, turma.getDataInicio().toString());
            pstmt.setString(3, turma.getDataFim().toString());
            pstmt.setString(4, turma.getLingua());
            pstmt.setString(5, turma.getNivel());
            pstmt.setDouble(6, turma.getPreco());
            pstmt.executeUpdate();

            for (Aluno aluno : turma.getAlunosMatriculados()) {
                matricularAluno(turma.getId(), aluno.getMatricula());
            }

            for (Map.Entry<Aluno, Double> entrada : turma.getNotasFinais().entrySet()) {
                registrarNota(turma.getId(), entrada.getKey().getMatricula(), entrada.getValue());
            }

            System.out.println("Turma inserida com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir turma: " + e.getMessage());
        }
    }

    public Turma buscarPorId(int id) {
        String sql = "SELECT * FROM turma WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                LocalDate dataInicio = LocalDate.parse(rs.getString("dataInicio"));
                LocalDate dataFim = LocalDate.parse(rs.getString("dataFim"));
                String lingua = rs.getString("lingua");
                String nivel = rs.getString("nivel");
                double preco = rs.getDouble("preco");

                Turma turma = new Turma(id, dataInicio, dataFim, lingua, nivel, preco);
                turma.getAlunosMatriculados().addAll(buscarAlunosDaTurma(id));
                turma.getNotasFinais().putAll(buscarNotasFinais(id));
                return turma;
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
                int id = rs.getInt("id");
                LocalDate dataInicio = LocalDate.parse(rs.getString("dataInicio"));
                LocalDate dataFim = LocalDate.parse(rs.getString("dataFim"));
                String lingua = rs.getString("lingua");
                String nivel = rs.getString("nivel");
                double preco = rs.getDouble("preco");

                Turma turma = new Turma(id, dataInicio, dataFim, lingua, nivel, preco);
                turma.getAlunosMatriculados().addAll(buscarAlunosDaTurma(id));
                turma.getNotasFinais().putAll(buscarNotasFinais(id));
                turmas.add(turma);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar turmas: " + e.getMessage());
        }

        return turmas;
    }

    private List<Aluno> buscarAlunosDaTurma(int turmaId) {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT aluno_matricula FROM turma_aluno WHERE turma_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, turmaId);
            ResultSet rs = pstmt.executeQuery();

            Set<Integer> matriculas = new HashSet<>();
            while (rs.next()) {
                matriculas.add(rs.getInt("aluno_matricula"));
            }

            for (Aluno aluno : alunoDAO.listarTodos()) {
                if (matriculas.contains(aluno.getMatricula())) {
                    alunos.add(aluno);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar alunos da turma: " + e.getMessage());
        }

        return alunos;
    }

    private Map<Aluno, Double> buscarNotasFinais(int turmaId) {
        Map<Aluno, Double> notas = new HashMap<>();
        String sql = "SELECT aluno_matricula, nota FROM nota_final WHERE turma_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, turmaId);
            ResultSet rs = pstmt.executeQuery();

            Map<Integer, Double> mapNotas = new HashMap<>();
            while (rs.next()) {
                mapNotas.put(rs.getInt("aluno_matricula"), rs.getDouble("nota"));
            }

            for (Aluno aluno : alunoDAO.listarTodos()) {
                if (mapNotas.containsKey(aluno.getMatricula())) {
                    notas.put(aluno, mapNotas.get(aluno.getMatricula()));
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar notas finais: " + e.getMessage());
        }

        return notas;
    }

    public void matricularAluno(int turmaId, int alunoMatricula) {
        String sql = "INSERT INTO turma_aluno (turma_id, aluno_matricula) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, turmaId);
            pstmt.setInt(2, alunoMatricula);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao matricular aluno: " + e.getMessage());
        }
    }

    public void registrarNota(int turmaId, int alunoMatricula, double nota) {
        String sql = "INSERT OR REPLACE INTO nota_final (turma_id, aluno_matricula, nota) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, turmaId);
            pstmt.setInt(2, alunoMatricula);
            pstmt.setDouble(3, nota);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao registrar nota: " + e.getMessage());
        }
    }

    public void atualizar(Turma turma) {
        String sql = "UPDATE turma SET dataInicio=?, dataFim=?, lingua=?, nivel=?, preco=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, turma.getDataInicio().toString());
            pstmt.setString(2, turma.getDataFim().toString());
            pstmt.setString(3, turma.getLingua());
            pstmt.setString(4, turma.getNivel());
            pstmt.setDouble(5, turma.getPreco());
            pstmt.setInt(6, turma.getId());

            pstmt.executeUpdate();
            System.out.println("Turma atualizada.");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar turma: " + e.getMessage());
        }
    }

    public void excluir(int turmaId) {
        String[] tabelasRelacionadas = { "nota_final", "turma_aluno", "turma" };

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            for (String tabela : tabelasRelacionadas) {
                stmt.executeUpdate("DELETE FROM " + tabela + " WHERE turma_id = " + turmaId);
            }

            System.out.println("Turma e dados associados excluídos.");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir turma: " + e.getMessage());
        }
    }
}
