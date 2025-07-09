/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Gasto;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GastoTableManager.criarTabelaGasto();

        GastoDAO dao = new GastoDAO();

        Gasto g1 = new Gasto(1, "Aluguel de sala", 1200.0, LocalDate.of(2025, 7, 10));
        Gasto g2 = new Gasto(2, "Compra de material", 300.0, LocalDate.of(2025, 7, 12));

        dao.inserir(g1);
        dao.inserir(g2);

        List<Gasto> lista = dao.listarTodos();
        for (Gasto g : lista) {
            System.out.println("ID: " + g.getId() + " | " + g.getDescricao() + " | R$" + g.getValor() + " | " + g.getData());
        }

        dao.excluir(1); // Excluir um dos gastos
    }
}


