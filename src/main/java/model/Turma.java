/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Turma {
    private int id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String lingua;
    private String nivel; // Iniciante, Intermediário, Avançado
    private double preco;
    private List<Aluno> alunosMatriculados;
    private Map<Aluno, Double> notasFinais;

    public Turma(int id, LocalDate dataInicio, LocalDate dataFim, String lingua, String nivel, double preco) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.lingua = lingua;
        this.nivel = nivel;
        this.preco = preco;
        this.alunosMatriculados = new ArrayList<>();
        this.notasFinais = new HashMap<>();
    }

    // Métodos de matrícula e notas
    public void matricularAluno(Aluno aluno) {
        if (!alunosMatriculados.contains(aluno)) {
            alunosMatriculados.add(aluno);
        }
    }

    public void registrarNota(Aluno aluno, double nota) {
        if (alunosMatriculados.contains(aluno)) {
            notasFinais.put(aluno, nota);
        }
    }

    public double getArrecadacaoTotal() {
        return preco * alunosMatriculados.size();
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public String getLingua() { return lingua; }
    public void setLingua(String lingua) { this.lingua = lingua; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public List<Aluno> getAlunosMatriculados() { return alunosMatriculados; }

    public Map<Aluno, Double> getNotasFinais() { return notasFinais; }
    
    @Override
    public String toString() { return id + " - " + lingua + " (" + nivel + ")";  }
}
