/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Gerente extends Funcionario {

    public Gerente(int id, String nome, String endereco, String telefone, double salario) {
        super(id, nome, endereco, telefone, salario, Cargo.GERENTE);
    }

    // Métodos específicos
    public void fazerPagamento(double valor) {
        // lógica fictícia para simular um pagamento
        System.out.println("Pagamento de R$ " + valor + " realizado.");
    }
}

