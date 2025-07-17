/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.RelatorioDAO;

public class RelatorioController {

    public double calcularMensalReceitaArrecadada(int mes, int ano) {
        return RelatorioDAO.calcularMensalReceitaArrecadada(mes, ano);
    }

    public double calcularMensalGastoRealizado(int mes, int ano) {
        return RelatorioDAO.calcularMensalGastoRealizado(mes, ano);
    }

    public double calcularMensalGastoPrevisto(int mes, int ano) {
        return RelatorioDAO.calcularMensalGastoPrevisto(mes, ano);
    }
    
    public double calcularAnualReceitaArrecadada(int ano) {
        return RelatorioDAO.calcularAnualReceitaArrecadada(ano);
    }
    
    public double calcularAnualReceitaPrevista(int ano) {
        return RelatorioDAO.calcularAnualReceitaPrevista(ano);
    }

    public double calcularAnualGastoRealizado(int ano) {
        return RelatorioDAO.calcularAnualGastoRealizado(ano);
    }

    public double calcularAnualGastoPrevisto(int ano) {
        return RelatorioDAO.calcularAnualGastoPrevisto(ano);
    }
}
