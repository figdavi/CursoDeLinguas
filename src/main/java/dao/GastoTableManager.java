/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class GastoTableManager {

    public static void criarTabelaGasto() {
        String sql = """
            CREATE TABLE IF NOT EXISTS gasto (
                id INTEGER PRIMARY KEY,
                descricao TEXT NOT NULL,
                valor REAL NOT NULL,
                data TEXT NOT NULL
            );
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela 'gasto' criada/verificada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela gasto: " + e.getMessage());
        }
    }
}
