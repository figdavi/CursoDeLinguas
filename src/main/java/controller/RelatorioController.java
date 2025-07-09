/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.RelatorioDAO;

/**
 *
 * @author figueiredodavi
 */
public class RelatorioController {
    public double calcularValorArrecadado(int mes, int ano) {
        return RelatorioDAO.calcularValorArrecadado(mes, ano);
    }
    public double calcularGastoRealizado(int mes, int ano) {
        return RelatorioDAO.calcularGastoRealizado(mes, ano);
    }
    public double calcularGastoPrevisto(int mes, int ano, double valorHora) {
        return RelatorioDAO.calcularGastoPrevisto(mes, ano, 50.0);
    }
}
