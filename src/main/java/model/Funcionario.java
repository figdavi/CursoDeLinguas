/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public abstract class Funcionario {
    // Protected porque pode ser acessado pelas subclasses (Secretário e Gerente)
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

        // Construção de Cargo é privada.
        Cargo(String nome) {
            this.nome = nome;
        }

        // .name(): "SECRETARIO"
        // .toString(): "Secretário"
        @Override
        public String toString() {
            return nome;
        }
    }
    
    public Funcionario(int id, String nome, String endereco, String telefone, double salario, Cargo cargo) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.salario = salario;
        this.cargo = cargo;
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getTelefone() { return telefone; }
    public double getSalario() { return salario; }
    public Cargo getCargo() { return cargo; }

    // Setters (adicione conforme necessário)
    public void setNome(String nome) { this.nome = nome; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setSalario(double salario) { this.salario = salario; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }
}


