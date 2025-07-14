/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Professor;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProfessorDAO {

    public boolean inserir(Professor professor) {
        String sql = "INSERT INTO professor (matricula, nome, endereco, telefone, valorHora, linguas) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, professor.getMatricula());
            pstmt.setString(2, professor.getNome());
            pstmt.setString(3, professor.getEndereco());
            pstmt.setString(4, professor.getTelefone());
            pstmt.setDouble(5, professor.getValorHora());
            pstmt.setString(6, linguasToString(professor.getLinguas()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir professor: " + e.getMessage());
            return false;
        }
    }

    public List<Professor> listarTodos() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professor";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                List<Professor.Lingua> linguas = stringToLinguas(rs.getString("linguas"));
                Professor p = new Professor(
                        rs.getInt("matricula"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getDouble("valorHora"),
                        linguas
                );
                professores.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar professores: " + e.getMessage());
        }
        return professores;
    }

    public Professor buscarPorMatricula(int matricula) {
        String sql = "SELECT * FROM professor WHERE matricula = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, matricula);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                List<Professor.Lingua> linguas = stringToLinguas(rs.getString("linguas"));
                return new Professor(
                        rs.getInt("matricula"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getDouble("valorHora"),
                        linguas
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar professor: " + e.getMessage());
        }
        return null;
    }

    public boolean atualizar(Professor professor) {
        String sql = "UPDATE professor SET nome=?, endereco=?, telefone=?, valorHora=?, linguas=? WHERE matricula=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, professor.getNome());
            pstmt.setString(2, professor.getEndereco());
            pstmt.setString(3, professor.getTelefone());
            pstmt.setDouble(4, professor.getValorHora());
            pstmt.setString(5, linguasToString(professor.getLinguas()));
            pstmt.setInt(6, professor.getMatricula());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar professor: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int matricula) {
        String sql = "DELETE FROM professor WHERE matricula=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, matricula);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir professor: " + e.getMessage());
            return false;
        }
    }

    private String linguasToString(List<Professor.Lingua> linguas) {
        return linguas.stream().map(Enum::name).collect(Collectors.joining(","));
    }

    private List<Professor.Lingua> stringToLinguas(String s) {
        if (s == null || s.isBlank()) return new ArrayList<>();
        return Arrays.stream(s.split(","))
                .map(String::trim)
                .map(Professor.Lingua::valueOf)
                .collect(Collectors.toList());
    }
}