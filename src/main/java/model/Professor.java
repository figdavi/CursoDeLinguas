/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import java.util.Objects;

public class Professor {
    private int matricula;
    private String nome;
    private String endereco;
    private String telefone;
    private double valorHora;
    private List<Lingua> linguas;

    public Professor(int matricula, String nome, String endereco, String telefone, double valorHora, List<Lingua> linguas) {
        if (matricula <= 0) throw new IllegalArgumentException("Matrícula deve ser positiva.");
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome obrigatório.");
        if (valorHora < 0) throw new IllegalArgumentException("Valor da hora deve ser positivo.");
        if (linguas == null || linguas.isEmpty()) throw new IllegalArgumentException("Professor deve ter ao menos uma língua.");
        this.matricula = matricula;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.valorHora = valorHora;
        this.linguas = linguas;
    }

    // Getters e Setters
    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) {
        if (matricula <= 0) throw new IllegalArgumentException("Matrícula deve ser positiva.");
        this.matricula = matricula;
    }
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome obrigatório.");
        this.nome = nome;
    }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public double getValorHora() { return valorHora; }
    public void setValorHora(double valorHora) {
        if (valorHora < 0) throw new IllegalArgumentException("Valor da hora deve ser positivo.");
        this.valorHora = valorHora;
    }
    public List<Lingua> getLinguas() { return linguas; }
    public void setLinguas(List<Lingua> linguas) {
        if (linguas == null || linguas.isEmpty()) throw new IllegalArgumentException("Professor deve ter ao menos uma língua.");
        this.linguas = linguas;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Professor that = (Professor) obj;
        return matricula == that.matricula;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return matricula + " - " + nome;
    }
}