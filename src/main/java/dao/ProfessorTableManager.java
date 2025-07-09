/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfessorTableManager {

    public static void criarTabelaProfessor() {
        String sql = """
            CREATE TABLE IF NOT EXISTS professor (
                matricula INTEGER PRIMARY KEY,
                nome TEXT NOT NULL,
                endereco TEXT,
                telefone TEXT,
                valorHora REAL,
                linguas TEXT
            );
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela 'professor' verificada/criada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar/verificar tabela professor: " + e.getMessage());
        }
    }
}
