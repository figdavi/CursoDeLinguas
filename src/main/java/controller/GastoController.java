/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Gasto;
import dao.GastoDAO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author figueiredodavi
 */
public class GastoController {
    private GastoDAO gastoDAO = new GastoDAO();
    
    public List<Gasto> listarTodosGastos() {
        return gastoDAO.listarTodos();
    }
    
    public void inserirGasto(int id, String descricao, double valor, LocalDate data) {
        Gasto gasto = new Gasto(id, descricao, valor, data);
        gastoDAO.inserir(gasto);
    }
    
    public void atualizarGasto(int id, String descricao, double valor, LocalDate data) {
        Gasto gasto = new Gasto(id, descricao, valor, data);
        gastoDAO.atualizar(gasto);
    }
    
    public void excluirGasto(int id) {
        gastoDAO.excluir(id);
    }
}
