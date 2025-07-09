/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Funcionario;
import model.Gerente;
import model.Secretario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    // INSERIR
    public void inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (id, nome, endereco, telefone, salario, cargo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, funcionario.getId());
            pstmt.setString(2, funcionario.getNome());
            pstmt.setString(3, funcionario.getEndereco());
            pstmt.setString(4, funcionario.getTelefone());
            pstmt.setDouble(5, funcionario.getSalario());
            pstmt.setString(6, funcionario.getCargo().name());

            pstmt.executeUpdate();
            System.out.println("Funcionário inserido com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir funcionário: " + e.getMessage());
        }
    }

    // LISTAR TODOS
    public List<Funcionario> listarTodos() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                double salario = rs.getDouble("salario");
                String cargo = rs.getString("cargo");

                Funcionario f;

                if (cargo.equalsIgnoreCase("Gerente")) {
                    f = new Gerente(id, nome, endereco, telefone, salario);
                } else {
                    f = new Secretario(id, nome, endereco, telefone, salario);
                }

                funcionarios.add(f);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
        }

        return funcionarios;
    }

    // ATUALIZAR
    public void atualizar(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nome=?, endereco=?, telefone=?, salario=?, cargo=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, funcionario.getNome());
            pstmt.setString(2, funcionario.getEndereco());
            pstmt.setString(3, funcionario.getTelefone());
            pstmt.setDouble(4, funcionario.getSalario());
            pstmt.setString(5, funcionario.getCargo().toString());
            pstmt.setInt(6, funcionario.getId());

            int linhas = pstmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Funcionário atualizado com sucesso.");
            } else {
                System.out.println("Funcionário não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    // EXCLUIR
    public void excluir(int id) {
        String sql = "DELETE FROM funcionario WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int linhas = pstmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Funcionário excluído com sucesso.");
            } else {
                System.out.println("Funcionário não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir funcionário: " + e.getMessage());
        }
    }
}
