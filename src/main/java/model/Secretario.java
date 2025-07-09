/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Secretario extends Funcionario {

    public Secretario(int id, String nome, String endereco, String telefone, double salario) {
        super(id, nome, endereco, telefone, salario, Cargo.SECRETARIO);
    }

    // Podemos adicionar métodos como:
    public void matricularAlunoEmTurma(Aluno aluno, Turma turma) {
        turma.matricularAluno(aluno);
    }
}

