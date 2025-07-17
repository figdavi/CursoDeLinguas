/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Aluno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    
    public boolean inserir(Aluno aluno) {
        String sql = "INSERT INTO aluno (matricula, nome, endereco, telefone, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, aluno.getMatricula());
            pstmt.setString(2, aluno.getNome());
            pstmt.setString(3, aluno.getEndereco());
            pstmt.setString(4, aluno.getTelefone());
            pstmt.setString(5, aluno.getEmail());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir aluno: " + e.getMessage());
            return false;
        }
    }

    public List<Aluno> listarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Aluno a = new Aluno(
                    rs.getInt("matricula"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
                alunos.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        }
        return alunos;
    }

    public Aluno buscarPorMatricula(int matricula) {
        String sql = "SELECT * FROM aluno WHERE matricula=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, matricula);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Aluno(
                        rs.getInt("matricula"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
        }
        return null;
    }

    public boolean atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET nome=?, endereco=?, telefone=?, email=? WHERE matricula=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2, aluno.getEndereco());
            pstmt.setString(3, aluno.getTelefone());
            pstmt.setString(4, aluno.getEmail());
            pstmt.setInt(5, aluno.getMatricula());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int matricula) {
        String sql = "DELETE FROM aluno WHERE matricula=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, matricula);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir aluno: " + e.getMessage());
            return false;
        }
    }
}
