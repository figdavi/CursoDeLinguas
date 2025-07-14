/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Representa um Gerente da escola de idiomas.
 * Herda de Funcionario e possui permissões administrativas e financeiras.
 * 
 * OBS: Métodos ilustrativos para simulação de permissões.
 */
public class Gerente extends Funcionario {

    public Gerente(int id, String nome, String endereco, String telefone, double salario) {
        super(id, nome, endereco, telefone, salario, Cargo.GERENTE);
    }

    // -------- Métodos ilustrativos de permissões do Gerente --------

    public void fazerPagamento(String destinatario, double valor) {
        System.out.println("[Gerente] Pagamento de R$" + valor + " realizado para " + destinatario + ".");
    }

    public void consultarRelatorioFinanceiro(String periodo) {
        System.out.println("[Gerente] Consultando relatório financeiro: " + periodo);
    }

    public void cadastrarGasto(String descricao, double valor) {
        System.out.println("[Gerente] Gasto cadastrado: " + descricao + ", R$" + valor);
    }

    public void editarGasto(int idGasto, String novaDescricao, double novoValor) {
        System.out.println("[Gerente] Gasto " + idGasto + " alterado para: " + novaDescricao + ", R$" + novoValor);
    }

    public void excluirGasto(int idGasto) {
        System.out.println("[Gerente] Gasto " + idGasto + " excluído.");
    }

    public void incluirFuncionario(String nome) {
        System.out.println("[Gerente] Funcionário incluído: " + nome);
    }

    public void editarFuncionario(String nome) {
        System.out.println("[Gerente] Funcionário editado: " + nome);
    }

    public void excluirFuncionario(String nome) {
        System.out.println("[Gerente] Funcionário excluído: " + nome);
    }

    public void incluirProfessor(String nome) {
        System.out.println("[Gerente] Professor incluído: " + nome);
    }

    public void editarProfessor(String nome) {
        System.out.println("[Gerente] Professor editado: " + nome);
    }

    public void excluirProfessor(String nome) {
        System.out.println("[Gerente] Professor excluído: " + nome);
    }

    /**
     * Simulação de relatório das permissões do gerente.
     */
    public void listarPermissoes() {
        System.out.println("[Gerente] Permissões: Gerência financeira, RH, Cadastro/edição/exclusão de Professores e Funcionários, acesso a relatórios.");
    }
}
