/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AlunoTableManager {

    public static void criarTabelaAluno() {
        String sql = """
            CREATE TABLE IF NOT EXISTS aluno (
                matricula INTEGER PRIMARY KEY,
                nome TEXT NOT NULL,
                endereco TEXT NOT NULL,
                telefone TEXT NOT NULL,
                email TEXT NOT NULL
            );
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela 'aluno' verificada/criada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar/verificar tabela aluno: " + e.getMessage());
        }
    }
}
