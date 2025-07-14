/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Representa um Secretário da escola de idiomas.
 * Herda de Funcionario e tem permissões voltadas à gestão operacional:
 * cadastro/edição/exclusão de alunos, turmas e aulas.
 * 
 * OBS: Os métodos são ilustrativos e não têm implementação real de banco.
 */
public class Secretario extends Funcionario {

    public Secretario(int id, String nome, String endereco, String telefone, double salario) {
        super(id, nome, endereco, telefone, salario, Cargo.SECRETARIO);
    }

    // -------- Métodos ilustrativos de permissões do Secretário --------

    /**
     * Inclui uma turma no sistema.
     */
    public void incluirTurma(String turmaInfo) {
        System.out.println("[Secretario] Turma incluída: " + turmaInfo);
    }

    public void alterarTurma(String turmaInfo) {
        System.out.println("[Secretario] Turma alterada: " + turmaInfo);
    }

    public void excluirTurma(String turmaInfo) {
        System.out.println("[Secretario] Turma excluída: " + turmaInfo);
    }

    public void incluirAula(String aulaInfo) {
        System.out.println("[Secretario] Aula incluída: " + aulaInfo);
    }

    public void alterarAula(String aulaInfo) {
        System.out.println("[Secretario] Aula alterada: " + aulaInfo);
    }

    public void excluirAula(String aulaInfo) {
        System.out.println("[Secretario] Aula excluída: " + aulaInfo);
    }

    public void incluirAluno(String alunoInfo) {
        System.out.println("[Secretario] Aluno incluído: " + alunoInfo);
    }

    public void alterarAluno(String alunoInfo) {
        System.out.println("[Secretario] Aluno alterado: " + alunoInfo);
    }

    public void excluirAluno(String alunoInfo) {
        System.out.println("[Secretario] Aluno excluído: " + alunoInfo);
    }

    public void matricularAlunoEmTurma(String alunoInfo, String turmaInfo) {
        System.out.println("[Secretario] Aluno " + alunoInfo + " matriculado na turma " + turmaInfo + ".");
    }

    /**
     * Simulação de relatório das permissões do secretário.
     */
    public void listarPermissoes() {
        System.out.println("[Secretario] Permissões: Cadastro/alteração/exclusão de Aluno, Turma e Aula.");
    }
}
