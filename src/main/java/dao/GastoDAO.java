/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Gasto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GastoDAO {

    // INSERIR
    public void inserir(Gasto gasto) {
        String sql = "INSERT INTO gasto (id, descricao, valor, data) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, gasto.getId());
            pstmt.setString(2, gasto.getDescricao());
            pstmt.setDouble(3, gasto.getValor());
            pstmt.setString(4, gasto.getData().toString());

            pstmt.executeUpdate();
            System.out.println("Gasto inserido com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir gasto: " + e.getMessage());
        }
    }

    // LISTAR TODOS
    public List<Gasto> listarTodos() {
        List<Gasto> gastos = new ArrayList<>();
        String sql = "SELECT * FROM gasto";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Gasto g = new Gasto(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        LocalDate.parse(rs.getString("data"))
                );
                gastos.add(g);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar gastos: " + e.getMessage());
        }

        return gastos;
    }

    // ATUALIZAR
    public void atualizar(Gasto gasto) {
        String sql = "UPDATE gasto SET descricao=?, valor=?, data=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, gasto.getDescricao());
            pstmt.setDouble(2, gasto.getValor());
            pstmt.setString(3, gasto.getData().toString());
            pstmt.setInt(4, gasto.getId());

            int linhas = pstmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("Gasto atualizado com sucesso.");
            } else {
                System.out.println("Gasto não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar gasto: " + e.getMessage());
        }
    }

    // EXCLUIR
    public void excluir(int id) {
        String sql = "DELETE FROM gasto WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int linhas = pstmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Gasto excluído.");
            } else {
                System.out.println("Gasto não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir gasto: " + e.getMessage());
        }
    }
}