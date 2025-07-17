/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.time.LocalDate;

public class RelatorioDAO {

    public static double calcularMensalReceitaArrecadada(int mes, int ano) {
        // Calcula o primeiro e último dia do mês selecionado
        LocalDate primeiroDia = LocalDate.of(ano, mes, 1);
        LocalDate ultimoDia = primeiroDia.withDayOfMonth(primeiroDia.lengthOfMonth());

        String sql = """
            SELECT SUM(t.preco * (
                SELECT COUNT(*) FROM turma_aluno ta WHERE ta.turma_id = t.id
            ))
            FROM turma t
            WHERE t.dataInicio <= ?
              AND t.dataFim >= ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ultimoDia.toString());     // t.dataInicio <= último dia do mês
            pstmt.setString(2, primeiroDia.toString());  // t.dataFim >= primeiro dia do mês

            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getDouble(1) : 0.0;
        } catch (SQLException e) {
            System.out.println("Erro ao calcular valor arrecadado: " + e.getMessage());
            return 0.0;
        }
    }

    public static double calcularMensalGastoRealizado(int mes, int ano) {
        String sqlHorasAulas = """
            SELECT SUM((julianday(horaFim) - julianday(horaInicio)) * 24)
            FROM aula
            WHERE strftime('%m', data) = ? 
              AND strftime('%Y', data) = ?
              AND date(data) <= date('now')
        """;

        String sqlGastosProfessores = """
            SELECT SUM((julianday(horaFim) - julianday(horaInicio)) * 24 * p.valorHora)
            FROM aula a
            JOIN professor p ON a.professor_matricula = p.matricula
            WHERE a.professor_matricula IS NOT NULL
              AND strftime('%m', a.data) = ? 
              AND strftime('%Y', a.data) = ?
        """;

        String sqlGastosManuais = """
            SELECT SUM(valor)
            FROM gasto
            WHERE strftime('%m', data) = ? 
              AND strftime('%Y', data) = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmtHorasAulas = conn.prepareStatement(sqlHorasAulas);
             PreparedStatement pstmtProfessores = conn.prepareStatement(sqlGastosProfessores);
             PreparedStatement pstmtManuais = conn.prepareStatement(sqlGastosManuais)) {

            String mesStr = String.format("%02d", mes);
            String anoStr = String.valueOf(ano);

            // Horas totais das aulas
            pstmtHorasAulas.setString(1, mesStr);
            pstmtHorasAulas.setString(2, anoStr);
            ResultSet rsHoras = pstmtHorasAulas.executeQuery();
            double horasAulas = rsHoras.next() ? rsHoras.getDouble(1) : 0.0;
            
            // GastosAulas
            double gastoFixoAulas = horasAulas * model.Aula.getGastoFixoAula();

            // GastosProfessores
            pstmtProfessores.setString(1, mesStr);
            pstmtProfessores.setString(2, anoStr);
            ResultSet rsProf = pstmtProfessores.executeQuery();
            double gastoProfessores = rsProf.next() ? rsProf.getDouble(1) : 0.0;

            // GastosManuais
            pstmtManuais.setString(1, mesStr);
            pstmtManuais.setString(2, anoStr);
            ResultSet rsManuais = pstmtManuais.executeQuery();
            double gastoManuais = rsManuais.next() ? rsManuais.getDouble(1) : 0.0;

            return gastoFixoAulas + gastoProfessores + gastoManuais;

        } catch (SQLException e) {
            System.out.println("Erro ao calcular gasto realizado: " + e.getMessage());
            return 0.0;
        }
    }

    // Gasto ainda a acontecer = aulas agendadas SEM professor
    public static double calcularMensalGastoPrevisto(int mes, int ano) {
        String sql = """
            SELECT SUM((julianday(horaFim) - julianday(horaInicio)) * 24)
            FROM aula
            WHERE professor_matricula IS NULL
              AND strftime('%m', data) = ?
              AND strftime('%Y', data) = ?
              AND date(data) > date('now')
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, String.format("%02d", mes));
            pstmt.setString(2, String.valueOf(ano));
            ResultSet rs = pstmt.executeQuery();
            double horas = rs.next() ? rs.getDouble(1) : 0.0;
            return horas * model.Aula.getGastoFixoAula();
        } catch (SQLException e) {
            System.out.println("Erro ao calcular gasto previsto: " + e.getMessage());
            return 0.0;
        }
    }
    
    
    public static double calcularAnualReceitaArrecadada(int ano) {
        double resultado = 0;
        LocalDate hoje = LocalDate.now();
        if(ano == hoje.getYear()) {
            int mesAtual = hoje.getMonthValue();
            for(int i = 1; i <= mesAtual; i++) {
                resultado += calcularMensalReceitaArrecadada(i, ano);
            }
        } else if (ano < hoje.getYear()) {
            for(int i = 1; i < 13; i++) {
                resultado += calcularMensalReceitaArrecadada(i, ano);
            }
        }

        return resultado;
    }
    
    public static double calcularAnualReceitaPrevista(int ano) {
        double resultado = 0;
        LocalDate hoje = LocalDate.now();
        if(ano == hoje.getYear()) {
            int mesAtual = hoje.getMonthValue();
            for(int i = (mesAtual+1); i < 13 ; i++) {
                resultado += calcularMensalReceitaArrecadada(i, ano);
            }
        } else if (ano > hoje.getYear()) {
            for(int i = 1; i < 13; i++) {
                resultado += calcularMensalReceitaArrecadada(i, ano);
            }
        }
        
        return resultado;
    }

    public static double calcularAnualGastoRealizado(int ano) {
        String sqlHorasAulas = """
            SELECT SUM((julianday(horaFim) - julianday(horaInicio)) * 24)
            FROM aula
            WHERE strftime('%Y', data) = ? 
              AND date(data) <= date('now')
        """;

        String sqlGastosProfessores = """
            SELECT SUM((julianday(horaFim) - julianday(horaInicio)) * 24 * p.valorHora)
            FROM aula a
            JOIN professor p ON a.professor_matricula = p.matricula
            WHERE a.professor_matricula IS NOT NULL
              AND strftime('%Y', a.data) = ?
        """;

        String sqlGastosManuais = """
            SELECT SUM(valor)
            FROM gasto
            WHERE strftime('%Y', data) = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmtHorasAulas = conn.prepareStatement(sqlHorasAulas);
             PreparedStatement pstmtProfessores = conn.prepareStatement(sqlGastosProfessores);
             PreparedStatement pstmtManuais = conn.prepareStatement(sqlGastosManuais)) {

            String anoStr = String.valueOf(ano);

            // Horas totais das aulas
            pstmtHorasAulas.setString(1, anoStr);
            ResultSet rsHoras = pstmtHorasAulas.executeQuery();
            double horasAulas = rsHoras.next() ? rsHoras.getDouble(1) : 0.0;
            
            // GastosAulas
            double gastoFixoAulas = horasAulas * model.Aula.getGastoFixoAula();

            // GastosProfessores
            pstmtProfessores.setString(1, anoStr);
            ResultSet rsProf = pstmtProfessores.executeQuery();
            double gastoProfessores = rsProf.next() ? rsProf.getDouble(1) : 0.0;

            // GastosManuais
            pstmtManuais.setString(1, anoStr);
            ResultSet rsManuais = pstmtManuais.executeQuery();
            double gastoManuais = rsManuais.next() ? rsManuais.getDouble(1) : 0.0;

            return gastoFixoAulas + gastoProfessores + gastoManuais;

        } catch (SQLException e) {
            System.out.println("Erro ao calcular gasto realizado: " + e.getMessage());
            return 0.0;
        }
    }

    public static double calcularAnualGastoPrevisto(int ano) {
        String sql = """
            SELECT SUM((julianday(horaFim) - julianday(horaInicio)) * 24)
            FROM aula
            WHERE professor_matricula IS NULL
              AND strftime('%Y', data) = ?
              AND date(data) > date('now')
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, String.valueOf(ano));
            ResultSet rs = pstmt.executeQuery();
            double horas = rs.next() ? rs.getDouble(1) : 0.0;
            return horas * model.Aula.getGastoFixoAula();
        } catch (SQLException e) {
            System.out.println("Erro ao calcular gasto previsto: " + e.getMessage());
            return 0.0;
        }
    }
}
