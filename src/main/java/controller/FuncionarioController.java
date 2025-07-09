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

/**
 *
 * @author figueiredodavi
 */
public class FuncionarioController {
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    public void inserirFuncionario(int id, 
            String nome, 
            String endereco, 
            String telefone, 
            double salario,
            Cargo cargo
    ) {
        Funcionario funcionario;
        // Rule switch não precisa incluir "break;"
        switch(cargo) {
            case Cargo.GERENTE -> funcionario = new Gerente(id, nome, endereco, telefone, salario);
            case Cargo.SECRETARIO -> funcionario = new Secretario(id, nome, endereco, telefone, salario);
            default -> throw new IllegalArgumentException("Cargo inválido");
        }
        funcionarioDAO.inserir(funcionario);
    }
    
    public void atualizarFuncionario(int id, 
            String nome, 
            String endereco, 
            String telefone, 
            double salario,
            Cargo cargo
    ) {
        Funcionario funcionario;
        switch(cargo) {
            case Cargo.GERENTE -> funcionario = new Gerente(id, nome, endereco, telefone, salario);
            case Cargo.SECRETARIO -> funcionario = new Secretario(id, nome, endereco, telefone, salario);
            default -> throw new IllegalArgumentException("Cargo inválido");
        }
        funcionarioDAO.atualizar(funcionario);
    }
    
    public void excluirFuncionario(int id) {
        funcionarioDAO.excluir(id);
    }
}
