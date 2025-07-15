/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Turma {
    public enum Nivel { 
        INICIANTE("Iniciante"), 
        INTERMEDIARIO("Intermediário"), 
        AVANCADO("Avançado");
    
        private final String nome;
        Nivel(String nome) { this.nome = nome; }
        
        @Override
        public String toString() { return nome; }
        
        public static String allToString() {
            return Arrays.stream(Nivel.values())
                    .map(Nivel::toString)
                    .collect(Collectors.joining(", "));
        }
    }

    private int id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Lingua lingua;
    private Nivel nivel;
    private double preco;

    public Turma(int id, LocalDate dataInicio, LocalDate dataFim, Lingua lingua, Nivel nivel, double preco) {
        if (id <= 0) throw new IllegalArgumentException("ID deve ser positivo.");
        if (dataInicio == null) throw new IllegalArgumentException("Data de início obrigatória.");
        if (dataFim == null) throw new IllegalArgumentException("Data de fim obrigatória.");
        if (lingua == null) throw new IllegalArgumentException("Língua obrigatória.");
        if (nivel == null) throw new IllegalArgumentException("Nível obrigatório.");
        if (preco < 0) throw new IllegalArgumentException("Preço não pode ser negativo.");

        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.lingua = lingua;
        this.nivel = nivel;
        this.preco = preco;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID deve ser positivo.");
        this.id = id;
    }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    public Lingua getLingua() { return lingua; }
    public void setLingua(Lingua lingua) { this.lingua = lingua; }
    public Nivel getNivel() { return nivel; }
    public void setNivel(Nivel nivel) { this.nivel = nivel; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    @Override
    public String toString() {
        return id + " - " + lingua + " (" + nivel + ")";
    }
}