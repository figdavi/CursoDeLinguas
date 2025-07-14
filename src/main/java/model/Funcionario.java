/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

public abstract class Funcionario {
    protected int id;
    protected String nome;
    protected String endereco;
    protected String telefone;
    protected double salario;
    protected Cargo cargo;

    public enum Cargo {
        GERENTE("Gerente"),
        SECRETARIO("Secretário");

        private final String nome;
        Cargo(String nome) { this.nome = nome; }
        @Override
        public String toString() { return nome; }
    }

    public Funcionario(int id, String nome, String endereco, String telefone, double salario, Cargo cargo) {
        if (id <= 0) throw new IllegalArgumentException("ID deve ser positivo.");
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome obrigatório.");
        if (salario < 0) throw new IllegalArgumentException("Salário deve ser positivo.");
        if (cargo == null) throw new IllegalArgumentException("Cargo obrigatório.");
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.salario = salario;
        this.cargo = cargo;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getTelefone() { return telefone; }
    public double getSalario() { return salario; }
    public Cargo getCargo() { return cargo; }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome obrigatório.");
        this.nome = nome;
    }

    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setSalario(double salario) {
        if (salario < 0) throw new IllegalArgumentException("Salário deve ser positivo.");
        this.salario = salario;
    }
    public void setCargo(Cargo cargo) {
        if (cargo == null) throw new IllegalArgumentException("Cargo obrigatório.");
        this.cargo = cargo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Funcionario that = (Funcionario) obj;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " - " + nome + " (" + cargo + ")";
    }
}

