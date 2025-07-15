/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Funcionario;
import model.Gerente;
import model.Secretario;
import model.Funcionario.Cargo;
import dao.FuncionarioDAO;
import java.util.List;

public class FuncionarioController {
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public List<Funcionario> listarTodosFuncionarios() {
        return funcionarioDAO.listarTodos();
    }

    public String inserirFuncionario(int id, String nome, String endereco, String telefone, double salario, Cargo cargo) {
        if (id <= 0) return "ID deve ser positivo.";
        if (nome == null || nome.trim().isEmpty()) return "Nome obrigatório.";
        if (endereco == null || endereco.trim().isEmpty()) return "Endereço obrigatório.";
        if (telefone == null || telefone.trim().isEmpty()) return "Telefone obrigatório.";
        if (salario < 0) return "Salário deve ser positivo.";
        if (cargo == null) return "Cargo obrigatório.";
        if (funcionarioDAO.buscarPorId(id) != null) return "Já existe funcionário com esse ID.";

        Funcionario funcionario;
        switch(cargo) {
            case GERENTE -> funcionario = new Gerente(id, nome, endereco, telefone, salario);
            case SECRETARIO -> funcionario = new Secretario(id, nome, endereco, telefone, salario);
            default -> throw new IllegalArgumentException("Cargo inválido.");
        }
        boolean ok = funcionarioDAO.inserir(funcionario);
        return ok ? "Funcionário cadastrado com sucesso!" : "Erro ao cadastrar funcionário.";
    }

    public String atualizarFuncionario(int id, String nome, String endereco, String telefone, double salario, Cargo cargo) {
        if (id <= 0) return "ID deve ser positivo.";
        if (nome == null || nome.trim().isEmpty()) return "Nome obrigatório.";
        if (endereco == null || endereco.trim().isEmpty()) return "Endereço obrigatório.";
        if (telefone == null || telefone.trim().isEmpty()) return "Telefone obrigatório.";
        if (salario < 0) return "Salário deve ser positivo.";
        if (cargo == null) return "Cargo obrigatório.";
        if (funcionarioDAO.buscarPorId(id) == null) return "Funcionário não encontrado para atualização.";

        Funcionario funcionario;
        switch(cargo) {
            case GERENTE -> funcionario = new Gerente(id, nome, endereco, telefone, salario);
            case SECRETARIO -> funcionario = new Secretario(id, nome, endereco, telefone, salario);
            default -> throw new IllegalArgumentException("Cargo inválido.");
        }
        boolean ok = funcionarioDAO.atualizar(funcionario);
        return ok ? "Funcionário atualizado com sucesso!" : "Erro ao atualizar funcionário.";
    }

    public String excluirFuncionario(int id) {
        boolean ok = funcionarioDAO.excluir(id);
        return ok ? "Funcionário excluído com sucesso!" : "Erro ao excluir funcionário (ID não encontrado).";
    }

    public Funcionario buscarFuncionarioPorId(int id) {
        return funcionarioDAO.buscarPorId(id);
    }
}