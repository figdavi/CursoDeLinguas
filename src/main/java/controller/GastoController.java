/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Gasto;
import dao.GastoDAO;
import java.time.LocalDate;
import java.util.List;

public class GastoController {
    private final GastoDAO gastoDAO = new GastoDAO();

    public List<Gasto> listarTodosGastos() {
        return gastoDAO.listarTodos();
    }

    public Gasto buscarGastoPorId(int id) {
        return gastoDAO.buscarPorId(id);
    }

    public String inserirGasto(int id, String descricao, double valor, LocalDate data) {
        if (id <= 0) return "ID deve ser positivo.";
        if (descricao == null || descricao.trim().isEmpty()) return "Descrição obrigatória.";
        if (valor < 0) return "Valor deve ser positivo.";
        if (data == null) return "Data obrigatória.";

        if (gastoDAO.buscarPorId(id) != null)
            return "Já existe um gasto com esse ID.";

        Gasto gasto = new Gasto(id, descricao, valor, data);
        boolean sucesso = gastoDAO.inserir(gasto);
        return sucesso ? "Gasto cadastrado com sucesso!" : "Erro ao cadastrar gasto.";
    }

    public String atualizarGasto(int id, String descricao, double valor, LocalDate data) {
        if (id <= 0) return "ID deve ser positivo.";
        if (descricao == null || descricao.trim().isEmpty()) return "Descrição obrigatória.";
        if (valor < 0) return "Valor deve ser positivo.";
        if (data == null) return "Data obrigatória.";

        if (gastoDAO.buscarPorId(id) == null)
            return "Gasto não encontrado para atualização.";

        Gasto gasto = new Gasto(id, descricao, valor, data);
        boolean sucesso = gastoDAO.atualizar(gasto);
        return sucesso ? "Gasto atualizado com sucesso!" : "Erro ao atualizar gasto.";
    }

    public String excluirGasto(int id) {
        boolean sucesso = gastoDAO.excluir(id);
        return sucesso ? "Gasto excluído com sucesso!" : "Erro ao excluir gasto (ID não encontrado).";
    }
}