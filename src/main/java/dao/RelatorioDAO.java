/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;

public class RelatorioDAO {

    public static double calcularValorArrecadado(int mes, int ano) {
        String sql = """
            SELECT SUM(preco) FROM turma
            WHERE strftime('%m', dataInicio) = ? AND strftime('%Y', dataInicio) = ?
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, String.format("%02d", mes));
            pstmt.setString(2, String.valueOf(ano));
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getDouble(1) : 0.0;
        } catch (SQLException e) {
            System.out.println("Erro ao calcular valor arrecadado: " + e.getMessage());
            return 0.0;
        }
    }

    public static double calcularGastoRealizado(int mes, int ano) {
        String sql = """
            SELECT SUM(
                (julianday(horaFim) - julianday(horaInicio)) * 24 * p.valorHora
            )
            FROM aula a
            JOIN professor p ON a.professor_matricula = p.matricula
            WHERE a.professor_matricula IS NOT NULL
              AND strftime('%m', a.data) = ?
              AND strftime('%Y', a.data) = ?
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, String.format("%02d", mes));
            pstmt.setString(2, String.valueOf(ano));
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getDouble(1) : 0.0;
        } catch (SQLException e) {
            System.out.println("Erro ao calcular gasto realizado: " + e.getMessage());
            return 0.0;
        }
    }

    // Gasto ainda a acontecer = aulas agendadas SEM professor (usar valorHoraMédio)
    public static double calcularGastoPrevisto(int mes, int ano, double valorHoraMedio) {
        String sql = """
            SELECT SUM((julianday(horaFim) - julianday(horaInicio)) * 24)
            FROM aula
            WHERE professor_matricula IS NULL
              AND strftime('%m', data) = ?
              AND strftime('%Y', data) = ?
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, String.format("%02d", mes));
            pstmt.setString(2, String.valueOf(ano));
            ResultSet rs = pstmt.executeQuery();
            double horas = rs.next() ? rs.getDouble(1) : 0.0;
            return horas * valorHoraMedio;
        } catch (SQLException e) {
            System.out.println("Erro ao calcular gasto previsto: " + e.getMessage());
            return 0.0;
        }
    }
}
