/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TurmaTableManager {

    public static void criarTabelasTurma() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Tabela principal de turma
            String sqlTurma = """
                CREATE TABLE IF NOT EXISTS turma (
                    id INTEGER PRIMARY KEY,
                    dataInicio TEXT NOT NULL,
                    dataFim TEXT NOT NULL,
                    lingua TEXT NOT NULL,
                    nivel TEXT NOT NULL,
                    preco REAL NOT NULL
                );
            """;

            // Tabela de alunos matriculados na turma
            String sqlTurmaAluno = """
                CREATE TABLE IF NOT EXISTS turma_aluno (
                    turma_id INTEGER,
                    aluno_matricula INTEGER,
                    PRIMARY KEY (turma_id, aluno_matricula),
                    FOREIGN KEY (turma_id) REFERENCES turma(id) ON DELETE CASCADE,
                    FOREIGN KEY (aluno_matricula) REFERENCES aluno(matricula)
                );
            """;

            // Tabela de notas finais dos alunos
            String sqlNotaFinal = """
                CREATE TABLE IF NOT EXISTS nota_final (
                    turma_id INTEGER,
                    aluno_matricula INTEGER,
                    nota REAL NOT NULL,
                    PRIMARY KEY (turma_id, aluno_matricula),
                    FOREIGN KEY (turma_id) REFERENCES turma(id) ON DELETE CASCADE,
                    FOREIGN KEY (aluno_matricula) REFERENCES aluno(matricula) ON DELETE CASCADE
                );
            """;

            stmt.execute(sqlTurma);
            stmt.execute(sqlTurmaAluno);
            stmt.execute(sqlNotaFinal);

            System.out.println("Tabelas 'turma', 'turma_aluno' e 'nota_final' criadas/verificadas.");

        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas da turma: " + e.getMessage());
        }
    }
}
