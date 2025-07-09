/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

public class Professor {
    private int matricula;
    private String nome;
    private String endereco;
    private String telefone;
    private double valorHora;
    private List<String> linguas;

    public Professor(int matricula, String nome, String endereco, String telefone, double valorHora, List<String> linguas) {
        this.matricula = matricula;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.valorHora = valorHora;
        this.linguas = linguas;
    }

    // Getters e Setters
    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public double getValorHora() { return valorHora; }
    public void setValorHora(double valorHora) { this.valorHora = valorHora; }

    public List<String> getLinguas() { return linguas; }
    public void setLinguas(List<String> linguas) { this.linguas = linguas; }
    
    @Override
    public String toString() { return matricula + " - " + nome;  }
}
