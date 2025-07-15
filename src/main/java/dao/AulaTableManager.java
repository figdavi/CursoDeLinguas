/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AulaTableManager {

    public static void criarTabelaAula() {
        String sql = """
            CREATE TABLE IF NOT EXISTS aula (
                id INTEGER PRIMARY KEY,
                turma_id INTEGER NOT NULL,
                data TEXT NOT NULL,
                horaInicio TEXT NOT NULL,
                horaFim TEXT NOT NULL,
                professor_matricula INTEGER,
                FOREIGN KEY (turma_id) REFERENCES turma(id) ON DELETE CASCADE,
                FOREIGN KEY (professor_matricula) REFERENCES professor(matricula)
            );
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela 'aula' criada/verificada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela aula: " + e.getMessage());
        }
    }
}
